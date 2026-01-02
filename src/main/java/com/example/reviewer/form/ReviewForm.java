package com.example.reviewer.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewForm {
    @NotBlank(message = "名前は必須です")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String reviewer;

    @NotNull(message = "スコアを入力してください")
    @Min(value = 1, message = "スコアは1以上です")
    @Max(value = 5, message = "スコアは5以下です")
    private Integer score;

    @NotBlank(message = "コメントは必須です")
    @Size(max = 500, message = "コメントは500文字以内で入力してください")
    private String comment;
}

