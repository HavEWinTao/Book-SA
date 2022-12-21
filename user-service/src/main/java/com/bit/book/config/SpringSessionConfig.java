package com.bit.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class SpringSessionConfig {
    @Bean("SpringSessionDefaultRedisSerializer")
    public RedisSerializer defaultRedisSerializer(){
        return getSerializer();
    }

    private RedisSerializer getSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
