package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuroraAdminInfoDTO {

    private Integer viewsCount;

    private Integer messageCount;

    private Integer userCount;

    private Integer articleCount;


    private List<TagDTO> tagDTOs;

    private List<MemoryStatisticsDTO> memoryStatisticsDTOS;

    private List<UniqueViewDTO> uniqueViewDTOs;

    private List<MemoryRankDTO> memoryRankDTOS;

}
