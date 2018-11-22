package com.myexperiments.springmvc.configuration;

import com.myexperiments.springmvc.configuration.condition.Neo4JDbCondition;
import org.neo4j.ogm.config.Configuration.Builder;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Configuration
@PropertySource("classpath:beans-config.properties")
@Conditional(Neo4JDbCondition.class)
@EnableNeo4jRepositories(basePackages="com.myexperiments.springmvc.domain.service.neo4j")
public class Neo4JConfiguration {

    // **** BEGIN: HOW IT WAS DEFINED ON THE BOOK ****
    /*public Neo4jConfig() {
        setBasePackage("orders");
    }*/

    /**
     * “Embedded” means that the database engine is running within the same JVM as a part of your application rather
     * than as a separate server. The data is still persisted to the filesystem (at /tmp/graphdb in this case).
     * */
    /*@Bean(destroyMethod="shutdown")
    public GraphDatabaseService graphDatabaseService(Environment env) {
        return new GraphDatabaseFactory().newEmbeddedDatabase("/tmp/graphdb");
    }*/
    // **** END: HOW IT WAS DEFINED ON THE BOOK ****

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        return new Builder().build();
    }

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "com.myexperiments.springmvc.domain.service.neo4j");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(getSessionFactory());
    }

}
