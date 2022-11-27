package com.uno.hworld.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String userPw;
    private String userNm;
    private String token;

    @Builder
    public UserDto(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

}
