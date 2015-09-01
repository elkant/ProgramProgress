/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Maureen
 */
public class AutoBackup extends HttpServlet {

   HttpSession session;
String today,path,ExistingPath,filePath,lastBackUp;
int foundClients,foundRegister;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConnect conn = new dbConnect();
        
           //CREATE A PATH IN THE COMPUTER FOR STORING TEXT FILES
           //IdGenerator IG = new IdGenerator();
        
        
        Date date= new Date();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(date);
           today=formattedDate;
           //today=IG.toDay();
           foundClients=foundRegister=0;
    String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
        //dbconnpath=mydrive+":\\MNHC_SYSTEM_APHIA_PLUS\\"; 
  path=mydrive+":\\PPT_UPLOADS\\DO_NOT_DELETE\\_\\_\\."; 
  ExistingPath=mydrive+":\\PPT_UPLOADS\\DO_NOT_DELETE\\_\\_\\LastBackUp.txt";     
  System.out.println(ExistingPath);
           File f = new File(ExistingPath);
           if(f.isFile() && !f.isDirectory()){
           System.out.println("The file exist");
           
          
            String fpath = mydrive+ ":/PPT_UPLOADS/DO_NOT_DELETE/_/_/LastBackUp.txt";
  
            FileInputStream fstream = new FileInputStream(fpath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stLine;
            //Read File Line By Line
            int count = 0;
            if((stLine = br.readLine()) != null) {
            lastBackUp=stLine;
            System.out.println("last date is : "+lastBackUp);
//            Check if date has gone past 1 day and data exist for back up...............
          if(!today.equals(lastBackUp)){
//             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkClients="SELECT COUNT(activityID) FROM indicatoractivities1 WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + "BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d') ";
             System.out.println(checkClients);
              conn.rs=conn.state.executeQuery(checkClients);
              if(conn.rs.next()==true){
                  foundClients=conn.rs.getInt(1);
              }
             
            if(foundClients==0){
                //             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkRegister="SELECT COUNT(activityID) FROM indicatoractivities WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + " BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d')";
              System.out.println(checkRegister);
              conn.rs=conn.state.executeQuery(checkRegister);
              if(conn.rs.next()==true){
                  foundRegister=conn.rs.getInt(1);
              }
            } 
            if(foundClients==0){
                //             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkRegister="SELECT COUNT(resultID) FROM indicatorachieved WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + " BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d')";
              System.out.println(checkRegister);
              conn.rs=conn.state.executeQuery(checkRegister);
              if(conn.rs.next()==true){
                  foundRegister=conn.rs.getInt(1);
              }
            } 
            if(foundClients==0){
                //             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkRegister="SELECT COUNT(resultID) FROM indicatorachievedcombined WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + " BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d')";
              System.out.println(checkRegister);
              conn.rs=conn.state.executeQuery(checkRegister);
              if(conn.rs.next()==true){
                  foundRegister=conn.rs.getInt(1);
              }
            } 
          System.out.println("found clients :   "+foundClients+"     found register   :  "+foundRegister);
          if(foundClients>0 || foundRegister>0){
              System.out.println("===================YOUR DATA WILL BE BACKED UP==================");
    AutoBackups backup = new AutoBackups();
    backup.CreateBackUp(); 
    
//    UPDATE LAST BACK UP DATE------------------------------------
   filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out = new PrintWriter(outFile);

out.print(today);
out.close();
 } catch (IOException e) {} 
    
          }  
          else{
              System.out.println("============no data to back up======================");
         
          }  
          }
          else{
              System.out.println("today's back up has already been created==============");
          }
            
            }
            in.close(); 
           }
           
           else{
               System.out.println("File not found");
              new File(path).mkdirs();
             filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out = new PrintWriter(outFile);

out.print(today);
out.close();

   } catch (IOException e) {}
             
   AutoBackups backup = new AutoBackups();
    backup.CreateBackUp();          
    }

        
//

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
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
