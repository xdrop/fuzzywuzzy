package com.xdrop.diffutils

class DiffUtilsTest extends GroovyTestCase {



    void testGetEditOps() {

    }

    void testGetMatchingBlocks() {

    }

    void testGetMatchingBlocks1() {

    }

    void testGetRatio() {

        def s1 = "abxcd";
        def s2 = "abcd";

        assertEquals(0.88888, DiffUtils.getRatio(s1,s2), 0.01)


    }


}
