package it.uniroma2.nightplan.controllers;

import it.uniroma2.nightplan.beans.BMessage;
import it.uniroma2.nightplan.utils.LoggedUser;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*Nicolas Oberi*/

@Tag("integration")
class TestChatController {
    CFacade facade;
    private static final String MESSAGE = "This is a test message";
    private static final Integer GROUP_ID = 2;

    public TestChatController() {
        facade = new CFacade();
    }

    @Test
    void testSendMessage() {
        LoggedUser.setUserName("User1");
        LoggedUser.setUserID(3);

        BMessage messageBean = new BMessage(LoggedUser.getUserID(), MESSAGE, GROUP_ID);
        facade.sendMessageToGroup(messageBean);
        List<BMessage> messages = facade.retrieveGroupChat(GROUP_ID);
        assertEquals(MESSAGE, messages.getLast().getMessage());

    }
}
