/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class importSQLData extends HttpServlet {


   String dbname,dbuser,dbpassword;
boolean executed=false;
String dbpath="";
String found_folder,full_date,path,computername,senderofmail;
HttpSession session;
String [] myalphabet={"B","C","D","E","F","G","H","I","J","K","L","M","N","O","Q","R","S","T","U","V","W","X","Y","Z"};
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       dbConnect conn = new dbConnect();
        String filename="";
        if(request.getParameter("sqlfile")!=null){
        filename=request.getParameter("sqlfile");
        }
        else{
        filename="no_file.sql";
        
        }
     //        GET CURRENT DATE:

  URL location = dbConnect.class.getProtectionDomain().getCodeSource().getLocation();
 String   mydriv = location.getFile().substring(1, 2);   
        
String machineuname =System.getProperty("user.name");
 

System.out.println("Machine name is::"+machineuname);


     //dbpath=mydriv+":\\Users\\"+machineuname+"\\Downloads\\"+filename;   
     dbpath=mydriv+":\\PPT_UPLOADS\\BACKUP\\"+filename;   
       System.out.println(dbpath); 
        session=request.getSession();
     
        //this database name should be static to avoid replicationg the master database
dbname="programprogresstemp";
dbuser="root";
dbpassword="";

found_folder="";



     computername = InetAddress.getLocalHost().getHostName();
    if(1==1){
//         
          String  executeCmd="";  
String[] executeCmd1=null;
        
             if(conn.dbsetup[3]!=null){
             dbpassword=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             dbuser=conn.dbsetup[2];
                 
             
             }
             
             
             
//             if(conn.dbsetup[1]!=null){
//                 
//             dbname=conn.dbsetup[1];                 
//             
//             }
             System.out.println("PASSWORD IS :"+dbpassword);
             System.out.println("USER IS :"+dbuser);
             System.out.println("DBNAME IS :"+dbname);
  
             
                for (int i=0;i<myalphabet.length;i++){
                    System.out.println("at position  :  "+myalphabet[i]);
              String current_drive=myalphabet[i];
              File f =  new File(current_drive+":\\wamp\\mysql\\bin\\");
              File f1 = new File(current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
             

        
  
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             
              
if (f.exists() && f.isDirectory()){

executeCmd1 = new String[]{current_drive+":\\wamp\\mysql\\bin\\mysql", "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


System.out.println(executeCmd);
found_folder="it is old wamp";
}
if (f1.exists() && f1.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysql", "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


System.out.println(executeCmd);

found_folder="it is new wamp";
}
 if (f2.exists() && f2.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql", "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


found_folder="it is workbench";
System.out.println(executeCmd);
}
}      
             
             
             
             
System.out.println(found_folder);

Process runtimeProcess;
        try {
 System.out.println("trying to import the data.");
 
 
 //if the selected database is biometric_master_db,do not import a database since 
 
 
runtimeProcess = Runtime.getRuntime().exec(executeCmd1);



System.out.println("process started");
             int processComplete = runtimeProcess.waitFor();
            System.out.println("at 1 is "+processComplete);
            if (processComplete == 0) {

                System.out.println("Import completed successfully");
           
                
                session.setAttribute("datasend1", "<font color=\"green\">Data has been imported successfully</font>");
               
              
				 
            } else {
                System.out.println("Could not import data");
	session.setAttribute("datasend1", "Data not imported");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    response.sendRedirect("concatenateData");
    
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
        processRequest(request, response);
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
        processRequest(request, response);
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

