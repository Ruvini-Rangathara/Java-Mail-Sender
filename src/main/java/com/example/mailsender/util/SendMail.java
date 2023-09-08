package com.example.mailsender.util;

import com.example.mailsender.controller.MainFormController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class SendMail extends Thread {
    private final String senderEmail;
    private final String recipientEmail;
    private final String subject;
    private final String messageText;
    private final File selectedFile;
    private final String password;
    Properties properties = new Properties();

    public SendMail(String recipientEmail, String subject, String messageText, File selectedFile) throws IOException {
        this.senderEmail = "ichat925@gmail.com";
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.messageText = messageText;
        this.selectedFile = selectedFile;
        this.password = Config.getInstance().getPassword();
    }

    @Override
    public void run() {
        // Set up mail server properties

        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server
        properties.put("mail.smtp.port", "587"); // Replace with your SMTP server's port
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                try {
                    return new PasswordAuthentication("ichat925@gmail.com", Config.getInstance().getPassword()); // Replace with your email and password
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        try {
            // Send the email

            // Create a new MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender's email address
            message.setFrom(new InternetAddress("ichat925@gmail.com")); // Replace with your email

            // Set the recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Replace with the recipient's email

            // Set the email subject
            message.setSubject(subject);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Create the email text part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(messageText);
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            if (selectedFile != null) {
                DataSource source = new FileDataSource(selectedFile);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(selectedFile.getName());
                multipart.addBodyPart(messageBodyPart);
            }

            // Set the message content
            message.setContent(multipart);


            // Send the message
            Transport.send(message);

            Platform.runLater(() -> {
                Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION, "Email sent successfully!", ButtonType.OK, ButtonType.CANCEL).showAndWait();
                if (choose.get() == ButtonType.OK) {

                }
                System.out.println("Email sent successfully!");
            });


        } catch (MessagingException e) {
            e.printStackTrace();

            Platform.runLater(() -> {
                // Show an alert on the JavaFX application thread for error handling
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error sending email: " + e.getMessage());
                alert.showAndWait();
            });
        }

    }

}
