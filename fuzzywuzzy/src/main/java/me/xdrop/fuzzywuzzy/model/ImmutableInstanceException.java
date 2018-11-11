package me.xdrop.fuzzywuzzy.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Thrown when a class instance is mutated.
 */
public class ImmutableInstanceException extends RuntimeException {
    public <T> ImmutableInstanceException(Class<T> tClass) {
        super("Instance is not mutable; use one of these constructors instead: "+ alternatives(tClass));
    }

    private static <T> String alternatives(Class<T> tClass) {
        List<Constructor> ls = new ArrayList<>();
        for (Constructor<?> constr : tClass.getConstructors()) {
            if (Modifier.isPublic(constr.getModifiers())) {
                ls.add(constr);
            }
        }
        StringBuilder sb = new StringBuilder("[");
        Iterator<Constructor> iter = ls.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next().toGenericString());
            if (iter.hasNext()) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
