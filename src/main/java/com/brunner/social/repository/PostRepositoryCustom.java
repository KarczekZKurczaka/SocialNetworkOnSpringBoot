package com.brunner.social.repository;

import com.brunner.social.dto.PostDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostDto> findByUserId(Long UserId);
}
