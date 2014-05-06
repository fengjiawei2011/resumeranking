package com.sjsu.cmpe282.resumeranking.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjsu.cmpe282.resumeranking.model.Job;
import com.sjsu.cmpe282.resumeranking.model.Resume;
import com.sjsu.cmpe282.resumeranking.repository.JobRep;
import com.sjsu.cmpe282.resumeranking.repository.ResumeRep;
import com.sjsu.cmpe282.resumeranking.service.Crawler;

@Controller
public class ResumeRankingController {
	
private static final Logger logger = LoggerFactory.getLogger(ResumeRankingController.class);
	@Inject ResumeRep resumeRep;
	@Inject JobRep jobRep;
	@Inject Crawler crawler;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
			return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("keywords") String keywords,Model model) {
		logger.debug(keywords);
		model.addAttribute("resumes", crawler.getSeaschResult(keywords.replace(" ", "-")));
		return "results";
	}
	
	@RequestMapping(value = "/resumes/{keywords}", method = RequestMethod.GET)
	public @ResponseBody String search1(@PathVariable("keywords") String keywords) {
			logger.debug("in search");
			String[] kywords = keywords.split("-");
			return kywords.length+"";
	}
	
	@RequestMapping(value = "/resumes", method = RequestMethod.GET)
	public @ResponseBody List<Resume> search2(@RequestParam("jobTitle") String jobTitle) {
			logger.debug("in searching resumes");
			return resumeRep.getTopResume(50);
	}
	
	@RequestMapping(value = "/postjob", method = RequestMethod.POST)
	public String postJob(@RequestParam("company") String company, 
									 @RequestParam("job_des") String jobDes,
									 @RequestParam("job_title") String jobTitle, Model model){
		Job job = new Job();
		job.setCompany(company);
		job.setJobTitle(jobTitle);
		job.setJobDescription(jobDes);
		if(jobRep.saveJobPosted(job) == 1) 
			logger.debug("save job posted successfully");
		
		model.addAttribute("resumes", resumeRep.getTopResume(10));
		return "results";
	} 

}
