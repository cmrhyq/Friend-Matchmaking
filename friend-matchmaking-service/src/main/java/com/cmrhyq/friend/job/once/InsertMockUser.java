package com.cmrhyq.friend.job.once;

import com.cmrhyq.friend.model.entity.User;
import com.cmrhyq.friend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
@Component
public class InsertMockUser implements CommandLineRunner {

    @Resource
    private UserService UserService;

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
    public void concurrencyInsertUser(){
        int DATA_COUNT = 100000;
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
            } while (j % 10000 != 0);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                UserService.saveBatch(userList, 10000);
            });
            futuresList.add(future);
        }
        CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[]{})).join();
    }

    @Override
    public void run(String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        insertUser(100);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
