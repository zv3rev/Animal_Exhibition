package com.vsu.app.entity;

public enum Role {
    Participant ("Участник"),
    Administrator ("Администратор"),
    Judge ("Судья");

    private final String title;

    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
