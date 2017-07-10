package com.zenvia.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
	}


	/*

	@Bean
	public Report test(){
		try {

			ObjectMapper mapper = new ObjectMapper();
			ReportGeneric generic = mapper.readValue(Resources.getResource("layout_a.txt"), ReportGeneric.class);

			Report report = new Report.Builder()
												  	.name(generic.getName())
													.header(generic.getHeader())
												  	.build();

			return report;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	*/
}
