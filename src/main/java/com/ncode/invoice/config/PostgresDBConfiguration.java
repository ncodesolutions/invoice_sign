package com.ncode.invoice.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ncode.invoice.repository", transactionManagerRef = "transactionManager")
public class PostgresDBConfiguration {

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverClass;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUserName;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${spring.jpa.show-sql}")
	private String hibernateShowSql;

	@Value("${spring.jpa.properties.hibernate.format_sql}")
	private String hibernateFormatSql;

	private String basePackagesToScan = "com.ncode.invoice.entity";

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbDriverClass);
		String url = dbUrl;
		dataSource.setUrl(url);
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);
		dataSource.setMaxActive(10);
		dataSource.setMinIdle(10);
		dataSource.setMaxIdle(10);
		dataSource.setMaxWait(10);
		dataSource.setTestOnBorrow(true);
		dataSource.setInitialSize(10);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(10000);
		dataSource.setMinEvictableIdleTimeMillis(20000);
		dataSource.setValidationQuery("Select 1");
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(basePackagesToScan);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShowSql);
		properties.put("hibernate.format_sql", hibernateFormatSql);
		return properties;
	}

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory =
				/** define local Coniner entity manager factory bean */
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(basePackagesToScan);
		entityManagerFactory.setPersistenceUnitName("postgresDb");
		HibernateJpaVendorAdapter hibernateVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(hibernateVendorAdapter);
		entityManagerFactory.setJpaProperties(hibernateProperties());
		return entityManagerFactory;
	}

	@Bean(name = "transactionManager")
	@Primary
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}