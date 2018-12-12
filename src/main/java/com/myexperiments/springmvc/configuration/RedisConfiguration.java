package com.myexperiments.springmvc.configuration;

import com.myexperiments.springmvc.configuration.condition.RedisDbCondition;
import com.myexperiments.springmvc.domain.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@PropertySource("classpath:beans-config.properties")
@Conditional(RedisDbCondition.class)
@EnableRedisRepositories(basePackages="com.myexperiments.springmvc.domain.service.redis")
public class RedisConfiguration {

    /**
     * Instantiating the connection factory via its default constructor results in a connection
     * factory that creates its connections for localhost, port 6379, and with no password.
     * */
    @Bean
    public RedisConnectionFactory redisCF() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Product> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, Product> redis = new RedisTemplate<>();
        redis.setConnectionFactory(cf);
        return redis;
    }

}
