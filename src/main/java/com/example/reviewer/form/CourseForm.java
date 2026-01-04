package com.example.reviewer.form;

import com.example.reviewer.entity.DayOfClass;
import com.example.reviewer.entity.Faculty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseForm {
    @NotBlank
    private String className;

    @NotBlank
    private String teacher;

    @NotNull
    private Faculty faculty;

    @NotNull
    private DayOfClass dayOfClass;

    @Size(max = 1000)
    private String description;
}
