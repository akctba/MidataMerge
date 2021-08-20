package com.mi.datamerge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mi.datamerge.model.ReportModel;
import com.mi.datamerge.model.Summary;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            
            LOGGER.info(">>> JOB FINISHED! Time to verify the results");

            System.out.println("\n\nSummary: ");
            
            String querySum = "SELECT serviceGuid, count(1) as numberOfRecords "
             + "FROM report WHERE packetsServiced > 0 "
             + "GROUP BY serviceGuid";
            jdbcTemplate.query(querySum, (rs, row) -> new Summary(rs.getString(1), rs.getInt(2)))
            .forEach(line -> System.out.println(line));
            
            System.out.println("===================\n");
            
            String query = "SELECT clientAddress, clientGuid, requestTime, serviceGuid, retriesRequest, "
            		+ "packetsRequested, packetsServiced, maxHoleSize "
            		+ "FROM report WHERE packetsServiced > 0 "
            		+ "ORDER BY requestTime";
            jdbcTemplate.query(query, (rs, row) -> new ReportModel(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4),
            		rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)))
            .forEach(line -> LOGGER.info("Found < {} > in the database.", line));
            
            
            
            
        }
    }
}