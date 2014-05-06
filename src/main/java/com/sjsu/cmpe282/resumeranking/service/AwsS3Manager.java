package com.sjsu.cmpe282.resumeranking.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class AwsS3Manager {

	public static final int S3_URL_EXP_MINUTE = 180;

	private final static String ACCESS_KEY_ID = "AKIAIH55EOOYUNENJPEQ";
	private final static String SECRET_ACCESS_KEY = "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";
	private static String bucketName = "resume-ranking-dataset";
	private String resultFolder = "resume/";

	public static void main(String[] args) {
		AwsS3Manager s3 = new AwsS3Manager();
		s3.setResultFolder("hottech");
		String aa = new String(s3.getFileBytes("hottest_techs.txt"));
		System.out.println(aa);
	}
	
	public AwsS3Manager() {

	}

	public AwsS3Manager(String folder) {
		resultFolder = folder + "/";
	}

	private static Logger logger = LoggerFactory.getLogger(AwsS3Manager.class);

	public void deteFile(String fileName) {
		AmazonS3 s3 = getS3();
		s3.deleteObject(bucketName, resultFolder + fileName);
	}

	public byte[] getFileBytes(String fileName) {
		AmazonS3 s3 = getS3();
		logger.debug("getting file from bucket: " + bucketName + " in " + resultFolder + fileName);
		S3Object obj = s3.getObject(bucketName, resultFolder + fileName);
		try {
			return IOUtils.toByteArray(obj.getObjectContent());
		} catch (IOException e) {
			return null;
		}
	}

	public void putFile(String fileName, InputStream is, long size) {
		AmazonS3 s3 = getS3();
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(size);
		GetObjectRequest getRequest = new GetObjectRequest(bucketName, resultFolder + fileName);
		s3.getObject(getRequest);
		s3.putObject(bucketName, resultFolder + fileName, is, meta);
	}

	public void putPublicFile(String fileName, InputStream is, long size) {
		AmazonS3 s3 = getS3();
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(size);
		PutObjectRequest put = new PutObjectRequest(bucketName, resultFolder + fileName, is, meta);
		put.setCannedAcl(CannedAccessControlList.PublicRead);		
		s3.putObject(put);
	}

	private AmazonS3 getS3() {
		return myS3 == null ? new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY)) : myS3;
	}

	private AmazonS3 myS3;

	public void setPublishS3() {
		myS3 = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		bucketName = "rstudio";
	}

	public void setResultFolder(String folder) {
		resultFolder = folder + "/";
	}
	
}
