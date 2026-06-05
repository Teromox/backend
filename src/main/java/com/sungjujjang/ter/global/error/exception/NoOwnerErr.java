package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NoOwnerErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NoOwnerErr();
    public NoOwnerErr() {
        super(ErrorCode.NOT_EXIST_ID, "You isn't owner.");
    }
}
