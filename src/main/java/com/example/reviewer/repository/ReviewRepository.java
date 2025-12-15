package com.example.reviewer.repository;

import java.util.List;
import com.example.reviewer.entity.Course;
import com.example.reviewer.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCourse(Course course); //Courseテーブルを見て指定された行につながれたReviewテーブルの情報を取ってくる
}
