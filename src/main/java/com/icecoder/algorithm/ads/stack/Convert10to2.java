package com.icecoder.algorithm.ads.stack;

import com.icecoder.algorithm.adt.ArrayStack;
import com.icecoder.algorithm.adt.Stack;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 23:15
 */
public class Convert10to2 {

    public static String convert(int val) {
        Stack<Integer> vt = new ArrayStack<Integer>();
        while (val != 0) {
            vt.push(val % 2);
            val /= 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!vt.empty()) {
            sb.append(vt.pop());
        }
        return sb.toString();
    }
}
