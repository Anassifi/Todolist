package com.pragmatic.anassifi.todolist.mysql.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory", transactionManagerRef = "transactionManagerUser", basePackages = {"com.pragmatic.anassifi.todolist.mysql.repository"})
public class MysqlDBConfig {

    @Primary
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource dataSourceProperties() {
    	return new HikariDataSource();
    }

    @Primary
    @Bean(name="userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("datasource") DataSource dataSource
    )
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.put("driverClassName", "com.mysql.cj.jdbc.Driver");
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.pragmatic.anassifi.todolist.mysql.model")
                .persistenceUnit("")
                .build();
    }

    @Primary
    @Bean(name="transactionManagerUser")
    public PlatformTransactionManager transactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory entityManagerFactory
    )
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    public HikariConfig hikariConfig() {
//    	return new HikariConfig();
//    }
//    
//   @Bean
//	public DataSource dataSource() {
//		return new HikariDataSource(hikariConfig());
//	}
//   
    
    
    
}
