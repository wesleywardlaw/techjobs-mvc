package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController{

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(
            Model model,
            @RequestParam(name="searchType", required=false) String searchType,
            @RequestParam(name="searchTerm", required=false) String searchTerm
            ){
        ArrayList<HashMap<String, String>>jobs;
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm.toLowerCase());
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm.toLowerCase());
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchType", searchType);

        return "search";

    }

}
