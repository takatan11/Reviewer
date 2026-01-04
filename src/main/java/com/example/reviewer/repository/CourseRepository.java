package com.example.reviewer.repository;

import com.example.reviewer.entity.Course;
import com.example.reviewer.entity.Faculty;
import com.example.reviewer.entity.DayOfClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE " + //検索用のSQL文。cはCourseテーブルを指している。
           "(:faculty IS NULL OR c.faculty = :faculty) AND " +/*:facultyの意味は下の@Param("faculty") Faculty facultyで定義されている変数facultyのこと。
            c.facultyの意味はCourseテーブルのfaculty。ANDでほかの条件もつなげていく*/
           "(:className IS NULL OR c.className LIKE CONCAT('%', :className, '%')) AND " +//Like文は部分一致検索をするためのもの。
           "(:dayOfClass IS NULL OR c.dayOfClass = :dayOfClass) AND " +
           "(:teacher IS NULL OR c.teacher LIKE CONCAT('%', :teacher, '%'))")//
    List<Course> search(//本命の処理はここから。""で囲ってあるのは上のSQL文で使用する為の変数
      @Param("faculty")  Faculty faculty,//@Paramで上のSQL文の:facultyと紐づけている
      @Param("className") String className,
      @Param("dayOfClass")  DayOfClass dayOfClass,
      @Param("teacher") String teacher
    );
}
