package com.example.mailsender.controller;

import com.example.mailsender.util.Config;
import com.example.mailsender.util.SendMail;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public JFXButton btnBack;
    @FXML
    private TextArea txtMessage;
    @FXML
    private TextField txtReceiverMail;
    @FXML
    private TextField txtSenderMail;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtAttachedFile;

    @FXML
    private Label lblAttachedFileLabel;

    private File selectedFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBack.setOnAction(event -> Platform.exit());

        lblAttachedFileLabel.setVisible(false);
        txtAttachedFile.setVisible(false);

        txtMessage.setStyle("-fx-control-inner-background:  #393646; -fx-text-fill:  #f4eee0; -fx-border-width: 0;");
        txtReceiverMail.setStyle("-fx-control-inner-background:  #393646; -fx-text-fill:  #f4eee0; ");
        txtSenderMail.setStyle("-fx-control-inner-background:  #393646; -fx-text-fill:  #f4eee0; ");
        txtSubject.setStyle("-fx-control-inner-background:  #393646; -fx-text-fill:  #f4eee0; ");
        txtAttachedFile.setStyle("-fx-control-inner-background:  #393646; -fx-text-fill:  #f4eee0;");

        txtSenderMail.setText("ichat925@gmail.com");

    }


    @FXML
    void txtMessageOnAction(MouseEvent event) {

    }

    @FXML
    void txtReceiverMailOnAction(ActionEvent event) {

    }

    @FXML
    void txtSenderMailOnAction(ActionEvent event) {

    }

    @FXML
    void txtSubjectOnAction(ActionEvent event) {

    }

    @FXML
    void btnAttachmentOnAction(ActionEvent event) {
        lblAttachedFileLabel.setVisible(true);
        txtAttachedFile.setVisible(true);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File to Attach");
<<<<<<< HEAD
//        fileChooser.getExtensionFilters().addAll(
//                new ExtensionFilter("PDF Files", "*.pdf"),
//                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
//                new ExtensionFilter("All Files", "*.*")
//        );


=======
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
>>>>>>> cc43178d8994941fc683260c81298b552179b9e7
        selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

            if ("png".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "jpeg".equalsIgnoreCase(fileExtension) || "gif".equalsIgnoreCase(fileExtension)) {
                txtAttachedFile.setText(selectedFile.getName());
            } else {
                System.out.println("Selected file is not an image.");
            }
        }
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        // Get the email sender, recipient, subject, and message text from the text fields
        String recipientEmail = txtReceiverMail.getText();
        String subject = txtSubject.getText();
        String messageText = txtMessage.getText();

        // Create a new EmailSenderThread
        SendMail emailSenderThread = new SendMail(recipientEmail, subject, messageText, selectedFile);

        // Start the thread to send the email
        emailSenderThread.start();

        clearFields();
    }

    private void clearFields() {
        txtReceiverMail.clear();
        txtSubject.clear();
        txtMessage.clear();
        txtAttachedFile.clear();
        txtAttachedFile.setVisible(false);
        lblAttachedFileLabel.setVisible(false);
    }


}
