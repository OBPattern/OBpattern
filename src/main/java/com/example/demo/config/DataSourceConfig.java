package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

//import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = {"com.example.demo.data"}, entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@EntityScan("com.example.demo.data.entity")
@Slf4j
public class DataSourceConfig {

    @Primary
    @Bean("dataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean("dataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean("entityManagerFactory")
    @ConfigurationProperties("spring.jpa")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder.dataSource(dataSource).packages("com.example.demo.data").build();
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto","create");
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
        log.info("::::::::::PROPERTIES UPDATE::::::JPA::::"+properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
        return  new JpaTransactionManager(entityManagerFactory);
    }
}
