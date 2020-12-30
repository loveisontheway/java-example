package com.muxi.java.example.web;


import com.muxi.java.example.domain.SysRole;
import com.muxi.java.example.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 高并发下redis缓存穿透问题解决方案
 * </p>
 * 使用场景：我们在日常的开发中，经常会遇到查询数据列表的问题，有些数据是不经常变化的，
 *         如果想做一下优化，在提高查询的速度的同时减轻数据库的压力，那么redis缓存绝对是一个好的解决方案。
 * 需求：假设有10000个请求，想达到第一次请求从数据库中获取，其他9999个请求从redis中获取这种效果
 *
 * @author muxi
 * @since 2020-12-29
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 常规写法
     * 常规的这种写法单线程没有问题，
     * 但是考虑到并发的存在，就会出现缓存渗透的问题，
     * 也就是不能保证其他9999个请求都是从redis中取。
     *
     * @return String
     */
    @GetMapping(value = "/hign")
    public String hign() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 10000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    List<SysRole> list = sysRoleService.hign();
                }
            });
        }
        return "hign thread over";
    }

    /**
     * 常规写法改进（使用双重检测锁，解决常规写法问题）
     *
     * @return String
     */
    @GetMapping(value = "/hignOptimizes")
    public String hignOptimizes() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 10000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    List<SysRole> list = sysRoleService.hignOptimizes();
                }
            });
        }
        return "hignOptimizes thread over";
    }

}

