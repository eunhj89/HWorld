package com.uno.hworld.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuth {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
