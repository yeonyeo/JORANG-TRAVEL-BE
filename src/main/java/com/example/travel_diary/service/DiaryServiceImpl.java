package com.example.travel_diary.service;


import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.repository.DiaryRepository;
import com.example.travel_diary.global.request.DiaryRequestDto;
import com.example.travel_diary.global.response.DiaryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final PostService postService;

    // diary 생성 버튼 누르자마자 DB에 넣고 id 생성 -> photo에 post id를 할당해주기 위함
    // 실제 data 추가는 update에서 진행
    @Override
    @Transactional
    public Long insertDiary(Post post) {
        Diary diary = diaryRepository.save(Diary.builder().createdAt(LocalDateTime.now()).post(post).build());
        return diary.getId();
    }

    @Override
    public Diary getById(Long id) {
        return diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<DiaryResponse> getAllByPostId(Long postId) {
        List<Diary> diaries = diaryRepository.findAllByPost_Id(postId)
                .orElseThrow(IllegalArgumentException::new);
        List<DiaryResponse> diaryResponses = new ArrayList<>();
        diaries.forEach((diary) ->
                diaryResponses.add(DiaryResponse.from(diary)));
        return diaryResponses;
    }

    @Override
    @Transactional
    public void updateDiary(Long id, DiaryRequestDto req) {
        Diary diary = diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        diary.setTitle(req.title());
        diary.setDate(req.date());
        diary.setScope(req.scope());
        diary.setCountry(req.country());
    }

    @Override
    @Transactional
    public void deleteDiaryById(Long id) {
        diaryRepository.deleteById(id);
    }
}
