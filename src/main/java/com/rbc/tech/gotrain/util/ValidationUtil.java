package com.rbc.tech.gotrain.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ValidationUtil {

    protected static final Logger log = LoggerFactory.getLogger(ValidationUtil.class);
    /*
    The departure time should accept times in two different formats:
    • 24-hour "military" format: a single, three or four-digit number, with the hours and
    minutes in sequence, e.g. 700 (7 am), 1215 (12:15 pm), 1755 (5:55 pm).
    • 12-hour format: hours and minutes separated by a colon and ending with
    either am or pm, like 12:00am, 3:40pm, 7:55am. For simplicity, in this format the colon
    and the "am/pm" bits are mandatory, so times like 6am or 11:00 are considered
    invalid.
    If departure is not a valid 12 or 24-hour format time, the service should respond with
    HTTP 400 (Bad Request).
     */
    public static boolean isValidDepartureTime(String departureTime) {
        if(departureTime.contains(":") && (!validateAMPMTime(departureTime))){
            return false;
        }
        Date time = null;
        try {
            if (departureTime.length() == 3){
                SimpleDateFormat sdf = new SimpleDateFormat("hmm");
                time = sdf.parse(departureTime);
            } else if (departureTime.length() == 4) {
                SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
                time = sdf.parse(departureTime);
            } else if (departureTime.length() == 6) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("h:mma", Locale.ENGLISH);
                time = sdf1.parse(departureTime);
            } else if (departureTime.length() == 7) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mma", Locale.ENGLISH);
                time = sdf1.parse(departureTime);
            }
            log.info("Valid Departure Time :{}",time);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    private static boolean validateAMPMTime(String departureTime) {
        return departureTime.contains("AM") || departureTime.contains("PM") || departureTime.contains("am") || departureTime.contains("pm");
    }

    public static String formattedDepartureTime(String departure) {
        Date time = null;
        String formattedTime = null;
        try {
            if (departure.length() == 3){
                SimpleDateFormat sdf = new SimpleDateFormat("hmm");
                time = sdf.parse(departure);
            } else if (departure.length() == 4) {
                SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
                time = sdf.parse(departure);
            } else if (departure.length() == 6) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("h:mma", Locale.ENGLISH);
                time = sdf1.parse(departure);
            } else if (departure.length() == 7) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mma", Locale.ENGLISH);
                time = sdf1.parse(departure);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm", Locale.ENGLISH);
            formattedTime = sdf.format(time);
            if (departure.length() == 3 || departure.length() == 6){
                SimpleDateFormat sdf1 = new SimpleDateFormat("Hmm", Locale.ENGLISH);
                formattedTime = sdf1.format(time);
            }
            log.info("Departure formatted Time: {}",formattedTime);
        } catch (ParseException exception) {
            log.error("Exception while parsing date: {}", exception.getMessage(), exception);
        }
        return formattedTime;
    }


}
