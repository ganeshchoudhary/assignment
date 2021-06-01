package com.learning.food.ordering.service.utility;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class BeanConfiguration {

    private final EnvVariableConfiguration envVariableConfiguration;

    BeanConfiguration(EnvVariableConfiguration envVariableConfiguration) {
        this.envVariableConfiguration = envVariableConfiguration;
    }

    @Bean
    @Primary
    @Profile({"local", "prod", "dev", "default"})
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(envVariableConfiguration.getDATABASE_URL())
                .username(envVariableConfiguration.getDATABASE_USERNAME())
                .password(envVariableConfiguration.getDATABASE_PASSWORD())
                .build();
    }

    @Bean
    @Profile({"test"})
    public DataSource localDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb")
                .username("test")
                .password("test")
                .build();
    }
}
