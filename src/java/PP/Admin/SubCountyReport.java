/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class SubCountyReport extends HttpServlet {

    String county_id = "", indicator_id = "", year = "", quarter = "", titleID = "";
    String indicator = "", indicator_number = "", district_id = "";
    String activity_title = "", start_date = "", end_date = "";
    int m_achieved = 0, w_achieved = 0, sub_total = 0;
    int m_total = 0, w_total = 0, total = 0, j = 0, i = 5;
   
    HttpSession session;
    String path = "", path2 = "";
    String baselineid = "";
    int totalRowCount = 1;
//    int baseline_women = 0, baseline_men = 0;
    String district = "";
    int yr = 0;
    //w_target = 0, m_target = 0;
    String p_quarter = "";
//    int menAchieved = 0;
//    int womenAchieved = 0;
    int p_baseline_women = 0;
    int p_baseline_men = 0;
//    int targeted_women = 0, targeted_men = 0;
    int p_year = 0;
    int p = 0;
     int combinedtotal=0;
    String current_date = "";
    int counter = 1;
    int counts = 0;
  
//    int quartertotal = 0;
//    int quartertotal_1 = 0;
//    int totalquarter = 0;
//    int totalquarter_1 = 0;
String others="";
 String districts = "";
   int countDist=0; 
   int countDist1=0;  
      
             // initialisation for total 
             
             
    int TotalTargetW=0;
    int TotalTargetM=0;
    int TotalTargeted_W=0;
    int TotalTargeted_M=0;
     int Total_baselineM =0;
     int Total_baselineW =0;
     int Total_Wtarget=0;
     int Total_Mtarget=0;
     
      int TotalQuarterW=0;
      int TotalQuarterM=0;
     
     int PriorTargetW=0;
     int PriorTargetM=0;
     int TotalPriorTargetW=0;
     int TotalPriorTargetM=0;
     
     int target_women=0;
     int target_men=0;
       int s=0;
     int totalWomen=0;
     int totalMen=0;
     
     int priorQuarterW=0;
   int priorQuarterM=0;
   int TotalpriorQuarterW=0;
   int TotalpriorQuarterM=0;
   
   int targeted_women = 0;
    int targeted_men = 0;
     
     int w_target=0;
   int  m_target=0;
     
    int baseline_men=0;
     int baseline_women=0;
     
    int totalquarter=0;
    int totalquarter_1=0;
             
   int menAchieved=0;                   
   int  womenAchieved=0;   
   int quartertotal = 0;
   int quartertotal_1 = 0;
   int quarter1 = 0, quarter2 = 0, quarter3 = 0, quarter4 = 0;
   int quarter_1 = 0, quarter_2 = 0, quarter_3 = 0, quarter_4 = 0; 
   int baseline_combined=0,target_combined=0,priorAchieved=0,priorTarget=0,priorsTarget=0,Qtrbaseline_combined=0,Qtrtarget_combined=0,Yrtarget_combined=0;
    
                        
                   ArrayList countyname=new ArrayList();
                   ArrayList countyid=new ArrayList();  
                   
                   
                    
                   String[] CountyID;
                     int TotalpriorTarget=0; 
                     
                        
                    int Total_baseline=0;
                    int Totalprior_Achieved=0;
                    
                  
                  int Total_Quarter=0;
                    int TotalYearTarget=0;
                 
                  int TotalQtrTarget=0;
                  int TotalCombinedTarget=0,totalAchievedments=0; int totl=0; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
      //  PrintWriter out = response.getWriter();
        try {
            
             int countersfor=0;
            Calendar cal = Calendar.getInstance();
            int year1 = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DATE);
            int hr = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            current_date = year1 + "_" + month + "_" + day ;
            //System.out.println("current day is: "+current_date);
            
            
             int FINALTOTALQUARTER=0;
             int FINALTOTALQUARTER1=0;
              if(countyid.size()!=0 && countyid!=null){
                countyid.clear();
            }
                      
                      
                      if(countyname!=null && countyname.size()!=0){
                countyname.clear();
            }
            dbConnect conn = new dbConnect();
            HttpSession session;
            session = request.getSession();
            String county = "";
            String indicator = "";
            String[] district_ids;
             String district_name = "";
            
          totalquarter = quarter1= quarter2= quarter3 = quarter4=0;
priorTarget=0;
//            *******************RECEIVE PARAMETERS*******************************
            if (request.getParameter("county") != null && !request.getParameter("county").equals("")) {
                county = request.getParameter("county");
            }
            if (request.getParameter("indicator") != null && !request.getParameter("indicator").equals("")) {
                indicator = request.getParameter("indicator");
            }
            if (request.getParameter("FinancialYear") != null && !request.getParameter("FinancialYear").equals("")) {
                year = request.getParameter("FinancialYear");
            }
            if (request.getParameter("Quarter") != null && !request.getParameter("Quarter").equals("")) {
                quarter = request.getParameter("Quarter");
            }
            String[] idcounty;
            
         
            if (request.getParameterValues("countyid") != null && !request.getParameterValues("countyid").equals("")) {
                CountyID=request.getParameterValues("countyid");
               
            }

            String period="";
            String previous_period ="";
            int Display_year=0;
            int Display_year_previous=0;
            
              if(quarter.equals("Q1")){
      period="Oct-Dec"; 
      Display_year= Integer.parseInt(year)-1;
      previous_period="July-Sep";
      Display_year_previous=Integer.parseInt(year)-1;
    }
    if(quarter.equals("Q2")){
      period="Jan-March"; 
      Display_year=Integer.parseInt(year);
      previous_period="Oct-Dec";
     Display_year_previous=Integer.parseInt(year)-1; 
    }
    if(quarter.equals("Q3")){
      period="April-June";
      Display_year=Integer.parseInt(year);
      previous_period="Jan-March";
      Display_year_previous=Integer.parseInt(year);
    }
    if(quarter.equals("Q4")){
      period="July-Sep";
      Display_year=Integer.parseInt(year);
      previous_period="April-June";
      Display_year_previous=Integer.parseInt(year);
    }
 
            String dist = "";

            if (request.getParameterValues("district_ids") != null && !request.getParameterValues("district_ids").equals("")) {
                district_ids = request.getParameterValues("district_ids");
                int size_a = district_ids.length;
              
            }
            if (request.getParameterValues("district_ids" + i) != null && !(request.getParameterValues("district_ids" + i).equals(""))) {
                district_ids = request.getParameterValues("district_ids" + i);
                for (int y = 0; y < district_ids.length; y++) {
                    dist += district_ids[y] + "_";
                
                }
            }

ArrayList ind= new ArrayList();

                    
                     
//            ****************HEADING IN EXCEL**********************************************************
            HSSFWorkbook wb = new HSSFWorkbook();
			 String[] designations = {"3","4","9","8"} ;
    String[] sheets={"M&E","Clinical","SDH","HC"};
        for(int shts=0;shts< designations.length ; shts++){
        //            ****************HEADING IN EXCEL**********************************************************
          
               HSSFSheet shet1=wb.createSheet(sheets[shts]);
            
             shet1.setColumnWidth(0, 20);
    shet1.setColumnWidth(1, 3000); 
    shet1.setColumnWidth(2, 2000);
    shet1.setColumnWidth(3, 2500);
    shet1.setColumnWidth(4, 2500);
    shet1.setColumnWidth(5, 2000);
    shet1.setColumnWidth(6, 2500);
    shet1.setColumnWidth(7, 2000);
    shet1.setColumnWidth(8, 2500); 
    shet1.setColumnWidth(9, 2000);
    shet1.setColumnWidth(10, 2000);
    shet1.setColumnWidth(11, 2000);
    shet1.setColumnWidth(12, 2000);
    shet1.setColumnWidth(13, 2000);
    shet1.setColumnWidth(14, 2000);
    shet1.setColumnWidth(15, 2000);
    shet1.setColumnWidth(16, 2000);
    shet1.setColumnWidth(17, 2000);
    
 
            
            
            int a = 1;
          


            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Cambria");
            font.setItalic(true);
            font.setBoldweight((short) 02);
            font.setColor(HSSFColor.BLACK.index);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setWrapText(true);
            style.setFillForegroundColor(HSSFColor.GOLD.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);


//%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            HSSFFont font_header = wb.createFont();
            font_header.setFontHeightInPoints((short) 10);
            font_header.setFontName("Arial Black");
            font_header.setItalic(true);
            font_header.setBoldweight((short) 05);
            font_header.setColor(HSSFColor.BLACK.index);
            CellStyle style_header = wb.createCellStyle();
            style_header.setFont(font_header);
            style_header.setWrapText(true);
            style_header.setFillForegroundColor(HSSFColor.YELLOW.index);
            style_header.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_header.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style_header.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style_header.setAlignment(CellStyle.ALIGN_CENTER);
            style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


            HSSFCellStyle stylex = wb.createCellStyle();
            stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFFont fontx = wb.createFont();
            fontx.setColor(HSSFColor.DARK_BLUE.index);
            stylex.setFont(fontx);
            stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
             //stylex.setAlignment(CellStyle.ALIGN_CENTER);
            stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);

            HSSFCellStyle styley = wb.createCellStyle();

            HSSFFont fonty = wb.createFont();
            styley.setFont(fonty);

            styley.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            HSSFCellStyle stylez = wb.createCellStyle();
            HSSFFont fontz = wb.createFont();
            stylez.setFont(fontz);
            stylez.setBorderRight(HSSFCellStyle.BORDER_THIN);

            HSSFCellStyle styletop = wb.createCellStyle();
            HSSFFont fontp = wb.createFont();
            styletop.setFont(fontp);
            styletop.setBorderTop(HSSFCellStyle.BORDER_THIN);


            CellStyle borderStyle = wb.createCellStyle();
            borderStyle.setFillForegroundColor(HSSFColor.ROSE.index);
            borderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            borderStyle.setFont(fontx);
            borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
            borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
            borderStyle.setBorderRight(CellStyle.BORDER_THIN);
            borderStyle.setBorderTop(CellStyle.BORDER_THIN);
            borderStyle.setAlignment(CellStyle.ALIGN_CENTER);
            
            
            CellStyle indicator_style = wb.createCellStyle();
            indicator_style.setFillForegroundColor(HSSFColor.PLUM.index);
            indicator_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                HSSFFont fonts = wb.createFont();
                fonts.setColor(HSSFColor.BLACK.index);
                fonts.setFontHeightInPoints((short) 17);
            indicator_style.setFont(fonts);
            indicator_style.setBorderBottom(CellStyle.BORDER_THIN);
            indicator_style.setBorderLeft(CellStyle.BORDER_THIN);
            indicator_style.setBorderRight(CellStyle.BORDER_THIN);
            indicator_style.setBorderTop(CellStyle.BORDER_THIN);
            indicator_style.setAlignment(CellStyle.ALIGN_CENTER);

int percentages=0;
         
   String distCount="";
   int counters=0;
   
     String indQuery = "select * from indicatortitles WHERE (tableIdentifier='1' ||tableIdentifier='2') and designation like '%"+designations[shts]+"%' and  active='yes' ";
            conn.rs5 = conn.state5.executeQuery(indQuery);
            while (conn.rs5.next()) {
countDist=0;
countDist1=0;
                String indicators = conn.rs5.getString("titleID");
                String indicatortitle = conn.rs5.getString(3);
                String tableID = conn.rs5.getString("tableIdentifier");
                percentages=conn.rs5.getInt("percentage");
                
                System.out.println("checkpercent   "+indicatortitle+"_______"+percentages);
for(int w=0;w<CountyID.length;w++){
   System.out.println("_______"+CountyID[w]);
          String selectCounty="select * from county where countyID='"+CountyID[w] +"'";
          System.out.println(selectCounty);
                  conn.rs6 = conn.state6.executeQuery(selectCounty);
                  while(conn.rs6.next()){
                     countyname.add(conn.rs6.getString("countyName"));
                     countyid.add(conn.rs6.getString("countyID"));
                  }
      
          
                
                
                   String districtCount = "select count(resultID) from indicatorachieved where financialYear='" + year + "' AND reportingPeriod='" + quarter + "' and titleID='" + indicators+ "' and (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                    conn.rs6 = conn.state6.executeQuery(districtCount);
                  System.out.println(districtCount);
                    if(conn.rs6.next()==true){
                    
                  countDist=conn.rs6.getInt(1);
                    
                    } 
            
            //  System.out.println("countDist   "+countDist);
                
                
                String counterQuery = "select * from indicatoractivities where titleID='" + indicators + "' AND FinancialYear='" + year + "' AND Quarter='" + quarter + "' and (countyID='"+countyname.get(w)+"' || countyID='"+countyid.get(w)+"')";
                 conn.rs6 = conn.state6.executeQuery(counterQuery);
                 
                 System.out.println(counterQuery);
                 while(conn.rs6.next()){
                 distCount = conn.rs6.getString("DistrictID");
                 counters++;
                 }}
       
//------------------------------- beginining of title with separate totals for men and women _---------------------------------------------------
                if (tableID.equals("1")) {
                  
                    HSSFRow rw1 = shet1.createRow(a + 2);
                 
                    HSSFCell cell_0;


                    for (int y = 2; y <= 18; ++y) {
                         cell_0 = rw1.createCell(y);
                        cell_0.setCellStyle(style_header);
                      
                        if (y == 2) {
                            cell_0.setCellValue("Table   :" + indicators + " " + "ACTIVITIES");
                           
                        }
                    }

                    a += 2;
                 
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2,18));
                           HSSFRow rw2 = shet1.createRow(a + 2);
                                HSSFCell cell2;
                                    rw2.setHeightInPoints(12);
             
                    for (int y = 2; y <= 18; ++y) {
                          cell2 = rw2.createCell(y);
                         cell2.setCellStyle(style_header);
                     if (y == 2) {
                            cell2.setCellValue("Indicator Title: " + indicatortitle);
                         }
                    }

                    a += 2;
              
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));                 
                    HSSFRow rw3 = shet1.createRow(a + 1);                 
                    HSSFCell cell3;
 
                    for (int y = 4; y <= 11; ++y) {
                        cell3 = rw3.createCell(y);
                        cell3.setCellStyle(style_header);
                        if (y == 4) {
                            cell3.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
                            } }
                 //  Merge the cells
                    a += 1;
                   
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 4, 11));
                                  HSSFRow rw4 = shet1.createRow(a + 1);
                   HSSFCell cells1, cells2, cells3, cells4, cells5, cells6, cells7, cells8, cells9, cells10;

                    for (int y = 2; y <= 3; y++) {
                         cells7 = rw4.createCell(y);
                             cells7.setCellStyle(stylex);
                             if (y == 2) {
                               cells7.setCellValue("County");
                            }  }
                for (int y = 4; y <= 6; y++) {
                     cells6 = rw4.createCell(y);
                        cells6.setCellStyle(stylex);
                       if (y ==4) {
                        cells6.setCellValue("Geographical Location");
                     
                        } }
                 shet1.addMergedRegion(new CellRangeAddress(a, a, 4, 6));
                                  for (int y = 7; y <= 12; y++) {
                        cells3 = rw4.createCell(y);
                        cells3.setCellStyle(stylex);
                      if (y == 7) {
                              cells3.setCellValue("Activity title");
                        }
                    }
  
                   for (int y = 13; y <= 14; ++y) {
                     cells4 = rw4.createCell(y);
                 cells4.setCellStyle(stylex);
                        if (y == 13) {
                             cells4.setCellValue("start date");
                                                  }
                    }
      
                                for (int y = 15; y <= 16; ++y) {

                      cells5 = rw4.createCell(y);
                      cells5.setCellStyle(stylex);
                       if (y == 15) {
                               cells5.setCellValue("end date");
                      }
                    }
                    for (int y = 17; y <= 18; y++) {
                           cells8 = rw4.createCell(y);
                         cells8.setCellStyle(stylex);
                     if (y == 17) {
                                cells8.setCellValue("sub total");
                        }
                    }
    a += 1;
   
shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 3));
shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 12));
shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));  
 shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));

for(int w=0;w<CountyID.length;w++){
                    String activities_selector = "select countyID, DistrictID,activityTitle,startDate,endDate,activityOthers,SUM(subtotals) from indicatoractivities where titleID='" + indicators + "' AND FinancialYear='" + year + "' AND Quarter='" + quarter + "' AND (countyID='"+countyname.get(w)+"' || countyID='"+countyid.get(w)+"')  GROUP BY activityTitle,activityOthers,countyID, DistrictID";
                     conn.rs1 = conn.state1.executeQuery(activities_selector);
                    System.out.println(activities_selector);
                    while (conn.rs1.next()) {
                        totalRowCount++;
                        county = conn.rs1.getString("countyID").toString();
                        district_name = conn.rs1.getString("DistrictID").toString();
                        activity_title = conn.rs1.getString("activityTitle").toString();
                        start_date = conn.rs1.getString("startDate").toString();
                        end_date = conn.rs1.getString("endDate").toString();
//                        w_achieved = conn.rs1.getInt("women");
//                        m_achieved = conn.rs1.getInt("men");
                        if(conn.rs1.getString("activityOthers").toString()!=null && !conn.rs1.getString("activityOthers").toString().equals("")){
                         others=conn.rs1.getString("activityOthers").toString();}
                          sub_total = conn.rs1.getInt(7);
                        //System.out.println("totalRowCount"+totalRowCount);
                        String dists[] = district_name.split("_");
                        ArrayList distArrays = new ArrayList();
                        for (int k = 0; k < dists.length; k++) {
                            if (!dists[k].isEmpty()) {
                                String district_selector = "select * from districts where districtID='" + dists[k] + "'";
                                conn.rs = conn.state.executeQuery(district_selector);
                                //System.out.println("district_selector "+district_selector);
                                while (conn.rs.next()) {
                                    district_id = conn.rs.getString("districtID");
                                    districts=conn.rs.getString("DistrictName");
                                                                      }                                 
                                }
                            }
                        
                                 
                                   
                        String county_name = "";
                        String countyselector = "select * from county where countyID='" + county + "'";
                        conn.rs2 = conn.state2.executeQuery(countyselector);
                        while (conn.rs2.next()) {
                            county_name = conn.rs2.getString("countyName");
                        }
                        String activity = "";
                          if(activity_title.equals("1031")){
                         activity =others; }
                        else{
                        String activityselector = "select * from indicatoractivity where ActivityID='" + activity_title + "'";
                        //System.out.println("activityselector "+activityselector);
                        conn.rs3 = conn.state3.executeQuery(activityselector);
                        while (conn.rs3.next()) {
                            activity = conn.rs3.getString("Activity");

                        }}                    
                        a += 1;
                      
//            ****************EXCEL FOR ONE ROW*********************************
                        HSSFRow rwi = shet1.createRow(a);
                     HSSFCell cells_1, cells_2, cells_3, cells_4, cells_5, cells_6, cells_7, cells_8, cells_9, cells_10;
                        cells_1 = rwi.createCell(2);
                        cells_1.setCellValue(county_name);
                        cells_1.setCellStyle(styley);
                        rwi.createCell(4).setCellValue(districts);
                        rwi.createCell(7).setCellValue(activity);
                        rwi.createCell(13).setCellValue(start_date);
                        rwi.createCell(15).setCellValue(end_date);
                        cells_10 = rwi.createCell(18);
                        cells_10.setCellStyle(stylez);
                        districts="";
                        cells_10.setCellValue(sub_total); 
       combinedtotal+=sub_total;
  }
                    total = total + sub_total;

                    }

                    j = i + 1;
                    a += 1;
                   
 shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 3));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 4, 6));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 12));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));

                    HSSFRow rwj = shet1.createRow(a);
                for (int y = 2; y <= 16; ++y) {
                        cells2 = rwj.createCell(y);
                       cells2.setCellStyle(stylex);
                    if (y == 2) {
                            cells2.setCellValue("Total");
                       }
                    }
                    cells5 = rwj.createCell(17);
                    cells5.setCellValue(combinedtotal);
                    cells5.setCellStyle(stylex);
                    cells5 = rwj.createCell(18);
                    cells5.setCellValue(combinedtotal);
                    cells5.setCellStyle(stylex);
                   m_achieved = w_achieved = sub_total = 0;
                    m_total = w_total = total = 0;combinedtotal=0;
                    int k = j + 1;
                    a += 1;
                    HSSFRow rwk = shet1.createRow(a);
                   HSSFCell cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, cell_10,cell_11,cell_12,cell_13,cell_14,cell_15,cell_16,cell_17,cells_11, cells_12, cells_13, cells_14, cells_15, cells_16, cells_17, cells_18, cells_19, cells_20, cells_21, cells_22;
                    int l = k + 1;
                    cell_1 = rwk.createCell(2);
                    cell_1.setCellValue("RESULTS");
                    cell_1.setCellStyle(borderStyle);
                     shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
                        a += 1;
                   HSSFRow rwl = shet1.createRow(a);
                    cell_2 = rwl.createCell(2);
                    cell_2.setCellValue("District");
                    cell_2.setCellStyle(style);
                    for (int y = 3; y <= 4; ++y) {
                        cell_3 = rwl.createCell(y);
                        cell_3.setCellStyle(style_header);
                       if (y == 3) {
                            cell_3.setCellValue("Baseline");
                        }
                    }
                    for (int y = 5; y <= 6; ++y) {
                        cell_4 = rwl.createCell(y);
                        cell_4.setCellStyle(style_header);
                     if (y == 5) {
                            cell_4.setCellValue("Results Achieved Prior Period ");
                          }
                    }
                     for (int y = 7; y <= 8; ++y) {
                        cell_11 = rwl.createCell(y);
                        cell_11.setCellStyle(style_header);
                        if (y == 7) {
                            cell_11.setCellValue("Previous Quarters("+previous_period+" "+Display_year_previous+" )");
                         }
                    }
                        for (int y = 9; y <= 12; ++y) {
                        cell_5 = rwl.createCell(y);
                        cell_5.setCellStyle(style_header);
                     
                        if (y == 9) {
                            cell_5.setCellValue("This Reporting Period "+Display_year+"");
                         
                        }
                    }
                        for (int y = 13; y <= 16; ++y) {
                        cell_6 = rwl.createCell(y);
                        cell_6.setCellStyle(style_header);
                      
                        if (y == 13) {
                            cell_6.setCellValue("Pepfar Year ("+year+" )");
                           
                        }
                    }
                    for (int y = 17; y <= 18; ++y) {
                        cell_12 = rwl.createCell(y);
                        cell_12.setCellStyle(style_header);
                       
                        if (y == 17) {
                            cell_12.setCellValue("End of Project Target: " + year);
                           
                        }
                    }

                    shet1.addMergedRegion(new CellRangeAddress(a, a, 3, 4));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(a, a,7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 9, 12));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 16));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
                    int m = l + 1;
                    a += 1;
                     HSSFCell cels_1,cels_2,cels_3,cels_4,cels_5,cels_6;
                    HSSFRow rwm = shet1.createRow(a);
                    cell_5 = rwm.createCell(2);
                    cell_5.setCellValue("");
                    cell_5.setCellStyle(style);
                    cell_6 = rwm.createCell(3);
                    cell_6.setCellValue("");
                    cell_6.setCellStyle(style);
                    cells_21 = rwm.createCell(4);
                    cells_21.setCellValue("");
                    cells_21.setCellStyle(style);
                    cell_7 = rwm.createCell(5);
                    cell_7.setCellValue("Achieved");
                    cell_7.setCellStyle(style);
                    cell_8 = rwm.createCell(7);
                    cell_8.setCellValue("Achieved");
                    cell_8.setCellStyle(style);
                    cell_9 = rwm.createCell(9);
                    cell_9.setCellValue("Target");
                    cell_9.setCellStyle(style);
                     cell_10 = rwm.createCell(11);
                    cell_10.setCellValue("Achieved");
                    cell_10.setCellStyle(style);
                    cell_14 = rwm.createCell(13);
                    cell_14.setCellValue("Target");
                    cell_14.setCellStyle(style);
                    cell_15 = rwm.createCell(15); 
                    cell_15.setCellValue("Achieved");
                    cell_15.setCellStyle(style);
                    cell_16 = rwm.createCell(17);
                    cell_16.setCellValue("");
                    cell_16.setCellStyle(style);
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
                      int n = m + 1;
                         a += 1;
                   HSSFRow rwn = shet1.createRow(a);
                   cells_22 = rwn.createCell(2);
                   cells_22.setCellValue("");
                   cells_22.setCellStyle(style);
                     cells_11 = rwn.createCell(3);
                    cells_11.setCellValue("W");
                    cells_11.setCellStyle(style);
                     cells_12 = rwn.createCell(4);
                    cells_12.setCellValue("M");
                    cells_12.setCellStyle(style);
                     cells_13 = rwn.createCell(5);
                    cells_13.setCellValue("W");
                    cells_13.setCellStyle(style);
                    cells_14 = rwn.createCell(6);
                    cells_14.setCellValue("M");
                    cells_14.setCellStyle(style);
                    cells_15 = rwn.createCell(7);
                    cells_15.setCellValue("W");
                    cells_15.setCellStyle(style);
                    cells_16 = rwn.createCell(8);
                    cells_16.setCellValue("M");
                    cells_16.setCellStyle(style);
                    cells_17 = rwn.createCell(9);
                    cells_17.setCellValue("W");
                    cells_17.setCellStyle(style);
                   cells_18 = rwn.createCell(10);
                    cells_18.setCellValue("M");
                    cells_18.setCellStyle(style);
                    cells_19 = rwn.createCell(11);
                    cells_19.setCellValue("W");
                    cells_19.setCellStyle(style);
                    cells_20 = rwn.createCell(12);
                    cells_20.setCellValue("M");
                    cells_20.setCellStyle(style);
                     cels_1 = rwn.createCell(13);
                    cels_1.setCellValue("W");
                    cels_1.setCellStyle(style);
                    cels_2 = rwn.createCell(14);
                    cels_2.setCellValue("M");
                    cels_2.setCellStyle(style);
                    cels_3 = rwn.createCell(15);
                    cels_3.setCellValue("W");
                    cels_3.setCellStyle(style);
                    cels_4 = rwn.createCell(16);
                    cels_4.setCellValue("M");
                    cels_4.setCellStyle(style);
                    cels_5 = rwn.createCell(17);
                    cels_5.setCellValue("W");
                    cels_5.setCellStyle(style);
                     cels_6 = rwn.createCell(18);
                    cels_6.setCellValue("M");
                    cels_6.setCellStyle(style);
                                      p = n + 1;
           for(int w=0;w<CountyID.length;w++){
                    String indicatorachieved = "select * from indicatorachieved where financialYear='" + year + "' AND reportingPeriod='" + quarter + "' and titleID='" + indicators + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                    conn.rs4 = conn.state4.executeQuery(indicatorachieved);
                    while (conn.rs4.next()) {
                        counter++;                       
                        womenAchieved = conn.rs4.getInt("womenAchieved");
                        menAchieved = conn.rs4.getInt("menAchieved");                       
                        String dists = conn.rs4.getString("District");                        
                        System.out.println("found dist ###  "+ dists +"*****"+ womenAchieved +"****"+ menAchieved);
                             a += 1;
                   HSSFRow rwp = shet1.createRow(a);
                      String districtID = "";
                        String queryDist = "select * from districts where DistrictName='" + dists + "'";
                        conn.rs3 = conn.state3.executeQuery(queryDist);
                        while (conn.rs3.next()) {
                            districtID = conn.rs3.getString("districtID");
                        }
                        cell_3 = rwp.createCell(2);
                        cell_3.setCellValue(dists);
                        cell_3.setCellStyle(styley);
                        cell_4 = rwp.createCell(18);
                        cell_12 =  rwp.createCell(14);
                        // yearyly baseline and end of project targets 
                        String result1 = "select * from project_target where indicator_id='" + indicators + "' AND district_id='" + districtID + "' and  county_id='"+countyid.get(w)+"'";
                         conn.rs1 = conn.state1.executeQuery(result1);
                        while (conn.rs1.next()) {
                            w_target = conn.rs1.getInt("target_women");
                            m_target = conn.rs1.getInt("target_men");
                         if(percentages==0){
                                    rwp.createCell(17).setCellValue(w_target);
                                     cell_4.setCellValue(m_target);
                                        }
                                     if(percentages==1){
                                    rwp.createCell(17).setCellValue(w_target+"%");
                                     cell_4.setCellValue(m_target+"%");
                                     }
                            
//                *********************BASELINE*******************************************

                            baseline_women = conn.rs1.getInt("baseline_women");
                            baseline_men = conn.rs1.getInt("baseline_men");
                            if(percentages==0){
                            rwp.createCell(3).setCellValue(baseline_women);
                            rwp.createCell(4).setCellValue(baseline_men);
                           
                                }
                if(percentages==1){
                             rwp.createCell(3).setCellValue(baseline_women+"%");
                             rwp.createCell(4).setCellValue(baseline_men+"%");
                            
                                     }

                        }
                     String queryTargets="select * from quartely_targets where indicator_id='" + indicators + "' AND district_id='" + districtID + "'  AND year='"+year+"' AND county_id='"+countyid.get(w)+"'";
                    conn.rs =conn.state.executeQuery(queryTargets);
                    if(conn.rs.next()==true)
                     {   targeted_women = conn.rs.getInt("target_women");
                            targeted_men = conn.rs.getInt("target_men");
                  if(percentages==0){
                            rwp.createCell(9).setCellValue(targeted_women);
                            rwp.createCell(10).setCellValue(targeted_men);
                          
                                }
                if(percentages==1){
                            rwp.createCell(9).setCellValue(targeted_women+"%");
                            rwp.createCell(10).setCellValue(targeted_men+"%");
                           
                                     }
 
                     }
                    else{
                  targeted_women=0;
                  targeted_men=0;
                     if(percentages==0){
                            rwp.createCell(9).setCellValue(targeted_women);
                            rwp.createCell(10).setCellValue(targeted_men);
                          
                                }
                if(percentages==1){
                            rwp.createCell(9).setCellValue(targeted_women+"%");
                            rwp.createCell(10).setCellValue(targeted_men+"%");
                          
                                     }
                    
                    }
                            // END OF PROJET TARGET
                     String yearTargets="select * from yearly_targets where indicator_id='" + indicators + "' AND district_id='" + districtID + "' AND year='"+year+"' AND county_id='"+countyid.get(w)+"'";
                    conn.rs =conn.state.executeQuery(yearTargets);
                   
                    if(conn.rs2.next()==true)
                     {   
//                            rwp.createCell(13).setCellValue(conn.rs2.getInt("target_women"));
//                            cell_12.setCellValue(conn.rs2.getInt("target_men"));
                         target_women=conn.rs2.getInt("target_women");
                         target_men=conn.rs2.getInt("target_men");
                          if(percentages==0){
                           rwp.createCell(13).setCellValue(target_women);
                            cell_12.setCellValue(target_men);
                         
                                }
                if(percentages==1){
                            rwp.createCell(13).setCellValue(target_women+"%");
                            cell_12.setCellValue(target_men+"%");
                            
                         
                                     }
 }
                    else{
                            
                         target_women=0;
                         target_men=0;
                          if(percentages==0){
                           rwp.createCell(13).setCellValue(target_women);
                            cell_12.setCellValue(target_men);
                        
                                }
                if(percentages==1){
                            rwp.createCell(13).setCellValue(target_women+"%");
                            cell_12.setCellValue(target_men+"%");
                            
                                     }
                    }
//             **************GET DATA FOR PREVIOUS QUARTERS********************************
                       
                        
                        // FOR PRIOR PERIODS
                        
                        
                          if (quarter.equals("Q1")) {
                            p_quarter = "Q4";
                            int f_year = Integer.parseInt(year);
                            // to get data for quarter 4, previous year
            p_year=Integer.parseInt(year)-1;
                            String result_selector2 = "select  SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q4' AND financialYear <'" + p_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved where titleID='"+indicators+"' && financialYear='"+p_year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            
                            while (conn.rs1.next()) {
                             priorQuarterW=conn.rs1.getInt(1);
                              priorQuarterM=conn.rs1.getInt(2);
                              
                                if(percentages==0){
                            rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
                              rwp.createCell(8).setCellValue(conn.rs1.getInt(2));
                          
                              
                                }
                             if(percentages==1){
                             rwp.createCell(7).setCellValue(conn.rs1.getInt(1)+"%");
                              rwp.createCell(8).setCellValue(conn.rs1.getInt(2)+"%");
                            }
         }
                            // data for quarter one same year
                       }
                        if (quarter.equals("Q4")) {
                            // data for quarter one same year
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select  SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q3' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            //String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                 priorQuarterW=conn.rs1.getInt(1);
                              priorQuarterM=conn.rs1.getInt(2);
                              
//                            rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
//                            rwp.createCell(8).setCellValue( conn.rs1.getInt(2));
                           if(percentages==0){
                           rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
                            rwp.createCell(8).setCellValue( conn.rs1.getInt(2));
                       
                        }
                             if(percentages==1){
                          
                            rwp.createCell(7).setCellValue(conn.rs1.getInt(1)+"%");
                            rwp.createCell(8).setCellValue( conn.rs1.getInt(2)+"%");
                       
                        } } }
                        // END OF QUARTER 4

                        //START OF QUARTER 3
                        if (quarter.equals("Q3")) {
                            // data for quarter 2
                            p_quarter = "Q2";
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q2' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                              
//                            rwp.createCell(7).setCellValue( conn.rs1.getInt(1));
//                            rwp.createCell(8).setCellValue(conn.rs1.getInt(2));
                              priorQuarterW=conn.rs1.getInt(1);
                              priorQuarterM=conn.rs1.getInt(2);
                              
                                 if(percentages==0){
                       rwp.createCell(7).setCellValue( conn.rs1.getInt(1));
                           rwp.createCell(8).setCellValue(conn.rs1.getInt(2));
                    
                        }
                             if(percentages==1){
                          
                          rwp.createCell(7).setCellValue( conn.rs1.getInt(1)+"%");
                           rwp.createCell(8).setCellValue(conn.rs1.getInt(2)+"%");
                       
                        }
                            }
                           
                        }

                        // END OF PRIORS FOR  QUARTER 3

                        // START OF PRIOR FOR QUARTER 2
                        if (quarter.equals("Q2")) {

                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved  where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                              priorQuarterW=conn.rs1.getInt(1);
                              priorQuarterM=conn.rs1.getInt(2);
                              
//                            rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
//                            rwp.createCell(8).setCellValue(conn.rs1.getInt(2));
                                if(percentages==0){
                        rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
                           rwp.createCell(8).setCellValue(conn.rs1.getInt(2));
                     
                        }
                             if(percentages==1){
                          
                           rwp.createCell(7).setCellValue(conn.rs1.getInt(1)+"%");
                           rwp.createCell(8).setCellValue(conn.rs1.getInt(2)+"%");
                        }
}}
            if (quarter.equals("Q1")) {
                            p_quarter = "Q4";
                            int f_year = Integer.parseInt(year);
                            // to get data for quarter 4, previous year
//            p_year=Integer.parseInt(year)-1;
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved where titleID='"+indicators+"' && financialYear='"+p_year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            
                            while (conn.rs1.next()) {
                                quarter1 = conn.rs1.getInt(1);
                                quarter_1 = conn.rs1.getInt(2);
                                                              }
                            // data for quarter one same year
                           String result_selector = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q3' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                                //rwp.createCell(5).setCellValue(conn.rs1.getInt("totalAchieved"));     
                                quarter2 = conn.rs2.getInt(1);
                                quarter_2 = conn.rs2.getInt(2);
                            }
                            String result_selector3 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q2' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                                quarter3 = conn.rs3.getInt(1);
                                quarter_3 = conn.rs3.getInt(2);
                                                  }

                            String result_selector4 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs6 = conn.state6.executeQuery(result_selector4);
                            while (conn.rs6.next()) {
                                quarter4 = conn.rs6.getInt(1);
                                quarter_4 = conn.rs6.getInt(2);

                            }

                            quartertotal = quarter1 + quarter2 + quarter3 + quarter4;
                            quartertotal_1 = quarter_1 + quarter_2 + quarter_3 + quarter_4;
                                 if(percentages==0){
                          rwp.createCell(5).setCellValue(quartertotal);
                            rwp.createCell(6).setCellValue(quartertotal_1);
                       
                        }  if(percentages==1){
                          rwp.createCell(5).setCellValue(quartertotal+"%");
                          rwp.createCell(6).setCellValue(quartertotal_1+"%");
                       }  }
                        if (quarter.equals("Q4")) {
                            // data for quarter one same year
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q3' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            //String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                counts++;
                                quarter1 = conn.rs1.getInt(1);
                                quarter_1 = conn.rs1.getInt(2);
                                          }
                            String result_selector = "select * from indicatorachieved where reportingPeriod='Q2' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                                quarter2 += conn.rs2.getInt("womenAchieved");
                                quarter_2 += conn.rs2.getInt("menAchieved");
                            }
                             String result_selector3 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                                 quarter3 = conn.rs3.getInt(1);
                                quarter_3 = conn.rs3.getInt(2);
                                         }
                                     try {
                                String result_selector4 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                                 conn.rs6 = conn.state6.executeQuery(result_selector4);
                                if (conn.rs6.next() == true) {
                                    quarter4 = conn.rs6.getInt(1);
                                    quarter_4 = conn.rs6.getInt(2);
                                 } else {
                                    quarter4 = 0;
                                }
                            } catch (SQLException EX) {
                                System.out.println("error " + EX.toString());
                            }
                            quartertotal = quarter1 + quarter2 + quarter3 + quarter4;
                            quartertotal_1 = quarter_1 + quarter_2 + quarter_3 + quarter_4;
                            if(percentages==0){
                            rwp.createCell(5).setCellValue(quartertotal);
                            rwp.createCell(6).setCellValue(quartertotal_1);
                                                 }
                             if(percentages==1){
                           rwp.createCell(5).setCellValue(quartertotal+"%");
                           rwp.createCell(6).setCellValue(quartertotal_1+"%");
                        } }
                        // END OF QUARTER 4

                        //START OF QUARTER 3
                   if (quarter.equals("Q3")) {
                            // data for quarter 2
                            p_quarter = "Q2";
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q2' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                quarter1 = conn.rs1.getInt(1);
                                quarter_1 = conn.rs1.getInt(2);
                            }

                            // data for quarter one same year

                            String result_selector = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                             conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                                //rwp.createCell(5).setCellValue(conn.rs1.getInt("totalAchieved"));     
                                quarter2 = conn.rs2.getInt(1);
                                quarter_2 = conn.rs2.getInt(2);
                            }
                         String result_selector3 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q3' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                                quarter3 = conn.rs3.getInt(1);
                                quarter_3 = conn.rs3.getInt(2);
                                             }
                      String result_selector4 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs6 = conn.state6.executeQuery(result_selector4);
                            while (conn.rs6.next()) {
                                quarter4 = conn.rs6.getInt(1);
                                quarter_4 = conn.rs6.getInt(2);  }
                               quartertotal = quarter1 + quarter2 + quarter3 + quarter4;
                            quartertotal_1 = quarter_1 + quarter_2 + quarter_3 + quarter_4;
                            if(percentages==0){
                              rwp.createCell(5).setCellValue(quartertotal);
                              rwp.createCell(6).setCellValue(quartertotal_1);
                                    }
                             if(percentages==1){
                            rwp.createCell(5).setCellValue(quartertotal+"%");
                              rwp.createCell(6).setCellValue(quartertotal_1+"%");
                                         }
