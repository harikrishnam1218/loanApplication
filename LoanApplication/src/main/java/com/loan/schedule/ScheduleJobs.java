package com.loan.schedule;


import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@EnableScheduling
public class ScheduleJobs {
	/*
	 * @Autowired private Job job;
	 * 
	 * @Autowired JobLauncher jobLauncher;
	 * 
	 * @Scheduled(fixedDelay = 5000) public void doJob() { JobParameters
	 * parameters=new JobParametersBuilder() .addString("JobId",
	 * String.valueOf(System.currentTimeMillis())) .addString("JobName",
	 * job.getName()) .toJobParameters(); try { JobExecution execution=
	 * jobLauncher.run(job, parameters);
	 * if(execution.getStatus().equals(BatchStatus.COMPLETED)){ execution.stop(); }
	 * } catch (JobExecutionAlreadyRunningException | JobRestartException |
	 * JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
	 * e.printStackTrace(); } }
	 */

}
