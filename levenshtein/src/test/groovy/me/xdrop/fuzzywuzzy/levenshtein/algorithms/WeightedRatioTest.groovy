package me.xdrop.fuzzywuzzy.levenshtein.algorithms


import me.xdrop.fuzzywuzzy.functions.StringMapper
import org.junit.Test

import static org.easymock.EasyMock.*

class WeightedRatioTest extends GroovyTestCase {
    @Test
    void testUsesStringProcessor() {
        def wr = new WeightedRatio()

        StringMapper<String> mock = mock(StringMapper)

        expect(mock.apply(eq("notthesame"))).andReturn("thesame")
        expect(mock.apply(eq("thesame"))).andReturn("thesame")
        replay(mock)

        assertEquals 100, wr.apply("notthesame", "thesame", mock)
    }

    @Test
    void testWRatio() {
        assertEquals 68, new WeightedRatio().apply("test", "pesticide")
    }
}
