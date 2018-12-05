/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherodb.controller;

import com.sg.superherodb.dao.Dao;
import com.sg.superherodb.model.Sighting;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nstep
 */

@Controller
public class IndexController {
    Dao dao;
    
    @Inject
    public IndexController(Dao dao) {
       this.dao = dao;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayLast10Sightings(Model model) {
       List<Sighting> sightings = dao.get10MostRecentSightings();
       model.addAttribute("sightings", sightings);
       
        
      return "index";  
    }
            
    
}
