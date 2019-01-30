package Connectivity;

import Home.HomepgController;
import Login_Stage.LoginController;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sun.security.util.Password;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SendEmail {

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
       private String username = "jefffishing73@gmail.com";
       private String password = "Jeff'sfishing123";
    public SendEmail(String address,String receiver) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

            try {
                
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("jefffishing73@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(address));
                message.setSubject("Get started With Jeff Fishing Shack");
                message.setText("Dear "+ receiver +"," + "\n\n You successfully Register to Jeff's Fishing Shack.\n" +
                        "From now You will receive monthly Newsletters.\nThank you.");


                Transport.send(message);

                System.out.println("Email sent");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

    }
    public SendEmail(String toAddress,String cardnumber,String grandtotal) {
        System.out.println(toAddress);
        String from="jefffishing73@gmail.com";
        Properties props = new Properties();
        String host ="smtp.gmail.com";
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject("Tax Invoice");
            BodyPart messageBodyPart = new MimeBodyPart();
            //messageBodyPart.setText("here's the file " );
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            //messageBodyPart = new MimeBodyPart();
            //DataSource source = new FileDataSource(filename);
            //messageBodyPart.setDataHandler(new DataHandler(source));
            //messageBodyPart.setFileName(filename);
            //multipart.addBodyPart(messageBodyPart);
            //messageBodyPart.setContent(multipart);
            message.setContent(taxinvoice(cardnumber,grandtotal), "text/html");
            Transport tr = session.getTransport("smtps");
            tr.connect(host, username, password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail sent Successfully");
            tr.close();
            Transport.send(message);

        }catch(Exception e){
            e.printStackTrace();
        }


    }
    private String taxinvoice(String cardnumber,String grandtotal) throws SQLException {

        ResultSet rs2 = connection.createStatement().executeQuery("SELECT * FROM User_bill WHERE Date_Time='" + HomepgController.getdate("yyyy/MM/dd") +
                "' AND CustomerID='" + LoginController.getUserID() + "';");
        String invoice="<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>A simple, clean, and responsive HTML invoice template</title>\n" +
                "    \n" +
                "    <style>\n" +
                "    .invoice-box {\n" +
                "        max-width: 800px;\n" +
                "        margin: auto;\n" +
                "        padding: 30px;\n" +
                "        border: 1px solid #eee;\n" +
                "        box-shadow: 0 0 10px rgba(0, 0, 0, .15);\n" +
                "        font-size: 16px;\n" +
                "        line-height: 24px;\n" +
                "        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n" +
                "        color: #555;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table {\n" +
                "        width: 100%;\n" +
                "        line-height: inherit;\n" +
                "        text-align: left;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table td {\n" +
                "        padding: 5px;\n" +
                "        vertical-align: top;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr td:nth-child(2) {\n" +
                "        text-align: right;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.top table td {\n" +
                "        padding-bottom: 20px;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.top table td.title {\n" +
                "        font-size: 45px;\n" +
                "        line-height: 45px;\n" +
                "        color: #333;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.information table td {\n" +
                "        padding-bottom: 40px;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.heading td {\n" +
                "        background: #eee;\n" +
                "        border-bottom: 1px solid #ddd;\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.details td {\n" +
                "        padding-bottom: 20px;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.item td{\n" +
                "        border-bottom: 1px solid #eee;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.item.last td {\n" +
                "        border-bottom: none;\n" +
                "    }\n" +
                "    \n" +
                "    .invoice-box table tr.total td:nth-child(2) {\n" +
                "        border-top: 2px solid #eee;\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "    \n" +
                "    @media only screen and (max-width: 600px) {\n" +
                "        .invoice-box table tr.top table td {\n" +
                "            width: 100%;\n" +
                "            display: block;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        \n" +
                "        .invoice-box table tr.information table td {\n" +
                "            width: 100%;\n" +
                "            display: block;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    .rtl {\n" +
                "        direction: rtl;\n" +
                "        font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n" +
                "    }\n" +
                "    \n" +
                "    .rtl table {\n" +
                "        text-align: right;\n" +
                "    }\n" +
                "    \n" +
                "    .rtl table tr td:nth-child(2) {\n" +
                "        text-align: left;\n" +
                "    }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"invoice-box\">\n" +
                "        <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                "            <tr class=\"top\">\n" +
                "                <td colspan=\"2\">\n" +
                "                    <table>\n" +
                "                        <tr>\n" +
                "                            <td class=\"title\">\n" +
                "                                <img src=\"https://i.imgur.com/NaSBA3y.png\" style=\"width:100%; max-width:300px;\">\n" +
                "                            </td>\n" +
                "                            \n" +
                "                            <td>\n" +
                "                                Created:"+ HomepgController.getdate("MMMM dd, yyyy")+"<br>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"information\">\n" +
                "                <td colspan=\"2\">\n" +
                "                    <table>\n" +
                "                        <tr>\n" +
                "                            <td>\n" +
                "                                Jeff's Fishing Shack, .Pvt <br>\n" +
                "                                12345 Sunny Road<br>\n" +
                "                                wellawatta, Colombo 06\n" +
                "                            </td>\n" +
                "                            \n" +
                "                            <td>\n" +
                "                                Jeff's Fishing Shack Corp.<br>\n" +
                "                                Jeff john<br>\n" +
                "                                \n" +
                "                                jefffishing73@gmail.com\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"heading\">\n" +
                "                <td>\n" +
                "                    Payment Method\n" +
                "                </td>\n" +
                "                \n" +
                "                <td>\n" +
                "                    Card\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"details\">\n" +
                "                <td>\n" +
                "                    Card\n" +
                "                </td>\n" +
                "                \n" +
                "                <td>\n" +cardnumber  +
                "                </td>\n" +
                "            </tr>\n" +
                "            </table>\n" +
                "            <table>\n" +
                "            <tr class=\"heading\">\n" +
                "                <td>\n" +
                "                    Items\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    Unit Price\n" +
                "                </td>\n"+
                "                <td>\n" +
                "                    Qty\n" +
                "                </td>\n"+
                "                <td>\n" +
                "                    Price\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n";
        while(rs2.next()){
            String itemName =rs2.getString("Product Name");
            double unitprice=rs2.getDouble("Product Price");
            int qty=rs2.getInt("Quantity");
            double subtotal=rs2.getDouble("Sub Total");
            invoice+=
                "            <tr class=\"item\">\n" +
                "                <td>\n" +itemName   +
                "                </td>\n" +
                "                <td>\n" + unitprice+
                "                </td>\n"+
                "                <td>\n" +qty +
                "                </td>\n"+
                "                <td>\n" +subtotal;
        }
                invoice+="                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"total\">\n" +
                "                <td></td>\n" +
                "                \n" +
                "                <td></td>\n" +
                "                <td></td>\n" +
                "                <td></td>\n" +
                "                <td>\n" +
                "                   Total:"+ grandtotal +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";


        return invoice;
    }
}
