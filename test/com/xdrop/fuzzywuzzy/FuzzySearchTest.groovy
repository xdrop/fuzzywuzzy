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

        assertEquals 67, FuzzySearch.tokenSortPartial("mvn","wwwwww.mavencentral.comm")

    }

    void testTokenSortRatio() {

        assertEquals 84, FuzzySearch.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear")

    }

    void testTokenSetRatio() {

        assertEquals 91, FuzzySearch.tokenSetRatio("fuzzy was a bear", "fuzzy d fuzzy was a bsear")

    }

    void testTokenSetPartial() {

        assertEquals 22, FuzzySearch.tokenSetPartial("fuzzy was a bear", "blind 100")

    }

    void testWeightedRatio() {


        assertEquals 60, FuzzySearch.weightedRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 40, FuzzySearch.weightedRatio("mvn","www;'l3;4;.4;23.4/23.4/234//////www.mavencentral.comm")

    }
}
