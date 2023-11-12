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
public class MemoryDTO {
    private Integer id;

    private UserInfo author;


    private String memoryCover;

    private String memoryTitle;

    private String memoryContent;


    private Integer isDelete;


    private List<Tag> tags;

    private Integer viewCount;



    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private MemoryCardDTO preArticleCard;

    private MemoryCardDTO nextArticleCard;

    private String audioUrl;

    private String videoUrl;
}
