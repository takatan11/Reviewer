/*アプリが起動したときにサンプルデータを渡すためのプログラム。ゆくゆくは消去される。*/

package com.example.reviewer.dataLoader;


import com.example.reviewer.entity.Course;
import com.example.reviewer.entity.Faculty;
import com.example.reviewer.entity.DayOfClass;
import com.example.reviewer.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor//自動でコンストラクタ作成してくれる
//commandlinerunnerというインターフェイスはアプリケーション起動時に実行される
public class DataLoader implements CommandLineRunner {
    private final CourseRepository courseRepository;

    public void run(String[] args) throws Exception {
        if (courseRepository.count()>0){
            return;//データが既に存在している場合は新しく作成しないようになっている。無限に増えてしまうから。
        }
        Course c1=new Course(null,Faculty.EDUCATION,"体育","上田", DayOfClass.MONDAY,"体を動かす");
        Course c2=new Course(null,Faculty.ENGINEERING,"線形代数","田中", DayOfClass.MONDAY,"線形代数の基礎を学ぶ");
        //Courseからインスタンスを作成した
        courseRepository.save(c1);//デモ用に作成したc1のデータをデータベースに保存する
        courseRepository.save(c2);//同じく
    }

}
