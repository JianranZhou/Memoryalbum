package com.aurora.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.aurora.entity.Memory;
import com.aurora.model.dto.*;
import com.aurora.entity.MemoryTag;
import com.aurora.entity.Tag;
import com.aurora.mapper.MemoryMapper;
import com.aurora.mapper.MemoryTagMapper;
import com.aurora.mapper.TagMapper;
import com.aurora.service.MemoryService;
import com.aurora.service.MemoryTagService;
import com.aurora.service.RedisService;
import com.aurora.service.TagService;
import com.aurora.strategy.context.UploadStrategyContext;
import com.aurora.util.BeanCopyUtil;
import com.aurora.util.PageUtil;
import com.aurora.util.UserUtil;
import com.aurora.model.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.aurora.constant.RedisConstant.*;

@Service
public class MemoryServiceImpl extends ServiceImpl<MemoryMapper, Memory> implements MemoryService {

    @Autowired
    private MemoryMapper memoryMapper;

    @Autowired
    private MemoryTagMapper memoryTagMapper;



    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private MemoryTagService memoryTagService;

    @Autowired
    private RedisService redisService;


    @Autowired
    private UploadStrategyContext uploadStrategyContext;


    @SneakyThrows
    @Override
    public TopAndFeaturedArticlesDTO listTopAndFeaturedArticles() {
        if (ObjectUtil.isEmpty(UserUtil.getUserDetailsDTO())){
            return new TopAndFeaturedArticlesDTO();
        }
        Integer userId = UserUtil.getUserDetailsDTO().getUserInfoId();
        List<MemoryCardDTO> memoryCardDTOS = memoryMapper.listTopAndFeaturedArticles(userId);
        if (memoryCardDTOS.size() == 0) {
            return new TopAndFeaturedArticlesDTO();
        } else if (memoryCardDTOS.size() > 3) {
            memoryCardDTOS = memoryCardDTOS.subList(0, 3);
        }
        TopAndFeaturedArticlesDTO topAndFeaturedArticlesDTO = new TopAndFeaturedArticlesDTO();
        topAndFeaturedArticlesDTO.setTopArticle(memoryCardDTOS.get(0));
        memoryCardDTOS.remove(0);
        topAndFeaturedArticlesDTO.setFeaturedArticles(memoryCardDTOS);
        return topAndFeaturedArticlesDTO;
    }

    @SneakyThrows
    @Override
    public PageResultDTO<MemoryCardDTO> listArticles(String keywords) {
        if (ObjectUtil.isEmpty(UserUtil.getUserDetailsDTO())){
            return new PageResultDTO<>();
        }
        Integer userId = UserUtil.getUserDetailsDTO().getUserInfoId();
        LambdaQueryWrapper<Memory> queryWrapper = new LambdaQueryWrapper<Memory>()
                .eq(Memory::getIsDelete, 0);
        CompletableFuture<Integer> asyncCount = CompletableFuture.supplyAsync(() -> memoryMapper.selectCount(queryWrapper));
        List<MemoryCardDTO> articles = memoryMapper.listArticles(PageUtil.getLimitCurrent(), PageUtil.getSize(),keywords,userId);
        return new PageResultDTO<>(articles, asyncCount.get());
    }



    @SneakyThrows
    @Override
    public MemoryDTO getArticleById(Integer memoryId) {
        Memory articleForCheck = memoryMapper.selectOne(new LambdaQueryWrapper<Memory>().eq(Memory::getId, memoryId));
        if (Objects.isNull(articleForCheck)) {
            return null;
        }



        updateArticleViewsCount(memoryId);
        CompletableFuture<MemoryDTO> asyncArticle = CompletableFuture.supplyAsync(() -> memoryMapper.getArticleById(memoryId));
        CompletableFuture<MemoryCardDTO> asyncPreArticle = CompletableFuture.supplyAsync(() -> {
            MemoryCardDTO preArticle = memoryMapper.getPreArticleById(memoryId,articleForCheck.getUserId());
            return preArticle;
        });
        CompletableFuture<MemoryCardDTO> asyncNextArticle = CompletableFuture.supplyAsync(() -> {
            MemoryCardDTO nextArticle = memoryMapper.getNextArticleById(memoryId,articleForCheck.getUserId());

            return nextArticle;
        });
        MemoryDTO article = asyncArticle.get();
        if (Objects.isNull(article)) {
            return null;
        }
        Double score = redisService.zScore(ARTICLE_VIEWS_COUNT, memoryId);
        if (Objects.nonNull(score)) {
            article.setViewCount(score.intValue());
        }
        article.setPreArticleCard(asyncPreArticle.get());
        article.setNextArticleCard(asyncNextArticle.get());
        return article;
    }


    @SneakyThrows
    @Override
    public PageResultDTO<MemoryCardDTO> listArticlesByTagId(Integer tagId) {
        Integer userId = UserUtil.getUserDetailsDTO().getUserInfoId();
        LambdaQueryWrapper<MemoryTag> queryWrapper = new LambdaQueryWrapper<MemoryTag>().eq(MemoryTag::getTagId, tagId);
        CompletableFuture<Integer> asyncCount = CompletableFuture.supplyAsync(() -> memoryTagMapper.selectCount(queryWrapper));
        List<MemoryCardDTO> articles = memoryMapper.listArticlesByTagId(PageUtil.getLimitCurrent(), PageUtil.getSize(), tagId,userId);
        return new PageResultDTO<>(articles, asyncCount.get());
    }

