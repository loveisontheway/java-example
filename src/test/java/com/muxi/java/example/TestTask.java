package com.muxi.java.example;


import com.muxi.java.example.consts.DateConst;
import com.muxi.java.example.enums.EstimateTypeEnum;
import com.muxi.java.example.utils.DateUtil;
import com.muxi.java.example.utils.MultiThreadUtil;
import com.muxi.java.example.domain.User;
import com.muxi.java.example.utils.ITask;
import com.muxi.java.example.utils.ResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体执行业务任务 需要 实现ITask接口  在execute中重写业务逻辑
 */
public class TestTask implements ITask<ResultBean<String>, User> {

    @Override
    public ResultBean execute(User e, Map<String, Object> params) {

        String date = DateConst.DATE;
        Integer year = DateUtil.getYear();
        String val = EstimateTypeEnum.getName("E2501");
        /**
         * 具体业务逻辑：将list中的元素加上辅助参数中的数据返回
         */
        int addNum = Integer.valueOf(String.valueOf(params.get("addNum")));
//        e = e + addNum;
        ResultBean<String> resultBean = ResultBean.newInstance();
        resultBean.setData(e.toString());
        return resultBean;
    }

    public static void main(String[] args) {
        // 需要多线程处理的大量数据list
        /*List<Integer> data = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            data.add(i + 1);
        }*/
        List<User> data = new ArrayList<>();
        data.add(new User("小A","男",22));
        data.add(new User("小B","男",29));
        data.add(new User("小C","女",25));
        data.add(new User("小D","男",30));

        // 创建多线程处理任务
        MultiThreadUtil<User> threadUtils = MultiThreadUtil.newInstance(3);
        ITask<ResultBean<String>, User> task = new TestTask();
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("addNum", 4);
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(data, params, task);
    }


}
