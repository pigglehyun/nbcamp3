package org.example.expert.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.aspect.AdminLogging;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @AdminLogging
    @DeleteMapping("/admin/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long commentId) {
        commentAdminService.deleteComment(commentId);
        return ResponseEntity.ok("comment 삭제 완료");
    }
}
