package it.uniroma2.nightplan.controllers;

import it.uniroma2.nightplan.beans.BEvent;
import it.uniroma2.nightplan.exceptions.EventAlreadyDeleted;
import it.uniroma2.nightplan.utils.enums.UserTypes;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import it.uniroma2.nightplan.utils.LoggedUser;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/*Matteo Trossi*/

@Tag("integration")
class TestEventParticipation {
    CFacade facade;

    public TestEventParticipation() {
        facade = new CFacade();
    }

    @Test
    void participateToEventAlreadyCreated() {
        LoggedUser.setUserID(1); //come se mi fossi loggato con Matteo
        List<BEvent> eventsInUserCity = facade.retrieveEvents(UserTypes.USER, "GCHomeUser");

        BEvent chosenEvent = eventsInUserCity.getFirst();
        boolean participationResult;
        try {
            participationResult = facade.participateToEvent(chosenEvent);

            // Verifico che il risultato di partecipazione sia true
            assertTrue(participationResult, "Participation result should be true");
        } catch (EventAlreadyDeleted e) {
            Logger.getLogger("NightPlan").log(Level.SEVERE, "Event already deleted");
        }
    }

    @Test
    void removeEventParticipation() {
        LoggedUser.setUserID(1); //come se mi fossi loggato con Matteo
        List<BEvent> eventsInUserCity = facade.retrieveEvents(UserTypes.USER, "GCHomeUser");

        BEvent chosenEvent = null;
        for (BEvent bEvent : eventsInUserCity) {
            if (bEvent.getEventID() == 1) {
                chosenEvent = bEvent;
            }
        }

        boolean resultRemoveParticipation = facade.removeEventParticipation(chosenEvent);

        assertTrue(resultRemoveParticipation);
    }

    @Test
    void checkPreviousParticipationToAlreadyPartEvent() {
        LoggedUser.setUserID(1);
        List<BEvent> eventsInUserCity = facade.retrieveEvents(UserTypes.USER, "GCHomeUser");

        BEvent chosenEvent = null;
        for (BEvent bEvent : eventsInUserCity) {
            if (bEvent.getEventID() == 1) {
                chosenEvent = bEvent;
            }
        }

        try {
            facade.participateToEvent(chosenEvent);
        } catch (EventAlreadyDeleted e) {
            Logger.getLogger("NightPlan").log(Level.SEVERE, "Event already deleted");
        }

        boolean resultCheckPreviousParticipation = facade.checkPreviousEventParticipation(chosenEvent);
        assertTrue(resultCheckPreviousParticipation, "Event already participated");
    }
}
