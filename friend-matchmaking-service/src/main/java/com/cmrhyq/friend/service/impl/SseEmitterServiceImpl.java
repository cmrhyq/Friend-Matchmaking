package com.cmrhyq.friend.service.impl;

import com.cmrhyq.friend.service.SseEmitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname SseEmitterServiceImpl.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.service.impl
 * @date 2025/1/8 16:18
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@Slf4j
@Service
public class SseEmitterServiceImpl implements SseEmitterService {

    private static final Map<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取连接
     *
     * @param clientId 客户端id
     * @return
     */
    @Override
    public SseEmitter getConn(String clientId) {
        SseEmitter sseEmitter = SSE_CACHE.get(clientId);
        if (sseEmitter != null) {
            return sseEmitter;
        } else {
            SseEmitter emitter = new SseEmitter(600_000L);
            emitter.onTimeout(() -> {
                log.info("连接已经超时，正准备关闭，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
            });
            emitter.onCompletion(() -> {
                log.info("连接已关闭，准备释放，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
                log.info("连接已经释放，clientId = {}", clientId);
            });
            emitter.onError(throwable -> {
                log.info("连接已异常，正准备关闭，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
            });
            SSE_CACHE.put(clientId, emitter);
            return emitter;
        }
    }

    /**
     * 发送消息
     *
     * @param clientId
     * @param message
     */
    @Override
    public void send(String clientId, String message) throws IOException {
        SseEmitter sseEmitter = SSE_CACHE.get(clientId);
        sseEmitter.send(message, MediaType.APPLICATION_JSON);
        sseEmitter.complete();
    }

    /**
     * 关闭连接
     *
     * @param clientId
     */
    @Override
    public void close(String clientId) {
        SseEmitter sseEmitter = SSE_CACHE.get(clientId);
        if (sseEmitter != null) {
            sseEmitter.complete();
        }
    }
}
