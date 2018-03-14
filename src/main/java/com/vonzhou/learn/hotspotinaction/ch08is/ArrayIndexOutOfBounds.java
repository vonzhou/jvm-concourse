package com.vonzhou.learn.hotspotinaction.ch08is;

/**
 * @version 2018/3/13.
 */
public class ArrayIndexOutOfBounds {
    public static void main(String[] args) {
        int[] src = {10, 20, 30, 40, 50};
        int dst = src[10]; // 数组访问，越界检查
    }
}
