package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuroraHomeInfoDTO {

    private Integer articleCount;

    private Integer talkCount;


    private Integer tagCount;


    private Integer viewCount;

}
