package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {

    AVATAR("aurora/avatar/", "avatar path"),

    ARTICLE("aurora/articles/", "articles path"),

    VOICE("aurora/voice/", "voice path"),

    PHOTO("aurora/photos/", "photos path"),

    CONFIG("aurora/config/", "config path"),

    TALK("aurora/talks/", "talk path"),

    MD("aurora/markdown/", "mdfiles path");

    private final String path;

    private final String desc;

}
