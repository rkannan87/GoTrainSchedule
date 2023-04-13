package com.rbc.tech.gotrain.service;

import com.rbc.tech.gotrain.domain.Schedule;
import com.rbc.tech.gotrain.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Iterable<Schedule> list() {
        return scheduleRepository.findAll();
    }

    public void save(List<Schedule> schedules) {
        scheduleRepository.saveAll(schedules);
    }

    public Iterable<Schedule> findByLine(String line) {
        log.debug("ScheduleService : findByLine for line "+line);
        Optional<List<Schedule>> scheduleOptional =  scheduleRepository.findByLineIgnoreCase(line);
        return scheduleOptional.orElse(null);
    }

    public Schedule findByLineAndDeparture(String line, String departure) {
        log.debug("ScheduleService : findByLineAndDeparture for line "+line+" and departure "+departure);
        Optional<Schedule> scheduleOptional =  scheduleRepository.findByLineAndDepartureIgnoreCase(line, departure);
        return scheduleOptional.orElse(null);
    }
}
