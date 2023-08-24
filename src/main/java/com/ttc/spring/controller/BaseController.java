package com.ttc.spring.controller;

import com.ttc.spring.exception.BusinessErrorCode;
import com.ttc.spring.model.BaseResponse;
import com.ttc.spring.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BaseController {
    @Value("${ms-template-timeout-request}")
    int timeoutRequest;

    private BaseResponse<?> handleError(String message, BusinessErrorCode responseCode) {
        return BaseResponse.ofFailed(responseCode, message);
    }

    public CompletableFuture<BaseResponse<?>> handle(CompletableFuture<BaseResponse<?>> completableFuture, String clientMessageId) {
        return completableFuture
                .completeOnTimeout(
                        handleError("Request time out", ResponseCode.REQUEST_TIMEOUT),
                        timeoutRequest,
                        TimeUnit.MILLISECONDS
                ).handle((result, ex) -> {
                    if (Objects.nonNull(ex)) {
                        log.error(clientMessageId, ex);
                        return handleError("Transaction Fail", ResponseCode.INTERNAL_SERVER_ERROR);
                    }
                    return result;
                });
    }
}
