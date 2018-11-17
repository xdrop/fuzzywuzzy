package me.xdrop.fuzzywuzzy.rymm

import me.xdrop.fuzzywuzzy.FuzzyWuzzy
import org.junit.Before
import org.junit.Test

import static java.lang.System.nanoTime

class RymmSubstitutionTest extends GroovyTestCase {
    FuzzyWuzzy<Rymm> fuzzy

    @Before
    void setUp() {
        fuzzy = FuzzyWuzzy.algorithm(Rymm.FACTORY)
    }

    @Test
    void test() {
        int[] diffs = new int[1000]
        def target = "goolge"
        for (int i = 0; i < diffs.length; i++) {
            def start = nanoTime()
            fuzzy.extractAllSorted(target, Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"))
            def end = nanoTime()
            diffs[i] = (int) (end - start)
        }
        int i = 0
        for (int diff : diffs) {
            println "diff#${++i}:$diff"
        }
    }
}
