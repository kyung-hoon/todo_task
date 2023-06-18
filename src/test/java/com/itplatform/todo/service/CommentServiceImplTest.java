package com.itplatform.todo.service;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentServiceImpl(commentRepository);
    }

    @Test
    void update_ExistingComment_ShouldReturnUpdatedComment() {
        Comment existingComment = new Comment();
        existingComment.setId(1);
        existingComment.setText("Existing comment");

        Comment updatedComment = new Comment();
        updatedComment.setId(1);
        updatedComment.setText("Updated comment");

        when(commentRepository.existsById(existingComment.getId())).thenReturn(true);
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

        Comment result = commentService.update(existingComment);

        assertNotNull(result);
        assertEquals(updatedComment.getId(), result.getId());
        assertEquals(updatedComment.getText(), result.getText());
        verify(commentRepository, times(1)).existsById(existingComment.getId());
        verify(commentRepository, times(1)).save(existingComment);
    }

    @Test
    void update_NonExistingComment_ShouldThrowResponseStatusException() {
        Comment nonExistingComment = new Comment();
        nonExistingComment.setId(1);
        nonExistingComment.setText("Non-existing comment");

        when(commentRepository.existsById(nonExistingComment.getId())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> commentService.update(nonExistingComment));
        verify(commentRepository, times(1)).existsById(nonExistingComment.getId());
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void deleteById_ValidId_ShouldCallRepositoryDeleteById() {
        int validId = 1;

        commentService.deleteById(validId);

        verify(commentRepository, times(1)).deleteById(validId);
    }
}