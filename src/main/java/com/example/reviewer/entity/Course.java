package com.example.reviewer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//自動でidを振ってくれる

    @Column(nullable = false)

    private String faculty;//学部

    private String className;//授業の名前

    private String teacher;//担当教員

    private String dayOfclass;//授業の曜日

    @Column(length=1000)
    private String description; //授業の概要
}
