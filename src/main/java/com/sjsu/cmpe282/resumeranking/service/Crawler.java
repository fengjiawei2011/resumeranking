package com.sjsu.cmpe282.resumeranking.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import com.sjsu.cmpe282.resumeranking.model.Resume;
import com.sjsu.cmpe282.resumeranking.repository.ResumeRep;

public class Crawler {
	@Inject ResumeRep resumeRep;
	private final static String PATH = "D:\\Documents\\KuaiPan\\resume-dataset-txt";
	public static void main(String[] args) {
	/*	ArrayList<String> resumeid = getSeaschResult("software-engineer");
		for(String id : resumeid){
			System.out.println(id);
		}*/
	}

	public ArrayList<Resume> getSeaschResult(String keyword) {
		ArrayList<String> resumeid = new ArrayList<String>();
		String keywordOne = keyword.split("-")[0];
		String keywordTwo = keyword.split("-")[1];
		boolean flagOne = false;
		boolean flagTwo = false;
		File folder = new File(PATH);
		BufferedReader br = null;
		int counter = 0;
		try {
			File[] files = folder.listFiles();
			String line;
			for (File f : files) {
				br = new BufferedReader(new FileReader(f.getAbsolutePath()));
				//System.out.println(f.getAbsolutePath());
				while ((line = br.readLine()) != null) {
					if (line.indexOf(keywordOne) >= 0) {
						flagOne = true;
					}
					if (line.indexOf(keywordTwo) >= 0) {
						flagTwo = true;
					}
					if (flagOne && flagTwo) {
						resumeid.add(f.getName().replace(".txt", ""));
						counter++;
						flagOne = false;
						flagTwo = false;
						break;
					}

				}
				if (counter == 100) {
					ArrayList<Resume> resumes = new ArrayList<Resume>();
					
					for(String s : resumeid){
						resumes.add(resumeRep.getTopResume(s, 0).get(0));
					}
					
					return resumes;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}
