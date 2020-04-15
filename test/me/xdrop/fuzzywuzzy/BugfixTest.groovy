package me.xdrop.fuzzywuzzy

class BugfixTest extends GroovyTestCase {
    void testIssue39() {
        int score = FuzzySearch.partialRatio("kaution",
                "kdeffxxxiban:de1110010060046666666datum:16.11.17zeit:01:12uft0000899999tan076601testd.-20-maisonette-z4-jobas-hagkautionauszug")

        assert score == 100
    }
}
