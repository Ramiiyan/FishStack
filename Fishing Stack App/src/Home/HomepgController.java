package Home;

import Connectivity.ConnectionClass;
import Login_Stage.Login;
import Login_Stage.LoginController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;


public class HomepgController implements Initializable {
    @FXML
    private ImageView item1,item2,item3,item4,item5,item6,item7,item8;
    @FXML
    private Label prod,preel,phook,pline,psinker,pswivel,pbait,pfloat;
    @FXML
    public Label totalLbl;
    @FXML
    private ComboBox<String> combo1,combo2,combo3,combo4,combo5,combo6 ;
    @FXML
    private TextField qrod,qreel,qhook,qline,qsin,qswi,qbait,qfloat;


    Login login=new Login();
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    private TextField [] quantity;
    private ArrayList<Double>priceamount =new ArrayList<>();
    private ArrayList<String> productId= new ArrayList<>();
    private int indexOfNew;
    public   Double total=0.0;

    private  Double[] sub=new Double[8];   //trod,treel,thook,tline,tsin,tswi,tbait,tfloat
    private Label [] priceLabel;
    private String [] itemName ={"Fishing Rod", "Fishing Reel", "Fishing hooks", "Fishing Line", "Fishing Sinkers", "Swivels", "Fishing bait", "Fishing Float"};
    private String[] itemCode = {"FR01", "FE01", "FH01", "FL01", "FS01", "SW01", "FB01", "FF01"};
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image[] images = new Image[8];
        ImageView[] items = {item1, item2, item3, item4, item5, item6, item7, item8};
        ComboBox[] combolist = {combo1, combo2, combo3, combo4, combo5, combo6};
          priceLabel = new Label[]{prod, preel, phook, pline, psinker, pswivel};
            quantity=new TextField[] {qrod,qreel,qhook,qline,qsin,qswi};
        ArrayList<String> sizelist = new ArrayList<>();
        ArrayList<String> sizelist2 = new ArrayList<>();


