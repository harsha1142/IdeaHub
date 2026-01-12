package com.ipd.demo.controller;

import com.ipd.demo.dto.request.CommentCreateRequestDTO;
import com.ipd.demo.dto.response.CommentResponseDTO;
import com.ipd.demo.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    // Constructor injection
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Add a comment to an idea
    @PostMapping
    public ResponseEntity<Void> addComment(
            @RequestBody CommentCreateRequestDTO request) {
        System.out.println(">>> POST /api/comments HIT");
        commentService.addComment(request);
        return ResponseEntity.ok().build();
    }

    // Get all comments for a specific idea
    @GetMapping("/idea/{ideaId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByIdea(
            @PathVariable Long ideaId) {

        return ResponseEntity.ok(commentService.getCommentsByIdea(ideaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestBody CommentCreateRequestDTO request) {

        commentService.updateComment(id, userId, request.getContent());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userId) {

        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
