package nrt.common.microservice.services;

import nrt.common.microservice.models.dto.BaseEmail;

import java.util.Map;

/**
 * Common EmailService Layer
 * This interface provides the signs to send emails
 *
 * @author nahueltabasso
 */
public interface EmailService {

    public void sendSimpleMail(BaseEmail baseEmail);
    public void sendEmailWithAttachments(Map<String, String> parameters);
}
