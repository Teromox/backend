package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NoCreditErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NoCreditErr();
    public NoCreditErr() {
        super(ErrorCode.NO_CREDIT, "No Credit");
    }
}
