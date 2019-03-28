package com.wonhigh.i18n.ms.common.utils;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

/**
 * redis cache 工具类
 * 
 */
public final class RedisUtil {

	private RedisTemplate<Serializable, Object> redisTemplate;

	private RedisTemplate<Serializable, Integer> redisIntegerTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

	public void setRedisIntegerTemplate(RedisTemplate<Serializable, Integer> redisIntegerTemplate) {
		this.redisIntegerTemplate = redisIntegerTemplate;
	}

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (!keys.isEmpty())
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			LOGGER.error("Exception{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			LOGGER.error("Exception{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setInteger(final String key, Integer value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Integer> operations = redisIntegerTemplate.opsForValue();
			operations.increment(key,value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			LOGGER.error("Exception", e);
		}
		return result;
	}

	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * list---插入key，返回相同的value值--目前用于验证码
	 * @param key
	 * @param value
	 * @return
	 */
	public <T> long llpush(final String key, final Class<T> classType, final T value) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				long count = connection.lPush(serializer.serialize(key),
						new JacksonJsonRedisSerializer(classType).serialize(value));
				return count;
			}
		});
		return result;
	}

	/**
	 * list---删除第一个值，返回最前面一条的value值
	 * @param key
	 * @return
	 */
	public <T> T rrpop(final String key, final Class<T> classType) {
		T result = redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] res = connection.rPop(serializer.serialize(key));
				return (T) new JacksonJsonRedisSerializer(classType).deserialize(res);
			}
		});
		return result;
	}

	public long lPush(final String key, final Object value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	public Object rPop(final String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 设置key的失效时间
	 * @param key
	 * @param expire
	 * @return
	 */
	public boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	/**
	 * list---删除最后值，返回最后一条的value值
	 * @param key
	 * @return
	 */
	public String lpop(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] res = connection.lPop(serializer.serialize(key));
				return serializer.deserialize(res);
			}
		});
		return result;
	}

	/**
	 * list---删除第一个值，返回最前面一条的value值
	 * @param key
	 * @return
	 */
	public String rpop(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] res = connection.rPop(serializer.serialize(key));
				return serializer.deserialize(res);
			}
		});
		return result;
	}

	/**
	 * list---获取长度
	 * @param key
	 * @return
	 */
	public Long lLen(final String key) {
		Long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				Long count = connection.lLen(serializer.serialize(key));
				return count;
			}
		});
		return result;
	}

	public RedisTemplate<Serializable, Object> getRedisTemplate() {
		return redisTemplate;
	}

	/**
	 * 自增1操作
	 * @param key
	 * @param expireTime
	 * @return
	 */
	public boolean  incr(final String key,Long expireTime){

		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.increment(key, 1L);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			LOGGER.error("Exception{}", e.getMessage());
		}
		return result;

	}
	
	public long getIncrValue(final String key) {
		
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer=redisTemplate.getStringSerializer();
				byte[] rowkey=serializer.serialize(key);
				byte[] rowval=connection.get(rowkey);
				try {
					Long var=0L;
					String val=serializer.deserialize(rowval);
					if(StringUtils.isNotBlank(val)){
						var = Long.parseLong(val);
					}
					return var;
				} catch (Exception e) {
					e.printStackTrace();
					return 0L;
				}
			}
		});
	}

	
	
	
	

    /**
     * 判断当前key是否存在
     * @param key
     * @param value 值
     * @param expireTime 以秒计算
     * @return
     */
	public boolean setnx(final String key, final String value,final Long expireTime){

        return  redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Boolean flag = redisConnection.setNX(serializer.serialize(key), serializer.serialize(value));
				redisConnection.expire(serializer.serialize(key),expireTime);
//                redisTemplate.expire(serializer.serialize(key),expireTime,TimeUnit.SECONDS);
                return flag;
            }
        });

    }
}