package com.example.reviewer.controller;

import com.example.reviewer.entity.Course;
import com.example.reviewer.entity.Review;
import com.example.reviewer.repository.CourseRepository;
import com.example.reviewer.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/")
    public String listCourses(Model model) {
        List<Course> courseList = courseRepository.findAll();

        model.addAttribute("courses", courseList);

        return "list";
    }

    @GetMapping("/course/{id}")
    public String showCourse(@PathVariable Long id, Model model) {
        var courseOpt = courseRepository.findById(id);
        if (courseOpt.isEmpty()) {
            return "redirect:/";
        }
        var course = courseOpt.get();
        model.addAttribute("course", course);

        // コメント一覧を取得してモデルに追加
        var reviews = reviewRepository.findByCourseId(id);
        model.addAttribute("reviews", reviews);

        return "course";
    }

    @PostMapping("/course/{id}/review")
    public String addReview(@PathVariable Long id, @RequestParam String reviewer, @RequestParam int score, @RequestParam String comment) {
        var courseOpt = courseRepository.findById(id);
        if (courseOpt.isEmpty()) {
            return "redirect:/";
        }
        var course = courseOpt.get();

        // 新しいレビューを作成して保存
        Review review = new Review();
        review.setReviewer(reviewer);
        review.setScore(score);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        review.setCourse(course);
        reviewRepository.save(review);

        return "redirect:/course/" + id;
    }
}