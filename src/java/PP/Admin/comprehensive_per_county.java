/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

/**
 *
 * @author Geofrey Nyabuto
 */
public class comprehensive_per_county extends HttpServlet {
HttpSession session;
String quarter,activity_date;
ArrayList counties= new ArrayList();
String county_name;
int county_id;
String activities,start_date,end_date;
int total_activities_achieved,overall_activities;
int achieved_prior,achieved_this_period,achieved_this_year;
int achieved_prior_total,achieved_this_period_total,achieved_this_year_total;
int this_year_target, project_target;
int this_year_target_total, project_target_total;
int min_year_combined,min_year;
int max_year_combined,max_year,previous_quarter_achieved_total;
int previous_quarter,previous_quarter_achieved,year;
int table_id;
int previous_quarter_achieved_total_m,previous_quarter_achieved_total_f;
int previous_quarter_achieved_m,previous_quarter_achieved_f;
int achieved_prior_m,achieved_this_period_m,achieved_this_year_m;
int achieved_prior_total_m,achieved_this_period_total_m,achieved_this_year_total_m;
int this_year_target_m, project_target_m;
int this_year_target_total_m, project_target_total_m;
int achieved_prior_f,achieved_this_period_f,achieved_this_year_f;
int achieved_prior_total_f,achieved_this_period_total_f,achieved_this_year_total_f;
int this_year_target_f, project_target_f;
int this_year_target_total_f, project_target_total_f;
String period="",previous_period="";
int Display_year=0,Display_year_previous=0;
int percentages=0,quartely_targets=0,total_quartely_targets;
int quarterly_target_men,quarterly_target_women,total_quarterly_target_men,total_quarterly_target_women;
String current_time;
int district_counter,overall_districts,district_counter_yr,overall_districts_yr;
int district_counter_tar,overall_district_counter_tar;
int achieved_this_periodAVG=0;
     int       quartely_targetsAVG=0;
         int   achieved_this_yearAVG=0;
ArrayList activity = new ArrayList();
ArrayList activity_value = new ArrayList();
ArrayList end_dt = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      
       
            session=request.getSession();
        int activity_checker=0;
        dbConnect conn= new dbConnect();
        counties.clear();
        activity.clear();
        activity_value.clear();
        end_dt.clear();
        
        Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);


current_time=year+"_"+month+"_"+date;
  district_counter=0;      
        
        int aa=0;
        
        
        county_name="";
        county_id=0;
        total_activities_achieved=0;
        activities=start_date=end_date="";
        overall_activities=0;
achieved_prior=achieved_this_period=achieved_this_year=0;
achieved_prior_total=achieved_this_period_total=achieved_this_year_total=0;
this_year_target=project_target=0;
this_year_target_total= project_target_total=0;
min_year_combined=min_year=2010;
max_year_combined=max_year=2015;
previous_quarter_achieved_total=previous_quarter_achieved=previous_quarter=0;

previous_quarter_achieved_total_m=previous_quarter_achieved_total_f=0;
previous_quarter_achieved_m=previous_quarter_achieved_f=0;
achieved_prior_m=achieved_this_period_m=achieved_this_year_m=0;
achieved_prior_total_m=achieved_this_period_total_m=achieved_this_year_total_m=0;
this_year_target_m=project_target_m=0;
this_year_target_total_m= project_target_total_m=0;
achieved_prior_f=achieved_this_period_f=achieved_this_year_f=0;
achieved_prior_total_f=achieved_this_period_total_f=achieved_this_year_total_f=0;
this_year_target_f=project_target_f=0;
this_year_target_total_f= project_target_total_f=0;
percentages=quartely_targets=total_quartely_targets=0;
quarterly_target_men=quarterly_target_women=total_quarterly_target_men=total_quarterly_target_women=0;
//RECEIVE THE PARAMETERS HERE....................
quarter="";
    if (request.getParameter("Quarter") != null && !request.getParameter("Quarter").equals("")) {
                quarter = request.getParameter("Quarter");
            }
    activity_date="";
    String financials="";
     if (request.getParameter("FinancialYear") != null && !request.getParameter("FinancialYear").equals("")) {
                financials = request.getParameter("FinancialYear");
            }
    year=Integer.parseInt(financials);
    table_id=0;
//    GET THE QUARTERS FOR DISPLAY
    System.out.println("quarter  "+quarter);
    System.out.println("quarter"+financials);
    if(quarter.equals("Q1")){
      period="Oct-Dec"; 
      Display_year=year-1;
      previous_period="July-Sep";
      Display_year_previous=year-1;
    }
    if(quarter.equals("Q2")){
      period="Jan-March"; 
      Display_year=year;
      previous_period="Oct-Dec";
     Display_year_previous=year-1; 
    }
    if(quarter.equals("Q3")){
      period="April-June";
      Display_year=year;
      previous_period="Jan-March";
      Display_year_previous=year;
    }
    if(quarter.equals("Q4")){
      period="July-Sep";
      Display_year=year;
      previous_period="April-June";
      Display_year_previous=year;
    }
    try{
   
        //            ****************HEADING IN EXCEL**********************************************************
               HSSFWorkbook wb=new HSSFWorkbook();
               HSSFSheet shet1=wb.createSheet();
               
               
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
    
               HSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)12);
    font.setFontName("Cambria");
    font.setItalic(true);
    font.setBoldweight((short)02);
    font.setColor(HSSFColor.BLACK.index);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setWrapText(true);
    style.setFillForegroundColor(HSSFColor.GOLD.index);
style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

 style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

       HSSFFont font_indicator=wb.createFont();
    font_indicator.setFontHeightInPoints((short)15);
    font_indicator.setFontName("Cambria");
    font_indicator.setItalic(true);
    font_indicator.setBoldweight((short)02);
    font_indicator.setColor(HSSFColor.WHITE.index);
    CellStyle style_indicator=wb.createCellStyle();
    style_indicator.setFont(font_indicator);
    style_indicator.setWrapText(true);
    style_indicator.setFillForegroundColor(HSSFColor.PLUM.index);
style_indicator.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

 style_indicator.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style_indicator.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style_indicator.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style_indicator.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      
      
//%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 HSSFFont font_header=wb.createFont();
    font_header.setFontHeightInPoints((short)10);
    font_header.setFontName("Arial Black");
    font_header.setItalic(true);
    font_header.setBoldweight((short)05);
    font_header.setColor(HSSFColor.BLACK.index);
    CellStyle style_header=wb.createCellStyle();
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
            
            
            HSSFCellStyle styles = wb.createCellStyle();
            styles.setFillForegroundColor(HSSFColor.WHITE.index);
            styles.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFFont fonts = wb.createFont();
            fonts.setColor(HSSFColor.BLACK.index);
            styles.setFont(fonts);
            styles.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styles.setBorderTop(HSSFCellStyle.BORDER_THIN);
             //stylex.setAlignment(CellStyle.ALIGN_CENTER);
            styles.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styles.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            
            HSSFCellStyle stylesright = wb.createCellStyle();
            stylesright.setFillForegroundColor(HSSFColor.WHITE.index);
            stylesright.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

          
          
            stylesright.setBorderRight(HSSFCellStyle.BORDER_THIN);
          
               
HSSFCellStyle styley = wb.createCellStyle();
HSSFFont fonty = wb.createFont();
 styley.setFont(fonty);

 styley.setBorderLeft(HSSFCellStyle.BORDER_THIN);
HSSFCellStyle stylez = wb.createCellStyle();
HSSFFont fontz= wb.createFont();
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
                HSSFFont fontb = wb.createFont();
                 font_header.setBoldweight((short)05);
                fontb.setColor(HSSFColor.BLACK.index);
                fontb.setFontHeightInPoints((short) 25);
            indicator_style.setFont(fonts);
            indicator_style.setBorderBottom(CellStyle.BORDER_THIN);
            indicator_style.setBorderLeft(CellStyle.BORDER_THIN);
            indicator_style.setBorderRight(CellStyle.BORDER_THIN);
            indicator_style.setBorderTop(CellStyle.BORDER_THIN);
            indicator_style.setAlignment(CellStyle.ALIGN_CENTER);


       //aa+=1;        
  HSSFRow rw_1=shet1.createRow(1);
    rw_1.setHeightInPoints(35);
    HSSFCell rw0cell0;
//=rw_1.createCell(2);
//   rw0cell0.setCellValue("PROGRAM PROGRESS COUNTY REPORT");  

 
  // rw0cell0.setCellStyle(borderStyle);
   
   for (int y = 2; y <= 18; y++) {
//    Cell cell = row.createCell(i);
                        rw0cell0=rw_1.createCell(y);

                       rw0cell0.setCellStyle(borderStyle);
                        if (y == 2) {
                               rw0cell0.setCellValue("PROGRAM PROGRESS COUNTY REPORT"); 
                        }
                    }
   shet1.addMergedRegion(new CellRangeAddress(1,1,2,18));
   aa+=1;
   
        String county_selector="SELECT * FROM county";
      conn.rs=conn.state.executeQuery(county_selector);
      while(conn.rs.next()){
         counties.add(conn.rs.getString(2)); 
      }
     

