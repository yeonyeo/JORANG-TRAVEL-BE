package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Like;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.LikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    @Override
    @Transactional
    public int likeComment(User user, Long postId) {
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(user.getId(), postId);
        Post post = postService.getById(postId);
        if (like.isEmpty()) {
            likeRepository.save(Like.builder().user(user).post(post).build());
            return +1;
        } else {
            likeRepository.deleteByUser_IdAndPost_Id(user.getId(), postId);
            return -1;
        }
    }

    @Override
    public List<Like> getPosts(User user) {
        List<Like> likes = likeRepository.findAllByUser(user);
        if (likes.isEmpty()) throw new IllegalArgumentException("좋아요 한 포스트가 존재하지 않습니다.");
        return likes;
    }

    @Override
    public Long countLike(Long postId) {
        return likeRepository.countByPost_Id(postId);
    }
}
