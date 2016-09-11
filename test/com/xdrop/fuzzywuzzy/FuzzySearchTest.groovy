package com.xdrop.fuzzywuzzy

class FuzzySearchTest extends GroovyTestCase {

    def fuzzy = new FuzzySearch();

    void testRatio() {

        assertEquals 76, FuzzySearch.ratio("mysmilarstring", "mymostsimilarstsdsdring"), 2

    }

    void testPartialRatio() {

        assertEquals 71, FuzzySearch.partialRatio("mysmilarstring", "mymostsimilarstsdsdring"), 2

    }

    void testTokenSortPartial() {

    }

    void testTokenSortRatio() {

    }

    void testTokenSetRatio() {

    }

    void testTokenSetPartial() {

    }

    void testWeightedRatio() {

    }
}