//      SPLIT TO GET THE QUARTER VALUE
       String [] splted_quarter=quarter.split("");
         
          
//           aa=1;       
//          aa+=3;       
  
    String indicator_selector="SELECT * FROM indicatortitles";
    conn.rs=conn.state.executeQuery(indicator_selector);
    while(conn.rs.next()){
        quartely_targets=total_quartely_targets=overall_districts=overall_districts_yr=overall_district_counter_tar=0;
        quarterly_target_men=quarterly_target_women=total_quarterly_target_men=total_quarterly_target_women=0;
        String indicator_id=conn.rs.getString(1) ;
      String indicator_title=conn.rs.getString(3);
      String indicator_number=conn.rs.getString(4);
      table_id=conn.rs.getInt("tableIdentifier");
      percentages=conn.rs.getInt("percentage");
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,0,17)); 
//       HSSFRow rw1=shet1.createRow(aa);
//        rw1.setHeightInPoints(35);
//       HSSFCell rw1cell0=rw1.createCell(0);
//       rw1cell0.setCellValue(" INDICATOR TITLE :       "+indicator_title);
//       rw1cell0.setCellStyle(style_indicator);
//       
//       aa+=1;
//      HSSFRow rw2=shet1.createRow(aa);
//      rw2.setHeightInPoints(20);
//       HSSFCell rw2cell0=rw2.createCell(0);
//      rw2cell0.setCellValue("INDICATOR NUMBER  : "+indicator_number); 
//            shet1.addMergedRegion(new CellRangeAddress(aa,aa,0,5)); 
//             rw2cell0.setCellStyle(style_header);
//             
//      System.out.println(" ID IS : "+indicator_id+" INDICATOR TITLE  :  "+indicator_title);
//      aa+=1;
//      HSSFRow rw3=shet1.createRow(aa);
//      HSSFCell rw3cell0=rw3.createCell(1);
//      rw3cell0.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,8));
//       rw3cell0.setCellStyle(style);
//       aa+=1; 
//       HSSFRow rw4=shet1.createRow(aa);
//      HSSFCell rw4cell1=rw4.createCell(1);
//      HSSFCell rw4cell2=rw4.createCell(3);
//      HSSFCell rw4cell3=rw4.createCell(12);
//      HSSFCell rw4cell4=rw4.createCell(14);
//      HSSFCell rw4cell5=rw4.createCell(16);
////      HSSFCell rw4cell6=rw4.createCell(11);
//      rw4cell1.setCellValue("Geographical Location");
//      rw4cell2.setCellValue("Activity Title");        
//      rw4cell3.setCellValue("Date");
//      rw4cell4.setCellValue("Number");        
//      rw4cell5.setCellValue("Sub-Total");
//           
//     shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,2));
//    shet1.addMergedRegion(new CellRangeAddress(aa,aa,3,11));
//    shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
//    shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
//    shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
      
     aa+=1;
      
       HSSFRow row1 = shet1.createRow(aa);
                    HSSFCell cell0;
                    //cell0=rw0.createCell(2);
                    //cell0.setCellValue("Program Progress Table");
                    for (int y = 2; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                        cell0 = row1.createCell(y);
                        cell0.setCellStyle(indicator_style);
                        if (y == 2) {
                            cell0.setCellValue("Program Progress Table");
                        }
                    }

                    // cell0.setCellStyle(style_header);
                    row1.setHeightInPoints(25);

//  Merge the cells
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 18));


                    HSSFRow rw1 = shet1.createRow(aa + 2);
                    HSSFCell cell_0;
//   cell=rw1.createCell(2);
//   cell.setCellValue("Table   :"+indicators);

                    for (int y = 2; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                        cell_0 = rw1.createCell(y);

                        cell_0.setCellStyle(style_header);
                        if (y == 2) {
                            cell_0.setCellValue("Table   :" + indicator_id + " " + "ACTIVITIES");
                        }
                    }




//    rw1.setHeightInPoints(12);
//  Merge the cells
                    aa += 2;
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2,18));

                     HSSFRow rw2 = shet1.createRow(aa + 2);
                    HSSFCell cell2;
//   cell2=rw2.createCell(2);
//  cell2.setCellStyle(style_header);
                     rw2.setHeightInPoints(30);

//   cell2.setCellValue("indicator Number: "+indicatortitle);

                    for (int y = 2; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                        cell2 = rw2.createCell(y);

                        cell2.setCellStyle(style_header);
                        if (y == 2) {
                            cell2.setCellValue("Indicator Title: " + indicator_title);
                        }
                    }




//  Merge the cells
                    aa += 2;
                    //shet1.addMergedRegion(new CellRangeAddress(2,4,4,12)); 
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 18));

                    HSSFRow rw3 = shet1.createRow(aa + 1);
                    HSSFCell cell3;
//   cell3=rw3.createCell(4);
//   cell3.setCellStyle(style);
//    rw3.setHeightInPoints(12);
//   cell3.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
                    for (int y = 2; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                        cell3 = rw3.createCell(y);

                        cell3.setCellStyle(style_header);
                        if (y == 2) {
                            cell3.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
                        }
                    }





//  Merge the cells
                    aa += 1;
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 18));

                    HSSFRow rw4 = shet1.createRow(aa + 1);
                    HSSFCell cells1, cells2, cells3, cells4, cells5, cells6, cells7, cells8, cells9, cells10;
//                    cells1 = rw4.createCell(2);
//                    cells1.setCellValue("County");
//                    cells1.setCellStyle(stylex);
                    for (int y = 2; y <= 3; ++y) {
//    Cell cell = row.createCell(i);
                        cells7 = rw4.createCell(y);
                          cells7.setCellStyle(stylex);
                        if (y == 2) {
                               cells7.setCellValue("County");
                        }
                    }

                for (int y = 4; y <=10; ++y) {
//    Cell cell = row.createCell(i);
                        cells6 = rw4.createCell(y);
                        cells6.setCellStyle(stylex);
                        if (y ==4) {
                        cells6.setCellValue("Activity Title");
                        }
                    }
               
                      for (int y = 11; y <= 13;++y) {
//    Cell cell = row.createCell(i);
                      cells4 = rw4.createCell(y);

                        cells4.setCellStyle(stylex);
                        if (y == 11) {
                             cells4.setCellValue("Date");
                        }
                    }
                    
                   
                    


