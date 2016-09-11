package com.xdrop.fuzzywuzzy

class FuzzySearchTest extends GroovyTestCase {

    def fuzzy = new FuzzySearch();

    void testRatio() {

        assertEquals 76, FuzzySearch.ratio("mysmilarstring", "mymostsimilarstsdsdring"), 2
        assertEquals 72, FuzzySearch.ratio("mysmilarstring","myawfullysimilarstirng"), 2
        assertEquals 97, FuzzySearch.ratio("mysmilarstring","mysimilarstring"), 2

    }

    void testPartialRatio() {

        assertEquals 71, FuzzySearch.partialRatio("similar", "somewhresimlrbetweenthisstring")
        assertEquals 43, FuzzySearch.partialRatio("similar", "notinheresim")

    }

    void testTokenSortPartial() {

        assertEquals 67, FuzzySearch.tokenSortPartialRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 100, FuzzySearch.tokenSortPartialRatio("  order words out of ","  words out of order")

    }

    void testTokenSortRatio() {

        assertEquals 84, FuzzySearch.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear")

    }

    void testTokenSetRatio() {

        assertEquals 100, FuzzySearch.tokenSetRatio("fuzzy fuzzy fuzzy bear", "fuzzy was a bear" )

    }

    void testTokenSetPartial() {

        assertEquals 22, FuzzySearch.tokenSetPartialRatio("fuzzy was a bear", "blind 100")

    }

    void testWeightedRatio() {


        assertEquals 60, FuzzySearch.weightedRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 40, FuzzySearch.weightedRatio("mvn","www;'l3;4;.4;23.4/23.4/234//////www.mavencentral.comm")

    }
}
