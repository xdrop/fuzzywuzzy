package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;
import me.xdrop.fuzzywuzzy.ratios.PartialRatio;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class TokenSetTest {
    @Test
    public void testUsesStringProcessor() {
        TokenSet ts = new TokenSet();
        //noinspection unchecked
        ToStringFunction<String> mock = mock(ToStringFunction.class);

        expect(mock.apply(eq("notthesame"))).andReturn("thesame");
        expect(mock.apply(eq("thesame"))).andReturn("thesame");
        replay(mock);

        assertEquals(100, ts.apply("notthesame", "thesame", mock));
    }

    @Test
    public void testUsesRatio(){
        TokenSet ts = new TokenSet();
        Ratio mock = mock(Ratio.class);

        expect(mock.apply(anyObject(String.class), anyObject(String.class))).andReturn(0).anyTimes();
        replay(mock);

        assertEquals(0, ts.apply("one two", "one three", mock));
    }

    @Test
    public void testTokenSet() {
        TokenSet ts = new TokenSet();

        assertEquals(46, ts.apply("test", "pesticide"));
        assertEquals(75, ts.apply("test", "pesticide", new PartialRatio()));
    }
}
