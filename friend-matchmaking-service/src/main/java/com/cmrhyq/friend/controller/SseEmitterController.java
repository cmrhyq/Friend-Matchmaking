package com.cmrhyq.friend.controller;

import com.cmrhyq.friend.common.BaseResponse;
import com.cmrhyq.friend.common.ErrorCode;
import com.cmrhyq.friend.common.ResultUtils;
import com.cmrhyq.friend.exception.BusinessException;
import com.cmrhyq.friend.service.SseEmitterService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CompletableFuture;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v0.0.1
 * @classname SseEmitter.java
 * @project friend-matchmaking-service
 * @package com.cmrhyq.friend.controller
 * @date 2025/1/8 16:11
 * @email cmrhyq@163.com
 * @since v0.0.1
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class SseEmitterController {
    @Autowired
    private SseEmitterService sseEmitter;

    @GetMapping("/connect/{clientId}")
    @ApiOperation(value = "建立SseEmitter连接")
    public SseEmitter connect(@PathVariable String clientId) {
        SseEmitter emitter = sseEmitter.getConn(clientId);
        CompletableFuture.runAsync(() -> {
            try{
                sseEmitter.send(clientId,"连接成功");
            } catch (Exception e){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        });
        return emitter;
    }

    @PostMapping("/send/{clientId}")
    @ApiOperation(value = "发送信息给SseEmitter")
    public SseEmitter send(@PathVariable String clientId, @RequestBody String message) {
        SseEmitter emitter = sseEmitter.getConn(clientId);
        CompletableFuture.runAsync(() -> {
            try{
                sseEmitter.send(clientId, message);
            } catch (Exception e){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        });
        return emitter;
    }

    @GetMapping("/close/{clientId}")
    @ApiOperation(value = "关闭SseEmitter连接")
    public BaseResponse<Boolean> close(@PathVariable String clientId) {
        sseEmitter.close(clientId);
        return ResultUtils.success(true);
    }
}
