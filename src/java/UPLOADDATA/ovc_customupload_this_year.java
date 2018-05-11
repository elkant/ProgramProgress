/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UPLOADDATA;

import PP.Admin.dbConnect;
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
public class ovc_customupload_this_year extends HttpServlet {

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
    String county = "", district = "", quarter = "";
// FOR NON HIV MODULE BOOK4 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConnect conn = new dbConnect();
        ArrayList cells = new ArrayList();
        int id1 = 0, id2 = 0, id3 = 0, id4 = 0, id5 = 0, id6 = 0, id7 = 0,id8=0,id9=0,id10=9,id11=0, year = 0;

       

        
        //String id1="",id2="",id3="",id4="",id5="",id6="",id7="",year=;
        ArrayList allcells = new ArrayList();

        String itemName = "";
        erroroccured = 0;
        unuploadedrows = "";
        replyback = "";
        itemName = request.getParameter("fname");
        //____________________GET COMPUTER NAME____________________________________
        String computername = InetAddress.getLocalHost().getHostName();
//System.out.println("Computer name "+computername);



        String splits[] = computername.split("-");
        String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);


//        String pth = mydrive + ":/PPT_UPLOADS/" + itemName;
String pth = "C:/PPT_UPLOADS/ovc_customppmt_this_year.xlsx";

      

        //  new File(dbconnpath).mkdir();

        //System.out.println("path____________________"+allpath);


        FileInputStream inputFile = new FileInputStream(pth);
        //FileInputStream inputFile = new FileInputStream("//Users//suk//Documents/tes//testexcel.xlsx");

        //now initializing the Workbook with this inputFie


        // Create workbook using WorkbookFactory util method

        Workbook wb = WorkbookFactory.create(inputFile);

        // creating helper for writing cells

        CreationHelper createHelper = wb.getCreationHelper();

        // setting the workbook to handle null

        wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);


      // Ge t the first worksheet
        Sheet sheet = wb.getSheetAt(0);
        //NOTE: the first row contains indicator ids in the format id eg 61 or id_sex eg 62_M, 62_F
        //The second row contains full indicator names  in a normal english words
        //The first four columns should contain Year, quarter, County , Subcounty then indicators follow
        
        //In all the instances where data is diasggregated by gender, the pair should always be adjacent to each other
        //eg 62_M should be followed by 62_F and not 62_M, 63,62_F
        //Data for male should come first in the pair 
        

        ArrayList indicatorids= new ArrayList();
        ArrayList indicatornames= new ArrayList();
        ArrayList rowvalues= new ArrayList();
        int rowNo=0;

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row rowi = rowIterator.next();
//            For each row, iterate through all the columns
            Iterator<Cell> cellIterator = rowi.cellIterator();
           
            System.out.println("Row no ______"+rowNo); 
           
           
//                 String value="";

            int lastCellNo = rowi.getLastCellNum();//get the last cell
            int firstCellNo = rowi.getFirstCellNum(); //get the first cell

          rowNo  = rowi.getRowNum();
//            System.out.println(" row number = "+rowNo);
//            System.out.println(" last cell no = "+lastCellNo);
                


            for (int i = 0; i < lastCellNo; i++) {
               
                //inside the rows
      //first row is for indicator ids
                
 if (rowi.getRowNum() == 0) {
 //add the indicator ids
  //ALL the indicator ids should be of text type
Cell columnx = rowi.getCell((short)i);
if(columnx.getCellType()==1){
 String valuex = columnx.getStringCellValue(); 
indicatorids.add(valuex);  
    System.out.println(" Indicator id=> "+valuex);
//this is a string
}
else if (columnx.getCellType()==0){
//integer
    int valuex =(int)columnx.getNumericCellValue(); 
    indicatorids.add(valuex); 
    System.out.println(" Indicator id=> "+valuex);
}
else {

 String valuex = columnx.getStringCellValue(); 
indicatorids.add(valuex);  
System.out.println(" Indicator id=> "+valuex);
}

            }
 else if (rowi.getRowNum() == 1) {
   //save the indicator names
     //add the indicator ids
  //ALL the indicator ids should be of text type
Cell columnx = rowi.getCell((short)i);
String valuex = columnx.getStringCellValue(); 
indicatornames.add(valuex);           
   }
 else {
 //other rows
     
  //the colmns should be of this order |YEAR|QUARTER|COUNTY|SUBCOUNTY|indic1|indic2|indic3...
   if(i<4){
   //fetch value as strings
Cell columnx = rowi.getCell((short)i);
if(columnx.getCellType()==1){
 String valuex = columnx.getStringCellValue(); 
rowvalues.add(valuex);    
 System.out.println("row integer values_"+valuex);   //this is a string
}
else if (columnx.getCellType()==0){
//integer
    int valuex =(int)columnx.getNumericCellValue(); 
    rowvalues.add(valuex); 
     System.out.println("row integer values_"+valuex);   
} 


         
          } 
     else {
   //add values that are integers 
Cell columnx = rowi.getCell((short)i);
int valuex = (int) columnx.getNumericCellValue(); 
rowvalues.add(valuex);   
   System.out.println("row number values_"+valuex);   
        }//end of columns with data
 
 }
 
//end of allcolumns
 
   
 
 
 
 
            }
