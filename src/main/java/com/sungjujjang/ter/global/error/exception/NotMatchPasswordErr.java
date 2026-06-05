package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NotMatchPasswordErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NotMatchPasswordErr();
    public NotMatchPasswordErr() {
        super(ErrorCode.NOT_MATCH_PASSWORD, "Password is not matched.");
    }
}