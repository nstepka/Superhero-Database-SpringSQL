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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nstep
 */
public class DaoDbImpl implements Dao {

    private JdbcTemplate jdbc;

    public void setJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    ////SQL BRIDGE QUERY\\\\
    private static final String SQL_INSERT_HERO_ORG
            = "insert into heroesorganizations (heroID, organizationID) values(?, ?)";

    ////////////////////////HERO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static final String SQL_INSERT_HERO
            = "insert into hero "
            + "(heroName, description, email, phoneNumber, superHeroPower) "
            + "values (?, ?, ?, ?, ?)";

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        jdbc.update(SQL_INSERT_HERO,
                hero.getHeroName(),
                hero.getDescription(),
                hero.getEmail(),
                hero.getPhoneNumber(),
                hero.getSuperHeroPower());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        hero.setHeroID(newId);
        return hero;

    }

    private static final String SQL_UPDATE_HERO
            = "UPDATE hero "
            + "SET heroName = ?, description = ?, email = ?, phoneNumber = ?, superHeroPower = ? "
            + "WHERE heroId = ?";

    private static final String DELETE_HERO_FROM_BRIDGE_HERO_ORGANIZATION
            = "DELETE FROM heroesorganizations "
            + "WHERE heroID = ?";

    @Override
    @Transactional
    public Hero updateHero(Hero hero) {
        int heroID = hero.getHeroID();

        jdbc.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getDescription(),
                hero.getEmail(),
                hero.getPhoneNumber(),
                hero.getSuperHeroPower(),
                heroID);

        return hero;
    }

    private static final String SQL_DELETE_HERO
            = "DELETE "
            + "FROM hero "
            + "WHERE heroID = ?";

    private static final String SQL_DELETE_HEROE_FROM_HEROORG
            = "DELETE FROM heroesorganizations WHERE heroID = ?";

    private static final String SQL_DELETE_HEROE_FROM_HEROSIGHTING
            = "DELETE FROM herosightings WHERE heroID = ?";

    @Override
    @Transactional
    public void deleteHero(int heroID) {

        jdbc.update(SQL_DELETE_HEROE_FROM_HEROORG, heroID);
        jdbc.update(SQL_DELETE_HEROE_FROM_HEROSIGHTING, heroID);
        jdbc.update(SQL_DELETE_HERO, heroID);

    }

    private static final String SQL_SELECT_HERO
            = "SELECT * "
            + "FROM hero "
            + "WHERE heroId = ?";

    @Override
    public Hero getHero(int heroID) {
        try {
            return jdbc.queryForObject(SQL_SELECT_HERO,
                    new HeroMapper(), heroID);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }

    }
    private static final String SQL_SELECT_ALL_HEROS
            = "SELECT * "
            + "FROM hero";

    @Override
    public List<Hero> getAllHeros() {
        return jdbc.query(SQL_SELECT_ALL_HEROS,
                new HeroMapper());

    }

