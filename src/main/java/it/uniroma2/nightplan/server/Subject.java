package it.uniroma2.nightplan.server;

import it.uniroma2.nightplan.model.Notification;
import it.uniroma2.nightplan.utils.enums.SubjectTypes;

import java.io.ObjectOutputStream;

public interface Subject {
    //Subject Interface For Server
    boolean attach(SubjectTypes type, Notification noti, ObjectOutputStream out);

    boolean detach(SubjectTypes type, Notification noti);

    boolean notify(SubjectTypes type, Object o);
}
