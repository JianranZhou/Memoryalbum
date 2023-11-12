package com.aurora.mapper;

import com.aurora.entity.Memory;
import com.aurora.model.dto.MemoryAdminDTO;
import com.aurora.model.dto.MemoryCardDTO;
import com.aurora.model.dto.MemoryDTO;
import com.aurora.model.dto.MemoryStatisticsDTO;
import com.aurora.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemoryMapper extends BaseMapper<Memory> {

    List<MemoryCardDTO> listTopAndFeaturedArticles(@Param("userId") Integer userId);

    List<MemoryCardDTO> listArticles(@Param("current") Long current, @Param("size") Long size,
                                     @Param("keywords") String keywords, @Param("userId") Integer userId);


    MemoryDTO getArticleById(@Param("memoryId") Integer memoryId);

    MemoryCardDTO getPreArticleById(@Param("memoryId") Integer memoryId,@Param("userId") Integer userId);

    MemoryCardDTO getNextArticleById(@Param("memoryId") Integer memoryId,@Param("userId") Integer userId);

    MemoryCardDTO getFirstArticle();

    MemoryCardDTO getLastArticle();

    List<MemoryCardDTO> listArticlesByTagId(@Param("current") Long current, @Param("size") Long size,
                                            @Param("tagId") Integer tagId, @Param("userId") Integer userId);

    List<MemoryCardDTO> listArchives(@Param("current") Long current, @Param("size") Long size,
                                     @Param("userId") Integer userId);

    Integer countArticleAdmins(@Param("conditionVO") ConditionVO conditionVO);

    List<MemoryAdminDTO> listArticlesAdmin(@Param("current") Long current, @Param("size") Long size,
                                           @Param("conditionVO") ConditionVO conditionVO);

    List<MemoryStatisticsDTO> listArticleStatistics();

}

