/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Maureen
 */
public class UploadSQLData extends HttpServlet {

 private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "PPTDATA";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    
    String filename="";
 String replyback = "";
    int erroroccured = 0;
    String unuploadedrows = "";
      String id="",activity="";
              int indicatorid=0; 
               dbConnect conn = new dbConnect();
        ArrayList cells = new ArrayList();
           HttpSession session;
 int id1=0,id2=0,id3=0,id4=0,id5=0,id6=0,id7=0,id8=0,id9=0,year=0;
  
 //String id1="",id2="",id3="",id4="",id5="",id6="",id7="",year=;
        ArrayList allcells = new ArrayList();

        String itemName = "";
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       erroroccured = 0;
        unuploadedrows = "";
          response.sendRedirect("UploadActivity.jsp");
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
            
         
            session = request.getSession(true);
                PrintWriter out = response.getWriter();
              filename=request.getParameter("filename");
            System.out.println("file name is"+filename);
            
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
                        System.out.println("filePath   "+filePath);
                        // saves the file to upload directory
                        item.write(uploadedFile);
                        
                      


        FileInputStream inputFile = new FileInputStream(filePath);
        //FileInputStream inputFile = new FileInputStream("//Users//suk//Documents/tes//testexcel.xlsx");

        //now initializing the Workbook with this inputFie


        // Create workbook using WorkbookFactory util method

        Workbook wb = WorkbookFactory.create(inputFile);

        // creating helper for writing cells

        CreationHelper createHelper = wb.getCreationHelper();

        // setting the workbook to handle null

        wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);


        Sheet sheet = wb.getSheetAt(0);




        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row rowi = rowIterator.next();
//            For each row, iterate through all the columns
                Iterator<Cell> cellIterator = rowi.cellIterator();
            if (rowi.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            if (cells.size() >= 0 && cells != null) {
                cells.clear();
            }
            if (allcells.size() >= 0 && allcells != null) {
                allcells.clear();
            }
//                 String value="";

            int lastCellNo = rowi.getLastCellNum();
            int firstCellNo = rowi.getFirstCellNum();

            int rowNo = rowi.getRowNum();
//            System.out.println(" row number = "+rowNo);
//            System.out.println(" last cell no = "+lastCellNo);


            for (int i = 0; i < lastCellNo; i++) {
                // System.out.println("************");

                              Cell cell = rowi.getCell(i);
                                           
                               //columns
                        Cell cell0 = rowi.getCell((short) 0);//1r1c
			activity =cell0.getStringCellValue();
                                
                        Cell cell1 = rowi.getCell((short) 1);
			indicatorid = (int) cell1.getNumericCellValue();//2
                        
                        
                        
                   System.out.println(activity) ; 
                   System.out.println(indicatorid) ; 
                       
                      
             
            }
            
            
            
            
            
           if(!activity.equals("") ){
String query="";                            
String checkexistingval="Select * from indicatoractivity where Activity='"+activity.trim().replace("'", "")+"' and IndicatorID='"+indicatorid+"' ";                              


  

conn.rs3=conn.state3.executeQuery(checkexistingval);

if(conn.rs3.next()){
  query = "update indicatoractivity set Activity=?, IndicatorID=? where ActivityID='"+conn.rs3.getString(1)+"'";
    
        System.out.println(query);
            conn.ps = conn.connect.prepareStatement(query);
            conn.ps.setString(1,activity.trim().replace("'", ""));
            conn.ps.setInt(2,indicatorid);
//            conn.ps.executeUpdate();
}      
else{
    
    query = "insert into indicatoractivity(ActivityID,Activity,IndicatorID)"
                                   + "VALUES(?,?,?)"; 

  
                              System.out.println(query);
                               conn.ps = conn.connect.prepareStatement(query);
               conn.ps.setString(1,uniqueid().trim());
               conn.ps.setString(2,activity.trim().replace("'", ""));
               conn.ps.setInt(3,indicatorid);

}

                            try {
              
               
               conn.ps.executeUpdate();
               
                  session.setAttribute("replyback", "<font color=\"orange\"><b>Importing completed ");
//                replyback = "<font color=\"orange\"><b>Importing completed ";
            } catch (SQLException se) {
                erroroccured = 1;
                unuploadedrows += rowi.getRowNum() + " , ";
                System.out.println(se.toString());

            }

                           
                           
                          
//                              System.out.println(query);
                       }   
                          
                          
                          
      


        }//end of while iterator
        
        
          if (erroroccured == 1) {
            session.setAttribute("replyback","<font color=\"red\">Importing completed with an error.<br> Row " + unuploadedrows + " of the excel file contains errors.<br/>.</font>");
           // replyback = "<font color=\"red\">Importing completed with an error.<br> Row " + unuploadedrows + " of the excel file contains errors.<br/>.</font>";


        } else {
session.setAttribute("replyback", "<b><font color=\"red\">Done succesfully!!</b></font>");
         //   replyback += "succesfully!!</b></font>";
        }
        try {
            session.setAttribute("replyback", "<b><font color=\"red\">Activities Imported succesfully!!</b></font>");
//            out.println(replyback);
        } finally {
            out.close();
        }
        
                    }}
        } catch (Exception ex) {
            Logger.getLogger(UploadSQLData.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    
    
    
    
    //====================random id functions================================
public String uniqueid() {

Calendar cal1 = Calendar.getInstance();

int year1 = cal1.get(Calendar.YEAR);
int month1 = cal1.get(Calendar.MONTH) + 1;
int date1 = cal1.get(Calendar.DAY_OF_MONTH);
int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
int min1 = cal1.get(Calendar.MINUTE);
int sec1 = cal1.get(Calendar.SECOND);
int milsec = cal1.get(Calendar.MILLISECOND);


return generateRandomNumber(800, 8000) + year1 + "" + month1 + "" + date1 + hour1 + min1 + sec1 + milsec;
}

public int generateRandomNumber(int start, int end) {
Random random = new Random();
long fraction = (long) ((end - start + 1) * random.nextDouble());
return ((int) (fraction + start));
}
//==========================================================================
}
