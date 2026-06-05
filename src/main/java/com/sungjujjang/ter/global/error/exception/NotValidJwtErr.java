package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NotValidJwtErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NotValidJwtErr();
    public NotValidJwtErr() {
        super(ErrorCode.NOT_VALID_JWT_TOKEN, "JWT token is not Valid.");
    }
}
