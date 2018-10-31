package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.ToStringFunction;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class WeightedRatioTest {
    @Test
    public void testUsesStringProcessor() {
        WeightedRatio wr = new WeightedRatio();

        //noinspection unchecked
        ToStringFunction<String> mock = mock(ToStringFunction.class);

        expect(mock.apply(eq("notthesame"))).andReturn("thesame");
        expect(mock.apply(eq("thesame"))).andReturn("thesame");
        replay(mock);

        assertEquals(100, wr.apply("notthesame", "thesame", mock));
    }

    @Test
    public void testWRatio() {
        assertEquals(68, new WeightedRatio().apply("test", "pesticide"));
    }
}
