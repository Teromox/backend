package com.sungjujjang.ter.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATE_ID_ERR(401, "DUPLICATE_ID"),
    NOT_VALID_DTO_ERR(402, "NOT_VALID_DTO"),

    INTERNAL_SERVER_ERR(500, "INTERNAL_SERVER");

    private Integer errcode;
    private String errormsg;
}