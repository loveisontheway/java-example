package com.muxi.java.example.basic;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        Optional<String> stream = list.stream().filter(element -> {
            System.out.println("filter() was invoked");
            return element.contains("b");
        }).map(ele -> {
            System.out.println("map() was invoked");
            return ele.toUpperCase();
        }).findFirst();
        System.out.println("----------------------");

        // forEach()方法
        Arrays.asList("try", "it", "Now")
                .forEach(System.out::println);
//                .forEach(ele -> System.out.println(ele));

/*        Pattern pattern = Pattern.compile("\\+");
        // 错误
        List<String> list2 = new ArrayList<>();
        stream.filter(s -> pattern.matcher(s).matches())
                .forEach(s -> list2.add(s));//错误的副作用使用场景
        // 正确
        List<String> list3 =
                stream.filter(s -> pattern.matcher(s).matches())
                        .collect(Collectors.toList());//无副作用*/
        System.out.println("----------------------");
        Arrays.asList("try", "it", "Now")
                .stream()
                .filter(ele -> ele.length() == 3)
                .forEach(System.out::println);

        System.out.println("----------------------");
        Arrays.asList("try", "it", "Now", "Now")
                .stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("----------------------");
        Arrays.asList("Try", "It", "Now")
                .stream()
                .sorted((str1, str2) -> str1.length() - str2.length())
                .forEach(System.out::println);

        System.out.println("----------------------");
        Arrays.asList("Try", "It", "Now")
                .stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("----------------------");
/*        Stream.of(Arrays.asList("Try", "It"), Arrays.asList("Now"))
                .flatMap(list2 -> list2.stream())
                .forEach(System.out::println);*/
    }

}

