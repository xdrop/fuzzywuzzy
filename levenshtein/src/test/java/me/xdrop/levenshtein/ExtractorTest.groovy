package me.xdrop.levenshtein
import me.xdrop.fuzzywuzzy.algorithms.WeightedRatio
import me.xdrop.fuzzywuzzy.model.Result
import org.junit.Test

class ExtractorTest extends GroovyTestCase {
    List<String> choices
    Extractor extractor

    @Test
    void setUp() throws Exception {
        super.setUp()
        choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
        extractor = new Extractor()
    }

    @Test
    void testExtractWithoutOrder() {
        List<Result> res = extractor.extractWithoutOrder("goolge", choices, new WeightedRatio())

        assert res.size() == choices.size()
        assert res.get(0).score > 0
    }

    @Test
    void testExtractOne() {
        Result res = extractor.extractOne("goolge", choices, new WeightedRatio())

        assert res.string == "google"
    }

    @Test
    void testExtractBests() {
        def res = extractor.extractTop("goolge", choices, new WeightedRatio())

        assert res.get(0).string == "google" && res.get(1).string == "googleplus"
    }

    @Test
    void testExtractBests1() {
        def res = extractor.extractTop("goolge", choices, new WeightedRatio(), 3)

        assert res.size() == 3
        assert res.get(0).string == "google" && res.get(1).string == "googleplus" && res.get(2).string == "plexoogl"
    }
}
