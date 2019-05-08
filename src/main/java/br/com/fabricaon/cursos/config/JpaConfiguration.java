package br.com.fabricaon.cursos.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JpaConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean EntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
				
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();			
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);		
		
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		
		localContainerEntityManagerFactoryBean.setJpaProperties(setHibernateProperties());
		
		localContainerEntityManagerFactoryBean.setPackagesToScan("br.com.fabricaon.cursos.model");
		
		return localContainerEntityManagerFactoryBean;
	}
	
	@Bean
	@Profile("dev")
	private DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/cursosonline");
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");		
		
		return driverManagerDataSource;
	}

	private Properties setHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		//properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");		
		return properties;
	}
	
	@Bean
	public JpaTransactionManager transactionalManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
}
