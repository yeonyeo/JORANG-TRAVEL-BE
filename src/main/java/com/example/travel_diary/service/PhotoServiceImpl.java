package com.example.travel_diary.service;


import com.example.travel_diary.global.domain.entity.Diary;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.repository.PhotoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final DiaryService diaryService;

    @Override
    @Transactional
    public void insert(String path, Long diaryId) {
        Diary diary = diaryService.getById(diaryId);
        photoRepository.save(Photo.builder().path(path).diary(diary).build());
    }

    @Override
    public List<Photo> getByDiary(Long diaryId) {
        return photoRepository.findAllByDiary_Id(diaryId);
    }

    @Override
    @Transactional
    public void update(Long id, String path) {
        Photo photo = photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        photo.setPath(path);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }
}
