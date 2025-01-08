package com.cmrhyq.friend.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname SseEmitterService.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.service
 * @date 2025/1/8 16:17
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
public interface SseEmitterService {

    /**
     * 获取连接
     * @param clientId 客户端id
     * @return
     */
    public SseEmitter getConn(@NotBlank String clientId);

    /**
     * 发送消息
     * @param clientId
     * @param message
     */
    public void send(@NotBlank String clientId, String message) throws IOException;

    /**
     * 关闭连接
     * @param clientId
     */
    public void close(@NotBlank String clientId);
}
