package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.response.PostResponse;
import com.example.travel_diary.service.PostService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @RolesAllowed("USER")
    public Long createPost(@AuthenticationPrincipal User user) {
        return postService.createPost(user);
    }

//    @GetMapping
//    public List<Post> getAll() {
//        return postService.getAll();
//    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

    @GetMapping
    public Page<Post> getAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size) {
        return postService.getAll(page, size);
    }

    @GetMapping("/recent-first")
    public Page<Post> getRecentPostsFirst(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size) {
        return postService.getRecentPostsFirst(page, size);
    }

    @GetMapping("/diaries")
    public Page<Post> getPostsByCountry(@RequestParam(value = "country") String country,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size) {
        return postService.getPostsByCountry(country, page, size);
    }

    @GetMapping("/like-first")
    public Page<Post> getTopLikeFirstOnThisWeek(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        return postService.getTopLikeFirstOnThisWeek(page, size);
    }

    @GetMapping("/between-dates")
    public Page<Post> getPostsBetween(@RequestParam(value = "from") LocalDate from,
                                      @RequestParam(value = "to") LocalDate to,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return postService.getPostsBetween(from, to, page, size);
    }
}
