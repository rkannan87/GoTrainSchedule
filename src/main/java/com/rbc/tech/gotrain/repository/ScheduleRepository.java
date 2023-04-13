package com.rbc.tech.gotrain.repository;

import com.rbc.tech.gotrain.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository  extends JpaRepository<Schedule,Integer> {

    Optional<List<Schedule>> findByLineIgnoreCase(String line);

    Optional<Schedule> findByLineAndDepartureIgnoreCase(String line, String departure);
}
