package com.aurora.controller;

import com.aurora.model.dto.*;
import com.aurora.enums.FilePathEnum;
import com.aurora.service.MemoryService;
import com.aurora.strategy.context.UploadStrategyContext;
import com.aurora.model.vo.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.List;


@Api(tags = "memory controller")
@RestController
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;



    @GetMapping("/memory/topAndFeatured")
    public ResultVO<TopAndFeaturedArticlesDTO> listTopAndFeaturedArticles() {
        return ResultVO.ok(memoryService.listTopAndFeaturedArticles());
    }


    @GetMapping("/memory/all")
    public ResultVO<PageResultDTO<MemoryCardDTO>> listArticles(@RequestParam String keywords) {
        return ResultVO.ok(memoryService.listArticles(keywords));
    }




    @GetMapping("/memory/{id}")
    public ResultVO<MemoryDTO> getArticleById(@PathVariable("id") Integer id) {
        return ResultVO.ok(memoryService.getArticleById(id));
    }



    @GetMapping("/memory/tagId")
    public ResultVO<PageResultDTO<MemoryCardDTO>> listArticlesByTagId(@RequestParam Integer tagId) {
        return ResultVO.ok(memoryService.listArticlesByTagId(tagId));
    }


    @GetMapping("/archives/all")
    public ResultVO<PageResultDTO<ArchiveDTO>> listArchives() {
        return ResultVO.ok(memoryService.listArchives());
    }


    @GetMapping("/admin/memory")
    public ResultVO<PageResultDTO<MemoryAdminDTO>> listArticlesAdmin(ConditionVO conditionVO) {
        return ResultVO.ok(memoryService.listArticlesAdmin(conditionVO));
    }


    @PostMapping("/admin/memory")
    public ResultVO<?> saveOrUpdateArticle(@Valid @RequestBody MemoryVO memoryVO) {
        memoryService.saveOrUpdateArticle(memoryVO);
        return ResultVO.ok();
    }



    @PutMapping("/admin/memory")
    public ResultVO<?> updateArticleDelete(@Valid @RequestBody DeleteVO deleteVO) {
        memoryService.updateArticleDelete(deleteVO);
        return ResultVO.ok();
    }





    @PostMapping("/admin/memory/images")
    public ResultVO<String> saveArticleImages(MultipartFile file) {
        return ResultVO.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.ARTICLE.getPath()));
    }


    @GetMapping("/admin/memory/{memoryId}")
    public ResultVO<MemoryAdminViewDTO> getArticleBackById(@PathVariable("memoryId") Integer memoryId) {
        return ResultVO.ok(memoryService.getArticleByIdAdmin(memoryId));
    }




    @GetMapping("/memory/search")
    public ResultVO<List<MemorySearchDTO>> listArticlesBySearch(ConditionVO condition) {
        return ResultVO.ok(memoryService.listArticlesBySearch(condition));
    }

}
