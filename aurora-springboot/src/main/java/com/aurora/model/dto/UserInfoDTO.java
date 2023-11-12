package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private Integer id;

    private Integer userInfoId;

    private String email;


    private String username;

    private String nickname;

    private String avatar;

    private String intro;

    private String website;


    private String ipAddress;

    private String ipSource;

    private LocalDateTime lastLoginTime;

    private String token;

}
