package com.example.travel_diary.service;


import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    @Transactional
    public Long createDiary(Long postId) {
        Post post = Post.builder().id(postId).build();
        Diary diary = Diary.builder().post(post).build();
        return diaryRepository.save(diary).getId();
    }

    @Override
    public Diary getById(Long id) {
        return diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Diary> getAllByPostId(Long postId) {
        return diaryRepository.findAllByPost_Id(postId);
    }

    @Override
    @Transactional
    public void updateDiary(Long id, LocalDate date) {
        Diary diary = diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        diary.setDate(date);
    }

    @Override
    @Transactional
    public void deleteDiaryById(Long id) {
        diaryRepository.deleteById(id);
    }
}
