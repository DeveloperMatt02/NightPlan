package it.uniroma2.nightplan.controllers.factory;

import it.uniroma2.nightplan.model.MessageObserverClass;
import it.uniroma2.nightplan.model.NotiObserverClass;
import it.uniroma2.nightplan.model.ObserverClass;
import it.uniroma2.nightplan.utils.enums.ObserverType;

import java.io.ObjectOutputStream;

public class ObserverFactory {
    public ObserverClass createObserver(ObserverType obsType, Integer id, ObjectOutputStream out) {
        ObserverClass obs;
        if (obsType == ObserverType.NOTI_OBSERVER) {
            obs = new NotiObserverClass();
        } else {
            obs = new MessageObserverClass();
        }
        obs.setObsID(id);
        obs.setOut(out);
        return obs;
    }
}
