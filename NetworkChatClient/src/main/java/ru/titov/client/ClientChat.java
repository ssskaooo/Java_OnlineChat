package ru.titov.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.titov.client.controllers.AuthController;
import ru.titov.client.controllers.ClientController;

import java.io.IOException;

public class ClientChat extends Application {

    public static ClientChat INSTANCE;

    private static final String CHAT_WINDOW_FXML = "chat-template.fxml";
    private static final String AUTH_DIALOG_FXML = "authDialog.fxml";

    private Stage primaryStage;
    private Stage authStage;
    private FXMLLoader chatWindowLoader;
    private FXMLLoader authLoader;


    @Override
    public void init() throws Exception {
        INSTANCE = this;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        initViews();
        getChatStage().show();
        getAuthStage().show();
        getAuthController().initializeMessageHandler();
    }

    private void initViews() throws IOException {
        initChatWindow();
        initAuthDialog();
    }

    private void initChatWindow() throws IOException {
        chatWindowLoader = new FXMLLoader();
        chatWindowLoader.setLocation(ClientChat.class.getResource(CHAT_WINDOW_FXML));

        Parent root = chatWindowLoader.load();
        this.primaryStage.setScene(new Scene(root));
    }

    private void initAuthDialog() throws java.io.IOException {
        authLoader = new FXMLLoader();
        authLoader.setLocation(ClientChat.class.getResource(AUTH_DIALOG_FXML));
        Parent authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));
    }

    public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        Application.launch();
    }

    public Stage getAuthStage() {
        return authStage;
    }

    public Stage getChatStage() {
        return primaryStage;
    }

    private AuthController getAuthController() {
        return authLoader.getController();
    }

    private ClientController getChatController() {
        return chatWindowLoader.getController();
    }

    public void switchToMainChatWindow(String username) {
        getChatStage().setTitle(username);
        getChatController().initializeMessageHandler();
        getAuthController().close();
        getAuthStage().close();
    }
}