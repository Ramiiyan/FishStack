package Register;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Register extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("reg.fxml"));
        primaryStage.setTitle("Jeff’s Fishing Shack");
        primaryStage.setScene(new Scene(root, 980, 700));
        primaryStage.show();
    }


}
