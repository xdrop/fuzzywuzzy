package com.xdrop.fuzzywuzzy.algorithms

import com.xdrop.fuzzywuzzy.Ratio
import com.xdrop.fuzzywuzzy.StringProcessor
import com.xdrop.fuzzywuzzy.ratios.PartialRatio
import com.xdrop.fuzzywuzzy.ratios.SimpleRatio

import static org.easymock.EasyMock.anyObject
import static org.easymock.EasyMock.eq
import static org.easymock.EasyMock.expect
import static org.easymock.EasyMock.mock
import static org.easymock.EasyMock.replay

class TokenSortTest extends GroovyTestCase {

    void testUsesStringProcessor() {

        def ts = new TokenSort()

        def mock = mock(StringProcessor)

        expect(mock.process(eq("notthesame")))
                .andReturn("thesame")

        expect(mock.process(eq("thesame")))
                .andReturn("thesame")

        replay(mock)

        assertEquals 100, ts.apply("notthesame", "thesame", mock)

    }

    void testUsesRatio() {

        def ts = new TokenSort();

        def mock = mock(Ratio)

        expect(mock.apply(anyObject(String), anyObject(String)))
                .andReturn(0)
                .anyTimes()

        replay(mock)

        assertEquals 0, ts.apply("one two", "one three", mock)

    }

    void testTokenSort() {

        def ts = new TokenSort();

        assertEquals 75, ts.apply("test", "pesticide", new PartialRatio())
        assertEquals 46, ts.apply("test", "pesticide", new SimpleRatio())

    }

}