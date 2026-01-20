package com.example.reviewer.entity;


import lombok.Getter;

@Getter
public enum Faculty {
    EDUCATION("教育学部"),//システム側ではEDUCATIONだが画面表示では教育学部とする。下のコードも同じようになっている
    ENGINEERING("工学部"),
    LOCAL("地域科学部"),
    APPLIEDBIOLOGICALSCIENCE("応用生物科学部"),
    GLOBALSYSTEM("社会システム経営学環"),
    MEDICINE("医学部"),
    ALL("全学共通教育"),
    OTHER("その他");

    private final String displayName;

    Faculty(String displayName) {
        this.displayName = displayName;//コンストラクタ
    }

    @Override
    public String toString() {
        return displayName;//画面表示のときに""の中身の学部を表示する変換をするために使われる
    }
}

