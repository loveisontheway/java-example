package com.muxi.java.example.array;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Array
 * type[] arrayName;    // 数据类型[] 数组名;
 * type arrayName[];    // 数据类型 数组名[];
 *
 * @author jl.jiang 2022/3/21
 */
public class Array {

    public static void main(String[] args) {
//        int[] prices = new int[5]; // 声明数组并分配空间
//        Scanner input = new Scanner(System.in); // 接收用户从控制台输入的数据
//        for (int i = 0; i < prices.length; i++) {
//            System.out.println("请输入第" + (i + 1) + "件商品的价格：");
//            prices[i] = input.nextInt(); // 接收用户从控制台输入的数据
//        }
//        System.out.println(Arrays.toString(prices));
//        System.out.println("第 3 件商品的价格为：" + prices[2]);

        String phone = "[\"15925666566\",\"15036004285\"]";
        String name = "[\"鼎时科技\",\"鼎时科技\"]";
//方法2
        JSONArray array = new JSONArray();
//        array.add("value1");
//        array.add("value2");
//        array.add("value3");
        String[] phoneArr = {"15925666566", "15036004285"};
        String[] unameArr = {"Jack", "Lily"};
        for (String tel : phoneArr) {
            array.add(tel);
        }
        System.out.println(phoneArr.length);
        JSONArray nameArr = new JSONArray();
        for(int i=0;i<phoneArr.length;i++){
            nameArr.add("鼎时科技");
        }
        String param = "[{\"pro\":\"1234\",\"step\":\"1234\",\"name\":\"1234\"},{\"pro\":\"1234\",\"step\":\"1234\",\"name\":\"1234\"}]";
        // obj
        JSONObject objJson = JSONUtil.createObj();
        List<Template> tmpList = new ArrayList<Template>();
        for (int j = 0; j < unameArr.length; j++) {
            Template tmp = new Template();
            tmp.setPro("( Test工程 )");
            tmp.setStep("[ 部门分配 ]");
            tmp.setName(unameArr[j]);
            tmpList.add(tmp);
        }
//转为JSONArray字符串
        System.out.println(array.toString());
        System.out.println(nameArr.toString());
        System.out.println(JSONUtil.toJsonStr(tmpList));
    }

}
