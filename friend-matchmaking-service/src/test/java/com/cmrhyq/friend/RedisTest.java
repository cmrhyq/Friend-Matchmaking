package com.cmrhyq.friend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname RedisTest.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend
 * @date 2025/1/14 14:13
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("alan", "123");
        valueOperations.set("kevin", "123");
        // 查
        Object alan = valueOperations.get("alan");
        Assertions.assertTrue("123".equals(alan));
        System.out.println("alan = " + alan);
        Object kevin = valueOperations.get("kevin");
        Assertions.assertTrue("123".equals(kevin));
        System.out.println("kevin = " + kevin);
    }
}
