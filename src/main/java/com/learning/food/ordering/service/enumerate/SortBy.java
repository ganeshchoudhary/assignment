package com.learning.food.ordering.service.enumerate;

public enum SortBy {
    VENDOR_NAME("vendorName");

    private String value;

    SortBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
