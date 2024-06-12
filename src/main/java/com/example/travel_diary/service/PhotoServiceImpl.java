package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.DiaryDetail;
import com.example.travel_diary.global.domain.entity.Photo;
import com.example.travel_diary.global.domain.repository.PhotoRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Override
    @Transactional
    public void insert(String[] paths, Long diaryDetailId) throws IOException {
        Storage storage =  StorageOptions.newBuilder().setProjectId("titanium-vision-424101-s9").build().getService();
        String bucketName = "jorang";
        DiaryDetail diaryDetail = DiaryDetail.builder().id(diaryDetailId).build();
        for (int i = 0; i < paths.length; i++) {
            BlobId blobId = BlobId.of(bucketName, "diaryDetail/" + diaryDetailId + "/image" + (i+1));
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.createFrom(blobInfo, Paths.get(paths[i]));
            String googlePath = storage.get(blobId).getMediaLink();
            photoRepository.save(Photo.builder().path(googlePath).diaryDetail(diaryDetail).build());
        }
    }

    @Override
    public Photo getById(Long id) {
        return photoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Photo> getByDiaryDetailId(Long diaryDetailId) {
        return photoRepository.findAllByDiary_Id(diaryDetailId);
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
