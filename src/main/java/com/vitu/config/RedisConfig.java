package com.vitu.config;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Singleton
public class RedisConfig {


    @Value("${redis.uri}")
    String redisHost;

    @Value("${redis.port}")
    int redisPort;

    @Value("${redis.password}")
    String redisPassword;

    public Jedis getJedis() {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort, 2000, redisPassword);
        return jedisPool.getResource();
    }

    public String get(String chave) {
        return getJedis().get(chave);
    }

    public void set(String chave, String valor) {
        getJedis().set(chave, valor);
    }

}