System.out.println(indicators+"-----"+quartertotal);
System.out.println(indicators+"-----"+quartertotal_1);
                        FINALTOTALQUARTER+=quartertotal;
                        FINALTOTALQUARTER1+=quartertotal_1;
                        }

                        // END OF PRIORS FOR  QUARTER 3

                        // START OF PRIOR FOR QUARTER 2
                        if (quarter.equals("Q2")) {
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                          conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                quarter1 = conn.rs1.getInt(1);
                                quarter_1 = conn.rs1.getInt(2); }
                         String result_selector = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                                quarter2 = conn.rs2.getInt(1);
                                quarter_2 = conn.rs2.getInt(2);}

                            String result_selector3 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q3' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                                quarter3 = conn.rs3.getInt(1);
                                quarter_3 = conn.rs3.getInt(2);  }

                            String result_selector4 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q2' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs6 = conn.state6.executeQuery(result_selector4);
                            while (conn.rs6.next()) {
                                quarter4 = conn.rs4.getInt("womenAchieved");
                                quarter_4 = conn.rs4.getInt("menAchieved");}
                            totalquarter = quarter1 + quarter2 + quarter3 + quarter4;
                            totalquarter_1 = quarter_1 + quarter_2 + quarter_3 + quarter_4;
                        
                        if(percentages==0){
                             rwp.createCell(5).setCellValue(totalquarter);
                            rwp.createCell(6).setCellValue(totalquarter_1);
                         
                        }                             if(percentages==1){
                            rwp.createCell(5).setCellValue(totalquarter+"%");
                            rwp.createCell(6).setCellValue(totalquarter_1+"%"); }

                        }
 /// FOR PRIOR TARGET
                        if (quarter.equals("Q1")) {
                            int f_year = Integer.parseInt(year);
                           String result_selector = "select SUM(womenAchieved), SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                             conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                             PriorTargetW=conn.rs2.getInt(1);
                             PriorTargetM=conn.rs2.getInt(2);
                                if(percentages==0){
                            rwp.createCell(15).setCellValue(PriorTargetW);
                           rwp.createCell(16).setCellValue(PriorTargetM);
                                 }
                             if(percentages==1){
                                 rwp.createCell(15).setCellValue(PriorTargetW+"%");
                                rwp.createCell(16).setCellValue(PriorTargetM+"%");
                              }
                             }
                         }
                        if (quarter.equals("Q4")) {
                            // data for quarter one same year
                            int f_year = Integer.parseInt(year);
                            int p_year=f_year-1;
                            String result_selector2 = "select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q3' OR reportingPeriod='Q2' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname+"' || County='"+countyid+"')";
                             String results= " select SUM(womenAchieved),SUM(menAchieved) from indicatorachieved where reportingPeriod='Q1'  AND financialYear ='" + p_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            int data=0;
                            int data1=0;
                             conn.rs6 =conn.state6.executeQuery(results);
                             while(conn.rs6.next()){
                             data= conn.rs6.getInt(1);
                             data1= conn.rs6.getInt(2);
                             }
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                 PriorTargetW=conn.rs1.getInt(1)+data;
                                PriorTargetM=conn.rs1.getInt(2)+data1;
                                 if(percentages==0){
                           rwp.createCell(15).setCellValue(PriorTargetW);
                            rwp.createCell(16).setCellValue(PriorTargetM); }
                             if(percentages==1){
                              rwp.createCell(15).setCellValue(PriorTargetW+"%");
                            rwp.createCell(16).setCellValue(PriorTargetM+"%");}
                                     }
                        }
                    
                        //START OF QUARTER 3
                        if (quarter.equals("Q3")) {
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved), SUM(menAchieved) from indicatorachieved where (reportingPeriod='Q2' OR reportingPeriod='Q1') AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                              PriorTargetW=conn.rs1.getInt(1);
                              PriorTargetM=conn.rs1.getInt(2);
                              System.out.println(PriorTargetW);
                              System.out.println(PriorTargetM);
                             if(percentages==0){
                           rwp.createCell(15).setCellValue(PriorTargetW);
                            rwp.createCell(16).setCellValue(PriorTargetM);
                              }
                             if(percentages==1){
                           rwp.createCell(15).setCellValue(PriorTargetW+"%");
                            rwp.createCell(16).setCellValue(PriorTargetM+"%");
                        } }
                        }

                        // END OF PRIORS FOR  QUARTER 3

                        // START OF PRIOR FOR QUARTER 2
                        if (quarter.equals("Q2")) {
                              int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(womenAchieved), SUM(menAchieved) from indicatorachieved where (reportingPeriod='Q1' OR  reportingPeriod='Q2') AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved  where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                              PriorTargetW=conn.rs1.getInt(1);
                              PriorTargetM=conn.rs1.getInt(2);
                              System.out.println(PriorTargetW);
                              System.out.println(PriorTargetM);
                             if(percentages==0){
                           rwp.createCell(15).setCellValue(PriorTargetW);
                            rwp.createCell(16).setCellValue(PriorTargetM);
                        }
                             if(percentages==1){
                           rwp.createCell(15).setCellValue(PriorTargetW+"%");
                            rwp.createCell(16).setCellValue(PriorTargetM+"%");
                      
                        } } }
                

