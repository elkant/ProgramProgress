/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
public class UploadActivities extends HttpServlet {

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
    String replyback = "";
    int erroroccured = 0;
    String unuploadedrows = "";
      String id="",activity="";
              int indicatorid=0; 
// FOR NON HIV MODULE BOOK4 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConnect conn = new dbConnect();
        ArrayList cells = new ArrayList();
 int id1=0,id2=0,id3=0,id4=0,id5=0,id6=0,id7=0,id8=0,id9=0,year=0;
  
 //String id1="",id2="",id3="",id4="",id5="",id6="",id7="",year=;
        ArrayList allcells = new ArrayList();

        String itemName = "";
        erroroccured = 0;
        unuploadedrows = "";

        itemName = request.getParameter("fname");
        //____________________GET COMPUTER NAME____________________________________
        String computername = InetAddress.getLocalHost().getHostName();
//System.out.println("Computer name "+computername);



        String splits[] = computername.split("-");
        String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
String machineuname =System.getProperty("user.name");


System.out.println("Machine name is::"+machineuname);


   // dbpath=mydriv+":\\Users\\"+machineuname+"\\Downloads\\"+filename;
  String pth=mydrive+":\\PPT_UPLOADS\\IMPORTS\\"+itemName;
         
        //String pth = "C:/Users/"+machineuname+"/Downloads/" + itemName;

        
        //create a directory if not exists
        
       //  new File(dbconnpath).mkdir();
        
        System.out.println("path____________________"+pth);


        FileInputStream inputFile = new FileInputStream(pth);
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
                replyback = "<font color=\"orange\"><b>Importing completed ";
            } catch (SQLException se) {
                erroroccured = 1;
                unuploadedrows += rowi.getRowNum() + " , ";
                System.out.println(se.toString());

            }

                           
                           
                          
//                              System.out.println(query);
                       }   
                          
                          
                          
      


        }//end of while iterator
        
        
          if (erroroccured == 1) {
            replyback = "<font color=\"red\">Importing completed with an error.<br> Row " + unuploadedrows + " of the excel file contains errors.<br/>.</font>";


        } else {

            replyback += "Import completed succesfully!!</b></font>";
        }
        try {
            out.println(replyback);
        } finally {
            out.close();
        }
        
        
    
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
            Logger.getLogger(UploadActivities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(UploadActivities.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UploadActivities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(UploadActivities.class.getName()).log(Level.SEVERE, null, ex);
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
