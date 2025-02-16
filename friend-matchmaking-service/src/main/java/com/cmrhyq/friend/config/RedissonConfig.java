package com.cmrhyq.friend.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname RedissonConfig.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.config
 * @date 2025/2/16 17:04
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@Configuration
@EnableConfigurationProperties
// 有bug，无法注入，暂时注释掉
//@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig {

    private String host = "127.0.0.1";

    private String port = "6379";

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        String redisAddress = String.format("redis://%s:%s", host, port);
        System.out.println("redisAddress = " + redisAddress);
        config.useSingleServer()
                .setAddress(redisAddress)
                .setDatabase(2);
        // 创建实例
        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }
}
