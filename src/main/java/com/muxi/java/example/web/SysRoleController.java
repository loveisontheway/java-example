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
     * 常规写法改成（使用双重检测锁，解决常规写法问题）
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

