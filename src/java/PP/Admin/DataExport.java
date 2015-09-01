/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class DataExport extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
            
            
            //mysqldump -u<user> -p<password> -h<host> --where=jtaskResult=2429 --tab=<file.csv> <database> TaskResult
         String dbname,dbuser,dbpassword;
boolean executed=false;
String dbpath,dbpathD,dbpathE,dbpathF,dbpathG,dbpathM;
String found_folder,full_date,path,computername,senderofmail;
HttpSession session;
String [] myalphabet={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConnect conn = new dbConnect();
        PrintWriter out = response.getWriter();
        
        
       String startdate="";
       String enddate="";
       
       if(request.getParameter("startdate")!=null && !request.getParameter("startdate").equals("")){
            startdate=request.getParameter("startdate");
       }
       if(request.getParameter("enddate")!=null && !request.getParameter("enddate").equals("")){
            enddate=request.getParameter("enddate");
       }
       
       System.out.println(startdate);
       System.out.println(enddate);
dbname="dic";
dbuser="root";
dbpassword="";
String nextpage="";
found_folder="";
//MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
//        GET CURRENT DATE:
        Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int day=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
    


Date date= new Date();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
                String formattedDate = formatter.format(date);

 full_date="Created_On_"+year+"_"+month+"_"+day+"_"+hour+"_"+min+"_"+sec;
 String full_dates=day+" / "+month+" / "+year+"  :and the exact time is  "+hour+":"+min+":"+sec;  
  URL location = dbConnect.class.getProtectionDomain().getCodeSource().getLocation();
  if(session.getAttribute("Username")!=null){
        senderofmail=" which has been send by system user by name "+session.getAttribute("Username").toString();
        
        }
        else{
        senderofmail="";
        }
if("127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress().toString())){
//         System.out.println("you are offline");
         session.setAttribute("network_error", "<font color=\"red\">You are not connected to the internet. Please connect to the internet and try again.</font>");
    

 
    }
     computername = InetAddress.getLocalHost().getHostName();
  
//         
          String  executeCmd="";  

        
             if(conn.dbsetup[3]!=null){
             dbpassword=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             dbuser=conn.dbsetup[2];
                 
             
             }
             
             
             
             if(conn.dbsetup[1]!=null){
                 
             dbname=conn.dbsetup[1];                 
             
             }
             System.out.println("PASSWORD IS :"+dbpassword);
             System.out.println("USER IS :"+dbuser);
             System.out.println("DBNAME IS :"+dbname);
  
             
               // for (int i=0;i<myalphabet.length;i++){
                   // System.out.println("at position  :  "+myalphabet[i]);
             // String current_drive=myalphabet[i];
              File f = new File("C:\\wamp3\\mysql\\bin\\");
              File f1 = new File("C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin");
//              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
              File f3 = new File("C:\\DIC_DBBACKUP\\DBCONNECTION");

              //     CREATE A DIRECTORY AND THE FILE TO HOLD DATA
              if (f3.exists() && f3.isDirectory()) {
  path="C:\\DIC_DBBACKUP\\DBCONNECTION\\DO_NOT_DELETE\\";
            new File(path).mkdirs(); 
           dbpath=path+"\\"+full_date+"_DIC.sql";
}
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             
              
if (f.exists() && f.isDirectory()){
    
//mysqldump -u<user> -p<password> -h<host> --where=jtaskResult=2429 --tab=<file.csv> <database> TaskResult
executeCmd = "C:\\wamp3\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table -u "+dbuser+" -p"+dbpassword+" "+dbname+" indicatorachieved -r "+dbpath+"";

found_folder="it is old wamp";
}

if (f1.exists() && f1.isDirectory()){
  executeCmd = "C:\\wamp3\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table -u "+dbuser+" -p"+dbpassword+" "+dbname+" indicatorachieved -r "+dbpath+"";

//executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" --where=timestamp>='"+startdate+"' -r "+dbpath+"";//taskauditor --where=timestamp='2014-02-20'

found_folder="it is new wamp";
}
   
             
     System.out.println(executeCmd);        
             
         
System.out.println(found_folder);
System.out.println("");
Process runtimeProcess;
        try {
 System.out.println("trying to back up the data.");
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
             int processComplete = runtimeProcess.waitFor();
            System.out.println("at 1 is "+processComplete);
            if (processComplete == 0) {
//                SEND TO THE MAIL BACKED UP DATA.
                System.out.println("Backup created successfully");
                
                if(!"127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress().toString())){
                Send_Data dt=new Send_Data();
                dt.Sendattachment(full_dates, dbpath,computername,senderofmail);
                session.setAttribute("network_error", "Backup has been created and send via mail");
                out.println("Backup has been created and send via mail....");
                }
                else{
               
                session.setAttribute("network_error", "Backup has been created but NOT send via mail due to problems in internet connection");
                  out.println("Backup has been created but NOT send via mail due to problems in internet connection........");
                }
				 
            } else {
                System.out.println("Could not create the backup");
               out.println("Could not create the backup");
				session.setAttribute("network_error", "Backup Could not be created");
                                out.println("Backup Could not be created.......");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        response.sendRedirect("admin/index_admin.jsp");
    
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
            Logger.getLogger(DataExport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DataExport.class.getName()).log(Level.SEVERE, null, ex);
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
}
