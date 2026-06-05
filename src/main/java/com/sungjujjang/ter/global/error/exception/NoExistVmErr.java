package com.sungjujjang.ter.global.error.exception;

import com.sungjujjang.ter.global.error.BusinessException;
import com.sungjujjang.ter.global.error.ErrorCode;

public class NoExistVmErr extends BusinessException {
    public static final BusinessException EXCEPTION = new NoExistVmErr();
    public NoExistVmErr() {
        super(ErrorCode.NO_EXIST_VM, "Vm is not exist.");
    }
}
