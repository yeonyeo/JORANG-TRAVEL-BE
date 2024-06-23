package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.PostRepository;
import com.example.travel_diary.global.domain.type.Scope;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Post post = postRepository.save(Post.builder().user(user).build());
        return post.getId();
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }
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
    @Transactional
    public void update(Long id, String title) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        post.setTitle(title);
        post.setCreatedAt(LocalDateTime.now());
    }

    @Override
    public Page<Post> getAll(int page, int size) {
        return postRepository.findAllByDiaries_Scope(Scope.PUBLIC, PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getRecentPostsFirst(int page, int size) {
        List<Post> all1 = postRepository.findAll();
        System.out.println(all1.size());
        System.out.println("page = " + page + ", size = " + size);
        Page<Post> all = postRepository.findByDiaries_ScopeOrderByCreatedAtDesc(Scope.PUBLIC, PageRequest.of(page, size));
        System.out.println(all.getTotalElements());
        System.out.println(all.getNumber());
        return all;
    }


    @Override
    public Page<Post> getRecentPostsFirstByCountry(String country, int page, int size) {
        return postRepository.findAllByDiaries_ScopeAndDiaries_CountryOrderByCreatedAtDesc(Scope.PUBLIC, country, PageRequest.of(page, size));
    }

    // ok
//    @Override
//    public Page<Post> getPostsByCountry(String country, int page, int size) {
//        return postRepository.findAllByDiaries_ScopeAndDiaries_Country(Scope.PUBLIC, country.toLowerCase(), PageRequest.of(page, size));
//    }


    @Override
    public Page<Post> getTopLikeFirstOnThisWeek(int page, int size) {
        LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return postRepository.findAllByDiaries_ScopeAndCreatedAtBetweenOrderByLoveDesc(Scope.PUBLIC, startOfWeek, endOfWeek, PageRequest.of(page, size));
    }

//
    @Override
    public Page<Post> getPostsBetween(LocalDate from, LocalDate to, int page, int size) {
        return postRepository.findAllByDiaries_ScopeAndDiaries_DateBetween(Scope.PUBLIC, from, to, PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getByUser(User user, int page, int size) {
        return postRepository.findByUser(user, PageRequest.of(page, size));
    }


}
