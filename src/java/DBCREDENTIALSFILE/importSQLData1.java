/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Maureen
 */
public class importSQLData1 extends HttpServlet {

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
     private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "PPTDATA";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 20;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024*4;
    HttpSession session;
    String filename="";
String dbname,dbuser,dbpassword;
boolean executed=false;
String dbpath="";
String found_folder,full_date,path,computername,senderofmail;
 String localhost="";
        String[] localhostsplit;
String [] myalphabet={"B","C","D","E","F","G","H","I","J","K","L","M","N","O","Q","R","S","T","U","V","W","X","Y","Z"};
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
            }
            
            
            
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
            
              dbConnect conn = new dbConnect();
            session = request.getSession(true);
            
//              filename=request.getParameter("filename");
//            System.out.println("file name is"+filename);
            dbname="programprogresstemp";
dbuser="root";
dbpassword="";

found_folder="";
             // Check that we have a file upload request
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (!isMultipart) {
                return;
            }

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Sets the size threshold beyond which files are written directly to
            // disk.
            factory.setSizeThreshold(MAX_MEMORY_SIZE);

            // Sets the directory used to temporarily store files that are larger
            // than the configured size threshold. We use temporary directory for
            // java
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            // constructs the folder where uploaded file will be stored
            String uploadFolder = getServletContext().getRealPath("")+ File.separator + DATA_DIRECTORY;

            new File(uploadFolder).mkdirs();
            
            
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(MAX_REQUEST_SIZE);

            
                // Parse the request
                List /* FileItem */ items = upload.parseRequest(request);  
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (!item.isFormField()) {
                  
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadFolder + File.separator + fileName;
                        File uploadedFile = new File(filePath);
                        System.out.println(filePath);
                        // saves the file to upload directory
                        item.write(uploadedFile);
                        
                   
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
               if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit=localhost.split(":");

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
              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.7\\bin");
             
System.out.println(localhostsplit[0]);
System.out.println(localhostsplit[1]);
        
  
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             
              
if (f.exists() && f.isDirectory()){

executeCmd1 = new String[]{current_drive+":\\wamp\\mysql\\bin\\mysql","--host=localhost", "--port="+localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+filePath};  


System.out.println(executeCmd);
found_folder="it is old wamp";
}
if (f1.exists() && f1.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysql","--host="+localhostsplit[0], "--port="+localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+filePath};  


System.out.println(executeCmd);

found_folder="it is new wamp";
}
 if (f2.exists() && f2.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql","--host="+localhostsplit[0], "--port="+localhostsplit[1],"--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+filePath};  


found_folder="it is workbench";
System.out.println(executeCmd1[5]);
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
                    }}
        } catch (Exception ex) {
            Logger.getLogger(importSQLData1.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
