package com.muxi.java.example.service.impl;

import com.muxi.java.example.mapper.UserMpMapper;
import com.muxi.java.example.model.UserMp;
import com.muxi.java.example.service.IUserMpService;
import com.muxi.java.example.utils.ITask;
import com.muxi.java.example.utils.MultiThreadUtils;
import com.muxi.java.example.utils.ResultBean;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muxi
 * @since 2020-10-30
 */
@Service
public class UserMpServiceImpl extends ServiceImpl<UserMpMapper, UserMp> implements IUserMpService, ITask<ResultBean<String>, UserMp> {

    @Override
    public void add() {
        List<UserMp> data = new ArrayList<>();
        UserMp user1 = new UserMp();
        user1.setName("A");
        user1.setAge(22);
        user1.setEmail("a@a.com");

        UserMp user2 = new UserMp();
        user2.setName("B");
        user2.setAge(25);
        user2.setEmail("b@b.com");

        UserMp user3 = new UserMp();
        user3.setName("C");
        user3.setAge(30);
        user3.setEmail("c@c.com");

        UserMp user4 = new UserMp();
        user4.setName("D");
        user4.setAge(28);
        user4.setEmail("d@d.com");

        UserMp user5 = new UserMp();
        user5.setName("E");
        user5.setAge(32);
        user5.setEmail("e@e.com");

        data.add(user1);
        data.add(user2);
        data.add(user3);
        data.add(user4);
        data.add(user5);

        // 创建多线程处理任务
        MultiThreadUtils<UserMp> threadUtils = MultiThreadUtils.newInstance(3);
        ITask<ResultBean<String>, UserMp> task = new UserMpServiceImpl();
        // 辅助参数  加数
        Map<String, Object> params = new HashMap<>();
        params.put("addNum", 4);
        // 执行多线程处理，并返回处理结果
        ResultBean<List<ResultBean<String>>> resultBean = threadUtils.execute(data, params, task);
    }

    @Override
    public ResultBean<String> execute(UserMp userMp, Map<String, Object> params) {
        /**
         * 具体业务逻辑：将list中的元素加上辅助参数中的数据返回
         */
        int addNum = Integer.valueOf(String.valueOf(params.get("addNum")));
        ResultBean<String> resultBean = ResultBean.newInstance();
        resultBean.setData(userMp.toString());
        return resultBean;
    }
}
