package com.ttc.spring.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String PREFIX_RESPONSE_CODE;
    public static String OK_CODE;
    public static String GROUP_CODE_SUCCESS = "00";
    public static String GROUP_CODE_DATA_INVALID = "01";
    public static String GROUP_CODE_BUSINESS = "02";
    public static String GROUP_CODE_AUTHORIZATION = "03";
    public static String GROUP_CODE_CONNECT_TIMEOUT = "04";
    public static String GROUP_CODE_UNKNOWN = "99";

    @Value("${response-code.prefix}")
    public void setSvnUrl(String prefixResponseCode) {
        PREFIX_RESPONSE_CODE = prefixResponseCode;
        OK_CODE = PREFIX_RESPONSE_CODE + "-" + GROUP_CODE_SUCCESS + "-" + 200;
    }

    public static final String CLIENT_MESSAGE_ID = "clientMessageId";
}
