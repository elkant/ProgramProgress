/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import DBCREDENTIALSFILE.EmgBackup;
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
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Maureen
 */
public class UploadImmune extends HttpServlet {

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
        int id1 = 0, id2 = 0, id3 = 0, id4 = 0, id5 = 0, id6 = 0, id7 = 0;
int year=0;
        
//create a buckup first before uploading again
        EmgBackup em =new EmgBackup();
        em.dobackup();
        
        
        
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
 String pth = "C:/PPT_UPLOADS/PPTDATA.xlsx";

        //create a directory if not exists

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


      //  Sheet sheet = wb.getSheetAt(0);
        Sheet sheet = wb.getSheet("Immune");




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
               year = (int) cell8.getNumericCellValue();



            }


            //this are static indicator ids of different columns as per the order of the excel file. This assumes that the excel order will 





            System.out.println("County :" + county + "District :" + district + "quarter :" + quarter + "id1 :" + id1 + "id2: " + id2 + "id3 :" + id3 + "id4 :" + id4 + "id5 :" + id5 + "id6 :" + id6 + "id7 :" + id7 + "Year :" + year);
            if (id1 >-1) {


//check whether there is an existing record then do an update                              
                String query = "";
                String checkexistingval = "Select * from indicatorachieved where district='" + district + "' and financialYear='" + year + "' and titleID='34' and reportingPeriod='" + quarter + "'";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachieved set menAchieved='" + id1 + "',womenAchieved='" + id2 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachieved(resultID,county,district,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id1 + "','" + id2 + "','" + quarter + "','" + year + "','34')";
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
          
         
           
            if (id3 >-1) {


//check whether there is an existing record then do an update                              
                String query = "";
                String checkexistingval = "Select * from indicatorachieved where district='" + district + "' and financialYear='" + year + "' and titleID='33' and reportingPeriod='" + quarter + "'";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {
                    query = "update indicatorachieved set menAchieved='" + id3 + "',womenAchieved='" + id4 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachieved(resultID,county,district,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id3 + "','" + id4 + "','" + quarter + "','" + year + "','33')";
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
          
         
           
            
             if (id5>-1) {
                String query = "";
                String checkexistingval = "Select * from indicatorachievedcombined where  district='" + district + "' and financialYear='" + year + "' and titleID='35'  and reportingPeriod='" + quarter + "'";
                conn.rs3 = conn.state3.executeQuery(checkexistingval);

                if (conn.rs3.next()) {

                    query = "update indicatorachievedcombined set totalAchieved='" + id5 + "' where resultID='" + conn.rs3.getString(1) + "'";

                } else {
                    query = "insert into indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                            + "VALUES('"+uniqueid().trim()+"','" + county + "','" + district + "','" + id5 + "','" + quarter + "','" + year + "','35')";
                }

//                              conn.state.executeUpdate(query);
                try {
                    conn.state.executeUpdate(query);
                    replyback = "<font color=\"orange\"><b>Importing completed ";
                } catch (SQLException se) {
                    erroroccured = 1;
                    unuploadedrows += rowi.getRowNum() + " , ";

                }
                System.out.println(query);
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
}
