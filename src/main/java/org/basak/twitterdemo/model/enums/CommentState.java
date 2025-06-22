package org.basak.twitterdemo.model.enums;

public enum CommentState {
    ACTIVE("Yorum aktif ve görünür durumda"),
    BANNED("Yorum engellendi"),
    DELETED("Yorum silindi");

    private final String description;

    CommentState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