//                    cells8 = rw4.createCell(18);
//                    cells8.setCellValue("sub total");
//                    cells8.setCellStyle(stylex);
                    
                  for (int y = 14; y <= 16; ++y) {
//    Cell cell = row.createCell(i);
                        cells8 = rw4.createCell(y);

                       cells8.setCellStyle(stylex);
                        if (y == 14) {
                                cells8.setCellValue("Achieved");
                        }
                    }
                  
               
 
                  for (int y = 17; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                        cells8 = rw4.createCell(y);

                       cells8.setCellStyle(stylex);
                        if (y == 17) {
                                cells8.setCellValue("sub total");
                        }
                    }
 



                    aa += 1;
    
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 3));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 4, 10));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 11, 13));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 14, 16));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
      
     for (int i=0;i<counties.size();i++){
         int merger=0;
         
         district_counter=0;
         activity_checker=0;
     county_name=counties.get(i).toString();
     county_id=0;
     total_activities_achieved=0;
     county_id=i+1;
activities="";
if(table_id==2){
    
//    String district_count="SELECT count(activityID) FROM indicatoractivities1 WHERE "
//             + "countyID='"+county_id+"' && FinancialYear='"+year+"' && Quarter='"+quarter+"'&& titleID='"+indicator_id+"'";
//     conn.rs1=conn.state1.executeQuery(district_count);
//    if(conn.rs1.next()==true){
//     district_counter=conn.rs1.getInt(1);
//     overall_districts+=district_counter;
//    }
    
     String activities_selector2="SELECT indicatoractivities1.total,indicatoractivity.Activity,indicatoractivities1.endDate,indicatoractivities1.activityOthers FROM indicatoractivities1 JOIN indicatoractivity ON indicatoractivities1.activityTitle=indicatoractivity.ActivityID WHERE "
             + "indicatoractivities1.countyID='"+county_id+"' && indicatoractivities1.FinancialYear='"+year+"' && indicatoractivities1.Quarter='"+quarter+"'&& indicatoractivities1.titleID='"+indicator_id+"'";
     conn.rs1=conn.state1.executeQuery(activities_selector2);
     while(conn.rs1.next()){
         int found=0,found1=0;
        String current_activity=conn.rs1.getString(2);
        int activity_achieved=conn.rs1.getInt(1);
        String det=conn.rs1.getString(3);
    if(current_activity.equals("Others")){
         current_activity=conn.rs1.getString(4);
     }  
     if(activity.size()>0){
      for (int a=0;a<activity.size();a++){
          if(current_activity!=null){
         if(current_activity.equalsIgnoreCase(activity.get(a).toString())){
           found=a;
           found1=1;
          }}
//         break;
       }
     }
      if(found1==1){
          int total_achieved=Integer.parseInt(activity_value.get(found).toString())+activity_achieved;
        activity_value.set(found, total_achieved);
      }
     if(found1==0 && current_activity!=null){
          activity.add(current_activity);
          activity_value.add(activity_achieved);
          end_dt.add(det);
      }
      
} 
}
if(table_id==1){

     String activities_selector="SELECT indicatoractivities.subtotals,indicatoractivity.Activity,indicatoractivities.endDate,indicatoractivities.activityOthers FROM indicatoractivities JOIN indicatoractivity ON indicatoractivities.activityTitle=indicatoractivity.ActivityID WHERE "
             + "indicatoractivities.countyID='"+county_id+"' && indicatoractivities.FinancialYear='"+year+"' && indicatoractivities.Quarter='"+quarter+"'&& indicatoractivities.titleID='"+indicator_id+"'";
     conn.rs1=conn.state1.executeQuery(activities_selector);
     while(conn.rs1.next()){
                int found=0,found1=0;
        String current_activity=conn.rs1.getString(2);
        int activity_achieved=conn.rs1.getInt(1);
        String det=conn.rs1.getString(3);
     if(current_activity.equals("Others")){
         current_activity=conn.rs1.getString(4);
     }  
     if(activity.size()>0){
      for (int a=0;a<activity.size();a++){
         if(current_activity.equalsIgnoreCase(activity.get(a).toString())){
           found=a;
           found1=1;
          }
//         break;
       }
     }
      if(found1==1){
          int total_achieved=Integer.parseInt(activity_value.get(found).toString())+activity_achieved;
        activity_value.set(found, total_achieved);
      }
      if(found1==0){
          activity.add(current_activity);
          activity_value.add(activity_achieved);
           end_dt.add(det);
      }
     
     
     }

     
}
    
    overall_activities+=total_activities_achieved; 
//    if(percentages==1) {
//    total_activities_achieved=0;   
//   }
    for(int j=0;j<activity.size();j++) {
         aa+=1; 
  //System.out.println("The Indicator  is  : "+indicator_title +"     The County is  : "+county_name+"       activity is "+activity.get(j).toString()+"    and the achieved is   :  "+activity_value.get(j).toString());   
  overall_activities+=Integer.parseInt(activity_value.get(j).toString());
       HSSFRow rw5=shet1.createRow(aa);
       rw5.setHeightInPoints(20);
      HSSFCell rw5cell0=rw5.createCell(1);
      rw5cell0.setCellValue("");
      rw5cell0.setCellStyle(stylesright);
      HSSFCell rw5cell1;
      //=rw5.createCell(2);
      HSSFCell rw5cell2;
      //=rw5.createCell(4);
      HSSFCell rw5cell3;//=rw5.createCell(11);
      HSSFCell rw5cell4;//=rw5.createCell(14);
      HSSFCell rw5cell5;//=rw5.createCell(17);
//      HSSFCell rw4cell6=rw4.createCell(11);
      if(merger==0){
//      rw5cell1.setCellValue(county_name);
      merger=1;
     // rw5cell1.setCellStyle(styles);
//      if(activity.size()>1){
//        
//        
//        
//        for (int y = aa; y <= aa-(activity.size()-1); ++y) {
////    Cell cell = row.createCell(i);
//                       rw5cell1=rw5.createCell(y);
//
//                       rw5cell1.setCellStyle(styles);
//                        if (y == 2) {
//                                rw5cell1.setCellValue(county_name);
      
        for (int y = 2; y <= 3; ++y) {
//    Cell cell = row.createCell(i);
                       rw5cell1=rw5.createCell(y);

                       rw5cell1.setCellStyle(styles);
                      
                    
                        if (y == 2) {
                                rw5cell1.setCellValue(county_name);
                        }
                    }
       
      
      
      }
      
      for (int y = 4; y <= 10; ++y) {
//    Cell cell = row.createCell(i);
                       rw5cell2=rw5.createCell(y);

                       rw5cell2.setCellStyle(styles);
                        if (y == 4) {
                                rw5cell2.setCellValue(activity.get(j).toString());
                        }
                    }
      
     // rw5cell2.setCellValue(activity.get(j).toString());
      
         for (int y = 11; y <= 13; ++y) {
//    Cell cell = row.createCell(i);
                       rw5cell3=rw5.createCell(y);

                       rw5cell3.setCellStyle(styles);
                        if (y == 11) {
                                 rw5cell3.setCellValue(end_dt.get(j).toString());
                        }
                    }
     // rw5cell3.setCellValue(end_dt.get(j).toString());

         
            for (int y = 14; y <= 16; ++y) {
//    Cell cell = row.createCell(i);
                       rw5cell4=rw5.createCell(y);

                       rw5cell4.setCellStyle(styles);
                        if (y == 14) {
                    rw5cell4.setCellValue(Integer.parseInt(activity_value.get(j).toString()));
                        }
                    }
     // rw5cell4.setCellValue(Integer.parseInt(activity_value.get(j).toString()));
            
                for (int y = 17; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                       rw5cell5=rw5.createCell(y);

                       rw5cell5.setCellStyle(styles);
                        if (y == 17) {
                rw5cell5.setCellValue(Integer.parseInt(activity_value.get(j).toString())); 
                        }
                    }
//                    HSSFCell  cell;
//                  for(int m=aa; m<=(aa-(activity.size()-1));m++){
//                            cell=rw5.setRowStyle(m);
//                              cell.setCellStyle(styles);
//                               cell.setCellValue("");
//                       }
      //rw5cell5.setCellValue(Integer.parseInt(activity_value.get(j).toString())); 

//                  for (int y = 17; y <= 18; ++y) {
////    Cell cell = row.createCell(i);
//                       rw5cell=rw5.createCell(y);
//
//                       rw5cell5.setCellStyle(styles);
//                        if (y == 14) {
//                rw5cell5.setCellValue(Integer.parseInt(activity_value.get(j).toString())); 
//                        }
//                    }
                
                
    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 3));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 4, 10));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 11, 13));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 14, 16));
 shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
// shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
 }

    if(activity.size()>1){
        
      
 shet1.addMergedRegion(new CellRangeAddress(aa-(activity.size()-1), aa, 2, 3));
    
    
    }
  activity.clear();
 activity_value.clear();
 end_dt.clear();
     }
     if(overall_activities>0){
      aa+=1; 
       HSSFRow rw5=shet1.createRow(aa);
       rw5.setHeightInPoints(25);
      HSSFCell rw5cell1;
      //=rw5.createCell(2);
      HSSFCell rw5cell2;
      //=rw5.createCell(15);
      HSSFCell rw5cell3;
      //=rw5.createCell(17);
      
      
      for (int y = 2; y <= 13; ++y) {
                        rw5cell1 = rw5.createCell(y);
                        rw5cell1.setCellStyle(stylex);
                        if (y == 2) {
                            rw5cell1.setCellValue("Total : ");
                        }
                    }
      for (int y = 14; y <= 16; ++y) {
                        rw5cell2 = rw5.createCell(y);
                        rw5cell2.setCellStyle(stylex);
                        if (y == 14) {
                           rw5cell2.setCellValue(overall_activities);
                        }
                    }
      for (int y = 17; y <= 18; ++y) {
                        rw5cell3 = rw5.createCell(y);
                        rw5cell3.setCellStyle(stylex);
                        if (y == 17) {
                           rw5cell3.setCellValue(overall_activities);
                        }
                    }
    
//      if(percentages==0){
    
    
//      }
//      if(percentages==1 && overall_districts>0){
//     rw5cell2.setCellValue((overall_activities/overall_districts)+"%");
//     rw5cell3.setCellValue((overall_activities/overall_districts)+"%");
//      }
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,13));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,16));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,17,18));
//     rw5cell1.setCellStyle(stylex);
//     rw5cell2.setCellStyle(stylex);
//     rw5cell3.setCellStyle(stylex);
     }
     overall_activities=overall_districts=0;
//     DISPLAY THE TOTALS FOR ALL THE RESULTS IN ALL THE COUNTIES..................
     // aa+=1; 
