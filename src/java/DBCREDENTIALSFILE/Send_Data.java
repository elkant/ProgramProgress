/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;


import PP.Admin.dbConnect;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

public class Send_Data {
public String filenames,cu,dates,computername,senderofmail,location;
    HttpSession session;  
    
 String full_date;
    public Send_Data(){
    }
  
 public boolean Sendattachment (String date,String path,String comp,String senderofemail,String filname,String Location)throws MessagingException, SQLException {
     String toAddress1="";
     String toAddress="";
     
     dbConnect conn= new dbConnect();
       try {
            conn.rs=conn.state.executeQuery("select email from mail");
            
            while(conn.rs.next()){
            
                
            toAddress=conn.rs.getString(1);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(mailtom_and_e.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        filenames=path; 
        full_date=date;
        computername=comp;
        senderofmail=senderofemail;
        location=Location;
         if(!toAddress.equals("")){
        toAddress+=",";
        }
       
              String versionname="";
       String version ="select * from version";
        conn.rs3 = conn.state3.executeQuery(version);
        while(conn.rs3.next()){
        versionname = conn.rs3.getString("version_name").replace(" ","_");
        }
     String textBody="Hi ,\n Attached is PPMT data back up as at "+full_date+" "+senderofmail+".("+Location+"). \n To merge this data , "
                + "\n(1)Download the attachment to any folder of choice;"
                + " \n(2) Over your mouse on the Data menu and select Merge Data.\n"
                + "(3)From the merging page that opens, Browse and select the sql file you have downloaded.\n"
                + "(4)Click on Merge and wait for a success message";
     
     
System.out.println(textBody);
         toAddress+="EKaunda@fhi360.org,mobuya@aphiarift.org";
        String host = "smtp.gmail.com";
        String Password ="plusaphia";
        String from = "aphiabackup@gmail.com";
        // toAddress = "aphiapluschwsattendance@gmail.com";  filled above...
        String filename = filenames;
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, toAddress);

        message.setSubject("PPMT 1.25 SQL DATA BACK_UP From : "+computername);

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText(textBody);

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));

       messageBodyPart.setFileName(filname+""+versionname+"ppmt.sql");
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try {
            javax.mail.Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();
            
            return true;

        } catch (SendFailedException sfe) {

            System.out.println(sfe);
            return false;
        }
    }

//    public static void main(String args[]) {
////        try {
////            SendMail sm = new SendMail();
////        } catch (MessagingException ex) {
////            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
////        }
//    }
}