package me.xdrop.fuzzywuzzy

import me.xdrop.fuzzywuzzy.ratios.SimpleRatio


class FuzzySearchTest extends GroovyTestCase {


    def choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
    def moreChoices = ["Atlanta Falcons", "New York Jets", "New York Giants", "Dallas Cowboys"]



    void testRatio() {

        assertEquals 76, FuzzySearch.ratio("mysmilarstring", "mymostsimilarstsdsdring"), 2
        assertEquals 72, FuzzySearch.ratio("mysmilarstring","myawfullysimilarstirng"), 2
        assertEquals 97, FuzzySearch.ratio("mysmilarstring","mysimilarstring"), 2
        assertEquals 75, FuzzySearch.ratio("csr", "c s r"), 2

    }

    void testPartialRatio() {

        assertEquals 71, FuzzySearch.partialRatio("similar", "somewhresimlrbetweenthisstring")
        assertEquals 43, FuzzySearch.partialRatio("similar", "notinheresim")
        assertEquals 38, FuzzySearch.partialRatio("pros holdings, inc.","settlement facility dow corning trust")
        assertEquals 33, FuzzySearch.partialRatio("Should be the same","Opposite ways go alike")
        assertEquals 33, FuzzySearch.partialRatio("Opposite ways go alike" , "Should be the same")

    }

    void testTokenSortPartial() {

        assertEquals 67, FuzzySearch.tokenSortPartialRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 100, FuzzySearch.tokenSortPartialRatio("  order words out of ","  words out of order")
        assertEquals 44, FuzzySearch.tokenSortPartialRatio("Testing token set ratio token", "Added another test")

    }

    void testTokenSortRatio() {

        assertEquals 84, FuzzySearch.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear")

    }

    void testTokenSetRatio() {

        assertEquals 100, FuzzySearch.tokenSetRatio("fuzzy fuzzy fuzzy bear", "fuzzy was a bear" )
        assertEquals 39, FuzzySearch.tokenSetRatio("Testing token set ratio token", "Added another test")

    }

    void testTokenSetPartial() {

        assertEquals 11, FuzzySearch.tokenSetPartialRatio("fuzzy was a bear", "blind 100")
        assertEquals 67, FuzzySearch.partialRatio("chicago transit authority" , "cta")

    }

    void testWeightedRatio() {


        assertEquals 60, FuzzySearch.weightedRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 40, FuzzySearch.weightedRatio("mvn","www;'l3;4;.4;23.4/23.4/234//////www.mavencentral.comm")
        assertEquals 97, FuzzySearch.weightedRatio("The quick brown fox jimps ofver the small lazy dog",
                "the quick brown fox jumps over the small lazy dog")

    }

    void testExtractTop() {

        def res = FuzzySearch.extractTop("goolge", choices, 2);
        def res2 = FuzzySearch.extractTop("goolge", choices, new SimpleRatio(), 2);

        assert res.size() == 2
        assert res.get(0).string == "google" && res.get(1).string == "googleplus"

        assert res2.size() == 2
        assert res2.get(0).string == "google" && res2.get(1).string == "googleplus"

        assert FuzzySearch.extractTop("goolge", choices, 2, 100).size() == 0

    }

    void testExtractAll() {

        def res = FuzzySearch.extractAll("goolge", choices);

        assert res.size() == choices.size()
        assert res.get(0).string == "google"

        assert FuzzySearch.extractAll("goolge", choices, 40).size() == 3

    }

    void testExtractSorted() {

        def res = FuzzySearch.extractSorted("goolge", choices);

        assert res.size() == choices.size()
        assert res.get(0).string == "google"
        assert res.get(1).string == "googleplus"

        assert FuzzySearch.extractSorted("goolge", choices, 40).size() == 3

    }



    void testExtractOne() {

        def res = FuzzySearch.extractOne("twiter", choices, new SimpleRatio());
        def res2 = FuzzySearch.extractOne("twiter", choices);
        def res3 = FuzzySearch.extractOne("cowboys", moreChoices)

        assert res.string == "twitter"
        assert res2.string == "twitter"
        assert res3.string == "Dallas Cowboys" && res3.score == 90


    }


}
