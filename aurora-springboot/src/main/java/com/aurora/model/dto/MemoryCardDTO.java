package com.aurora.model.dto;

import com.aurora.entity.Tag;
import com.aurora.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoryCardDTO {

    private Integer id;

    private String memoryCover;

    private String memoryTitle;

    private String memoryContent;



    private UserInfo author;


    private List<Tag> tags;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
