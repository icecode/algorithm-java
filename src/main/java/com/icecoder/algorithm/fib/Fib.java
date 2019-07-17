package com.icecoder.algorithm.fib;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-15 23:24
 */
public class Fib {


    public static long fib(int n) {
        return n > 1 ? fib(n - 1) + fib(n - 2) : n;
    }


    public static void main(String[] args) {
        System.out.println(fib(8));
    }
}
