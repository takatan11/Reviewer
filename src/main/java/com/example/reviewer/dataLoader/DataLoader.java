package com.example.reviewer.dataLoader;


import com.example.reviewer.entity.Course;
import com.example.reviewer.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CourseRepository courseRepository;

    public void run(String[] args) throws Exception {
        if (courseRepository.count()>0){
            return;
        }
        Course c1=new Course(null,"学部","授業の名前","担当教員","授業の曜日","授業の概要");
        Course c2=new Course(null,"工学部","線形代数","田中","毎週月曜日","線形代数の基礎を学ぶ");
        //Courseからインスタンスを作成した
        courseRepository.save(c1);//デモ用に作成したc1のデータを保存する
        courseRepository.save(c2);//同じく
    }

}
