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

import com.sjsu.cmpe282.resumeranking.model.Job;
import com.sjsu.cmpe282.resumeranking.model.Resume;

public class ResumeRep {
	@Inject JdbcTemplate resumeJdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(ResumeRep.class);
			
	public List<Resume> getTopResume(int topNum) {
		List<Resume> resumes = new ArrayList<Resume>();
		RowMapper<Resume> rowMapper = new RowMapper<Resume>() {
			
			@Override
			public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resume resume = new Resume();
				resume.setId(rs.getInt("id"));
				resume.setResumeName(rs.getString("resume_name"));
				resume.setResumeId(rs.getString("resume_id"));
				resume.setWorkingExp(rs.getString("working_exp"));
				resume.setResumeLink(rs.getString("resume_link"));
				resume.setResumeDownloadLink(rs.getString("resume_download_link"));
				resume.setBachelorSchool(1);
				resume.setDoctorSchool(2);
				resume.setMasterSchool(3);
				resume.setCity(rs.getString("city"));
				resume.setState(rs.getString("state"));
				resume.setCountry(rs.getString("country"));
				resume.setKeywords(rs.getString("keywords"));
				return resume;
			}
		};
		String sql = "select * from resumes where is_duplicated=0 limit 0,"+ topNum ;
		resumes = resumeJdbcTemplate.query(sql, rowMapper);
		return resumes;
	}
	
	
	public List<Resume> getTopResume(String id, int topNum) {
		List<Resume> resumes = new ArrayList<Resume>();
		RowMapper<Resume> rowMapper = new RowMapper<Resume>() {
			
			@Override
			public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resume resume = new Resume();
				resume.setId(rs.getInt("id"));
				resume.setResumeName(rs.getString("resume_name"));
				resume.setResumeId(rs.getString("resume_id"));
				resume.setWorkingExp(rs.getString("working_exp"));
				resume.setResumeLink(rs.getString("resume_link"));
				resume.setResumeDownloadLink(rs.getString("resume_download_link"));
				resume.setBachelorSchool(1);
				resume.setDoctorSchool(2);
				resume.setMasterSchool(3);
				resume.setCity(rs.getString("city"));
				resume.setState(rs.getString("state"));
				resume.setCountry(rs.getString("country"));
				resume.setKeywords(rs.getString("keywords"));
				return resume;
			}
		};
		String sql = "select * from resumes  where is_duplicated=0 and resume_id='"+id+"'" ;
		resumes = resumeJdbcTemplate.query(sql, rowMapper);
		return resumes;
	}
	
	public int deleteReuse(String id) {
		Object[] params = new Object[] {id};
		String sql = "update resumes set is_duplicated = 1 where resume_id=?";
		return resumeJdbcTemplate.update(sql, params);
	}
	

}
