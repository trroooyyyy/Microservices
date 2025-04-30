package chnu.edu.anetrebin.notification.model;

import chnu.edu.anetrebin.notification.enums.NotificationChannel;
import chnu.edu.anetrebin.notification.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 200)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "channel")
    private NotificationChannel channel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private NotificationStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.status = NotificationStatus.SENT;
        this.createdAt = LocalDateTime.now();
    }
}
