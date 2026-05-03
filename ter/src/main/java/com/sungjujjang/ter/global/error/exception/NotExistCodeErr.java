package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NotExistCodeErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NotExistCodeErr();
    public NotExistCodeErr() {
        super(ErrorCode.NOT_EXIST_CODE, "Code is not exist.");
    }
}
