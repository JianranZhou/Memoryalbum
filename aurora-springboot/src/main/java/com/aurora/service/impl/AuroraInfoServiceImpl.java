package com.aurora.service.impl;

import com.alibaba.fastjson.JSON;
import com.aurora.model.dto.*;
import com.aurora.entity.*;
import com.aurora.mapper.*;
import com.aurora.service.AuroraInfoService;
import com.aurora.service.RedisService;
import com.aurora.util.BeanCopyUtil;
import com.aurora.util.IpUtil;
import com.aurora.model.vo.AboutVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.aurora.constant.CommonConstant.*;
import static com.aurora.constant.RedisConstant.*;

// The realization of Info methods //

@Service
public class AuroraInfoServiceImpl implements AuroraInfoService {


    @Autowired
    private MemoryMapper memoryMapper;


    @Autowired
    private TagMapper tagMapper;


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AboutMapper aboutMapper;

    @Autowired
    private RedisService redisService;


    @Autowired
    private HttpServletRequest request;

    @Override
    public void report() {
        String ipAddress = IpUtil.getIpAddress(request);
        UserAgent userAgent = IpUtil.getUserAgent(request);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        String uuid = ipAddress + browser.getName() + operatingSystem.getName();
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        if (!redisService.sIsMember(UNIQUE_VISITOR, md5)) {
            String ipSource = IpUtil.getIpSource(ipAddress);
            if (StringUtils.isNotBlank(ipSource)) {
                String ipProvince = IpUtil.getIpProvince(ipSource);
                redisService.hIncr(VISITOR_AREA, ipProvince, 1L);
            } else {
                redisService.hIncr(VISITOR_AREA, UNKNOWN, 1L);
            }
            redisService.incr(BLOG_VIEWS_COUNT, 1);
            redisService.sAdd(UNIQUE_VISITOR, md5);
        }
    }

    @SneakyThrows
    @Override
    public AuroraHomeInfoDTO getAuroraHomeInfo() {
        CompletableFuture<Integer> asyncArticleCount = CompletableFuture.supplyAsync(() -> memoryMapper
                .selectCount(new LambdaQueryWrapper<Memory>().eq(Memory::getIsDelete, FALSE)));

        CompletableFuture<Integer> asyncTagCount = CompletableFuture.supplyAsync(() -> tagMapper.selectCount(null));
        CompletableFuture<Integer> asyncViewCount = CompletableFuture.supplyAsync(() -> {
            Object count = redisService.get(BLOG_VIEWS_COUNT);
            return Integer.parseInt(Optional.ofNullable(count).orElse(0).toString());
        });
        return AuroraHomeInfoDTO.builder()
                .articleCount(asyncArticleCount.get())
                .tagCount(asyncTagCount.get())
                .viewCount(asyncViewCount.get()).build();
    }

    @Override
    public AuroraAdminInfoDTO getAuroraAdminInfo() {
        Object count = redisService.get(BLOG_VIEWS_COUNT);
        Integer viewsCount = Integer.parseInt(Optional.ofNullable(count).orElse(0).toString());
        //Integer messageCount = commentMapper.selectCount(new LambdaQueryWrapper<Comment>().eq(Comment::getType, 2));
        Integer userCount = userInfoMapper.selectCount(null);
        Integer articleCount = memoryMapper.selectCount(new LambdaQueryWrapper<Memory>()
                .eq(Memory::getIsDelete, FALSE));
        List<MemoryStatisticsDTO> memoryStatisticsDTOS = memoryMapper.listArticleStatistics();

        List<TagDTO> tagDTOs = BeanCopyUtil.copyList(tagMapper.selectList(null), TagDTO.class);
        Map<Object, Double> articleMap = redisService.zReverseRangeWithScore(ARTICLE_VIEWS_COUNT, 0, 4);
        AuroraAdminInfoDTO auroraAdminInfoDTO = AuroraAdminInfoDTO.builder()
                .memoryStatisticsDTOS(memoryStatisticsDTOS)
                .tagDTOs(tagDTOs)
                .viewsCount(viewsCount)
                //.messageCount(messageCount)
                .userCount(userCount)
                .articleCount(articleCount)
                .build();
        if (CollectionUtils.isNotEmpty(articleMap)) {
            List<MemoryRankDTO> memoryRankDTOList = listArticleRank(articleMap);
            auroraAdminInfoDTO.setMemoryRankDTOS(memoryRankDTOList);
        }
        return auroraAdminInfoDTO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAbout(AboutVO aboutVO) {
        About about = About.builder()
                .id(DEFAULT_ABOUT_ID)
                .content(JSON.toJSONString(aboutVO))
                .build();
        aboutMapper.updateById(about);
        redisService.del(ABOUT);
    }

    @Override
    public AboutDTO getAbout() {
        AboutDTO aboutDTO;
        Object about = redisService.get(ABOUT);
        if (Objects.nonNull(about)) {
            aboutDTO = JSON.parseObject(about.toString(), AboutDTO.class);
        } else {
            String content = aboutMapper.selectById(DEFAULT_ABOUT_ID).getContent();
            aboutDTO = JSON.parseObject(content, AboutDTO.class);
            redisService.set(ABOUT, content);
        }
        return aboutDTO;
    }

    private List<MemoryRankDTO> listArticleRank(Map<Object, Double> articleMap) {
        List<Integer> memoryIds = new ArrayList<>(articleMap.size());
        articleMap.forEach((key, value) -> memoryIds.add((Integer) key));
        return memoryMapper.selectList(new LambdaQueryWrapper<Memory>()
                        .select(Memory::getId, Memory::getMemoryTitle)
                        .in(Memory::getId, memoryIds))
                .stream().map(article -> MemoryRankDTO.builder()
                        .memoryTitle(article.getMemoryTitle())
                        .viewsCount(articleMap.get(article.getId()).intValue())
                        .build())
                .sorted(Comparator.comparingInt(MemoryRankDTO::getViewsCount).reversed())
                .collect(Collectors.toList());
    }

}
