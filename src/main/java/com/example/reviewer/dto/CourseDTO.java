package com.example.reviewer.dto;

import com.example.reviewer.entity.Faculty;
import com.example.reviewer.entity.DayOfClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id; // 画面表示には使わないがリンク生成に必要
    private Faculty faculty;
    private String className;
    private String teacher;
    private DayOfClass dayOfClass;
    private String description;
}