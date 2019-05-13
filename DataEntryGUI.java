import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DataEntryGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainBPane = new BorderPane();
        GridPane gridPane = new GridPane();

        NameField nameField1 = new NameField();
        NameField nameField2 = new NameField();
        NameField nameField3 = new NameField();

        PhoneField phoneField1 = new PhoneField();
        PhoneField phoneField2 = new PhoneField();
        PhoneField phoneField3 = new PhoneField();

        nameField1.getNameField().focusedProperty().addListener(e ->
        {
            if (nameField1.getNameField().focusedProperty().get())
            {
                nameField1.getNameField().setStyle("-fx-text-fill: black;");
            }
            else
            {
                nameField1.checkTextName();
            }
        });
        nameField2.getNameField().focusedProperty().addListener(e ->
        {
            if (nameField2.getNameField().focusedProperty().get())
            {
                nameField2.getNameField().setStyle("-fx-text-fill: black;");
            }
            else
            {
                nameField2.checkTextName();
            }
        });
        nameField3.getNameField().focusedProperty().addListener(e ->
        {
            if (nameField3.getNameField().focusedProperty().get())
            {
                nameField3.getNameField().setStyle("-fx-text-fill: black;");
            }
            else
            {
                nameField3.checkTextName();
            }
        });
        phoneField1.getPhoneField().focusedProperty().addListener(e ->
        {
            if (phoneField1.getPhoneField().focusedProperty().get())
            {
                phoneField1.getPhoneField().setStyle("-fx-text-fill: black;");
            }
            else
            {
                phoneField1.checkTextPhone();
            }
        });
        phoneField2.getPhoneField().focusedProperty().addListener(e ->
        {
            if (phoneField2.getPhoneField().focusedProperty().get())
            {
                phoneField2.getPhoneField().setStyle("-fx-text-fill: black;");
            }
            else
            {
                phoneField2.checkTextPhone();
            }
        });
        phoneField3.getPhoneField().focusedProperty().addListener(e ->
        {
            if (phoneField3.getPhoneField().focusedProperty().get())
            {
                phoneField3.getPhoneField().setStyle("-fx-text-fill: black;");
            }
            else
            {
                phoneField3.checkTextPhone();
            }
        });

        nameField2.checkTextName();
        nameField3.checkTextName();
        phoneField1.checkTextPhone();
        phoneField2.checkTextPhone();
        phoneField3.checkTextPhone();

        Btn newBtn = new Btn(nameField1, nameField2, nameField3, phoneField1, phoneField2, phoneField3);
        newBtn.checkIfEmpty();


        gridPane.setPadding(new Insets(50));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        gridPane.add(nameField1.getNameField(), 0,0);
        gridPane.add(nameField2.getNameField(), 0,2);
        gridPane.add(nameField3.getNameField(), 0, 4);
        gridPane.add(phoneField1.getPhoneField(), 2, 0);
        gridPane.add(phoneField2.getPhoneField(), 2, 2);
        gridPane.add(phoneField3.getPhoneField(), 2, 4);
        gridPane.add(newBtn.getCreateProfileBtn(), 1,6);


        mainBPane.setCenter(gridPane);
        mainBPane.setAlignment(gridPane, Pos.CENTER);

        mainBPane.setPadding(new Insets(20));


        newBtn.getCreateProfileBtn().setOnAction(e -> {
            newBtn.alert();
        });





        primaryStage.setTitle("Data Entry GUI");
        primaryStage.setScene(new Scene(mainBPane, 635, 300));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

