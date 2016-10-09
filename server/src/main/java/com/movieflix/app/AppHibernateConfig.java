package com.movieflix.app;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Loukik
 * 
 *         This class contains Hibernate Configurations
 * 
 */

@EnableTransactionManagement
@ComponentScan
@PropertySource({"classpath:mysql.properties" })
public class AppHibernateConfig {

	@Autowired
	private Environment env;


	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.movieflix.app.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/movieflix");
		dataSource.setUsername(env.getProperty("mysql.username"));
		dataSource.setPassword(env.getProperty("mysql.password"));
	
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2dll.auto", org.hibernate.boot.SchemaAutoTooling.UPDATE.toString());
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.format_sql", "true");
		prop.setProperty("hibernate.use_sql_comments", "true");

		return prop;
	}

	@Bean
	public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txm = new HibernateTransactionManager();
		txm.setSessionFactory(sessionFactory);
		return txm;
	}

}
