package com.icecoder.algorithm.ads;

import com.icecoder.algorithm.ads.stack.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-23 22:04
 */
public class CalculatorTest {

    @Test
    public void infix() {
        Assertions.assertEquals(3, Calculator.infix("1 + 2"));
        Assertions.assertEquals(0, Calculator.infix("1 + 2 - 3"));
        Assertions.assertEquals(-297, Calculator.infix("1 + 2 - 3 * 100"));
        Assertions.assertEquals(0, Calculator.infix("0 * 1 * 2 * 3 * 4 * 5"));
        Assertions.assertEquals(30, Calculator.infix("100 + 2 * 3 * 5 - 100"));
        Assertions.assertEquals(7, Calculator.infix("1 + 2 + 3 + 4 / 4"));
        Assertions.assertEquals(16, Calculator.infix("1 + 2 + 3 + 4 / 4 * 10"));
    }
}
