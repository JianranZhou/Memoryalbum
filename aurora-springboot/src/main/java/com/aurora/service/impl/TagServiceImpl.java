package com.aurora.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.aurora.entity.Memory;
import com.aurora.mapper.MemoryMapper;
import com.aurora.model.dto.*;
import com.aurora.entity.MemoryTag;
import com.aurora.entity.Tag;
import com.aurora.exception.BizException;
import com.aurora.mapper.MemoryTagMapper;
import com.aurora.mapper.TagMapper;
import com.aurora.service.TagService;
import com.aurora.util.BeanCopyUtil;
import com.aurora.util.PageUtil;
import com.aurora.model.vo.ConditionVO;
import com.aurora.model.vo.TagVO;
import com.aurora.util.UserUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// The realization of tag methods //

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private MemoryTagMapper memoryTagMapper;

    @Autowired
    private MemoryMapper memoryMapper;

    @Override
    public List<TagDTO> listTags() {
        if (ObjectUtil.isEmpty(UserUtil.getUserDetailsDTO())){
            return new ArrayList<>();
        }
        Integer userId = UserUtil.getUserDetailsDTO().getUserInfoId();

        return tagMapper.listTags(userId);
    }

    @Override
    public List<TagDTO> listTopTenTags() {
        return tagMapper.listTopTenTags();
    }

    @SneakyThrows
    @Override
    public PageResultDTO<TagAdminDTO> listTagsAdmin(ConditionVO conditionVO) {
        if (ObjectUtil.isEmpty(UserUtil.getUserDetailsDTO())){
            return new PageResultDTO<>();
        }
        Integer userId = UserUtil.getUserDetailsDTO().getUserInfoId();
        conditionVO.setUserId(userId);
        Integer count = tagMapper.selectCount(new LambdaQueryWrapper<Tag>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), Tag::getTagName, conditionVO.getKeywords()));
        if (count == 0) {
            return new PageResultDTO<>();
        }
        List<TagAdminDTO> tags = tagMapper.listTagsAdmin(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        return new PageResultDTO<>(tags, count);
    }

    @SneakyThrows
    @Override
    public List<TagAdminDTO> listTagsAdminBySearch(ConditionVO conditionVO) {
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<Tag>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), Tag::getTagName, conditionVO.getKeywords())
                .orderByDesc(Tag::getId));
        return BeanCopyUtil.copyList(tags, TagAdminDTO.class);
    }

    @Override
    public void saveOrUpdateTag(TagVO tagVO) {
        Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId)
                .eq(Tag::getTagName, tagVO.getTagName()));
        if (Objects.nonNull(existTag) && !existTag.getId().equals(tagVO.getId())) {
            throw new BizException("标签名已存在");
        }
        Tag tag = BeanCopyUtil.copyObject(tagVO, Tag.class);
        tag.setUserId(UserUtil.getUserDetailsDTO().getUserInfoId());
        this.saveOrUpdate(tag);
    }

    @Override
    public void deleteTag(List<Integer> tagIds) {
        List<MemoryTag> memoryTagList = memoryTagMapper.selectList(new LambdaQueryWrapper<MemoryTag>()
                .in(MemoryTag::getTagId, tagIds));
        if (CollectionUtil.isNotEmpty(memoryTagList)) {
            List<Integer> ids = memoryTagList.stream().map(MemoryTag::getId).collect(Collectors.toList());
            memoryTagMapper.deleteBatchIds(ids);
        }

        tagMapper.deleteBatchIds(tagIds);
    }

}

