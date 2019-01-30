import Home.HomepgController;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

public class test {
    private static String username = "jefffishing73@gmail.com";
    private static String password = "Jeff'sfishing123";
    private static String address="Ramiiyan.harry@gmail.com";
    public static void main(String[] args) {
        String [] array={"1.0 m","3.0 m","3.6 m","large","Medium","Small","10","7","11"};
        //int indexof=searchResult(array.length,array,"large");
        ArrayList <String> sortedarr=new ArrayList<>();
        String str="hi hello how are you ?";

        /*String [] strArray=str.split("");
        for(int i=0; i < strArray.length; i++){
            System.out.println(strArray[i]);
        }*/

        String row="            <tr class=\"item\">\n" +
                "                <td>\n" +"rod"   +
                "                </td>\n" +
                "                <td>\n" +10 +
                "                </td>\n"+
                "                <td>\n" +15 +
                "                </td>\n" +
                "            </tr>\n" ;
        System.out.println(row);
        /*for(int i=0; i<str.length(); i++){
            int key= str.charAt(i);
            if(!(key>=65 && key<=90 || key>=97 && key<=122) && !(key==32)){
                System.out.println(key);
            }
        } */
        //System.out.println(indexof);

       /* String timeStamp = new SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp);
        String filename="C:\\Users\\DELL\\Desktop\\Java_Projects\\CW\\src\\test.txt";
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
            message.setFrom(new InternetAddress("jefffishing73@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
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
            message.setContent(taxinvoice(), "text/html");
            Transport tr = session.getTransport("smtps");
            tr.connect(host, username, password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail sent Successfully");
            tr.close();
            Transport.send(message);
            
        }catch(Exception e){
            e.printStackTrace();
        } */




    }
    private  String taxinvoice(){
        int n=1;
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
                "                                Invoice #:"+(n++) +"<br>\n" +
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
                "                <td>\n" +1000  +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"heading\">\n" +
                "                <td>\n" +
                "                    Item\n" +
                "                </td>\n" +
                "                \n" +
                "                <td>\n" +
                "                    Price\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"item\">\n" +
                "                <td>\n" +
                "                    Website design\n" +
                "                </td>\n" +
                "                \n" +
                "                <td>\n" +
                "                    $300.00\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"item\">\n" +
                "                <td>\n" +
                "                    Hosting (3 months)\n" +
                "                </td>\n" +
                "                \n" +
                "                <td>\n" +
                "                    $75.00\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"item last\">\n" +
                "                <td>\n" +
                "                    Domain name (1 year)\n" +
                "                </td>\n" +
                "                \n" +
                "                <td>\n" +
                "                    $10.00\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr class=\"total\">\n" +
                "                <td></td>\n" +
                "                \n" +
                "                <td>\n" +
                "                   Total: $385.00\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";


    return invoice;
    }
    public static int searchResult( String[] arr, String key) {
        /* Selection Sort */
        int len=arr.length;
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
            System.out.println("While loop working");
            int mid= first + ((len - first) / 2);
            if(key.compareTo(arr[mid])<0){
                len = mid;
            }else if(key.compareTo(arr[mid])>0){
                first =mid+1;
            }else {
                return mid;
            }
        } return -1;

    }
    public void printBill(){
        String row="            <tr class=\"item\">\n" +
                "                <td>\n" +"rod"   +
                "                </td>\n" +
                "                <td>\n" +10 +
                "                </td>\n"+
                "                <td>\n" +15 +
                "                </td>\n" +
                "            </tr>\n" ;

    }

}
