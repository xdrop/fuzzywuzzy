package me.xdrop.fuzzywuzzy.algorithms

import me.xdrop.fuzzywuzzy.ToStringFunction

import static org.easymock.EasyMock.*;

class WeightedRatioTest extends GroovyTestCase {

    void testUsesStringProcessor() {
        def wr = new WeightedRatio()

        ToStringFunction<String> mock = mock(ToStringFunction)

        expect(mock.apply(eq("notthesame")))
            .andReturn("thesame")

        expect(mock.apply(eq("thesame")))
                .andReturn("thesame")

        replay(mock)

        assertEquals 100, wr.apply("notthesame", "thesame", mock)
    }

    void testWRatio() {
        assertEquals 68, new WeightedRatio().apply("test", "pesticide")
    }

}
