package com.uno.hworld.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuth {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;
}
