package com.ipd.demo.service;

import com.ipd.demo.dto.request.CommentCreateRequestDTO;
import com.ipd.demo.dto.response.CommentResponseDTO;
import com.ipd.demo.entity.Comment;
import com.ipd.demo.entity.Idea;
import com.ipd.demo.entity.Users;
import com.ipd.demo.exception.ResourceNotFoundException;
import com.ipd.demo.mapper.CommentMapper;
import com.ipd.demo.repository.CommentRepository;
import com.ipd.demo.repository.IdeaRepository;
import com.ipd.demo.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final IdeaRepository ideaRepository;
    private final UsersRepository usersRepository;

    public CommentServiceImpl(CommentRepository commentRepository, IdeaRepository ideaRepository, UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.ideaRepository = ideaRepository;
        this.usersRepository = usersRepository;
    }

    // to add comment
    @Override
    public void addComment(CommentCreateRequestDTO request) {
        Idea idea = ideaRepository.findById(request.getIdeaId())
                .orElseThrow(() -> new ResourceNotFoundException("Idea not found"));

        Users users = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment = new Comment();
        comment.setIdea(idea);
        comment.setUser(users); //this is for createdBy field
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);

    }

    @Override
    public List<CommentResponseDTO> getCommentsByIdea(Long ideaId) {
        return commentRepository.findByIdeaIdOrderByCreatedAtDesc(ideaId)
                .stream()
                .map(CommentMapper::toResponseDto)
                .toList();
    }

    @Override
    public void updateComment(Long commentId, Long userId, String content) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("Not authorized to edit this comment");
        }

        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment not found"));
        if (comment.getUser() == null ||
                comment.getUser().getId() == null ||
                !comment.getUser().getId().equals(userId)){
            throw new RuntimeException("Not authorized to edit this comment");
        }
        commentRepository.delete(comment);

    }
}
