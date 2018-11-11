package me.xdrop.fuzzywuzzy;

public class FuzzyWuzzy {
    public static <A extends Algorithm> A algorithm(Class<A> ofClass) {
        try {
            return ofClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new AssertionError(e); // this should never be reached
        }
    }
}