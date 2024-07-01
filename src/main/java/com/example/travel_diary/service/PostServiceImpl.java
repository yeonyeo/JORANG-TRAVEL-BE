package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.response.PostResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    // 여행 일지 작성 누르면 바로 post id를 생성 시킴, 업데이트도 작성일자만 갱신
    @Override
    @Transactional
    public Long createPost(@AuthenticationPrincipal User user) {
        Post post = postRepository.save(Post.builder().createdAt(LocalDateTime.now()).user(user).build());
        return post.getId();
    }

//    @Override
//    public List<Post> getAll() {
//        return postRepository.findAll();
//    }
    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> getAll(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getRecentPostsFirst(int page, int size) {
        return postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getPostsByCountry(String country, int page, int size) {
        return postRepository.findAllByDiaries_Country(country.toLowerCase(), PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getTopLikeFirstOnThisWeek(int page, int size) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return postRepository.findAllByCreatedAtBetweenOrderByLoveDesc(startOfWeek, endOfWeek, PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getPostsBetween(LocalDate from, LocalDate to, int page, int size) {
        return postRepository.findAllByDiaries_DateBetween(from, to, PageRequest.of(page, size));
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }


}
