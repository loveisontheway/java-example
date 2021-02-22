package com.muxi.java.example.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * 斗地主案例代码
 *
 * @author jl.jiang 2021/2/22
 */
public class PokerGame {
    public static void main(String[] args) {
        HashMap<Integer, String> hm = new HashMap<>();
        ArrayList<Integer> array = new ArrayList<>();

        String[] colors = {"♥", "♠", "♣", "♦"};
        String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // 生成一副扑克牌（54张）
        int index = 0;
        for (String number : numbers) {
            for (String color : colors) {
                String poker = color.concat(number);
                hm.put(index, poker);
                array.add(index);
                index++;
            }
        }
        hm.put(index, "小王");
        array.add(index);
        index++;
        hm.put(index, "大王");
        array.add(index);

        // 洗牌
        Collections.shuffle(array);

        // 发牌（发的是编号，为了保证编号是排序的，使用TreeSet接收）
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();
        TreeSet<Integer> handcards = new TreeSet<Integer>();

        for (int x = 0; x < array.size(); x++) {
            if (x >= array.size() - 3) {
                handcards.add(array.get(x));
            } else if (x % 3 == 0) {
                player1.add(array.get(x));
            } else if (x % 3 == 1) {
                player2.add(array.get(x));
            } else if (x % 3 == 2) {
                player3.add(array.get(x));
            }
        }
        System.out.println("------欢乐斗地主------");
        // 看牌(遍历TreeSet集合，获取编号，到HashMap集合找对应的牌)
        lookpocker("player1", player1, hm);
        lookpocker("player2", player2, hm);
        lookpocker("player3", player3, hm);
        lookpocker("预留牌", handcards, hm);
    }

    /**
     * 看牌功能
     */
    public static void lookpocker(String name, TreeSet<Integer> ts, HashMap<Integer, String> hm) {
        System.out.println(name);
        for (Integer key : ts) {
            String value = hm.get(key);
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
