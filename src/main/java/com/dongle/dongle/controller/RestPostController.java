package com.dongle.dongle.controller;

import com.dongle.dongle.entitiy.PostsEntity;
import com.dongle.dongle.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class RestPostController {
    private final PostsService postsService;

    @GetMapping("/list")
    public ResponseEntity<Page<PostsEntity>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        Page<PostsEntity> products = postsService.getAllPosts(PageRequest.of(page, size));
        return ResponseEntity.ok(products);
    }
}
