package com.aurora.service;

import com.aurora.entity.Memory;
import com.aurora.model.dto.*;
import com.aurora.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface MemoryService extends IService<Memory> {

    TopAndFeaturedArticlesDTO listTopAndFeaturedArticles();

    PageResultDTO<MemoryCardDTO> listArticles(String keywords);


    MemoryDTO getArticleById(Integer memoryId);


    PageResultDTO<MemoryCardDTO> listArticlesByTagId(Integer tagId);

    PageResultDTO<ArchiveDTO> listArchives();

    PageResultDTO<MemoryAdminDTO> listArticlesAdmin(ConditionVO conditionVO);

    void saveOrUpdateArticle(MemoryVO memoryVO);


    void updateArticleDelete(DeleteVO deleteVO);

    MemoryAdminViewDTO getArticleByIdAdmin(Integer memoryId);


    List<MemorySearchDTO> listArticlesBySearch(ConditionVO condition);

}
