package com.codemiro.hour4u.notificationservice.util;

import com.codemiro.hour4u.notificationservice.model.MailAttachment;
import com.codemiro.hour4u.notificationservice.model.SESFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.util.ByteArrayDataSource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The type Email utility.
 */
@Component
@Slf4j
public class EmailUtility {

    /**
     * The constant ENCODING.
     */
    public static final String ENCODING = "UTF-8";

    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Email utility.
     *
     * @param mailSender     the mail sender
     * @param templateEngine the template engine
     */
    @Autowired
    public EmailUtility(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * Prepare and send.
     *
     * @param from         the from
     * @param recipients   the recipients
     * @param cc           the cc
     * @param bcc          the bcc
     * @param mailSubject  the mail subject
     * @param mailTemplate the mail template
     * @param variables    the variables
     * @param attachments  the attachments
     * @throws Exception the exception
     */
    public void prepareAndSend(@NotNull SESFrom from, @NotNull String[] recipients, String[] cc, String[] bcc,
                               @NotNull String mailSubject, @NotNull String mailTemplate, Map variables,
                               List<MailAttachment> attachments) throws Exception {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            String content = this.build(mailTemplate, variables);
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, ENCODING);
            messageHelper.setFrom(from.getEmail());
            messageHelper.setTo(recipients);
            messageHelper.setSubject(mailSubject);
            messageHelper.setText(content, true);
            messageHelper.setSentDate(new Date());

            if(cc != null && cc.length > 0) {
                messageHelper.setCc(cc);
            }
            if(bcc != null && bcc.length > 0) {
                messageHelper.setBcc(bcc);
            }

            if(attachments != null && attachments.size() > 0) {
                attachments.forEach(attachment -> {
                    try {
                        messageHelper.addAttachment(attachment.getName(), new ByteArrayDataSource(attachment.getContent(), attachment.getContentType()));
                    } catch (javax.mail.MessagingException e) {
                        log.error("Could not attach file#" + attachment.getName());
                    }
                });

            }
        };
        mailSender.send(messagePreparator);
    }

    /**
     * Send simple message.
     *
     * @param from    the from
     * @param to      the to
     * @param cc      the cc
     * @param bcc     the bcc
     * @param subject the subject
     * @param body    the body
     */
    public void sendSimpleMessage(@NotNull SESFrom from, @NotNull String to, String cc, String bcc, @NotNull String subject, @NotNull String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from.getEmail());
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        if(cc != null)
            simpleMailMessage.setCc(cc);

        mailSender.send(simpleMailMessage);
    }

    private String build(String mailTemplate, Map variables) {
        Context context = new Context();
        if(variables != null)
            variables.forEach((key, value) -> context.setVariable(key.toString(), value));
        return templateEngine.process(mailTemplate, context);
    }
}
