package me.xdrop.fuzzywuzzy.rymm.matrix;

import jdk.nashorn.internal.objects.NativeBoolean;

import static me.xdrop.fuzzywuzzy.Util.*;

public class Substitution extends CostMatrix {
    public static final int[][] MATRIX;

    static {
        int[][] qwerty = new int[][]{
                new int[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
                new int[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'},
                new int[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'}
        };

        MATRIX = new int[26][];
        for (final int[] row : qwerty) {
            for (final int c : row) {
                int[] p = new int[0];
                for (final int x : intRange(-1, 1))
                    for (final int y : intRange(-1, 1))
                        if ((x < qwerty.length && x > -1) && (y < row.length && y > -1))
                            p = addIntArr(p, qwerty[x][y]);
                MATRIX[c-97] = p;
            }
        }
    }

    @Override
    public int cost(final char expected, final char actual) {
        return findIntArr(MATRIX[Character.toLowerCase(expected)-97],
                Character.toLowerCase(actual)-97, -1) == -1
                ? 3 : 1;
    }

    private static int findIntArr(int[] arr, int find, int or) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == find) return i;
        return or;
    }

    private static int[] addIntArr(int[] arr, int add) {
        int[] ret = new int[arr.length + 1];
        System.arraycopy(arr, 0, ret, 0, arr.length);
        ret[ret.length - 1] = add;
        return ret;
    }
}
