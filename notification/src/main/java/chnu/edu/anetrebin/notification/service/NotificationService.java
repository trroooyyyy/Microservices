package chnu.edu.anetrebin.notification.service;

import chnu.edu.anetrebin.notification.dto.request.NotificationRequest;
import chnu.edu.anetrebin.notification.model.Notification;

import java.util.List;

public interface NotificationService {
    Notification getNotificationById(Long id);

    List<Notification> getAllNotifications();

    List<Notification> getAllNotificationsForUser(Long id);

    void createNotification(NotificationRequest notificationRequest);
}
