package com.cmrhyq.friend.job.cycle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cmrhyq.friend.common.ResultUtils;
import com.cmrhyq.friend.mapper.UserMapper;
import com.cmrhyq.friend.model.entity.User;
import com.cmrhyq.friend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.cmrhyq.friend.constant.CommonConstant.SYSTEM_REDIS_KEY;

/**
 * 增量同步帖子到 es
 *
 * @author <a href="https://github.com/cmrhyq">AlanHuang</a>
 */
@Slf4j
// todo 取消注释开启任务
@Component
public class IncSyncPostToEs {


}
