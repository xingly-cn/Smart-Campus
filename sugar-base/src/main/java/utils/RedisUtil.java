package utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取键过期时间
     * @param key 键
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 查询键是否存在
     * @param key 键
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除键
     * @param key 键
     * @return
     */
    public boolean del(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取值
     * @param key 键
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 存入值
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入值设置时间
     * @param key 键
     * @param value 值
     * @param time 时间
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else {
                set(key,value);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增or递减
     * @param key 键
     * @param cnt 递增变量
     * @return
     */
    public long incr(String key, long cnt) {
        if (cnt > 0) {
            return redisTemplate.opsForValue().increment(key,cnt);
        }else if (cnt < 0) {
            cnt = -cnt;
            return redisTemplate.opsForValue().decrement(key,cnt);
        }
        return 0;
    }
}
