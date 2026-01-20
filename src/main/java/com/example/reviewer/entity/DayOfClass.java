package com.example.reviewer.entity;

import lombok.Getter;

@Getter
public enum DayOfClass {
    MONDAY("月曜日"),
    TUESDAY("火曜日"),
    WEDNESDAY("水曜日"),
    THURSDAY("木曜日"),
    FRIDAY("金曜日"),
    SATURDAY("土曜日"),
    SUNDAY("日曜日");

    //書き換えられないdisplaynameを取得するためのメソッド
    private final String displayName;//勝手に書き換えられないようにprivate型になっている

    DayOfClass(String displayName) {

        this.displayName = displayName;
    }

    @Override
    public String toString() {

        return displayName; //画面表示のときに""の中身の曜日をMondayなどから月曜日などに表示する変換をするために使われる
    }
}
