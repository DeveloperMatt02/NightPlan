package it.uniroma2.nightplan.controllers;

import it.uniroma2.nightplan.beans.BEvent;
import it.uniroma2.nightplan.beans.BNotification;
import it.uniroma2.nightplan.exceptions.EventAlreadyAdded;
import it.uniroma2.nightplan.exceptions.InvalidValueException;
import it.uniroma2.nightplan.exceptions.TextTooLongException;
import it.uniroma2.nightplan.utils.LoggedUser;

import it.uniroma2.nightplan.utils.enums.NotificationTypes;
import it.uniroma2.nightplan.utils.enums.UserTypes;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*Nicolas Oberi*/

@Tag("integration")
class TestNotificationController {
    CFacade facade;
    private static final String MESSAGE = "This is a test message";
    private static final Integer GROUP_ID = 2;

    public TestNotificationController() {
        facade = new CFacade();
    }

    @Test
    void testAddNotification() {
        //Sono organizzatore -> pubblico un evento
        LoggedUser.setUserType(UserTypes.ORGANIZER);
        LoggedUser.setUserName("OrganizerTest");
        LoggedUser.setUserID(2);

        //Creazione evento
        BEvent event = new BEvent();
        event.setEventOrganizer(LoggedUser.getUserName());
        event.setEventOrganizerID(LoggedUser.getUserID());
        try {
            event.setEventName("test");
            event.setEventProvince("Palermo");
            event.setEventCity("Palermo");
            event.setEventAddress("Via Falcone 6");
            event.setEventMusicGenre("Pop");
            event.setEventTime("23","23");
            event.setEventDate("30-09-2025");
            event.setEventPicPath("dummy-path");
            facade.addEvent(event);
        } catch (InvalidValueException | TextTooLongException | EventAlreadyAdded e) {
            Logger.getLogger("NightPlan").log(Level.SEVERE, e.getMessage());
        }

        //Ora divento user e controllo se mi e' arrivata una notifica che un nuovo evento e' nella mia citta'
        LoggedUser.setUserType(UserTypes.USER);
        LoggedUser.setUserID(6);
        LoggedUser.setUserName("UserTest");

        List<BNotification> myNotifications = facade.retrieveNotifications(LoggedUser.getUserID());
        assertEquals(NotificationTypes.EVENT_ADDED, myNotifications.getLast().getMessageType());

    }
}
