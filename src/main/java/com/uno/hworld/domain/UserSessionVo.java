package com.uno.hworld.domain;

import com.uno.hworld.common.UserAuth;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionVo implements Serializable {

    private String userId;

    private String userPw;

    private String userNm;

    private UserAuth userAuth;

    public UserSessionVo(User user) {
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.userNm = user.getUserNm();
        this.userAuth = user.getUserAuth();
    }
}
