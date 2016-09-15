package me.xdrop.fuzzywuzzy
import me.xdrop.fuzzywuzzy.algorithms.WeightedRatio
import me.xdrop.fuzzywuzzy.model.ExtractedResult

class ExtractorTest extends GroovyTestCase {

    List<String> choices;
    Extractor extractor;

    void setUp() throws Exception {
        super.setUp()
        choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
        extractor = new Extractor();
    }

    void testExtractWithoutOrder() {

        List<ExtractedResult> res = extractor.extractWithoutOrder("goolge", choices, new WeightedRatio());

        assert res.size() == choices.size()
        assert res.get(0).score > 0

    }

    void testExtractOne() {

        ExtractedResult res = extractor.extractOne("goolge", choices, new WeightedRatio());

        assert res.string == "google"

    }

    void testExtractBests() {

        def res = extractor.extractTop("goolge", choices, new WeightedRatio());

        assert res.get(0).string == "google" && res.get(1).string == "googleplus"

    }

    void testExtractBests1() {

        def res = extractor.extractTop("goolge", choices, new WeightedRatio(), 3);

        assert res.size() == 3
        assert res.get(0).string == "google" && res.get(1).string == "googleplus" && res.get(2).string == "plexoogl"

    }
}
