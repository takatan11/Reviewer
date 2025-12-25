package com.example.reviewer.entity;


import lombok.Getter;

@Getter
public enum Faculty {
    EDUCATION("教育学部"),//システム側ではEDUCATIONだが画面表示では教育学部とする。下のコードも同じようになっている
    ENGINEERING("工学部"),
    SCIENCE("理学部"),
    LITERATURE("文学部"),
    ECONOMICS("経済学部"),
    LAW("法学部"),
    MEDICINE("医学部"),
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

