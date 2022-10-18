package com.uno.hworld.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_LOGIN(HttpStatus.BAD_REQUEST, "로그인 정보가 일치하지 않습니다."),
    INVALID_AUTH(HttpStatus.UNAUTHORIZED, "권한이 불충분합니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 정보를 찾을 수 없습니다."),
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "이미 존재하는 데이터입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
