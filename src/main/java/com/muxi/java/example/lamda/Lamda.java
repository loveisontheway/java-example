package com.muxi.java.example.lamda;

import java.util.Arrays;

/**
 * Lamda表达式 - 函数式编程
 *
 * Java 8 引入 Lambda 表达式的主要作用就是简化代码编写，它只是一个语法糖而已，
 * 底层还是基于函数接口来实现的，如下面的示例那样：
 * --------------------------------------
 * // 基于函数接口
 * Runnable task1 = new Runnable() {
 *     @Override
 *     public void run() {
 *         // business code
 *     }
 * };
 * // Lamdba 写法
 * Runnable task2 = () -> {
 *     // business code
 * };
 * --------------------------------------
 *
 * @author jl.jiang 2021/4/15
 */
public class Lamda {

    public static void main(String[] args) {
        /** 1. 最简单的 Lambda 表达式可以用逗号分隔的参数列表、-> 箭头符号以及语句块：*/
        Arrays.asList("a", "b", "d")
                .forEach(e -> System.out.println(e));
        System.out.println("------------------------");
        /** 2.Lambda表达式可能会有返回值，编译器会根据上下文推断返回值的类型，*/
        //   如果lambda的语句块只有一行，可以省略return关键字。
        // 传统的方式
        /*
        Arrays.asList("a", "d", "c").sort(new Comparator<String>() {
            @Override
            public int compare(String e1, String e2) {
                return e1.compareTo(e2);
            }
        });
         */
        // Lambda 语法
        Arrays.asList("a", "d", "c").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
        // Lambda 语法精简
        Arrays.asList("a", "d", "c").sort((e1, e2) -> e1.compareTo(e2));

        /*
         * 对局部变量的限制    我们在使用过程中，常常发现 Lamdba 引用局部变量必须是final的或者说是隐式的final的，
         *                  这主要是因为实例变量和局部变量背后的实现有一个关键不同：实例变量都存储在堆中，
         *                  而局部变量则保存在栈上。堆是线程共享的，而栈是线程私有的，换句话说，
         *                  Lambda 表达式引用的是值，而不是变量。
         *
         * 方法引用          Java 8 还提供了方法应用来进一步简化代码的编写，
         *                  就像上个示例中代码，我们还能进一步简写为：
         */
        /** 3. 方法引用 */
        Arrays.asList("a", "b", "d").sort(String::compareTo);
    }
}
