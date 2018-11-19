package com.myexperiments.springmvc.configuration;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

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

    /*
    * The LocalSessionFactoryBean from hibernate3 is used for XML Mapping.
    * In this case we are going for an Annotation-based mapping.
    *
    * If one prefers, one may also explicitly list all of the application’s persistent classes by specifying a list of
    * fully qualified class names in the annotatedClasses property (see below).
    * */
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan(new String[] { "com.myexperiments.springmvc.domain" });
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        sfb.setHibernateProperties(props);
        /*sfb.setAnnotatedClasses(
                new Class<?>[] { Spitter.class, Spittle.class }
        );*/
        return sfb;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post-processor that adds an adviser to any bean that’s
     * annotated with @Repository so that any platform-specific exceptions are caught and then rethrown as one of
     * Spring’s unchecked data-access exceptions.
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
