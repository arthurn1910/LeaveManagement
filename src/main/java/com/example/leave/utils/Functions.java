package com.example.leave.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Medion on 2016-09-27.
 */
@Component
public class Functions {
    public Date removeTimeInDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