//       HSSFRow rw5=shet1.createRow(aa);
//       rw5.setHeightInPoints(30);
//      HSSFCell rw5cell1=rw5.createCell(1);
//     rw5cell1.setCellValue("RESULTS");
//     rw5cell1.setCellStyle(stylex);
//     shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,18));
 
      
 
   if(table_id==2) { 
        String get_min_year="SELECT MIN(financialYear) FROM indicatorachievedcombined";
      conn.rs4=conn.state4.executeQuery(get_min_year);
      if(conn.rs4.next()==true){
       min_year_combined=conn.rs4.getInt(1); 
       if(min_year_combined<2010){
         min_year_combined=2010;  
       }
      }
     aa+=1;
//  HSSFRow rw6=shet1.createRow(aa);
//       rw6.setHeightInPoints(43);
//      HSSFCell rw6cell1=rw6.createCell(1);
//      HSSFCell rw6cell2=rw6.createCell(2);
//      HSSFCell rw6cell3=rw6.createCell(4);
//      HSSFCell rw6cell4=rw6.createCell(6);
//      HSSFCell rw6cell5=rw6.createCell(8);
//      HSSFCell rw6cell7=rw6.createCell(12);
//      HSSFCell rw6cell9=rw6.createCell(16);
//     rw6cell1.setCellValue("County Name");
//     rw6cell2.setCellValue("Baseline");
//     rw6cell3.setCellValue("Achieved Prior Periods");
//     rw6cell4.setCellValue("Previous Quarter ("+previous_period+" "+Display_year_previous+" )");
//     rw6cell5.setCellValue("This Reporting Period ("+period+" "+Display_year+" )");
//     rw6cell7.setCellValue("PEPFAR Year ("+year+" )");
//     rw6cell9.setCellValue("End Of Project Target");
//     shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
//     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,11));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,15));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
//      
//      rw6cell1.setCellStyle(style_header);
//      rw6cell2.setCellStyle(style_header);
//      rw6cell3.setCellStyle(style_header);
//      rw6cell4.setCellStyle(style_header);
//      rw6cell5.setCellStyle(style_header);
//      rw6cell7.setCellStyle(style_header);
//      rw6cell9.setCellStyle(style_header);
//     aa+=1;
//  HSSFRow rw7=shet1.createRow(aa);
//       rw7.setHeightInPoints(20);
//      HSSFCell rw7cell1=rw7.createCell(1);
//      HSSFCell rw7cell2=rw7.createCell(2);
//      HSSFCell rw7cell3=rw7.createCell(4);
//      HSSFCell rw7cell4=rw7.createCell(6);
//      HSSFCell rw7cell6=rw7.createCell(10);
//      HSSFCell rw7cell8=rw7.createCell(14);
//      HSSFCell rw7cell5=rw7.createCell(8);
//      HSSFCell rw7cell7=rw7.createCell(12);
//      HSSFCell rw7cell9=rw7.createCell(16);
//     rw7cell1.setCellValue("");
//     rw7cell2.setCellValue("");
//     rw7cell3.setCellValue("Achieved");
//     rw7cell4.setCellValue("Achieved");
//     rw7cell5.setCellValue("Target");
//     rw7cell6.setCellValue("Achieved");
//     rw7cell7.setCellValue("Target");
//     rw7cell8.setCellValue("Achieved");
//     rw7cell9.setCellValue(""); 
//      rw7cell1.setCellStyle(style);
//      rw7cell2.setCellStyle(style);
//      rw7cell3.setCellStyle(style);
//      rw7cell4.setCellStyle(style);
//      rw7cell5.setCellStyle(style);
//      rw7cell6.setCellStyle(style);
//      rw7cell7.setCellStyle(style);
//      rw7cell8.setCellStyle(style);
//      rw7cell9.setCellStyle(style);
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
//     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,9));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,10,11));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
//      shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
     
     
     
     
     
     
                    HSSFRow rwk = shet1.createRow(aa);
                    HSSFCell cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, cell_10,cell_11,cell_12,cell_13,cell_14,cell_15,cell_16,cell_17,cell_18,cell_19,cells_11, cells_12, cells_13, cells_14, cells_15, cells_16, cells_17, cells_18, cells_19, cells_20, cells_21, cells_22;
                    cell_1 = rwk.createCell(2);
                    cell_1.setCellValue("RESULTS");
                    cell_1.setCellStyle(borderStyle);
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 18));
                    //int l=k+1;
                    aa += 1;
                    HSSFRow rwb = shet1.createRow(aa);
                    cell_2 = rwb.createCell(2);
                    cell_2.setCellValue("County");
                    cell_2.setCellStyle(style_header);
//   cell_3=rwl.createCell(3);
//   cell_3.setCellValue("Baseline");
//   cell_3.setCellStyle(style);
                    for (int y = 3; y <= 4; ++y) {
                        cell_3 = rwb.createCell(y);
                        cell_3.setCellStyle(style_header);
                        if (y == 3) {
                            cell_3.setCellValue("Baseline");
                        }
                    }
//        
//         cell_4=rwl.createCell(5);
//   cell_4.setCellValue("Results Achieved Prior Period");
//   cell_4.setCellStyle(style);
                    for (int y = 5; y <= 6; ++y) {
                        cell_4 = rwb.createCell(y);
                        cell_4.setCellStyle(style_header);
                        if (y == 5) {
                            cell_4.setCellValue("Achieved Prior Period");
                        }
                    }




//   cell_5= rwl.createCell(7);
//   cell_5.setCellValue("This Reporting Period");
//   cell_5.setCellStyle(style);

                    
//                    rw6cell4.setCellValue("Previous Quarter ("+previous_period+" "+Display_year_previous+" )");
//     rw6cell5.setCellValue("This Reporting Period ("+period+" "+Display_year+" )");
//     rw6cell7.setCellValue("PEPFAR Year ("+year+" )");
                    for (int y = 7; y <= 8; ++y) {
                        cell_5 = rwb.createCell(y);
                        cell_5.setCellStyle(style_header);
                        if (y == 7) {
                            cell_5.setCellValue("Prior Quarter  ("+previous_period+" "+Display_year_previous+" )");
                        }
                    }

//   cell_6 =rwl.createCell(11);
//   cell_6.setCellValue("Year: "+year+" target");
//   cell_6.setCellStyle(style_header);

                    for (int y = 9; y <= 12; ++y) {
                        cell_6 = rwb.createCell(y);
                        cell_6.setCellStyle(style_header);
                        if (y == 9) {
                            cell_6.setCellValue("This reporting period ("+period+" "+Display_year+" )");
                        }
                    }
                    for (int y = 13; y <= 16; ++y) {
                        cell_13 = rwb.createCell(y);
                        cell_13.setCellStyle(style_header);
                        if (y == 13) {
                            cell_13.setCellValue("Pepfar year ("+year+" )");
                        }
                    }
                    for (int y = 17; y <= 18; ++y) {
                        cell_14 = rwb.createCell(y);
                        cell_14.setCellStyle(style_header);
                        if (y == 17) {
                            cell_14.setCellValue("End of Project Target");
                        }
                    }
 // aa += 1;
                    shet1.addMergedRegion(new CellRangeAddress(aa,aa,3,4));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 9, 12));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 13, 16));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
  aa += 1;
                  

                    HSSFRow rwm = shet1.createRow(aa);
                    cell_5 = rwm.createCell(2);
                    cell_5.setCellValue("");
                    cell_5.setCellStyle(style);

                    cell_6 = rwm.createCell(3);
                    cell_6.setCellValue("");
                    cell_6.setCellStyle(style);

                    cells_21 = rwm.createCell(4);
                    cells_21.setCellValue("");
                    cells_21.setCellStyle(style);

                for (int y = 5; y <= 6; ++y) {
//    Cell cell = row.createCell(i);
                        cell_7 = rwm.createCell(y);

                       cell_7.setCellStyle(style);
                        if (y == 5) {
                             cell_7.setCellValue("Achieved");
                        }
                    }
                
                
                for (int y = 7; y <= 8; ++y) {
//    Cell cell = row.createCell(i);
                        cell_8 = rwm.createCell(y);

                        cell_8.setCellStyle(style);
                        if (y == 7) {
                              cell_8.setCellValue("Achieved");
                        }
                    }
                   
                   
                for (int y = 9; y <= 10; ++y) {
//    Cell cell = row.createCell(i);
                          cell_9 = rwm.createCell(y);

                         cell_9.setCellStyle(style);
                        if (y == 9) {
                              cell_9.setCellValue("Target");
                        }
                    }
                
                for (int y = 11; y <= 12; ++y) {
//    Cell cell = row.createCell(i);
                            cell_10 = rwm.createCell(y);

                          cell_10.setCellStyle(style);
                        if (y == 11) {
                           cell_10.setCellValue("Achieved");
                        }
                    }
                
                for (int y = 13; y <= 14; ++y) {
//    Cell cell = row.createCell(i);
                            cell_15 = rwm.createCell(y);

                         cell_15.setCellStyle(style);
                        if (y == 13) {
                         cell_15.setCellValue("Target");
                        }
                    }
                for (int y = 15; y <= 16; ++y) {
//    Cell cell = row.createCell(i);
                             cell_16 = rwm.createCell(y);

                           cell_16.setCellStyle(style);
                        if (y == 15) {
                        cell_16.setCellValue("Achieved");
                        }
                    }
               
                for (int y = 16; y <=17; ++y) {
//    Cell cell = row.createCell(i);
                              cell_17 = rwm.createCell(y);

                           cell_17.setCellStyle(style);
                        if (y == 17) {
                         cell_17.setCellValue("Target");
                        }
                    }
                for (int y = 18; y <= 18; ++y) {
//    Cell cell = row.createCell(i);
                              cell_18 = rwm.createCell(y);

                          cell_18.setCellStyle(style);
                        if (y == 18) {
                         cell_18.setCellValue("");
                        }
                    }
                
                 
                   
                    
