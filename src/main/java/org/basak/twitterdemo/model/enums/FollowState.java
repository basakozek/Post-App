package org.basak.twitterdemo.model.enums;

public enum FollowState {
    PENDING("Takip isteği gönderildi, onay bekliyor"),
    OK("Takip isteği kabul edildi"),
    CANCELED("Takip isteği iptal edildi");

    private final String description;

    FollowState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
