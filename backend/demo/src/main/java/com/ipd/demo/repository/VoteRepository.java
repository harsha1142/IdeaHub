package com.ipd.demo.repository;

import com.ipd.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    void deleteByIdea_Id(Long ideaId);
    boolean existsByIdeaIdAndUserId(Long ideaId, Long userId);

    long countByIdea_Id(Long ideaId);
}