//////////////////////////////Organziations\\\\\\\\\\\\\\\\\\\\\
    private static final String SQL_INSERT_ORGANZIATION
            = "insert into organizations "
            + "(orgName, description, locationID, phoneNumber, emailContact) "
            + "values (?, ?, ?, ?, ?)";

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        jdbc.update(SQL_INSERT_ORGANZIATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getLocation().getLocationID(),
                organization.getOrganizationNumber(),
                organization.getOrganizationEmail());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationID(newId);
        insertHeroOrganization(organization);
        return organization;
    }

    private static final String UPDATE_GAME = "UPDATE game SET name = ?, releaseYear = ?, genreId = ? where id = ?";

    private static final String UPDATE_ORGANZIATION
            = "UPDATE organizations "
            + "SET orgName = ?, description = ?, locationID = ?, phoneNumber = ?, emailContact = ? "
            + "WHERE orgID = ?";

    private static final String DELETE_ORG_FROM_HERO_ORG
            = "DELETE "
            + "FROM heroesorganizations "
            + "where organizationID = ?";

    @Override
    @Transactional
    public Organization updateOrganization(Organization o) {
        jdbc.update(UPDATE_ORGANZIATION,
                o.getOrganizationName(),
                o.getOrganizationDescription(),
                (o.getLocation() == null) ? null : o.getLocation().getLocationID(),
                o.getOrganizationNumber(),
                o.getOrganizationEmail(),
                o.getOrganizationID()
        );
        jdbc.update(DELETE_ORG_FROM_HERO_ORG, o.getOrganizationID());
        insertHeroOrganization(o);
        return o;
    }

    private static final String SQL_DELETE_ORG_FROM_BRIDGE_HEROSORG_TABLE
            = "DELETE FROM heroesorganizations "
            + "WHERE organizationID = ?";

    private static final String SQL_DELETE_ORG
            = "DELETE "
            + "FROM organizations "
            + "where orgID = ?";

    @Override
    @Transactional
    public void deleteOrganization(int orgID) {
        jdbc.update(SQL_DELETE_ORG_FROM_BRIDGE_HEROSORG_TABLE, orgID);
        jdbc.update(SQL_DELETE_ORG, orgID);

    }

    private static final String SQL_SELECT_ORG
            = "SELECT * "
            + "FROM organizations "
            + "WHERE orgID = ?";

    @Override
    public Organization getOrgById(int orgID) {

        try {
            Organization o = jdbc.queryForObject(SQL_SELECT_ORG,
                    new OrganizationMapper(), orgID);
            o.setLocation(findLocationForOrganization(o));
            o.setHeros(getAllHerosOfOrganzation(o));
            return o;
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }

    }
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "SELECT * "
            + "FROM organizations";

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> o = jdbc.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        for (Organization org : o) {
            org.setHeros(getAllHerosOfOrganzation(org));
            org.setLocation(findLocationForOrganization(org));
        }
        return o;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting "
            + "(locationID, sightingDate) "
            + "values (?, ?)";

    @Override
    @Transactional
    public Sighting addSighting(Sighting s) {
        jdbc.update(SQL_INSERT_SIGHTING,
                s.getLocationID().getLocationID(),
                s.getSightingDate().toString());
        s.setSightingID(jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class));
        insertSightingintoHeroSighting(s);

        return s;
    }

    private static final String UPDATE_SIGHTING
            = "UPDATE sighting "
            + "SET locationId = ?, sightingDate = ? "
            + "Where sightingID = ?";

    private static final String DELETE_SIGHTING_FROM_HEROSIGHTINGS
            = "DELETE "
            + "FROM herosightings "
            + "where sightingID = ?";

    @Override
    @Transactional
    public Sighting updateSighting(Sighting s) {
        jdbc.update(UPDATE_SIGHTING,
                (s.getLocationID() == null) ? null : s.getLocationID().getLocationID(),
                s.getSightingDate().toString(),
                s.getSightingID()
        );

        jdbc.update(DELETE_SIGHTING_FROM_HEROSIGHTINGS, s.getSightingID());
        insertSightingintoHeroSighting(s);
        return s;

    }

    private static final String SQL_DELETE_SIGHTING_FROM_HEROSIGHTINGS
            = "DELETE FROM herosightings "
            + "WHERE sightingID = ?";

    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM sighting "
            + "WHERE sightingID = ?";

    @Override
    @Transactional
    public void deleteSightingById(int sightingID) {
        jdbc.update(SQL_DELETE_SIGHTING_FROM_HEROSIGHTINGS, sightingID);
        jdbc.update(SQL_DELETE_SIGHTING, sightingID);

    }

    private static String SELECT_HEROS_BY_SIGHTING
            = "Select * from hero "
            + "JOIN herosightings on hero.heroID = herosightings.heroID "
            + "WHERE sightingID = ?";

    public List<Hero> getAllHerosOfSighting(Sighting s) {
        List<Hero> heros = jdbc.query(SELECT_HEROS_BY_SIGHTING,
                new HeroMapper(),
                s.getSightingID());

        return heros;

    }
    
    private static final String SQL_SELECT_SIGHTING
            = "SELECT * "
            + "FROM sighting "
            + "WHERE sightingID = ?";

    @Override
    public Sighting getSightingById(int sightingID) {
        try {
            Sighting s = jdbc.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), sightingID);
            s.setLocationID(findLocationForSighting(s));
            s.setHero(getAllHerosOfSighting(s));
            return s;

        } catch (EmptyResultDataAccessException ex) {
            // there were no results forgetSightingById the given contact id - we just 
            // want to return null in this case
            return null;
        }

    }
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "SELECT * "
            + "FROM sighting";

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> s = jdbc.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
        for (Sighting sighting : s) {
            sighting.setHero(getAllHerosOfSighting(sighting));
            sighting.setLocationID(findLocationForSighting(sighting));

        }
        return s;

    }

