package me.xdrop.matchr.fuzzywuzzy.levenshtein

import org.junit.Test

class LevenshteinTest extends GroovyTestCase {
    def static generateString(Random rng, String characters, int length) {
        char[] text = new char[length]
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()))
        }
        return new String(text)
    }

    @Test
    void testGetEditDistance() {
        assertEquals 11, Levenshtein.levEditDistance("sf&t co., ltd.","sft", 0)
    }

    @Test
    void testGetRatio() {
        def s1 = "abxcd"
        def s2 = "abcd"

        assertEquals(0.88888, Levenshtein.getRatio(s1,s2), 0.01)
        Random rnd = new Random()
        String chars = "qwertyuiop[]#';lkjhgfdsazxcvbnm,./\\`#QWERTYUIOPLKJHGFDSAZXCVBNM1234567890"

        for (int i = 0; i < 1000; i++){
            int len = rnd.nextInt(200)
            assertNotNull Levenshtein.getRatio(generateString(rnd, chars, len),generateString(rnd, chars, i))
        }

        assertEquals 0.61, Levenshtein.getRatio( "fuzzy bearrrrrlll", "fuzzy was a bear"), 0.01
    }
}
