package it.uniroma2.nightplan.controllers;

import it.uniroma2.nightplan.beans.BUserData;
import it.uniroma2.nightplan.exceptions.InvalidValueException;
import it.uniroma2.nightplan.exceptions.MinimumAgeException;
import it.uniroma2.nightplan.exceptions.TextTooLongException;
import it.uniroma2.nightplan.exceptions.UsernameAlreadyTaken;
import it.uniroma2.nightplan.utils.LoggedUser;
import it.uniroma2.nightplan.utils.enums.UserTypes;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*Matteo Trossi*/
@Tag("integration")
class TestLoginController {
    CFacade facade;

    private static final String NEWPROVINCE = "Roma";
    private static final String NEWCITY = "Frascati";

    public TestLoginController(){
        facade = new CFacade();
    }

    @Test
    void changeCityToRegisteredUser(){
        LoggedUser.setUserID(1); //loggato come Matteo

        int userID = LoggedUser.getUserID();
        if(facade.changeUserCity(LoggedUser.getUserID(), NEWPROVINCE, NEWCITY) == 1){
            assertEquals(NEWCITY, facade.getCityByUserID(userID));
        }
    }

    @Test
    void registerNewUser(){
        BUserData newUser = new BUserData();
        //sto creando un bean user
        try {
            newUser.setUsername("TestUser");
            newUser.setPassword("TestPassword");
            newUser.setFirstName("TestUsername");
            newUser.setLastName("TestLastname");
            newUser.setGender("Other");
            newUser.setBirthDate(LocalDate.parse("1980-01-01"));
            newUser.setProvince("Frosinone");
            newUser.setCity("Anagni");
            newUser.setType(UserTypes.ORGANIZER);
            facade.registerUser(newUser);
        } catch (InvalidValueException | TextTooLongException | UsernameAlreadyTaken | MinimumAgeException e) {
            Logger.getLogger("NightPlan").log(Level.SEVERE, e.getMessage());
        }
        assertEquals(newUser.getUsername(), facade.getUsernameByID(newUser.getUserID()));
    }
}
