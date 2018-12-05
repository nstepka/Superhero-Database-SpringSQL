/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherodb.model;

import java.util.Objects;

/**
 *
 * @author nstep
 */
public class Hero {

    private int heroID;
    private String heroName;
    private String description;
    private String email;
    private String phoneNumber;
    private String superHeroPower;

    public int getHeroID() {
        return heroID;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSuperHeroPower() {
        return superHeroPower;
    }

    public void setSuperHeroPower(String superHeroPower) {
        this.superHeroPower = superHeroPower;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.heroID;
        hash = 83 * hash + Objects.hashCode(this.heroName);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.phoneNumber);
        hash = 83 * hash + Objects.hashCode(this.superHeroPower);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (this.heroID != other.heroID) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.superHeroPower, other.superHeroPower)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
