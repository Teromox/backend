package com.sungjujjang.ter.error;

import lombok.AllArgsConstructor;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

@AllArgsConstructor
public class BuErr extends RuntimeException{
    private final Integer errorCode;
    private final String message;
}
