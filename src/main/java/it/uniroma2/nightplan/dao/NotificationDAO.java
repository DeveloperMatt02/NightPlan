package it.uniroma2.nightplan.dao;

import it.uniroma2.nightplan.model.Notification;
import it.uniroma2.nightplan.utils.enums.NotificationTypes;

import java.util.ArrayList;
import java.util.List;

public interface NotificationDAO {
    void addNotificationToUsers(List<Integer> notifiedIDs, NotificationTypes notificationTypes, int eventID);
    ArrayList<Notification> getNotificationsByUserID(int usrID);
    boolean deleteNotification(int notificationID);
}
