package com.example.leave.controler.group;

import com.example.leave.dto.group.ImportantDateDTO;
import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.ImportantDates;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Medion on 2016-09-25.
 */
@Controller
public class ViewImportantDatesController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/getImportantDates", method = RequestMethod.GET)
    public @ResponseBody List<ImportantDateDTO> getImportantDates() {
        return groupEndpoint.getImportantDates();
    }

    @RequestMapping(value = "/viewImportantDates", method = RequestMethod.GET)
    public String importantDatesController() {
        return "group/viewImportantDates";
    }

    @RequestMapping(value = "/removeImportantDates", method = RequestMethod.POST)
    public @ResponseBody String removeImportantDates(@RequestBody String id) {
       groupEndpoint.removeImportantDate(id);
        return JSONParser.quote("Important date "+id+" removed");
    }
}
