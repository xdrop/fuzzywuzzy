package me.xdrop.matchr.levenshtein

import me.xdrop.fuzzywuzzy.FuzzyWuzzy
import org.junit.Before
import org.junit.Test

class LevenshteinSearchTest extends GroovyTestCase {
    def choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
    def moreChoices = ["Atlanta Falcons", "New York Jets", "New York Giants", "Dallas Cowboys"]
    FuzzyWuzzy<Levenshtein> fuzzy

    @Before
    void setupFuzzy() {
        fuzzy = algorithm(Levenshtein.FACTORY)
    }

    @Test
    void testRatio() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 76, SIMPLE_RATIO.apply("mysmilarstring", "mymostsimilarstsdsdring"), 2
        assertEquals 72, SIMPLE_RATIO.apply("mysmilarstring","myawfullysimilarstirng"), 2
        assertEquals 97, SIMPLE_RATIO.apply("mysmilarstring","mysimilarstring"), 2
        assertEquals 75, SIMPLE_RATIO.apply("csr", "c s r"), 2
    }

    @Test
    void testPartialRatio() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 71, PARTIAL_RATIO.apply("similar", "somewhresimlrbetweenthisstring")
        assertEquals 43, PARTIAL_RATIO.apply("similar", "notinheresim")
        assertEquals 38, PARTIAL_RATIO.apply("pros holdings, inc.","settlement facility dow corning trust")
        assertEquals 33, PARTIAL_RATIO.apply("Should be the same","Opposite ways go alike")
        assertEquals 33, PARTIAL_RATIO.apply("Opposite ways go alike" , "Should be the same")
    }

    @Test
    void testTokenSortPartial() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 67, TOKEN_SORT_PARTIAL.apply("mvn","wwwwww.mavencentral.comm")
        assertEquals 100, TOKEN_SORT_PARTIAL.apply("  order words out of ","  words out of order")
        assertEquals 44, TOKEN_SORT_PARTIAL.apply("Testing token set ratio token", "Added another test")
    }

    @Test
    void testTokenSortRatio() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 84, TOKEN_SORT_SIMPLE.apply("fuzzy was a bear", "fuzzy fuzzy was a bear")
    }

    @Test
    void testTokenSetRatio() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 100, TOKEN_SET_SIMPLE.apply("fuzzy fuzzy fuzzy bear", "fuzzy was a bear" )
        assertEquals 39, TOKEN_SET_SIMPLE.apply("Testing token set ratio token", "Added another test")
    }

    @Test
    void testTokenSetPartial() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 11, TOKEN_SET_PARTIAL.apply("fuzzy was a bear", "blind 100")
        assertEquals 67, TOKEN_SET_PARTIAL.apply("chicago transit authority" , "cta")
    }

    @Test
    void testWeightedRatio() {
        if (fuzzy == null) setupFuzzy()
        assertEquals 60, WEIGHTED_RATIO.apply("mvn","wwwwww.mavencentral.comm")
        assertEquals 40, WEIGHTED_RATIO.apply("mvn","www;'l3;4;.4;23.4/23.4/234//////www.mavencentral.comm")
        assertEquals 97, WEIGHTED_RATIO.apply("The quick brown fox jimps ofver the small lazy dog",
                "the quick brown fox jumps over the small lazy dog")
    }

    @Test
    void testExtractTop() {
        if (fuzzy == null) setupFuzzy()
        def res = fuzzy.extractLimited("goolge", choices, 2)
        def res2 = fuzzy.extractLimited("goolge", choices, SIMPLE_RATIO, 2)

        assert res.size() == 2
        assert res.get(0).string == "google" && res.get(1).string == "googleplus"

        assert res2.size() == 2
        assert res2.get(0).string == "google" && res2.get(1).string == "googleplus"

        assert fuzzy.extractLimited("goolge", choices, 100, 2).size() == 0
    }

    @Test
    void testExtractAll() {
        if (fuzzy == null) setupFuzzy()
        def res = fuzzy.extractAll("goolge", choices)

        assert res.size() == choices.size()
        assert res.get(0).string == "google"

        assert fuzzy.extractAll("goolge", choices, 40).size() == 3
    }

    @Test
    void testExtractSorted() {
        if (fuzzy == null) setupFuzzy()
        def res = fuzzy.extractAllSorted("goolge", choices)

        assert res.size() == choices.size()
        assert res.get(0).string == "google"
        assert res.get(1).string == "googleplus"

        assert fuzzy.extractAllSorted("goolge", choices, 40).size() == 3
    }

    @Test
    void testExtractOne() {
        if (fuzzy == null) setupFuzzy()
        def res = fuzzy.extractBest("twiter", choices, SIMPLE_RATIO)
        def res2 = fuzzy.extractBest("twiter", choices)
        def res3 = fuzzy.extractBest("cowboys", moreChoices)

        assert res.string == "twitter"
        assert res2.string == "twitter"
        assert res3.string == "Dallas Cowboys" && res3.score == 90
    }
}