//                 
//                   
//                   
//                    cell_7 = rwm.createCell(5);
//                    cell_7.setCellValue("Achieved");
//                    cell_7.setCellStyle(style);
//
//
//
//                    cell_8 = rwm.createCell(7);
//                    cell_8.setCellValue("Achieved");
//                    cell_8.setCellStyle(style);
//
//                    cell_9 = rwm.createCell(9);
//                    cell_9.setCellValue("Target");
//                    cell_9.setCellStyle(style);
//
//                    cell_10 = rwm.createCell(11);
//                    cell_10.setCellValue("Achieved");
//                    cell_10.setCellStyle(style);
//                    
//                    cell_15 = rwm.createCell(13);
//                    cell_15.setCellValue("Target");
//                    cell_15.setCellStyle(style);
//                    
//                    cell_16 = rwm.createCell(15);
//                    cell_16.setCellValue("Achieved");
//                    cell_16.setCellStyle(style);
//                    
//                    cell_17 = rwm.createCell(16);
//                    cell_17.setCellValue("Target");
//                    cell_17.setCellStyle(style);
//                    
//                    cell_18 = rwm.createCell(17);
//                    cell_18.setCellValue("");
//                    cell_18.setCellStyle(style);

                    
                  
                   
                  
                    
                  
                  
                    
                    
                   
                    

                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 3, 4));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
// aa += 1;
                   

     
     for (int i=0;i<counties.size();i++){
         district_counter=district_counter_yr=district_counter_tar=0;
         quartely_targets=0;
     county_name=counties.get(i).toString();
     county_id=0;
     county_id=i+1;
     
     String count_dist="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+quarter+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(count_dist);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter=conn.rs1.getInt(1);
     overall_districts+=conn.rs1.getInt(1);
     }
 String count_dist_tar="SELECT count(id) FROM quartely_targets WHERE county_id='"+county_id+"' && quarter='"+quarter+"' && year='"+year+"' && indicator_id='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(count_dist_tar);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_tar=conn.rs1.getInt(1);
     overall_district_counter_tar+=conn.rs1.getInt(1);
     }

   String count_dist_yr="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(count_dist_yr);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_yr=conn.rs1.getInt(1);
     overall_districts_yr+=conn.rs1.getInt(1);
     }   
     
   String achieved_selector_this_period="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+quarter+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_period);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_period=conn.rs1.getInt(1);
     achieved_this_period_total+=conn.rs1.getInt(1);
     }
 String achieved_target_this_period="SELECT SUM(target_combined) FROM quartely_targets WHERE county_id='"+county_id+"' && quarter='"+quarter+"' && year='"+year+"' && indicator_id='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_target_this_period);
if (conn.rs1.next()==true){
//    TARGETS PER COUNTY TOTALS.............................     
     quartely_targets=conn.rs1.getInt(1);
     total_quartely_targets+=conn.rs1.getInt(1);
     }
 previous_quarter=Integer.parseInt(splted_quarter[2])-1;
int year_agg=previous_quarter+1;
         while(year_agg>=1){
             String qr="Q"+year_agg;
 String achieved_selector_this_year="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='"+year+"' && reportingPeriod='"+qr+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year+=conn.rs1.getInt(1);
     achieved_this_year_total+=conn.rs1.getInt(1);
     }
 year_agg--;
         }
//         get data for the previous quarter;
         if(previous_quarter>=1){
             String cust_q="Q"+previous_quarter;
   String prev_selector="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved=conn.rs1.getInt(1);
    previous_quarter_achieved_total+= previous_quarter_achieved;
  }
         }
         
         if(previous_quarter==0){
             String cust_q="Q4";
            int cust_y=year-1;
   String prev_selector="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+cust_y+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved=conn.rs1.getInt(1);  
     previous_quarter_achieved_total+= previous_quarter_achieved;
  }
         }
         
//     ^^^^^^^^^^^ACHIEVED PRIOR PERIODS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

           
//     LOOP FOR COUNTIES
  
     achieved_prior=0;
     previous_quarter=Integer.parseInt(splted_quarter[2])-1;
     max_year_combined=year;
     if(previous_quarter==0){
             max_year_combined--;
             previous_quarter=4;
//               System.out.println(" prev b   :  "+max_year_combined);
//                System.out.println(" prev   :  "+previous_quarter);
         }
      
//     LOOP FOR YEARS   
     for(int j=max_year_combined;j>=min_year_combined;j--){
//    LOOP FOR QUARTERS

         int k=previous_quarter;
         while(k>=1){
     String custom_quarter="Q"+k; 
//     System.out.println("The quarter is :  "+custom_quarter);
         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+custom_quarter+"' && financialYear='"+j+"' && titleID='"+indicator_id+"'";  
       conn.rs2=conn.state2.executeQuery(get_total_prior);
       if(conn.rs2.next()==true){
      achieved_prior+=conn.rs2.getInt(1);     
         achieved_prior_total+= conn.rs2.getInt(1);
       } 
       k--;
       if(k==0){
     previous_quarter=4;
     break;
     }
     }    
//    System.out.println("The year is :  "+j);
     }
   
// System.out.println(" COUNTY NAME  :  "+county_name+"  PRIOR ACHIEVED  :  "+achieved_prior);
if(achieved_prior>0 || achieved_this_period>0 || quartely_targets>0){
//DISPLAY THE RESULTS
   aa+=1;
 HSSFRow rw8=shet1.createRow(aa);
       rw8.setHeightInPoints(20);
      HSSFCell rw8cell1=rw8.createCell(2);
      rw8cell1.setCellStyle(styles);
      HSSFCell rw8cell2;
      
      
      HSSFCell rw8cell3;
      
      
      HSSFCell rw8cell4;
      HSSFCell rw8cell6;
      //=rw8.createCell(9);
      HSSFCell rw8cell8;
      //=rw8.createCell(11);
      HSSFCell rw8cell5;
      HSSFCell rw8cell7;
      HSSFCell rw8cell9;
      //=rw8.createCell(17);
      //=rw8.createCell(15);
      
      
     rw8cell1.setCellValue(county_name);
      for (int y = 3; y <= 4; ++y) {
      rw8cell2= rw8.createCell(y);
      rw8cell2.setCellStyle(styles);
                        if (y == 3) {
           rw8cell2.setCellValue("");
                        }
                    }
//     rw8cell2.setCellValue
      
        for (int y = 5; y <= 6; ++y) {
      rw8cell3= rw8.createCell(y);
      rw8cell3.setCellStyle(styles);
                        if (y == 5) {
           rw8cell3.setCellValue(achieved_prior);
                        }
                    }
    // rw8cell3.setCellValue(achieved_prior);
    // rw8cell4.setCellValue(previous_quarter_achieved);
     
       for (int y = 7; y <= 9; ++y) {
      rw8cell4= rw8.createCell(y);
      rw8cell4.setCellStyle(styles);
                        if (y == 7) {
           rw8cell4.setCellValue(previous_quarter_achieved);
                        }
                    }
      if (percentages==0){
         // ;=rw8.createCell(13);
     for (int y = 9; y <= 10; ++y) {
      rw8cell5= rw8.createCell(y);
      rw8cell5.setCellStyle(styles);
                        if (y == 9) {
           rw8cell5.setCellValue(quartely_targets);
                        }
                    }
          
          //rw8cell5.setCellValue(quartely_targets);
        for (int y = 11; y <= 12; ++y) {
      rw8cell6= rw8.createCell(y);
      rw8cell6.setCellStyle(styles);
                        if (y == 11) {
           rw8cell6.setCellValue(achieved_this_period);
                        }
                    }
     //rw8cell6.setCellValue(achieved_this_period);
     
    // rw8cell7.setCellValue("");
     
        for (int y = 13; y <= 14; ++y) {
      rw8cell7= rw8.createCell(y);
      rw8cell7.setCellStyle(styles);
                        if (y == 13) {
           rw8cell7.setCellValue("");
                        }
                    }
        
         for (int y = 15; y <= 16; ++y) {
      rw8cell8= rw8.createCell(y);
      rw8cell8.setCellStyle(styles);
                        if (y == 15) {
           rw8cell8.setCellValue(achieved_this_year);
                        }
                    }
     //rw8cell8.setCellValue(achieved_this_year);
      }
      if (percentages==1){
          if(district_counter_tar>0){
              quartely_targetsAVG=quartely_targets/district_counter_tar;
                for (int y = 9; y <= 10; ++y) {
      rw8cell5= rw8.createCell(y);
      rw8cell5.setCellStyle(styles);
                        if (y == 9) {
           rw8cell5.setCellValue(quartely_targetsAVG+"%");
                        }
                    }
    // rw8cell5.setCellValue((quartely_targets/district_counter_tar)+"%");
//     System.out.println("total districts  :  "+district_counter_tar);
          }
          else{
              
                for (int y = 9; y <= 10; ++y) {
      rw8cell5= rw8.createCell(y);
      rw8cell5.setCellStyle(styles);
                        if (y == 9) {
           rw8cell5.setCellValue(0+"%");
                        }
                    }
    // rw8cell5.setCellValue((quartely_targets/district_counter_tar)+"%");
//     System.out.println("total districts  :  "+district_counter_tar);
          }
          if(district_counter>0){
              
              
                for (int y = 11; y <= 12; ++y) {
                    achieved_this_periodAVG=achieved_this_period/district_counter;
      rw8cell6= rw8.createCell(y);
      rw8cell6.setCellStyle(styles);
                        if (y == 11) {
           rw8cell6.setCellValue(achieved_this_periodAVG+"%");
                        }
                    }
     //rw8cell6.setCellValue(achieved_this_period);
     
    // rw8cell7.setCellValue("");
     
        for (int y = 13; y <= 14; ++y) {
      rw8cell7= rw8.createCell(y);
      rw8cell7.setCellStyle(styles);
                        if (y == 13) {
           rw8cell7.setCellValue("");
                        }
                    }
        
         for (int y = 15; y <= 16;++y) {
             achieved_this_yearAVG=achieved_this_year/district_counter_yr;
      rw8cell8= rw8.createCell(y);
      rw8cell8.setCellStyle(styles);
                        if (y == 15) {
           rw8cell8.setCellValue(achieved_this_yearAVG+"%");
                        }
                    }
              
              
//     rw8cell6.setCellValue((achieved_this_period/district_counter)+"%");
//     rw8cell7.setCellValue("");
//     rw8cell8.setCellValue((achieved_this_year/district_counter_yr)+"%");
      }}
      
        for (int y = 17; y <= 18; ++y) {
      rw8cell9= rw8.createCell(y);
      rw8cell9.setCellStyle(styles);
                        if (y == 15) {
           rw8cell9.setCellValue("");
                        }
                    }
//     rw8cell9.setCellValue("");  
//     rw8cell9.setCellStyle(styles);  
 
//     shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 2));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 3, 4));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
                    
//              aa+=1;       
}
 previous_quarter_achieved=achieved_prior=achieved_this_year=achieved_this_period=previous_quarter=0; 
 district_counter_tar=district_counter=0;
  }
