package com.aurora.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_memory")
public class Memory {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;


    private String memoryCover;

    private String memoryTitle;

    private String memoryAbstract;

    private String memoryContent;



    private Integer isDelete;




    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    private String audioUrl;

    private String videoUrl;

}
