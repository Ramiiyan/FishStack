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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class jeffController implements Initializable {
        @FXML
        private TableView<CustomerInfo1> billTbl ;

        //private TableColumn<CustomerInfo1,String> dateCol ;
        @FXML
        private TableColumn <CustomerInfo1,Integer>customerIdCol ;
        @FXML
        private TableColumn <CustomerInfo1,String>proIdCol ;
        @FXML
        private TableColumn <CustomerInfo1,String>proNameCol ;
        @FXML
        private TableColumn <CustomerInfo1,Double>proPriceCol ;
        @FXML
        private TableColumn <CustomerInfo1,Integer>qtyCol ;
        @FXML
        private TableColumn <CustomerInfo1,Double>subTotalCol  ;
        @FXML
        private ComboBox<String> dateCombo;
        private ObservableList<CustomerInfo1> data;  //initialize observable list to hold db
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        private String newDate;
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            ArrayList<String> dateof = new ArrayList<>();
            System.out.println("READ");
            System.out.println(this.getClass().getSimpleName() + ".initialize");

            try {
                ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM user_bill;");
                while(rs1.next()){
                    String datesforcombo=rs1.getString("Date_Time");
                    dateof.add(datesforcombo);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            dateCombo.getItems().addAll(dateof);

            dateCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                   newDate=newValue;

                }
            });
        }
        @FXML
        private void loadDB1(ActionEvent event) throws SQLException {
            String date1= new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            data = FXCollections.observableArrayList();
            String date,proid,proname;
            int cusid,qty;
            double price,subttl;
            //ResultSet rs=connection.createStatement().executeQuery("SELECT * FROM user_bill WHERE Date_Time='"+date1+"' AND CustomerID='"+ LoginController.getUserID() +"';");

            ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM user_bill WHERE Date_Time='"+newDate+"';");


            while (rs1.next()){
                cusid=rs1.getInt("CustomerID");
                proid=rs1.getString("ProductID");
                proname=rs1.getString("Product Name");
                price=rs1.getDouble("Product Price");
                qty=rs1.getInt("Quantity");
                subttl=rs1.getDouble("Sub Total");

                data.add(new CustomerInfo1(cusid,proid,proname,price,qty,subttl));
            }

            //dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            proIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
            proNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
            proPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
            qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            subTotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

            billTbl.setItems(null);
            billTbl.setItems(data);

        }
}
