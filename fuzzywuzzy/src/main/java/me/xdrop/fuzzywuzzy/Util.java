package me.xdrop.fuzzywuzzy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import me.xdrop.fuzzywuzzy.functions.StringMapper;
import me.xdrop.fuzzywuzzy.model.Result;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

/**
 * Utilites class
 */
public class Util {
    /**
     * Implementation of K Heap Sort.
     * Used by {@link FuzzyWuzzy#extractLimited(String, Collection, StringMapper, ScoringMethod, int, int)}.
     * THE COMPARATOR IS REVERSED!
     *
     * @param arr List of results.
     * @param k   Limit.
     * @param <T> Type-variable for Results.
     * @return The limited, sorted list of results.
     */
    public static <T extends Result> Collection<T> extractLimitedKHeap(Collection<T> arr, int k) {
        PriorityQueue<T> pq = new PriorityQueue<>();

        for (T x : arr) {
            if (pq.size() < k) pq.add(x);
            else if (x.compareTo(pq.peek()) > 0) {
                pq.poll();
                pq.add(x);
            }
        }

        Collection<T> res = Util.sameTypeCollection(arr);

        for (int i = k; i > 0; i--) {
            T polled = pq.poll();
            if (polled != null) {
                res.add(polled);
            }
        }

        return res;
    }

    public static <T extends Comparable<T>> Collection<T> sortAndReverse(Collection<T> coll) {
        if (coll instanceof List) {
            Collections.sort((List) coll);
            Collections.reverse((List) coll);
            return coll;
        } else return coll;
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Collection<R> sameTypeCollection(Collection<T> coll) {
        try {
            return coll.getClass().getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            try {
                if (coll instanceof Set) return HashSet.class.getConstructor().newInstance();
                else if (coll instanceof Queue) return PriorityQueue.class.getConstructor().newInstance();
                else return ArrayList.class.getConstructor().newInstance();
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
