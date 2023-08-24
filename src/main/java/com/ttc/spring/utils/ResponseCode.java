package com.ttc.spring.utils;

import com.ttc.spring.exception.BusinessErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResponseCode {
    public static final BusinessErrorCode INTERNAL_SERVER_ERROR =
            new BusinessErrorCode(5000, Constants.GROUP_CODE_UNKNOWN, "Internal server error", 503);

    public static final BusinessErrorCode INTERNAL_STORAGE_ERROR =
            new BusinessErrorCode(5001, Constants.GROUP_CODE_UNKNOWN, "Internal storage error", 503);
    public static final BusinessErrorCode INVALID_FIELD_FORMAT =
            new BusinessErrorCode(4013, Constants.GROUP_CODE_DATA_INVALID, "Invalid field format", 400);
    public static final BusinessErrorCode UNAUTHORIZED =
            new BusinessErrorCode(4016, Constants.GROUP_CODE_AUTHORIZATION, "You need to login to to access this resource", 401);
    public static final BusinessErrorCode FORBIDDEN =
            new BusinessErrorCode(4017, Constants.GROUP_CODE_AUTHORIZATION, "You don't have permission to to access this resource", 403);
    public static final BusinessErrorCode MISSING_PARAMETER =
            new BusinessErrorCode(4023, Constants.GROUP_CODE_DATA_INVALID, "Missing parameter", 400);
    public static final BusinessErrorCode REQUEST_TIMEOUT =
            new BusinessErrorCode(4024, Constants.GROUP_CODE_CONNECT_TIMEOUT, "Request timeout", 500);
    public static final BusinessErrorCode ERROR_DATA_INPUT =
            new BusinessErrorCode(4025, Constants.GROUP_CODE_DATA_INVALID, "Request timeout", 500);
    public static final BusinessErrorCode FORMAT_DATE_INVALID =
            new BusinessErrorCode(4026, Constants.GROUP_CODE_DATA_INVALID, "Date format error", 400);
    public static final BusinessErrorCode INVALID_FIELD_NAME =
            new BusinessErrorCode(4027, Constants.GROUP_CODE_DATA_INVALID, "Field name is invalid", 400);
    public static final BusinessErrorCode BOOLEAN_FIELD_NAME =
            new BusinessErrorCode(4028, Constants.GROUP_CODE_DATA_INVALID, "Boolean name is invalid", 400);
    public static final BusinessErrorCode NUMBER_FORMAT_ERROR =
            new BusinessErrorCode(4029, Constants.GROUP_CODE_DATA_INVALID, "Number format error", 400);
    public static final BusinessErrorCode UNSUPPORTED_ACTION_REF =
            new BusinessErrorCode(4030, Constants.GROUP_CODE_DATA_INVALID, "Unsupported action ref", 400);
    public static final BusinessErrorCode ENUM_FIELD_VALUE_INVALID =
            new BusinessErrorCode(4031, Constants.GROUP_CODE_DATA_INVALID, "Enum value is invalid", 400);
    public static final BusinessErrorCode BOOLEAN_FORMAT_ERROR =
            new BusinessErrorCode(4033, Constants.GROUP_CODE_DATA_INVALID, "Invalid value of boolean type", 400);
    public static final BusinessErrorCode INVALID_FILTER_OPERATOR =
            new BusinessErrorCode(4034, Constants.GROUP_CODE_DATA_INVALID, "Invalid filter operator", 400);
    public static final BusinessErrorCode MISSING_DATA_BRANCH_LIST =
            new BusinessErrorCode(4035, Constants.GROUP_CODE_DATA_INVALID, "Missing data branch list system", 400);
    public static final BusinessErrorCode MISSING_DATA_BRANCH_CODE =
            new BusinessErrorCode(4036, Constants.GROUP_CODE_DATA_INVALID, "Missing data branch code", 400);
    public static final BusinessErrorCode MISS_PERMISSION_CODE =
            new BusinessErrorCode(401, Constants.GROUP_CODE_AUTHORIZATION, "Bạn Không có quyền", 401);

    public static final BusinessErrorCode ESB_CONNECT_TIME_OUT =
            new BusinessErrorCode(10000, Constants.GROUP_CODE_AUTHORIZATION, "Không thể kết nối tới hệ thống ESB", 401);
  }
