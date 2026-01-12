package com.ipd.demo.repository;

import com.ipd.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIdeaIdOrderByCreatedAtDesc(Long ideaId);
}
