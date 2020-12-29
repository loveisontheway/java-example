package com.muxi.java.example.service;

import com.muxi.java.example.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muxi
 * @since 2020-12-29
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> hign();

    List<SysRole> hignOptimizes();
}
