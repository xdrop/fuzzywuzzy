package com.xdrop.diffutils;

import com.xdrop.diffutils.structs.EditOp;
import com.xdrop.diffutils.structs.EditType;
import com.xdrop.diffutils.structs.MatchingBlock;
import com.xdrop.diffutils.structs.OpCode;
import org.apache.commons.lang.StringUtils;

public class DiffUtils {

    public static EditOp[] getEditOps(String s1, String s2) {
        return getEditOps(s1.length(), s1, s2.length(), s2);
    }


    private static EditOp[] getEditOps(int len1, String s1, int len2, String s2) {

        int len1o, len2o;
        int i;

        int[] matrix;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int p1 = 0;
        int p2 = 0;

        len1o = 0;

        while (len1 > 0 && len2 > 0 && c1[p1] == c2[p2]) {
            len1--;
            len2--;

            p1++;
            p2++;

            len1o++;
        }

        len2o = len1o;

        /* strip common suffix */
        while (len1 > 0 && len2 > 0 && c1[len1 - 1] == c2[len2 - 1]) {
            len1--;
            len2--;
        }

        len1++;
        len2++;

        matrix = new int[len2 * len1];

        for (i = 0; i < len2; i++)
            matrix[i] = i;
        for (i = 1; i < len1; i++)
            matrix[len2 * i] = i;

        for (i = 1; i < len1; i++) {

            int ptrPrev = (i - 1) * len2;
            int ptrC = i * len2;
            int ptrEnd = ptrC + len2 - 1;

            char char1 = c1[p1 + i - 1];
            int ptrChar2 = p2;

            int x = i;

            ptrC++;

            while (ptrC <= ptrEnd) {

                int c3 = matrix[ptrPrev++] + (char1 != c2[ptrChar2++] ? 1 : 0);
                x++;

                if (x > c3) {
                    x = c3;
                }

                c3 = matrix[ptrPrev] + 1;

                if (x > c3) {
                    x = c3;
                }

                matrix[ptrC++] = x;

            }

        }


        return editOpsFromCostMatrix(len1, c1, p1, len1o, len2, c2, p2, len2o, matrix);
    }


    private static EditOp[] editOpsFromCostMatrix(int len1, char[] c1, int p1, int o1,
                                           int len2, char[] c2, int p2, int o2,
                                           int[] matrix) {

        int i, j, pos;

        int ptr;

        EditOp[] ops;

        int dir = 0;

        pos = matrix[len1 * len2 - 1];

        ops = new EditOp[pos];

        i = len1 - 1;
        j = len2 - 1;

        ptr = len1 * len2 - 1;

        while (i > 0 || j > 0) {

            if (dir < 0 && j != 0 && matrix[ptr] == matrix[ptr - 1] + 1) {

                EditOp eop = new EditOp();

                pos--;
                ops[pos] = eop;
                eop.type = EditType.INSERT;
                eop.spos = i + o1;
                eop.dpos = --j + o2;
                ptr--;

                continue;
            }

            if (dir > 0 && i != 0 && matrix[ptr] == matrix[ptr - len2] + 1) {

                EditOp eop = new EditOp();

                pos--;
                ops[pos] = eop;
                eop.type = EditType.DELETE;
                eop.spos = --i + o1;
                eop.dpos = j + o2;
                ptr -= len2;

                continue;

            }

            if (i != 0 && j != 0 && matrix[ptr] == matrix[ptr - len2 - 1]
                    && c1[p1 + i - 1] == c2[p2 + j - 1]) {

                i--;
                j--;
                ptr -= len2 + 1;
                dir = 0;

                continue;

            }

            if (i != 0 && j != 0 && matrix[ptr] == matrix[ptr - len2 - 1] + 1) {

                pos--;

                EditOp eop = new EditOp();
                ops[pos] = eop;

                eop.type = EditType.REPLACE;
                eop.spos = --i + o1;
                eop.dpos = --j + o2;

                ptr -= len2 + 1;
                dir = 0;
                continue;

            }

            if (dir == 0 && j != 0 && matrix[ptr] == matrix[ptr - 1] + 1) {

                pos--;
                EditOp eop = new EditOp();
                ops[pos] = eop;
                eop.type = EditType.INSERT;
                eop.spos = i + o1;
                eop.dpos = --j + o2;
                ptr--;
                dir = -1;

                continue;
            }

            if (dir == 0 && i != 0 && matrix[ptr] == matrix[ptr - len2] + 1) {
                pos--;
                EditOp eop = new EditOp();
                ops[pos] = eop;

                eop.type = EditType.DELETE;
                eop.spos = --i + o1;
                eop.dpos = j + o2;
                ptr -= len2;
                dir = 1;
                continue;
            }

            assert false;

        }

        return ops;

    }

