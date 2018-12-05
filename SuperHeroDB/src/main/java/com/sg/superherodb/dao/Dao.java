/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherodb.dao;

import com.sg.superherodb.model.Hero;
import com.sg.superherodb.model.Location;
import com.sg.superherodb.model.Organization;
import com.sg.superherodb.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nstep
 */
public interface Dao {
            
    //Hero CRUD 
    public Hero addHero(Hero hero);
    public Hero updateHero(Hero hero);
    public void deleteHero(int heroID);
    public Hero getHero(int heroID);
    public List<Hero> getAllHeros();
    
    //Organzation CRUD
    public Organization addOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(int orgID);
    public Organization getOrgById(int id);
    public List<Organization> getAllOrganizations();
    
    
    //Sighting CRUD
    public Sighting addSighting(Sighting sight);
    public Sighting updateSighting(Sighting sight);
    public void deleteSightingById(int sightingID);
    public Sighting getSightingById(int sightingID);
    public List<Sighting> getAllSightings();
    
    //Location CRUD
    public Location addLocation(Location location);       
    public Location updateLocation(Location location);
    public void deleteLocationByID(int locationID);
    public Location getLocationById(int locationID);
    public List<Location> getAllLocations();
        
    
    
    //Query
    //The system must be able to report all of the superheroes sighted at a particular location.
    public List<Hero> getAllHerosAtLocation(int locationID);
    //public List<Hero> getAllHerosAtLocation(Hero hero, Location location);
    //The system must be able to report all of the locations where a particular superhero has been seen.
    public List<Location> getAllLocationsofHero(int heroID);
    //The system must be able to report all sightings (hero and location) for a particular date.
    public List<Sighting> getAllSightingsOnDate(LocalDate sightingDate);
    //The system must be able to report all of the members of a particular organization.
    public List<Hero> getAllHerosOfOrganzation(Organization organzation);
    //The system must be able to report all of the organizations a particular superhero/villain belongs to.
    public List<Organization> getAllOrganizationOfHero(int heroID);
    public List<Sighting> get10MostRecentSightings();
    
    
    
}
