package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;
import me.xdrop.fuzzywuzzy.ratios.PartialRatio;
import me.xdrop.fuzzywuzzy.ratios.SimpleRatio;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class TokenSortTest {
    @Test
    public void testUsesStringProcessor() {
        TokenSort ts = new TokenSort();
        //noinspection unchecked
        ToStringFunction<String> mock = mock(ToStringFunction.class);

        expect(mock.apply(eq("notthesame"))).andReturn("thesame");
        expect(mock.apply(eq("thesame"))).andReturn("thesame");
        replay(mock);

        assertEquals(100, ts.apply("notthesame", "thesame", mock));
    }

    @Test
    public void testUsesRatio() {
        TokenSort ts = new TokenSort();
        Ratio mock = mock(Ratio.class);

        expect(mock.apply(anyObject(String.class), anyObject(String.class))).andReturn(0).anyTimes();
        replay(mock);

        assertEquals(0, ts.apply("one two", "one three", mock));
    }

    @Test
    public void testTokenSort() {
        TokenSort ts = new TokenSort();

        assertEquals(75, ts.apply("test", "pesticide", new PartialRatio()));
        assertEquals(46, ts.apply("test", "pesticide", new SimpleRatio()));
    }
}
