package com.aurora.service;

import com.aurora.model.dto.PageResultDTO;
import com.aurora.model.dto.UserInfoDTO;
import com.aurora.model.dto.UserOnlineDTO;
import com.aurora.entity.UserInfo;
import com.aurora.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserInfoService extends IService<UserInfo> {

    void updateUserInfo(UserInfoVO userInfoVO);

    String updateUserAvatar(MultipartFile file);


    UserInfoDTO getUserInfoById(Integer id);

    void deleteByIds(List<Integer> ids);
}
