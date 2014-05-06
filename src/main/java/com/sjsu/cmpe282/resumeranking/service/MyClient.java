package com.sjsu.cmpe282.resumeranking.service;
 
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.elasticmapreduce.model.*;
import com.amazonaws.services.elasticmapreduce.util.StepFactory;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
 
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
/**
 * Created with IntelliJ IDEA.
 * User: pascal
 * Date: 22-07-13
 * Time: 20:45
 */
public class MyClient {
 
    private static final String HADOOP_VERSION = "1.0.3";
    private static final int INSTANCE_COUNT = 1;
    private static final String INSTANCE_TYPE = InstanceType.M1Small.toString();
    private static final UUID RANDOM_UUID = UUID.randomUUID();
    private static final String FLOW_NAME = "dictionary-" + RANDOM_UUID.toString();
    private static final String BUCKET_NAME = "map-reduce-intro";
    private static final String S3N_HADOOP_JAR =
            "s3n://" + BUCKET_NAME + "/job/MapReduce-1.0-SNAPSHOT.jar";
    private static final String S3N_LOG_URI = "s3n://" + BUCKET_NAME + "/log/";
    private static final String[] JOB_ARGS =
            new String[]{"s3n://" + BUCKET_NAME + "/input/input.txt",
                    "s3n://" + BUCKET_NAME + "/result/" + FLOW_NAME};
    private static final List<String> ARGS_AS_LIST = Arrays.asList(JOB_ARGS);
    private static final List<JobFlowExecutionState> DONE_STATES = Arrays
            .asList(new JobFlowExecutionState[]{JobFlowExecutionState.COMPLETED,
                    JobFlowExecutionState.FAILED,
                    JobFlowExecutionState.TERMINATED});
    static AmazonS3 s3;
    static AmazonElasticMapReduceClient emr;
 
    private static void init() throws Exception {
        AWSCredentials credentials = new PropertiesCredentials(
                MyClient.class.getClassLoader().getResourceAsStream("AwsCredentials.properties"));
        s3 = new AmazonS3Client(credentials);
        emr = new AmazonElasticMapReduceClient(credentials);
        emr.setRegion(Region.getRegion(Regions.EU_WEST_1));
    }
 
    private static JobFlowInstancesConfig configInstance() throws Exception {
 
        // Configure instances to use
        JobFlowInstancesConfig instance = new JobFlowInstancesConfig();
        instance.setHadoopVersion(HADOOP_VERSION);
        instance.setInstanceCount(INSTANCE_COUNT);
        instance.setMasterInstanceType(INSTANCE_TYPE);
        instance.setSlaveInstanceType(INSTANCE_TYPE);
        // instance.setKeepJobFlowAliveWhenNoSteps(true);
        // instance.setEc2KeyName("4synergy_palma");
 
        return instance;
    }
 
    private static void runCluster() throws Exception {
        // Configure the job flow
        RunJobFlowRequest request = new RunJobFlowRequest(FLOW_NAME, configInstance());
        request.setLogUri(S3N_LOG_URI);
 
        // Configure the Hadoop jar to use
        HadoopJarStepConfig jarConfig = new HadoopJarStepConfig(S3N_HADOOP_JAR);
        jarConfig.setArgs(ARGS_AS_LIST);
 
        try {
 
            StepConfig enableDebugging = new StepConfig()
                    .withName("Enable debugging")
                    .withActionOnFailure("TERMINATE_JOB_FLOW")
                    .withHadoopJarStep(new StepFactory().newEnableDebuggingStep());
 
            StepConfig runJar =
                    new StepConfig(S3N_HADOOP_JAR.substring(S3N_HADOOP_JAR.indexOf('/') + 1),
                            jarConfig);
 
            request.setSteps(Arrays.asList(new StepConfig[]{enableDebugging, runJar}));
 
            //Run the job flow
            RunJobFlowResult result = emr.runJobFlow(request);
 
            //Check the status of the running job
            String lastState = "";
 
            STATUS_LOOP:
            while (true) {
                DescribeJobFlowsRequest desc =
                        new DescribeJobFlowsRequest(
                                Arrays.asList(new String[]{result.getJobFlowId()}));
                DescribeJobFlowsResult descResult = emr.describeJobFlows(desc);
                for (JobFlowDetail detail : descResult.getJobFlows()) {
                    String state = detail.getExecutionStatusDetail().getState();
                    if (isDone(state)) {
                        System.out.println("Job " + state + ": " + detail.toString());
                        break STATUS_LOOP;
                    } else if (!lastState.equals(state)) {
                        lastState = state;
                        System.out.println("Job " + state + " at " + new Date().toString());
                    }
                }
                Thread.sleep(10000);
            }
        } catch (AmazonServiceException ase) {
            System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
        }
    }
 
    public static boolean isDone(String value) {
        JobFlowExecutionState state = JobFlowExecutionState.fromValue(value);
        return DONE_STATES.contains(state);
    }
 
    public static void main(String[] args) {
        try {
            init();
            runCluster();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
}