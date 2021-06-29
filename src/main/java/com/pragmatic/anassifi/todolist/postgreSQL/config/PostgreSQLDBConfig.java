package com.pragmatic.anassifi.todolist.postgreSQL.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postEntityManagerFactory",
        transactionManagerRef = "postTransactionManager",
        basePackages = {"com.pragmatic.anassifi.todolist.postgreSQL.repository"}
)
public class PostgreSQLDBConfig {
	
	@Bean(name="postDatasource")
	@ConfigurationProperties(prefix = "spring.tododb.datasource")
	public DataSource dataSource(){
	    return DataSourceBuilder.create().build();
	}
		
	@Bean(name="postEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
	        EntityManagerFactoryBuilder builder,
	        @Qualifier("postDatasource") DataSource dataSource
	)
	{
		Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("driverClassName", "org.postgresql.Driver");
	    return builder
	            .dataSource(dataSource)
	            .properties(properties)
	            .packages("com.pragmatic.anassifi.todolist.postgreSQL.model")
	            .persistenceUnit("")
	            .build();
	}	
	
	@Bean(name="postTransactionManager")
	public PlatformTransactionManager transactionManager(
	        @Qualifier("postEntityManagerFactory")
	        EntityManagerFactory entityManagerFactory
	)
	{
	    return new JpaTransactionManager(entityManagerFactory);
	}
}
