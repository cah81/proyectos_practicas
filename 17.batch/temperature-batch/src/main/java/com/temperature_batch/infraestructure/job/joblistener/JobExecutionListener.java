package com.temperature_batch.infraestructure.job.joblistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionListener implements org.springframework.batch.core.JobExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutionListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job started at: "+ jobExecution.getStartTime());
        System.out.println("Status of the Job: "+jobExecution.getStatus());
        LOGGER.info("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job Ended at: "+ jobExecution.getEndTime());
        System.out.println("Status of the Job: "+jobExecution.getStatus());

        LOGGER.info("afterJob: " + jobExecution.getStatus());

    }
}
