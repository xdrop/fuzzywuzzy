package com.xdrop.fuzzywuzzy.extract

import com.xdrop.fuzzywuzzy.Extractor
import com.xdrop.fuzzywuzzy.algorithms.WeightedRatio

class ExtractorTest extends GroovyTestCase {

    List<String> choices;
    Extractor extractor;

    void setUp() throws Exception {
        super.setUp()
        choices = ["google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"]
        extractor = new Extractor();
    }

    void testExtractWithoutOrder() {

        List<Extractor.ExtractedResult> res = extractor.extractWithoutOrder("goolge", choices, new WeightedRatio());

        assert res.size() == choices.size()
        assert res.get(0).score > 0

    }

    void testExtractOne() {

        Extractor.ExtractedResult res = extractor.extractOne("goolge", choices, new WeightedRatio());

        assert res.string == "google"

    }

    void testExtractBests() {

        def res = extractor.extractBests("goolge", choices, new WeightedRatio());

        assert res.get(0).string == "google" && res.get(1).string == "googleplus"

    }

    void testExtractBests1() {

        def res = extractor.extractBests("goolge", choices, new WeightedRatio(), 3);

        assert res.size() == 3
        assert res.get(0).string == "google" && res.get(1).string == "googleplus" && res.get(2).string == "plexoogl"

    }
}
