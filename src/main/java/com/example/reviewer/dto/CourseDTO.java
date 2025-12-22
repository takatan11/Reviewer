package com.example.reviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id; // 画面表示には使わないがリンク生成に必要
    private String faculty;
    private String className;
    private String teacher;
    private String dayOfclass;
    private String description;
}
