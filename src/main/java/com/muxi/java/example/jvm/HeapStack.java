package com.muxi.java.example.jvm;

/**
 * Heap Stack
 *
 * @author jjl
 * @date 2022/9/23
 */
class Obj {
    private int year;
    private int month;
    private int day;

    public Obj(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}

/**
 * 堆(heap)                 栈(stack)
 * ----------------------------------
 * new HeapStack()          a -> 11
 * new Obj                  hs
 * year=2022                i -> 123
 * month=9                  obj
 * day=23                   y -> 2022
 *                          m -> 9
 *                          d -> 23
 * ----------------------------------
 * 说明:
 * > a为局部变量
 * > i,y,m,d都是形参为局部变量
 * > year，month，day为成员变量
 * 1. main
 *      方法开始执行：int a = 11;
 *      a 局部变量，基础类型，引用和值都存在栈中。
 * 2. HeapStack hs = new HeapStack();
 *      hs 为对象引用，存在栈中，对象 new HeapStack() 存在堆中。
 * 3. hs.change(a);
 *      调用 change(int i) 方法，i为局部变量，引用和值存在栈中。当方法change执行完成后，i就会从栈中消失。
 * 4. Obj obj = new Obj(2022, 9, 23);
 *      调用Obj类的构造函数生成对象。
 *      obj为对象引用，存在栈中；
 *      对象 new Obj() 存在堆中；
 *      其中 y,m,d 为局部变量存储在栈中，且它们的类型为基础类型，因此它们的数据也存储在栈中；
 *      year,month,day为 Obj 对象的的成员变量，它们存储在堆中存储的 new Obj() 对象里面；
 *      当 Obj 构造方法执行完之后 y,m,d 将从栈中消失。
 * 5. main 方法执行完之后。
 *      a 变量，hs，obj 引用将从栈中消失；
 *      new HeapStack(),new obj() 将等待垃圾回收器进行回收。
 */
public class HeapStack {
    public static void main(String[] args) {
        int a = 11;
        HeapStack hs = new HeapStack();
        hs.change(a);
        Obj obj = new Obj(2022, 9, 23);
    }

    public void change(int i) {
        i = 123;
    }
}