///////////////LOCATIONS\\\\\\\\\\\\\\\\\\\\\\\\\
    private static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(locationName, description, address, city, state, latitude, longitude) "
            + "values (?, ?, ?, ?, ?, ?, ?)";

    @Override
    @Transactional
    public Location addLocation(Location location) {
        jdbc.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationCity(),
                location.getLocationState(),
                location.getLocationLatitude(),
                location.getLocationLongitude());

        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationID(newId);
        return location;

    }
    private static final String SQL_UPDATE_LOCATION
            = "UPDATE location "
            + "SET locationName = ?, description = ?, address = ?, city = ?, state = ?, latitude = ?, longitude = ? "
            + "WHERE locationID = ?";

    @Override
    @Transactional
    public Location updateLocation(Location location) {

        jdbc.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationCity(),
                location.getLocationState(),
                location.getLocationLatitude(),
                location.getLocationLongitude(),
                location.getLocationID());

        return location;
    }

    private static final String SQL_DELETE_LOCATION
            = "DELETE "
            + "FROM location "
            + "WHERE locationID = ?";

    private static final String SQL_UPDATE_ORG_LOCATION_ID
            = "UPDATE organizations SET locationID = null "
            + "WHERE locationID = ?";

    private static final String SQL_UPDATE_SIGHTING_LOCATION_ID
            = "UPDATE sighting set locationID = null "
            + "WHERE locationID = ?";

    @Override
    @Transactional
    public void deleteLocationByID(int locationID) {
        jdbc.update(SQL_UPDATE_ORG_LOCATION_ID, locationID);
        jdbc.update(SQL_UPDATE_SIGHTING_LOCATION_ID, locationID);
        jdbc.update(SQL_DELETE_LOCATION, locationID);

    }

    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationID = ?";

    @Override
    public Location getLocationById(int locationID) {
        try {
            return jdbc.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    locationID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    private static final String SQL_SELECT_ALL_LOCATION
            = "SELECT * "
            + " FROM LOCATION";

    @Override
    public List<Location> getAllLocations() {
        return jdbc.query(SQL_SELECT_ALL_LOCATION, new LocationMapper());
    }

    ////////////////////    QUERYS  \\\\\\\\\\\\\\\\\\\\\\\\\
    //The system must be able to report all of the superheroes sighted at a particular location.
    private static final String SQL_SELECT_HERO_AT_SIGHTING
            = "select * from location "
            + "JOIN hero on hero.heroID = herosightings.heroID  "
            + "JOIN sighting on herosightings.sightingID = Sighting.sightingID "
            + "JOIN location on sighting.location = location.locationID "
            + "WHERE location.locationID = ?";

    @Override
    public List<Hero> getAllHerosAtLocation(int locationID) {
        List<Hero> heros = jdbc.query(SQL_GET_ALL_SIGHTINGS_AT_LOCATION,
                new HeroMapper(), locationID);

        return heros;

    }
    //The system must be able to report all of the superheroes sighted at a particular location.

    private static final String SQL_GET_ALL_SIGHTINGS_AT_LOCATION
            = "SELECT * from location "
            + "Join  sighting on sighting.locationID = location.LocationID "
            + "Join herosightings on herosightings.sightingid = sighting.sightingID "
            + "JOIN hero on herosightings.heroID = hero.heroID "
            + "WHERE hero.heroID = ?";

    @Override
    public List<Location> getAllLocationsofHero(int heroID) {
        //List<Integer> locationID = jdbc.queryForList(SQL_GET_ALL_SIGHTINGS_AT_LOCATION,
        //        Integer.class, heroID);
        List<Location> locations = jdbc.query(SQL_GET_ALL_SIGHTINGS_AT_LOCATION,
                new LocationMapper(), heroID);
        return locations;
    }

    private static final String SQL_GET_ALL_HEROS_AND_LOCATION_BY_DATE
            = "select * from sighting "
            + "where sightingDate = ?";

    @Override
    public List<Sighting> getAllSightingsOnDate(LocalDate sightingDate) {
        List<Sighting> sighting = jdbc.query(SQL_GET_ALL_HEROS_AND_LOCATION_BY_DATE,
                new SightingMapper(), sightingDate);

        return sighting;

    }
    private static final String SQL_SELECT_ALL_ORGS
            = "SELECT * FROM organizations";

    private static final String SELECT_HEROS_BY_ORG
            = "SELECT * from hero "
            + "JOIN heroesorganizations ON hero.heroID = heroesorganizations.heroID "
            + "WHERE organizationID = ?";

    public List<Hero> getAllHerosOfOrganzation(Organization o) {
        List<Hero> heros = jdbc.query(SELECT_HEROS_BY_ORG,
                new HeroMapper(),
                o.getOrganizationID());

        return heros;
    }

    public static final String SELECT_ORG_FROM_HEROORG
            = "select * from organizations "
            + "join heroesorganizations on Organizations.orgID = heroesorganizations.organizationID "
            + "where heroID = ?";

    @Override
    public List<Organization> getAllOrganizationOfHero(int heroID) {
        List<Organization> organizations = jdbc.query(SELECT_ORG_FROM_HEROORG,
                new OrganizationMapper(),
                heroID);

        return organizations;
    }

    private static final String SQL_SELECT_10_MOST_RECENT_SIGHTINGS
            = "SELECT * FROM sighting "
            + "ORDER BY SightingDate DESC LIMIT 10";

    public List<Sighting> get10MostRecentSightings() {
        List<Sighting> sightings = jdbc.query(SQL_SELECT_10_MOST_RECENT_SIGHTINGS,
                new SightingMapper());
        for (Sighting s : sightings) {
            s.setHero(getAllHerosOfSighting(s));
            s.setLocationID(findLocationForSighting(s));
        }

        return sightings;
    }

    ////////////////////     MAPPERS  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Hero h = new Hero();
            h.setHeroID(rs.getInt("heroID"));
            h.setHeroName(rs.getString("heroName"));
            h.setDescription(rs.getString("description"));
            h.setEmail(rs.getString("email"));
            h.setPhoneNumber(rs.getString("phoneNumber"));
            h.setSuperHeroPower(rs.getString("superHeroPower"));

            return h;
        }
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationID(rs.getInt("OrgID"));
            o.setOrganizationName(rs.getString("OrgName"));
            o.setOrganizationDescription(rs.getString("Description"));

            o.setOrganizationNumber(rs.getString("phoneNumber"));
            o.setOrganizationEmail(rs.getString("emailContact"));
            return o;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        //private static final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Location l = new Location();
            l.setLocationID(rs.getInt("locationID"));
            l.setLocationName(rs.getString("locationName"));
            l.setLocationDescription(rs.getString("description"));
            l.setLocationAddress(rs.getString("address"));
            l.setLocationCity(rs.getString("city"));
            l.setLocationState(rs.getString("state"));
            l.setLocationLatitude(rs.getBigDecimal("latitude"));
            l.setLocationLongitude(rs.getBigDecimal("longitude"));
            return l;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingID(rs.getInt("sightingID"));
            s.setSightingDate(rs.getTimestamp("sightingDate").
                    toLocalDateTime().toLocalDate());
            return s;
        }

    }

