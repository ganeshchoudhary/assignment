package com.learning.food.ordering.service.constant;

import java.time.format.DateTimeFormatter;

public class CommonConstants {

    private CommonConstants(){}

    public final static String UTF = "UTF-8";
    public final static String AUTHORIZATION = "Authorization";
    public final static String TOKEN = "token";

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

}
