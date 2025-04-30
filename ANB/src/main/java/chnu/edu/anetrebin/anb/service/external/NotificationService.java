package chnu.edu.anetrebin.anb.service.external;

import chnu.edu.anetrebin.anb.dto.requests.external.NotificationRequest;
import chnu.edu.anetrebin.anb.exceptions.external.NotificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    private final RestTemplate restTemplate;
    private final String notificationServiceUrl;

    public NotificationService(RestTemplate restTemplate, @Value("${notification-service.url}") String notificationServiceUrl) {
        this.restTemplate = restTemplate;
        this.notificationServiceUrl = notificationServiceUrl;
    }

    public void createNotification(NotificationRequest notificationRequest) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    notificationServiceUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(notificationRequest),
                    Void.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new NotificationException("Failed to create notification!");
            }

        } catch (HttpClientErrorException e) {
            throw new NotificationException("Notification service error: " + e.getMessage());
        } catch (RestClientException e) {
            throw new NotificationException("Notification service unavailable: " + e.getMessage());
        }
    }

    // TODO: ADD REQUEST FOR GETTING NOTIFICATIONS FOR USER EVERY 5 SEC
    // TODO: BETTER DO IT USING WEB SOCKETS
    // TODO: ADD REQUEST FOR CHANGING NOTIFICATION STATUS WHEN USER'S ARRAY LIST UPDATED
    // TODO: ADD DEPLOYING MS USING DOCKER :D
}