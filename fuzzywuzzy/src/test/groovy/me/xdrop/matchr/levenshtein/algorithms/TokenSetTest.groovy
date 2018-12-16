package me.xdrop.matchr.levenshtein.algorithms

import me.xdrop.fuzzywuzzy.functions.ScoringFunction
import me.xdrop.fuzzywuzzy.functions.StringMapper
import me.xdrop.fuzzywuzzy.levenshtein.ratios.PartialRatio
import org.junit.Test

import static me.xdrop.fuzzywuzzy.levenshtein.Levenshtein.Method.TOKEN_SET_SIMPLE
import static org.easymock.EasyMock.*

class TokenSetTest extends GroovyTestCase {
    @Test
    void testUsesStringProcessor() {
        def ts = new TokenSet()
        StringMapper<String> mock = mock(StringMapper)

        expect(mock.apply(eq("notthesame"))).andReturn("thesame")
        expect(mock.apply(eq("thesame"))).andReturn("thesame")
        replay(mock)

        assertEquals 100, ts.apply("notthesame", "thesame", mock)
    }

    @Test
    void testUsesRatio(){
        ScoringFunction mock = mock(ScoringFunction)

        expect(mock.apply(anyObject(String), anyObject(String)))
                .andReturn(0)
                .anyTimes()
        replay(mock)

        assertEquals 63, TOKEN_SET_SIMPLE.apply("one two", "one three")
    }

    @Test
    void testTokenSet() {
        def ts = new TokenSet()

        assertEquals 46, ts.apply("test", "pesticide")
        assertEquals 75, ts.apply("test", "pesticide", new PartialRatio())
    }
}
