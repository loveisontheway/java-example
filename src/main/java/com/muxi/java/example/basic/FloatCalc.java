package com.muxi.java.example.basic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 虽然某些场景下推荐使用BigDecimal，它能够达到更好的精度，
 * 但性能相较于double和float，还是有一定的损失的，
 * 特别在处理庞大，复杂的运算时尤为明显。故一般精度的计算没必要使用BigDecimal。
 *
 * @author jjl
 * @date 2022/7/22
 */
public class FloatCalc {

    public static void main(String[] args) {
        float a = 1;
        float b = 0.9f;
        System.out.println(a - b);
//        之所以产生这样的结果，是因为0.1的二进制表示是无限循环的。
//        由于计算机的资源是有限的，所以是没办法用二进制精确的表示 0.1，
//        只能用「近似值」来表示，就是在有限的精度情况下，
//        最大化接近 0.1 的二进制数，于是就会造成精度缺失的情况。
        System.out.println("------------------------");

//        BigDecimal c = new BigDecimal("0.01");
        BigDecimal c = new BigDecimal(0.01);
        BigDecimal d = BigDecimal.valueOf(0.01);
        System.out.println("c: " + c);
        System.out.println("d: " + d);
//        之所以会出现上述现象，是因为new BigDecimal时，
//        传入的0.1已经是浮点类型了，鉴于上面说的这个值只是近似值，
//        在使用new BigDecimal时就把这个近似值完整的保留下来了。
//        第一，在使用BigDecimal构造函数时，尽量传递字符串而非浮点类型；
//        第二，如果无法满足第一条，则可采用BigDecimal#valueOf方法来构造初始化值。
//        BigDecimal(int)       创建一个具有参数所指定整数值的对象。
//        BigDecimal(double)    创建一个具有参数所指定双精度值的对象。
//        BigDecimal(long)      创建一个具有参数所指定长整数值的对象。
//        BigDecimal(String)    创建一个具有参数所指定以字符串表示的数值的对象。
        System.out.println("------------------------");

        BigDecimal e = new BigDecimal("0.01");
        BigDecimal f = new BigDecimal("0.010");
        System.out.println(e.equals(f));
        System.out.println(e.compareTo(f));
//        如果比较两个BigDecimal值的大小，采用其实现的compareTo方法；返回的值为-1（小于），0（等于），1（大于）
//        如果严格限制精度的比较，那么则可考虑使用equals方法。equals方法不仅比较了值是否相等，还比较了精度是否相同。
//        比较BigDecimal("0")、BigDecimal("0.0")、BigDecimal("0.00")，此时一定要使用compareTo方法进行比较。
        System.out.println("------------------------");

        BigDecimal g = new BigDecimal("1.0");
        BigDecimal h = new BigDecimal("3.0");
//        g.divide(h);
        // 如果在除法（divide）运算过程中，如果商是一个无限小数（0.333…），
        // 而操作的结果预期是一个精确的数字，那么将会抛出ArithmeticException异常。
        // 解决办法
        BigDecimal j = g.divide(h, 2, RoundingMode.HALF_UP);
//        RoundingMode.CEILING：取右边最近的整数
//        RoundingMode.DOWN：去掉小数部分取整，也就是正数取左边，负数取右边，相当于向原点靠近的方向取整
//        RoundingMode.FLOOR：取左边最近的正数
//        RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数
//        RoundingMode.HALF_UP:四舍五入，负数原理同上
//        RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
//        在使用BigDecimal进行（所有）运算时，一定要明确指定精度和舍入模式。
        System.out.println("j: " + j);
        System.out.println("------------------------");

        BigDecimal k = BigDecimal.valueOf(35634535255456719.22345634534124578902);
        System.out.println(k.toString());
        System.out.println(k.toPlainString());
        System.out.println(k.toEngineeringString());
//        toPlainString()：不使用任何科学计数法；
//        toString()：在必要的时候使用科学计数法；
//        toEngineeringString() ：在必要的时候使用工程计数法。类似于科学计数法，
//        只不过指数的幂都是3的倍数，这样方便工程上的应用，因为在很多单位转换的时候都是10^3；
//        根据数据结果展示格式不同，采用不同的字符串输出方法，通常使用比较多的方法为toPlainString() 。
        System.out.println("------------------------");

        // NumberFormat类的format()方法可以使用BigDecimal对象作为其参数，
        // 可以利用BigDecimal对超出16位有效数字的货币值，百分值，以及一般数值进行格式化控制。
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位​

        BigDecimal loanAmount = new BigDecimal("15000.48"); //金额
        BigDecimal interestRate = new BigDecimal("0.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
        System.out.println("------------------------");
    }
}
