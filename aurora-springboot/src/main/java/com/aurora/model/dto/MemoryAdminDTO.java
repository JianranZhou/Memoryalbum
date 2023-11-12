package com.aurora.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 后台文章
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoryAdminDTO {

    private Integer id;

    private String memoryCover;

    private String memoryTitle;

    private LocalDateTime createTime;

    private Integer viewsCount;


    private List<TagDTO> tagDTOs;


    private Integer isDelete;

    private String audioUrl;

    private String videoUrl;

    private String userName;

}
