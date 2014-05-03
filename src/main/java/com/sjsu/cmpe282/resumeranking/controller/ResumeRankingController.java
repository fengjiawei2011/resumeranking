package com.sjsu.cmpe282.resumeranking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResumeRankingController {
	
private static final Logger logger = LoggerFactory.getLogger(ResumeRankingController.class);
	
	
	@RequestMapping(value = "/resumes/{keywords}", method = RequestMethod.GET)
	public @ResponseBody String search(@PathVariable("keywords") String keywords) {
			logger.debug("in search");
			return keywords;
	}
	
	@RequestMapping(value = "/postjob", method = RequestMethod.GET)
	public @ResponseBody String  postJob(){
			return "post job";
	}

}
