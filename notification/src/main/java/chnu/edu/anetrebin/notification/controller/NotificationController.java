package chnu.edu.anetrebin.notification.controller;

import chnu.edu.anetrebin.notification.dto.request.NotificationRequest;
import chnu.edu.anetrebin.notification.model.Notification;
import chnu.edu.anetrebin.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Validated
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping("/forUser/{id}")
    public ResponseEntity<List<Notification>> getNotificationsForUser(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getAllNotificationsForUser(id));
    }

    @PostMapping("/")
    public ResponseEntity<Void> createNotification(@RequestBody @Valid NotificationRequest notificationRequest) {
        notificationService.createNotification(notificationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
