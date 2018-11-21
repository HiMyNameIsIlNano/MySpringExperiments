package com.myexperiments.springmvc.configuration;

import com.myexperiments.springmvc.security.condition.ApplicationManagedEntityManagerFactoryCondition;
import com.myexperiments.springmvc.security.condition.ContainerManagedEntityManagerFactoryCondition;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
@ComponentScan({ "com.myexperiments.springmvc.configuration" })
@PropertySource(value = { "classpath:application.properties", "classpath:beans-config.properties" })
public class DataSourceConfiguration {

    @Bean
    public DataSource h2DataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .setName("appdb")
                .addScript("db/sql/schema.sql")
                .addScript("db/sql/test-data.sql")
                .build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        /*
        * To use the PostgreSQL mode, use the database URL jdbc:h2:~/test;MODE=PostgreSQL or the SQL statement SET MODE PostgreSQL.
         * */
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adapter;
    }

    /**
     * 1. Application-managed—Entity managers are created when an application directly requests one from an entity
     * manager factory. With application-managed entity managers, the application is responsible for opening or closing
     * entity managers and involving the entity manager in transactions.
     * This type of entity manager is most appropriate  for use in standalone applications that don’t run in a Java EE
     * container.
     *
     * 2. Container-managed—Entity managers are created and managed by a Java EE container. The application does not
     * interact with the entity manager factory at all. Instead, entity managers are obtained directly through injection
     * or from JNDI. The container is responsible for configuring the entity manager factories.
     * This type of entity manager is most appropriate for use by a Java EE container that wants to maintain some control
     * over JPA configuration beyond what’s specified in persistence.xml.
     *
     * What does this all mean for Spring developers wanting to use JPA?
     * Not much.
     *
     * Regardless of which variety of EntityManagerFactory you want to use, Spring will take responsibility for managing
     * EntityManagers for you. If you’re using an application-managed entity manager, Spring plays the role of an application
     * and transparently deals with the EntityManager on your behalf. In the container-managed scenario, Spring plays
     * the role of the container.
     */
    @Bean
    @Conditional(ApplicationManagedEntityManagerFactoryCondition.class)
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        // Application Managed Entity Manager Factory Bean
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("spitterPU");
        return emfb;
    }

    @Bean
    @Conditional(ContainerManagedEntityManagerFactoryCondition.class)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        // Container Managed Entity Manager Factory Bean
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.myexperiments.springmvc.domain.model");
        return emfb;
    }

}
