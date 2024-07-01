//package com.example.travel_diary.controller;
//
//import com.example.travel_diary.global.domain.entity.Photo;
//import com.example.travel_diary.global.request.PhotoRequestDto;

//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/photos")
//@RequiredArgsConstructor
//public class PhotoController {
//    private final PhotoService photoService;
//

//    @PostMapping("/diary/{diaryId}")

//    @ResponseStatus(HttpStatus.CREATED)
//    public void insert(@RequestBody PhotoRequestDto req, @PathVariable Long diaryId) throws IOException {
//        photoService.insert(req, diaryId);
//    }
//
//    @GetMapping("/{id}")
//    public Photo getById(@PathVariable Long id) {
//        return photoService.getById(id);
//    }
//

//    @GetMapping("/diary/{diaryId}")

//    @GetMapping("/diaries/{diaryId}")

//    public List<Photo> getByDiaryDetailId(@PathVariable Long diaryId) {
//        return photoService.getByDiaryId(diaryId);
//    }
//
//    @PutMapping("/{id}")
//    public void update(@PathVariable Long id, @RequestBody String path) throws IOException {
//        photoService.update(id, path);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable Long id) {
//        photoService.deleteById(id);
//    }
//}

