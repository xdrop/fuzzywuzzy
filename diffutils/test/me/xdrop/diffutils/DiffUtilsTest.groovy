package me.xdrop.diffutils

class DiffUtilsTest extends GroovyTestCase {


    def generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    void testGetEditDistance() {

        assertEquals 11, DiffUtils.levEditDistance("sf&t co., ltd.","sft", 0)

    }

    void testGetRatio() {

        def s1 = "abxcd";
        def s2 = "abcd";

        assertEquals(0.88888, DiffUtils.getRatio(s1,s2), 0.01)
        Random rnd = new Random();
        String chars = "qwertyuiop[]#';lkjhgfdsazxcvbnm,./\\`#QWERTYUIOPLKJHGFDSAZXCVBNM1234567890";

        for (int i = 0; i < 1000; i++){
            int len = rnd.nextInt(200)
            assertNotNull DiffUtils.getRatio(generateString(rnd, chars, len),generateString(rnd, chars, i));
        }

        assertEquals 0.61, DiffUtils.getRatio( "fuzzy bearrrrrlll", "fuzzy was a bear"), 0.01


    }


}
