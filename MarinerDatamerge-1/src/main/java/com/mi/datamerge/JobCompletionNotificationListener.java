package com.mi.datamerge;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mi.datamerge.model.ReportModel;
import com.mi.datamerge.model.Summary;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;
	
	@Value("${file.output:output.csv}")
    private String fileOutput;

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
					+ "FROM report WHERE packetsServiced > 0 " + "GROUP BY serviceGuid";
			jdbcTemplate.query(querySum, (rs, row) -> new Summary(rs.getString(1), rs.getInt(2)))
					.forEach(line -> System.out.println(line));

			System.out.println("===================\n");

			String query = "SELECT clientAddress, clientGuid, requestTime, serviceGuid, retriesRequest, "
					+ "packetsRequested, packetsServiced, maxHoleSize FROM report WHERE packetsServiced <> 0 "
					+ "ORDER BY requestTime";
			List<ReportModel> results = jdbcTemplate.query(query,
					(rs, row) -> new ReportModel(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4),
							rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			// results.forEach(line -> LOGGER.info("Found < {} > in the database.", line));

			ByteArrayInputStream reportToCSV = CsvHelper.reportToCSV(results);

			try {
				// Taking the JAR path to export
				String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
				jarPath = jarPath.substring(0, jarPath.lastIndexOf("/") + 1) + fileOutput;

				LOGGER.warn(">>> Result report path: {}", jarPath);

				File file = new File(jarPath);

				Files.copy(reportToCSV, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				LOGGER.error("Error exporting report {}", e);
			}

		}
	}
}