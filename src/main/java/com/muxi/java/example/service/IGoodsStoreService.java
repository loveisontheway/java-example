package com.muxi.java.example.service;

import com.muxi.java.example.domain.GoodsStore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muxi
 * @since 2020-11-06
 */
public interface IGoodsStoreService extends IService<GoodsStore> {

    int updateStore(String code, Integer store);

    GoodsStore getGoodsStore(String code);

    String updateGoodsStore(String code, int count);
}
