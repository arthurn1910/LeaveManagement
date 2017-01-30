package com.example.leave.controler.group.manager;

import com.example.leave.endpoint.group.GroupEndpoint;
import com.example.leave.entity.group.TeamGroup;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Medion on 2016-09-25.
 */
@Controller
public class CreateImportantDatesController {
    @Autowired
    GroupEndpoint groupEndpoint;

    @RequestMapping(value = "/createImportantDate", method = RequestMethod.POST)
    public @ResponseBody String createImportantDate(@RequestBody String date) {
        groupEndpoint.createImportantDate(date);
        return JSONParser.quote("Wazna data stworzona");
    }

    @RequestMapping(value = "/createImportantDateView", method = RequestMethod.GET)
    public String importantDatesView() {
        return "group/manager/createImportantDates";
    }

}