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
public class UploadOVCData extends HttpServlet {

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
        int id1 = 0, id2 = 0, id3 = 0, id4 = 0, id5 = 0, id6 = 0, id7 = 0, id8 = 0, id9 = 0,id10=0,id11=0, year = 0;

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


        String pth = "C:/PPT_UPLOADS/OVC_2016Q4.xls";
       // String pth = mydrive + ":/PPT_UPLOADS/" + itemName;


        //create a directory if not exists

        //  new File(dbconnpath).mkdir();

        System.out.println("path____________________"+allpath);


        FileInputStream inputFile = new FileInputStream(pth);
        //FileInputStream inputFile = new FileInputStream("//Users//suk//Documents/tes//testexcel.xlsx");

        //now initializing the Workbook with this inputFie


        // Create workbook using WorkbookFactory util method

        Workbook wb = WorkbookFactory.create(inputFile);

        // creating helper for writing cells

        CreationHelper createHelper = wb.getCreationHelper();

        // setting the workbook to handle null

        wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);


       // Sheet sheet = wb.getSheetAt(0);
        Sheet sheet = wb.getSheet("OVC");




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
                county = cell0.getStringCellValue();

                Cell cell1 = rowi.getCell((short) 1);
                district = cell1.getStringCellValue();//2

                Cell cell2 = rowi.getCell((short) 2);
                quarter = cell2.getStringCellValue();//3

                Cell cell3 = rowi.getCell((short) 3);
                id1 = (int) cell3.getNumericCellValue();
                
                Cell cell4 = rowi.getCell((short) 4);
                id2 = (int) cell4.getNumericCellValue();
                
                Cell cell5 = rowi.getCell((short) 5);
                id3 = (int) cell5.getNumericCellValue();
                
                Cell cell6 = rowi.getCell((short) 6);
                id4 = (int) cell6.getNumericCellValue();
                
                Cell cell7 = rowi.getCell((short) 7);
                id5 = (int) cell7.getNumericCellValue();
                
                Cell cell8 = rowi.getCell((short) 8);
                id6 = (int) cell8.getNumericCellValue();
                
                Cell cell9 = rowi.getCell((short) 9);
                id7 = (int) cell9.getNumericCellValue();
                
                Cell cell10 = rowi.getCell((short) 10);
                id8 = (int) cell10.getNumericCellValue();
                
              
                
                Cell cell14 = rowi.getCell((short) 11);
                year = (int) cell14.getNumericCellValue();



            }





            System.out.println("County :" + county + "District :" + district + "quarter :" + quarter + "id1 :" + id1 + "id2: " + id2 + "id3 :" + id3 + "id4 :" + id4 + "id5 :" + id5 + "id6 :" + id6 + "id7 :" + id7 + "Year :" + year);
            if (id1 > -1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='47' and reportingPeriod='" + quarter + "' ";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined set totalAchieved='" + id1 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id1 + "','" + quarter + "','" + year + "','47')";
                }

                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                    System.out.println("successfully done"+replyback);
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }




                System.out.println(query);
            }
           
            if (id2 > -1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='49' and reportingPeriod='" + quarter + "' ";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined set totalAchieved='" + id2 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id2 + "','" + quarter + "','" + year + "','49')";
                }

                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                     System.out.println("successfully done"+replyback);
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }




                System.out.println(query);
            }
           
        
            if (id3 > -1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='46' and reportingPeriod='" + quarter + "' ";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined set totalAchieved='" + id3 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id3 + "','" + quarter + "','" + year + "','46')";
                }

                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }




                System.out.println(query);
            }
            
            
             if (id4 > -1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='55' and reportingPeriod='" + quarter + "' ";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined set totalAchieved='" + id4 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id4 + "','" + quarter + "','" + year + "','55')";
                }

                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }




                System.out.println(query);
            }
             
             
             
              if (id5 > -1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='153' and reportingPeriod='" + quarter + "' ";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined set totalAchieved='" + id5 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id5 + "','" + quarter + "','" + year + "','153')";
                }

                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }




                System.out.println(query);
            }
              
              
              if (id6 > -1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='53' and reportingPeriod='" + quarter + "' ";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachievedcombined set totalAchieved='" + id6 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id6 + "','" + quarter + "','" + year + "','53')";
                }

                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }




                System.out.println(query);
            }
              
              
              if (id7 > -1) {
//                String query = "";
//                String checkexistingval = "Select * from indicatorachievedcombined where district='" + district + "' and financialYear='" + year + "' and titleID='54' and reportingPeriod='" + quarter + "' ";
//                conn.rs3 = conn.state3.executeQuery(checkexistingval);
//
//                if (conn.rs3.next()) {
//                    query = "update indicatorachievedcombined set totalAchieved='" + id7 + "' where resultID='" + conn.rs3.getString(1) + "'";
//
//                } else {
//                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
//                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id7 + "','" + quarter + "','" + year + "','54')";
//                }
//
//                try {
//                    conn.state.executeUpdate(query);
//                    replyback = "<font color=\"orange\"><b>Importing completed ";
//                } catch (SQLException se) {
//                    erroroccured = 1;
//                    unuploadedrows += rowi.getRowNum() + " , ";
//
//                }
   String query = "";
                String checkexistingval = "Select * from indicatorachieved where district='" + district + "' and financialYear='" + year + "' and titleID='54' and reportingPeriod='" + quarter + "'";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachieved set menAchieved='" + id7 + "',womenAchieved='" + id8 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachieved(resultID,county,district,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id7 + "','" + id8 + "','" + quarter + "','" + year + "','54')";
                }
                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }



                System.out.println(query);
            }
              
              
           
               
               
           
//=================================================================Two new rows====
            
            
            

        }//end of while iterator



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
            Logger.getLogger(UploadOVCData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(UploadOVCData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UploadOVCData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(UploadOVCData.class.getName()).log(Level.SEVERE, null, ex);
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
