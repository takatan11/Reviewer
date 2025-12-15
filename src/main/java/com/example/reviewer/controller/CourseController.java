package com.example.reviewer.controller;

import com.example.reviewer.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class CourseController {
    private ReviewRepository reviewRepository;
    @GetMapping("/courses")
    public String controller(Model model){
        List<>
    };

}
