package com.sjsu.cmpe282.resumeranking.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.cmpe282.resumeranking.model.Job;
import com.sjsu.cmpe282.resumeranking.model.Resume;
import com.sjsu.cmpe282.resumeranking.repository.JobRep;
import com.sjsu.cmpe282.resumeranking.repository.ResumeRep;

@Controller
public class ResumeRankingController {
	
private static final Logger logger = LoggerFactory.getLogger(ResumeRankingController.class);
	@Inject ResumeRep resumeRep;
	@Inject JobRep jobRep;
	
	@RequestMapping(value = "/resumes/{keywords}", method = RequestMethod.GET)
	public @ResponseBody String search1(@PathVariable("keywords") String keywords) {
			logger.debug("in search");
			String[] kywords = keywords.split("-");
			return kywords.length+"";
	}
	
	@RequestMapping(value = "/resumes", method = RequestMethod.GET)
	public @ResponseBody List<Resume> search2(@RequestParam("jobTitle") String jobTitle) {
			logger.debug("in searching resumes");
			return resumeRep.getResumeId("1");
	}
	
	@RequestMapping(value = "/postjob", method = RequestMethod.POST)
	public @ResponseBody Job postJob(@RequestParam("company") String company, 
									 @RequestParam("job_des") String jobDes,
									 @RequestParam("job_title") String jobTitle){
		Job job = new Job();
		job.setCompany(company);
		job.setJobTitle(jobTitle);
		job.setJobDescription(jobDes);
		//int result = jobRep.saveJobPosted(job);
		logger.debug("save job into db");
		return job;
	} 

}
