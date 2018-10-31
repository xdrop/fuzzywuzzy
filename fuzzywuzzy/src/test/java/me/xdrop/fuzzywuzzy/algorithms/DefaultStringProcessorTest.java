package me.xdrop.fuzzywuzzy.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultStringProcessorTest {
    @Test
    public void testProcess() {
        String inp = "s.trim μεγιουνικουντ n/o/n a.lph.a n.um";

        assertEquals("s trim μεγιουνικουντ n o n a lph a n um", new DefaultStringFunction().apply(inp));
    }
}
