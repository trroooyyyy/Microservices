package chnu.edu.anetrebin.notification.service.impl;

import chnu.edu.anetrebin.notification.dto.request.NotificationRequest;
import chnu.edu.anetrebin.notification.enums.NotificationStatus;
import chnu.edu.anetrebin.notification.exceptions.NotificationNotFound;
import chnu.edu.anetrebin.notification.model.Notification;
import chnu.edu.anetrebin.notification.repository.NotificationRepository;
import chnu.edu.anetrebin.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFound("Notification with id - " + id + " not found!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Notification> getAllNotificationsForUser(Long id) {
        return notificationRepository.findAllByUserId(id);
    }

    @Transactional
    @Override
    public void createNotification(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .userId(notificationRequest.userId())
                .message(notificationRequest.message())
                .channel(notificationRequest.channel())
                .status(NotificationStatus.PENDING)
                .build();

        // TODO: Handle Delivered status, when ArrayList of user's notifications has been extended

        notificationRepository.save(notification);
    }
}
