package com.muxi.java.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.muxi.java.example.model.GoodsStore;
import com.muxi.java.example.mapper.GoodsStoreMapper;
import com.muxi.java.example.redis.RedisLock;
import com.muxi.java.example.service.IGoodsStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author muxi
 * @since 2020-11-06
 */
@Service
public class GoodsStoreServiceImpl extends ServiceImpl<GoodsStoreMapper, GoodsStore> implements IGoodsStoreService {

    @Autowired
    private RedisLock redisLock;

    /**
     * 超时时间 5s
     */
    private static final int TIMEOUT = 5 * 1000;

    @Override
    public int updateStore(String code, Integer store) {
        GoodsStore entity = this.getGoodsStore(code);
        LambdaUpdateWrapper<GoodsStore> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(GoodsStore::getStore, entity.getStore() - store);
        updateWrapper.eq(GoodsStore::getCode, code);
        boolean result = this.update(updateWrapper);
        return result ? 1 : 0;
    }

    /**
     * 获取库存对象
     */
    @Override
    public GoodsStore getGoodsStore(String code) {
        LambdaQueryWrapper<GoodsStore> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GoodsStore::getCode, code);
        return this.getOne(queryWrapper);
    }

    /**
     * 根据产品编号更新库存
     */
    @Override
    public String updateGoodsStore(String code, int count) {
        //上锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(code, String.valueOf(time))) {
            return "排队人数太多，请稍后再试.";
        }
        System.out.println("获得锁的时间戳：" + String.valueOf(time));
        try {
            GoodsStore goodsStore = getGoodsStore(code);
            if (goodsStore != null) {
                if (goodsStore.getStore() <= 0) {
                    return "对不起，卖完了，库存为：" + goodsStore.getStore();
                }
                if (goodsStore.getStore() < count) {
                    return "对不起，库存不足，库存为：" + goodsStore.getStore() + " 您的购买数量为：" + count;
                }
                System.out.println("剩余库存：" + goodsStore.getStore());
                System.out.println("扣除库存：" + count);
                this.updateStore(code, count);
                try {
                    //为了更好的测试多线程同时进行库存扣减，在进行数据更新之后先等1秒，让多个线程同时竞争资源
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "恭喜您，购买成功！";
            } else {
                return "获取库存失败。";
            }
        } finally {
            //释放锁
            redisLock.release(code, String.valueOf(time));
            System.out.println("释放锁的时间戳：" + String.valueOf(time));
        }

        /*try {
            GoodsStore goodsStore = getGoodsStore(code);
            if (goodsStore != null) {
                if (goodsStore.getStore() <= 0) {
                    return "对不起，卖完了，库存为：" + goodsStore.getStore();
                }
                if (goodsStore.getStore() < count) {
                    return "对不起，库存不足，库存为：" + goodsStore.getStore() + " 您的购买数量为：" + count;
                }
                System.out.println("剩余库存：" + goodsStore.getStore());
                System.out.println("扣除库存：" + count);
                this.updateStore(code, count);
                try {
                    //为了更好的测试多线程同时进行库存扣减，在进行数据更新之后先等1秒，让多个线程同时竞争资源
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "恭喜您，购买成功！";
            } else {
                return "获取库存失败。";
            }
        } finally {

        }*/
    }
}
