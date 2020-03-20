package com.loan;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.loan.exception.BatchJobException;

@SpringBootApplication
@EnableScheduling
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}


	@Autowired
	private Job job;
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Scheduled(fixedDelay = 50000)
	public void doJob() {
		JobParameters parameters=new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.addString("JobName", job.getName())
				.toJobParameters();
		
		try {
			JobExecution	execution = jobLauncher.run(job, parameters);
			if(execution.getStatus().equals(BatchStatus.COMPLETED)){
				execution.stop();
			}
		} catch (JobExecutionAlreadyRunningException e) {
			throw new BatchJobException("Job Execution AlreadyRunning Exception",e);
		} catch (JobRestartException e) {
			throw new BatchJobException("Job Restart Exception",e);
		} catch (JobInstanceAlreadyCompleteException e) {
			throw new BatchJobException("Job Instance Already Complete Exception",e);
		} catch (JobParametersInvalidException e) {
			throw new BatchJobException("Job Parameters Invalid Exception",e);
		}
	
		
	}
	
}
