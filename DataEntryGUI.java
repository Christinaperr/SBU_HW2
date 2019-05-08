package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static java.awt.Color.white;

public class DataEntryGUI extends Application {
    boolean nameField1check;
    boolean nameField2check;
    boolean nameField3check;
    boolean phoneField1check;
    boolean phoneField2check;
    boolean phoneField3check;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainBPane = new BorderPane();
        GridPane gridPane = new GridPane();

        TextField nameField1 = new TextField();
        nameField1.setPromptText("Name");

        TextField nameField2 = new TextField();
        nameField2.setPromptText("Name");

        TextField nameField3 = new TextField();
        nameField3.setPromptText("Name");

        TextField phoneField1 = new TextField();
        phoneField1.setPromptText("(###) ###-####");

        TextField phoneField2 = new TextField();
        phoneField2.setPromptText("(###) ###-####");

        TextField phoneField3 = new TextField();
        phoneField3.setPromptText("###) ###-####");

        Button createProfileBtn = new Button("Create Profiles");

        BooleanBinding textField1Valid = Bindings.createBooleanBinding(() -> {
            if (nameField1.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, nameField1.textProperty());

        BooleanBinding textField2Valid = Bindings.createBooleanBinding(() -> {
            if (nameField2.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, nameField2.textProperty());

        BooleanBinding textField3Valid = Bindings.createBooleanBinding(() -> {
            if (nameField3.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, nameField1.textProperty());

        BooleanBinding textField4Valid = Bindings.createBooleanBinding(() -> {
            if (phoneField1.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, phoneField1.textProperty());

        BooleanBinding textField5Valid = Bindings.createBooleanBinding(() -> {
            if (phoneField2.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, phoneField2.textProperty());

        BooleanBinding textField6Valid = Bindings.createBooleanBinding(() -> {
            if (phoneField3.getText().isEmpty()) {
                return false;
            } else {
                return true;
            }
        }, phoneField3.textProperty());

        createProfileBtn.disableProperty().bind(textField1Valid.not().or(textField2Valid.not().or(textField3Valid.not().and(textField4Valid.not().or(textField5Valid.not().or(textField6Valid.not()))))));
       // createProfileBtn.setDisable(true);

        gridPane.setPadding(new Insets(50));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        gridPane.add(nameField1, 0,0);
        gridPane.add(nameField2, 0,2);
        gridPane.add(nameField3, 0, 4);
        gridPane.add(phoneField1, 2, 0);
        gridPane.add(phoneField2, 2, 2);
        gridPane.add(phoneField3, 2, 4);


        mainBPane.setCenter(gridPane);
        mainBPane.setBottom(createProfileBtn);
        mainBPane.setAlignment(gridPane, Pos.CENTER);
        mainBPane.setAlignment(createProfileBtn, Pos.BOTTOM_CENTER);

        mainBPane.setPadding(new Insets(20));



        createProfileBtn.setOnAction(e -> {
            String name1 = nameField1.getText();
            String name2 = nameField2.getText();
            String name3 = nameField3.getText();
            String phone1 = phoneField1.getText();
            String phone2 = phoneField2.getText();
            String phone3 = phoneField3.getText();

            if (!phone1.matches("[(](\\d{3})[)](\\d{3})[-](\\d{4})")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Phone Number is wrong format! Please enter again!",
                        ButtonType.CLOSE);
                    alert.showAndWait();
                    phoneField1.requestFocus();
                    phoneField1.setStyle("-fx-highlight-text-fill: red;");
                }
            if (!phone2.matches("[(](\\d{3})[)](\\d{3})[-](\\d{4})")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Phone Number is wrong format! Please enter again!",
                        ButtonType.CLOSE);
                alert.showAndWait();
                phoneField2.requestFocus();
                phoneField1.setStyle("-fx-highlight-text-fill: red;");
            }
            if(!phone3.matches("[(](\\d{3})[)](\\d{3})[-](\\d{4})")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Phone Number is wrong format! Please enter again!",
                        ButtonType.CLOSE);
                alert.showAndWait();
                phoneField3.requestFocus();
                phoneField3.setStyle("-fx-highlight-text-fill: red;");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The profiles have been saved and added to the database.", ButtonType.CLOSE);
                alert.showAndWait();
                nameField1.setEditable(false);
                nameField2.setEditable(false);
                nameField3.setEditable(false);
                phoneField1.setEditable(false);
                phoneField2.setEditable(false);
                phoneField3.setEditable(false);
            }
        });





        primaryStage.setTitle("Data Entry GUI");
        primaryStage.setScene(new Scene(mainBPane, 470, 300));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
