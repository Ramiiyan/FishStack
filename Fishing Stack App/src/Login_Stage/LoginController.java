package Login_Stage;

import Connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.*;

public class LoginController extends Login {
    @FXML
    public TextField username;
    public TextField password;
    public Button loginBtn;

    //private static String userName="";
    private static String email="";
    private static String passWord="";
    private static int user_ID;

        public void UserLogin(ActionEvent actionEvent) throws SQLException, IOException {
            boolean alrt = true;
            String  uname=username.getText();
            String pwd=password.getText();
            int id;
            String jeff="jefffishing73@gmail.com";
            String jeffpwd="jeff123";
            String staff="staff@staff.com";
            String staffpwd="staff123";
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_info WHERE Email='"+ uname +"'");

            ResultSet rs1 = ps.executeQuery();

            String sql = "SELECT * FROM user_info ;";
            ResultSet databaseSet = statement.executeQuery(sql);

            if (uname.isEmpty() || pwd.isEmpty()) {
                alertMsg("Fill All required Fields.");

            }else {

                while (databaseSet.next()) {
                    //userName = databaseSet.getString("First Name");
                    email = databaseSet.getString("Email");
                    passWord = databaseSet.getString("Password");


                    if (uname.equals(email) && pwd.equals(passWord)) {
                        System.out.println("login success");
                        while(rs1.next()){
                            user_ID=rs1.getInt("User_ID");
                            System.out.println(user_ID);
                        }
                        try {
                            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                            loadWindow("/Home/home.fxml", "Jeff's Fishing Shack");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                         alrt =false;
                    }else if(uname.equals(jeff) && pwd.equals(jeffpwd)){
                        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                        loadWindow("/Jeff/jeff.fxml", "Welcome Jeff");
                        alrt =false;
                    }else if(uname.equals(staff) && pwd.equals(staffpwd)){
                        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                        loadWindow("/Jeff/staff.fxml", "Welcome Jeff");
                        alrt =false;
                    }
                }
                    if (alrt) {
                        alertMsg("Sorry please Enter the Correct Credentials");
                    }

            }



        }
        public void Register(ActionEvent actionEvent) throws IOException {
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            loadWindow("/Register/reg.fxml ","Sign Up" );

        }
        public void clearscn(ActionEvent event){
            username.clear();
            password.clear();
        }
        // Encapsulation
        public static void setUserID(int user_ID){
            LoginController.user_ID =user_ID;
        }
        public static int getUserID() {
            System.out.println(user_ID);
            return user_ID;
        }

        public static void setEmail(String email){
            LoginController.email=email;
        }
        public static String getEmail(){
            return email;
        }

        public static void setPassWord(String passWord){
            LoginController.passWord=passWord;
        }
        public static  String getPassWord(){
            return passWord;
        }


}
