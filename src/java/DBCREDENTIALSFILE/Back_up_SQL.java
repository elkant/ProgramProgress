/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import DBCREDENTIALSFILE.Send_Data;
import PP.Admin.dbConnect;
import PP.Admin.dbConnect1;
import Scripts.OSValidator;
import static Scripts.OSValidator.isMac;
import static Scripts.OSValidator.isSolaris;
import static Scripts.OSValidator.isUnix;
import static Scripts.OSValidator.isWindows;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class Back_up_SQL extends HttpServlet {

    String dbname, dbuser, dbpassword;
    boolean executed = false;
    String dbpath, dbpathD, dbpathE, dbpathF, dbpathG, dbpathM,importpath;
    String found_folder, full_date, path, computername, senderofmail;
    HttpSession session;
    String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
 String localhost="";
        String[] localhostsplit;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session = request.getSession();
        dbConnect conn = new dbConnect();

        //OSValidator ov= new OSValidator();
        
        
       localhost="localhost:3306";
        
        
        String lasttimestampid = "1";
        String lasttimestamp = "2014-03-12 11:25:20";
Date dat= new Date();
System.out.println("using admin");
 String date=dat.toString().replace(" ", "_");
        dbname = "programprogress";
        dbuser = "root";
        dbpassword = "";
        String nextpage = "";
        found_folder = "";
                if (isWindows()) {
                        dbpassword = "";
                } 
                else if (isMac()) {}
                else if (isUnix()) {
		dbpassword = "P@ss4M&E!Fhiimpact!";
		}  
       
//MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
//        GET CURRENT DATE:
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
      String filname="";
        full_date = "Created_On_" + year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
        String full_dates = day + " / " + month + " / " + year + "  :and the exact time is  " + hour + ":" + min + ":" + sec;
        URL location = dbConnect.class.getProtectionDomain().getCodeSource().getLocation();
        if (session.getAttribute("Username") != null) {
          filname=session.getAttribute("Username").toString()+"_";

          senderofmail = " which has been send by user :: " + session.getAttribute("Username").toString() + " ";

        } else {
            senderofmail = "";
        }
        
         filname+=date+"_";
        if (isInternetReachable() == false) {
//         System.out.println("you are offline");
            session.setAttribute("datasend", "<font color=\"red\">You are not connected to the internet. Please connect to the internet and try again.</font>");

        }
        computername = InetAddress.getLocalHost().getHostName();
        if (1 == 1) {
//         
            String executeCmd = "";


            if (conn.dbsetup[3] != null) {
                dbpassword = conn.dbsetup[3];
}



            if (conn.dbsetup[2] != null) {

                dbuser = conn.dbsetup[2];


            }



            if (conn.dbsetup[1] != null) {

                dbname = conn.dbsetup[1];

            }
            if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit=localhost.split(":");

            }
            System.out.println("PASSWORD IS :" + dbpassword);
            System.out.println("USER IS :" + dbuser);
            System.out.println("DBNAME IS :" + dbname);


            for (int i = 0; i < myalphabet.length; i++) {
                try {
                    System.out.println("at position  :  " + myalphabet[i]);
                    String current_drive = myalphabet[i];
                    File f = new File(current_drive + ":\\wamp\\mysql\\bin\\");
                    File f1 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.6\\bin");
                    File f2 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.7\\bin");
                    File f4 = new File("etc/init.d");
                    File f3 =null;
                    
                     if (isWindows()) {
                        f3 = new File(current_drive + ":\\PPT_UPLOADS");
                        path = current_drive + ":\\PPT_UPLOADS\\BACKUP";
                        importpath = current_drive + ":\\PPT_UPLOADS\\IMPORTS";
                        dbpath = path + "\\" + full_date + "_PPT.sql";
                } 
                else if (isMac()) {}
                else if (isUnix()) {
		f3 = new File("PPT_UPLOADS");
                path = "PPT_UPLOADS/BACKUP";
                        importpath = "PPT_UPLOADS/IMPORTS";
                        dbpath = path + "/" + full_date + "_PPT.sql";
		} 
                    
                 

                    //     CREATE A DIRECTORY AND THE FILE TO HOLD DATA
                    if (f3.exists() && f3.isDirectory()) {
                        //path="C:\\PPT_UPLOADS\\BACKUP\\";
                        
                        new File(path).mkdirs();
                        new File(importpath).mkdirs();
                        
                    }

                    //select the last timestamp which a backup was picked from.....



                    conn.rs = conn.state.executeQuery("select MAX(timestampid) from timestamp");
                    if (conn.rs.next()) {

                        
      conn.rs1=conn.state1.executeQuery("select timestamp from timestamp where timestampid='"+conn.rs.getString(1)+"'");
          
      if(conn.rs1.next()){
       lasttimestamp = conn.rs1.getString("timestamp");

      System.out.println(conn.rs1.getString("timestamp"));
      
      }
                        
     
                        System.out.println("Timestamp id::"+conn.rs.getString(1));
                        
                        
                        lasttimestampid = conn.rs.getString(1);

                    }

                    //conn.st_6.close();


// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             

                    if (f.exists() && f.isDirectory()) {
                        executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host="+localhostsplit[0]+" --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " indicatorachieved indicatorachievedcombined indicatoractivities indicatoractivities1 indicatoractivity --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
//executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";

                        found_folder = "it is old wamp";
                    }
                    if (f1.exists() && f1.isDirectory()) {


                        executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump --host="+localhostsplit[0]+" --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " indicatorachieved indicatorachievedcombined indicatoractivities indicatoractivities1 indicatoractivity --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                        found_folder = "it is new wamp";
                    }
                    
                    if (f4.exists() && f4.isDirectory()) {
                             //linux

                        executeCmd = " mysqldump --host="+localhostsplit[0]+" --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " indicatorachieved indicatorachievedcombined indicatoractivities indicatoractivities1 indicatoractivity --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                        found_folder = "Ubuntu linux";
                    }
                    
                    if (f2.exists() && f2.isDirectory()) {
                        executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump --host="+localhostsplit[0]+" --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " indicatorachieved indicatorachievedcombined indicatoractivities indicatoractivities1 indicatoractivity --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                        found_folder = "it is workbench";
                    }
                } catch (SQLException ex) {
                    
                    
                         session.setAttribute("datasend", "<font color=\"red\">an SQL Error occured while sending data</font>");
                        
                    Logger.getLogger(Back_up_SQL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

  String Location="";
        String userid= session.getAttribute("userid").toString();
         String getlocation="select * from users where UserID='"+userid+"'";
         System.out.println(getlocation);
         conn.rs2 = conn.state2.executeQuery(getlocation);
         while(conn.rs2.next()){
             Location= conn.rs2.getString("Location");
             System.out.println(Location);
         }


            System.out.println(found_folder);
            System.out.println(executeCmd);
            Process runtimeProcess;
            try {
                System.out.println("trying to back up the data.");
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                System.out.println("at 1 is " + processComplete);
                if (processComplete == 0) {
//                SEND TO THE MAIL BACKED UP DATA.
                    System.out.println("Backup created successfully");

                    if (isInternetReachable() == true) {
                        Send_Data dt = new Send_Data();


                        //============at this pint, if the data i send, then do a new timestamp into the database


                        if (dt.Sendattachment(full_dates, dbpath, computername, senderofmail,filname,Location) == true) {
                            //do an insert
                            conn.state.executeUpdate("update timestamp set datasend='yes' where timestampid='" + lasttimestampid + "'");

            
                            
                            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String mdate;

                           Date mydate = new Date();
                        mdate = formatter.format(mydate);
                            
                            
String daytime=""+year+"-"+month+"-"+day;
                            // a new timestamp that will be called next time a backup is being created.

                            conn.state.executeUpdate("insert into timestamp (timestamp,datasend) values('"+mdate+"','No')");



                            session.setAttribute("datasend", "<font color=\"green\">Backup has been created and send via mail</font>");
                        }
                        
                    else {

                            session.setAttribute("datasend", "<font color=\"red\">Backup has been created but NOT send via mail due to problems in internet connection. Try to send Backup Again.</font>");
                        }

                    } 
                    else {

                        session.setAttribute("datasend", "<font color=\"red\">Backup has been created but NOT send via mail due to problems in internet connection</font>");
                    }

                } else {
                    System.out.println("Could not create the backup");
                    session.setAttribute("datasend", "Backup Could not be created");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
        response.sendRedirect("backup.jsp");

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Back_up_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Back_up_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            URLConnection urlConnect = url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            urlConnect.getInputStream();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println("ALL SMS SCHEDULER:Unknown host");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("ALL SMS SCHEDULER:Error in internet connection");
            return false;
        }
        return true;
    }
}
