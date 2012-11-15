package org.springframework.integration.samples.cafe.scheduling;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.samples.cafe.dao.DeliveryDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.QueueingConsumer.Delivery;

@Service
public class WriteRevenue {
	
	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private JpaPagingItemReader<Delivery> reader;
	
	@Autowired
	private Job job;
	
	private long id = 0;
	
	@Autowired
	private DeliveryDao dao;
	
	@Scheduled(cron="*/5 * * * * ?")
	public void writeRevenueEveryDay() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		String query = "Select d from Delivery d where d.id >= " + id;
		id = dao.count();
		reader.setQueryString(query);
		JobParametersBuilder builder = new JobParametersBuilder();
        JobExecution jobExecution = launcher.run(job,
             builder.toJobParameters());
		System.out.println("Processing");
	}	
}
