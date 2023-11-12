package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    ADMIN(1, "admin", "admin"),

    USER(2, "user", "user");

    private final Integer roleId;

    private final String name;

    private final String label;

}
