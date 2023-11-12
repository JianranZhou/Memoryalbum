package com.aurora.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;


/**
 * 文章编辑页码DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "文章")
public class MemoryAdminViewDTO {

    private Integer id;

    private String memoryTitle;

    private String memoryAbstract;

    private String memoryContent;

    private String memoryCover;


    private List<String> tagNames;


    private String originalUrl;

    private String password;

}
