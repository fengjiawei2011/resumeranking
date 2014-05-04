package com.sjsu.cmpe282.resumeranking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sjsu.cmpe282.resumeranking.model.Resume;

public class ResumeRep {
	@Inject JdbcTemplate resumeJdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(ResumeRep.class);
			
	public List<Resume> getResumeId(String id) {
		List<Resume> resumes = new ArrayList<Resume>();
		RowMapper<Resume> rowMapper = new RowMapper<Resume>() {
			
			@Override
			public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resume resume = new Resume();
				resume.setId(rs.getInt("id"));
				resume.setResumeId(rs.getString("resume_id"));
				resume.setWorkingExp(rs.getString("resume_name"));
				resume.setResumeLink(rs.getString("resume_download_link"));
				resume.setResumeDownloadLink(rs.getString("resume_link"));
				resume.setBachelorSchool(1);
				resume.setDoctorSchool(2);
				resume.setMasterSchool(3);
				resume.setCity(rs.getString("city"));
				resume.setState(rs.getString("state"));
				resume.setCountry(rs.getString("country"));
				return resume;
			}
		};
		String sql = "select * from resumes limit 0, 100";
		resumes = resumeJdbcTemplate.query(sql, rowMapper);
		return resumes;
	}
}
