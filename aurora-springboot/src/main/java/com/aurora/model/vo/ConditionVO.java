package com.aurora.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Search Condition")
public class ConditionVO {

    @ApiModelProperty(name = "current", value = "page number", dataType = "Long")
    private Long current;

    @ApiModelProperty(name = "size", value = "row number", dataType = "Long")
    private Long size;

    @ApiModelProperty(name = "keywords", value = "search content", dataType = "String")
    private String keywords;



    @ApiModelProperty(name = "tagId", value = "tag id", dataType = "Integer")
    private Integer tagId;



    @ApiModelProperty(name = "startTime", value = "start time", dataType = "LocalDateTime")
    private LocalDateTime startTime;

    @ApiModelProperty(name = "endTime", value = "end time", dataType = "LocalDateTime")
    private LocalDateTime endTime;

    @ApiModelProperty(name = "isDelete", value = "delete confirm", dataType = "Integer")
    private Integer isDelete;

    private Integer userId;

}
