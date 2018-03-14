package com.vonzhou.learn.hotspotinaction.ch08is.dispatch;

public class PrepareInvokeDemo {

    public static void main(String[] args) {
        PrepareInvokeDemo demo = new PrepareInvokeDemo();
        demo.callInvokevirtual();
    }

    /**
     * invokevirtual
     */
    public void callInvokevirtual() {
        add(3, 5);
    }

    public int add(int i, int j) {
        return i + j;
    }

    /**
     * invokestatic
     */
    public static void callInvokestatic() {
        staticAdd(3, 5);
    }

    public static int staticAdd(int i, int j) {
        return i + j;
    }

}