//     total for all counties achieved
     if(achieved_prior_total>0 || achieved_this_year_total>0 || achieved_this_period_total>0 || total_quartely_targets>0){
   aa+=1;
 HSSFRow rw9=shet1.createRow(aa);
       rw9.setHeightInPoints(22);
    
     

        HSSFCell rw9cell1,rw9cell2,rw9cell3, rw9cell4,rw9cell6,rw9cell8,rw9cell5, rw9cell7,rw9cell9;
                
//      rw9cell1.setCellValue("Totals:");
      
      
      
                        rw9cell1 = rw9.createCell(2);
                        rw9cell1.setCellStyle(stylex);
                        rw9cell1.setCellValue("Totals:");
   
                        for (int y = 3; y <=4; ++y) {
                        rw9cell2 = rw9.createCell(y);
                        rw9cell2.setCellStyle(stylex);
                        if (y == 3) {
                             rw9cell2.setCellValue("");
                        }
                    }
//     rw9cell2.setCellValue("");
//       rw9cell1.setCellValue("Totals:");
//     rw9cell2.setCellValue("");
//     rw9cell3.setCellValue(achieved_prior_total);
//     rw9cell4.setCellValue(previous_quarter_achieved_total);
         
           for (int y = 5; y <=6; ++y) {
                        rw9cell3 = rw9.createCell(y);
                        rw9cell3.setCellStyle(stylex);
                        if (y == 5) {
                          rw9cell3.setCellValue(achieved_prior_total);
                        }
                    }
           for (int y = 7; y <=8; ++y) {
                        rw9cell4 = rw9.createCell(y);
                        rw9cell4.setCellStyle(stylex);
                        if (y == 7) {
                        rw9cell4.setCellValue(previous_quarter_achieved_total);
                        }
                    }
   //rw9cell5.setCellValue(total_quartely_targets);
//     rw9cell6.setCellValue(achieved_this_period_total);
//     rw9cell7.setCellValue("");
//     rw9cell8.setCellValue(achieved_this_year_total);
     if (percentages==0){
        for (int y = 9; y <=10; ++y) {
                        rw9cell5 = rw9.createCell(y);
                        rw9cell5.setCellStyle(stylex);
                        if (y == 9) {
                          rw9cell5.setCellValue(total_quartely_targets);
                        }
                    }
           for (int y = 11; y <=12; ++y) {
                        rw9cell6 = rw9.createCell(y);
                        rw9cell6.setCellStyle(stylex);
                        if (y == 11) {
                         rw9cell6.setCellValue(achieved_this_period_total);
                        }
                    }
           for (int y = 13; y <=14; ++y) {
                        rw9cell7 = rw9.createCell(y);
                        rw9cell7.setCellStyle(stylex);
                        if (y == 13) {
                     rw9cell7.setCellValue("");
                        }
                    }
           for (int y = 15; y <=16; ++y) {
                        rw9cell8 = rw9.createCell(y);
                        rw9cell8.setCellStyle(stylex);
                        if (y == 15) {
                 rw9cell8.setCellValue(achieved_this_year_total);
     
                        }
                    }
     
       for (int y = 17; y <=18; ++y) {
                        rw9cell9 = rw9.createCell(y);
                        rw9cell9.setCellStyle(stylex);
                        if (y == 17) {
                    rw9cell9.setCellValue("");
                    }
     
           }
     
     
     
     
     }
     if (percentages==1){

     
   //rw9cell5.setCellValue(total_quartely_targets);
//     rw9cell6.setCellValue(achieved_this_period_total);
//     rw9cell7.setCellValue("");
//     rw9cell8.setCellValue(achieved_this_year_total);
         
           for (int y = 9; y <=10; ++y) {
                        rw9cell5 = rw9.createCell(y);
                        rw9cell5.setCellStyle(stylex);
                        if (y == 9) {
                           if(overall_district_counter_tar>0){
     rw9cell5.setCellValue((total_quartely_targets/overall_district_counter_tar)+"%");
         }
                        }
                    }
           for (int y = 11; y <=12; ++y) {
                        rw9cell6 = rw9.createCell(y);
                        rw9cell6.setCellStyle(stylex);
                        if (y == 11) {
                         if(overall_districts>0){
     rw9cell6.setCellValue((achieved_this_period_total/overall_districts)+"%");
     }
                        }
                    }
           for (int y = 13; y <=14; ++y) {
                        rw9cell7 = rw9.createCell(y);
                        rw9cell7.setCellStyle(stylex);
                        if (y == 13) {
                               if(overall_districts>0){
                     rw9cell7.setCellValue("");
                               }
                        }
                    }
           for (int y = 15; y <=16; ++y) {
                        rw9cell8 = rw9.createCell(y);
                        rw9cell8.setCellStyle(stylex);
                        if (y == 15) {
                   if(overall_districts>0){
      rw9cell8.setCellValue((achieved_this_year_total/overall_districts_yr)+"%");}
                        }
                    }
           
             for (int y = 17; y <=18; ++y) {
                        rw9cell9 = rw9.createCell(y);
                        rw9cell9.setCellStyle(stylex);
                        if (y == 17) {
                    rw9cell9.setCellValue("");
                    }
     
           }
     }
         
     
        
                 
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 3, 4));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
       aa+=1;
    }
      overall_districts=overall_districts_yr=previous_quarter_achieved_total=achieved_prior_total=achieved_this_year_total=achieved_this_period_total=total_quartely_targets=0;
  district_counter_yr=district_counter=overall_district_counter_tar=0;
  achieved_this_periodAVG=0;
            quartely_targetsAVG=0;
            achieved_this_yearAVG=0;
      aa+=4;
   }
  else  if(table_id==1){
     
        String get_min_year="SELECT MIN(financialYear) FROM indicatorachieved";
      conn.rs4=conn.state4.executeQuery(get_min_year);
      if(conn.rs4.next()==true){
       min_year_combined=conn.rs4.getInt(1);
       if(min_year_combined<2010){
           min_year_combined=2010;
           min_year=2010;
       }
      }
     aa+=1;

                    HSSFRow rwa = shet1.createRow(aa);
                    HSSFCell cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, cell_10,cell_11,cell_12,cell_13,cell_14,cell_15,cell_16,cell_17,cells_11, cells_12, cells_13, cells_14, cells_15, cells_16, cells_17, cells_18, cells_19, cells_20, cells_21, cells_22;
                    cell_1 = rwa.createCell(2);
                    cell_1.setCellValue("RESULTS");
                    cell_1.setCellStyle(borderStyle);
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 2, 18));
                  
                    aa+=1;
                    HSSFRow rwl = shet1.createRow(aa);
                    cell_2 = rwl.createCell(2);
                    cell_2.setCellValue("County");
                    cell_2.setCellStyle(style);
