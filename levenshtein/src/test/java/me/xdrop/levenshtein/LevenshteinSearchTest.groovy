package me.xdrop.levenshtein

import me.xdrop.fuzzywuzzy.ratios.SimpleRatio
import org.junit.Test

class LevenshteinSearchTest extends GroovyTestCase {
    def choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
    def moreChoices = ["Atlanta Falcons", "New York Jets", "New York Giants", "Dallas Cowboys"]

    @Test
    void testRatio() {
        assertEquals 76, LevenshteinSearch.ratio("mysmilarstring", "mymostsimilarstsdsdring"), 2
        assertEquals 72, LevenshteinSearch.ratio("mysmilarstring","myawfullysimilarstirng"), 2
        assertEquals 97, LevenshteinSearch.ratio("mysmilarstring","mysimilarstring"), 2
        assertEquals 75, LevenshteinSearch.ratio("csr", "c s r"), 2
    }

    @Test
    void testPartialRatio() {
        assertEquals 71, LevenshteinSearch.partialRatio("similar", "somewhresimlrbetweenthisstring")
        assertEquals 43, LevenshteinSearch.partialRatio("similar", "notinheresim")
        assertEquals 38, LevenshteinSearch.partialRatio("pros holdings, inc.","settlement facility dow corning trust")
        assertEquals 33, LevenshteinSearch.partialRatio("Should be the same","Opposite ways go alike")
        assertEquals 33, LevenshteinSearch.partialRatio("Opposite ways go alike" , "Should be the same")
    }

    @Test
    void testTokenSortPartial() {
        assertEquals 67, LevenshteinSearch.tokenSortPartialRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 100, LevenshteinSearch.tokenSortPartialRatio("  order words out of ","  words out of order")
        assertEquals 44, LevenshteinSearch.tokenSortPartialRatio("Testing token set ratio token", "Added another test")
    }

    @Test
    void testTokenSortRatio() {
        assertEquals 84, LevenshteinSearch.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear")
    }

    @Test
    void testTokenSetRatio() {
        assertEquals 100, LevenshteinSearch.tokenSetRatio("fuzzy fuzzy fuzzy bear", "fuzzy was a bear" )
        assertEquals 39, LevenshteinSearch.tokenSetRatio("Testing token set ratio token", "Added another test")
    }

    @Test
    void testTokenSetPartial() {
        assertEquals 11, LevenshteinSearch.tokenSetPartialRatio("fuzzy was a bear", "blind 100")
        assertEquals 67, LevenshteinSearch.partialRatio("chicago transit authority" , "cta")
    }

    @Test
    void testWeightedRatio() {
        assertEquals 60, LevenshteinSearch.weightedRatio("mvn","wwwwww.mavencentral.comm")
        assertEquals 40, LevenshteinSearch.weightedRatio("mvn","www;'l3;4;.4;23.4/23.4/234//////www.mavencentral.comm")
        assertEquals 97, LevenshteinSearch.weightedRatio("The quick brown fox jimps ofver the small lazy dog",
                "the quick brown fox jumps over the small lazy dog")
    }

    @Test
    void testExtractTop() {
        def res = LevenshteinSearch.extractTop("goolge", choices, 2)
        def res2 = LevenshteinSearch.extractTop("goolge", choices, new SimpleRatio(), 2)

        assert res.size() == 2
        assert res.get(0).string == "google" && res.get(1).string == "googleplus"

        assert res2.size() == 2
        assert res2.get(0).string == "google" && res2.get(1).string == "googleplus"

        assert LevenshteinSearch.extractTop("goolge", choices, 2, 100).size() == 0
    }

    @Test
    void testExtractAll() {
        def res = LevenshteinSearch.extractAll("goolge", choices)

        assert res.size() == choices.size()
        assert res.get(0).string == "google"

        assert LevenshteinSearch.extractAll("goolge", choices, 40).size() == 3
    }

    @Test
    void testExtractSorted() {
        def res = LevenshteinSearch.extractSorted("goolge", choices)

        assert res.size() == choices.size()
        assert res.get(0).string == "google"
        assert res.get(1).string == "googleplus"

        assert LevenshteinSearch.extractSorted("goolge", choices, 40).size() == 3
    }

    @Test
    void testExtractOne() {
        def res = LevenshteinSearch.extractOne("twiter", choices, new SimpleRatio())
        def res2 = LevenshteinSearch.extractOne("twiter", choices)
        def res3 = LevenshteinSearch.extractOne("cowboys", moreChoices)

        assert res.string == "twitter"
        assert res2.string == "twitter"
        assert res3.string == "Dallas Cowboys" && res3.score == 90
    }
}
