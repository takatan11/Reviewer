/*ユーザーが検索ボックスで入力した情報を受け取る所。変数に当てはめて管理したりする場所。*/
package com.example.reviewer.form;

import com.example.reviewer.entity.DayOfClass;
import com.example.reviewer.entity.Faculty;
import lombok.Data;

@Data
public class CourseSearchForm {
    private Faculty faculty;

    private String className;

    private DayOfClass dayOfClass;
}
