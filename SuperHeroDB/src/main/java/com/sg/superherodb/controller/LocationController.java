
package com.sg.superherodb.controller;

import com.sg.superherodb.dao.Dao;
import com.sg.superherodb.model.Hero;
import com.sg.superherodb.model.Location;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocationController {

    Dao dao;

    @Inject
    public LocationController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayLocationPage", method = RequestMethod.GET)
    public String displayLocationPage(Model model) {
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);

        return "location";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object

        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescription(request.getParameter("locationDescription"));
        location.setLocationAddress(request.getParameter("locationAddress"));
        location.setLocationCity(request.getParameter("locationCity"));
        location.setLocationState(request.getParameter("locationState"));
        String stringLatitude = request.getParameter("locationLatitude");
        BigDecimal bigDecimalLatitude = new BigDecimal(stringLatitude);
        location.setLocationLatitude(bigDecimalLatitude);
        String stringLongitude = request.getParameter("locationLongitude");
        BigDecimal bigDecimalLongitude = new BigDecimal(stringLongitude);
        location.setLocationLongitude(bigDecimalLongitude);
        location = dao.addLocation(location);

        return "redirect:displayLocationPage";

    }

    
    
    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIDParameter = request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDParameter);

        Location location = dao.getLocationById(locationID);
        
        model.addAttribute("location", location);

        return "locationDetails";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIDParameter = request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDParameter);
        dao.deleteLocationByID(locationID);
        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIDParameter = request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDParameter);
        Location location = dao.getLocationById(locationID);
        model.addAttribute("location", location);
        return "editLocationForm";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "editLocationForm";
        }
        
        dao.updateLocation(location);

        return "redirect:displayLocationPage";
    }
}
