/*ユーザーが検索ボックスで入力した情報を受け取る所。変数に当てはめて管理したりする場所。*/
package com.example.reviewer.form;

import com.example.reviewer.entity.DayOfClass;
import com.example.reviewer.entity.Faculty;
import lombok.Data;

@Data
public class CourseSearchForm {
    private Faculty faculty;//入力フォームで選択された学部をfaculty変数に入れる。入れられるのはFaculty.javaの中にある学部名のみ

    private String className;//入力フォームで入力された授業名をclassName変数に入れる

    private DayOfClass dayOfClass;//入力フォームで選択された授業曜日をdayOfClass変数に入れる

    private String teacher;//入力フォームで入力された担当教員名をteacher変数に入れる
}
