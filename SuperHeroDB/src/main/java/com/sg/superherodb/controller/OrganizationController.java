/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherodb.controller;
import com.sg.superherodb.dao.Dao;
import com.sg.superherodb.model.Hero;
import com.sg.superherodb.model.Location;
import com.sg.superherodb.model.Organization;
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
 * @author ward
 */
/**
 *
 * @author nstep
 */

@Controller
public class OrganizationController {
    Dao dao;

    @Inject
    public OrganizationController(Dao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayOrganizationPage", method = RequestMethod.GET)
    public String displayOrganizationPage(Model model) {
        List<Organization> organizationList = dao.getAllOrganizations();
        List<Location> locations = dao.getAllLocations();
        List<Hero> heros = dao.getAllHeros();
        model.addAttribute("locations", locations);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("heros", heros);

        return "organization";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request, Model model) {
        Organization organization = new Organization();
        organization.setOrganizationName(request.getParameter("organizationName"));
        
        organization.setOrganizationDescription(request.getParameter("organizationDescription"));
        
        
        int locationID = 0;
        locationID = Integer.parseInt(request.getParameter("organizationLocation"));
        Location location = dao.getLocationById(locationID);
        organization.setLocation(location);
        
        organization.setOrganizationNumber(request.getParameter("organizationNumber"));
        organization.setOrganizationEmail(request.getParameter("organizationEmail"));
        
        
        String[] heroIDs = request.getParameterValues("organizationHeros");
        List<Hero> heros = new ArrayList<>();
        for (String hID : heroIDs) {
            int heroID = Integer.parseInt(hID);
            heros.add(dao.getHero(heroID));
        }
        organization.setHeros(heros);
        dao.addOrganization(organization);
        
        model.addAttribute("organization", organization);
        model.addAttribute("loction", location);
        model.addAttribute("heros", heros);
        return "redirect:displayOrganizationPage";
    }
    
    
    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayorganizationDetails(HttpServletRequest request, Model model) {
        String organizationIDParameter = request.getParameter("organizationID");
        int organizationID = Integer.parseInt(organizationIDParameter);
        Organization organization = dao.getOrgById(organizationID);
        model.addAttribute("organization", organization);

        return "organizationDetails";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIDParameter = request.getParameter("organizationID");
        int organizationID = Integer.parseInt(organizationIDParameter);
        dao.deleteOrganization(organizationID);
        return "redirect:displayOrganizationPage";
    }
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIDParameter = request.getParameter("organizationID");
        int organizationID = Integer.parseInt(organizationIDParameter);
        Organization organization = dao.getOrgById(organizationID);
        List<Location> locations = dao.getAllLocations();

        model.addAttribute("locations", locations);
        List<Hero> heros = dao.getAllHeros();
        model.addAttribute("heros", heros);
        model.addAttribute("organization", organization);
        return "editOrganizationForm";
    }
    
    
    //public String createSighting(HttpServletRequest request, Model model) {
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editOrganizationForm";
        }
        //get the location id, use that to get location object
        //get the array of hero id's and turn that into a list of hero objects, then 
        //insert

        //date to be take in
        String organizationIDParameter = request.getParameter("organizationID");
        int organizationID = Integer.parseInt(organizationIDParameter);
        organization = dao.getOrgById(organizationID);
        
        
        
        //locationt to take in.
        String organizationName = request.getParameter("organizationName");
     
        organization.setOrganizationName(organizationName);
        String orgizationDescription = request.getParameter(("organizationDescription"));
       organization.setOrganizationDescription(orgizationDescription);
       organization.setOrganizationNumber(request.getParameter("organizationNumber"));
        organization.setOrganizationEmail(request.getParameter("organizationEmail"));
        int locationID = Integer.parseInt(request.getParameter("organizationLocation"));
        Location location = dao.getLocationById(locationID);
        organization.setLocation(location);

        // list of heroes to be taken in
        String[] heroIDs = request.getParameterValues("organizationHeros");
        List<Hero> heros = new ArrayList<>();
        for (String hID : heroIDs) {
            int heroID = Integer.parseInt(hID);
            heros.add(dao.getHero(heroID));
        }
        organization.setHeros(heros);
        dao.updateOrganization(organization);

        return "redirect:displayOrganizationPage";
    }
    
    
    
    
    
}