    @SneakyThrows
    @Override
    public PageResultDTO<ArchiveDTO> listArchives() {
        if (ObjectUtil.isEmpty(UserUtil.getUserDetailsDTO())){
            return new PageResultDTO<ArchiveDTO>();
        }
        Integer userId = UserUtil.getUserDetailsDTO().getUserInfoId();
        LambdaQueryWrapper<Memory> queryWrapper = new LambdaQueryWrapper<Memory>().eq(Memory::getIsDelete, 0)
                .eq(Memory::getUserId,userId);
        CompletableFuture<Integer> asyncCount = CompletableFuture.supplyAsync(() -> memoryMapper.selectCount(queryWrapper));
        List<MemoryCardDTO> articles = memoryMapper.listArchives(PageUtil.getLimitCurrent(), PageUtil.getSize(),userId);
        HashMap<String, List<MemoryCardDTO>> map = new HashMap<>();
        for (MemoryCardDTO article : articles) {
            LocalDateTime createTime = article.getCreateTime();
            int month = createTime.getMonth().getValue();
            int year = createTime.getYear();
            String key = year + "-" + month;
            if (Objects.isNull(map.get(key))) {
                List<MemoryCardDTO> memoryCardDTOS = new ArrayList<>();
                memoryCardDTOS.add(article);
                map.put(key, memoryCardDTOS);
            } else {
                map.get(key).add(article);
            }
        }
        List<ArchiveDTO> archiveDTOs = new ArrayList<>();
        map.forEach((key, value) -> archiveDTOs.add(ArchiveDTO.builder().Time(key).articles(value).build()));
        archiveDTOs.sort((o1, o2) -> {
            String[] o1s = o1.getTime().split("-");
            String[] o2s = o2.getTime().split("-");
            int o1Year = Integer.parseInt(o1s[0]);
            int o1Month = Integer.parseInt(o1s[1]);
            int o2Year = Integer.parseInt(o2s[0]);
            int o2Month = Integer.parseInt(o2s[1]);
            if (o1Year > o2Year) {
                return -1;
            } else if (o1Year < o2Year) {
                return 1;
            } else return Integer.compare(o2Month, o1Month);
        });
        return new PageResultDTO<>(archiveDTOs, asyncCount.get());
    }

    @SneakyThrows
    @Override
    public PageResultDTO<MemoryAdminDTO> listArticlesAdmin(ConditionVO conditionVO) {
        CompletableFuture<Integer> asyncCount = CompletableFuture.supplyAsync(() -> memoryMapper.countArticleAdmins(conditionVO));
        List<MemoryAdminDTO> articleAdminDTOs = memoryMapper.listArticlesAdmin(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        Map<Object, Double> viewsCountMap = redisService.zAllScore(ARTICLE_VIEWS_COUNT);
        articleAdminDTOs.forEach(item -> {
            Double viewsCount = viewsCountMap.get(item.getId());
            if (Objects.nonNull(viewsCount)) {
                item.setViewsCount(viewsCount.intValue());
            }
        });
        return new PageResultDTO<>(articleAdminDTOs, asyncCount.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateArticle(MemoryVO memoryVO) {
        Memory article = BeanCopyUtil.copyObject(memoryVO, Memory.class);

        article.setUserId(UserUtil.getUserDetailsDTO().getUserInfoId());
        this.saveOrUpdate(article);
        saveArticleTag(memoryVO, article.getId());

    }



    @Override
    public void updateArticleDelete(DeleteVO deleteVO) {
        List<Memory> articles = deleteVO.getIds().stream()
                .map(id -> Memory.builder()
                        .id(id)
                        .isDelete(deleteVO.getIsDelete())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(articles);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemoryAdminViewDTO getArticleByIdAdmin(Integer memoryId) {
        Memory article = memoryMapper.selectById(memoryId);
        List<String> tagNames = tagMapper.listTagNamesByMemoryId(memoryId);
        MemoryAdminViewDTO memoryAdminViewDTO = BeanCopyUtil.copyObject(article, MemoryAdminViewDTO.class);
        memoryAdminViewDTO.setTagNames(tagNames);
        return memoryAdminViewDTO;
    }



    @Override
    public List<MemorySearchDTO> listArticlesBySearch(ConditionVO condition) {
        return null;
    }

    public void updateArticleViewsCount(Integer memoryId) {
        redisService.zIncr(ARTICLE_VIEWS_COUNT, memoryId, 1D);
    }



    @Transactional(rollbackFor = Exception.class)
    public void saveArticleTag(MemoryVO memoryVO, Integer memoryId) {
        if (Objects.nonNull(memoryVO.getId())) {
            memoryTagMapper.delete(new LambdaQueryWrapper<MemoryTag>()
                    .eq(MemoryTag::getMemoryId, memoryVO.getId()));
        }
        List<String> tagNames = memoryVO.getTagNames();
        if (CollectionUtils.isNotEmpty(tagNames)) {
            List<Tag> existTags = tagService.list(new LambdaQueryWrapper<Tag>()
                    .in(Tag::getTagName, tagNames));
            List<String> existTagNames = existTags.stream()
                    .map(Tag::getTagName)
                    .collect(Collectors.toList());
            List<Integer> existTagIds = existTags.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());
            tagNames.removeAll(existTagNames);
            if (CollectionUtils.isNotEmpty(tagNames)) {
                List<Tag> tags = tagNames.stream().map(item -> Tag.builder()
                                .tagName(item)
                                .build())
                        .collect(Collectors.toList());
                tagService.saveBatch(tags);
                List<Integer> tagIds = tags.stream()
                        .map(Tag::getId)
                        .collect(Collectors.toList());
                existTagIds.addAll(tagIds);
            }
            List<MemoryTag> memoryTags = existTagIds.stream().map(item -> MemoryTag.builder()
                            .memoryId(memoryId)
                            .tagId(item)
                            .build())
                    .collect(Collectors.toList());
            memoryTagService.saveBatch(memoryTags);
        }
    }

}
