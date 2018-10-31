package me.xdrop.fuzzywuzzy;

import java.util.Arrays;
import java.util.List;
import me.xdrop.fuzzywuzzy.algorithms.WeightedRatio;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.junit.Before;
import org.junit.Test;

public class ExtractorTest {
    List<String> choices;
    Extractor extractor;

    @Before
    public void setUp() {
        choices = Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl");
        extractor = new Extractor();
    }

    @Test
    public void testExtractWithoutOrder() {
        List<ExtractedResult<String>> res = extractor.extractWithoutOrder("goolge", choices, new WeightedRatio());

        assert res.size() == choices.size();
        assert res.get(0).getScore() > 0;
    }

    @Test
    public void testExtractOne() {
        ExtractedResult<String> res = extractor.extractOne("goolge", choices, new WeightedRatio());

        assert res.getString().equals("google");
    }

    @Test
    public void testExtractBests() {
        List<ExtractedResult<String>> res = extractor.extractTop("goolge", choices, new WeightedRatio());

        assert res.get(0).getString().equals("google") && res.get(1).getString().equals("googleplus");
    }

    @Test
    public void testExtractBests1() {
        List<ExtractedResult<String>> res = extractor.extractTop("goolge", choices, new WeightedRatio(), 3);

        assert res.size() == 3;
        assert res.get(0).getString().equals("google") && res.get(1).getString().equals("googleplus") && res.get(2).getString().equals("plexoogl");
    }
}
