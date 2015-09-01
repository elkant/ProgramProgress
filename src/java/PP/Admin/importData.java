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
import java.util.Iterator;
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
public class importData extends HttpServlet {

    String replyback = "";
    int erroroccured = 0;
    String unuploadedrows = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConnect conn = new dbConnect();
        ArrayList cells = new ArrayList();

        ArrayList allcells = new ArrayList();
String type="";
if(!request.getParameter("type").equals("") && request.getParameter("type")!=null){
type=request.getParameter("type");
}
System.out.println("type"+type);
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

         
        String pth = mydrive + ":/PPT_UPLOADS/" + itemName;

        
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


        Sheet sheet = wb.getSheetAt(0);




        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
//            For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
//            if (row.getRowNum() == 0) {
//                continue; //just skip the rows if row number is 0
//            }
            if (cells.size() >= 0 && cells != null) {
                cells.clear();
            }
            if (allcells.size() >= 0 && allcells != null) {
                allcells.clear();
            }
//                 String value="";

            int lastCellNo = row.getLastCellNum();
            int firstCellNo = row.getFirstCellNum();

            int rowNo = row.getRowNum();
//            System.out.println(" row number = "+rowNo);
//            System.out.println(" last cell no = "+lastCellNo);


            for (int i = 0; i < lastCellNo; i++) {
                // System.out.println("************");

                Cell cell = row.getCell(i);
                int colIndex = cell.getColumnIndex();
                if (cell == null || getCellValue(row.getCell(i)).trim().isEmpty()) {
                    cell.setCellValue("NO VALUE");
//   System.out.println(" The Cell:"+colIndex+" for row "+row.getRowNum()+" is NULL");
                    //cells.add(cell.getStringCellValue());

                    //System.out.println("NULL CELLS    "+cell.getRichStringCellValue());
                }

                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    String stringvalues = cell.getStringCellValue();
                    cells.add(stringvalues);
//         System.out.println("STRING CELLS  "+stringvalues);

                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

                    if (DateUtil.isCellDateFormatted(cell)) {
                        HSSFDataFormatter formatter;
                        formatter = new HSSFDataFormatter();
                        String temp = formatter.formatCellValue(cell);
//    System.out.println("DATE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+temp);
                        cells.add(temp);
                    } else {
                        HSSFDataFormatter formatter;
                        formatter = new HSSFDataFormatter();
                        String value = formatter.formatCellValue(cell);
// int value=(int)cell.getNumericCellValue();
                        cells.add(value);
// System.out.println("NUMERIC CELLS "+value);
                    }





                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    cells.add(cell.getBooleanCellValue());
                    //System.out.println("BOOLEAN CELLS"+cell.getBooleanCellValue());
                }


                //   System.out.println(" column  index  = "+colIndex);


                int cellType = cell.getCellType();

                // System.out.println(" cell type ="+cellType);
                allcells.add(cells);
                //System.out.println("cells___________"+cells.get(i).toString());





            }
            allcells.add(cells);

//                      allcells2.add(allcells);
            System.out.println("cells___________" + allcells.get(0).toString());
            //System.out.println("cells___________"+allcells.get(1).toString());

String query="";

if(type.equals("separate")){
             query = "insert into indicatorachieved(county,district,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID) values"
                    + "('" + cells.get(0) + "',"
                    + "'" + cells.get(1) + "',"
                    + "'" + cells.get(2) + "',"
                    + "'" + cells.get(3) + "',"
                    + "'" + cells.get(4) + "',"
                    + "'" + cells.get(5) + "',"
                    + "'" + cells.get(6) + "')";
}
else{
       query = "insert into indicatorachievedcombined(county,district,totalAchieved,reportingPeriod,financialYear,titleID) values"
                    + "('" + cells.get(0) + "',"
                    + "'" + cells.get(1) + "',"
                    + "'" + cells.get(2) + "',"
                    + "'" + cells.get(3) + "',"
                    + "'" + cells.get(4) + "',"
                    + "'" + cells.get(5) + "')";
}
//                   
            // System.out.println("query +++++++++++++++"+query);

            try {
                conn.state.executeUpdate(query);
                replyback = "<font color=\"orange\"><b>Importing completed ";
            } catch (SQLException se) {
                erroroccured = 1;
                unuploadedrows += row.getRowNum() + " , ";

            }






            System.out.print("REPLY BACK  ====" + replyback);

        }// row and cell iterator

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

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return cell.getErrorCellValue() + "";
        } else {
            return null;
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
            Logger.getLogger(importData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(importData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(importData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(importData.class.getName()).log(Level.SEVERE, null, ex);
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
