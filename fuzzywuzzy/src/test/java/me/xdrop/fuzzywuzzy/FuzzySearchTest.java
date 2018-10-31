package me.xdrop.fuzzywuzzy;

import java.util.Arrays;
import java.util.List;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import me.xdrop.fuzzywuzzy.ratios.SimpleRatio;
import org.junit.Test;

import static org.junit.Assert.*;

public class FuzzySearchTest {
    List<String> choices = Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl");
    List<String> moreChoices = Arrays.asList("Atlanta Falcons", "New York Jets", "New York Giants", "Dallas Cowboys");

    @Test
    public void testRatio() {
        assertEquals(76, FuzzySearch.ratio("mysmilarstring", "mymostsimilarstsdsdring"), 2);
        assertEquals(72, FuzzySearch.ratio("mysmilarstring","myawfullysimilarstirng"), 2);
        assertEquals(97, FuzzySearch.ratio("mysmilarstring","mysimilarstring"), 2);
        assertEquals(75, FuzzySearch.ratio("csr", "c s r"), 2);
    }

    @Test
    public void testPartialRatio() {
        assertEquals(71, FuzzySearch.partialRatio("similar", "somewhresimlrbetweenthisstring"));
        assertEquals(43, FuzzySearch.partialRatio("similar", "notinheresim"));
        assertEquals(38, FuzzySearch.partialRatio("pros holdings, inc.","settlement facility dow corning trust"));
        assertEquals(33, FuzzySearch.partialRatio("Should be the same","Opposite ways go alike"));
        assertEquals(33, FuzzySearch.partialRatio("Opposite ways go alike" , "Should be the same"));
    }

    @Test
    public void testTokenSortPartial() {
        assertEquals(67, FuzzySearch.tokenSortPartialRatio("mvn","wwwwww.mavencentral.comm"));
        assertEquals(100, FuzzySearch.tokenSortPartialRatio("  order words out of ","  words out of order"));
        assertEquals(44, FuzzySearch.tokenSortPartialRatio("Testing token set ratio token", "Added another test"));
    }

    @Test
    public void testTokenSortRatio() {
        assertEquals(84, FuzzySearch.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear"));
    }

    @Test
    public void testTokenSetRatio() {
        assertEquals(100, FuzzySearch.tokenSetRatio("fuzzy fuzzy fuzzy bear", "fuzzy was a bear" ));
        assertEquals(39, FuzzySearch.tokenSetRatio("Testing token set ratio token", "Added another test"));
    }

    @Test
    public void testTokenSetPartial() {
        assertEquals(11, FuzzySearch.tokenSetPartialRatio("fuzzy was a bear", "blind 100"));
        assertEquals(67, FuzzySearch.partialRatio("chicago transit authority" , "cta"));
    }

    @Test
    public void testWeightedRatio() {
        assertEquals(60, FuzzySearch.weightedRatio("mvn","wwwwww.mavencentral.comm"));
        assertEquals(40, FuzzySearch.weightedRatio("mvn","www;'l3;4;.4;23.4/23.4/234//////www.mavencentral.comm"));
        assertEquals(97, FuzzySearch.weightedRatio("The quick brown fox jimps ofver the small lazy dog",
                "the quick brown fox jumps over the small lazy dog"));
    }

    @Test
    public void testExtractTop() {
        List<ExtractedResult<String>> res = FuzzySearch.extractTop("goolge", choices, 2);
        List<ExtractedResult<String>> res2 = FuzzySearch.extractTop("goolge", choices, new SimpleRatio(), 2);

        assert res.size() == 2;
        assert res.get(0).getString() == "google" && res.get(1).getString().equals("googleplus");

        assert res2.size() == 2;
        assert res2.get(0).getString().equals("google") && res2.get(1).getString().equals("googleplus");

        assert FuzzySearch.extractTop("goolge", choices, 2, 100).size() == 0;
    }

    @Test
    public void testExtractAll() {
        List<ExtractedResult<String>> res = FuzzySearch.extractAll("goolge", choices);

        assert res.size() == choices.size();
        assert res.get(0).getString().equals("google");

        assert FuzzySearch.extractAll("goolge", choices, 40).size() == 3;
    }

    @Test
    public void testExtractSorted() {
        List<ExtractedResult<String>> res = FuzzySearch.extractSorted("goolge", choices);

        assert res.size() == choices.size();
        assert res.get(0).getString().equals("google");
        assert res.get(1).getString().equals("googleplus");

        assert FuzzySearch.extractSorted("goolge", choices, 40).size() == 3;
    }

    @Test
    public void testExtractOne() {
        ExtractedResult<String> res = FuzzySearch.extractOne("twiter", choices, new SimpleRatio());
        ExtractedResult<String> res2 = FuzzySearch.extractOne("twiter", choices);
        ExtractedResult<String> res3 = FuzzySearch.extractOne("cowboys", moreChoices);

        assert res.getString().equals("twitter");
        assert res2.getString().equals("twitter");
        assert res3.getString().equals("Dallas Cowboys") && res3.getScore() == 90;
    }
}
