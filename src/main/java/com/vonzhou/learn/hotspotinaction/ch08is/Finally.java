package com.vonzhou.learn.hotspotinaction.ch08is;

public class Finally {

    /**
     public void finally1();
     descriptor: ()V
     flags: ACC_PUBLIC
     Code:
     stack=2, locals=2, args_size=1
     0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     3: iconst_1
     4: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
     7: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     10: iconst_2
     11: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
     14: goto          27
     17: astore_1
     18: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     21: iconst_2
     22: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
     25: aload_1
     26: athrow
     27: return
     Exception table:
     from    to  target type
     0     7    17   any
    
     */
    public void finally1() {
        try {
            System.out.println(1);
        } finally {
            System.out.println(2);
        }
    }

    /**
     public void finally2_break();
     descriptor: ()V
     flags: ACC_PUBLIC
     Code:
     stack=2, locals=3, args_size=1
     0: bipush        33
     2: istore_1
     3: iload_1
     4: lookupswitch  { // 1
     0: 24
     default: 51
     }
     24: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     27: iconst_1
     28: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
     31: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     34: iconst_2
     35: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
     38: goto          51
     41: astore_2
     42: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     45: iconst_2
     46: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
     49: aload_2
     50: athrow
     51: return
     Exception table:
     from    to  target type
     24    31    41   any
     */
    public void finally2_break() {
        int i = 33;
        switch (i) {
            case 0:
                try {
                    System.out.println(1);
                    break;
                } finally {
                    System.out.println(2);
                }
        }
    }

	/**
	 public void finally2_continue();
	 descriptor: ()V
	 flags: ACC_PUBLIC
	 Code:
	 stack=2, locals=3, args_size=1
	 0: iconst_0
	 1: istore_1
	 2: iload_1
	 3: bipush        100
	 5: if_icmpge     41
	 8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 11: iconst_1
	 12: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 15: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 18: iconst_2
	 19: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 22: goto          35
	 25: astore_2
	 26: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 29: iconst_2
	 30: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 33: aload_2
	 34: athrow
	 35: iinc          1, 1
	 38: goto          2
	 41: return
	 Exception table:
	 from    to  target type
	 8    15    25   any
	 */
    public void finally2_continue() {
        for (int i = 0; i < 100; i++)
            try {
                System.out.println(1);
                continue;
            } finally {
                System.out.println(2);
            }
    }

	/**
	 public void finally3_return();
	 descriptor: ()V
	 flags: ACC_PUBLIC
	 Code:
	 stack=2, locals=2, args_size=1
	 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 3: iconst_1
	 4: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 7: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 10: iconst_2
	 11: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 14: return
	 15: astore_1
	 16: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 19: iconst_2
	 20: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 23: aload_1
	 24: athrow
	 Exception table:
	 from    to  target type
	 0     7    15   any

	 */
    public void finally3_return() {
        try {
            System.out.println(1);
            return;
        } finally {
            System.out.println(2);
        }

    }

	/**
	 public void finally4_ex() throws java.lang.Exception;
	 descriptor: ()V
	 flags: ACC_PUBLIC
	 Code:
	 stack=3, locals=2, args_size=1
	 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 3: iconst_1
	 4: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 7: new           #4                  // class java/lang/Exception
	 10: dup
	 11: ldc           #5                  // String exception in try block
	 13: invokespecial #6                  // Method java/lang/Exception."<init>":(Ljava/lang/String;)V
	 16: athrow
	 17: astore_1
	 18: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
	 21: iconst_2
	 22: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
	 25: aload_1
	 26: athrow
	 Exception table:
	 from    to  target type
	 0    18    17   any
     */
    public void finally4_ex() throws Exception {
        try {
            System.out.println(1);
            throw new Exception("exception in try block");
        } finally {
            System.out.println(2);
        }

    }

    public static void main(String[] args) {
        new Finally().finally1();
    }

}
