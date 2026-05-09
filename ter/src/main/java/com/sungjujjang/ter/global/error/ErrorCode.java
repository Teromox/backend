package com.sungjujjang.ter.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATE_ID_ERR(401, "DUPLICATE_ID"),
    NOT_VALID_DTO_ERR(402, "NOT_VALID_DTO"),

    NOT_EXIST_ID(403, "NOT_EXIST_ID"),
    NOT_MATCH_PASSWORD(403, "NOT_MATCH_PASSWORD"),

    NOT_VALID_JWT_TOKEN(405, "NOT_VALID_JWT_TOKEN"),

    NOT_EXIST_CODE(406, "NOT_EXIST_CODE"),

    NO_CREDIT(408, "NO_CREDIT"),

    NO_EXIST_VM(409, "NO_EXIST_VM"),

    NOT_OWNER(410, "NOT_OWNER"),

    MAX_PORT(411, "MAX_PORT"),

    INTERNAL_SERVER_ERR(500, "INTERNAL_SERVER");

    private Integer errcode;
    private String errormsg;
}