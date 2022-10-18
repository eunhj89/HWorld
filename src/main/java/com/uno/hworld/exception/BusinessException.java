package com.uno.hworld.exception;

import com.uno.hworld.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BusinessException extends Exception{

    ErrorCode errorCode;

}
