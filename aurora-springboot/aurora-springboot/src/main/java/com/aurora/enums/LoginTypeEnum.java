package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    EMAIL(1, "email login", "");

    private final Integer type;

    private final String desc;

    private final String strategy;

}
