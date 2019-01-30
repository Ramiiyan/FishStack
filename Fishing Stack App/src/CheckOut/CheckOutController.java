package CheckOut;


import Connectivity.ConnectionClass;
import Connectivity.SendEmail;
import Home.HomepgController;
import Login_Stage.Login;
import Login_Stage.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CheckOutController  implements Initializable {
    @FXML
    private TableView<CustomerInfo> billTbl ;
    @FXML
    private TableColumn<CustomerInfo,String> dateCol ;
    @FXML
    private TableColumn <CustomerInfo,Integer>customerIdCol ;
    @FXML
    private TableColumn <CustomerInfo,String>proIdCol ;
    @FXML
    private TableColumn <CustomerInfo,String>proNameCol ;
    @FXML
    private TableColumn <CustomerInfo,Double>proPriceCol ;
    @FXML
    private TableColumn <CustomerInfo,Integer>qtyCol ;
    @FXML
    private TableColumn <CustomerInfo,Double>subTotalCol  ;
    @FXML
    private TextField cardName,cardNumber,cVV;
    @FXML
    private Label grandtotal;
    @FXML
    private ChoiceBox month,year;
    private ObservableList<CustomerInfo>data;  //initialize observable list to hold db
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    HomepgController home=new HomepgController();
    Login login =new Login();
    int n=1;
    private double total;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("READ");
        System.out.println(this.getClass().getSimpleName() + ".initialize");
    }
    @FXML
    private void loadDB(ActionEvent event) throws SQLException{
        String date1= new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        Connection connection = connectionClass.getConnection();
        data = FXCollections.observableArrayList();
        String date,proid,proname;
        int cusid,qty;
        double price,subttl;
        ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM user_bill WHERE Date_Time='"+date1+"' AND CustomerID='"+LoginController.getUserID() +"';");
        ResultSet rs1=connection.createStatement().executeQuery("SELECT * FROM user_purchase WHERE Date='"+date1+"' AND CustomerID='"+LoginController.getUserID() +"';");
        while (rs1.next()){
           total=rs1.getDouble("Total");
        }
        while (rs.next()){
            date=rs.getString("Date_Time");
            cusid=rs.getInt("CustomerID");
            proid=rs.getString("ProductID");
            proname=rs.getString("Product Name");
            price=rs.getDouble("Product Price");
            qty=rs.getInt("Quantity");
            subttl=rs.getDouble("Sub Total");

            data.add(new CustomerInfo(date,cusid,proid,proname,price,qty,subttl));
        }

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        proIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        proNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        proPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subTotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        billTbl.setItems(null);
        billTbl.setItems(data);
        grandtotal.setText(String.valueOf(total));

    }
    @FXML
    private void pay(ActionEvent event)throws NullPointerException {

        if (cardName.getText().isEmpty() || cardNumber.getText().isEmpty() || cVV.getText().isEmpty()) {
            login.alertMsg("Fill the Card Details");

        }else if(!(cardName.getText().isEmpty())) {
            for (int i = 0; i < cardName.getText().length(); i++) {
                int key1 = cardName.getText().charAt(i);
                if (!(key1 >= 65 && key1 <= 90 || key1 >= 97 && key1 <= 122) && !(key1 == 32)) {
                    login.alertMsg("Enter Card Holder's Name Correctly!");
                    break;
                }
            }
        }else if( !(cardNumber.getText().isEmpty() )){
            for (int i = 0; i < cardNumber.getText().length(); i++) {
                int key2 = cardNumber.getText().charAt(i);
                if (!(key2 >= 48 && key2 <= 57)) {
                    login.alertMsg("Enter Your Card Number correctly!");
                    break;
                }
            }

        }else{
            login.infoMsg("Your Tax Invoice sent to you Mail!");
        }
            try {
                SendEmail sentmail = new SendEmail("ramiiyan.harry@gmail.com", cardNumber.getText(), grandtotal.getText());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("mail not send");
            }



    }
}
