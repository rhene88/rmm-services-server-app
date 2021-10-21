package com.ninja.rmm.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "rmmEntityManagerFactory",
        transactionManagerRef = "rmmTransactionManager",
        basePackages = {"com.ninja.rmm.repository"
        })
public class DBConfig {

    @Primary
    @Bean("rmmDataSource")
    @ConfigurationProperties("rmm.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("rmmEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("rmmDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.ninja.rmm.model"
                )
                .persistenceUnit("rmm")
                .build();
    }

    @Primary
    @Bean("rmmTransactionManager")
    public PlatformTransactionManager rmmTransactionManager(
            @Qualifier("rmmEntityManagerFactory") EntityManagerFactory rmmEntityManagerFactory) {
        return new JpaTransactionManager(rmmEntityManagerFactory);
    }
}
