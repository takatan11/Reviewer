package com.example.reviewer.controller;

import com.example.reviewer.entity.Course;
import com.example.reviewer.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping("/")
    public String listCourses(Model model) {
        List<Course> courseList = courseRepository.findAll();//courseデータベースから授業データ（一つ一つはCourseという名前。Listをつけて全部取ってくる）をすべて取ってくる

        model.addAttribute("courses", courseList);//上の行でcourseListに保存された内容にcousesという名前を付けて渡す

        return "list";//表示するHTMLファイルの名前を指定
    }
}