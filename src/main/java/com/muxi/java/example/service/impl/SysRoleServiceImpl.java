package com.muxi.java.example.service.impl;

import com.muxi.java.example.domain.SysRole;
import com.muxi.java.example.io.FileIOStream;
import com.muxi.java.example.mapper.SysRoleMapper;
import com.muxi.java.example.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author muxi
 * @since 2020-12-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 常规写法
     *
     * @return List<SysRole>
     */
    @Override
    public List<SysRole> hign() {
        try {
            //序列化器，将key的值设置为字符串
            RedisSerializer redisSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(redisSerializer);

            // 查缓存
            List<SysRole> list = (List<SysRole>) redisTemplate.opsForValue().get("allRole");
            if (null == list) {
                list = this.list();
                redisTemplate.opsForValue().set("allRole", list);
                System.out.println("从数据库中取数据");
            } else {
                System.out.println("从缓存中取数据");
            }
            return list;
        } catch (Exception e) {
            log.error("SysRoleService.hign error", e);
        }
        return null;
    }

    /**
     * 常规写法改进
     *
     * @return List<SysRole>
     */
    @Override
    public List<SysRole> hignOptimizes() {
        try {

            //序列化器，将key的值设置为字符串
            RedisSerializer redisSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(redisSerializer);

            // 查缓存
            List<SysRole> list = (List<SysRole>) redisTemplate.opsForValue().get("allRole");
            if (list == null) {
                // 双重检测 锁
                synchronized (this) {
                    List<SysRole> list2 = (List<SysRole>) redisTemplate.opsForValue().get("allRole");
                    if (list2 == null) {
                        list = this.list();
                        redisTemplate.opsForValue().set("allRole", list);
                        System.out.println("从数据库中取数据");
                    } else {
                        System.out.println("从缓存中取数据");
                    }
                }
            } else {
                System.out.println("从缓存中取数据");
            }
            return list;
        } catch (Exception e) {
            log.error("SysRoleService.hignOptimizes error", e);
        }
        return null;
    }

}
