package com.cmrhyq.friend.job.cycle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cmrhyq.friend.model.entity.User;
import com.cmrhyq.friend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.cmrhyq.friend.constant.CommonConstant.PRE_CACHE_JOB_LOCK;
import static com.cmrhyq.friend.constant.CommonConstant.SYSTEM_REDIS_KEY;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname PreCacheJob.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.job.cycle
 * @date 2025/2/16 14:15
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@Slf4j
// todo 取消注释开启任务
@Component
public class PreCacheJob {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @Scheduled(cron = "0 15 11 * * ?")
    public void doCacheRecommendUser() throws InterruptedException {
        RLock lock = redissonClient.getLock(PRE_CACHE_JOB_LOCK);
        try {
            // 加锁, 30s后自动释放锁,只有一个线程可以获取锁
            // waitTime 设置成 0 表示立即获取锁，只抢一次， 抢不到就返回 false
            // waitTime 等待时间, leaseTime 锁过期时间, 单位: 毫秒
            // leaseTime 设置成 -1 表示锁不自动过期，直到主动释放锁
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)){
                log.info("获取到锁: {}", Thread.currentThread().getId());
                String redisKey = SYSTEM_REDIS_KEY + ":user:recommend";
                ValueOperations valueOperations = redisTemplate.opsForValue();
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                Page<User> userPage = userService.page(new Page<>(1, 20), queryWrapper);
                // 写缓存
                try {
                    valueOperations.set(redisKey, userPage, 1, TimeUnit.DAYS);
                } catch (Exception e) {
                    log.error("Redis set key error: ", e);
                }
            } else {
                log.info("获取锁失败: {}", Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            log.error("Redis job error: ", e);
        } finally {
            // 释放锁
            if (lock.isHeldByCurrentThread()){
                log.info("释放锁: {}", Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
