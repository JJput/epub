package com.zy.lib100;

import java.util.Scanner;

public class myClass {

    private static int[] numbers;
    private static String[] signs;
    /**
     * 计算的符号，按优先顺序从左至右
     */
    private static final String[] SIGN = {"+", "-", "*", "/"};

    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            getNumbersAndSign(in);
            System.out.print(getValue() + " \n");
        }
    }

    /**
     * 将表达式字符串中的所有数值按顺序取出
     *
     * @param data 表达式字符串
     * @return
     */
    public static void getNumbersAndSign(String data) {
        numbers = new int[data.length() / 2 + 1];
        signs = new String[data.length() / 2];
        int count = 0;
        //计算
        for (int i = 0; i < data.length(); i++) {
            //1.依次取出String的每一位
            String numCache = data.substring(i, i + 1);
            //2.判断是否为符号位
            if (!isSign(numCache)) {
                //3.当前number是否已经有数值 有：自身*10+数值 无：直接赋值
                numbers[count] = (numbers[count] == 0 ? 0 : numbers[count] * 10) + getNumber(numCache);
            } else {
                signs[count] = numCache;
                count++;
            }
        }
    }

    public static int getValue() {
        int num = 0;
        for (int i = 0; i < SIGN.length; i++) {
            int cache = signValue(SIGN[i]);
            if(cache!=0){
                num = cache;
            }
        }
        return num;
    }

    /**
     * 按符号计算
     *
     * @param sign 符号
     */
    public static int signValue(String sign) {
        int num = 0;
        int i=0;
        while(i<signs.length && signs[i]!=null){
            if (signs[i].equals(sign)) {
                switch (sign) {
                    case "+":
                        num = numbers[i] + numbers[i + 1];
                        break;
                    case "-":
                        num = numbers[i] - numbers[i + 1];
                        break;
                    case "*":
                        num = numbers[i] * numbers[i + 1];
                        break;
                    case "/":
                        num = numbers[i] / numbers[i + 1];
                        break;
                    default:
                        System.out.print("错误符号:" + sign);
                        break;
                }
                numbers[i] = num;
                numbers[i + 1] = num;
            }
            i++;
        }
        return num;
    }

    /**
     * 是否为符号位
     *
     * @param data
     * @return
     */
    public static boolean isSign(String data) {
        if (data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/")) {
            return true;
        }
        return false;
    }

    /**
     * String转int数值
     *
     * @param data
     * @return
     */
    public static int getNumber(String data) {
        //根据Ascii值 48~58为0~9
        return ((int) data.getBytes()[0]) - 48;
    }
}