if (rowi.getRowNum() >1) {
System.out.println("___________________________INSERT DATA______________________________");  
            //this are static indicator ids of different columns as per the order of the excel file. This assumes that the excel order will 
//-------------------------------------------------------end              
//insertion to the db should take place  from here downwards
 //this is still inside the for loop
            System.out.println("ARRAYLIST SIZE"+rowvalues.size());
 //when sending the data, skip sending data for values that have skip as indicator id
String mwaka=rowvalues.get(0).toString();
String qtr=rowvalues.get(1).toString();
String cnty=rowvalues.get(2).toString().trim();
String dist=rowvalues.get(3).toString().trim();

int columnnumber=0;
//row starts from position four
for(int a=4;a<rowvalues.size();a++){
    System.out.println("Indicator Id is :::::: "+indicatorids.get(a).toString());
    if(indicatorids.get(a).toString().equalsIgnoreCase("skip")){
        System.out.println("column "+a+1+" Skipped");
    }
    else if(indicatorids.get(a).toString().contains("_M")){
        System.out.println(" Data By Gender ");
    //this means data for the next two columns is per gender and for one indicator
        String indicator=indicatorids.get(a).toString().replace("_M","");
        int malevalue=Integer.parseInt(rowvalues.get(a).toString());
        int femalevalue=Integer.parseInt(rowvalues.get(a+1).toString());
        //insertintoachieved( dbConnect conn, String year,String quarter, String county, String district, String indicator, int malevalue,int femalevalue, int rownum)
        insertintoachieved(conn, mwaka, qtr, cnty, dist, indicator, malevalue, femalevalue, rowNo);
        //increment a by one to skip female column since te indicator has already been captured
        a++;
    }
    else if (indicatorids.get(a).toString().contains("_")){
    //insert combined indicators data
        
        String indicator=indicatorids.get(a).toString().replace("_","");
        int value=Integer.parseInt(rowvalues.get(a).toString());       
        //insertintoachieved( dbConnect conn, String year,String quarter, String county, String district, String indicator, int malevalue,int femalevalue, int rownum)
        insertintoachievedcombined(conn, mwaka, qtr, cnty, dist, indicator, value, rowNo);
       
    
    }
    else {
    
        System.out.println("#CAUTION__COLUMN "+a+" has been skipped and has data");
  
    }
    
    
  System.out.println("_____________________________________________________________________");    


}
}
 if (rowvalues.size() >= 0 && rowvalues != null) {
                rowvalues.clear();
            }


            
            
            
            
            
            
            

            




        }//end of while iterator

        if (erroroccured == 1) {
            replyback = "<font color=\"red\">Importing completed with an error.<br> Row " + unuploadedrows + " of the excel file contains errors.Check if the ANC numbers are already added.<br/>.</font>";


        } else {

            replyback += "succesfully!!</b></font>";
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
            try {
                processRequest(request, response);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(UploadDataTrial.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadDataTrial.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                processRequest(request, response);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(UploadDataTrial.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadDataTrial.class.getName()).log(Level.SEVERE, null, ex);
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


public void insertintoachievedcombined(  dbConnect conn, String year,String quarter, String county, String district, String indicator, int value, int rownum) throws SQLException{

    System.out.println("ACHIEVEDCOMBINED");
    
            if (value >-1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined_ovc_pepfar where  district='" + district + "' and financialYear='" + year + "' and titleID='"+indicator+"' and reportingPeriod='" + quarter + "'";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined_ovc_pepfar set totalAchieved='" + value + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined_ovc_pepfar (resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + value + "','" + quarter + "','" + year + "','"+indicator+"')";
//                              conn.state.executeUpdate(query);
                }//end of else
                System.out.println(query);
                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rownum + " , ";

                }


            }//end of if
            
        

}


public void insertintoachieved( dbConnect conn, String year,String quarter, String county, String district, String indicator, int malevalue,int femalevalue, int rownum) throws SQLException{

    System.out.println("ACHIEVED BY GENDER");
            
             if (malevalue>-1) {
              
//check whether there is an existing record then do an update                              
                String query = "";
                String checkexistingval = "Select * from indicatorachieved_ovc_pepfar where  district='" + district + "' and financialYear='" + year + "' and titleID='"+indicator+"' and reportingPeriod='" + quarter + "'";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachieved_ovc_pepfar set menAchieved='" + malevalue + "',womenAchieved='" + femalevalue + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachieved_ovc_pepfar (resultID,county,district,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + malevalue + "','" + femalevalue + "','" + quarter + "','" + year + "','"+indicator+"')";
                }
                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rownum+ " , ";

                }

                System.out.println(query);
            }
            
    
    

}

}








