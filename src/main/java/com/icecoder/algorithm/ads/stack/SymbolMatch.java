package com.icecoder.algorithm.ads.stack;

import com.icecoder.algorithm.adt.ArrayStack;
import com.icecoder.algorithm.adt.Stack;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 23:48
 */
public class SymbolMatch {

    public static boolean isMatch(String searchStr, char leftChar, char rightChar) {
        Stack<Integer> leftSymbol = new ArrayStack<>();
        int symbolDepth = 0;
        for (char c : searchStr.toCharArray()) {
            if (c == leftChar) {
                leftSymbol.push(symbolDepth++);
            } else if (c == rightChar) {
                if (leftSymbol.empty()) {
                    return false;
                }
                leftSymbol.pop();
            }
        }
        return leftSymbol.empty();
    }
}
