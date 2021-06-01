package com.learning.food.ordering.service.enumerate;

public enum FilterBy {
    ALL("ALL");

    private String value;
    FilterBy(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
