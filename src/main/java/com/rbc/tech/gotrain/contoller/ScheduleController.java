package com.rbc.tech.gotrain.contoller;

import com.rbc.tech.gotrain.domain.Schedule;
import com.rbc.tech.gotrain.service.ScheduleService;
import com.rbc.tech.gotrain.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ScheduleController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService =scheduleService;
    }

    @GetMapping("/schedule")
    @Transactional(readOnly = true)
    public Iterable<Schedule> getSchedules(){
        return scheduleService.list();
    }

    @GetMapping("/schedule/{line}")
    @Transactional(readOnly = true)
    @ResponseBody
    public ResponseEntity<Iterable<Schedule>> getSchedulesByLine( @PathVariable("line") String line,@RequestParam(required=false) Map<String,String> queryParams){
        if (line == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (queryParams.get("departure") == null) {
            List<Schedule> scheduleList = StreamSupport.stream(scheduleService.findByLine(line).spliterator(), false)
                    .collect(Collectors.toList());
            if (scheduleList.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(scheduleList, HttpStatus.OK);
            }
        } else {
            String departure = queryParams.get("departure");
            if (!ValidationUtil.isValidDepartureTime(departure)){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            log.info("Departure Time: {}",departure.length());
            String formattedDepartureTime = ValidationUtil.formattedDepartureTime(departure);
            Schedule schedule = scheduleService.findByLineAndDeparture(line, formattedDepartureTime);
            if (schedule == null) {
                return new ResponseEntity<>(List.of(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(List.of(schedule), HttpStatus.OK);
            }
        }

    }
}
