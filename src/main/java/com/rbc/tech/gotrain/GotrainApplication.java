package com.rbc.tech.gotrain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.tech.gotrain.domain.Schedule;
import com.rbc.tech.gotrain.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class GotrainApplication {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(GotrainApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ScheduleService scheduleService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeReference<List<Schedule>> typeReference = new TypeReference<>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/timetable.json");
			try {
				List<Schedule> schedules = mapper.readValue(inputStream, typeReference);
				scheduleService.save(schedules);
				log.info("Train Schedules Saved!");
			} catch (Exception e) {
				log.error("Unable to save train schedules: {}", e.getMessage(), e);
			}
		};

	}
}