//   cell_3=rwl.createCell(3);
//   cell_3.setCellValue("Baseline");
//   cell_3.setCellStyle(style);
                    for (int y = 3; y <= 4; ++y) {
                        cell_3 = rwl.createCell(y);
                        cell_3.setCellStyle(style_header);
                        if (y == 3) {
                            cell_3.setCellValue("Baseline");
                        }
                    }
//        
//         cell_4=rwl.createCell(5);
//   cell_4.setCellValue("Results Achieved Prior Period");
//   cell_4.setCellStyle(style);
                    
                        //  rw6cell4.setCellValue("Previous Quarter ("+previous_period+" "+Display_year_previous+" )");
//     rw6cell5.setCellValue("This Reporting Period ("+period+" "+Display_year+" )");
//     rw6cell7.setCellValue("PEPFAR Year ("+year+" )");

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




//   cell_5= rwl.createCell(7);
//   cell_5.setCellValue("This Reporting Period");
//   cell_5.setCellStyle(style);
                    
                  
                    for (int y = 9; y <= 12; ++y) {
                        cell_5 = rwl.createCell(y);
                        cell_5.setCellStyle(style_header);
                        if (y == 9) {
                            cell_5.setCellValue("This Reporting Period "+Display_year+"");
                        }
                    }

//   cell_6 =rwl.createCell(11);
//   cell_6.setCellValue("Year: "+year+" target");
//   cell_6.setCellStyle(style_header);

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

                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 3, 4));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa,7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 9, 12));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 13, 16));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
                   
                    aa += 1;
                    
                    
                    HSSFCell cels_1,cels_2,cels_3,cels_4,cels_5,cels_6;
                    HSSFRow rwm = shet1.createRow(aa);
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

                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 5, 6));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 7, 8));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 9, 10));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 11, 12));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 13, 14));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 15, 16));
                    shet1.addMergedRegion(new CellRangeAddress(aa, aa, 17, 18));
                    
                    aa += 1;
                    HSSFRow rwn = shet1.createRow(aa);

                    cells_22 = rwn.createCell(2);
                    cells_22.setCellValue("");
                    cells_22.setCellStyle(style);

                    cells_11 = rwn.createCell(3);
                    cells_11.setCellValue("W");
                    cells_11.setCellStyle(style);

                    cells_12 = rwn.createCell(4);
                    cells_12.setCellValue("M");
                    cells_12.setCellStyle(style);

//   rwn.createCell(4).setCellValue("M");
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


      
     for (int i=0;i<counties.size();i++){
         quarterly_target_men=quarterly_target_women=0;
     county_name=counties.get(i).toString();
     county_id=district_counter=district_counter_yr=0;
     county_id=i+1;
     
       String count_dist="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+quarter+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(count_dist);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter=conn.rs1.getInt(1);
     overall_districts+=conn.rs1.getInt(1);
     }
 String count_dist_tar="SELECT count(id) FROM quartely_targets WHERE county_id='"+county_id+"' && quarter='"+quarter+"' && year='"+year+"' && indicator_id='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(count_dist_tar);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_tar=conn.rs1.getInt(1);
     overall_district_counter_tar+=conn.rs1.getInt(1);
     }
   String count_dist_yr="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(count_dist_yr);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_yr=conn.rs1.getInt(1);
     overall_districts_yr+=conn.rs1.getInt(1);
     } 
     
   String achieved_selector_this_period="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+quarter+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_period);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_period_m=conn.rs1.getInt(1);
     achieved_this_period_total_m+=conn.rs1.getInt(1);
     achieved_this_period_f=conn.rs1.getInt(2);
     achieved_this_period_total_f+=conn.rs1.getInt(2);
     }

 previous_quarter=Integer.parseInt(splted_quarter[2])-1;
int year_agg=previous_quarter+1;
         while(year_agg>=1){
             String qr="Q"+year_agg;
 String achieved_selector_this_year="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='"+qr+"' && titleID='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year_m+=conn.rs1.getInt(1);
     achieved_this_year_total_m+=conn.rs1.getInt(1);
      achieved_this_year_f+=conn.rs1.getInt(2);
     achieved_this_year_total_f+=conn.rs1.getInt(2);
     }
 year_agg--;
         }
//         get data for the previous quarter;
         if(previous_quarter>=1){
             String cust_q="Q"+previous_quarter;
   String prev_selector="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved_m=conn.rs1.getInt(1);  
     previous_quarter_achieved_total_m+= previous_quarter_achieved_m;
     previous_quarter_achieved_f=conn.rs1.getInt(2);  
     previous_quarter_achieved_total_f+= previous_quarter_achieved_f;
  }
         }
         
         if(previous_quarter==0){
             String cust_q="Q4";
            int cust_y=year-1;
   String prev_selector="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+cust_y+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved_m=conn.rs1.getInt(1);  
     previous_quarter_achieved_total_m+= previous_quarter_achieved_m;
     previous_quarter_achieved_f=conn.rs1.getInt(2);  
     previous_quarter_achieved_total_f+= previous_quarter_achieved_f;
  }
         }
         
//     ^^^^^^^^^^^ACHIEVED PRIOR PERIODS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

           
//     LOOP FOR COUNTIES
  
     achieved_prior=0;
     previous_quarter=Integer.parseInt(splted_quarter[2])-1;
     max_year_combined=year;
     if(previous_quarter==0){
             max_year_combined--;
             previous_quarter=4;
         }
      
//     LOOP FOR YEARS   
     for(int j=max_year_combined;j>=min_year_combined;j--){
//    LOOP FOR QUARTERS

         int k=previous_quarter;
         while(k>=1){
     String custom_quarter="Q"+k; 
     
     String achieved_target_this_period="SELECT SUM(target_men),SUM(target_women) FROM quartely_targets WHERE county_id='"+county_id+"' && quarter='"+quarter+"' && year='"+year+"' && indicator_id='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_target_this_period);
if (conn.rs1.next()==true){
//    TARGETS PER COUNTY TOTALS FOR MALE AND FEMALE.............................
//    quarterly_target_men=quarterly_target_women=total_quarterly_target_men=total_quarterly_target_women=0;
     quarterly_target_men=conn.rs1.getInt(1);
     total_quarterly_target_men+=conn.rs1.getInt(1);
     quarterly_target_women=conn.rs1.getInt(1);
     total_quarterly_target_women+=conn.rs1.getInt(1);
     }
     
//     System.out.println("The quarter is :  "+custom_quarter);
         String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+custom_quarter+"' && financialYear='"+j+"' && titleID='"+indicator_id+"'";  
       conn.rs2=conn.state2.executeQuery(get_total_prior);
       if(conn.rs2.next()==true){
      achieved_prior_m+=conn.rs2.getInt(1);     
         achieved_prior_total_m+= conn.rs2.getInt(1);
         achieved_prior_f+=conn.rs2.getInt(2);     
         achieved_prior_total_f+= conn.rs2.getInt(2);
       } 
       k--;
       if(k==0){
     previous_quarter=4;
     break;
     }
     }    
//    System.out.println("The year is :  "+j);
     }
