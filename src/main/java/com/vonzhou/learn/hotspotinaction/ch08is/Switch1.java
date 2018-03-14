package com.vonzhou.learn.hotspotinaction.ch08is;

/**
 * @version 2018/3/13.
 */
public class Switch1 {
    public static void doSwitch(int i) {
        switch (i) {
            case -1:
                break;
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
        }
    }

    public static void doSwitch2(int i) {
        switch (i) {
            case -1:
                break;
            case 1:
                break;
            case 3:
                break;
            case 5:
                break;
            default:
        }
    }

    public static void doSwitch3(int i) {
        switch (i) {
            case -1:
                break;
            case 1:
                break;
            case 3:
                break;
            case 15:
                break;
            default:
        }
    }

    public static void doSwitch4(String i) {
        switch (i) {
            case "shiyan":
                break;
            case "hangzhou":
                break;
            case "beijing":
                break;
            default:
        }
    }

    public static void main(String[] args) {
        doSwitch(1);
    }
}
