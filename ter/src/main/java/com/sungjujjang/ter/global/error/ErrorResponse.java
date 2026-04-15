package com.sungjujjang.ter.global.error;

import lombok.Builder;

@Builder
public record ErrorResponse(
        Integer errorCode,
        String errorMsg,
        String errorDescription
) {
    public static ErrorResponse errorCodeFrom(ErrorCode errorCode, String description) {
        return new ErrorResponse(
            errorCode.getErrcode(),
            errorCode.getErrormsg(),
            description
        );
    }

    public static ErrorResponse errorCodeOf(Integer errorCode, String errorMsg, String description) {
        return new ErrorResponse(
                errorCode,
                errorMsg,
                description
        );
    }
}
