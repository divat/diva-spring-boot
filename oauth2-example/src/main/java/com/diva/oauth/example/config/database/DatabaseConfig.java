package com.diva.oauth.example.config.database;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database & Hibernate config
 * @author DHIVAKART
 *
 */
@Configuration
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
	    sessionFactory.setPackagesToScan(new String[] { "com.diva.oauth.example.model" });
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		/*hibernateProperties.put("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));*/
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setPassword(env.getProperty("database.password"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setDriverClassName(env.getProperty("database.driver"));
		return dataSource;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws IOException, PropertyVetoException{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public TokenStore tokenStore() throws PropertyVetoException{
		return new JdbcTokenStore(dataSource());
	}
}
