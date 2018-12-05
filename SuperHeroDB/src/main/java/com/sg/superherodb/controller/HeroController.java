
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherodb.controller;

import com.sg.superherodb.dao.Dao;
import com.sg.superherodb.model.Hero;
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
@Controller
public class HeroController {
    Dao dao;
    
    @Inject
    public HeroController(Dao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayHeroPage", method = RequestMethod.GET)
    public String displayHeroPage(Model model) {
        List<Hero> heroList = dao.getAllHeros();
        model.addAttribute("heroList", heroList);
        
        return "hero";
    }
    
    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Contact
        // object
        Hero hero = new Hero();
        hero.setHeroName(request.getParameter("heroName"));
        hero.setDescription(request.getParameter("heroDiscription"));
        hero.setEmail(request.getParameter("heroEmail"));
        hero.setPhoneNumber(request.getParameter("heroNumber"));
        hero.setSuperHeroPower(request.getParameter("superHeroPower"));
        hero = dao.addHero(hero);


        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displayHeroPage";
    }
    
    
    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        String heroIDParameter = request.getParameter("heroID");
        int heroID = Integer.parseInt(heroIDParameter);

        Hero hero = dao.getHero(heroID);

        model.addAttribute("hero", hero);

        return "heroDetails";
    }
    
    
    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
        String heroIDParameter = request.getParameter("heroID");
        int heroID = Integer.parseInt(heroIDParameter);
        dao.deleteHero(heroID);
        return "redirect:displayHeroPage";
    }
    
    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditHeroForm(HttpServletRequest request, Model model) {
        String heroIDParameter = request.getParameter("heroID");
        int heroID = Integer.parseInt(heroIDParameter);
        Hero hero = dao.getHero(heroID);
        model.addAttribute("hero", hero);
        return "editHeroForm";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result) {

        if (result.hasErrors()) {
            return "editHeroForm";
        }

        dao.updateHero(hero);

        return "redirect:displayHeroPage";
    }
}