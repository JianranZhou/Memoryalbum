package com.aurora.strategy.impl;

import com.aurora.entity.Memory;
import com.aurora.mapper.MemoryMapper;
import com.aurora.model.dto.MemorySearchDTO;
import com.aurora.strategy.SearchStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.aurora.constant.CommonConstant.*;

// Methods interact with database //

@Service("mySqlSearchStrategyImpl")
public class MySqlSearchStrategyImpl implements SearchStrategy {

    @Autowired
    private MemoryMapper memoryMapper;

    @Override
    public List<MemorySearchDTO> searchArticle(String keywords) {
        if (StringUtils.isBlank(keywords)) {
            return new ArrayList<>();
        }
        List<Memory> articles = memoryMapper.selectList(new LambdaQueryWrapper<Memory>()
                .eq(Memory::getIsDelete, FALSE)
                .and(i -> i.like(Memory::getMemoryTitle, keywords)
                        .or()
                        .like(Memory::getMemoryContent, keywords)));
        return articles.stream().map(item -> {
                    boolean isLowerCase = true;
                    String memoryContent = item.getMemoryContent();
                    int contentIndex = item.getMemoryContent().indexOf(keywords.toLowerCase());
                    if (contentIndex == -1) {
                        contentIndex = item.getMemoryContent().indexOf(keywords.toUpperCase());
                        if (contentIndex != -1) {
                            isLowerCase = false;
                        }
                    }
                    if (contentIndex != -1) {
                        int preIndex = contentIndex > 15 ? contentIndex - 15 : 0;
                        String preText = item.getMemoryContent().substring(preIndex, contentIndex);
                        int last = contentIndex + keywords.length();
                        int postLength = item.getMemoryContent().length() - last;
                        int postIndex = postLength > 35 ? last + 35 : last + postLength;
                        String postText = item.getMemoryContent().substring(contentIndex, postIndex);
                        if (isLowerCase) {
                            memoryContent = (preText + postText).replaceAll(keywords.toLowerCase(),
                                    PRE_TAG + keywords.toLowerCase() + POST_TAG);
                        } else {
                            memoryContent = (preText + postText).replaceAll(keywords.toUpperCase(),
                                    PRE_TAG + keywords.toUpperCase() + POST_TAG);
                        }
                    } else {
                        return null;
                    }
                    isLowerCase = true;
                    int titleIndex = item.getMemoryTitle().indexOf(keywords.toLowerCase());
                    if (titleIndex == -1) {
                        titleIndex = item.getMemoryTitle().indexOf(keywords.toUpperCase());
                        if (titleIndex != -1) {
                            isLowerCase = false;
                        }
                    }
                    String memoryTitle;
                    if (isLowerCase) {
                        memoryTitle = item.getMemoryTitle().replaceAll(keywords.toLowerCase(),
                                PRE_TAG + keywords.toLowerCase() + POST_TAG);
                    } else {
                        memoryTitle = item.getMemoryTitle().replaceAll(keywords.toUpperCase(),
                                PRE_TAG + keywords.toUpperCase() + POST_TAG);
                    }
                    return MemorySearchDTO.builder()
                            .id(item.getId())
                            .memoryTitle(memoryTitle)
                            .memoryContent(memoryContent)
                            .build();
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
