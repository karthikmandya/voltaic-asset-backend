package com.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {

    public void sendSensorEmailNoAuth(String id, String name, double temperature, double humidity) throws MessagingException {
        // recipient and sender
        String to = "transformer@test";
        String from = "no-reply@transformer.com"; // set to an address allowed by your SMTP server

        // SMTP server configuration — point to an MTA that accepts unauthenticated mail from this host
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "localhost");   // change to your SMTP host (e.g., internal-relay.company.local)
        props.put("mail.smtp.port", "25");          // default SMTP port
        props.put("mail.smtp.auth", "false");       // no auth
        props.put("mail.smtp.starttls.enable", "false"); // TLS usually not used for open unauthenticated relay

        // Create session without Authenticator (no credentials)
        Session session = Session.getInstance(props);
        session.setDebug(false); // set true to see SMTP dialog in console

        // Compose message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Attention!! Need urgent resolution: " + name);

        String body = String.format(
                "Hello,\n\nTemperature of below transformer has breached given threshold :\n\nID: %s\nName: %s\nTemperature: %.2f °C\nHumidity: %.2f %%\n\nRegards,\nBosch IoT",
                id, name, temperature, humidity
        );

        message.setText(body);

        // Send - Transport.send will connect to the SMTP server and attempt to send without auth
        Transport.send(message);
        System.out.println("Email sent (no auth) to " + to);
    }
}
