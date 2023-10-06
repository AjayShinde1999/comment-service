package com.comment.service.service.impl;

import com.comment.service.exception.UserNotFoundException;
import com.comment.service.model.Comment;
import com.comment.service.payload.CommentDto;
import com.comment.service.payload.User;
import com.comment.service.repository.CommentRepository;
import com.comment.service.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://localhost:8080/api/user?id=";

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        try {
            ResponseEntity<User> userResponse = restTemplate.getForEntity(
                    url + commentDto.getUserId(), User.class);

            User userDetails = userResponse.getBody();
            log.info("User Details {}", userDetails);

            Comment comment = new Comment();
            comment.setUserId(commentDto.getUserId());
            comment.setDescription(commentDto.getDescription());

            Comment savedComment = commentRepository.save(comment);

            CommentDto savedCommentDto = new CommentDto();
            savedCommentDto.setId(savedComment.getId());
            savedCommentDto.setUserId(savedComment.getUserId());
            savedCommentDto.setDescription(savedComment.getDescription());
            savedCommentDto.setUserDetails(userDetails);
            return savedCommentDto;

        } catch (HttpClientErrorException ex) {
            throw new UserNotFoundException("Error occurred while fetching user details Or User not found");
        }
    }
}
