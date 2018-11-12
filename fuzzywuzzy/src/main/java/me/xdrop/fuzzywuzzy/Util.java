package me.xdrop.fuzzywuzzy;

public class Util {
    public static <T> T instantiateNoArgs(Class<T> tClass) {
        try {
            return tClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
