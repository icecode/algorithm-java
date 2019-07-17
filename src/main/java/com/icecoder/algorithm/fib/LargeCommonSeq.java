package com.icecoder.algorithm.fib;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-16 08:27
 */
public class LargeCommonSeq {


    static final AtomicInteger RECURSION_COUNTER = new AtomicInteger(1);

    static char[] recursion(char[] v1, char[] v2) {
        return recursion(v1, v2, new char[0]);
    }

    static char[] recursion(char[] v1, char[] v2, char[] res) {
        RECURSION_COUNTER.incrementAndGet();
        if (v1.length == 0 || v2.length == 0) {
            return res;
        }
        if (v1[0] == v2[0]) {
            char[] newRes = new char[res.length + 1];
            System.arraycopy(res, 0, newRes, 0, res.length);
            newRes[res.length] = v1[0];
            char[] newV1 = new char[v1.length - 1];
            char[] newV2 = new char[v2.length - 1];
            System.arraycopy(v1, 1, newV1, 0, v1.length - 1);
            System.arraycopy(v2, 1, newV2, 0, v2.length - 1);
            return recursion(newV1, newV2, newRes);
        } else {
            char[] newV1 = new char[v1.length - 1];
            char[] newV2 = new char[v2.length - 1];
            System.arraycopy(v1, 1, newV1, 0, v1.length - 1);
            System.arraycopy(v2, 1, newV2, 0, v2.length - 1);
            char[] res1 = recursion(v1, newV2, res);
            char[] res2 = recursion(newV1, v2, res);
            return res1.length > res2.length ? res1 : res2;
        }
    }

    public static void main(String[] args) {
        System.out.println(recursion("aprogram".toCharArray(), "algorithm".toCharArray()));
        System.out.println("max recursion:" + RECURSION_COUNTER.intValue());
    }
}
