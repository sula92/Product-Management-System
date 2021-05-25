package com.sula.productmanagementsystem.model.enumeration;

public enum Status {
    D("Deleted"), A("Available");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
