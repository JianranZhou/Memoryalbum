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

public class AuroraBackInfoDTO {

    private Integer viewsCount;

    private Integer messageCount;

    private Integer userCount;

    private Integer articleCount;


    private List<TagDTO> tagDTOList;

    private List<MemoryStatisticsDTO> articleStatisticsList;

    private List<UniqueViewDTO> uniqueViewDTOList;

    private List<MemoryRankDTO> memoryRankDTOList;

}
