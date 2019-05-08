package br.com.fabricaon.cursos.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {
	
	@Bean
	@Profile("test")
	private DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/teste_cursosonline");
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		return driverManagerDataSource;
	}
}
