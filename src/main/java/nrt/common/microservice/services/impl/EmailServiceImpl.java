package nrt.common.microservice.services.impl;

import lombok.extern.slf4j.Slf4j;
import nrt.common.microservice.models.dto.BaseEmail;
import nrt.common.microservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String NO_REPLY = "noreply@app.com";
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(BaseEmail baseEmail) {
        log.info("Execute sendSimpleMail()");
        try {
            log.info("Preparing the mail");
            Context context = new Context();
            context.setVariable("object", baseEmail);

            String process = templateEngine.process(baseEmail.getTemplate(), context);
            log.info("Load mail template from resources: /resources/templates/" + baseEmail.getTemplate());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject(baseEmail.getSubjetc());
            mimeMessageHelper.setText(process, true);
            mimeMessageHelper.setFrom(NO_REPLY);
            mimeMessageHelper.setTo(baseEmail.getEmailTo());
            log.info("Sending email to = " + baseEmail.getEmailTo());
            javaMailSender.send(mimeMessage);
            log.info("Mail sent successfully!");
        } catch (MailException e) {
            log.error("ERROR: an error occured while sending the mail");
            log.error("ERROR: " + e.getMessage());
            e.printStackTrace();
        } catch (MessagingException e) {
            log.error("ERROR: an error occured while sending the mail");
            log.error("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailWithAttachments(Map<String, String> parameters) {

    }
}
