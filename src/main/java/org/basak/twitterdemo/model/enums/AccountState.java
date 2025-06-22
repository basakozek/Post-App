package org.basak.twitterdemo.model.enums;

public enum AccountState {
    ACTIVE("Kullanıcı aktif ve sistemi kullanabilir"),
    PASSIVE("Kullanıcı pasif"),
    BANNED("Kullanıcı engellenmiş ve sisteme erişemez");

    private final String description;

    AccountState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}