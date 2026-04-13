package com.sungjujjang.ter.error;

import com.sungjujjang.ter.auth.dto.ErrorRsp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalEx {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRsp> handleAllException(Exception e) {
        ErrorRsp response = new ErrorRsp(500, "SERVER-500", "서버 내부 오류가 발생했습니다.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}