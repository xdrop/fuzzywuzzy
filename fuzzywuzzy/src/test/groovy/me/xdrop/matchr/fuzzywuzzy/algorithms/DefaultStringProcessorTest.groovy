package me.xdrop.matchr.fuzzywuzzy.algorithms

import org.junit.Test

class DefaultStringProcessorTest extends GroovyTestCase {
    @Test
    void testProcess() {
        def inp = "s.trim μεγιουνικουντ n/o/n a.lph.a n.um"

        assertEquals "s trim μεγιουνικουντ n o n a lph a n um", new DefaultStringProcessor().apply(inp)
    }
}