        byte b[];
        String combo;
        String productid;
        Blob blob;
        double price;
        double price2;
        for (int i = 0; i < itemCode.length; i++) {
            try {
                File file = new File("image" + i + ".png");
                FileOutputStream fos = new FileOutputStream(file);

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM product_items WHERE Product_Code='" + itemCode[i] + "'");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    blob = rs.getBlob("Image");
                    b = blob.getBytes(1, (int) blob.length());
                    fos.write(b);
                    price2 = rs.getDouble("Product_Price");
                    if (i == 6) {
                        pbait.setText(Double.toString(price2));
                    }
                    if (i == 7) {
                        pfloat.setText(Double.toString(price2));
                    }

                }
                images[i] = new Image("file:image" + i + ".png", 200, 250, true, true);
                items[i].setImage(images[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < combolist.length; i++) {
            try {
                PreparedStatement forcombo = connection.prepareStatement("SELECT * FROM product_items WHERE Product_Name='" + itemName[i] + "'");
                ResultSet rs1 = forcombo.executeQuery();

                while (rs1.next()) {
                    combo = rs1.getString("Size/Weight");
                    sizelist.add(combo);
                    sizelist2.add(combo);
                    price = rs1.getDouble("Product_Price");
                    priceamount.add(price);
                    productid=rs1.getString("Product_Code");
                    productId.add(productid);

                }
                System.out.println(sizelist2);
                combolist[i].getItems().addAll(sizelist);
                System.out.println(priceamount);
                System.out.println(productId);
                sizelist.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }

            combolist[i].getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {

                    indexOfNew = sizelist2.indexOf(newValue);
                    System.out.println("indexofNew  :" + indexOfNew);

                    int n=0;
                    for(int i=0;i<18;i++){
                        if (indexOfNew == i++ || indexOfNew == i++ || indexOfNew == i) {
                            priceLabel[n].setText(Double.toString(priceamount.get(indexOfNew)));
                            quantity[n].getText();
                        } else{
                            n++;
                        }
                    }
                }
            });
        }
    }
    public void rods(ActionEvent actionEvent) throws SQLException {
        findTotal(0);
        insertBill(0);
    }
    public void reels(ActionEvent actionEvent) throws SQLException {
        findTotal(1);
        insertBill(1);
    }
    public void hooks(ActionEvent actionEvent) throws SQLException {
        findTotal(2);
        insertBill(2);
    }
    public void lines(ActionEvent actionEvent) throws SQLException {
        findTotal(3);
        insertBill(3);
    }public void sinkers(ActionEvent actionEvent) throws SQLException {
        findTotal(4);
        insertBill(4);
    }
    public void swivels(ActionEvent actionEvent) throws SQLException {
        findTotal(5);
        insertBill(5);
    }
    public void bait(ActionEvent actionEvent) throws SQLException {
         if(qbait.getText().isEmpty()){
            login.alertMsg("Please Fill Quantity!");
        }else {
            sub[6]= Double.parseDouble(pbait.getText()) * Double.parseDouble(qbait.getText());
            System.out.println(sub[6]);
            total += sub[6];
            totalLbl.setText(String.valueOf(total));
        }
        String sql = "INSERT INTO user_bill (`Date_Time`,`CustomerID`,`ProductID`,`Product Name`,`Product Price`,`Quantity`,`Sub Total`) VALUES ('"
                + getdate("yyyy/MM/dd") + "', '" + LoginController.getUserID() + "', '"+ itemCode[6]  + "', '"+itemName[6]  + "', '"+pbait.getText() + "', '"
                + qbait.getText() + "', '"+ sub[6] + "');";
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate(sql);
    }
    public void floats(ActionEvent actionEvent) throws SQLException {
        if(qfloat.getText().isEmpty()){
            login.alertMsg("Please Fill Quantity!");
        }else {
            sub[7]= Double.parseDouble(pfloat.getText()) * Double.parseDouble(qfloat.getText());
            System.out.println(sub[7]);
            total += sub[7];
            totalLbl.setText(String.valueOf(total));
        }
        String sql = "INSERT INTO user_bill (`Date_Time`,`CustomerID`,`ProductID`,`Product Name`,`Product Price`,`Quantity`,`Sub Total`) VALUES ('"
                + getdate("yyyy/MM/dd") + "', '" + LoginController.getUserID() + "', '"+ itemCode[7]  + "', '"+itemName[7]  + "', '"+pfloat.getText() + "', '"
                + qfloat.getText() + "', '"+ sub[7] + "');";
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate(sql);

    }

    public void checkout(ActionEvent actionEvent)throws SQLException,IOException{
        String sql1="INSERT INTO user_purchase (`Date`,`CustomerID`,`Total`) VALUES ('"
                + getdate("yyyy/MM/dd") + "', '" + LoginController.getUserID() + "', '"+ totalLbl.getText()  + "');";
         PreparedStatement ps1=connection.prepareStatement(sql1);
         ps1.executeUpdate(sql1);


        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        login.loadWindow("/CheckOut/checkOut.fxml ","Check Out" );

    }
    
    private void findTotal(int index){
        if(priceLabel[index].getText().equals("")){
            login.alertMsg("Choose One of the Product Type!");
        }else if(quantity[index].getText().isEmpty()){
            login.alertMsg("Please Fill Quantity!");
        }else {
            sub[index]= Double.parseDouble(priceLabel[index].getText()) * Double.parseDouble(quantity[index].getText());
            System.out.println(sub[index]);
            total += sub[index];
            totalLbl.setText(String.valueOf(total));
        }
    }
    private void insertBill(int index) throws SQLException{
        String sql = "INSERT INTO user_bill (`Date_Time`,`CustomerID`,`ProductID`,`Product Name`,`Product Price`,`Quantity`,`Sub Total`) VALUES ('"
                + getdate("yyyy/MM/dd") + "', '" + LoginController.getUserID() + "', '"+ productId.get(index)  + "', '"+itemName[index]  + "', '"+ priceLabel[index].getText() + "', '"
                + quantity[index].getText() + "', '"+ sub[index] + "');";
        System.out.println(sql);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate(sql);
    }
    public static String getdate(String format) {
        String time=  new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String date= new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
     return date ;



    }

    private int searchResult(int len,String [] arr,String key) {
        /* Selection Sort */
        for (int i=0; i<len-1; i++) {
            for (int j=i+1; j<len; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    String temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        /*Binary Search */
        int indexofNew1=0;
        int first=0;
        while(first<len){
            int mid=(first+len)/2;
            if(key.compareTo(arr[mid])<0){
                len = mid;
            }else if(key.compareTo(arr[mid])>0){
                first =mid+1;
            }else {
                 return mid;
            }
        }
     return -1;
    }


}

