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

    @Query("SELECT c FROM Course c")

    List<Course> search(
      @Param("faculty")  Faculty faculty,
      @Param("className") String className,
      @Param("dayOfClass")  DayOfClass dayOfClass,
      @Param ("teacher")String teacher
    );
}
