package com.uno.hworld.configuration;

import com.uno.hworld.common.ErrorResponseEntity;
import com.uno.hworld.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseEntity> handleBusinessException(BusinessException ex) {
        return ErrorResponseEntity.toResponseEntity(ex.getErrorCode());
    }
}
