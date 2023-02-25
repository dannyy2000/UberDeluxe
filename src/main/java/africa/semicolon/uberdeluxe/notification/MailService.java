package africa.semicolon.uberdeluxe.notification;

import africa.semicolon.uberdeluxe.data.dtos.requests.EmailNotificationRequest;

public interface MailService {
    String sendHtmlMail(EmailNotificationRequest request);
}