    public static MatchingBlock[] getMatchingBlocks(String s1, String s2){

        return getMatchingBlocks(s1.length(), s2.length(), getEditOps(s1, s2));

    }

    public static MatchingBlock[] getMatchingBlocks(int len1, int len2, OpCode[] ops) {

        int n = ops.length;

        int noOfMB, i;
        int o = 0;

        noOfMB = 0;

        for (i = n; i-- != 0; o++) {

            if (ops[o].type == EditType.KEEP) {

                noOfMB++;

                while (i != 0 && ops[o].type == EditType.KEEP) {
                    i--;
                    o++;
                }

                if (i == 0)
                    break;

            }

        }

        MatchingBlock[] matchingBlocks = new MatchingBlock[noOfMB];
        int mb = 0;
        o = 0;
        matchingBlocks[mb] = new MatchingBlock();

        for (i = n; i != 0; i--, o++) {

            if (ops[o].type == EditType.KEEP) {


                matchingBlocks[mb].spos = ops[o].sbeg;
                matchingBlocks[mb].dpos = ops[o].dbeg;

                while (i != 0 && ops[o].type == EditType.KEEP) {
                    i--;
                    o++;
                }

                if (i == 0) {
                    matchingBlocks[mb].length = len1 - matchingBlocks[mb].spos;
                    mb++;
                    break;
                }

                matchingBlocks[mb].length = ops[o].sbeg - matchingBlocks[mb].spos;
                mb++;
                matchingBlocks[mb] = new MatchingBlock();
            }


        }

        assert mb == noOfMB;

        return matchingBlocks;


    }


