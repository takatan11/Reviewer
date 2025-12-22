package com.example.reviewer.controller;

import com.example.reviewer.dto.CourseDTO;
import com.example.reviewer.dto.ReviewDTO;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/")
    public String listCourses(Model model) {
        List<Course> courseList = courseRepository.findAll();
        // ID を UI 表示せずリンク生成にのみ使うため DTO に詰め替え
        List<CourseDTO> courseDTOList = courseList.stream()
                .map(c -> new CourseDTO(c.getId(), c.getFaculty(), c.getClassName(), c.getTeacher(), c.getDayOfclass(), c.getDescription()))//元のCourseデータを新しいデータの箱であるCourseDTOに詰め替えている
                .collect(Collectors.toList());

        model.addAttribute("courses", courseDTOList);

        return "list";
    }

    @GetMapping("/course/{id}")
    public String showCourse(@PathVariable Long id, Model model) {
        var courseOpt = courseRepository.findById(id);
        if (courseOpt.isEmpty()) {
            return "redirect:/";
        }
        var course = courseOpt.get();
        // 詳細画面も DTO に詰め替え
        CourseDTO courseDTO = new CourseDTO(course.getId(), course.getFaculty(), course.getClassName(), course.getTeacher(), course.getDayOfclass(), course.getDescription());
        model.addAttribute("course", courseDTO);

        var reviews = reviewRepository.findByCourseId(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        List<ReviewDTO> reviewDTOList = reviews.stream()
                .map(r -> new ReviewDTO(r.getReviewer(), r.getScore(), r.getComment(), r.getCreatedAt().format(formatter)))
                .collect(Collectors.toList());
        model.addAttribute("reviews", reviewDTOList);

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