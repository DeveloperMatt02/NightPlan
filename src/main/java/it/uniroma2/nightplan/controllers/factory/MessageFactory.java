package it.uniroma2.nightplan.controllers.factory;

import it.uniroma2.nightplan.model.MGroupMessage;
import it.uniroma2.nightplan.model.Message;
import it.uniroma2.nightplan.utils.enums.MessageTypes;

public class MessageFactory {
    public Message createMessage(MessageTypes msgType, Integer senderID, Integer receiverID, String message) {
        //implementiamo solo il GroupMessage ma facciamo una factory in caso in futuro volessimo implementare ad esempio una chat privata tra utenti o tra organizer e user,
        //in questo modo potremo riusare questa factory aggiungendo un nuovo ConcreteObject ovvero il PrivateMessage.
        Message msg = null;
        if (msgType == MessageTypes.GROUP){
            msg = new MGroupMessage();
        }
        if (msg!=null){
            msg.setSenderID(senderID);
            msg.setReceiverID(receiverID);
            msg.setMessage(message);
        }
        return msg;
    }

}
