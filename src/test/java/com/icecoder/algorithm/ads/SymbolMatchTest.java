package com.icecoder.algorithm.ads;

import com.icecoder.algorithm.ads.stack.SymbolMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 23:57
 */
public class SymbolMatchTest {

    @Test
    public void match() {
        Assertions.assertTrue(SymbolMatch.isMatch("((()))", '(', ')'));
        Assertions.assertTrue(SymbolMatch.isMatch("(()****()(((*)))*)", '(', ')'));
        Assertions.assertFalse(SymbolMatch.isMatch("(()****()(((*)))*)(", '(', ')'));
    }
}
