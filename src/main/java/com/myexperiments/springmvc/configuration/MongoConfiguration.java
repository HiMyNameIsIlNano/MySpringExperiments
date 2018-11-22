package com.myexperiments.springmvc.configuration;

import com.mongodb.MongoClient;
import com.myexperiments.springmvc.configuration.condition.MongoDbCondition;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

/*
* Enabled Spring Data’s automatic Mongo repository generation with the @EnableMongoRepositories annotation.
* Rather than declare the bean below directly, the configuration class could extend AbstractMongoConfiguration and
* override its getDatabaseName() and mongo() methods. However, as we will be using an in-memory Mongo DB we will not
* do so.
*/
@Configuration
@PropertySource("classpath:beans-config.properties")
@Conditional(MongoDbCondition.class)
@EnableMongoRepositories(basePackages="com.myexperiments.springmvc.domain.service.mongo.*",
        repositoryImplementationPostfix="Impl")
public class MongoConfiguration {

    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "OrdersDB";

    /**
     * Even if one never use MongoTemplate directly, the bean is needed because the automatically generated
     * repositories will use it under the covers.
     */
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, MONGO_DB_NAME);
    }

}
