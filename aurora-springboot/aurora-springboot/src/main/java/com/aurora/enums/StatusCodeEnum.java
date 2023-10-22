package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    SUCCESS(20000, "success"),

    NO_LOGIN(40001, "user not login"),

    AUTHORIZED(40300, "no operate perssion"),

    SYSTEM_ERROR(50000, "system error"),

    FAIL(51000, "operate fail"),

    VALID_ERROR(52000, "fomrta is incorrect"),

    USERNAME_EXIST(52001, "username has existed"),

    USERNAME_NOT_EXIST(52002, "username is not exist");

    private final Integer code;

    private final String desc;

}
