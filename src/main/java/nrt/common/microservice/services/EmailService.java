package nrt.common.microservice.services;

import nrt.common.microservice.models.dto.BaseEmail;

import java.util.Map;

public interface EmailService {

    public void sendSimpleMail(BaseEmail baseEmail);
    public void sendEmailWithAttachments(Map<String, String> parameters);
}
