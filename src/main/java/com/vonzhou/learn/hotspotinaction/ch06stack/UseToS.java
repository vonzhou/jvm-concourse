package com.vonzhou.learn.hotspotinaction.ch06stack;

/**
 * @version 2018/3/12.
 */
public class UseToS {
    public static void main(String[] args) {
        UseToS useToS = new UseToS();
        useToS.compute(10, 20);
    }

    public int compute(int a, int b) {
        a = a + 1;
        b = b - 2;
        int tmp = a * b;
        return tmp;
    }
}
