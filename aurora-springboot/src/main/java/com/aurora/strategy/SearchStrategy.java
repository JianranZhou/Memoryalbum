package com.aurora.strategy;

import com.aurora.model.dto.MemorySearchDTO;

import java.util.List;

public interface SearchStrategy {

    List<MemorySearchDTO> searchArticle(String keywords);

}
