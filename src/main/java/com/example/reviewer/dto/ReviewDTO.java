/*コメントの一覧をHTMLで表示するためだけの専用の箱*/
package com.example.reviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private String reviewer;
    private int score;
    private String comment;
    private String createdAt; // 表示用にフォーマット済みの日時文字列を保持
}
