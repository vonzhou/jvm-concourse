package com.vonzhou.learn.hotspotinaction.ch08is;

/**
 public static int f1();
 descriptor: ()I
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=1, locals=0, args_size=0
 0: iconst_1
 1: ireturn
 LineNumberTable:
 line 8: 0

 public static long f2();
 descriptor: ()J
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=2, locals=0, args_size=0
 0: lconst_1
 1: lreturn
 LineNumberTable:
 line 12: 0

 public static boolean f3();
 descriptor: ()Z
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=1, locals=0, args_size=0
 0: iconst_1
 1: ireturn
 LineNumberTable:
 line 16: 0

 public static com.vonzhou.learn.hotspotinaction.ch08is.ReturnType$Foo f4();
 descriptor: ()Lcom/vonzhou/learn/hotspotinaction/ch08is/ReturnType$Foo;
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=2, locals=0, args_size=0
 0: new           #2                  // class com/vonzhou/learn/hotspotinaction/ch08is/ReturnType$Foo
 3: dup
 4: invokespecial #3                  // Method com/vonzhou/learn/hotspotinaction/ch08is/ReturnType$Foo."<init>":()V
 7: areturn
 LineNumberTable:
 line 20: 0

 * @version 2018/3/14.
 */
public class ReturnType {
    public static int f1() {
        return 1;
    }

    public static long f2() {
        return 1L;
    }

    public static boolean f3() {
        return true;
    }

    public static Foo f4() {
        return new Foo();
    }

    static class Foo {

    }

    public static void main(String[] args) {
        f1();
    }
}
