package Register;

import Connectivity.ConnectionClass;
import Connectivity.SendEmail;
import Login_Stage.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class RegController extends Register {
    @FXML
    private TextField fn,ln,email,epwd,st,city,state,country,zip;
    Login login=new Login();
    
    public void Reg(ActionEvent event) throws SQLException, IOException {
        System.out.println("Register");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();           // create a Statement from the connection
        String [] regDetails={fn.getText(),ln.getText(),email.getText(),epwd.getText(),st.getText(),city.getText(),state.getText(),country.getText(),zip.getText()};
        int count=0;
        int posat =0;
        int posdot =0;
        String [] ma=email.getText().split("");
        for(int i=0; i<ma.length;i++){
            if("@".equals(ma[i])){
                posat=i;
            }
            if(".".equals(ma[i])){
                posdot=i;
            }
        }


        for(int i=0;i<epwd.getText().length();i++){
            int key= (int)epwd.getText().charAt(i);

            if(!(key>=65 && key<=90 || key>=97 && key<=122)){
                count++;
            }
        }

        for (int i=0; i<regDetails.length; i++) {
            if (regDetails[i].isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setContentText("Fields must be filled!");
                alert.showAndWait();
                break;
            }
        }
            if(posat<2 || posdot<posat+2 || posdot+2>=email.getText().length() ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setContentText("Invalid Email Address!");
                alert.showAndWait();
            }else if(epwd.getText().length()<8 ) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setContentText("password Must be 8 Characters or more!");
                alert.showAndWait();
            }else if(count<2){
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setContentText("Password must contains at least 2 Non-Alphabetic Letters.");
                alert.showAndWait();
            }else {

                String sql = "INSERT INTO user_info (`First Name`, `Last Name`,`Street`,`City`,`State`,`Country`,`Zip`, `Email`, `Password`) VALUES ('"
                        + fn.getText() + "', '" + ln.getText() + "', '"+ st.getText() + "', '"+ city.getText() + "', '"+ state.getText() + "', '"
                        + country.getText() + "', '"+ zip.getText() + "', '" + email.getText() + "', '" + epwd.getText() + "');";
                statement.executeUpdate(sql);   // insert the data
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Welcome");
                alert.setContentText("You Successfully Registered.\nCheck your mail for further Information.\nThank you.");
                alert.showAndWait();
                SendEmail sendmail = new SendEmail(email.getText(), fn.getText());
                ((Node) event.getSource()).getScene().getWindow().hide();
                login.loadWindow("/Home/home.fxml","Jeff's Fishing Shack");

            }

    }
    public void SignIn(ActionEvent actionEvent) throws  IOException {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        login.loadWindow("/Login_Stage/login.fxml","Jeff's Fishing Shack");

    }
}
