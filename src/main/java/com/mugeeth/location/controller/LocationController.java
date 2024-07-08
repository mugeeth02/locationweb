package com.mugeeth.location.controller;

import com.mugeeth.location.entities.Location;
import com.mugeeth.location.repos.LocationRepository;
import com.mugeeth.location.service.LocationService;
import com.mugeeth.location.util.EmailUtil;
import com.mugeeth.location.util.ReportUtil;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class LocationController {
    @Autowired
    LocationService service;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    LocationRepository repository;
    @Autowired
    ReportUtil reportUtil;
    @Autowired
    ServletContext sc; //save the image related to my web app, so jsp can use it
    @RequestMapping("/showCreate")  //http://localhost:9091/locationweb/showCreate
    String showCreate(){
        return "createLocation";
    }
    // when the user enters the data and when the click submit, it will go below codes
    //modelattributes maps the data into html like id,name,code
    @RequestMapping("/saveLoc")
    String saveLocation(@ModelAttribute Location location, ModelMap modelMap){
        Location savedLocation = service.saveLocation(location);
        String msg = "Location saved with id: " + savedLocation.getId(); //msg printing
        modelMap.addAttribute("msg",msg);//set key value pair and mapping to html page
        emailUtil.sendEmail("mugeeth06@gmail.com","LocationSaved","Location saved successfully and about to return a response");
        return "createLocation"; //forward to createLocation.html
    }

    @RequestMapping("/displayLocations")
    String displayLocations(ModelMap modelMap){
        List<Location> locations = service.getAllLocations(); //retriving from db
        modelMap.addAttribute("locations",locations);
        return "displayLocations"; //forward to displayLocations.html
    }


    // Delete Options
    @RequestMapping("/deleteLocation")
    String deleteLocation(@RequestParam int id,ModelMap modelMap){
        Location location = new Location();
        location.setId(id);
        service.deleteLocation(location);
        List<Location> locations = service.getAllLocations(); //retriving from db
        modelMap.addAttribute("locations",locations);
        return "displayLocations"; //forward to displayLocations.html
    }

    //Update Options

    @RequestMapping("/showUpdate")  //http://localhost:9091/locationweb/showCreate
    String showUpdate(@RequestParam int id,ModelMap modelMap){
        Location locations = service.getLocationById(id); //retriving from db
        modelMap.addAttribute("location",locations);
        return "updateLocation";
    }
    @RequestMapping("/updateLoc")
    String updateLocation(@ModelAttribute Location location, ModelMap modelMap){
        service.updateLocation(location);
        List<Location> locations = service.getAllLocations();
        modelMap.addAttribute("locations",locations);
        return "displayLocations"; //forward to displayLocations.html
    }
    @RequestMapping("generateReport")
    public String generateReport(){
        String path = sc.getRealPath("/"); //return as the web app route to store the generatepath for the jpeg file
        List<Object[]> data = repository.findTypeAndTypeCount();
        reportUtil.generatePieChart(path,data);
        return "report";
    }
}
