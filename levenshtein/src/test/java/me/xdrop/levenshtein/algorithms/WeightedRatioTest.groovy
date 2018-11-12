package me.xdrop.levenshtein.algorithms

import me.xdrop.fuzzywuzzy.functions.ToStringMapper
import org.junit.Test

import static org.easymock.EasyMock.*

class WeightedRatioTest extends GroovyTestCase {
    @Test
    void testUsesStringProcessor() {
        def wr = new WeightedRatio()

        ToStringMapper<String> mock = mock(ToStringMapper)

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
