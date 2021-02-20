package com.muxi.java.example.exception;

/**
 * Throwable 作为顶层父类，派生出了 Error 和 Exception 两个子类。
 * - Error：错误。Error 类以及它的子类的示例，
 *         代表了 JVM 本身的错误，
 *         错误不能被程序员通过代码处理，Error 一般很少出现。
 * - Exception：异常。Exception 类以及它的子类，
 *             代表程序运行时发送的各种不期望发生的时间。
 *             可以被 Java 异常 处理机制使用，是异常处理的核心。
 *
 * Java 中提供了三种可抛出结构（throwable） ：
 *     受检异常（checked exception）
 *     运行时异常（run-time exception）
 *     错误（error）
 * 如果期望调用者能够适当地恢复程序，这种情况下我们就应该使用受检异常。
 * 通过抛出受检异常，我们应该在一个 catch 子句中处理该异常，或者将它传播出去，让调用者处理。
 * 运行时异常 和 错误 都属于 非受检可抛出结构。
 * 如果程序没有捕捉到这样的可抛出结构，将会导致当前线程中断。
 *
 * 注：不要滥用异常！！！
 *
 * @author jl.jiang 2021/2/20
 */
public class Exception {

    /**
     * 1、不管有没有出现异常，finally块中代码都会执行
     * 2、当 try和 catch中有return时，finally仍然会执行
     * 3、finally是在try中return后面的表达式运算后执行的
     *  （此时并没有返回运算后的值，而是先把要返回的值保存起来，
     *   管finally中的代码怎么样，返回的值都不会改变，仍然是之前保存的值），
     *   所以函数返回值是在finally执行前确定的
     * 4、finally中最好不要包含return，否则程序会提前退出，返回值不是try或catch中保存的返回值
     */
    public static String exceptionReturn() {
        try {
            System.out.println("打印try");
            return "try块";
        } catch (ArithmeticException e) {
            System.out.println("打印catch");
            return "catch块";
        } finally {
            System.out.println("打印finally");
            return "finally块";
        }
    }

    public static void main(String[] args) {
        System.out.println(Exception.exceptionReturn());
    }
}
