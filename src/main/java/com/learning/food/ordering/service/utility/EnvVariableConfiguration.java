package com.learning.food.ordering.service.utility;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties
public class EnvVariableConfiguration {

    private String DATABASE_URL;
    private String DATABASE_USERNAME;
    private String DATABASE_PASSWORD;
}
