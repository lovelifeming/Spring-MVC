package com.zsm.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * ValueOperations　　——基本数据类型和实体类的缓存
 * ListOperations　　   ——list的缓存
 * SetOperations　　  ——set的缓存
 * HashOperations　　——Map的缓存
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/4/3.
 * @Modified By:
 */
@Service
@Transactional(readOnly = true)
public class RedisCacheUtil<T>
{
    @Autowired
    @Qualifier("jedisTemplate")
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value)
    {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return operations;
    }

    /**
     * 获得缓存的基本对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(String key /*,ValueOperations<String,T> operation*/)
    {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 缓存List数据
     *
     * @param key
     * @param dataList
     * @param <T>
     * @return
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList)
    {
        ListOperations operations = redisTemplate.opsForList();
        if (null != dataList)
        {
            int size = dataList.size();
            for (int i = 0; i < size; i++)
            {
                operations.rightPush(key, dataList.get(i));
            }
        }
        return operations;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getCacheList(String key)
    {
        List<T> dataList = new ArrayList<>();
        ListOperations<String, T> operations = redisTemplate.opsForList();
        Long size = operations.size(key);
        for (int i = 0; i < size; i++)
        {
            dataList.add((T)operations.leftPop(key));
        }
        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key
     * @param dataSet
     * @param <T>
     * @return
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet)
    {
        BoundSetOperations<String, T> operations = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            operations.add(it.next());
        }
        return operations;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public Set<T> getCacheSet(String key /*,BoundSetOperations<String,T> operation*/)
    {
        Set<T> dataSet = new HashSet<>();
        BoundSetOperations<String, T> operations = redisTemplate.boundSetOps(key);
        Long size = operations.size();
        for (int i = 0; i < size; i++)
        {
            dataSet.add(operations.pop());
        }
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @param <T>
     * @return
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap)
    {
        HashOperations operations = redisTemplate.opsForHash();
        if (null != dataMap)
        {
            for (Map.Entry<String, T> entry : dataMap.entrySet())
            {
                operations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return operations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key /*,HashOperations<String,String,T> hashOperation*/)
    {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 缓冲Integer Map
     *
     * @param key
     * @param dataMap
     * @param <T>
     * @return
     */
    public <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap)
    {
        HashOperations operations = redisTemplate.opsForHash();
        if (null != dataMap)
        {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet())
            {
                operations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return operations;
    }

    /**
     * 获取缓存 Integer Map
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Map<Integer, T> getCacheIntegerMap(String key /*,HashOperations<String,String,T> hashOperation*/)
    {
        Map<Integer, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }
}
