package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NotExistIdErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NotExistIdErr();
    public NotExistIdErr() {
        super(ErrorCode.NOT_EXIST_ID, "Id is not exist.");
    }
}
