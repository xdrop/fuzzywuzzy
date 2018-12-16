package me.xdrop.matchr.levenshtein

import me.xdrop.fuzzywuzzy.FuzzyWuzzy
import me.xdrop.fuzzywuzzy.model.Result
import org.junit.Before
import org.junit.Test

class ExtractorTest extends GroovyTestCase {
    List<String> choices
    FuzzyWuzzy<Levenshtein> fuzzy

    @Before
    void setUp() throws Exception {
        super.setUp()
        choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
        fuzzy = FuzzyWuzzy.algorithm(Levenshtein.FACTORY)
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
