package com.example.travel_diary.controller;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody String title) {
        postService.update(id, title);
    }

    @GetMapping()
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable(name = "id") Long id) {
        return postService.getById(id);
    }

    @GetMapping("/recent")
    public List<Post> getRecentPostsFirst() {
        return postService.getRecentPostsFirst();
    }

    @GetMapping("/top5/diaries")
    public List<Post> getRecent5PostsByCountry(@RequestParam(name = "country") String country) {
        return postService.getRecent5PostsByCountry(country);
    }

    @GetMapping("recent/diaries")
    public List<Post> getRecentPostsFirstByCountry(@RequestParam(name = "country") String country) {
        return postService.getRecentPostsFirstByCountry(country);
    }

    @GetMapping("/top5/like")
    public List<Post> getTop5LikeOnThisWeek() {
        return postService.getTop5LikeOnThisWeek();
    }

    @GetMapping("/dates")
    public List<Post> getRecentPostsFirstBetweenTheseDates(@RequestParam(value = "from") LocalDate from,
                                      @RequestParam(value = "to") LocalDate to) {
        return postService.getRecentPostsFirstBetweenTheseDates(from, to);
    }

    @GetMapping("/user")
    public List<Post> getByUser(@AuthenticationPrincipal User user) {
        return postService.getByUser(user);
    }

}
