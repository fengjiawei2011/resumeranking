package com.sjsu.cmpe282.resumeranking.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import com.sjsu.cmpe282.resumeranking.repository.JobRep;
import com.sjsu.cmpe282.resumeranking.repository.ResumeRep;
import com.sjsu.cmpe282.resumeranking.service.Crawler;



@Configuration
@ComponentScan(basePackages = { "com.sjsu.cmpe282.resumeranking.controller"}, excludeFilters = { @Filter(Configuration.class) })
public class MainConfig {
	
	
	private DataSource mySqlDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();		
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/resumerank");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	
	
	@Bean 
	public JdbcTemplate resumeJdbcTemplate() {		
		return new JdbcTemplate(mySqlDataSource());
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ResumeRep resumeRep() {
		return new ResumeRep();
	}	
	
	@Bean
	public JobRep jobRep() {
		return new JobRep();
	}
	@Bean
	public Crawler crawler(){
		return new Crawler();
	}
	
	
	
	/*
	@Bean
	public AwsQueueManager sqsManager() {
		return new AwsQueueManager();
	}
	
	@Bean
	public AwsS3Manager s3Manager() {
		return new AwsS3Manager();
	}*/
	
	
/*	@Bean
	public EntityManagerFactory movieEntityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(mySqlDataSource());
		// must call before afterPropertiesSet(), otherwise no persistence.xml found error
		factory.setPackagesToScan("com.tcl.t6.rshop.reviewer.model.movie");
		factory.afterPropertiesSet();

		return factory.getObject();
	}*/
	
}
