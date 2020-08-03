package com.brunner.social.repository;

import com.brunner.social.dto.PostDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PostDto> findByUserId(Long userId) {
        List<PostDto> resultList;
        Query query = entityManager.createNativeQuery("select p.id, u.id as userId from posts p,user u where p.user_id=u.id and p.user_id=?", "findAllDataMapping");
        query.setParameter(1, userId);
        resultList = query.getResultList();
        return resultList;
    }
}
