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
        Course c1=new Course(null,"教育学部","体育","上田","毎週水曜日","体を動かす");
        Course c2=new Course(null,"工学部","線形代数","田中","毎週月曜日","線形代数の基礎を学ぶ");
        //Courseからインスタンスを作成した
        courseRepository.save(c1);//デモ用に作成したc1のデータを保存する
        courseRepository.save(c2);//同じく
    }

}
