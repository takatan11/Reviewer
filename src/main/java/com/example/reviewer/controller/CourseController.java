/*このファイルはHTMLにデータを渡している。トップページのスタイル、詳細ページのスタイル、レビュー投稿を表示する、の機能が実装されている。
さらに、ＤＴＯを使用することで必要なデータのみまとめてＨＴＭＬ側に渡すことができるようになっている。*/


package com.example.reviewer.controller;

import com.example.reviewer.dto.CourseDTO;
import com.example.reviewer.dto.ReviewDTO;
import com.example.reviewer.entity.Course;
import com.example.reviewer.entity.Review;
import com.example.reviewer.entity.Faculty;
import com.example.reviewer.form.CourseSearchForm;
import com.example.reviewer.repository.CourseRepository;
import com.example.reviewer.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;
//URLによって違うページにアクセスするようになっている

    @GetMapping("/")//URLがトップページのとき
    public String listCourses(Model model,CourseSearchForm form) {//ModelはHTMLにデータを渡すためのかご
        List<Course> courseList = courseRepository.search(
                form.getFaculty(),
                form.getClassName(),
                form.getDayOfClass(),
                form.getTeacher()
        );//CourseRepositoryデータベースから情報をすべて取ってくる
        List<CourseDTO> courseDTOList = courseList.stream()
                .map(c -> new CourseDTO(c.getId(), c.getFaculty(), c.getClassName(), c.getTeacher(), c.getDayOfClass(), c.getDescription()))
                .collect(Collectors.toList());
        model.addAttribute("courses", courseDTOList);//DTOで詰め替えた情報を画面に表示するHTMLに渡すmodelという名前のかごに入れる。名前はcourses
        model.addAttribute("faculties", Faculty.values());
        model.addAttribute("daysOfClass", com.example.reviewer.entity.DayOfClass.values());
        model.addAttribute("courseSearchForm", form);
        return "list";
    }

    @GetMapping("/course/{id}")//URLが/course/{id}のとき
    public String showCourse(@PathVariable Long id, Model model) {
        var courseOpt = courseRepository.findById(id);//データベースからidに該当する情報を取ってくる
        if (courseOpt.isEmpty()) {
            return "redirect:/"; // 該当するコースがない場合はトップページにリダイレクト
        }
        var course = courseOpt.get();//該当する情報をcourseに入れる
        CourseDTO courseDTO = new CourseDTO(course.getId(), course.getFaculty(), course.getClassName(), course.getTeacher(), course.getDayOfClass(), course.getDescription());//これが使用できるのはCourseDTOクラスのなかでコンストラクタを定義しているから
        model.addAttribute("course", courseDTO);

        var reviews = reviewRepository.findByCourseId(id);//該当するコースに紐づく口コミ情報を取ってくる
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");//口コミの日時表示のフォーマットを指定
        List<ReviewDTO> reviewDTOList = reviews.stream()
                .map(r -> new ReviewDTO(r.getReviewer(), r.getScore(), r.getComment(), r.getCreatedAt().format(formatter)))
                .collect(Collectors.toList());
        model.addAttribute("reviews", reviewDTOList);//レビューDTOの情報もかごに入れる
        model.addAttribute("faculties", Faculty.values());
        return "course";
    }

    @PostMapping("/course/{id}/review")//URLが/course/{id}/reviewのとき
    public String addReview(
            @PathVariable Long id, //URLのid部分を取得してどの授業に関するレビューかを特定する
            @RequestParam String reviewer,//HTMLのフォームで入力されたレビュー情報をそれぞれ受け取ってrevierwer,score,commentに入れる
            @RequestParam int score,
            @RequestParam String comment) {
        var courseOpt = courseRepository.findById(id);//データベースからidに該当する情報を取ってくる。授業名や講師名など
        if (courseOpt.isEmpty()) {
            return "redirect:/";// 該当するコースがない場合はトップページにリダイレクト
        }
        var course = courseOpt.get();//該当する情報をcourseに入れる

        // 新しいレビューを作成して保存
        Review review = new Review();//新しいレビュー情報を入れる箱を作成
        review.setReviewer(reviewer);//レビュー情報を箱に入れる
        review.setScore(score);//ユーザーが決めたスコアをいれる
        review.setComment(comment);//コメントを入れる
        review.setCreatedAt(LocalDateTime.now());//レビューが作成された日時を入れる
        review.setCourse(course);//保存するときに自動的に「あ、このレビューはcourse_id=5なんだ」と判断してDBに5を書き込んでくれる
        reviewRepository.save(review);//レビュー情報をデータベースに保存する

        return "redirect:/course/" + id;//レビュー投稿直後に自動でリロードして最新のレビューを表示するようにしている
    }
}
