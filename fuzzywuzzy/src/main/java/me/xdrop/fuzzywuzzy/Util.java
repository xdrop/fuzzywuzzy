package me.xdrop.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import me.xdrop.fuzzywuzzy.functions.StringMapper;
import me.xdrop.fuzzywuzzy.model.Result;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

/**
 * Utilites class
 */
public class Util {
    /**
     * Implementation of K Heap Sort.
     * Used by {@link FuzzyWuzzy#extractLimited(Object, Collection, StringMapper, ScoringMethod, int, int)}.
     * THE COMPARATOR IS REVERSED!
     *
     * @param arr List of results.
     * @param k Limit.
     * @param <T> Type-variable for Results.
     * @return The limited, sorted list of results.
     */
    public static <T extends Result> List<T> extractLimitedKHeap(List<T> arr, int k) {
        PriorityQueue<T> pq = new PriorityQueue<>();

        for (T x : arr) {
            if (pq.size() < k) pq.add(x);
            else if (x.compareTo(pq.peek()) > 0) {
                pq.poll();
                pq.add(x);
            }
        }

        List<T> res = new ArrayList<>();

        for (int i = k; i > 0; i--) {
            T polled = pq.poll();
            if (polled != null) {
                res.add(polled);
            }
        }

        return res;
    }

    public static <T extends Comparable<T>> List<T> sortAndReverse(List<T> list) {
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }
}
