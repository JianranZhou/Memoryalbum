package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileExtEnum {

    JPG(".jpg", "jpg files"),

    PNG(".png", "png files"),

    JPEG(".jpeg", "jpeg files"),

    WAV(".wav", "wav files"),

    MD(".md", "markdown files"),

    TXT(".txt", "txt files");

    public static FileExtEnum getFileExt(String extName) {
        for (FileExtEnum value : FileExtEnum.values()) {
            if (value.getExtName().equalsIgnoreCase(extName)) {
                return value;
            }
        }
        return null;
    }

    private final String extName;

    private final String desc;

}
