package com.muxi.java.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 * @author pecm
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisTemplateCache {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 高效批量存储List
     *
     * @param key      k
     * @param dataList data
     * @param <T>      obj
     */
    public <T> void setPipelineList(final String key, final List<T> dataList) {
        RedisSerializer keySer = redisTemplate.getKeySerializer();
        RedisSerializer valSer = redisTemplate.getValueSerializer();
        List<T> list = redisTemplate.executePipelined((RedisCallback<String>) connection -> {
            connection.set(keySer.serialize(key), valSer.serialize(dataList));
            // 不可以调用connection.closePipeline()方法，
            // 因为本来就有隐式的代理调用收集了返回值
            // List<T> list = connection.closePipeline();
            // 依靠代理的返回，此处固定返回null
            return null;
        });
    }

    public <T> List<T> getPipelineList(final String key) {
        RedisSerializer keySer = redisTemplate.getKeySerializer();
        List<T> list = redisTemplate.executePipelined((RedisCallback<T>) connection -> {
            connection.get(keySer.serialize(key));
            return null;
        });
        return list;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取Hash数据（key > List）
     *
     * @param key key
     * @return List<T>
     */
    public <T> List<T> getCacheMapValue(final String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key key
     * @param hkey hash key
     * @return Long
     */
    public Long delCacheMapValue(final String key, Object... hkey) {
        return redisTemplate.opsForHash().delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return boolean
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除List指定对象元素
     * 原列表: {1， 2， 3， 4， 5， 2， 1， 2， 5}
     * 传入参数 value=2, count=1 表示删除列表中value为2的元素一次
     * 执行后列表: {1， 3， 4， 5， 2， 1， 2， 5}
     *
     * @param key   key
     * @param count 次数
     * @param value 等同值
     */
    public Long removeList(final String key, int count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    public Object getListIndex(final String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 删除通配符key（多个）
     *
     * @param key key
     * @return long
     */
    public Long removeAst(final String key) {
        Set<String> keys = redisTemplate.keys(key);
        return redisTemplate.delete(keys);
    }
}