///////////////////       HELPERS       \\\\\\\\\\\\\\\\\\\\\\
    
    private static final String GET_LOCATION_BY_SIGHTING
            = "SELECT l.* "
            + "FROM location l "
            + "JOIN sighting s On  s.locationID = l.locationID "
            + "WHERE s.sightingID = ?";

    private Location findLocationForSighting(Sighting sight) {
        try {
            return jdbc.queryForObject(GET_LOCATION_BY_SIGHTING,
                    new LocationMapper(),
                    sight.getSightingID());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }
    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATIONID
            = "SELECT l.* "
            + "FROM location l "
            + "JOIN organizations o ON o.locationId = l.locationID "
            + "WHERE o.orgID = ?";

    private Location findLocationForOrganization(Organization organization) {
        try {
            return jdbc.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATIONID,
                    new LocationMapper(),
                    organization.getOrganizationID());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final String SQL_INSERT_HERO_ORGANIZATION
            = "insert into heroesorganizations "
            + "(heroID, organizationID) "
            + "values(?, ?)";

    private void insertHeroOrganization(Organization o) {
        for (Hero h : o.getHeros()) {

            jdbc.update(SQL_INSERT_HERO_ORGANIZATION, h.getHeroID(),
                    o.getOrganizationID());
        }
    }

    private static final String SQL_INSERT_HERO_SIGHTINGS
            = "insert into herosightings "
            + "(heroID, sightingID) "
            + "values(?,?)";

    private void insertSightingintoHeroSighting(Sighting s) {

        if (s.getHero() == null) {
            return;
        }

        for (Hero h : s.getHero()) {
            jdbc.update(SQL_INSERT_HERO_SIGHTINGS,
                    h.getHeroID(),
                    s.getSightingID());

        }

    }
    /////helpers tables
    private static final String DELETE_HERO_FROM_BRIDGE_TO_ORG
            = "delete from heroogranization "
            + "where organizationID = ?";

}
