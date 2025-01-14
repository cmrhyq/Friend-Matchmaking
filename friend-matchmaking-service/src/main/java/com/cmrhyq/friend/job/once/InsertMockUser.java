package com.cmrhyq.friend.job.once;

import com.cmrhyq.friend.model.entity.User;
import com.cmrhyq.friend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname InsertMockUser.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.job.once
 * @date 2024/12/27 13:20
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@Slf4j
@Component
public class InsertMockUser implements CommandLineRunner {

    @Resource
    private UserService UserService;

    /**
     * 参数：核心线程数，最大线程数，过期回收时间，时间单位，任务队列
     * 问题1：什么时候线程数会超过60，答：任务队列满了以后就会往下继续增加线程去做任务，就会慢慢增加到最大线程数
     * 问题2：如果最大线程数也满了怎么办，答：可以去指定任务的策略，默认的策略是中断策略
     */
    private ExecutorService executorService = new ThreadPoolExecutor(60, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    /**
     * 往数据库插入mock数据
     *
     * @param dataCount 数据条数
     */
    public void insertUser(int dataCount) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < dataCount; i++) {
            User user = new User();
            user.setUsername("虚假用户" + i);
            user.setUserAccount("faker_user_" + i);
            user.setUserAvatar("https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg");
            user.setUserPassword("6f59a2c7c58c853ea39666a8e9d69074");
            user.setUserStatus(1);
            user.setPhone("17712344321");
            user.setEmail("abc@gmail.com");
            user.setUserGender(1);
            user.setUserProfile("test");
            user.setUserRole("user");
            user.setIsDelete(0);
            user.setTags("[]");
            userList.add(user);
        }
        UserService.saveBatch(userList, 100);
    }

    /**
     * 并发往数据库插入数据
     */
    public void concurrencyInsertUser() {
        int batchSize = 1000;
        int j = 0;
        List<CompletableFuture<Void>> futuresList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<User> userList = new ArrayList<>();
            do {
                j++;
                User user = new User();
                user.setUsername("虚假用户" + i);
                user.setUserAccount("faker_user_" + i);
                user.setUserAvatar("https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg");
                user.setUserPassword("6f59a2c7c58c853ea39666a8e9d69074");
                user.setUserStatus(1);
                user.setPhone("17712344321");
                user.setEmail("abc@gmail.com");
                user.setUserGender(1);
                user.setUserProfile("test");
                user.setUserRole("user");
                user.setIsDelete(0);
                user.setTags("[]");
                userList.add(user);
            } while (j % batchSize != 0);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                log.info("thread name: {}", Thread.currentThread().getName());
                UserService.saveBatch(userList, batchSize);
            }, executorService);
            futuresList.add(future);
        }
        CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[]{})).join();
    }

    @Override
    public void run(String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        insertUser(100);
//        concurrencyInsertUser();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
