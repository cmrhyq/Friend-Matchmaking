package com.cmrhyq.friend.service;

import cn.hutool.core.lang.Assert;
import com.cmrhyq.friend.model.entity.User;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void getSafetyUser() {
    }

    @Test
    void searchUsersByTags() {
        List<String> tags = Arrays.asList("Java", "Python");
        List<User> userList = userService.searchUsersByTags(tags);
        System.out.println("userList = " + userList);
        Assert.notNull(userList);
    }
}