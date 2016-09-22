package com.movieflix.app;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:mysql.properties")
public class AppEclipseLinkConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
		emf.setJpaProperties(jpaProperties());
		emf.setPackagesToScan("com.movieflix.app.entity");
		return emf;

	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("mysql.driverClass"));
		ds.setUrl(env.getProperty("mysql.url"));
		ds.setUsername(env.getProperty("mysql.username"));
		ds.setPassword(env.getProperty("mysql.password"));
		return ds;
	}

	@Bean
	public PlatformTransactionManager txManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager(emf);
		return txManager;
	}

	private Properties jpaProperties() {
		Properties prop = new Properties();
		prop.setProperty(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
		prop.setProperty(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINER_LABEL);
		prop.setProperty(PersistenceUnitProperties.WEAVING, "false");
		return prop;
	}

}
