package it.uniroma2.nightplan.controllers;

import it.uniroma2.nightplan.beans.BEvent;
import it.uniroma2.nightplan.dao.*;
import it.uniroma2.nightplan.exceptions.DuplicateEventParticipation;
import it.uniroma2.nightplan.exceptions.EventAlreadyDeleted;
import it.uniroma2.nightplan.model.MEvent;

public class CEventParticipation {

    private UserEventDAO userEventDAO;

    public CEventParticipation() {
        userEventDAO = new UserEventDAO();
    }

    public boolean participateToEvent(BEvent eventBean) throws EventAlreadyDeleted {
        MEvent eventModel = new MEvent(eventBean);
        //in ogni caso scrivi sul database delle notifiche le notifiche per quell'utente
        return userEventDAO.joinUserToEvent(eventModel);
    }

    public boolean removeEventParticipation(BEvent eventBean) {
        MEvent eventModel = new MEvent(eventBean);
        return userEventDAO.removeUserToEvent(eventModel);
    }

    public boolean checkPreviousEventParticipation(BEvent eventBean) {
        try {
            userEventDAO.checkPreviousParticipation(eventBean.getEventID());
            return false;
        } catch (DuplicateEventParticipation e) {
            return true;
        }
    }
}
