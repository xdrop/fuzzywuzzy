package me.xdrop.diffutils;

import java.util.Random;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiffUtilsTest {
    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    @Test
    public void testGetEditDistance() {
        assertEquals(11, DiffUtils.levEditDistance("sf&t co., ltd.", "sft", 0));
    }

    @Test
    public void testGetRatio() {
        String s1 = "abxcd";
        String s2 = "abcd";

        assertEquals(0.88888, DiffUtils.getRatio(s1, s2), 0.01);
        Random rnd = new Random();
        String chars = "qwertyuiop[]#';lkjhgfdsazxcvbnm,./\\`#QWERTYUIOPLKJHGFDSAZXCVBNM1234567890";

        for (int i = 0; i < 1000; i++) {
            int len = rnd.nextInt(200);
            //noinspection ObviousNullCheck
            assertNotNull(DiffUtils.getRatio(generateString(rnd, chars, len), generateString(rnd, chars, i)));
        }

        assertEquals(0.61, DiffUtils.getRatio("fuzzy bearrrrrlll", "fuzzy was a bear"), 0.01);
    }
}
