package com.vsu.app.entity;

public enum Role {
    EXHIBITOR ("Участник"),
    ADMINISTRATOR ("Администратор"),
    JUDGE ("Судья");

    private final String title;

    Role(String title) {
        this.title = title;
    }
}
