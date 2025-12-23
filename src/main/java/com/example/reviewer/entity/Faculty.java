package com.example.reviewer.entity;

public enum Faculty {
    EDUCATION("教育学部"),
    ENGINEERING("工学部"),
    SCIENCE("理学部"),
    LITERATURE("文学部"),
    ECONOMICS("経済学部"),
    LAW("法学部"),
    MEDICINE("医学部"),
    OTHER("その他");

    private final String displayName;

    Faculty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

