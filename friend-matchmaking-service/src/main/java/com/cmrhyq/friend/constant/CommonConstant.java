package com.cmrhyq.friend.constant;

/**
 * 通用常量
 *
 * @author <a href="https://github.com/cmrhyq">AlanHuang</a>
 */
public interface CommonConstant {

    /**
     * 升序
     */
    String SORT_ORDER_ASC = "ascend";

    /**
     * 降序
     */
    String SORT_ORDER_DESC = " descend";

    /**
     * redis的系统key前缀
     */
    String SYSTEM_REDIS_KEY = "friend-matchmaking";

    /**
     * 任务的分布式锁
     */
    String PRE_CACHE_JOB_LOCK = String.format("%s:precachejob:docache:lock", SYSTEM_REDIS_KEY);

    /**
     * redis的key过期时间
     */
    String REDIS_EXPIRED_TIME = "30000";
    
}
