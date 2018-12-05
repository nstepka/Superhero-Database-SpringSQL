/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherodb.controller;

import com.sg.superherodb.dao.Dao;
import com.sg.superherodb.model.Hero;
import com.sg.superherodb.model.Location;
import com.sg.superherodb.model.Sighting;
import java.time.LocalDate;
import java.util.ArrayList;
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

/**
 *
 * @author nstep
 */
@Controller
public class SightingController {

    Dao dao;

    @Inject
    public SightingController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displaySightingPage", method = RequestMethod.GET)
    public String displaySightingPage(Model model) {
        List<Sighting> sightingList = dao.getAllSightings();
        List<Location> locations = dao.getAllLocations();
        List<Hero> heros = dao.getAllHeros();
        model.addAttribute("locations", locations);
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("heros", heros);

        return "sighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, Model model) {
        Sighting sighting = new Sighting();
        LocalDate sightDate = LocalDate.parse(request.getParameter("sightingDate"));
        String locationName = request.getParameter("locationName");
        int locationID = 0;
        sighting.setSightingDate(sightDate);

        locationID = Integer.parseInt(request.getParameter("sightingLocation"));
        Location location = dao.getLocationById(locationID);
        sighting.setLocationID(location);
        String[] heroIDs = request.getParameterValues("sightingHeros");
        List<Hero> heros = new ArrayList<>();
        for (String hID : heroIDs) {
            int heroID = Integer.parseInt(hID);
            heros.add(dao.getHero(heroID));
        }
        sighting.setHero(heros);
        dao.addSighting(sighting);
        model.addAttribute("sighting", sighting);
        model.addAttribute("loction", location);
        model.addAttribute("heros", heros);
        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIDParameter = request.getParameter("sightingID");
        int sightingID = Integer.parseInt(sightingIDParameter);
        Sighting sighting = dao.getSightingById(sightingID);

        model.addAttribute("sighting", sighting);

        return "sightingDetails";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIDParameter = request.getParameter("sightingID");
        int sightingID = Integer.parseInt(sightingIDParameter);
        dao.deleteSightingById(sightingID);
        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIDParameter = request.getParameter("sightingID");
        int sightingID = Integer.parseInt(sightingIDParameter);
        Sighting sighting = dao.getSightingById(sightingID);
        List<Location> locations = dao.getAllLocations();

        model.addAttribute("locations", locations);
        List<Hero> heros = dao.getAllHeros();
        model.addAttribute("heros", heros);
        model.addAttribute("sighting", sighting);
        return "editSightingForm";
    }
    
    
    //public String createSighting(HttpServletRequest request, Model model) {
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editSightingForm";
        }
        //get the location id, use that to get location object
        //get the array of hero id's and turn that into a list of hero objects, then 
        //insert

        //date to be take in
        String sightingIDParameter = request.getParameter("sightingID");
        int sightingID = Integer.parseInt(sightingIDParameter);
        sighting = dao.getSightingById(sightingID);
        LocalDate sightDate = LocalDate.parse(request.getParameter("sightingDate"));
        sighting.setSightingDate(sightDate);
        //locationt to take in.
        String locationName = request.getParameter("locationName");
        int locationID = 0;
        locationID = Integer.parseInt(request.getParameter("sightingLocation"));
        Location location = dao.getLocationById(locationID);
        sighting.setLocationID(location);

        // list of heroes to be taken in
        String[] heroIDs = request.getParameterValues("sightingHeros");
        List<Hero> heros = new ArrayList<>();
        for (String hID : heroIDs) {
            int heroID = Integer.parseInt(hID);
            heros.add(dao.getHero(heroID));
        }
        sighting.setHero(heros);

        dao.updateSighting(sighting);

        return "redirect:displaySightingPage";
    }

}
