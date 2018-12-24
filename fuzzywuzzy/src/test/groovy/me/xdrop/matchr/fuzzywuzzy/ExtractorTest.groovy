package me.xdrop.matchr.fuzzywuzzy

import me.xdrop.matchr.Matchr
import me.xdrop.matchr.model.Result
import org.junit.Before
import org.junit.Test

class ExtractorTest extends GroovyTestCase {
    List<String> choices
    FuzzyWuzzy<Fuzzywuzzy> fuzzy

    @Before
    void setUp() throws Exception {
        super.setUp()
        choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
        fuzzy = Matchr.algorithm(FuzzyWuzzy.FACTORY)
    }

    @Test
    void testExtractWithoutOrder() {
        List<Result> res = fuzzy.extractAll("goolge", choices, WEIGHTED_RATIO)

        assert res.size() == choices.size()
        assert res.get(0).score > 0
    }

    @Test
    void testExtractOne() {
        Result res = fuzzy.extractBest("goolge", choices, WEIGHTED_RATIO)

        assert res.string == "google"
    }

    @Test
    void testExtractBests() {
        def res = fuzzy.extractBest("goolge", choices, WEIGHTED_RATIO)

        assert res.string == "google"
    }

    @Test
    void testExtractBests1() {
        def res = fuzzy.extractLimited("goolge", choices, WEIGHTED_RATIO, 3)

        assert res.size() == 3
        assert res.get(0).string == "google" && res.get(1).string == "googleplus" && res.get(2).string == "plexoogl"
    }
}
