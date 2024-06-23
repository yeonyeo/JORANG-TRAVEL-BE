package com.example.travel_diary.global.domain.repository;
import com.example.travel_diary.global.domain.entity.Post;
import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.type.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByDiaries_Scope(Scope scope);
    List<Post> findByDiaries_ScopeOrderByCreatedAtDesc(Scope scope);
    List<Post> findTop5ByDiaries_ScopeAndDiaries_CountryOrderByCreatedAtDesc(Scope scope, String country);
    List<Post> findAllByDiaries_ScopeAndDiaries_CountryOrderByCreatedAtDesc(Scope scope, String country);
    List<Post> findTop5ByDiaries_ScopeAndCreatedAtBetweenOrderByLoveDesc(Scope scope, LocalDateTime startOfWeek, LocalDateTime endOfWeek);
    List<Post> findAllByDiaries_ScopeAndDiaries_DateBetweenOrderByCreatedAtDesc(Scope scope, LocalDate from, LocalDate to);
    List<Post> findAllByUser(User user);

}

