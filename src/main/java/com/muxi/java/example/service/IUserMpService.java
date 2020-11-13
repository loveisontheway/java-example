package com.muxi.java.example.service;

import com.muxi.java.example.domain.UserMp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muxi
 * @since 2020-10-30
 */
public interface IUserMpService extends IService<UserMp> {

    void add();
}
