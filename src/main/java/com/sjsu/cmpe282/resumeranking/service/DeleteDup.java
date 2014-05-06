package com.sjsu.cmpe282.resumeranking.service;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteDup {

	public static void main(String[] args) throws InterruptedException {
		int counter = 0;
		File  folder = new File("D:\\Documents\\KuaiPan\\resueme-dataset-pdf");
		Pattern p = Pattern.compile("\\-(.*?)\\.");
		File[] resumes = folder.listFiles();
		HashSet<String> hashSet = new HashSet<String>();
		for(int i = 0; i < resumes.length; i++){
			System.out.println(resumes[i].getName());
			Matcher m = p.matcher(resumes[i].getName());
			if(m.find()){
				String name = m.group(1);
				if(!hashSet.contains(name)){
					System.out.println(name);
					hashSet.add(name);
					counter ++; 
					String newName = counter+"-"+name+".txt";
					File folder_des = new File("D:\\Documents\\KuaiPan\\resume-dataset-txt\\"+newName);
					resumes[i].renameTo(folder_des);
				}	
			}	
			Thread.sleep(20);
		}		
	}
	
	
	public void deleteInDB(String id){
		
	}
}
