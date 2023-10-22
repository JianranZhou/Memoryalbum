package com.aurora.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoryVO {

    @ApiModelProperty(name = "id", value = "id", dataType = "Integer")
    private Integer id;


    private String memoryTitle;


    private String memoryContent;

    private String memoryAbstract;

    private String memoryCover;

    private String categoryName;

    private List<String> tagNames;

    private String audioUrl;

    private String videoUrl;

}
