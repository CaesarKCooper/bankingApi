package com.example.bankingapi.account;

public enum AccountType {

    SAVINGS("savings"), CHECKING("checking"), CREDIT("credit");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}