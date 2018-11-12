package me.xdrop.fuzzywuzzy.model;

/**
 * A referent to an input item.
 *
 * @param <T> The type of the referent.
 */
public class Result<T> implements Comparable<Result<T>> {
    private final T referent;
    private final String string;
    private final int score;
    private final int originIndex;
    private boolean isStringReferent;

    /**
     * Creates a new instance for a result from a list.
     * Mostly used internally.
     *
     * @param referent    The referent object.
     * @param string      The representation string.
     * @param score       The score of this result.
     * @param originIndex The originIndex of this result.
     * @throws IllegalArgumentException If the referent is a {@link String} and is not equal to the {@code string} value.
     */
    public Result(T referent, String string, int score, int originIndex) throws IllegalArgumentException {
        if ((isStringReferent = (referent.getClass() == String.class)) && !referent.equals(string))
            throw new IllegalArgumentException("When the referent is a String, it must be equal to the string value!");
        this.referent = referent;
        this.string = string;
        this.score = score;
        this.originIndex = originIndex;
    }

    /**
     * Creates a new instance for a single request.
     * Mostly used internally.
     *
     * @param referent The referent object.
     * @param string The representation string.
     * @param score The score of this result.
     * @throws IllegalArgumentException If the referent is a {@link String} and is not equal to the {@code string} value.
     */
    public Result(T referent, String string, int score) throws IllegalArgumentException {
        this(referent, string, score, -1);
    }

    /**
     * Gets the referent of this result.
     *
     * @return The referent.
     */
    public T getReferent() {
        return referent;
    }

    /**
     * Gets the string of this result.
     *
     * @return The string.
     */
    public String getString() {
        return string;
    }

    /**
     * Gets the score of this result.
     *
     * @return The score.
     */
    public int getScore() {
        return score;
    }

    /**
     * If the issued search was a list, gets the origin index of this result, otherwise, returns {@code -1}.
     *
     * @return The origin index.
     */
    public int getOriginIndex() {
        return originIndex;
    }

    @Override
    public int compareTo(Result other) {
        return Integer.compare(other.score, score);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Result)) return false;
        final Result res = (Result) other;
        return res.isStringReferent == isStringReferent && res.string.equals(string);
    }

    @Override
    public String toString() {
        return String.format("Result" + (isStringReferent ? "" : "@referent=%s") +
                "[string=%s;score=%d" + (originIndex == -1 ? "" : ";originIndex=%d") + "]#%d", getFormatArgs());
    }

    private Object[] getFormatArgs() {
        int size = 3;

        if (isStringReferent) size++;
        if (originIndex > -1) size++;

        Object[] arg = new Object[size];
        int i = 0;

        if (isStringReferent) arg[i++] = referent;
        arg[i++] = string;
        arg[i++] = score;
        if (originIndex > -1) arg[i++] = originIndex;
        arg[i] = hashCode();

        return arg;
    }
}