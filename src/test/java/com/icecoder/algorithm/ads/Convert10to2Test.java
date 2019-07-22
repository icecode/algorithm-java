package com.icecoder.algorithm.ads;

import com.icecoder.algorithm.ads.stack.Convert10to2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-22 23:20
 */
public class Convert10to2Test {

    @Test
    public void test() {
        Assertions.assertEquals("1", Convert10to2.convert(1));
        Assertions.assertEquals("10", Convert10to2.convert(2));
        Assertions.assertEquals("11", Convert10to2.convert(3));
        Assertions.assertEquals("100", Convert10to2.convert(4));
        Assertions.assertEquals("101", Convert10to2.convert(5));
        Assertions.assertEquals("110", Convert10to2.convert(6));
        Assertions.assertEquals("111", Convert10to2.convert(7));
        Assertions.assertEquals("1000", Convert10to2.convert(8));
        Assertions.assertEquals("1001", Convert10to2.convert(9));

        Assertions.assertEquals("11111111", Convert10to2.convert(255));
    }
}