//         *******************DISPLAY IN EXCELL THIS DATA FOR PREVIOUS QUARTER*******************************       

//  ***********************TARGETED AND ACHIEVED IN THAT QUARTER ******************************************
     if(percentages==0){
                        rwp.createCell(11).setCellValue(womenAchieved);
                        rwp.createCell(12).setCellValue(menAchieved);
                        }
           if(percentages==1){
                        rwp.createCell(11).setCellValue(womenAchieved+"%");
                         rwp.createCell(12).setCellValue(menAchieved+"%");    
                           }
            cell_4.setCellStyle(stylez);
                   // end of while for getting all the data 
                   totalWomen+=womenAchieved;
                    totalMen+=menAchieved;       
                
                    TotalTargetW+=target_women; 
                     TotalTargetM+=target_men; 
                        
                    Total_baselineM+=baseline_men;
                    Total_baselineW+=baseline_women;
                  
                    TotalTargeted_W+=targeted_women;
                    TotalTargeted_M+=targeted_men;
                    
                    Total_Wtarget+=w_target;
                    Total_Mtarget+=m_target;
                    
                    System.out.println("||||"+totalquarter+"__"+totalWomen+"___"+totalMen+"____"+TotalTargetW +"_______"+TotalTargetM);
                    System.out.println("||||"+totalquarter_1);
                    TotalQuarterW+=totalquarter;
                    TotalQuarterM+=totalquarter_1;
