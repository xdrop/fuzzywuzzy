package me.xdrop.matchr.fuzzywuzzy.algorithms

import me.xdrop.matchr.functions.ScoringFunction
import me.xdrop.matchr.functions.StringMapper
import me.xdrop.matchr.fuzzywuzzy.ratios.PartialRatio
import org.junit.Test

import static org.easymock.EasyMock.*

class TokenSetTest extends GroovyTestCase {
    @Test
    void testUsesStringProcessor() {
        def ts = new TokenSet()
        StringMapper<String> mock = mock(StringMapper)

        expect(mock.apply(eq("notthesame"))).andReturn("thesame")
        expect(mock.apply(eq("thesame"))).andReturn("thesame")
        replay(mock)

        assertEquals 100, TokenSet.with(mock).apply("notthesame", "thesame")
    }

    @Test
    void testUsesRatio(){
        ScoringFunction mock = mock(ScoringFunction)

        expect(mock.apply(anyObject(String), anyObject(String)))
                .andReturn(0)
                .anyTimes()
        replay(mock)

        assertEquals 63, new TokenSet().apply("one two", "one three")
    }

    @Test
    void testTokenSet() {
        def ts = new TokenSet()

        assertEquals 46, ts.apply("test", "pesticide")
        assertEquals 75, TokenSet.with(new PartialRatio()).apply("test", "pesticide")
    }
}
