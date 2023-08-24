package com.ttc.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttc.spring.exception.BusinessErrorCode;
import com.ttc.spring.exception.BusinessException;
import com.ttc.spring.model.BaseResponse;
import com.ttc.spring.utils.ResponseCode;
import com.ttc.spring.utils.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletionException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(BusinessException.class)
    public void handleBusinessException(BusinessException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        handle(e, e.getErrorCode(), request, response);
    }

    @ExceptionHandler(CompletionException.class)
    public void handleCompletionException(CompletionException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        var cause = e.getCause();
        if (cause instanceof BusinessException) {
            handleBusinessException((BusinessException) cause, request, response);
            return;
        }
        handleException(e, request, response);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public void handleMissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        handle(e, ResponseCode.INVALID_FIELD_FORMAT, request, response);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingServletRequestPartException(MissingServletRequestParameterException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        handle(e, ResponseCode.MISSING_PARAMETER, request, response);
    }
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleGobalException(e, ResponseCode.INTERNAL_SERVER_ERROR, request, response);
    }

    private <T extends Exception> void handle(T e, BusinessErrorCode errorCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        var errorResponse = BaseResponse.ofFailed(errorCode, e.getMessage());
        log.info("Request: {} {}, Response: {}", request.getMethod(), request.getRequestURL(), objectMapper.writeValueAsString(errorResponse), e);
        writeResponse(response, errorCode.getHttpStatus(), errorResponse);
    }


    private void handleGobalException(Exception e, BusinessErrorCode errorCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("[UNI-ERR]: {}", e.getMessage());
        var errorResponse = BaseResponse.ofFailed(errorCode, "Lỗi không xác định từ hệ thống");
        log.error("Request: {} {}, Response: {}", request.getMethod(), request.getRequestURL(), objectMapper.writeValueAsString(errorResponse), e);
        writeResponse(response, errorCode.getHttpStatus(), errorResponse);
    }

    private void writeResponse(HttpServletResponse servletResponse, int httpStatus, BaseResponse<?> errorResponse) throws IOException {
        byte[] body = objectMapper.writeValueAsBytes(errorResponse);
        Utility.writeResponse(servletResponse, httpStatus, body);
    }
}
