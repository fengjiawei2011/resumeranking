/*package com.sjsu.cmpe282.resumeranking.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;


public class Ranker {
	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.setProperty("fs.default.name", " https://s3.amazonaws.com/resume-ranking-dataset/");
			props.setProperty("mapred.job.tracker", "hadoop@ec2-54-82-161-128.compute-1.amazonaws.com:9001");
			PigServer pigServer = new PigServer(ExecType.MAPREDUCE, props);
			runMyQuery(pigServer, "D:\\Documents\\KuaiPan\\resume-dataset-txt\\");
		} catch (ExecException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void runMyQuery(PigServer pigServer, String inputFile) {
		
		
		try {
			pigServer.registerQuery(" lines = LOAD '"+inputFile+"'  using PigStorage(' ','-tagsource') AS (id:chararray, line:chararray);");
			pigServer.registerQuery("C = FOREACH lines GENERATE TOMAP(id, line) as c;");
			Iterator<Tuple> i = pigServer.openIterator("C");
			HashMap<String, String> map = new HashMap<String, String>();
			while (i.hasNext()) {
				String key = DataType.toString(i.next().get(0)); 
				System.out.println("key=" + key);
	        }
			pigServer1.store("C", "store e into 'TEST' "
					+ " using  org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver',"
					+ "'jdbc:mysql://127.0.0.1/resume','root','password',"
					+ "'insert into TEST (name,age,desg) values (?,?,?)');");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
*/