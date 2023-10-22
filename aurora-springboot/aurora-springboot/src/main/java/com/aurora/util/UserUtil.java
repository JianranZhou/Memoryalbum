package com.aurora.util;

import cn.hutool.core.util.ObjectUtil;
import com.aurora.model.dto.UserDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserUtil {

    public static UserDetailsDTO getUserDetailsDTO() {
        if (ObjectUtil.isEmpty(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            || "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
            return null;
        }
        return (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
