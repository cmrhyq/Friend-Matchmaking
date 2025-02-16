package com.cmrhyq.friend;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname RedissonTest.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend
 * @date 2025/2/16 19:49
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redisson;

    @Test
    public void testRedisson() {
        RList<String> list = redisson.getList("test-map");
        list.add("A");
        System.out.println("list.get(0) = " + list.get(0));

        RMap<String, String> map = redisson.getMap("test-map");
        map.put("A", "B");
        System.out.println("map.get(A) = " + map.get("A"));
    }
}
