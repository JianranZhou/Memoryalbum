package com.aurora.controller;


import com.aurora.entity.UserAuth;
import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.dto.UserInfoDTO;
import com.aurora.model.dto.UserOnlineDTO;
import com.aurora.service.UserInfoService;
import com.aurora.model.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

// The basic methods of user info //

@Api(tags = "user")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation("update user ")
    @PutMapping("/users/info")
    public ResultVO<?> updateUserInfo(@Valid @RequestBody UserInfoVO userInfoVO) {
        userInfoService.updateUserInfo(userInfoVO);
        return ResultVO.ok();
    }

    @ApiOperation("update user image")
    @ApiImplicitParam(name = "file", value = "用户头像", required = true, dataType = "MultipartFile")
    @PostMapping("/users/avatar")
    public ResultVO<String> updateUserAvatar(MultipartFile file) {
        return ResultVO.ok(userInfoService.updateUserAvatar(file));
    }


    @ApiOperation("getUserInfoById")
    @GetMapping("/users/info/{userInfoId}")
    public ResultVO<UserInfoDTO> getUserInfoById(@PathVariable("userInfoId") Integer userInfoId) {
        return ResultVO.ok(userInfoService.getUserInfoById(userInfoId));
    }

    @DeleteMapping("/admin/user/delete")
    public ResultVO<?> deleteUser(@RequestBody List<Integer> ids) {
        userInfoService.deleteByIds(ids);
        return ResultVO.ok();
    }
}

