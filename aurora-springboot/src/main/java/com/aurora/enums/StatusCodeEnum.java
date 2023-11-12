package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    SUCCESS(20000, "success"),

    NO_LOGIN(40001, "User not login"),

    AUTHORIZED(40300, "Permission denied"),

    SYSTEM_ERROR(50000, "System error"),

    FAIL(51000, "Operate fail"),

    VALID_ERROR(52000, "Format is incorrect"),

    USERNAME_EXIST(52001, "Username has existed"),

    USERNAME_NOT_EXIST(52002, "Username is not exists");

    private final Integer code;

    private final String desc;

}
