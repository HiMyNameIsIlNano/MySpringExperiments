package com.myexperiments.springmvc.configuration;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
@ComponentScan({ "com.myexperiments.springmvc.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class DataSourceConfiguration {

    @Bean
    public DataSource h2DataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/schema.sql")
                .addScript("db/sql/test-data.sql")
                .build();
    }

}
