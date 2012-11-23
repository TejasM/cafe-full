package org.springframework.integration.samples.cafe.scheduling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProcessOrders {
	
	@Autowired
	private JobLauncher launcher;
	
	
	@Autowired
	private Job job;
	
	
	@Scheduled(cron="0 0/5 * * * ?")
	public void processDailyOrders() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, IOException{
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("today", new Date());
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
		builder.addString("input.file", "file:json-" + format.format(new Date()) + ".txt");
		builder.addString("output.file", "file:processedorders-" + format.format(new Date()) + ".txt");
        launcher.run(job, builder.toJobParameters());
		System.out.println("Processing");
	}	
}
