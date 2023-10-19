package com.anthem.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil
{
  public static void sendEmail(String toAddress, String fromAddress,
      String subject, String body) throws AddressException, MessagingException
  {
    String smtpHost = "smtp.corp.anthem.com";

    Properties properties = new Properties();
    properties.put("mail.smtp.host", smtpHost);
    Session session = Session.getInstance(properties, null);

    MimeMessage message = new MimeMessage(session);
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
    message.setFrom(new InternetAddress(fromAddress));
    message.setSubject(subject);
    message.setText(body);

    Transport.send(message);
  }
}
