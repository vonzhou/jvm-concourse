package com.vonzhou.learn.hotspotinaction.ch07interpreter;

/**
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintInterpreter
 * @version 2018/3/12.
 */
public class Inc {

    public int inc(int i) {
        i++;
        return i;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int x = new Inc().inc(i);
            System.out.println(x);
        }
    }
}
