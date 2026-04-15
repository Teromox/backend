package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class DuplicateIdErr extends BusinessException {
    public static final BusinessException EXCEPTION = new DuplicateIdErr();
    public DuplicateIdErr() {
        super(ErrorCode.DUPLICATE_ID_ERR, "Id is duplicated");
    }
}