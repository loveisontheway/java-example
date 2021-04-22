package com.muxi.java.example.question;

import java.util.Arrays;

/**
 * 牛客网 - 题库
 *
 * @author jl.jiang 2021/4/21
 */
public class NowCoder {

    /*
    1、题目描述
        请实现一个函数，将一个字符串中的每个空格替换成“%20”。
        例如，当字符串为We Are Happy.
        则经过替换之后的字符串为We%20Are%20Happy。
     */
    public static String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }

    /*
    2、题目描述
        在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
        每一列都按照从上到下递增的顺序排序。请完成一个函数，
        输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
        [
          [1,2,8,9],
          [2,4,9,12],
          [4,7,10,13],
          [6,8,11,15]
        ]
        给定 target = 7，返回 true。
        给定 target = 3，返回 false。
     */
    public static boolean Find(int target, int[][] array) {
        boolean flag = false;
        if (array.length <= 0) {
            return flag;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (target == array[i][j]) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /*
    3、题目描述
        数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
        例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
        超过数组长度的一半，因此输出2。如果不存在则输出0。
     */
    public static int MoreThanHalfNum_Solution(int[] array) {
        int a = array.length / 2;
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            int num = 0;
            int val = array[i];
            for (int j = 0; j < array.length; j++) {
                if (val == array[j]) {
                    num++;
                }
            }
            if (num >= a) {
                idx = val;
            }
        }
        return idx;
    }

    /*
    题目描述
        牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，
        写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，
        但却读不懂它的意思。例如，“student. a am I”。后来才意识到，
        这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
        Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     */
    public static String ReverseSentence(String str) {
        String[] strArr = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = strArr.length - 1; i >= 0; i--) {
            sb.append(strArr[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("---------- 1题 ----------");
        String s = "We Are Happy";
        System.out.println(NowCoder.replaceSpace(s));
        System.out.println("---------- 2题 ----------");
        int target = 3;
        int[][] array = {{2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(NowCoder.Find(target, array));
        System.out.println("---------- 3题 ----------");
        int[] arr = {1, 7, 3, 7, 7, 7, 5, 4, 7};
        System.out.println(NowCoder.MoreThanHalfNum_Solution(arr));
        System.out.println("---------- 4题 ----------");
        String str = "nowcoder. a am I";
        System.out.println(NowCoder.ReverseSentence(str));
    }
}