//                  System.out.println()  
                    TotalpriorQuarterW+=priorQuarterW;
                    TotalpriorQuarterM+=priorQuarterM;
                 
                    TotalPriorTargetW+=PriorTargetW;
                    TotalPriorTargetM+=PriorTargetM;
 }
                    }
                 
                 
                // end of for loop
                   HSSFCell cel1,cel2,cel3,cel4,cel5,cel6,cel7,cel8,cel9,cel10,cel11,cel12,cel13,cel14,cel15,cel16;  
                   HSSFCell cel1_3,cel2_3,cel3_3,cel4_3,cel5_3,cel6_3,cel7_3,cel8_3,cel9_3,cel10_3,cel11_3,cel12_3,cel13_3,cel14_3,cel15_3,cel16_3;  
                 a+=1;
                
   HSSFRow rwp1 = shet1.createRow(a);
 
                    for (int y = 2; y <= 4; ++y) {
                          cell_5 = rwp1.createCell(y);
                        cell_5.setCellStyle( stylex);
                        if (y == 2) {
                            cell_5.setCellValue("Total");
                         }
                    }
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 4));
                      cel1 = rwp1.createCell(11);
                      cel1.setCellStyle( stylex);
                      cel2 = rwp1.createCell(12);
                      cel2.setCellStyle( stylex);
                       if (percentages==0){
                           cel1.setCellValue(totalWomen);
                           cel2.setCellValue(totalMen);
                          System.out.println(totalWomen+"^^^^"+totalMen);

                         }
                        if (percentages==1){
                            if(countDist>0){
                           cel1.setCellValue(totalWomen/countDist+"%");
                           cel2.setCellValue(totalMen/countDist+"%");
                      
                            }
                        }
                         cel3 = rwp1.createCell(13);
                      cel3.setCellStyle( stylex);
                      cel4 = rwp1.createCell(14);
                      cel4.setCellStyle( stylex);
                      if (percentages==0){
                      cel3.setCellValue(TotalTargetW);
                         cel4.setCellValue(TotalTargetM);
                  }
                         if (percentages==1){
                            if(countDist>0){
                       cel3.setCellValue(TotalTargetW/countDist+"%");
                      cel4.setCellValue(TotalTargetM/countDist+"%");
                      
                            }}
                      
                      cel5 = rwp1.createCell(3);
                      cel5.setCellStyle( stylex);
                      cel6 = rwp1.createCell(4);
                      cel6.setCellStyle( stylex); 
                     if (percentages==0){
                     cel5.setCellValue(Total_baselineW);
                       cel6.setCellValue(Total_baselineM);
                      }
                      
                       if (percentages==1){
                            if(countDist>0){
                      cel5.setCellValue(Total_baselineW/countDist+"%");
                      cel6.setCellValue(Total_baselineM/countDist+"%");
                     }}
                       cel7 = rwp1.createCell(9);
                      cel7.setCellStyle( stylex);
                      cel8 = rwp1.createCell(10);
                      cel8.setCellStyle( stylex);
                      if (percentages==0){
                      cel7.setCellValue(TotalTargeted_W);
                      cel8.setCellValue(TotalTargeted_M);
                 }
                       if (percentages==1){
                            if(countDist>0){
                      cel7.setCellValue(TotalTargeted_W/countDist+"%");
                      cel8.setCellValue(TotalTargeted_M/countDist+"%");
                
                            }}
                         
                         
                      cel9 = rwp1.createCell(17);
                      cel9.setCellStyle( stylex);
                      cel10 = rwp1.createCell(18);
                      cel10.setCellStyle( stylex);

                       if (percentages==0){
                      cel9.setCellValue(Total_Wtarget);
                      cel10.setCellValue(Total_Mtarget);
                   
                       }
                         if (percentages==1){
                            if(countDist>0){
                      cel9.setCellValue(Total_Wtarget/countDist+"%");
                      cel10.setCellValue(Total_Mtarget/countDist+"%");
                    
                            }}
                      cel11 = rwp1.createCell(5);
                      cel11.setCellStyle( stylex);
                      cel12 = rwp1.createCell(6);
                      cel12.setCellStyle( stylex);
                        if (percentages==0){

                      cel11.setCellValue(TotalQuarterW);
                      cel12.setCellValue(TotalQuarterM);
                       System.out.println("||||"+TotalQuarterW);
                      System.out.println("||||"+TotalQuarterM);
                        }
                       if (percentages==1){
                            if(countDist>0){
                        cel11.setCellValue(TotalQuarterW/countDist+"%");
                      cel12.setCellValue(TotalQuarterM/countDist+"%");
                      }}
                       TotalQuarterW=0;
                       TotalQuarterM=0;
                      cel13 = rwp1.createCell(7);
                      cel13.setCellStyle( stylex);
                      cel14 = rwp1.createCell(8);
                      cel14.setCellStyle( stylex);
                     
                       if (percentages==0){
                      cel13.setCellValue(TotalpriorQuarterW);
                      cel14.setCellValue(TotalpriorQuarterM);
                      }
                         if (percentages==1){
                            if(countDist>0){
                      cel13.setCellValue(TotalpriorQuarterW+"%");
                      cel14.setCellValue(TotalpriorQuarterM+"%");
                         }}
                       cel15 = rwp1.createCell(15);
                      cel15.setCellStyle(stylex);
                      cel16 = rwp1.createCell(16);
                      cel16.setCellStyle( stylex);
                        if (percentages==0){
                      cel15.setCellValue(TotalPriorTargetW);
                      cel16.setCellValue(TotalPriorTargetM);
                      }
                          if (percentages==1){
                            if(countDist>0){
                          cel15.setCellValue(TotalPriorTargetW+"%");
                      cel16.setCellValue(TotalPriorTargetM+"%");
                        
                            }}
 TotalPriorTargetW=0;
   TotalPriorTargetM=0;
                         
                    a +=1;
                  HSSFRow rwp2 = shet1.createRow(a);
                   for (int y = 2; y <= 18; ++y) {
                         cell_5 = rwp2.createCell(y);
                        cell_5.setCellStyle(styletop);
                        if (y == 2) {
                            cell_5.setCellValue("");
                         
                        }
                    }
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
                   a += 3;
                
                } //------------------------------- end of title with separate totals for men and women _---------------------------------------------------
                //------------------------------- beginining of title with combined totals for men and women _---------------------------------------------------
                else {
                for(int w=0;w<CountyID.length;w++){  
                  totalquarter = quarter1= quarter2= quarter3 = quarter4=0;   
                   String districtCount1 = "select count(resultID) from indicatorachievedcombined where financialYear='" + year + "' AND reportingPeriod='" + quarter + "' and titleID='" + indicators+ "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                   
//                   System.out.println(districtCount1+"   "+year+"@@@"+quarter)
                   conn.rs6 = conn.state6.executeQuery(districtCount1);
                 // System.out.println(districtCount1);
                    if(conn.rs6.next()){
                   countersfor++;
                  countDist1+=conn.rs6.getInt(1);
                    
                    } 
                }
             System.out.println("countDist1______   "+countDist1 +"______"+countersfor);
    HSSFRow rw0 = shet1.createRow(a);
            
                    HSSFCell cell0;
                 rw0.setHeightInPoints(20);
          shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
          HSSFRow rw1 = shet1.createRow(a + 2);
                    HSSFCell cell_0;
             for (int y = 2; y <= 18; ++y) {
                        cell_0 = rw1.createCell(y);
                         cell_0.setCellStyle(style_header);
                         if (y == 2) {
                            cell_0.setCellValue("Table   :" + indicators + " " + "ACTIVITIES");
                        }
                    }

                    a += 2;                
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
                   HSSFRow rw2 = shet1.createRow(a + 2);
                    HSSFCell cell2;
                    for (int y = 2; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                        cell2 = rw2.createCell(y);
                        cell2.setCellStyle(style_header);
                       
                        if (y == 2) {
                            cell2.setCellValue("Indicator Title: " + indicatortitle);
                       }
                    }

//  Merge the cells
                    a += 2;
                   shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
                     HSSFRow rw3 = shet1.createRow(a + 1);
                    HSSFCell cell3;
                 for (int y = 2; y <= 18; ++y) {
                        cell3 = rw3.createCell(y);
                        cell3.setCellStyle(style_header);
                     if (y == 2) {
                            cell3.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
                        }
                    }
//  Merge the cells
                    a += 1;
                 shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
                HSSFRow rw4 = shet1.createRow(a + 1);
                   HSSFCell HSSFCell1,HSSFCell2, HSSFCell3, HSSFCell4, HSSFCell5, HSSFCell6, HSSFCell7, HSSFCell8, HSSFCell9, HSSFCell10;
                      for (int y = 2; y <= 4; y++) {
                         HSSFCell1 = rw4.createCell(y);
                          HSSFCell1.setCellStyle(stylex);
                     if (y == 2) {
                               HSSFCell1.setCellValue("County");
                        }
                    }
                for (int y = 4; y <= 6; y++) {
                         HSSFCell2 = rw4.createCell(y);
                            HSSFCell2.setCellStyle(stylex);
                       
                        if (y ==4) {
                               HSSFCell2.setCellValue("Geographical Location");
                        }
                    }
                    for (int y = 7; y <= 12; y++) {
                          HSSFCell3 = rw4.createCell(y);
                          HSSFCell3.setCellStyle(stylex);
                      if (y == 7) {
                              HSSFCell3.setCellValue("Activity title");
                        }
                    }
                for (int y = 13; y <= 14; y++) {
                           HSSFCell4 = rw4.createCell(y);
                             HSSFCell4.setCellStyle(stylex);
                   if (y == 13) {
                             HSSFCell4.setCellValue("start date");
                        }
                    }  
                            for (int y = 15; y <= 16; y++) {
             HSSFCell5 = rw4.createCell(y);
              HSSFCell5.setCellStyle(stylex);
               if (y == 15) {
                   HSSFCell5.setCellValue("end date");
                                    }
                                }
               for (int y = 17; y <= 18; y++) {
                       HSSFCell6 = rw4.createCell(y);
                         HSSFCell6.setCellStyle(stylex);
                         if (y == 17) {
                                HSSFCell6.setCellValue("sub total");
                        }
                    }

 a += 1;
 shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 3));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 4, 6));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 12));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));         
 shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
    for(int w=0;w<CountyID.length;w++){    
                    String activities_selector = "select countyID,districtID,activityTitle,startDate,endDate,activityOthers,SUM(total)from indicatoractivities1 where titleID='" + indicators + "' AND FinancialYear='" + year + "' AND Quarter='" + quarter + "' AND (countyID='"+countyname.get(w)+"' || countyID='"+countyid.get(w)+"') GROUP BY activityTitle,districtID,activityOthers,countyID";
                   conn.rs1 = conn.state1.executeQuery(activities_selector);
                    while (conn.rs1.next()) {
                        totalRowCount++;
                        county = conn.rs1.getString("countyID").toString();
                        district_name = conn.rs1.getString("districtID").toString();
                        activity_title = conn.rs1.getString("activityTitle").toString();
                        start_date = conn.rs1.getString("startDate").toString();
                        end_date = conn.rs1.getString("endDate").toString();
                        if(conn.rs1.getString("activityOthers")!=null && !conn.rs1.getString("activityOthers").toString().equals("")){
                         others=conn.rs1.getString("activityOthers").toString();}
                        total = conn.rs1.getInt(7);

                        String dists[] = district_name.split("_");
                        for (int k = 0; k < dists.length; k++) {
                            if (!dists[k].isEmpty()) {
                                String district_selector = "select * from districts where districtID='" + dists[k] + "'";
                                conn.rs = conn.state.executeQuery(district_selector);
                                //System.out.println("district_selector "+district_selector);
                                while (conn.rs.next()) {
                                    district_id = conn.rs.getString("districtID");
                                    districts= conn.rs.getString("DistrictName");
                                    //System.out.println("district_id   "+district_id);
                                }
                            }
                        }
                       
                        String county_name = "";
                        String countyselector = "select * from county where countyID='" + county + "'";
                        conn.rs2 = conn.state2.executeQuery(countyselector);
                        while (conn.rs2.next()) {
                            county_name = conn.rs2.getString("countyName");
                        }
                        String activity = "";
                         if(activity_title.equals("1031")){
                         activity = others;}
                        else{
                        String activityselector = "select * from indicatoractivity where ActivityID='" + activity_title + "'";
                        //System.out.println("activityselector "+activityselector);
                        conn.rs3 = conn.state3.executeQuery(activityselector);
                        while (conn.rs3.next()) {
                            activity = conn.rs3.getString("Activity");

                        }}
                        //System.out.println("activity     "+activity);
                        a += 1;
                      
//            ****************EXCEL FOR ONE ROW*********************************
                         HSSFRow rwi = shet1.createRow(a);
                        HSSFCell cells_1,cells_10;
                        cells_1 = rwi.createCell(2);
                        cells_1.setCellValue(county_name);
                        cells_1.setCellStyle(styley);
                        rwi.createCell(4).setCellValue(districts);
                        rwi.createCell(7).setCellValue(activity);
                        rwi.createCell(13).setCellValue(start_date);
                        rwi.createCell(15).setCellValue(end_date);
                        cells_10 = rwi.createCell(18);
                        cells_10.setCellStyle(stylez);
                        cells_10.setCellValue(total);
                         districts="";
                        cells_10.setCellValue(total);
                         w_total+= total;
       }

}       

   a += 1;
 shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 3));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 4, 6));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 12));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
 shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
 HSSFRow rwj = shet1.createRow(a);
 HSSFCell  cells_2, cells_3, cells_4, cells_5, cells_6, cells_7, cells_8, cells_9;

                    for (int y = 2; y <=16; ++y) {
                        cells_2 = rwj.createCell(y);
                         cells_2.setCellStyle(stylex);
                       if (y == 2) {
                            cells_2.setCellValue("Total");
                        }
                    }
                    cells_5 = rwj.createCell(17);
                    cells_5.setCellValue(w_total);
                    cells_5.setCellStyle(stylex);
                    cells_5 = rwj.createCell(18);
                    cells_5.setCellValue(w_total);
                    cells_5.setCellStyle(stylex);
                    m_achieved = w_achieved = sub_total = 0;
                    m_total = w_total = total = 0;
                    int k = j + 1;
                    a += 1;
                 HSSFRow rwk = shet1.createRow(a);
                // for men and women 
                    HSSFCell cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, cell_10,cell_11,cell_12,cell_13,cell_14,cell_15,cell_16,cell_17,cell_18,cell_19,cells_11, cells_12, cells_13, cells_14, cells_15, cells_16, cells_17, cells_18, cells_19, cells_20, cells_21, cells_22;
                    cell_1 = rwk.createCell(2);
                    cell_1.setCellValue("RESULTS");
                    cell_1.setCellStyle(borderStyle);
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
                   a += 1;
                   HSSFRow rwl = shet1.createRow(a);
                    cell_2 = rwl.createCell(2);
                    cell_2.setCellValue("District");
                    cell_2.setCellStyle(style);
                    for (int y = 3; y <= 4; ++y) {
                        cell_3 = rwl.createCell(y);
                        cell_3.setCellStyle(style_header);
                        if (y == 3) {
                            cell_3.setCellValue("Baseline");
                        }
                    }
                    for (int y = 5; y <= 6; ++y) {
                        cell_4 = rwl.createCell(y);
                        cell_4.setCellStyle(style_header);
                       if (y == 5) {
                            cell_4.setCellValue("Achieved Prior Period");
                        }
                    }
                   for (int y = 7; y <= 8; ++y) {
                        cell_5 = rwl.createCell(y);
                        cell_5.setCellStyle(style_header);
                        if (y == 7) {
                            cell_5.setCellValue("Prior Quarter  ("+previous_period+" "+Display_year_previous+" )");
                        } }
                 for (int y = 9; y <= 12; ++y) {
                        cell_6 = rwl.createCell(y);
                        cell_6.setCellStyle(style_header);
                        if (y == 9) {
                            cell_6.setCellValue("This reporting period ("+period+" "+Display_year+" )");
                        } }
                    for (int y = 13; y <= 16; ++y) {
                        cell_13 = rwl.createCell(y);
                        cell_13.setCellStyle(style_header);
                 if (y == 13) {
                            cell_13.setCellValue("Pepfar year ("+year+" )"); }
                    }
                    for (int y = 17; y <= 18; ++y) {
                        cell_14 = rwl.createCell(y);
                        cell_14.setCellStyle(style_header); 
                        if (y == 17) {
                            cell_14.setCellValue("End of Project Target");
                       }
                    }
                    shet1.addMergedRegion(new CellRangeAddress(a,a,3,4));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 9, 12));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 16));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
                    a += 1;
                    HSSFRow rwm = shet1.createRow(a);
                    cell_5 = rwm.createCell(2);
                    cell_5.setCellValue("");
                    cell_5.setCellStyle(style);
                    cell_6 = rwm.createCell(3);
                    cell_6.setCellValue("");
                    cell_6.setCellStyle(style);
                    cells_21 = rwm.createCell(4);
                    cells_21.setCellValue("");
                    cells_21.setCellStyle(style);
                    cell_7 = rwm.createCell(5);
                    cell_7.setCellValue("Achieved");
                    cell_7.setCellStyle(style);
                    cell_7 = rwm.createCell(6);
                    cell_7.setCellValue("");
                    cell_7.setCellStyle(style);
                    cell_8 = rwm.createCell(7);
                    cell_8.setCellValue("Achieved");
                    cell_8.setCellStyle(style);
                    cell_8 = rwm.createCell(8);
                    cell_8.setCellValue("");
                    cell_8.setCellStyle(style);
                    cell_9 = rwm.createCell(9);
                    cell_9.setCellValue("Target");
                    cell_9.setCellStyle(style);
                    cell_9 = rwm.createCell(10);
                    cell_9.setCellValue("");
                    cell_9.setCellStyle(style);
                    cell_10 = rwm.createCell(11);
                    cell_10.setCellValue("Achieved");
                    cell_10.setCellStyle(style);
                    cell_10 = rwm.createCell(12);
                    cell_10.setCellValue("");
                    cell_10.setCellStyle(style);
                    cell_15 = rwm.createCell(13);
                    cell_15.setCellValue("Target");
                    cell_15.setCellStyle(style);
                     cell_15 = rwm.createCell(14);
                    cell_15.setCellValue("");
                    cell_15.setCellStyle(style);
                    cell_16 = rwm.createCell(15);
                    cell_16.setCellValue("Achieved");
                    cell_16.setCellStyle(style);
                     cell_17 = rwm.createCell(16);
                    cell_17.setCellValue("Target");
                    cell_17.setCellStyle(style);
                    cell_18 = rwm.createCell(17);
                    cell_18.setCellValue("");
                    cell_18.setCellStyle(style);
                    cell_18 = rwm.createCell(18);
                    cell_18.setCellValue("");
                    cell_18.setCellStyle(style);
                   shet1.addMergedRegion(new CellRangeAddress(a, a, 3, 4));
                   shet1.addMergedRegion(new CellRangeAddress(a, a, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
                 int totalAchieved = 0;
for(int w=0;w<CountyID.length;w++){
                    String indicatorachieved = "select * from indicatorachievedcombined where financialYear='" + year + "' AND reportingPeriod='" + quarter + "' and titleID='" + indicators + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"') order by county";
                    conn.rs4 = conn.state4.executeQuery(indicatorachieved);
                     while (conn.rs4.next()) {
                         counter++;
                         String sae=null;
                        sae=conn.rs4.getString("totalAchieved");
                        if(sae!=null){
                        totalAchieved = conn.rs4.getInt("totalAchieved");}
                    String dists = conn.rs4.getString("District");
                        System.out.println(dists);
                          a += 1;
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 3, 4));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(a, a, 17, 18));
                        HSSFRow rwp = shet1.createRow(a);
                        cell_12 =  rwp.createCell(18);
                        cell_3 = rwp.createCell(2);
                        cell_3.setCellValue(dists);
                        cell_3.setCellStyle(styley);
                        String districtID = "";
                        String queryDist = "select * from districts where DistrictName='" + dists + "'";
                        conn.rs3 = conn.state3.executeQuery(queryDist);
                        while (conn.rs3.next()) {
                            districtID = conn.rs3.getString("districtID");
                        }
   String result1 = "select * from project_target where indicator_id='" + indicators + "' AND district_id='" + districtID + "' AND county_id='"+countyid.get(w)+"'";
  conn.rs1 = conn.state1.executeQuery(result1);
  while (conn.rs1.next()) {
                            target_combined=conn.rs1.getInt(10);
                            baseline_combined=conn.rs1.getInt("baseline_combined");
            if(percentages==0){
                      rwp.createCell(3).setCellValue(baseline_combined);
                      cell_12.setCellValue(target_combined);
                     }
                             if(percentages==1){
                        rwp.createCell(3).setCellValue(baseline_combined+"%");
                            cell_12.setCellValue(target_combined+"%"); } }
//             ***********targeted quarterly***************
 String queryTargets="select * from quartely_targets where indicator_id='" + indicators + "' AND district_id='" + districtID + "' AND year='"+year+"' AND county_id='"+county_id+"'";
                    conn.rs =conn.state.executeQuery(queryTargets);
                    if(conn.rs.next()==true)
                     {      cell_4 = rwp.createCell(9);  
                     Qtrtarget_combined=conn.rs.getInt("target_combined");
                  if(percentages==0){
                       cell_4.setCellValue(Qtrtarget_combined);
                      }
                             if(percentages==1){
                         cell_4.setCellValue(Qtrtarget_combined+"%");
                       }   }
                    else{
                         cell_4 = rwp.createCell(9);
                        Qtrtarget_combined=0;
                      if(percentages==0){
                       cell_4.setCellValue(Qtrtarget_combined); }
                             if(percentages==1){
                         cell_4.setCellValue(Qtrtarget_combined+"%");
                      }  }    
                            
                            // END OF PROJET TARGET
                     String yearTargets="select * from yearly_targets where indicator_id='" + indicators + "' AND district_id='" + districtID + "' AND year='"+year+"' AND county_id='"+countyid.get(w) +"'";
                    conn.rs2 =conn.state2.executeQuery(yearTargets);
                    if(conn.rs2.next()==true)
                     {    Yrtarget_combined=conn.rs2.getInt("target_combined"); 
                     if(percentages==0){
                        rwp.createCell(13).setCellValue(Yrtarget_combined); }
                             if(percentages==1){
                          rwp.createCell(13).setCellValue(Yrtarget_combined+"%");
                       }
                         
 }
                    else{   Yrtarget_combined=0;
                    if(percentages==0){
                        rwp.createCell(13).setCellValue(Yrtarget_combined);
                      }  if(percentages==1){
                          rwp.createCell(13).setCellValue(Yrtarget_combined+"%");
                        }
                     }

//             **************GET DATA FOR PREVIOUS QUARTERS********************************
          if (quarter.equals("Q1")) {
                            p_quarter = "Q4";
                            int f_year = Integer.parseInt(year);
           String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (County='"+countyname.get(w)+"' || County='"+countyid.get(w)+"')";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                quarter1 = conn.rs1.getInt(1);  }
                            // data for quarter one same year
          String result_selector = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q3' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                                //rwp.createCell(5).setCellValue(conn.rs1.getInt("totalAchieved"));     
                                quarter2 = conn.rs2.getInt(1);
                            }
               String result_selector3 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q2' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                                quarter3 = conn.rs3.getInt(1);
                             }
              String result_selector4 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs6 = conn.state6.executeQuery(result_selector4);
                            while (conn.rs6.next()) {
                                quarter4 = conn.rs6.getInt(1);
                            }
         quartertotal = quarter1 + quarter2 + quarter3 + quarter4;
                            if(percentages==0){
                       rwp.createCell(5).setCellValue(quartertotal);
                           }
                             if(percentages==1){
                          rwp.createCell(5).setCellValue(quartertotal+"%");
                          } }
                        if (quarter.equals("Q4")) {
                            // data for quarter one same year
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q3' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                           conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                              quarter1 = conn.rs1.getInt(1);
                                 }
                            String result_selector = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q2' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                            quarter2 = conn.rs2.getInt(1);
                            }
                          String result_selector3 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                            quarter3 = conn.rs3.getInt(1);
                           }
                        try {
                                String result_selector4 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"') ";
                              //  System.out.println("query " + result_selector4);
                                conn.rs6 = conn.state6.executeQuery(result_selector4);
                                if (conn.rs6.next() == true) {
                            quarter4 = conn.rs6.getInt(1);
                            } else {
                                    quarter4 = 0;
                                }
                            } catch (SQLException EX) {
                                System.out.println("error " + EX.toString());
                            }

  quartertotal = quarter1 + quarter2 + quarter3 + quarter4;
                        
                             if(percentages==0){
                        rwp.createCell(5).setCellValue(quartertotal);
                        }  if(percentages==1){
                         rwp.createCell(5).setCellValue(quartertotal+"%");
                        }          }
                        // END OF QUARTER 4

                        //START OF QUARTER 3
                        if (quarter.equals("Q3")) {
                            // data for quarter 2
                            p_quarter = "Q2";
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q2' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                               quarter2 = conn.rs1.getInt(1);
                            }

                            // data for quarter one same year

                            String result_selector = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                           conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                           
                                quarter1 = conn.rs2.getInt(1);
                            }

                            quartertotal = quarter1 + quarter2 ;
                            if(percentages==0){
                        rwp.createCell(5).setCellValue(quartertotal);
                     

                        }
                             if(percentages==1){
                           rwp.createCell(5).setCellValue(quartertotal/2+"%");
                     

                        }

                        }

                        // END OF PRIORS FOR  QUARTER 3

                        // START OF PRIOR FOR QUARTER 2
                        if (quarter.equals("Q2")) {

                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1' AND financialYear <='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved  where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                quarter1 = conn.rs1.getInt(1);
                            }

                            String result_selector = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q4' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                                quarter2 = conn.rs2.getInt(1);

                            }

                            String result_selector3 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q3' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs3 = conn.state3.executeQuery(result_selector3);
                            while (conn.rs3.next()) {
                                quarter3 = conn.rs3.getInt(1);

                            }

                            String result_selector4 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q2' AND financialYear <'" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            conn.rs6 = conn.state6.executeQuery(result_selector4);
                            while (conn.rs6.next()) {
                                quarter4 = conn.rs6.getInt(1);

                            }

                            totalquarter = quarter1 + quarter2 + quarter3 + quarter4;
                           if(percentages==0){
                        rwp.createCell(5).setCellValue(totalquarter);
                  

                        }
                             if(percentages==1){
                          rwp.createCell(5).setCellValue(totalquarter+"%");
                      

                        }


                        }


