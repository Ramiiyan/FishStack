package CheckOut;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Checkout extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/CheckOut/checkOut.fxml"));
        primaryStage.setTitle("Jeff’s Fishing Shack");
        primaryStage.setScene(new Scene(root, 800, 676));
        primaryStage.show();
    }


}
