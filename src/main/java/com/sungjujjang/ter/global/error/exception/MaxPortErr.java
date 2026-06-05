package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class MaxPortErr extends BusinessException {
    public static final BusinessException EXCEPTION = new MaxPortErr();
    public MaxPortErr() {
        super(ErrorCode.MAX_PORT, "Max Port Limit");
    }
}
