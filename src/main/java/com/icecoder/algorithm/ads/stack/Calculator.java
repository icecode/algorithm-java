package com.icecoder.algorithm.ads.stack;

import com.icecoder.algorithm.adt.ArrayStack;
import com.icecoder.algorithm.adt.Stack;

/**
 * @author libing
 * @version 1.0
 * @date 2019-07-23 21:37
 */
public class Calculator {


    public static int infix(String expression) {
        Stack<Character> oper = new ArrayStack<>();
        Stack<Integer> opnd = new ArrayStack<>();
        char[] evalChars = expression.toCharArray();
        int scanIndex = 0;
        char c;
        do {
            StringBuilder sb = new StringBuilder();
            while (scanIndex < evalChars.length) {
                c = evalChars[scanIndex++];
                if (c == ' ') {
                    if (sb.length() > 0) {
                        opnd.push(Integer.parseInt(sb.toString()));
                        sb.setLength(0);
                        break;
                    } else {
                        continue;
                    }
                }
                if (isOperator(c)) {
                    oper.push(c);
                } else {
                    sb.append(c);
                    if (scanIndex == evalChars.length) {
                        opnd.push(Integer.parseInt(sb.toString()));
                    }
                }
            }
            //比较最近的2个操作符
            if (oper.size() >= 2) {
                char op2 = oper.pop();
                char op1 = oper.pop();
                if (isPriority(op2)) {
                    oper.push(op1);
                    int v2 = opnd.pop();
                    int v1 = opnd.pop();
                    opnd.push(eval(op2, v1, v2));
                } else {
                    oper.push(op2);
                    int v3 = opnd.pop();
                    int v2 = opnd.pop();
                    int v1 = opnd.pop();
                    opnd.push(eval(op1, v1, v2));
                    opnd.push(v3);
                }
            }
        } while (scanIndex < evalChars.length || oper.size() > 2);
        int v2 = opnd.pop();
        int v1 = opnd.pop();
        return eval(oper.pop(), v1, v2);
    }

    private static int eval(char op, int v1, int v2) {
        System.out.println("eval " + v1 + " " + op + " " + v2);
        switch (op) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '*':
                return v1 * v2;
            case '/':
                return v1 / v2;
            default:
                throw new IllegalArgumentException("" + v1 + op + v2);
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * 判断是否是优先计算操作符
     * @param op
     * @return
     */
    private static boolean isPriority(char op) {
        return ('*' == op || '/' == op);
    }
}