//                         **************GET DATA FOR PREVIOUS QUARTERS********************************
                       
                        
                        // FOR PRIOR PERIODS
                        
                        
                          if (quarter.equals("Q1")) {
                            p_quarter = "Q4";
                            int f_year = Integer.parseInt(year);
                            // to get data for quarter 4, previous year
            p_year=Integer.parseInt(year)-1;
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q4' AND financialYear ='" + p_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved where titleID='"+indicators+"' && financialYear='"+p_year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            //System.out.println("q1    " + result_selector2);
                            while (conn.rs1.next()) {
//                              rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
                                priorAchieved =conn.rs1.getInt(1);
                    if(percentages==0){
                         rwp.createCell(7).setCellValue(priorAchieved);
                        
                        }
                             if(percentages==1){
                          rwp.createCell(7).setCellValue(priorAchieved+"%");
                     

                        }                          
                            }
                            // data for quarter one same year
                          
                        }
                        if (quarter.equals("Q4")) {
                            // data for quarter one same year
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q3' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            //String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                                priorAchieved =conn.rs1.getInt(1);
//                            rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
                                if(percentages==0){
                         rwp.createCell(7).setCellValue(priorAchieved);
                     

                        }
                             if(percentages==1){
                          rwp.createCell(7).setCellValue(priorAchieved+"%");
                        

                        }
                           
                           

                            }
                          
                          
                        }
                        // END OF QUARTER 4

                        //START OF QUARTER 3
                        if (quarter.equals("Q3")) {
                            // data for quarter 2
                            p_quarter = "Q2";
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q2' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                              
//                            rwp.createCell(7).setCellValue( conn.rs1.getInt(1));
                              priorAchieved =conn.rs1.getInt(1);   
                                if(percentages==0){
                         rwp.createCell(7).setCellValue(priorAchieved);
                     

                        }
                             if(percentages==1){
                          rwp.createCell(7).setCellValue(priorAchieved+"%");
                       

                        }
                           
                            }
                           
                        }

                        // END OF PRIORS FOR  QUARTER 3

                        // START OF PRIOR FOR QUARTER 2
                        if (quarter.equals("Q2")) {
  
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorachieved  where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {

                                 priorAchieved =conn.rs1.getInt(1); 
                                if(percentages==0){
                         rwp.createCell(7).setCellValue(priorAchieved);
                       

                        }
                             if(percentages==1){
                          rwp.createCell(7).setCellValue(priorAchieved+"%");
                    

                        }
//                            rwp.createCell(7).setCellValue(conn.rs1.getInt(1));
                           
}

                        }
                        
                 // end of 
                        
                               
                        /// FOR PRIOR TARGET
                        
                           
                        if (quarter.equals("Q1")) {
                          
                            int f_year = Integer.parseInt(year);
             

                            String result_selector = "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1' AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs2 = conn.state2.executeQuery(result_selector);
                            while (conn.rs2.next()) {
                           priorTarget=conn.rs2.getInt(1);
//                            rwp.createCell(15).setCellValue(conn.rs2.getInt(1));
                                if(percentages==0){
                          rwp.createCell(15).setCellValue(priorTarget);
                    
                        }
                             if(percentages==1){
                        rwp.createCell(15).setCellValue(priorTarget+"%");
                     
                        }
                           
                             }
                           
                        }
                        if (quarter.equals("Q4")) {
                            int data=0;
                            // data for quarter one same year
                            int f_year = Integer.parseInt(year);
                            int p_year=f_year-1;
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where (reportingPeriod='Q3' OR reportingPeriod='Q2') AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                              String results= "select SUM(totalAchieved) from indicatorachievedcombined where reportingPeriod='Q1'  AND financialYear ='" + p_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            //String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            conn.rs6 = conn.state6.executeQuery(results);
                            while(conn.rs6.next()){
                            data = conn.rs6.getInt(1);
                            }
                            while (conn.rs1.next()) {
                                  priorTarget=conn.rs1.getInt(1);
                                //  rwp.createCell(15).setCellValue(conn.rs1.getInt(1)+data);
                             if(percentages==0){
                         rwp.createCell(15).setCellValue(priorTarget+data);
                     

                        }
                             if(percentages==1){
                       rwp.createCell(15).setCellValue(priorTarget+data+"%");
                      

                        }
                           

                            }
                        }
                        
                         



                        //START OF QUARTER 3
                        if (quarter.equals("Q3")) {
                          
                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where (reportingPeriod='Q2' OR reportingPeriod='Q1') AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                            // String result_selector2="select * from indicatorresults where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
                             priorTarget=conn.rs1.getInt(1);
                            //rwp.createCell(15).setCellValue(conn.rs1.getInt(1));
                             if(percentages==0){
                         rwp.createCell(15).setCellValue(priorTarget);
                    

                        }
                             if(percentages==1){
                        rwp.createCell(15).setCellValue(priorTarget+"%");
                      

                        }
                           
                            }
                        }

                        // END OF PRIORS FOR  QUARTER 3
