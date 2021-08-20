package com.mi.datamerge;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.mi.datamerge.model.ReportModel;

public class CsvHelper {

	public static ByteArrayInputStream reportToCSV(List<ReportModel> result) {
		final CSVFormat format = CSVFormat.DEFAULT.withHeader("client-address", "client-guid", "request-time",
				"service-guid", "retries-request", "packets-requested", "packets-serviced", "max-hole-size");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
		try (

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (ReportModel rep : result) {
				List<String> data = Arrays.asList(rep.getClientAddress(), rep.getClientGuid(),
						df.format(rep.getRequestTime()), rep.getServiceGuid(), String.valueOf(rep.getRetriesRequest()),
						String.valueOf(rep.getPacketsRequested()), String.valueOf(rep.getPacketsServiced()),
						String.valueOf(rep.getPacketsServiced()));

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to export data to CSV file: " + e.getMessage());
		}
	}

}
