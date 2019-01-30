package Login_Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Jeff’s Fishing Shack");
        primaryStage.setScene(new Scene(root, 980, 674));
        primaryStage.show();
    }


     public void alertMsg(String Message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }
    public void infoMsg(String Message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }
    public void loadWindow(String location, String title) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource(location));
        Scene scene= new Scene(root);
        Stage stage= new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
