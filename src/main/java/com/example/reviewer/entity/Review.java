package com.example.reviewer.entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reviews")

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//一つ一つの口コミに対してidを振る

    @Column(nullable=false)
    private String reviewer;//投稿した人の名前

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private int score;//ユーザーの五段階評価

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable = false,length=1000)
    private String comment;//1000字までの評価コメント

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;//Courceとの接続
}
