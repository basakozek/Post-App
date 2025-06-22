package org.basak.twitterdemo.model.enums;

public enum PostState {
    ACTIVE("Gönderi görünür durumda"),
    DELETED("Gönderi silindi");

    private final String description;

    PostState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