// System.out.println(" COUNTY NAME  :  "+county_name+"  PRIOR ACHIEVED  :  "+achieved_prior);
if(achieved_prior_m>0 || achieved_this_period_m>0 || achieved_prior_f>0 || achieved_this_period_f>0 || quarterly_target_women>0 || quarterly_target_men>0){
//DISPLAY THE RESULT
    aa+=1;
  HSSFRow rw8=shet1.createRow(aa);
       rw8.setHeightInPoints(20);
      HSSFCell rw8cell1=rw8.createCell(1);
      HSSFCell rw8cell2=rw8.createCell(2);
      HSSFCell rw8cell3=rw8.createCell(3);
      HSSFCell rw8cell4=rw8.createCell(4);
      HSSFCell rw8cell6=rw8.createCell(6);
      HSSFCell rw8cell8=rw8.createCell(8);
      HSSFCell rw8cell5=rw8.createCell(5);
      HSSFCell rw8cell7=rw8.createCell(7);
      HSSFCell rw8cell9=rw8.createCell(9);
      HSSFCell rw8cell10=rw8.createCell(10);
      HSSFCell rw8cell11=rw8.createCell(11);
      HSSFCell rw8cell12=rw8.createCell(12);
      HSSFCell rw8cell13=rw8.createCell(13);
      HSSFCell rw8cell14=rw8.createCell(14);
      HSSFCell rw8cell15=rw8.createCell(15);
      HSSFCell rw8cell16=rw8.createCell(16);
      HSSFCell rw8cell17=rw8.createCell(17);
      HSSFCell rw8cell18=rw8.createCell(18);
     rw8cell2.setCellValue(county_name);
     rw8cell3.setCellValue("");
     rw8cell4.setCellValue("");
     rw8cell5.setCellValue(achieved_prior_f);
     rw8cell6.setCellValue(achieved_prior_m);
     
     rw8cell7.setCellValue(previous_quarter_achieved_f);
     rw8cell8.setCellValue(previous_quarter_achieved_m);
     
//     styling
     rw8cell2.setCellStyle(styles);
     rw8cell3.setCellStyle(styles);
     rw8cell4.setCellStyle(styles);
     rw8cell5.setCellStyle(styles);
     rw8cell6.setCellStyle(styles);
     
     rw8cell7.setCellStyle(styles);
     rw8cell8.setCellStyle(styles);
     if (percentages==0){
     rw8cell9.setCellValue(quarterly_target_women);
     rw8cell10.setCellValue(quarterly_target_men); 
     rw8cell11.setCellValue(achieved_this_period_f);
     rw8cell12.setCellValue(achieved_this_period_m);
     rw8cell13.setCellValue("");
     rw8cell14.setCellValue("");
     rw8cell15.setCellValue(achieved_this_year_f);
     rw8cell16.setCellValue(achieved_this_year_m);
     // styling 
     rw8cell9.setCellStyle(styles);
     rw8cell10.setCellStyle(styles); 
     rw8cell11.setCellStyle(styles);
     rw8cell12.setCellStyle(styles);
     rw8cell13.setCellStyle(styles);
     rw8cell14.setCellStyle(styles);
     rw8cell15.setCellStyle(styles);
     rw8cell16.setCellStyle(styles);
}
     
   if (percentages==1){
       if(district_counter_tar>0){
      rw8cell9.setCellValue((quarterly_target_women/district_counter_tar)+"%"); 
     rw8cell10.setCellValue((quarterly_target_men/district_counter_tar)+"%");
       }
       if(district_counter>0){
     rw8cell11.setCellValue((achieved_this_period_f/district_counter)+"%");
     rw8cell12.setCellValue((achieved_this_period_m/district_counter)+"%");
       
     rw8cell13.setCellValue("");
     rw8cell14.setCellValue("");
     
     rw8cell15.setCellValue((achieved_this_year_f/district_counter_yr)+"%");
     rw8cell16.setCellValue((achieved_this_year_m/district_counter_yr)+"%");
}}
      
     rw8cell17.setCellValue("");
     rw8cell18.setCellValue("");
     
     
     
       rw8cell9.setCellStyle(styles);
     rw8cell10.setCellStyle(styles); 
     rw8cell11.setCellStyle(styles);
     rw8cell12.setCellStyle(styles);
     rw8cell13.setCellStyle(styles);
     rw8cell14.setCellStyle(styles);
     rw8cell15.setCellStyle(styles);
     rw8cell16.setCellStyle(styles);
     rw8cell16.setCellStyle(styles);
     rw8cell17.setCellStyle(styles);
     rw8cell18.setCellStyle(styles);
    
}
 previous_quarter_achieved_m=achieved_prior_m=achieved_this_year_m=achieved_this_period_m=previous_quarter=0;
 previous_quarter_achieved_f=achieved_prior_f=achieved_this_year_f=achieved_this_period_f=0;
 district_counter_yr=district_counter=district_counter_tar=0;
  }
//     total for all counties achieved
     if(achieved_prior_total_m>0 ||total_quarterly_target_women>0 ||total_quarterly_target_men>0 || achieved_this_year_total_m>0 || achieved_this_period_total_m>0 || achieved_prior_total_f>0 || achieved_this_year_total_f>0 || achieved_this_period_total_f>0){
     aa+=1;
HSSFRow rwk=shet1.createRow(aa);
       rwk.setHeightInPoints(20);
//      HSSFCell rwkcell1=rwk.createCell(1);
      HSSFCell rwkcell2=rwk.createCell(2);
      HSSFCell rwkcell3=rwk.createCell(3);
      HSSFCell rwkcell4=rwk.createCell(4);
      HSSFCell rwkcell6=rwk.createCell(6);
      HSSFCell rwkcell8=rwk.createCell(8);
      HSSFCell rwkcell5=rwk.createCell(5);
      HSSFCell rwkcell7=rwk.createCell(7);
      HSSFCell rwkcell9=rwk.createCell(9);
      HSSFCell rwkcell10=rwk.createCell(10);
      HSSFCell rwkcell11=rwk.createCell(11);
      HSSFCell rwkcell12=rwk.createCell(12);
      HSSFCell rwkcell13=rwk.createCell(13);
      HSSFCell rwkcell14=rwk.createCell(14);
      HSSFCell rwkcell15=rwk.createCell(15);
      HSSFCell rwkcell16=rwk.createCell(16);
      HSSFCell rwkcell17=rwk.createCell(17);
      HSSFCell rwkcell18=rwk.createCell(18);
     rwkcell2.setCellValue("Total");
     rwkcell3.setCellValue("");
     rwkcell4.setCellValue("");
     rwkcell5.setCellValue(achieved_prior_total_f);
     rwkcell6.setCellValue(achieved_prior_total_m);
     rwkcell7.setCellValue(previous_quarter_achieved_total_f);
     rwkcell8.setCellValue(previous_quarter_achieved_total_m);
     if(percentages==0){
     rwkcell9.setCellValue(total_quarterly_target_women);
     rwkcell10.setCellValue(total_quarterly_target_men); 
     rwkcell11.setCellValue(achieved_this_period_total_f);
     rwkcell12.setCellValue(achieved_this_period_total_m);
     rwkcell13.setCellValue("");
     rwkcell14.setCellValue("");
     rwkcell15.setCellValue(achieved_this_year_total_f);
     rwkcell16.setCellValue(achieved_this_year_total_m);
     }
     
       if(percentages==1){
           if(overall_district_counter_tar>0){
     rwkcell7.setCellValue((total_quarterly_target_women/overall_district_counter_tar)+"%");
     rwkcell8.setCellValue((total_quarterly_target_men/overall_district_counter_tar)+"%"); 
           }
           if(overall_districts>0){
     rwkcell11.setCellValue((achieved_this_period_total_f/overall_districts)+"%");
     rwkcell12.setCellValue((achieved_this_period_total_m/overall_districts)+"%");
     rwkcell13.setCellValue("");
     rwkcell14.setCellValue("");
     rwkcell15.setCellValue((achieved_this_year_total_f/overall_districts_yr)+"%");
     rwkcell16.setCellValue((achieved_this_year_total_m/overall_districts_yr)+"%");
     }}
     rwkcell17.setCellValue("");
     rwkcell18.setCellValue("");
       
//     rwkcell1.setCellStyle(stylex);
      rwkcell2.setCellStyle(stylex);
      rwkcell3.setCellStyle(stylex);
      rwkcell4.setCellStyle(stylex);
      rwkcell5.setCellStyle(stylex);
      rwkcell6.setCellStyle(stylex);
      rwkcell7.setCellStyle(stylex);
      rwkcell8.setCellStyle(stylex);
      rwkcell9.setCellStyle(stylex);
      rwkcell10.setCellStyle(stylex);
      rwkcell11.setCellStyle(stylex);
      rwkcell12.setCellStyle(stylex);
      rwkcell13.setCellStyle(stylex);
      rwkcell14.setCellStyle(stylex);
      rwkcell15.setCellStyle(stylex);
      rwkcell16.setCellStyle(stylex);
      rwkcell17.setCellStyle(stylex);
      rwkcell18.setCellStyle(stylex);
    }
      previous_quarter_achieved_total_m=achieved_prior_total_m=achieved_this_year_total_m=achieved_this_period_total_m=0;
      previous_quarter_achieved_total_f=achieved_prior_total_f=achieved_this_year_total_f=achieved_this_period_total_f=0; 
      total_quarterly_target_women=total_quarterly_target_men=overall_districts=overall_districts_yr=overall_district_counter_tar=0;
    aa+=2;    
    }
     aa+=1; 
     
    
    }
     
     ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename="+period+"_"+Display_year+"_"+"County_Report_Created_On_"+current_time+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
                
       

    //response.sendRedirect("county_quarter_report.jsp");
    
    
    
    
        }
        catch(Exception EX){
        System.out.println("Error   "+EX.toString());
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
            Logger.getLogger(comprehensive_per_county.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
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
            Logger.getLogger(comprehensive_per_county.class.getName()).log(Level.SEVERE, null, ex);
              System.out.println(ex.toString());
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