System.out.println(quarter);
//priorTarget=0;
//priorsTarget=0;
                        // START OF PRIOR FOR QUARTER 2
                        if (quarter.equals("Q2")) {

                            int f_year = Integer.parseInt(year);
                            String result_selector2 = "select SUM(totalAchieved) from indicatorachievedcombined where (reportingPeriod='Q1' OR reportingPeriod='Q2') AND financialYear ='" + f_year + "' AND titleID='" + indicators + "' AND District='" + dists + "' AND (county='"+countyname.get(w)+"' || county='"+countyid.get(w)+"')";
                           
                            System.out.println(result_selector2);
                                    
                            // String result_selector2="select * from indicatorachieved  where titleID='"+indicators+"' && financialYear='"+year+"' && reportingPeriod='"+p_quarter+"'";
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            conn.rs1 = conn.state1.executeQuery(result_selector2);
                            while (conn.rs1.next()) {
priorTarget=conn.rs1.getInt(1);
                          //  rwp.createCell(15).setCellValue(conn.rs1.getInt(1));
                             System.out.println("priors"+priorTarget);
                         if(percentages==0){
                        rwp.createCell(15).setCellValue(priorTarget);
                

                        }
                             if(percentages==1){
                         rwp.createCell(15).setCellValue(priorTarget+"%");
                    


                        }
                                              }

                        }
                        
                        
                        
                        
                        
                          if(percentages==0){
                         rwp.createCell(11).setCellValue(totalAchieved);
                         
      }
                          if(percentages==1){
                         rwp.createCell(11).setCellValue(totalAchieved+"%");  
                          
     
                                      }
                     cell_12.setCellStyle(stylez);
                   totalAchievedments+=totalAchieved;
                        
                
                      TotalpriorTarget+=priorTarget; 
                     
                        
                    Total_baseline+=baseline_combined;
                    Totalprior_Achieved+=priorAchieved;
                    
                  
                Total_Quarter+=totalquarter;
                    TotalYearTarget+=Yrtarget_combined;
                  TotalQtrTarget+=Qtrtarget_combined;
                   totl =TotalQtrTarget+Qtrtarget_combined;
                   
                   TotalCombinedTarget+=target_combined;
                    
                       System.out.println("Total_Quarter +++"+(Total_Quarter) +"++++"+totalquarter);
                         }
                    }
                //end of for loop
                  
                     System.out.println("_________________________________________");
                     System.out.println("indicator"+indicators);
                     System.out.println("QUARTER TARGETS TOTAL"+totl);
                     System.out.println("QUARTER TARGETS TOTAL   ___"+(TotalQtrTarget));
                     System.out.println("QUARTER TARGETS TOTAL   ___"+(Total_Quarter));
                     System.out.println("QUARTER TARGETS TOTAL   ___"+(totalquarter));
                       System.out.println("_________________________________________");
                   HSSFCell cel1,cel2,cel3,cel4,cel5,cel6,cel7,cel8,cel9,cel10,cel11,cel12,cel13,cel14,cel15,cel16;  
                   HSSFCell cel1_3,cel2_3,cel3_3,cel4_3,cel5_3,cel6_3,cel7_3,cel8_3,cel9_3,cel10_3,cel11_3,cel12_3,cel13_3,cel14_3,cel15_3,cel16_3;  
           
                   a+=1;
              
   HSSFRow rwp1 = shet1.createRow(a);
    System.out.println("checkpercent2   "+indicatortitle+"_______"+percentages);
                    for (int y = 2; y <= 3; ++y) {
//    Cell cell = row.createCell(i);
                        cell_5 = rwp1.createCell(y);
                        cell_5.setCellStyle( stylex);
                       
                        if (y == 2) {
                            cell_5.setCellValue("Totalxxxx");
                          
                        }
                    }
