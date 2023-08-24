package com.ttc.spring.exception;

import lombok.Value;

@Value
public class BusinessErrorCode {
  int code;
  String group;
  String message;
  int httpStatus;
}
