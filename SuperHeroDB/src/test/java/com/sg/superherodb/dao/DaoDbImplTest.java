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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author nstep
 */
public class DaoDbImplTest {

    private Dao dao;
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public DaoDbImplTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("Dao", Dao.class);
        //delete all heros
        List<Hero> heros = dao.getAllHeros();
        for (Hero h : heros) {
            dao.deleteHero(h.getHeroID());

        }
        //delete all organizations
        List<Organization> organizations = dao.getAllOrganizations();

        for (Organization o : organizations) {
            dao.deleteOrganization(o.getOrganizationID());
        }

        //delete all locations
        List<Location> locations = dao.getAllLocations();

        for (Location l : locations) {
            dao.deleteLocationByID(l.getLocationID());
        }

        //delete all sightings
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting s : sightings) {
            dao.deleteSightingById(s.getSightingID());
        }

    }

    ///helper adders
    private Hero addHero() {
        Hero h = new Hero();

        h.setHeroName("SuperMan");
        h.setDescription("Wears underwear");
        h.setEmail("noonelovesme@loveless.com");
        h.setPhoneNumber("6514522896");
        h.setSuperHeroPower("can fly");
        return dao.addHero(h);

    }

    private Location addLocation() {
        Location l = new Location();
        l.setLocationName("Location where something happened");
        l.setLocationDescription("Description... of location");
        l.setLocationAddress("1965 Safari Trail");
        l.setLocationCity("Eagan");
        l.setLocationState("Minnesota");
        l.setLocationLatitude(new BigDecimal("12.156151"));
        l.setLocationLongitude(new BigDecimal("12.151615"));
        return dao.addLocation(l);
    }

    private Organization addOrg() {
        Organization o = new Organization();
        Location l = addLocation();
        List<Hero> heros = new ArrayList();
        o.setOrganizationName("ORGANIZATION NAME");
        o.setOrganizationDescription("Bunch of people thinking they are protecting people");
        o.setLocation(l);
        o.setOrganizationNumber("9522158996");
        o.setOrganizationEmail("notAORG@heroless.com");
        heros.add(addHero());
        heros.add(addHero());
        heros.add(addHero());
        o.setHeros(heros);

        return dao.addOrganization(o);

    }

    private Sighting addSighting() {
        Sighting s = new Sighting();
        Location l = addLocation();
        List<Hero> heros = new ArrayList();
        s.setLocationID(l);
        heros.add(addHero());
        heros.add(addHero());
        heros.add(addHero());
        s.setSightingDate(LocalDate.of(2000, 12, 30));
        return dao.addSighting(s);
    }

    /**
     * Test of addHero method, of class DaoDbImpl.
     */
    @Test
    public void testAddHero() {
        Hero h1 = new Hero();
        Hero h2 = new Hero();
        Hero h3 = new Hero();

        h1 = addHero();
        assertEquals(true, dao.getAllHeros().contains(h1));
        h2 = addHero();
        assertEquals(2, dao.getAllHeros().size());

    }

    /**
     * Test of updateHero method, of class DaoDbImpl.
     */
    @Test
    public void testUpdateHero() {
        Hero h1 = addHero();
        String testName = "TestName";

        String testDescription = "TestDescription";
        String testEmail = "TestEmail";
        String testPhone = "1234567891";
        String testSuperPower = "TestSuperPower";

        h1.setHeroName(testName);
        h1.setDescription(testDescription);
        h1.setEmail(testEmail);
        h1.setPhoneNumber(testPhone);
        h1.setSuperHeroPower(testSuperPower);
        dao.updateHero(h1);

        Hero h2 = dao.getHero(h1.getHeroID());
        assertEquals(testName, h2.getHeroName());
        assertEquals(testDescription, h2.getDescription());
        assertEquals(testEmail, h2.getEmail());
        assertEquals(testPhone, h2.getPhoneNumber());
        assertEquals(testSuperPower, h2.getSuperHeroPower());

        // assertEquals(testName, dao.getHero(h1.getHeroID().getHeroName(());
    }

    /**
     * Test of deleteHero method, of class DaoDbImpl.
     */
    @Test
    public void testDeleteHero() {
        Hero h1 = addHero();
        Hero h2 = addHero();
        assertEquals(2, dao.getAllHeros().size());
        dao.deleteHero(h2.getHeroID());
        assertEquals(1, dao.getAllHeros().size());

    }

    /**
     * Test of getHero method, of class DaoDbImpl.
     */
    @Test
    public void testGetHero() {
        Hero h1 = addHero();
        Hero h2 = addHero();
        Hero h3 = addHero();
        dao.addHero(h1);
        dao.addHero(h2);
        dao.addHero(h3);

        assertEquals(h1, dao.getHero(h1.getHeroID()));

    }

    /**
     * Test of getAllHeros method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllHeros() {
        Hero h1 = addHero();
        Hero h2 = addHero();
        Hero h3 = addHero();
        List<Hero> heroList = new ArrayList<>();
        heroList.add(h1);
        heroList.add(h2);
        heroList.add(h3);
        assertEquals(3, dao.getAllHeros().size());
        assertEquals(heroList, dao.getAllHeros());

    }

    /**
     * Test of addOrganization method, of class DaoDbImpl.
     */
    @Test
    public void testAddOrganization() {

        Organization o1 = new Organization();
        Organization o2 = new Organization();
        Organization o3 = new Organization();
        o1 = addOrg();
        o2 = addOrg();
        o3 = addOrg();

        List<Organization> listO = dao.getAllOrganizations();
        assertEquals(true, dao.getAllOrganizations().contains(o1));
        assertEquals(3, dao.getAllOrganizations().size());

    }

    /**
     * Test of updateOrganization method, of class DaoDbImpl.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org1 = addOrg();
        Organization orgTestControl = new Organization();
        orgTestControl = org1;
        String testOrgName = "TestName";
        String testOrgDesc = "TestDesc";
        Location testLocation = addLocation();
        testLocation.setLocationName("TestName");
        testLocation.setLocationDescription("testDescription");
        testLocation.setLocationAddress("Testaddress");
        testLocation.setLocationCity("TestCity");
        testLocation.setLocationState("TestState");
        testLocation.setLocationLatitude(new BigDecimal("99.999999"));
        testLocation.setLocationLongitude(new BigDecimal("99.999999"));
        dao.updateLocation(testLocation);
        String testOrgPhoneNumber = "1234567891";
        String testOrgEmail = "TestEmail";
        org1.setOrganizationName(testOrgName);
        org1.setOrganizationDescription(testOrgDesc);
        org1.setLocation(testLocation);
        org1.setOrganizationNumber(testOrgPhoneNumber);
        org1.setOrganizationEmail(testOrgEmail);
        dao.updateOrganization(org1);
        Organization orgTest = dao.getOrgById(org1.getOrganizationID());

        assertEquals(testOrgName, orgTest.getOrganizationName());
        assertEquals(testOrgDesc, orgTest.getOrganizationDescription());
        assertEquals(testLocation, orgTest.getLocation());
        assertEquals(testOrgPhoneNumber, orgTest.getOrganizationNumber());
        assertEquals(testOrgEmail, orgTest.getOrganizationEmail());

    }

    /**
     * Test of deleteOrganization method, of class DaoDbImpl.
     */
    @Test
    public void testDeleteOrganization() {
        Organization org1 = addOrg();
        Organization org2 = addOrg();
        Organization org3 = addOrg();

        assertEquals(org1, dao.getOrgById(org1.getOrganizationID()));
        assertEquals(3, dao.getAllOrganizations().size());
        dao.deleteOrganization(org1.getOrganizationID());
        assertEquals(2, dao.getAllOrganizations().size());

    }

    /**
     * Test of getOrgById method, of class DaoDbImpl.
     */
    @Test
    public void testGetOrgById() {
        Organization org1 = addOrg();
        Organization org2 = new Organization();
        org2 = addOrg();
        Organization org3 = addOrg();
        assertEquals(org1, dao.getOrgById(org1.getOrganizationID()));

        assertEquals(org2, dao.getOrgById(org2.getOrganizationID()));
        assertEquals(org3, dao.getOrgById(org3.getOrganizationID()));
    }

    /**
     * Test of getAllOrganizations method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org1 = addOrg();
        Organization org2 = new Organization();
        org2 = addOrg();

        List<Organization> orgList = dao.getAllOrganizations();

        assertEquals(2, dao.getAllOrganizations().size());

    }

    /**
     * Test of addSighting method, of class DaoDbImpl.
     */
    @Test
    public void testAddSighting() {
        Sighting sighting1 = addSighting();
        Sighting sighting2 = new Sighting();
        List<Hero> heroList = new ArrayList<>();
        heroList.add(addHero());
        heroList.add(addHero());
        heroList.add(addHero());
        sighting1.setHero(heroList);
        sighting2 = addSighting();
        assertEquals(2, dao.getAllSightings().size());

    }

    /**
     * Test of updateSighting method, of class DaoDbImpl.
     */
    @Test
    public void testUpdateSighting() {
        Sighting sighting1 = addSighting();
        Sighting sighting2 = new Sighting();
        sighting2 = addSighting();

        Location testLocation = addLocation();
        testLocation.setLocationName("TestName");
        testLocation.setLocationDescription("testDescription");
        testLocation.setLocationAddress("Testaddress");
        testLocation.setLocationCity("TestCity");
        testLocation.setLocationState("TestState");
        testLocation.setLocationLatitude(new BigDecimal("99.999999"));
        testLocation.setLocationLongitude(new BigDecimal("99.999999"));
        dao.updateLocation(testLocation);
        sighting1.setLocationID(testLocation);
        sighting1.setSightingDate(LocalDate.of(2010, 1, 1));
        dao.updateSighting(sighting1);
        Sighting sighting3 = dao.getSightingById(sighting1.getSightingID());

        assertEquals(2, dao.getAllSightings().size());
        assertEquals(sighting1, dao.getSightingById(sighting1.getSightingID()));

    }

    /**
     * Test of deleteSightingById method, of class DaoDbImpl.
     */
    @Test
    public void testDeleteSightingById() {
        Sighting sighting1 = addSighting();
        List<Hero> heroList = new ArrayList<>();
        heroList.add(addHero());
        heroList.add(addHero());
        heroList.add(addHero());
        sighting1.setHero(heroList);

        Sighting sighting2 = new Sighting();
        sighting2 = addSighting();
        Sighting sighting3 = dao.getSightingById(sighting1.getSightingID());

        //assertEquals(sighting1, dao.getSightingById(sighting1.getSightingID()));
        assertEquals(2, dao.getAllSightings().size());

        dao.deleteSightingById(sighting1.getSightingID());
        assertEquals(1, dao.getAllSightings().size());

    }

    /**
     * Test of getSightingById method, of class DaoDbImpl.
     */
    @Test
    public void testGetSightingById() {
        Sighting sighting1 = addSighting();
        Sighting sighting2 = new Sighting();
        sighting2 = addSighting();
        Sighting sighting3 = dao.getSightingById(sighting1.getSightingID());
        Sighting sighting4 = dao.getSightingById(sighting2.getSightingID());

        assertEquals(sighting1, dao.getSightingById(sighting1.getSightingID()));
        assertEquals(sighting2, dao.getSightingById(sighting2.getSightingID()));

    }

    /**
     * Test of getAllSightings method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllSightings() {

        Sighting sighting1 = addSighting();
        assertEquals(1, dao.getAllSightings().size());
        Sighting sighting2 = new Sighting();
        sighting2 = addSighting();
        assertEquals(2, dao.getAllSightings().size());

    }

    /**
     * Test of addLocation method, of class DaoDbImpl.
     */
    @Test
    public void testAddLocation() {

        Location locOne = addLocation();
        Location locThree = dao.getLocationById(locOne.getLocationID());

        assertEquals(true, dao.getAllLocations().contains(locOne));
        assertEquals(1, dao.getAllLocations().size());
        Location locTwo = new Location();
        locTwo = addLocation();
        assertEquals(true, dao.getAllLocations().contains(locTwo));
        assertEquals(2, dao.getAllLocations().size());

    }

    /**
     * Test of updateLocation method, of class DaoDbImpl.
     */
    @Test
    public void testUpdateLocation() {
        Location l = new Location();

        l = addLocation();
        l.setLocationName("Testlocation");
        l.setLocationDescription("TestDiscrip");
        l.setLocationAddress("Test 190353 address");
        l.setLocationCity("TestEagan");
        l.setLocationState("testMinnesota");
        l.setLocationLatitude(new BigDecimal("99.156159"));
        l.setLocationLongitude(new BigDecimal("99.151611"));

        dao.updateLocation(l);
        assertEquals(l, dao.getLocationById(l.getLocationID()));

    }

    /**
     * Test of deleteLocationByID method, of class DaoDbImpl.
     */
    @Test
    public void testDeleteLocationByID() {
        Location locOne = addLocation();
        Location locTwo = new Location();
        locTwo = addLocation();
        assertEquals(2, dao.getAllLocations().size());
        Location locThree = addLocation();
        assertEquals(3, dao.getAllLocations().size());
        dao.deleteLocationByID(locTwo.getLocationID());
        assertEquals(2, dao.getAllLocations().size());

    }

    /**
     * Test of getLocationById method, of class DaoDbImpl.
     */
    @Test
    public void testGetLocationById() {
        Location locOne = addLocation();
        Location locTwo = new Location();
        locTwo = addLocation();
        assertEquals(locOne, dao.getLocationById(locOne.getLocationID()));

    }

    /**
     * Test of getAllLocations method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of getAllHerosAtLocation method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllHerosAtLocation() {
        Organization org = addOrg();
        Organization org2 = addOrg();
        Hero hero = addHero();
        hero.setHeroName("stepka");
        dao.updateHero(hero);
        List<Hero> listOfHeros = org.getHeros();
        List<Hero> listOfHeros2 = org2.getHeros();
        int heroID = hero.getHeroID();
        listOfHeros.add(hero);
        listOfHeros2.add(hero);
        org.setHeros(listOfHeros);
        org2.setHeros(listOfHeros2);
        Sighting sighting = addSighting();
        sighting.setHero(listOfHeros);
        Sighting sighting2 = addSighting();
        sighting2.setHero(listOfHeros2);

        dao.updateSighting(sighting);
        dao.updateSighting(sighting2);

        List<Location> listOfLocations = dao.getAllLocationsofHero(heroID);
        assertEquals(2, dao.getAllHerosAtLocation(heroID).size());
        // assertEquals(sighting, dao.getal listOfLocations.contains(sighting));

    }

    /**
     * Test of getAllLocationsofHero method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllLocationsofHero() {
        Sighting sighting1 = addSighting();
        Sighting sighting2 = new Sighting();
        sighting2 = addSighting();
        Hero hero = addHero();
        Hero hero2 = new Hero();
        hero2 = addHero();
        hero2.setHeroName("steven");
        hero.setHeroName("stepka");
        dao.updateHero(hero);
        dao.updateHero(hero2);
        List<Hero> heroList = new ArrayList<>();
        heroList.add(hero);
        heroList.add(hero2);
        sighting1.setHero(heroList);
        dao.updateSighting(sighting1);
        sighting2.setHero(heroList);
        dao.updateSighting(sighting2);
        Sighting sighting3 = new Sighting();
        sighting3 = addSighting();
        List<Location> listOfLocation = dao.getAllLocationsofHero(hero.getHeroID());

        assertEquals(2, dao.getAllLocationsofHero(hero.getHeroID()).size());

        //make sure they list
        //  assertEquals(locationList, dao.getAllLocationsofHero(h.getHeroID()));
    }

    /**
     * Test of getAllSighingsOnDate method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllSightingsOnDate() {
        Sighting sighting1 = addSighting();
        Sighting sighting2 = new Sighting();
        sighting2 = addSighting();
        Hero hero = addHero();
        Hero hero2 = new Hero();
        hero2 = addHero();
        hero2.setHeroName("steven");
        hero.setHeroName("stepka");
        dao.updateHero(hero);
        dao.updateHero(hero2);
        List<Hero> heroList = new ArrayList<>();
        heroList.add(hero);
        heroList.add(hero2);
        sighting1.setHero(heroList);
        dao.updateSighting(sighting1);
        sighting2.setHero(heroList);
        dao.updateSighting(sighting2);
        Sighting sighting3 = new Sighting();
        sighting3 = addSighting();
        List<Sighting> sightings = dao.getAllSightingsOnDate(LocalDate.of(2000, 12, 30));
        assertEquals(3, dao.getAllSightingsOnDate(sighting1.getSightingDate()).size());

    }

    /**
     * Test of getAllHerosOfOrganzation method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllHerosOfOrganzation() {
         Organization orgOne = addOrg();
         assertEquals(3, dao.getAllHeros().size());
         Hero heroTest = addHero();
         List<Hero> heroList = orgOne.getHeros();
         heroList.add(heroTest);
         orgOne.setHeros(heroList);
         dao.updateOrganization(orgOne);
         assertEquals(4, dao.getAllHeros().size());
         
         
    }

    /**
     * Test of getAllOrganizationOfHero method, of class DaoDbImpl.
     */
    @Test
    public void testGetAllOrganizationOfHero() {
        Hero heroTest = addHero();
        heroTest.setHeroName("Stepka");
        dao.updateHero(heroTest);
        Organization orgOne = addOrg();
        Organization orgTwo = addOrg();
        Organization orgThree = addOrg();
        List<Hero> heroList = orgOne.getHeros();
        heroList.add(heroTest);
        dao.updateOrganization(orgOne);
        assertEquals(1, dao.getAllOrganizationOfHero(heroTest.getHeroID()).size());
        orgTwo.setHeros(heroList);
        dao.updateOrganization(orgTwo);
        assertEquals(2, dao.getAllOrganizationOfHero(heroTest.getHeroID()).size());
        orgThree.setHeros(heroList);
        dao.updateOrganization(orgThree);
        assertEquals(3, dao.getAllOrganizationOfHero(heroTest.getHeroID()).size());
    }

}