//                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 3));
                  cel1 = rwp1.createCell(11);
                      cel1.setCellStyle( stylex);
                   
                      cel2= rwp1.createCell(12);
                      cel2.setCellStyle( stylex);
                       
                       if (percentages==0){
                          cel1.setCellValue("7&8  "+totalAchievedments);
                         }
                        if (percentages==1){
                            if(countDist1!=0){
                            cel1.setCellValue(totalAchievedments/countDist1+"%");
                           }
                        }
//                         shet1.addMergedRegion(new CellRangeAddress(a, a, 11, 12));  
                       cel3 = rwp1.createCell(13);
                      cel3.setCellStyle( stylex);
                      cel4 = rwp1.createCell(14);
                      cel4.setCellStyle( stylex);
                       if (percentages==0){
                      cel3.setCellValue("13&&14 "+TotalYearTarget);
                  }
                         if (percentages==1){
                            if(countDist1!=0){
                       cel3.setCellValue(TotalYearTarget/countDist1+"%");
                      }}
                            shet1.addMergedRegion(new CellRangeAddress(a, a, 13, 14)); 
                       
                      
                      cel5 = rwp1.createCell(3);
                      cel5.setCellStyle( stylex);
                      cel6 = rwp1.createCell(4);
                      cel6.setCellStyle( stylex);
                      
                    
                    
                      if (percentages==0){
                          
                      cel5.setCellValue("3&4 "+Total_baseline);
                
                      }
                      
                       if (percentages==1){
                            if(countDist1!=0){
                      cel5.setCellValue(Total_baseline/countDist1+"%");
                    
                    }}
                       shet1.addMergedRegion(new CellRangeAddress(a, a,3, 4)); 
                     
                      
                      
                        cel7 = rwp1.createCell(9);
                      cel7.setCellStyle( stylex);
                        cel8 = rwp1.createCell(10);
                      cel8.setCellStyle( stylex);
                      
                    
                    
                      if (percentages==0){
                      cel7.setCellValue("9&10"+TotalQtrTarget);
                   
                      }
                       if (percentages==1){
                            if(countDist1!=0){
                                cel7.setCellValue(TotalQtrTarget/countDist1+"%");
                            
                           
                            }}
                        shet1.addMergedRegion(new CellRangeAddress(a, a,9, 10));
                 
                      cel10 = rwp1.createCell(18);
                      cel10.setCellStyle( stylex);
                      cel11 = rwp1.createCell(17);
                      cel11.setCellStyle( stylex);
                       
                       if (percentages==0){
                      cel10.setCellValue(TotalCombinedTarget);
                    
                     }
                         if (percentages==1){
                            if(countDist1!=0){
                     
                      cel10.setCellValue(TotalCombinedTarget/countDist1+"%");
                     shet1.addMergedRegion(new CellRangeAddress(a, a,17, 18));
                    
                     System.out.println("bbb"+percentages); 
                      
                       if (percentages==0){
                           System.out.println("bbb entered");
                             cel11 = rwp1.createCell(5);
                      cel11.setCellStyle( stylex);
                       cel12 = rwp1.createCell(6);
                      cel12.setCellStyle( stylex);
                      cel11.setCellValue(Total_Quarter);
                   
                     }
                       if (percentages==1){
                             cel11 = rwp1.createCell(5);
                      cel11.setCellStyle( stylex);
                       cel12 = rwp1.createCell(6);
                      cel12.setCellStyle( stylex);
                            if(countDist1!=0){ 
                               
                        cel11.setCellValue(Total_Quarter/countDist1+"%");
                    
                      }}
//                        shet1.addMergedRegion(new CellRangeAddress(a, a,5, 6));      
                    
                             
                      
                      cel13 = rwp1.createCell(7);
                      cel13.setCellStyle( stylex);
                      cel14 = rwp1.createCell(8);
                      cel14.setCellStyle( stylex);
                      
                    
                     
                       if (percentages==0){
                      cel13.setCellValue("7&8"+Totalprior_Achieved);
                   
                      }
                         if (percentages==1){
                            if(countDist1!=0){
                           cel13.setCellValue(Totalprior_Achieved/countDist1+"%");
                               
                      }}
                           
          shet1.addMergedRegion(new CellRangeAddress(a, a,7, 8));    
         cel15 = rwp1.createCell(15);
                      cel15.setCellStyle(stylex);
                      cel16 = rwp1.createCell(16);
                      cel16.setCellStyle(stylex);
                      
                     
                    
                        if (percentages==0){
                      cel15.setCellValue("15&16"+TotalpriorTarget);
                  
                     }
                          if (percentages==1){
                            if(countDist1!=0){
                          cel15.setCellValue(TotalpriorTarget/countDist1+"%");
                        
                      }}
                 
  shet1.addMergedRegion(new CellRangeAddress(a, a,15, 16));   
 
                         
                    a +=1;
                   
//                    HSSFRow rwp2 = shet1.createRow(a);
//               
//                    for (int y = 2; y <= 18; ++y) {
////    Cell cell = row.createCell(i);
//                        cell_5 = rwp2.createCell(y);
//                        cell_5.setCellStyle(styletop);
//                       
//                        if (y == 2) {
//                            cell_5.setCellValue("");
//                           
//                        }
//                    }
//                    shet1.addMergedRegion(new CellRangeAddress(a, a, 2, 18));
               
                   
                    a += 5;
              
totalAchieved=0;
 totalquarter=0;totalquarter = quarter1= quarter2= quarter3 = quarter4=0;
TotalpriorTarget=0; priorTarget=0;  Total_baseline=0;
                     Totalprior_Achieved=0;
                   Total_Quarter=0;
                     TotalYearTarget=0;
                   TotalQtrTarget=0;
                   TotalCombinedTarget=0;
                   totalAchievedments=0;
     priorTarget=0;
priorsTarget=0;

     Qtrbaseline_combined=0; Yrtarget_combined=0;priorAchieved = 0;
     priorTarget = 0;
     target_combined=0;
     baseline_combined=0; 
     countersfor=0;
     totalquarter=0;
    
     quartertotal=0;
     totalquarter=0;
             
    
      quarter1=0 ;
      quarter2=0 ;
      quarter3=0 ; 
      quarter4=0;
      quarter_1 =0;
   
          Qtrtarget_combined=0;  
             TotalQtrTarget=0;
//     Qtrtarget_combined=0;
     totl=0; 
     TotalTargeted_W=0;
     TotalTargeted_M=0;
                
            }}}}}
           
           //System.out.println("Counter==============================" + counter);
                 ///get a drive to store reports
         URL location = comprehensive_per_county.class.getProtectionDomain().getCodeSource().getLocation();
             String  mydrive = location.getFile().substring(1, 2);
         
            path=mydrive+":\\PPT_UPLOADS\\REPORTS\\QUARTERLYREPORTS";
             session.setAttribute("saved_path", "<font color=\"grey\">"+path+"</font><br/> ");
 
//     new File(path2).mkdirs();
         new File(path).mkdirs();
       FileOutputStream fileOut = new FileOutputStream(path+"/Quarterly_Report"+current_date+".xls");

            wb.write(fileOut);
            fileOut.close();
            year = "";
 
     ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename="+period+"_"+Display_year+"_"+"District_Report_Created_On_"+current_date+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
                
        } finally {
//            response.sendRedirect("1stQuarterCountyReport.jsp");
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
            Logger.getLogger(Quarter1CountyReport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Quarter1CountyReport.class.getName()).log(Level.SEVERE, null, ex);
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
