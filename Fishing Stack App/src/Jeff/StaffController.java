package Jeff;


import Connectivity.ConnectionClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    @FXML
    private TableView<CustomerInfo2> billTbl ;
    @FXML
    private TableColumn<CustomerInfo2,String> dateCol ;
    @FXML
    private TableColumn <CustomerInfo2,String>proIdCol ;
    @FXML
    private TableColumn <CustomerInfo2,String>proNameCol ;
    @FXML
    private TableColumn <CustomerInfo2,Double>proPriceCol ;
    @FXML
    private TableColumn <CustomerInfo2,Integer>qtyCol ;
    @FXML
    private TableColumn <CustomerInfo2,Double>subTotalCol  ;
    @FXML
    private ComboBox<String> userCombo;
     ObservableList<CustomerInfo2> data;  //initialize observable list to hold db
    private ConnectionClass connectionClass = new ConnectionClass();
    private Connection connection = connectionClass.getConnection();
    private String newUser;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> userlist = new ArrayList<>();
        System.out.println("READ");
        System.out.println(this.getClass().getSimpleName() + ".initialize");

        try {
            ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM user_bill;");
            while(rs1.next()){
                String useridscombo=rs1.getString("CustomerID");
                userlist.add(useridscombo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        userCombo.getItems().addAll(userlist);

        userCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                newUser=newValue;

            }
        });
    }
    @FXML
    private void loadDB2(ActionEvent event) throws SQLException {

        data = FXCollections.observableArrayList();
        String date,proid,proname;
        int cusid,qty;
        double price,subttl;
        //ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM user_bill WHERE Date_Time='"+date1+"' AND CustomerID='"+ LoginController.getUserID() +"';");

        ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM user_bill WHERE CustomerID='"+newUser+"';");


        while (rs1.next()){
            date=rs1.getString("Date_Time");
            proid=rs1.getString("ProductID");
            proname=rs1.getString("Product Name");
            price=rs1.getDouble("Product Price");
            qty=rs1.getInt("Quantity");
            subttl=rs1.getDouble("Sub Total");

            data.add(new CustomerInfo2(date,proid,proname,price,qty,subttl));
        }

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        //customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        proIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        proNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        proPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subTotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        billTbl.setItems(null);
        billTbl.setItems(data);

    }


    







}
