package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final LikeService likeService;

    // 여행 일지 작성 누르면 바로 post id를 생성 시킴, 업데이트도 작성일자만 갱신
    @Override
    public Long createPost() {
        Post post = postRepository.save(Post.builder().createdAt(LocalDateTime.now()).build());
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
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Long getLikes(Long id) {
        return likeService.countLike(id);
    }
}
