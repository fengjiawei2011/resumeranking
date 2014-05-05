package com.sjsu.cmpe282.resumeranking.repository;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sjsu.cmpe282.resumeranking.model.Job;

public class JobRep {
	private static final Logger logger = LoggerFactory.getLogger(JobRep.class);
	@Inject JdbcTemplate resumeJdbcTemplate;
	
	public int saveJobPosted(Job job) {
		Object[] params = new Object[] {job.getCompany(), job.getJobTitle(), job.getJobDescription()};
		String sql = "insert into jobs(company,job_title,job_description) values(?,?,?)";
		return resumeJdbcTemplate.update(sql, params);
	}
}
