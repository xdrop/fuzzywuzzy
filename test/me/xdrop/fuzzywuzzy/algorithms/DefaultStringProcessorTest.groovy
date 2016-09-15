package me.xdrop.fuzzywuzzy.algorithms

class DefaultStringProcessorTest extends GroovyTestCase {

    void testProcess() {

        def inp = "s.trim μεγιουνικουντ n/o/n a.lph.a n.um"

        assertEquals "s trim μεγιουνικουντ n o n a lph a n um", new DefaultStringProcessor().process(inp)

    }

}
