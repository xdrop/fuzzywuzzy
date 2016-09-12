package com.xdrop.fuzzywuzzy.algorithms

import com.xdrop.fuzzywuzzy.StringProcessor

import static org.easymock.EasyMock.*;

class WeightedRatioTest extends GroovyTestCase {

    void testUsesStringProcessor() {
        def wr = new WeightedRatio()

        def mock = mock(StringProcessor)

        expect(mock.process(eq("notthesame")))
            .andReturn("thesame")

        expect(mock.process(eq("thesame")))
                .andReturn("thesame")

        replay(mock)

        assertEquals 100, wr.apply("notthesame", "thesame", mock)
    }

    void testWRatio() {

        assertEquals 68, new WeightedRatio().apply("test", "pesticide")

    }

}
