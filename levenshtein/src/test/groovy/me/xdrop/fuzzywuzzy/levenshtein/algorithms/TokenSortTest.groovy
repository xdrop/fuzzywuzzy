package me.xdrop.fuzzywuzzy.levenshtein.algorithms

import me.xdrop.fuzzywuzzy.functions.ScoringFunction
import me.xdrop.fuzzywuzzy.functions.StringMapper
import me.xdrop.fuzzywuzzy.levenshtein.Levenshtein
import me.xdrop.fuzzywuzzy.levenshtein.ratios.PartialRatio
import me.xdrop.fuzzywuzzy.levenshtein.ratios.SimpleRatio
import org.junit.Test

import static me.xdrop.fuzzywuzzy.levenshtein.Levenshtein.Method.TOKEN_SORT_SIMPLE
import static org.easymock.EasyMock.anyObject
import static org.easymock.EasyMock.eq
import static org.easymock.EasyMock.expect
import static org.easymock.EasyMock.mock
import static org.easymock.EasyMock.replay

class TokenSortTest extends GroovyTestCase {
    @Test
    void testUsesStringProcessor() {
        def ts = new TokenSort()
        StringMapper<String> mock = mock(StringMapper)

        expect(mock.apply(eq("notthesame"))).andReturn("thesame")
        expect(mock.apply(eq("thesame"))).andReturn("thesame")
        replay(mock)

        assertEquals 100, ts.apply("notthesame", "thesame", mock)
    }

    @Test
    void testUsesRatio() {
        ScoringFunction mock = mock(ScoringFunction)

        expect(mock.apply(anyObject(String), anyObject(String)))
                .andReturn(0)
                .anyTimes()
        replay(mock)

        assertEquals 63, TOKEN_SORT_SIMPLE.apply("one two", "one three")
    }

    @Test
    void testTokenSort() {
        def ts = new TokenSort()

        assertEquals 75, ts.apply("test", "pesticide", new PartialRatio())
        assertEquals 46, ts.apply("test", "pesticide", new SimpleRatio())
    }
}