    public static MatchingBlock[] getMatchingBlocks(int len1, int len2, EditOp[] ops) {

        int n = ops.length;

        int numberOfMatchingBlocks, i, spos, dpos;

        numberOfMatchingBlocks = 0;

        int o = 0;

        spos = dpos = 0;

        EditType type = EditType.KEEP;

        for (i = n; i != 0; ) {


            while (ops[o].type == EditType.KEEP && --i != 0) {
                o++;
            }

            if (i == 0)
                break;

            if (spos < ops[o].spos || dpos < ops[o].dpos) {

                numberOfMatchingBlocks++;
                spos = ops[o].spos;
                dpos = ops[o].dpos;

            }

            type = ops[o].type;

            switch (type) {
                case REPLACE:
                    do {
                        spos++;
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case DELETE:
                    do {
                        spos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case INSERT:
                    do {
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                default:
                    break;
            }
        }

        if (spos < len1 || dpos < len2) {
            numberOfMatchingBlocks++;
        }

        MatchingBlock[] matchingBlocks = new MatchingBlock[numberOfMatchingBlocks];

        o = 0;
        spos = dpos = 0;
        type = EditType.KEEP;
        int mbIndex = 0;


        for (i = n; i != 0; ) {

            while (ops[o].type == EditType.KEEP && --i != 0)
                o++;

            if (i == 0)
                break;

            if (spos < ops[o].spos || dpos < ops[o].dpos) {
                MatchingBlock mb = new MatchingBlock();

                mb.spos = spos;
                mb.dpos = dpos;
                mb.length = ops[o].spos - spos;
                spos = ops[o].spos;
                dpos = ops[o].dpos;

                matchingBlocks[mbIndex++] = mb;

            }

            type = ops[o].type;

            switch (type) {
                case REPLACE:
                    do {
                        spos++;
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case DELETE:
                    do {
                        spos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case INSERT:
                    do {
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                default:
                    break;
            }
        }

        if (spos < len1 || dpos < len2) {
            assert len1 - spos == len2 - dpos;

            MatchingBlock mb = new MatchingBlock();
            mb.spos = spos;
            mb.dpos = dpos;
            mb.length = len1 - spos;

            matchingBlocks[mbIndex++] = mb;
        }

        assert numberOfMatchingBlocks == mbIndex;

        return matchingBlocks;
    }


    private static OpCode[] editOpsToOpCodes(EditOp[] ops, int len1, int len2) {

        int n = ops.length;
        int noOfBlocks, i, spos, dpos;
        int o = 0;
        EditType type;

        noOfBlocks = 0;
        spos = dpos = 0;
        type = EditType.KEEP;

        for (i = n; i != 0; ) {

            while (ops[o].type == EditType.KEEP && --i != 0) {
                o++;
            }

            if (i == 0)
                break;

            if (spos < ops[o].spos || dpos < ops[o].dpos) {

                noOfBlocks++;
                spos = ops[o].spos;
                dpos = ops[o].dpos;

            }

            // TODO: Is this right?
            noOfBlocks++;
            type = ops[o].type;

            switch (type) {
                case REPLACE:
                    do {
                        spos++;
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case DELETE:
                    do {
                        spos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case INSERT:
                    do {
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                default:
                    break;
            }
        }

        if (spos < len1 || dpos < len2)
            noOfBlocks++;

        OpCode[] opCodes = new OpCode[noOfBlocks];

        o = 0;
        spos = dpos = 0;
        type = EditType.KEEP;
        int oIndex = 0;

        for (i = n; i != 0; ) {

            while (ops[o].type == EditType.KEEP && --i != 0)
                o++;

            if (i == 0)
                break;

            OpCode oc = new OpCode();
            opCodes[oIndex] = oc;
            oc.sbeg = spos;
            oc.dbeg = dpos;

            if (spos < ops[o].spos || dpos < ops[o].dpos) {

                oc.type = EditType.KEEP;
                spos = oc.send = ops[o].spos;
                dpos = oc.dend = ops[o].dpos;

                oIndex++;
                OpCode oc2 = new OpCode();
                opCodes[oIndex] = oc2;
                oc2.sbeg = spos;
                oc2.dbeg = dpos;

            }

            type = ops[o].type;

            switch (type) {
                case REPLACE:
                    do {
                        spos++;
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case DELETE:
                    do {
                        spos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                case INSERT:
                    do {
                        dpos++;
                        i--;
                        o++;
                    } while (i != 0 && ops[o].type == type &&
                            spos == ops[o].spos && dpos == ops[o].dpos);
                    break;

                default:
                    break;
            }

            opCodes[oIndex].type = type;
            opCodes[oIndex].send = spos;
            opCodes[oIndex].dend = dpos;
            oIndex++;
        }

        if (spos < len1 || dpos < len2) {

            assert len1 - spos == len2 - dpos;
            if (opCodes[oIndex] == null)
                opCodes[oIndex] = new OpCode();
            opCodes[oIndex].type = EditType.KEEP;
            opCodes[oIndex].sbeg = spos;
            opCodes[oIndex].dbeg = dpos;
            opCodes[oIndex].send = len1;
            opCodes[oIndex].dend = len2;

            oIndex++;

        }

        assert oIndex == noOfBlocks;

        return opCodes;

    }


    public static double getRatio(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();
        int lensum = len1 + len2;

        int editDistance = StringUtils.getLevenshteinDistance(s1, s2);

        return (lensum - editDistance) / (double) lensum;

    }


}
