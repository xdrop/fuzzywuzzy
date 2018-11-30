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
        def target = "goolge"
        def result = fuzzy.extractAllSorted(target, Arrays.asList("google", "bing", "facebook", "linkedin", "twitter", "googleplus", "bingnews", "plexoogl"))
        println "Target: $target"
        result.forEach(System.out.&println)
    }
}
