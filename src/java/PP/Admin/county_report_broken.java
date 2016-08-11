/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class county_report_broken extends HttpServlet {
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
int previous_quarter,previous_quarter_achieved,year,prior_quarter;
int aa=0,aa3=0,aa2=0,table_id;
int previous_quarter_achieved_total_m,previous_quarter_achieved_total_f;
int previous_quarter_achieved_m,previous_quarter_achieved_f;
int achieved_prior_m,achieved_this_period_m,achieved_this_year_m;
int achieved_prior_total_m,achieved_this_period_total_m,achieved_this_year_total_m;
  int total_yearly_targets1=0;
       int total_yearly_targets2=0;
        int total_yearly_targets=0;
		int avgqtr=0;
int this_year_target_m, project_target_m;
int this_year_target_total_m, project_target_total_m;
int achieved_prior_f,achieved_this_period_f,achieved_this_year_f;
int achieved_prior_total_f,achieved_this_period_total_f;
int this_year_target_f, project_target_f;
int this_year_target_total_f, project_target_total_f;
String period="",previous_period="";
int Display_year=0,Display_year_previous=0;
int percentages=0,quartely_targets=0,total_quartely_targets;
int quarterly_target_men,quarterly_target_women,total_quarterly_target_men,total_quarterly_target_women;
String current_time;
int district_counter,overall_districts,district_counter_yr,overall_districts_yr,district_counter_prev,district_counter_prior;
int district_counter_tar,overall_district_counter_tar,overall_district_counter_prev,overall_district_counter_prior;
ArrayList activity = new ArrayList();
ArrayList activity_value = new ArrayList();
ArrayList end_dt = new ArrayList();
ArrayList county_id_array=new ArrayList();
int percents=0;
int totaldistsprior=0;
int totaldistspriors=0;
 ArrayList countyname=new ArrayList();
                   ArrayList countyid=new ArrayList();     
                   String[] CountyID;
                    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
      
        try{int district_counter_priors=0;
	 int overall_district_counter_priors=0;
            session=request.getSession();
        int activity_checker=0;
        dbConnect conn= new dbConnect();
        counties.clear();
        activity.clear();
        activity_value.clear();
        end_dt.clear();
        county_id_array.clear();
        int achieved_this_year_totals=0;
          int achieved_prior_f_count=0;
        Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
int district_counter_yr1=0;
int counterforyear=0;
int achieved_this_year_total_m,achieved_this_year_total_f=0;

current_time=year+"_"+month+"_"+date;
  district_counter=0;      
        percents=0;
        
        
        
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
previous_quarter_achieved_total=previous_quarter_achieved=previous_quarter=prior_quarter=0;

previous_quarter_achieved_total_m=previous_quarter_achieved_total_f=0;
previous_quarter_achieved_m=previous_quarter_achieved_f=0;
achieved_prior_m=achieved_this_period_m=achieved_this_year_m=0;
achieved_prior_total_m=achieved_this_period_total_m=achieved_this_year_total_m=0;
   total_yearly_targets1=0;
        total_yearly_targets2=0;
        total_yearly_targets=0;
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
    quarter=request.getParameter("Quarter");
    activity_date="";
    year=Integer.parseInt(request.getParameter("FinancialYear"));
    
    if (request.getParameterValues("countyid") != null && !request.getParameterValues("countyid").equals("")) {
                CountyID=request.getParameterValues("countyid");
               
            }
    table_id=0;
//    GET THE QUARTERS FOR DISPLAY
    String prior_qtr="";
	int prior_year=0;
	if(quarter.equals("Q1")){
	prior_qtr ="Q3";
	prior_year= year-1;
	}
	else if(quarter.equals("Q2")){
	prior_qtr ="Q4";
	
	prior_year=year-1;
	}
	else if(quarter.equals("Q3")){
	prior_qtr ="Q1";
	prior_year=year;
	}
	else if(quarter.equals("Q4")){
	prior_qtr ="Q2";
	prior_year=year;
	}
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
   
    if(!counties.isEmpty()){
        counties.clear();
    }
    if(!county_id_array.isEmpty()){
        county_id_array.clear();
    }
      for(int w=0;w<CountyID.length;w++){
   String county_selector="SELECT * FROM county where countyID='"+CountyID[w] +"' order by countyName";
      conn.rs=conn.state.executeQuery(county_selector);
      while(conn.rs.next()){
         counties.add(conn.rs.getString(2)); 
         county_id_array.add(conn.rs.getString(1));
      }
   }
  
    String[] designations = {"3","4","9","8"} ;
    String[] sheets={"M&E","Clinical","SDH","HC"};
         HSSFWorkbook wb=new HSSFWorkbook();
    for(int shts=0;shts< designations.length ; shts++){
        //            ****************HEADING IN EXCEL**********************************************************
          
               HSSFSheet shet1=wb.createSheet(sheets[shts]);
              
        aa=0;       
               
        
    shet1.setColumnWidth(0, 2000);
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

     CellStyle style_border=wb.createCellStyle();
       style_border.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style_border.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style_border.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style_border.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      
      
       HSSFFont font1=wb.createFont();
    font1.setFontHeightInPoints((short)18);
    font1.setFontName("Cambria");
   
    font1.setBoldweight((short)7);
    font1.setColor(HSSFColor.BLACK.index);
      
     CellStyle style_border1=wb.createCellStyle();
       style_border1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style_border1.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style_border1.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style_border1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      style_border1.setFont(font);
      
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
//
// style_indicator.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//      style_indicator.setBorderTop(HSSFCellStyle.BORDER_THIN);
//      style_indicator.setBorderRight(HSSFCellStyle.BORDER_THIN);
//      style_indicator.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      
      
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
      stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
      stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
               
HSSFCellStyle styley = wb.createCellStyle();
HSSFFont fonty = wb.createFont();
 styley.setFont(fonty);

 styley.setBorderLeft(HSSFCellStyle.BORDER_THIN);
HSSFCellStyle stylez = wb.createCellStyle();
HSSFFont fontz= wb.createFont();
 stylez.setFont(fontz);
stylez.setBorderRight(HSSFCellStyle.BORDER_THIN);

HSSFCellStyle styletop = wb.createCellStyle();
HSSFFont fontp= wb.createFont();
 styletop.setFont(fontp);
styletop.setBorderTop(HSSFCellStyle.BORDER_THIN);

               
  CellStyle borderStyle = wb.createCellStyle();
  borderStyle.setFillForegroundColor(HSSFColor.ROSE.index);
borderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

      borderStyle.setFont(fontx);
//borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
//borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
//borderStyle.setBorderRight(CellStyle.BORDER_THIN);
//borderStyle.setBorderTop(CellStyle.BORDER_THIN);
borderStyle.setAlignment(CellStyle.ALIGN_CENTER);             
               
  HSSFRow rw0=shet1.createRow(0);
    rw0.setHeightInPoints(35);
     HSSFCell rw0cell0=rw0.createCell(1);
   rw0cell0.setCellValue("PROGRAM PROGRESS COUNTY REPORT");  

   
shet1.addMergedRegion(new CellRangeAddress(0,0,1,17)); 
   rw0cell0.setCellStyle(borderStyle);
   
   //end of sheet headers

       
 
//      SPLIT TO GET THE QUARTER VALUE
        String  splted_quarter=quarter.substring(1);
         
          
         
          
    
           
  int tableCounter=0;
    String indicator_selector="SELECT * FROM indicatortitles WHERE (tableIdentifier='1' ||tableIdentifier='2') and active='yes' and designation like '%"+designations[shts]+"%' ";
    conn.rs=conn.state.executeQuery(indicator_selector);
    while(conn.rs.next()){
        tableCounter++;
        
        quartely_targets=total_quartely_targets=overall_districts=overall_districts_yr=overall_district_counter_tar=overall_district_counter_prev=overall_district_counter_prior=0;
        quarterly_target_men=quarterly_target_women=total_quarterly_target_men=total_quarterly_target_women=0;
        String indicator_id=conn.rs.getString(1) ;
      String indicator_title=conn.rs.getString(3);
      String indicator_number=conn.rs.getString(4);
      table_id=conn.rs.getInt("tableIdentifier");
      percentages=conn.rs.getInt("percentage");
      percents=conn.rs.getInt("percentage");
     
      aa+=1;
    
   HSSFRow rw_2 = shet1.createRow(aa);
                    HSSFCell cell_0;
                    
                    rw_2.setHeightInPoints(20);
                    
                  
              


                    for (int y = 1; y <= 13; ++y) {
                        cell_0 = rw_2.createCell(y);
                       

                        cell_0.setCellStyle(style_border1);
                       
                        if (y == 1) {
                            cell_0.setCellValue("Table No  :" + tableCounter );
                         
                        }
                    }
        
                         shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,13)); 
                        
         aa+=1;
       
      
       HSSFRow rw1=shet1.createRow(aa);
        rw1.setHeightInPoints(35);
       HSSFCell rw1cell0=rw1.createCell(1);
       rw1cell0.setCellValue(" INDICATOR TITLE :       "+indicator_title);
       rw1cell0.setCellStyle(style_indicator);
        shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,17)); 
        
       
       aa+=1;
      
       
       //SHEET 1
      HSSFRow rw2=shet1.createRow(aa);
      rw2.setHeightInPoints(20);
       HSSFCell rw2cell0=rw2.createCell(1);
        HSSFCell rw2cell2=rw2.createCell(3);
      HSSFCell rw2cell3=rw2.createCell(12);
      HSSFCell rw2cell4=rw2.createCell(14);
      HSSFCell rw2cell5=rw2.createCell(16);
      HSSFCell rw2cell21=rw2.createCell(2);
      HSSFCell rw2cell41=rw2.createCell(4);
      HSSFCell rw2cell51=rw2.createCell(5);
      HSSFCell rw2cell6=rw2.createCell(6);
      HSSFCell rw2cell7=rw2.createCell(7);
      HSSFCell rw2cell8=rw2.createCell(8);
      HSSFCell rw2cell9=rw2.createCell(9);
      HSSFCell rw2cell10=rw2.createCell(10);
      HSSFCell rw2cell11=rw2.createCell(11);
      HSSFCell rw2cell13=rw2.createCell(13);
      HSSFCell rw2cell15=rw2.createCell(15);
      HSSFCell rw2cell17=rw2.createCell(17);
      
      //END OF SHEET 1
      
     
      
      //SHEET 1
      rw2cell0.setCellValue("INDICATOR NUMBER  : "+indicator_number); 
            shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,17)); 
             rw2cell0.setCellStyle(style_header);
          rw2cell0.setCellStyle(style_header);
       rw2cell2.setCellStyle(style_header);       
      rw2cell3.setCellStyle(style_header);
      rw2cell4.setCellStyle(style_header);        
      rw2cell5.setCellStyle(style_header);
      rw2cell21.setCellStyle(style_header);
     rw2cell41.setCellStyle(style_header);
     rw2cell51.setCellStyle(style_header);
     rw2cell6.setCellStyle(style_header);
     rw2cell7.setCellStyle(style_header);
     rw2cell8.setCellStyle(style_header);
     rw2cell9.setCellStyle(style_header);
     rw2cell10.setCellStyle(style_header);
     rw2cell11.setCellStyle(style_header);
     rw2cell13.setCellStyle(style_header);
     rw2cell15.setCellStyle(style_header);
     rw2cell17.setCellStyle(style_header);
     
     // END SHEET 2
     
    
      //System.out.println(" ID IS : "+indicator_id+" INDICATOR TITLE  :  "+indicator_title);
      aa+=1;
  
      HSSFRow rw3=shet1.createRow(aa);
      HSSFCell rw3cell0=rw3.createCell(1);
       HSSFCell rw3cell2=rw3.createCell(3);
      HSSFCell rw3cell3=rw3.createCell(12);
      HSSFCell rw3cell4=rw3.createCell(14);
      HSSFCell rw3cell5=rw3.createCell(16);
      HSSFCell rw3cell21=rw3.createCell(2);
      HSSFCell rw3cell41=rw3.createCell(4);
      HSSFCell rw3cell51=rw3.createCell(5);
      HSSFCell rw3cell6=rw3.createCell(6);
      HSSFCell rw3cell7=rw3.createCell(7);
      HSSFCell rw3cell8=rw3.createCell(8);
      HSSFCell rw3cell9=rw3.createCell(9);
      HSSFCell rw3cell10=rw3.createCell(10);
      HSSFCell rw3cell11=rw3.createCell(11);
      HSSFCell rw3cell13=rw3.createCell(13);
      HSSFCell rw3cell15=rw3.createCell(15);
      HSSFCell rw3cell17=rw3.createCell(17);
      rw3cell0.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,17));
       rw3cell0.setCellStyle(style);

     rw3cell17.setCellStyle(style_border);
    
     
     
       aa+=1; 
      
       
       
       //SHHET 1
       HSSFRow rw4=shet1.createRow(aa);
      HSSFCell rw4cell1=rw4.createCell(1);
      HSSFCell rw4cell2=rw4.createCell(3);
      HSSFCell rw4cell3=rw4.createCell(12);
      HSSFCell rw4cell4=rw4.createCell(14);
      HSSFCell rw4cell5=rw4.createCell(16);
      HSSFCell rw4cell21=rw4.createCell(2);
      HSSFCell rw4cell41=rw4.createCell(4);
      HSSFCell rw4cell51=rw4.createCell(5);
      HSSFCell rw4cell6=rw4.createCell(6);
      HSSFCell rw4cell7=rw4.createCell(7);
      HSSFCell rw4cell8=rw4.createCell(8);
      HSSFCell rw4cell9=rw4.createCell(9);
      HSSFCell rw4cell10=rw4.createCell(10);
      HSSFCell rw4cell11=rw4.createCell(11);
      HSSFCell rw4cell13=rw4.createCell(13);
      HSSFCell rw4cell15=rw4.createCell(15);
      HSSFCell rw4cell17=rw4.createCell(17);
//      HSSFCell rw4cell6=rw4.createCell(11);
      rw4cell1.setCellValue("Geographical Location");
      rw4cell2.setCellValue("Activity Title");        
      rw4cell3.setCellValue("Date");
      rw4cell4.setCellValue("Number");        
      rw4cell5.setCellValue("Sub-Total");
           
  
           //SHHET 1
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,2));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,3,11));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
    
    
    //END OF SHEET 1
//      rw4cell6.setCellValue("");
    
    
    //START OF SHEET 1
      rw4cell1.setCellStyle(stylex);
      rw4cell2.setCellStyle(stylex);       
      rw4cell3.setCellStyle(stylex);
      rw4cell4.setCellStyle(stylex);        
      rw4cell5.setCellStyle(stylex);
      rw4cell21.setCellStyle(style_border);
     rw4cell41.setCellStyle(style_border);
     rw4cell51.setCellStyle(style_border);
     rw4cell6.setCellStyle(style_border);
     rw4cell7.setCellStyle(style_border);
     rw4cell8.setCellStyle(style_border);
     rw4cell9.setCellStyle(style_border);
     rw4cell10.setCellStyle(style_border);
     rw4cell11.setCellStyle(style_border);
     rw4cell13.setCellStyle(style_border);
     rw4cell15.setCellStyle(style_border);
     rw4cell17.setCellStyle(style_border);
     
   
       System.out.println(counties.size() +" size of array "+ county_id_array.size()); 
     for (int i=0;i<counties.size();i++){
         int merger=0;
         district_counter=0;
         activity_checker=0;
     county_name=counties.get(i).toString();
     county_id=0;
     total_activities_achieved=0;
     county_id=Integer.parseInt(county_id_array.get(i).toString());
     
   
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
    
    overall_activities+=total_activities_achieved; 
//    if(percentages==1) {
//    total_activities_achieved=0;   
//   }
//      if(activity.size()>0){
//    System.out.println("the lisit size is   :  "+activity.size());
//   shet1.addMergedRegion(new CellRangeAddress(aa,aa+(activity.size()-1),1,2)); 
//    }
    if(activity.size()>0){
    for(int j=0;j<activity.size();j++) {
         aa+=1; 
         aa2+=1; 
         
         
//  System.out.println("The Indicator  is  : "+indicator_title +"     The County is  : "+county_name+"       activity is "+activity.get(j).toString()+"    and the achieved is   :  "+activity_value.get(j).toString());   
  overall_activities+=Integer.parseInt(activity_value.get(j).toString());
       HSSFRow rw5=shet1.createRow(aa);
    
       rw5.setHeightInPoints(20);
    
      HSSFCell rw5cell1=rw5.createCell(1);
      HSSFCell rw5cell2=rw5.createCell(3);
      HSSFCell rw5cell3=rw5.createCell(12);
      HSSFCell rw5cell4=rw5.createCell(14);
      HSSFCell rw5cell5=rw5.createCell(16);
      HSSFCell rw5cell21=rw5.createCell(2);
      HSSFCell rw5cell41=rw5.createCell(4);
      HSSFCell rw5cell51=rw5.createCell(5);
      HSSFCell rw5cell6=rw5.createCell(6);
      HSSFCell rw5cell7=rw5.createCell(7);
      HSSFCell rw5cell8=rw5.createCell(8);
      HSSFCell rw5cell9=rw5.createCell(9);
      HSSFCell rw5cell10=rw5.createCell(10);
      HSSFCell rw5cell11=rw5.createCell(11);
      HSSFCell rw5cell13=rw5.createCell(13);
      HSSFCell rw5cell15=rw5.createCell(15);
      HSSFCell rw5cell17=rw5.createCell(17);
     // end of sheeet 1
      
      
     
     
//      HSSFCell rw4cell6=rw4.createCell(11);
      if(merger==0){
      rw5cell1.setCellValue(county_name);
    
      merger=1;
      }
      
     // for sheet 1
      rw5cell2.setCellValue(activity.get(j).toString());        
      rw5cell3.setCellValue(end_dt.get(j).toString());
//      if(percentages==0){
      rw5cell4.setCellValue(Integer.parseInt(activity_value.get(j).toString()));        
      rw5cell5.setCellValue(Integer.parseInt(activity_value.get(j).toString()));
      
    
     // for sheet 1 
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,2));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,3,11));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17)); 
    
    
    
//    for sheet
     rw5cell1.setCellStyle(style_border);
     rw5cell2.setCellStyle(style_border);
     rw5cell3.setCellStyle(style_border);
     rw5cell4.setCellStyle(style_border);
     rw5cell5.setCellStyle(style_border);
     rw5cell21.setCellStyle(style_border);
     rw5cell41.setCellStyle(style_border);
     rw5cell51.setCellStyle(style_border);
     rw5cell6.setCellStyle(style_border);
     rw5cell7.setCellStyle(style_border);
     rw5cell8.setCellStyle(style_border);
     rw5cell9.setCellStyle(style_border);
     rw5cell10.setCellStyle(style_border);
     rw5cell11.setCellStyle(style_border);
     rw5cell13.setCellStyle(style_border);
     rw5cell15.setCellStyle(style_border);
     rw5cell17.setCellStyle(style_border);
     // for sheet 1 
     
     
     
 }
     }
     else{
       aa+=1; 
     
         
         
//  System.out.println("The Indicator  is  : "+indicator_title +"     The County is  : "+county_name+"       activity is "+activity.get(j).toString()+"    and the achieved is   :  "+activity_value.get(j).toString());   
       HSSFRow rw5=shet1.createRow(aa);
    
       rw5.setHeightInPoints(20);

       
      // for sheet
      HSSFCell rw5cell1=rw5.createCell(1);
      HSSFCell rw5cell2=rw5.createCell(3);
      HSSFCell rw5cell3=rw5.createCell(12);
      HSSFCell rw5cell4=rw5.createCell(14);
      HSSFCell rw5cell5=rw5.createCell(16);
      HSSFCell rw5cell21=rw5.createCell(2);
      HSSFCell rw5cell41=rw5.createCell(4);
      HSSFCell rw5cell51=rw5.createCell(5);
      HSSFCell rw5cell6=rw5.createCell(6);
      HSSFCell rw5cell7=rw5.createCell(7);
      HSSFCell rw5cell8=rw5.createCell(8);
      HSSFCell rw5cell9=rw5.createCell(9);
      HSSFCell rw5cell10=rw5.createCell(10);
      HSSFCell rw5cell11=rw5.createCell(11);
      HSSFCell rw5cell13=rw5.createCell(13);
      HSSFCell rw5cell15=rw5.createCell(15);
      HSSFCell rw5cell17=rw5.createCell(17);
    
     
//      HSSFCell rw4cell6=rw4.createCell(11);
      if(merger==0){
      rw5cell1.setCellValue(county_name);
    
      merger=1;
      }
      rw5cell2.setCellValue("No Activity");        
      rw5cell3.setCellValue("");
//      if(percentages==0){
      rw5cell4.setCellValue("");        
      rw5cell5.setCellValue(""); 
      
      
   
      
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,2));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,3,11));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17)); 
   
    
    
//    sheet 1 
     rw5cell1.setCellStyle(style_border);
     rw5cell2.setCellStyle(style_border);
     rw5cell3.setCellStyle(style_border);
     rw5cell4.setCellStyle(style_border);
     rw5cell5.setCellStyle(style_border);
     rw5cell21.setCellStyle(style_border);
     rw5cell41.setCellStyle(style_border);
     rw5cell51.setCellStyle(style_border);
     rw5cell6.setCellStyle(style_border);
     rw5cell7.setCellStyle(style_border);
     rw5cell8.setCellStyle(style_border);
     rw5cell9.setCellStyle(style_border);
     rw5cell10.setCellStyle(style_border);
     rw5cell11.setCellStyle(style_border);
     rw5cell13.setCellStyle(style_border);
     rw5cell15.setCellStyle(style_border);
     rw5cell17.setCellStyle(style_border);  
     
    
}
    if(activity.size()>1){
    System.out.println("the lisit size is   :  "+activity.size());
   shet1.addMergedRegion(new CellRangeAddress(aa-(activity.size()-1),aa,1,2)); 

    }
  activity.clear();
 activity_value.clear();
 end_dt.clear();
     }
    
     overall_activities=overall_districts=0;
     
     //end of activities
     
     
     
     
//     DISPLAY THE TOTALS FOR ALL THE RESULTS IN ALL THE COUNTIES..................
      aa+=1; 
//      aa2+=1; 
//      aa3+=1; 
      
      //sheet 1
       HSSFRow rw5=shet1.createRow(aa);
       rw5.setHeightInPoints(30);
      HSSFCell rw5cell1=rw5.createCell(1);
      HSSFCell rw5cell2=rw5.createCell(14);
      HSSFCell rw5cell3=rw5.createCell(16);
      HSSFCell rw5cell21=rw5.createCell(2);
      HSSFCell rw5cell31=rw5.createCell(3);
      HSSFCell rw5cell41=rw5.createCell(4);
      HSSFCell rw5cell51=rw5.createCell(5);
      HSSFCell rw5cell6=rw5.createCell(6);
      HSSFCell rw5cell7=rw5.createCell(7);
      HSSFCell rw5cell8=rw5.createCell(8);
      HSSFCell rw5cell9=rw5.createCell(9);
      HSSFCell rw5cell10=rw5.createCell(10);
      HSSFCell rw5cell11=rw5.createCell(11);
      HSSFCell rw5cell12=rw5.createCell(12);
      HSSFCell rw5cell13=rw5.createCell(13);
      HSSFCell rw5cell15=rw5.createCell(15);
      HSSFCell rw5cell17=rw5.createCell(17);
     rw5cell1.setCellValue("RESULTS");
     rw5cell1.setCellStyle(stylex);
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,1,17));
 rw5cell2.setCellStyle(style_border);
     rw5cell3.setCellStyle(style_border);
     rw5cell21.setCellStyle(style_border);
     rw5cell41.setCellStyle(style_border);
     rw5cell51.setCellStyle(style_border);
     rw5cell6.setCellStyle(style_border);
     rw5cell7.setCellStyle(style_border);
     rw5cell8.setCellStyle(style_border);
     rw5cell9.setCellStyle(style_border);
     rw5cell10.setCellStyle(style_border);
     rw5cell11.setCellStyle(style_border);
     rw5cell13.setCellStyle(style_border);
     rw5cell15.setCellStyle(style_border);
     rw5cell17.setCellStyle(style_border);
     rw5cell31.setCellStyle(style_border);
     rw5cell12.setCellStyle(style_border);
      
   
    
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
     aa3+=1;
  HSSFRow rw6=shet1.createRow(aa);
       rw6.setHeightInPoints(43);
      HSSFCell rw6cell1=rw6.createCell(1);
      HSSFCell rw6cell2=rw6.createCell(2);
      HSSFCell rw6cell3=rw6.createCell(4);
      HSSFCell rw6cell4=rw6.createCell(6);
      HSSFCell rw6cell5=rw6.createCell(8);
      HSSFCell rw6cell7=rw6.createCell(12);
      HSSFCell rw6cell9=rw6.createCell(16);
      HSSFCell rw6cell21=rw6.createCell(10);
      HSSFCell rw6cell22=rw6.createCell(3);
      HSSFCell rw6cell23=rw6.createCell(5);
      HSSFCell rw6cell24=rw6.createCell(7);
      HSSFCell rw6cell25=rw6.createCell(9);
      HSSFCell rw6cell27=rw6.createCell(13);
      HSSFCell rw6cell29=rw6.createCell(17);
       HSSFCell rw6cell32=rw6.createCell(11);
       HSSFCell rw6cell290=rw6.createCell(14);
       HSSFCell rw6cell320=rw6.createCell(15);
     rw6cell1.setCellValue("County Name");
     rw6cell2.setCellValue("Baseline");
     rw6cell3.setCellValue("Achieved Prior Periods");
     rw6cell4.setCellValue("Previous Quarter ("+previous_period+" "+Display_year_previous+" )");
     rw6cell5.setCellValue("This Reporting Period ("+period+" "+Display_year+" )");
     rw6cell7.setCellValue("PEPFAR Year ("+year+" )");
     rw6cell9.setCellValue("End Of Project Target");
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,11));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,15));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
      
      rw6cell1.setCellStyle(style_header);
      rw6cell2.setCellStyle(style_header);
      rw6cell3.setCellStyle(style_header);
      rw6cell4.setCellStyle(style_header);
      rw6cell5.setCellStyle(style_header);
      rw6cell7.setCellStyle(style_header);
      rw6cell9.setCellStyle(style_header);
      rw6cell21.setCellStyle(style_header);
      rw6cell22.setCellStyle(style_header);
      rw6cell23.setCellStyle(style_header);
      rw6cell24.setCellStyle(style_header);
      rw6cell25.setCellStyle(style_header);
      rw6cell27.setCellStyle(style_header);
      rw6cell29.setCellStyle(style_header);
      rw6cell32.setCellStyle(style_header);
      rw6cell290.setCellStyle(style_header);
      rw6cell320.setCellStyle(style_header);
     // end of sheet 1
      
     
//end of sheet 2
     aa+=1;
   
     
     
     //sheet 1
  HSSFRow rw7=shet1.createRow(aa);
       rw7.setHeightInPoints(20);
      HSSFCell rw7cell1=rw7.createCell(1);
      HSSFCell rw7cell2=rw7.createCell(2);
      HSSFCell rw7cell3=rw7.createCell(4);
      HSSFCell rw7cell4=rw7.createCell(6);
      HSSFCell rw7cell6=rw7.createCell(10);
      HSSFCell rw7cell8=rw7.createCell(14);
      HSSFCell rw7cell5=rw7.createCell(8);
      HSSFCell rw7cell7=rw7.createCell(12);
      HSSFCell rw7cell9=rw7.createCell(16);
      HSSFCell rw7cell21=rw7.createCell(3);
      HSSFCell rw7cell22=rw7.createCell(5);
      HSSFCell rw7cell23=rw7.createCell(7);
      HSSFCell rw7cell24=rw7.createCell(15);
      HSSFCell rw7cell26=rw7.createCell(9);
      HSSFCell rw7cell28=rw7.createCell(11);
      HSSFCell rw7cell25=rw7.createCell(13);
      HSSFCell rw7cell27=rw7.createCell(12);
      HSSFCell rw7cell29=rw7.createCell(17);
     rw7cell1.setCellValue("");
     rw7cell2.setCellValue("");
     rw7cell3.setCellValue("Achieved");
     rw7cell4.setCellValue("Achieved");
     rw7cell5.setCellValue("Target");
     rw7cell6.setCellValue("Achieved");
     rw7cell7.setCellValue("Target");
     rw7cell8.setCellValue("Achieved");
     rw7cell9.setCellValue(""); 
      rw7cell1.setCellStyle(style);
      rw7cell2.setCellStyle(style);
      rw7cell3.setCellStyle(style);
      rw7cell4.setCellStyle(style);
      rw7cell5.setCellStyle(style);
      rw7cell6.setCellStyle(style);
      rw7cell7.setCellStyle(style);
      rw7cell8.setCellStyle(style);
      rw7cell9.setCellStyle(style);
      rw7cell21.setCellStyle(style);
      rw7cell22.setCellStyle(style);
      rw7cell23.setCellStyle(style);
      rw7cell24.setCellStyle(style);
      rw7cell25.setCellStyle(style);
      rw7cell26.setCellStyle(style);
      rw7cell27.setCellStyle(style);
      rw7cell28.setCellStyle(style);
      rw7cell29.setCellStyle(style);
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,9));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,10,11));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
      
//      end of sheet 1
      
      
      
     for (int i=0;i<counties.size();i++){
         district_counter=district_counter_yr=district_counter_tar=district_counter_prev=district_counter_prior=0;
         quartely_targets=0;
     county_name=counties.get(i).toString();
     county_id=0;
     county_id=Integer.parseInt(county_id_array.get(i).toString());
     
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
while (conn.rs1.next()){
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
 previous_quarter=Integer.parseInt(splted_quarter)-1;
 prior_quarter=previous_quarter-1;
int year_agg=previous_quarter+1;
         while(year_agg>=1){
             
             
             String qr="Q"+year_agg;
             
             if((indicator_id.equals("47") || indicator_id.equals("49")||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53")) && quarter.equals("Q3")){
             String achieved_selector_this_year="SELECT SUM(totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='"+year+"' && reportingPeriod='Q6' && titleID='"+indicator_id+"' GROUP BY county ";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year=conn.rs1.getInt(1);
     //achieved_this_year_total+=conn.rs1.getInt(2);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }
             
        String achieved_selector_this_year1="SELECT sum(totalAchieved) FROM indicatorachievedcombined WHERE  financialYear='"+year+"' && reportingPeriod='Q6' && titleID='"+indicator_id+"' group by reportingPeriod";  
 conn.rs4=conn.state4.executeQuery(achieved_selector_this_year1);
if (conn.rs4.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     achieved_this_year_total=conn.rs4.getInt(1);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }     
             } 
           else if((indicator_id.equals("47") || indicator_id.equals("49")||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53")) && quarter.equals("Q1")){
            
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year=achieved_this_period;
     
     
     achieved_this_year_total=achieved_this_period_total;
     
       
             } 
             // if q4 achieved this year is q7, if q3 achieved this year is q6
            else if((indicator_id.equals("47") || indicator_id.equals("49")||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53") )&& quarter.equals("Q4")){
             
                if(year==2014){
                String achieved_selector_this_year="SELECT SUM(totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='2014' && reportingPeriod='Q7' && titleID='"+indicator_id+"' GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year=conn.rs1.getInt(1);
     //achieved_this_year_total+=conn.rs1.getInt(2);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }
             
        String achieved_selector_this_year1="SELECT sum(totalAchieved) FROM indicatorachievedcombined WHERE  financialYear='2014' && reportingPeriod='Q7' && titleID='"+indicator_id+"'";  
 conn.rs4=conn.state4.executeQuery(achieved_selector_this_year1);
if (conn.rs4.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     achieved_this_year_total=conn.rs4.getInt(1);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }    
                }//end of 2014
                
                
   //------------------2015--------------------------
     
  if(year==2015){
  
  String achieved_selector_this_year="SELECT SUM(totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='"+year+"' && reportingPeriod='Q10' && titleID='"+indicator_id+"' GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year=conn.rs1.getInt(1);
     
     }
             
        String achieved_selector_this_year1="SELECT sum(totalAchieved) FROM indicatorachievedcombined WHERE  financialYear='"+year+"' && reportingPeriod='Q10' && titleID='"+indicator_id+"' GROUP BY reportingPeriod";  
 conn.rs4=conn.state4.executeQuery(achieved_selector_this_year1);
if (conn.rs4.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     achieved_this_year_total=conn.rs4.getInt(1);

     }   

  }//end of 2015	 
             
                
                
                
             } 
             
             
            else if((indicator_id.equals("47") || indicator_id.equals("49")||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53") )&& quarter.equals("Q1")){
             String achieved_selector_this_year="SELECT SUM(totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='2015' && reportingPeriod='Q8' && titleID='"+indicator_id+"' GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year=conn.rs1.getInt(1);
     //achieved_this_year_total+=conn.rs1.getInt(2);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }
             
        String achieved_selector_this_year1="SELECT sum(totalAchieved) FROM indicatorachievedcombined WHERE  financialYear='2015' && reportingPeriod='Q8' && titleID='"+indicator_id+"'";  
 conn.rs4=conn.state4.executeQuery(achieved_selector_this_year1);
if (conn.rs4.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     achieved_this_year_total=conn.rs4.getInt(1);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }     
             } 
             
    else if((indicator_id.equals("47") || indicator_id.equals("49")||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53") )&& quarter.equals("Q2") && year==2015){
             String achieved_selector_this_year="SELECT SUM(totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='2015' && reportingPeriod='Q9' && titleID='"+indicator_id+"' GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year=conn.rs1.getInt(1);
     //achieved_this_year_total+=conn.rs1.getInt(2);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }
             
        String achieved_selector_this_year1="SELECT sum(totalAchieved) FROM indicatorachievedcombined WHERE  financialYear='2015' && reportingPeriod='Q9' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs4=conn.state4.executeQuery(achieved_selector_this_year1);
if (conn.rs4.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     achieved_this_year_total=conn.rs4.getInt(1);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }     
             }             
             else {
 String achieved_selector_this_year="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"')&& financialYear='"+year+"' && reportingPeriod='"+qr+"' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_year+=conn.rs1.getInt(1);
     achieved_this_year_total+=conn.rs1.getInt(1);
     
     
     //System.out.println("sssss"+achieved_this_year +"___"+achieved_this_year_total);
     }}
 year_agg--;
         }
         
         
         
         
        // get data for the previous quarter;
         if(quarter.equals("Q2")){
             
             String cust_q="Q"+previous_quarter;
             
              String count_dist_prev="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' &&  reportingPeriod = 'Q1' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs2=conn.state2.executeQuery(count_dist_prev);
 
 System.out.println("^^^^^^"+count_dist_prev);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    district_counter_prev=conn.rs2.getInt(1);
    overall_district_counter_prev+=conn.rs2.getInt(1);
    
//    System.out.println("^^^^^^^^^"+overall_district_counter_prev);
     }   
   String prev_selector="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='Q1' && titleID='"+indicator_id+"'  GROUP BY county";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved=conn.rs1.getInt(1);
    previous_quarter_achieved_total+= previous_quarter_achieved;
  }
         }
        else if(quarter.equals("Q3")){
             
//             String cust_q="Q"+previous_quarter;
             
              String count_dist_prev="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' &&  reportingPeriod = 'Q2' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs2=conn.state2.executeQuery(count_dist_prev);
 
 System.out.println("^^^^^^"+count_dist_prev);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    district_counter_prev=conn.rs2.getInt(1);
    overall_district_counter_prev+=conn.rs2.getInt(1);
    
//    System.out.println("^^^^^^^^^"+overall_district_counter_prev);
     }   
   String prev_selector="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='Q2' && titleID='"+indicator_id+"'  GROUP BY county";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved=conn.rs1.getInt(1);
    previous_quarter_achieved_total+= previous_quarter_achieved;
  }
         }
         
        else if(quarter.equals("Q1")){
                 String cust_q="Q4";
                   int cust_y=year-1;
              String count_dist_prev="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+cust_y+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'";  
 conn.rs2=conn.state2.executeQuery(count_dist_prev);
 System.out.println("*****"+count_dist_prev);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    district_counter_prev=conn.rs2.getInt(1);
   overall_district_counter_prev+=conn.rs2.getInt(1); 
   //System.out.println(overall_district_counter_prev +"indicators"+district_counter_prev +"ddd"+indicator_id);
     }   
          
          
   String prev_selector="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+cust_y+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved=conn.rs1.getInt(1);  
     previous_quarter_achieved_total+= previous_quarter_achieved;
  }
         }
         else if(quarter.equals("Q4")){
         
             String cust_q="Q3";
              String count_dist_prev="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='Q3' && titleID='"+indicator_id+"'";  
 conn.rs2=conn.state2.executeQuery(count_dist_prev);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    district_counter_prev=conn.rs2.getInt(1);
   overall_district_counter_prev+=conn.rs2.getInt(1); 
     }   
          
//            int cust_y=year-1;
   String prev_selector="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='Q3' && titleID='"+indicator_id+"'";
  conn.rs1=conn.state1.executeQuery(prev_selector);
  if(conn.rs1.next()==true){
    previous_quarter_achieved=conn.rs1.getInt(1);  
     previous_quarter_achieved_total+= previous_quarter_achieved;
  }
         
         
         
         
         }
         
//     ^^^^^^^^^^^ACHIEVED PRIOR PERIODS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

           
//     LOOP FOR COUNTIES
  
     achieved_prior=0;
     previous_quarter=Integer.parseInt(splted_quarter)-1;
     prior_quarter=previous_quarter-1;
     max_year_combined=year;
     if(previous_quarter==0){
             max_year_combined--;
             previous_quarter=4;
			 prior_quarter=previous_quarter-1;
               System.out.println(" prev b   :  "+max_year_combined);
                System.out.println(" prev   :  "+previous_quarter);
         }
      
//     LOOP FOR YEARS   
     for(int j=max_year_combined;j>=min_year_combined;j--){
//    LOOP FOR QUARTERS

         int k=previous_quarter;
         while(k>=1){
     String custom_quarter="Q"+k; 
     System.out.println("The quarter is :  "+custom_quarter);
     
    
     String count_dist_prior="SELECT count(resultID) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+j+"' && reportingPeriod='"+custom_quarter+"' && titleID='"+indicator_id+"'  GROUP BY county";  
System.out.println("&&&"+count_dist_prior);
     
     conn.rs2=conn.state2.executeQuery(count_dist_prior);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    district_counter_prior=conn.rs2.getInt(1);
   overall_district_counter_prior+=conn.rs2.getInt(1); 
    district_counter_priors=conn.rs2.getInt(1);
   overall_district_counter_priors+=conn.rs2.getInt(1); 
   
   System.out.println("*****"+district_counter_prior +"___"+overall_district_counter_prior);
    System.out.println("*****1"+district_counter_priors +"___"+overall_district_counter_priors);
     }   
System.out.println("***"+indicator_id);
String ind[]= new String[]{"47","49","46","55","35"};


if((indicator_id.equals("47") || indicator_id.equals("49")
        ||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53") )
        && quarter.equals("Q3")){
//       achieved_prior=0;     
//         achieved_prior_total=0;
         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='Q9' && financialYear='2015' && titleID='"+indicator_id+"'  GROUP BY county";  
      
         System.out.println("***"+get_total_prior);
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior=conn.rs2.getInt(1);     
//        achieved_prior_total+= achieved_prior;   
       } 
       
         String get_total_prior1="SELECT SUM(indicatorachievedcombined.totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE reportingPeriod='Q9' && financialYear='2015' && titleID='"+indicator_id+"'  GROUP BY reportingPeriod"; 
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
      achieved_prior_total=conn.rs3.getInt(1);     
          
       } 
       System.out.println(achieved_prior +"&&&&"+ achieved_prior_total);
       
         System.out.println("inloop");

}// for prior achieved i.e Q5 FOR Q3 AND Q6 FOR Q4 q7 for q1 2015
else if((indicator_id.equals("47") || indicator_id.equals("49")
        ||indicator_id.equals("46")||indicator_id.equals("55")||indicator_id.equals("53") )
        && quarter.equals("Q4")){
//       achieved_prior=0;     
//         achieved_prior_total=0;
    if(year==2014){
         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='Q6' && financialYear='2014' && titleID='"+indicator_id+"'  GROUP BY county";  
      
         System.out.println("***"+get_total_prior);
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior=conn.rs2.getInt(1);     
//        achieved_prior_total+= achieved_prior;   
       } 
       
         String get_total_prior1="SELECT SUM(indicatorachievedcombined.totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE reportingPeriod='Q6' && financialYear='2014' && titleID='"+indicator_id+"'  GROUP BY county"; 
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
      achieved_prior_total=conn.rs3.getInt(1);     
          
       }
}
  	
		//========================Q42015===================
		if(year==2015){
		
         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='Q6' && financialYear='2015' && titleID='"+indicator_id+"' GROUP BY county";  
      
         System.out.println("***"+get_total_prior);
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior=conn.rs2.getInt(1);     
       } 
       
         String get_total_prior1="SELECT SUM(indicatorachievedcombined.totalAchieved),totalAchieved FROM indicatorachievedcombined WHERE reportingPeriod='Q6' && financialYear='2015' && titleID='"+indicator_id+"' group by reportingPeriod"; 
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
      achieved_prior_total=conn.rs3.getInt(1);     
          
       } 
		
		}   

}
else if((indicator_id.equals("47")
        || indicator_id.equals("49") ||indicator_id.equals("46")||indicator_id.equals("55")
        ||indicator_id.equals("53") ) && quarter.equals("Q1") && year==2015 ){
//       achieved_prior=0;     
//         achieved_prior_total=0;
         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='Q7' && financialYear='2014' && titleID='"+indicator_id+"' GROUP BY county";  
      
         System.out.println("***"+get_total_prior);
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior=conn.rs2.getInt(1);     
//        achieved_prior_total+= achieved_prior;   
       } 
       
         String get_total_prior1="SELECT SUM(indicatorachievedcombined.totalAchieved) FROM indicatorachievedcombined WHERE reportingPeriod='Q7' && financialYear='2014' && titleID='"+indicator_id+"'"; 
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
      achieved_prior_total=conn.rs3.getInt(1);     
          
       } 
       System.out.println(achieved_prior +"&&&&"+ achieved_prior_total);
       
         System.out.println("inloop");

} 
// for quarter prior periods is Q1 OF THE SAME YEAR 
else if((indicator_id.equals("47")
        || indicator_id.equals("49") ||indicator_id.equals("46")||indicator_id.equals("55")
        ||indicator_id.equals("53") ) && quarter.equals("Q2") && year>=2015 ){

         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='Q1' && financialYear='"+year+"' && titleID='"+indicator_id+"' GROUP BY county";  
       conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior=conn.rs2.getInt(1);     

       } 
       
         String get_total_prior1="SELECT SUM(indicatorachievedcombined.totalAchieved) FROM indicatorachievedcombined WHERE reportingPeriod='Q1' && financialYear='"+year+"' && titleID='"+indicator_id+"'  GROUP BY county"; 
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
      achieved_prior_total=conn.rs3.getInt(1);     
          
       } 
       System.out.println(achieved_prior +"&&&&"+ achieved_prior_total);
       
         System.out.println("inloop");

}

    
else{
         String get_total_prior="SELECT SUM(indicatorachievedcombined.totalAchieved) FROM indicatorachievedcombined WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+custom_quarter+"' && financialYear='"+j+"' && titleID='"+indicator_id+"'  GROUP BY county";  
      
         System.out.println("pppp"+get_total_prior);
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior+=conn.rs2.getInt(1);     
         achieved_prior_total+= conn.rs2.getInt(1);
         
         
        System.out.println(achieved_prior +"|||"+ achieved_prior_total);
       }} 


       k--;
       if(k==0){
     previous_quarter=4;
	 
     break;
     }
     }    
//    System.out.println("The year is :  "+j);
     }
// System.out.println(" COUNTY NAME  :  "+county_name+"  PRIOR ACHIEVED  :  "+achieved_prior);
//if(achieved_prior>0 || achieved_this_period>0 || quartely_targets>0){
//DISPLAY THE RESULTS
    aa+=1;
    aa3+=1;
     int yearly_targets=0;
     
//     for sheet 1
 HSSFRow rw8=shet1.createRow(aa);
       rw8.setHeightInPoints(20);
      HSSFCell rw8cell1=rw8.createCell(1);
      HSSFCell rw8cell2=rw8.createCell(2);
      HSSFCell rw8cell3=rw8.createCell(4);
      HSSFCell rw8cell4=rw8.createCell(6);
      HSSFCell rw8cell6=rw8.createCell(10);
      HSSFCell rw8cell8=rw8.createCell(14);
      HSSFCell rw8cell5=rw8.createCell(8);
      HSSFCell rw8cell7=rw8.createCell(12);
      HSSFCell rw8cell9=rw8.createCell(16);
//      HSSFCell rw8cell21=rw8.createCell(1);
      HSSFCell rw8cell22=rw8.createCell(3);
      HSSFCell rw8cell23=rw8.createCell(5);
      HSSFCell rw8cell24=rw8.createCell(7);
      HSSFCell rw8cell26=rw8.createCell(11);
      HSSFCell rw8cell28=rw8.createCell(13);
      HSSFCell rw8cell25=rw8.createCell(9);
      HSSFCell rw8cell27=rw8.createCell(15);
      HSSFCell rw8cell29=rw8.createCell(17);
     rw8cell1.setCellValue(county_name);
     rw8cell2.setCellValue("");
     
     
     if(achieved_prior==0){
     rw8cell3.setCellValue("");    
      
     }
     
 if(indicator_id.equals("5")||indicator_id.equals("7")||indicator_id.equals("9")||indicator_id.equals("13")||indicator_id.equals("16")||indicator_id.equals("26")||indicator_id.equals("57") )
{
 rw8cell3.setCellValue(achieved_this_period);    
  


}
 else{
 if(achieved_prior>0){
     rw8cell3.setCellValue(achieved_prior);    
    
     }
 }
      if(previous_quarter_achieved==0){
     rw8cell4.setCellValue("");    
    
     }
     if(previous_quarter_achieved>0){
     rw8cell4.setCellValue(previous_quarter_achieved);    
  
     }
 //rw8cell3.setCellValue(achieved_prior +"KKK");
//     rw8cell4.setCellValue(previous_quarter_achieved);
     
     
   
      if (percentages==0){
         if(quartely_targets==0){
     rw8cell5.setCellValue("");    
     
     }
     if(quartely_targets>0){
     rw8cell5.setCellValue(quartely_targets);    
 
     } 
//     rw8cell5.setCellValue(quartely_targets); 
     if(achieved_this_period==0){
     rw8cell6.setCellValue("");    
     
     }
     if(achieved_this_period>0){
     rw8cell6.setCellValue(achieved_this_period);    
     
     }
//     rw8cell6.setCellValue(achieved_this_period);
       String achieved_target_this_year="SELECT SUM(target_combined) FROM yearly_targets WHERE county_id='"+county_id+"' && year='"+year+"' && indicator_id='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_target_this_year);
if (conn.rs1.next()==true){
//    TARGETS PER COUNTY TOTALS.............................     
    yearly_targets=conn.rs1.getInt(1);
    total_yearly_targets+=conn.rs1.getInt(1);
    
     }


   
   
     if(yearly_targets==0) 
    {
     rw8cell7.setCellValue("");
   
    }
    else
    {
     rw8cell7.setCellValue(yearly_targets);
 
    }
    
     
     
     
     
     
     
      if(achieved_this_year==0){
     rw8cell8.setCellValue("");    
     
     }
     if(achieved_this_year>0){
         if(indicator_id.equals("5")||indicator_id.equals("7")||indicator_id.equals("9")||
                 indicator_id.equals("16")||indicator_id.equals("20")||indicator_id.equals("22")||
                 indicator_id.equals("26")||indicator_id.equals("57")) {
      rw8cell8.setCellValue(achieved_this_period);    
   
    }else{
     rw8cell8.setCellValue(achieved_this_year);    
  
         } }
//     rw8cell8.setCellValue(achieved_this_year);
      }
      if (percentages==1){
           System.out.println("|||"+district_counter_prev + "indicators" + indicator_id);
          if(district_counter_tar>0){
     rw8cell5.setCellValue((quartely_targets/district_counter_tar)+"%");
   
//     System.out.println("total districts  :  "+district_counter_tar);
          }
           if(district_counter_tar==0){
     rw8cell5.setCellValue("");

//     System.out.println("total districts  :  "+district_counter_tar);
          }
		  
		   if(quarter.equals("Q1")){
               avgqtr=1;
           }
           else if(quarter.equals("Q2")){
                avgqtr=2;
           }
           else if(quarter.equals("Q3")){
                avgqtr=3;
           }
           else if(quarter.equals("Q4")){
                avgqtr=4;
           }
          
          if(district_counter>0){
     rw8cell6.setCellValue((achieved_this_period/district_counter)+"%");
      rw8cell7.setCellValue(yearly_targets+"%");
     rw8cell8.setCellValue(((achieved_this_year/district_counter_yr)/avgqtr)+"%");
     
    
      }
          
          
           String achieved_target_this_year="SELECT SUM(target_combined) FROM yearly_targets WHERE county_id='"+county_id+"' && year='"+year+"' && indicator_id='"+indicator_id+"'";  
 conn.rs1=conn.state1.executeQuery(achieved_target_this_year);
if (conn.rs1.next()==true){
//    TARGETS PER COUNTY TOTALS.............................     
    yearly_targets=conn.rs1.getInt(1);
    total_yearly_targets+=conn.rs1.getInt(1);
    
     }
     
    if(yearly_targets==0) 
    {
     rw8cell7.setCellValue("");
 
    }
    else
    {
     rw8cell7.setCellValue(yearly_targets+"%");
  
    }
     
          
          
      if(district_counter==0){
     rw8cell6.setCellValue("");
     rw8cell7.setCellValue(yearly_targets+"%");
     rw8cell8.setCellValue("");

      }
      System.out.println(district_counter_prev + "indicators" + indicator_id +"}}}"+overall_district_counter_prev +"____"+achieved_prior +"     to check      "+district_counter_prior);
      if(district_counter_prev>0){
            rw8cell4.setCellValue(previous_quarter_achieved/district_counter_prev+"%");    
          
      }
      if(district_counter_prev==0){
       rw8cell4.setCellValue("");
     
      }
     
      System.out.println(achieved_prior +"     to check      "+district_counter_prior +"  "+overall_district_counter_prior);
      if(overall_district_counter_prior>0){
           rw8cell3.setCellValue(achieved_prior/overall_district_counter_prior+"%");
      
      }
      
      if(overall_district_counter_prior==0){
      rw8cell3.setCellValue("no");
    
      }
       }
      
      //sheet1
     rw8cell9.setCellValue("");     
    shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,9));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,10,11));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
      rw8cell1.setCellStyle(style_border);
      rw8cell2.setCellStyle(style_border);
      rw8cell3.setCellStyle(style_border);
      rw8cell4.setCellStyle(style_border);
      rw8cell5.setCellStyle(style_border);
      rw8cell6.setCellStyle(style_border);
      rw8cell7.setCellStyle(style_border);
      rw8cell8.setCellStyle(style_border);
      rw8cell9.setCellStyle(style_border);
//      rw8cell21.setCellStyle(style);
      rw8cell22.setCellStyle(style_border);
      rw8cell23.setCellStyle(style_border);
      rw8cell24.setCellStyle(style_border);
      rw8cell25.setCellStyle(style_border);
      rw8cell26.setCellStyle(style_border);
      rw8cell27.setCellStyle(style_border);
      rw8cell28.setCellStyle(style_border);
      rw8cell29.setCellStyle(style_border);
      
      
     

 previous_quarter_achieved=achieved_prior=achieved_this_year=achieved_this_period=previous_quarter=prior_quarter=0; 
 district_counter_tar=district_counter=0;
  }
//     total for all counties achieved
     if(achieved_prior_total>0 || achieved_this_year_total>0 || achieved_this_period_total>0 || total_quartely_targets>0){
     aa+=1;
   
 HSSFRow rw9=shet1.createRow(aa);
       rw9.setHeightInPoints(22);
      HSSFCell rw9cell1=rw9.createCell(1);
      HSSFCell rw9cell2=rw9.createCell(2);
      HSSFCell rw9cell3=rw9.createCell(4);
      HSSFCell rw9cell4=rw9.createCell(6);
      HSSFCell rw9cell6=rw9.createCell(10);
      HSSFCell rw9cell8=rw9.createCell(14);
      HSSFCell rw9cell5=rw9.createCell(8);
      HSSFCell rw9cell7=rw9.createCell(12);
      HSSFCell rw9cell9=rw9.createCell(16);
//      HSSFCell rw9cell21=rw9.createCell(1);
      HSSFCell rw9cell22=rw9.createCell(3);
      HSSFCell rw9cell23=rw9.createCell(5);
      HSSFCell rw9cell24=rw9.createCell(7);
      HSSFCell rw9cell26=rw9.createCell(11);
      HSSFCell rw9cell28=rw9.createCell(15);
      HSSFCell rw9cell25=rw9.createCell(9);
      HSSFCell rw9cell27=rw9.createCell(13);
      HSSFCell rw9cell29=rw9.createCell(17);
     
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,9));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,10,11));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
      rw9cell1.setCellValue("Totals:");
     rw9cell2.setCellValue("");
     if(indicator_id.equals("5")||indicator_id.equals("7")||indicator_id.equals("9")||indicator_id.equals("13")||indicator_id.equals("16")||indicator_id.equals("26")||indicator_id.equals("57") )
{
 rw9cell3.setCellValue(achieved_this_period_total);    
   


} else{
     rw9cell3.setCellValue(achieved_prior_total);}
     rw9cell4.setCellValue(previous_quarter_achieved_total);
     
    
     if (percents==0){
     rw9cell5.setCellValue(total_quartely_targets);
     rw9cell6.setCellValue(achieved_this_period_total);
     rw9cell7.setCellValue(total_yearly_targets);
     rw9cell8.setCellValue(achieved_this_year_total);
       if(indicator_id.equals("5")||indicator_id.equals("7")||indicator_id.equals("9")||indicator_id.equals("16")||indicator_id.equals("20")||indicator_id.equals("22")||
               indicator_id.equals("26")||indicator_id.equals("57")) {
           rw9cell8.setCellValue(achieved_this_period_total);
         
       }
           else{   rw9cell8.setCellValue(achieved_this_year_total);
       } 
 
     
    
     }
     if (percents==1){
         if(overall_district_counter_tar>0){
     rw9cell5.setCellValue((total_quartely_targets/overall_district_counter_tar)+"%");
    
         }
         if(overall_districts>0){
     rw9cell6.setCellValue((achieved_this_period_total/overall_districts)+"%");
  
         
//     rw9cell7.setCellValue((total_yearly_targets/overall_districts_yr)+"%");
     rw9cell8.setCellValue(((achieved_this_year_total/overall_districts_yr)/avgqtr)+"%");
    
     }
      rw9cell7.setCellValue((total_yearly_targets/5)+"%");
    
      System.out.println(overall_district_counter_prev);
//     if(overall_district_counter_prev>0){
//          rw9cell4.setCellValue("aaa"+previous_quarter_achieved_total/overall_district_counter_prev+"%");
//          rw9cell4_3.setCellValue("aaa"+previous_quarter_achieved_total/overall_district_counter_prev+"%");
//     
//     }
     
      if(overall_district_counter_prev>0){
          
           rw9cell4.setCellValue(previous_quarter_achieved_total/overall_district_counter_prev+"%");
        
		  }
		   if(overall_district_counter_prior>0){
      rw9cell3.setCellValue(achieved_prior_total/overall_district_counter_prior+"%");
   
              }
      
     
     }
     rw9cell9.setCellValue("");
     rw9cell1.setCellStyle(stylex);
     rw9cell2.setCellStyle(stylex);
     rw9cell3.setCellStyle(stylex);
     rw9cell4.setCellStyle(stylex);
     rw9cell5.setCellStyle(stylex);
     rw9cell6.setCellStyle(stylex);
     rw9cell7.setCellStyle(stylex);
     rw9cell8.setCellStyle(stylex);
     rw9cell9.setCellStyle(stylex);
//     rw9cell21.setCellStyle(stylex);
     rw9cell22.setCellStyle(stylex);
     rw9cell23.setCellStyle(stylex);
     rw9cell24.setCellStyle(stylex);
     rw9cell25.setCellStyle(stylex);
     rw9cell26.setCellStyle(stylex);
     rw9cell27.setCellStyle(stylex);
     rw9cell28.setCellStyle(stylex);
     rw9cell29.setCellStyle(stylex);
     
     
    

    }
     total_yearly_targets= overall_districts=overall_districts_yr=previous_quarter_achieved_total=achieved_prior_total=achieved_this_year_total=achieved_this_period_total=total_quartely_targets=0;
  district_counter_yr=district_counter=overall_district_counter_tar=overall_district_counter_prior=overall_district_counter_prev=0;
      aa+=2;
      aa3+=2;
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
     aa3+=1;
  HSSFRow rw6=shet1.createRow(aa);
       rw6.setHeightInPoints(43);
      HSSFCell rw6cell1=rw6.createCell(1);
      HSSFCell rw6cell2=rw6.createCell(2);
      HSSFCell rw6cell3=rw6.createCell(4);
      HSSFCell rw6cell4=rw6.createCell(6);
      HSSFCell rw6cell5=rw6.createCell(8);
      HSSFCell rw6cell7=rw6.createCell(12);
      HSSFCell rw6cell9=rw6.createCell(16);
      HSSFCell rw6cell21=rw6.createCell(10);
      HSSFCell rw6cell22=rw6.createCell(3);
      HSSFCell rw6cell23=rw6.createCell(5);
      HSSFCell rw6cell24=rw6.createCell(7);
      HSSFCell rw6cell25=rw6.createCell(9);
      HSSFCell rw6cell27=rw6.createCell(13);
      HSSFCell rw6cell29=rw6.createCell(17);
       HSSFCell rw6cell32=rw6.createCell(11);
       HSSFCell rw6cell290=rw6.createCell(14);
       HSSFCell rw6cell320=rw6.createCell(15);
     rw6cell1.setCellValue("County Name");
     rw6cell2.setCellValue("Baseline");
     rw6cell3.setCellValue("Achieved Prior Periods");
     rw6cell4.setCellValue("Previous Quarter ("+previous_period+" "+Display_year_previous+" )");
     rw6cell5.setCellValue("This Reporting Period ("+period+" "+Display_year+" )");
     rw6cell7.setCellValue("PEPFAR Year ("+year+" )");
     rw6cell9.setCellValue("End Of Project Target");
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,11));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,15));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
     
      rw6cell1.setCellStyle(style_header);
      rw6cell2.setCellStyle(style_header);
      rw6cell3.setCellStyle(style_header);
      rw6cell4.setCellStyle(style_header);
      rw6cell5.setCellStyle(style_header);
      rw6cell7.setCellStyle(style_header);
      rw6cell9.setCellStyle(style_header);
      rw6cell21.setCellStyle(style_header);
      rw6cell22.setCellStyle(style_header);
      rw6cell23.setCellStyle(style_header);
      rw6cell24.setCellStyle(style_header);
      rw6cell25.setCellStyle(style_header);
      rw6cell27.setCellStyle(style_header);
      rw6cell29.setCellStyle(style_header);
      rw6cell32.setCellStyle(style_header);
      rw6cell290.setCellStyle(style_header);
      rw6cell320.setCellStyle(style_header);
      //sheet 1
      
   
//sheet 3
     aa+=1;

  HSSFRow rw7=shet1.createRow(aa);
       rw7.setHeightInPoints(20);
      HSSFCell rw7cell1=rw7.createCell(1);
      HSSFCell rw7cell2=rw7.createCell(2);
      HSSFCell rw7cell3=rw7.createCell(4);
      HSSFCell rw7cell4=rw7.createCell(6);
      HSSFCell rw7cell6=rw7.createCell(10);
      HSSFCell rw7cell8=rw7.createCell(14);
      HSSFCell rw7cell5=rw7.createCell(8);
      HSSFCell rw7cell7=rw7.createCell(12);
      HSSFCell rw7cell9=rw7.createCell(16);
      HSSFCell rw7cell21=rw7.createCell(3);
      HSSFCell rw7cell22=rw7.createCell(5);
      HSSFCell rw7cell23=rw7.createCell(7);
      HSSFCell rw7cell24=rw7.createCell(15);
      HSSFCell rw7cell26=rw7.createCell(9);
      HSSFCell rw7cell28=rw7.createCell(11);
      HSSFCell rw7cell25=rw7.createCell(13);
      HSSFCell rw7cell27=rw7.createCell(12);
      HSSFCell rw7cell29=rw7.createCell(17);
     rw7cell1.setCellValue("");
     rw7cell2.setCellValue("");
     rw7cell3.setCellValue("Achieved");
     rw7cell4.setCellValue("Achieved");
     rw7cell5.setCellValue("Target");
     rw7cell6.setCellValue("Achieved");
     rw7cell7.setCellValue("Target");
     rw7cell8.setCellValue("Achieved");
     rw7cell9.setCellValue("Target"); 
      rw7cell1.setCellStyle(style);
      rw7cell2.setCellStyle(style);
      rw7cell3.setCellStyle(style);
      rw7cell4.setCellStyle(style);
      rw7cell5.setCellStyle(style);
      rw7cell6.setCellStyle(style);
      rw7cell7.setCellStyle(style);
      rw7cell8.setCellStyle(style);
      rw7cell9.setCellStyle(style);
      rw7cell21.setCellStyle(style);
      rw7cell22.setCellStyle(style);
      rw7cell23.setCellStyle(style);
      rw7cell24.setCellStyle(style);
      rw7cell25.setCellStyle(style);
      rw7cell26.setCellStyle(style);
      rw7cell27.setCellStyle(style);
      rw7cell28.setCellStyle(style);
      rw7cell29.setCellStyle(style);
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,2,3));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,4,5));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,6,7));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,8,9));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,10,11));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,12,13));
      shet1.addMergedRegion(new CellRangeAddress(aa,aa,14,15));
     shet1.addMergedRegion(new CellRangeAddress(aa,aa,16,17));
    
    
      aa+=1;
      
      
      
      //sheet 1
      HSSFRow rwq=shet1.createRow(aa);
       rwq.setHeightInPoints(20);
      HSSFCell rwqcell1=rwq.createCell(1);
      HSSFCell rwqcell2=rwq.createCell(2);
      HSSFCell rwqcell3=rwq.createCell(3);
      HSSFCell rwqcell4=rwq.createCell(4);
      HSSFCell rwqcell6=rwq.createCell(6);
      HSSFCell rwqcell8=rwq.createCell(8);
      HSSFCell rwqcell5=rwq.createCell(5);
      HSSFCell rwqcell7=rwq.createCell(7);
      HSSFCell rwqcell9=rwq.createCell(9);
      HSSFCell rwqcell10=rwq.createCell(10);
      HSSFCell rwqcell11=rwq.createCell(11);
      HSSFCell rwqcell12=rwq.createCell(12);
      HSSFCell rwqcell13=rwq.createCell(13);
      HSSFCell rwqcell14=rwq.createCell(14);
      HSSFCell rwqcell15=rwq.createCell(15);
      HSSFCell rwqcell16=rwq.createCell(16);
      HSSFCell rwqcell17=rwq.createCell(17);
     rwqcell1.setCellValue("");
     rwqcell2.setCellValue("W");
     rwqcell3.setCellValue("M");
     rwqcell4.setCellValue("W");
     rwqcell5.setCellValue("M");
     rwqcell6.setCellValue("W");
     rwqcell7.setCellValue("M");
     rwqcell8.setCellValue("W");
     rwqcell9.setCellValue("M"); 
     rwqcell10.setCellValue("W");
     rwqcell11.setCellValue("M");
     rwqcell12.setCellValue("W");
     rwqcell13.setCellValue("M");
     rwqcell14.setCellValue("W");
     rwqcell15.setCellValue("M");
     rwqcell16.setCellValue("W");
     rwqcell17.setCellValue("M");
      rwqcell1.setCellStyle(style);
      rwqcell2.setCellStyle(style);
      rwqcell3.setCellStyle(style);
      rwqcell4.setCellStyle(style);
      rwqcell5.setCellStyle(style);
      rwqcell6.setCellStyle(style);
      rwqcell7.setCellStyle(style);
      rwqcell8.setCellStyle(style);
      rwqcell9.setCellStyle(style);
      rwqcell10.setCellStyle(style);
      rwqcell11.setCellStyle(style);
      rwqcell12.setCellStyle(style);
      rwqcell13.setCellStyle(style);
      rwqcell14.setCellStyle(style);
      rwqcell15.setCellStyle(style);
      rwqcell16.setCellStyle(style);
      rwqcell17.setCellStyle(style);
      
     for (int i=0;i<counties.size();i++){
         quarterly_target_men=quarterly_target_women=total_quarterly_target_women=total_quarterly_target_men=0;
     county_name=counties.get(i).toString();
     county_id=district_counter=district_counter_yr=0;
     county_id=Integer.parseInt(county_id_array.get(i).toString());
     
       String count_dist="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+quarter+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'  GROUP BY county";  
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
   String count_dist_yr="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(count_dist_yr);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_yr=conn.rs1.getInt(1);
     overall_districts_yr+=conn.rs1.getInt(1);
     } 
  
     
   String achieved_selector_this_period="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+quarter+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'  GROUP BY county ";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_period);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     achieved_this_period_m=conn.rs1.getInt(1);
     achieved_this_period_total_m+=conn.rs1.getInt(1);
     achieved_this_period_f=conn.rs1.getInt(2);
     achieved_this_period_total_f+=conn.rs1.getInt(2);
    
    
     }

 previous_quarter=Integer.parseInt(splted_quarter)-1;
  prior_quarter=previous_quarter-1;
int year_agg=previous_quarter+1;
         while(year_agg>=1){
             String qr="Q"+year_agg;
            if(indicator_id.equals("54") && quarter.equals("Q3")){
          String achieved_selector_this_year="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && reportingPeriod='Q6' && titleID='"+indicator_id+"' group by county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
 //System.out.println("!!!!!"+achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     System.out.println("$$$"+conn.rs1.getInt(1));
     achieved_this_year_m+=conn.rs1.getInt(1);
     
   
     //achieved_this_year_total_m=conn.rs1.getInt(1);
     
      achieved_this_year_f+=conn.rs1.getInt(2);
      achieved_this_year_totals+=conn.rs1.getInt(3);
    //district_counter_yr1 =conn.rs1.getInt(3);
     
    // achieved_this_year_total_f+=conn.rs1.getInt(2);
	 
	 } String achieved_selector_this_year1="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && reportingPeriod='Q6' && titleID='"+indicator_id+"' group by reportingPeriod";  
 conn.rs3=conn.state3.executeQuery(achieved_selector_this_year1);

if (conn.rs3.next()==true){

    achieved_this_year_total_m=conn.rs3.getInt(1);
    district_counter_yr1 =conn.rs3.getInt(3);
      achieved_this_year_total_f=conn.rs3.getInt(2); 

     
     }
   
       System.out.println(achieved_this_year_m +"$$$"+ achieved_this_year_f);      
             }   
            
            
            
             if(indicator_id.equals("54") && quarter.equals("Q4")  ){
                 
     if(year==2014){
 String achieved_selector_this_year="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2014' && reportingPeriod='Q7' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);

while (conn.rs1.next()){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     System.out.println("$$$"+conn.rs1.getInt(1));
     achieved_this_year_m=conn.rs1.getInt(1);
     
      counterforyear=conn.rs1.getInt(3);

     achieved_this_year_f=conn.rs1.getInt(2);
     }
              String achieved_selector_this_year1="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE financialYear='2014' && reportingPeriod='Q7' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs3=conn.state3.executeQuery(achieved_selector_this_year1);
 //System.out.println("!!!!!"+achieved_selector_this_year);
if (conn.rs3.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
   
    achieved_this_year_total_m=conn.rs3.getInt(1);
    district_counter_yr1 =conn.rs3.getInt(3);
    achieved_this_year_total_f=conn.rs3.getInt(2); 
   
     }
             }//end of 2014
             
     
     //-----------------------------------2015------------
     if(year==2015){ 
 String achieved_selector_this_year="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && reportingPeriod='Q10' && titleID='"+indicator_id+"' group by county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
 //System.out.println("!!!!!"+achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     System.out.println("$$$"+conn.rs1.getInt(1));
     achieved_this_year_m+=conn.rs1.getInt(1);
     
   
     //achieved_this_year_total_m=conn.rs1.getInt(1);
     
      achieved_this_year_f+=conn.rs1.getInt(2);
      achieved_this_year_totals+=conn.rs1.getInt(3);
    //district_counter_yr1 =conn.rs1.getInt(3);
     
    // achieved_this_year_total_f+=conn.rs1.getInt(2);
	 
	 } String achieved_selector_this_year1="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && reportingPeriod='Q10' && titleID='"+indicator_id+"' group by reportingPeriod";  
 conn.rs3=conn.state3.executeQuery(achieved_selector_this_year1);

if (conn.rs3.next()==true){

    achieved_this_year_total_m=conn.rs3.getInt(1);
    district_counter_yr1 =conn.rs3.getInt(3);
      achieved_this_year_total_f=conn.rs3.getInt(2); 

     
     }
	 
	 
}//end of 2015 
     
     
         }
             
             else if (indicator_id.equals("54") && quarter.equals("Q1")  ){
             achieved_this_year_f= achieved_this_period_f;
             achieved_this_year_m= achieved_this_period_m;
             achieved_this_year_total_m= achieved_this_period_total_m;
             achieved_this_year_total_f= achieved_this_period_total_f;
             
             
             
             }
              // for q2 2015 yearly achieved
			 else  if(indicator_id.equals("54") && quarter.equals("Q2") && year==2015){
              String achieved_selector_this_year="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && reportingPeriod='Q9' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
   while (conn.rs1.next()){
    System.out.println("~~~~~~  "+conn.rs1.getInt(1) +" "+conn.rs1.getInt(2));
     achieved_this_year_m=conn.rs1.getInt(1);
    counterforyear=conn.rs1.getInt(3);
    achieved_this_year_f=conn.rs1.getInt(2);
     }
              String achieved_selector_this_year1="SELECT SUM(menAchieved),SUM(womenAchieved),count(*) FROM indicatorachieved WHERE financialYear='2015' && reportingPeriod='Q9' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs3=conn.state3.executeQuery(achieved_selector_this_year1);

if (conn.rs3.next()==true){
achieved_this_year_total_m=conn.rs3.getInt(1);
    district_counter_yr1 =conn.rs3.getInt(3);
      achieved_this_year_total_f=conn.rs3.getInt(2); 
  
     }
             
       System.out.println(district_counter_yr1+"llll"   +counterforyear+"       "+achieved_this_year_m +"!!!"+ achieved_this_year_f +"____"+ achieved_this_year_total_f +" _____"+achieved_this_year_total_m);      
             }
             else{
 String achieved_selector_this_year="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='"+qr+"' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs1=conn.state1.executeQuery(achieved_selector_this_year);
 //System.out.println("!!!!!"+achieved_selector_this_year);
if (conn.rs1.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
    
     //System.out.println("!!!"+conn.rs1.getInt(1));
     achieved_this_year_m+=conn.rs1.getInt(1);
     
     System.out.println(conn.rs1.getInt(1) +"!!!!"+qr +"!!!"+achieved_this_year_m);
     achieved_this_year_total_m+=conn.rs1.getInt(1);
     
      achieved_this_year_f+=conn.rs1.getInt(2);
      
      
       System.out.println("else "+conn.rs1.getInt(1) +"!!!!"+qr +"!!!"+achieved_this_year_f);
     achieved_this_year_total_f+=conn.rs1.getInt(2);
     }}
 year_agg--;
         }
//         get data for the previous quarter;
         if(previous_quarter>=1){
             String cust_q="Q"+previous_quarter;
              String count_dist_prev="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+cust_q+"' && financialYear='"+year+"' && titleID='"+indicator_id+"'  GROUP BY county";  
 conn.rs2=conn.state2.executeQuery(count_dist_prev);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_prev=conn.rs2.getInt(1);
     overall_district_counter_prev+=conn.rs2.getInt(1);
     }
System.out.println(district_counter_prev +"ggggg"+overall_district_counter_prev+"gggg"+indicator_id);
   String prev_selector="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+year+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'  GROUP BY county";
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
            
            String count_dist_prev="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+cust_q+"' && financialYear='"+cust_y+"' && titleID='"+indicator_id+"'  GROUP BY county";  
 
            System.out.println("count_dist_prev   "+count_dist_prev);
            conn.rs2=conn.state2.executeQuery(count_dist_prev);
if (conn.rs2.next()==true){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_prev=conn.rs2.getInt(1);
     overall_district_counter_prev+=conn.rs2.getInt(1);
     }

   String prev_selector="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+cust_y+"' && reportingPeriod='"+cust_q+"' && titleID='"+indicator_id+"'  GROUP BY county";
  
   System.out.println("prev_selector      "+prev_selector);
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
     previous_quarter=Integer.parseInt(splted_quarter)-1;
	  prior_quarter=previous_quarter-1;
     max_year_combined=year;
     if(previous_quarter==0){
             max_year_combined--;
             previous_quarter=4;
			 prior_quarter=previous_quarter-1;
         }
      
//     LOOP FOR YEARS   
     for(int j=max_year_combined;j>=min_year_combined;j--){
//    LOOP FOR QUARTERS

         int k=previous_quarter;
         while(k>=1){
     String custom_quarter="Q"+k; 
    
    
String count_dist_prev="SELECT resultID, count(resultID) from indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+custom_quarter+"' && financialYear='"+j+"' && titleID='"+indicator_id+"' GROUP BY  resultID ";
System.out.println("uuuu"+count_dist_prev);
  conn.rs2=conn.state2.executeQuery(count_dist_prev);
while(conn.rs2.next()){
//    ACHIEVED PER COUNTY TOTALS.............................     
     district_counter_prior=conn.rs2.getInt(2);
     overall_district_counter_prior+=conn.rs2.getInt(2);
     
     }
     System.out.println(indicator_id +"uuuu"+district_counter_prior+"____ "+overall_district_counter_prior);
     
     
     
     
     String achieved_target_this_period="SELECT SUM(target_men),SUM(target_women) FROM quartely_targets WHERE county_id='"+county_id+"' && quarter='"+quarter+"' && year='"+year+"' && indicator_id='"+indicator_id+"' ";  
 conn.rs1=conn.state1.executeQuery(achieved_target_this_period);
if (conn.rs1.next()==true){
//    TARGETS PER COUNTY TOTALS FOR MALE AND FEMALE.............................
//total_quarterly_target_men=total_quarterly_target_women=0;
     quarterly_target_men=conn.rs1.getInt(1);
     total_quarterly_target_men+=conn.rs1.getInt(1);
     quarterly_target_women=conn.rs1.getInt(2);
     total_quarterly_target_women+=conn.rs1.getInt(2);
    

	}
	System.out.println("aaaa bbbb    "+ total_quarterly_target_women );
     
//     System.out.println("The quarter is :  "+custom_quarter);
//
//  String count_dist_prev="SELECT count(resultID) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && (reportingPeriod='Q1' OR reportingPeriod='Q2' OR reportingPeriod='Q3') && financialYear='"+j+"' && titleID='"+indicator_id+"'";  
 
// FOR INDICATOR ID 52 SHELTER AND CARE PECENTAGS IF Q3 PRIOR IS Q5, IF Q4, PRIOR IS Q6
if(indicator_id.equals("54") && quarter.equals("Q3") && year>=2014)  {
  
String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q9'  && (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && titleID='54' GROUP BY county";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       if(conn.rs2.next()==true){
      achieved_prior_m=conn.rs2.getInt(1);     
      
         achieved_prior_f=conn.rs2.getInt(2);     
        
          achieved_prior_f_count=conn.rs2.getInt(3);
}
  String get_total_prior1="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q9' && financialYear='2015' && titleID='54' group by reportingPeriod ";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
          
         achieved_prior_total_m= conn.rs3.getInt(1);
              
         achieved_prior_total_f= conn.rs3.getInt(2);
         totaldistspriors      =conn.rs3.getInt(3);
         
         System.out.println("totals"+ achieved_prior_total_f +"totals"+ achieved_prior_total_m +""+totaldistspriors);

}
}

 if(indicator_id.equals("54") && quarter.equals("Q4") ) {
     
     if(year==2014){
  String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q6'  && (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2014' && titleID='"+indicator_id+"' GROUP BY county";  
      
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       if(conn.rs2.next()==true){
      achieved_prior_m=conn.rs2.getInt(1);     
      
         achieved_prior_f=conn.rs2.getInt(2);     
        
            totaldistsprior=conn.rs2.getInt(3);
}
  String get_total_prior1="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q6' && financialYear='2014' && titleID='"+indicator_id+"'";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
          
         achieved_prior_total_m= conn.rs3.getInt(1);
              
         achieved_prior_total_f= conn.rs3.getInt(2);
         totaldistspriors      =conn.rs3.getInt(3);
         
         System.out.println("totals"+ achieved_prior_total_f +"totals"+ achieved_prior_total_m +""+totaldistspriors);

}
System.out.println(county_name+""+indicator_id+"totaldistsprior"+totaldistsprior +"____"+achieved_prior_f +"____"+achieved_prior_m +"totals"+ achieved_prior_total_f +"totas"+ achieved_prior_total_m);
     }//end of 2014
     
    
     
	if(year==2015){
       String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q6'  && (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2015' && titleID='"+indicator_id+"' GROUP BY county";  
      
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       if(conn.rs2.next()==true){
      achieved_prior_m=conn.rs2.getInt(1);     
      
         achieved_prior_f=conn.rs2.getInt(2);     
        
            totaldistsprior=conn.rs2.getInt(3);
}
  String get_total_prior1="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q6' && financialYear='2015' && titleID='"+indicator_id+"'";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
          
         achieved_prior_total_m= conn.rs3.getInt(1);
              
         achieved_prior_total_f= conn.rs3.getInt(2);
         totaldistspriors      =conn.rs3.getInt(3);
         
         System.out.println("totals"+ achieved_prior_total_f +"totals"+ achieved_prior_total_m +""+totaldistspriors);

}
System.out.println(county_name+""+indicator_id+"totaldistsprior"+totaldistsprior +"____"+achieved_prior_f +"____"+achieved_prior_m +"totals"+ achieved_prior_total_f +"totas"+ achieved_prior_total_m);

	}//end of 2015
     
     
     }

 if(indicator_id.equals("54") && quarter.equals("Q1") && year==2015) {
  String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q7'  && (County='"+county_name+"'|| County='"+county_id+"') && financialYear='2014' && titleID='"+indicator_id+"' GROUP BY county";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior_m=conn.rs2.getInt(1);     
      
         achieved_prior_f=conn.rs2.getInt(2);     
        
            //totaldistsprior=conn.rs2.getInt(3);
}
  String get_total_prior1="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='Q7' && financialYear='2014' && titleID='"+indicator_id+"'";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
          
         achieved_prior_total_m= conn.rs3.getInt(1);
              
         achieved_prior_total_f= conn.rs3.getInt(2);
         totaldistspriors      =conn.rs3.getInt(3);
         
         System.out.println("totals"+ achieved_prior_total_f +"totals"+ achieved_prior_total_m +""+totaldistspriors);

}
System.out.println(county_name+""+indicator_id+"totaldistsprior"+totaldistsprior +"____"+achieved_prior_f +"____"+achieved_prior_m +"totals"+ achieved_prior_total_f +"totas"+ achieved_prior_total_m);
}

 if(indicator_id.equals("17") || indicator_id.equals("18") || indicator_id.equals("19") || indicator_id.equals("20") ) {
 

	
 String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='"+prior_qtr+"'  && (County='"+county_name+"'|| County='"+county_id+"') && financialYear='"+prior_year+"' && titleID='"+indicator_id+"'  GROUP BY county ";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       while(conn.rs2.next()){
      achieved_prior_m=conn.rs2.getInt(1);     
      
         achieved_prior_f=conn.rs2.getInt(2);     
        
            //totaldistsprior=conn.rs2.getInt(3);
}
  String get_total_prior1="SELECT SUM(menAchieved),SUM(womenAchieved),count(ResultID) FROM indicatorachieved WHERE reportingPeriod='"+prior_qtr+"' && financialYear='"+prior_year+"' && titleID='"+indicator_id+"'";  
      
        System.out.println("***** get_total_prior"+get_total_prior);
         
         conn.rs3=conn.state3.executeQuery(get_total_prior1);
       while(conn.rs3.next()){
          
         achieved_prior_total_m= conn.rs3.getInt(1);
              
         achieved_prior_total_f= conn.rs3.getInt(2);
         totaldistspriors      =conn.rs3.getInt(3);
         
         System.out.println("totals"+ achieved_prior_total_f +"totals"+ achieved_prior_total_m +""+totaldistspriors);

}
System.out.println(county_name+""+indicator_id+"totaldistsprior"+totaldistsprior +"____"+achieved_prior_f +"____"+achieved_prior_m +"totals"+ achieved_prior_total_f +"totas"+ achieved_prior_total_m);
}
 
else{


         String get_total_prior="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE (County='"+county_name+"'|| County='"+county_id+"') && reportingPeriod='"+custom_quarter+"' && financialYear='"+j+"' && titleID='"+indicator_id+"'  GROUP BY county";  
      
        // System.out.println("*****"+get_total_prior);
         
         conn.rs2=conn.state2.executeQuery(get_total_prior);
       if(conn.rs2.next()==true){
      achieved_prior_m+=conn.rs2.getInt(1);     
         achieved_prior_total_m+= conn.rs2.getInt(1);
         achieved_prior_f+=conn.rs2.getInt(2);     
         achieved_prior_total_f+= conn.rs2.getInt(2);
       }

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
//if(achieved_prior_m>0 || achieved_this_period_m>0 || achieved_prior_f>0 || achieved_this_period_f>0 || quarterly_target_women>0 || quarterly_target_men>0){
//DISPLAY THE RESULTS
    aa+=1;
    aa3+=1;
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
     rw8cell1.setCellValue(county_name);
     rw8cell2.setCellValue("");
     rw8cell3.setCellValue("");
     
   
             
     if(achieved_prior_f==0){
       rw8cell4.setCellValue("");   
   
     }
     if(achieved_prior_f>0){

        rw8cell4.setCellValue(achieved_prior_f);  
     

     }
     if(achieved_prior_m==0){
       rw8cell5.setCellValue("");   
   
     }
     if(achieved_prior_m>0){

        rw8cell5.setCellValue(achieved_prior_m );  
   

     }

     if(previous_quarter_achieved_f==0){
       rw8cell6.setCellValue("");   
 
     }
     if(previous_quarter_achieved_f>0){
        rw8cell6.setCellValue(previous_quarter_achieved_f);  
     
     }
//     rw8cell6.setCellValue(previous_quarter_achieved_f);
      if(previous_quarter_achieved_m==0){
       rw8cell7.setCellValue("");   
     
     }
     if(previous_quarter_achieved_m>0){
        rw8cell7.setCellValue(previous_quarter_achieved_m);  
    
     }
//     rw8cell7.setCellValue(previous_quarter_achieved_m);
     if (percentages==0){
     if(quarterly_target_women==0){
       rw8cell8.setCellValue("");   
   
     }
     if(quarterly_target_women>0){
        rw8cell8.setCellValue(quarterly_target_women);  
   
     }    
//     rw8cell8.setCellValue(quarterly_target_women);
     if(quarterly_target_men==0){
       rw8cell9.setCellValue("");   

     }
     if(quarterly_target_men>0){
        rw8cell9.setCellValue(quarterly_target_men);  
    
     } 
//     rw8cell9.setCellValue(quarterly_target_men); 
      if(achieved_this_period_f==0){
       rw8cell10.setCellValue("");   
   
     }
     if(achieved_this_period_f>0){
        rw8cell10.setCellValue(achieved_this_period_f);  
   
     }
//     rw8cell10.setCellValue(achieved_this_period_f);
      if(achieved_this_period_m==0){
       rw8cell11.setCellValue("");   
  
     }
     if(achieved_this_period_m>0){
        rw8cell11.setCellValue(achieved_this_period_m);  
 
     }
//     rw8cell11.setCellValue(achieved_this_period_m);
     
     
       int yearly_targets1=0;
       int yearly_targets2=0;
     
     
 String achieved_target_this_year="SELECT SUM(target_men),SUM(target_women) FROM yearly_targets WHERE county_id='"+county_id+"' && year='"+year+"' && indicator_id='"+indicator_id+"' ";  
 conn.rs1=conn.state1.executeQuery(achieved_target_this_year);
if (conn.rs1.next()==true){
//    TARGETS PER COUNTY TOTALS.............................     
    yearly_targets1=conn.rs1.getInt(1);
    yearly_targets2=conn.rs1.getInt(2);
    
    total_yearly_targets1+=yearly_targets1;
    
    total_yearly_targets2+=yearly_targets2;
     }
     
     
     
     
    if(yearly_targets1==0||yearly_targets2==0) 
    {
     rw8cell12.setCellValue("");
     rw8cell13.setCellValue("");
    
    }
    else
    {
     rw8cell12.setCellValue(yearly_targets2);
     rw8cell13.setCellValue(yearly_targets1);
  
    }
     
     
     
     
//     rw8cell12.setCellValue("");
//     rw8cell13.setCellValue("");
     
       if(achieved_this_year_f==0){
       rw8cell14.setCellValue("");   
      
     }
     if(achieved_this_year_f>0){
      
        
         if(indicator_id.equals("8")||indicator_id.equals("7")||indicator_id.equals("9")||indicator_id.equals("16")||indicator_id.equals("18")||indicator_id.equals("19")
                 ||indicator_id.equals("20")||indicator_id.equals("22")||indicator_id.equals("26")||indicator_id.equals("57")) {
         
          rw8cell14.setCellValue(achieved_this_period_f);  
     
         }
   
         else{
             
             //System.out.println("else "+achieved_this_year_f);
           rw8cell14.setCellValue(achieved_this_year_f);  
     
         }
        
     }
//     rw8cell14.setCellValue(achieved_this_year_f);
      if(achieved_this_year_m==0){
       rw8cell15.setCellValue("");   
    
     }
     if(achieved_this_year_m>0){
         
         if(indicator_id.equals("8")||indicator_id.equals("7")||indicator_id.equals("9")||indicator_id.equals("16")||indicator_id.equals("18")||indicator_id.equals("19")||
                 indicator_id.equals("20")||indicator_id.equals("22")||indicator_id.equals("26")||indicator_id.equals("57")) {
         
         
          rw8cell15.setCellValue(achieved_this_period_m);  
     
         
         
         }else{
        rw8cell15.setCellValue(achieved_this_year_m);  
     
         } 
     } 
//     rw8cell15.setCellValue(achieved_this_year_m);
}
     
   if (percentages==1){
       if(district_counter_tar>0){
      rw8cell8.setCellValue((quarterly_target_women/district_counter_tar)+"%"); 
     rw8cell9.setCellValue((quarterly_target_men/district_counter_tar)+"%");
     
       }
       if(district_counter_tar==0){
      rw8cell8.setCellValue(""); 
     rw8cell9.setCellValue("");
    
       }
       if(district_counter>0){
     rw8cell10.setCellValue((achieved_this_period_f/district_counter)+"%");
     rw8cell11.setCellValue((achieved_this_period_m/district_counter)+"%");
       

       
     rw8cell12.setCellValue("");
     rw8cell13.setCellValue("");
     
    
      if(indicator_id.equals("54") && quarter.equals("Q3") )
      {
          if(achieved_this_year_total_f>0){
      rw8cell14.setCellValue((achieved_this_year_f/achieved_this_year_total_f)+"%");
     rw8cell15.setCellValue((achieved_this_year_m/achieved_this_year_total_m)+"%");
   
          }
      }
      
      if(indicator_id.equals("54") && quarter.equals("Q1") )
      {
          if(achieved_this_year_total_f>0){
      rw8cell14.setCellValue((achieved_this_period_f/district_counter_yr)+"%");
     rw8cell15.setCellValue((achieved_this_period_m/district_counter_yr)+"%");
     
    
          }
      }
      
     else if(indicator_id.equals("54") && quarter.equals("Q4") )
      {
            if(counterforyear>0){
      rw8cell14.setCellValue((achieved_this_year_f/counterforyear)+"%");
     rw8cell15.setCellValue((achieved_this_year_m/counterforyear)+"%");
     
     
	 }}
  else if(indicator_id.equals("54") && quarter.equals("Q2") )
      {
            if(counterforyear>0){
      rw8cell14.setCellValue((achieved_this_year_f/counterforyear)+"%");
     rw8cell15.setCellValue((achieved_this_year_m/counterforyear)+"%");
     }}
     else if(indicator_id.equals("54") && quarter.equals("Q3") )
      {
          if(achieved_this_year_total_f>0){
      rw8cell14.setCellValue((achieved_this_year_f/achieved_this_year_totals)+"%");
     rw8cell15.setCellValue((achieved_this_year_m/achieved_this_year_totals)+"%");
          }
      }
      else{
     rw8cell14.setCellValue((achieved_this_year_f/district_counter_yr)+"%");
     rw8cell15.setCellValue((achieved_this_year_m/district_counter_yr)+"%");
     
    }
}
  if(district_counter==0){
     rw8cell10.setCellValue("");
     rw8cell11.setCellValue("");
       
     rw8cell12.setCellValue("");
     rw8cell13.setCellValue("");
     
     rw8cell14.setCellValue("");
     rw8cell15.setCellValue("");
    
} 
  //System.out.println(district_counter_prev+"error"+indicator_id);
  if(district_counter_prev>0   ){
  rw8cell6.setCellValue(previous_quarter_achieved_f/district_counter_prev+"%");
   rw8cell7.setCellValue(previous_quarter_achieved_m/district_counter_prev+"%"); 
   
 
  }
  
  if(district_counter_prev==0){
      
      rw8cell6.setCellValue("");
   rw8cell7.setCellValue(""); 
     
  }
  if(indicator_id.equals("54") && quarter.equals("Q2") && year==2015) {
	 if(district_counter_prev>0   ){
	rw8cell4.setCellValue(previous_quarter_achieved_f/district_counter_prev+"%");  
         rw8cell5.setCellValue(previous_quarter_achieved_m/district_counter_prev+"%");   
        
	}}
	else{
   
  if(district_counter_prior>0 ){
      
       if(indicator_id.equals("54") && quarter.equals("Q3") && year==2015) {
           rw8cell4.setCellValue(achieved_prior_f/achieved_prior_f_count+"%");  
         rw8cell5.setCellValue(achieved_prior_m/achieved_prior_f_count+"%");   
       
          
          }else{
        rw8cell4.setCellValue(achieved_prior_f/district_counter_prior+"%");  
         rw8cell5.setCellValue(achieved_prior_m/district_counter_prior+"%");   
       }
       
  }
  else if(totaldistsprior>0){
     rw8cell4.setCellValue(achieved_prior_f/totaldistsprior+"%");  
         rw8cell5.setCellValue(achieved_prior_m/totaldistsprior+"%");   
        }
  
//  if(district_counter_prior==0 ){
  else{
        rw8cell4.setCellValue("");  
         rw8cell5.setCellValue("");   
    
  }}
   }
      
     rw8cell16.setCellValue("");
     rw8cell17.setCellValue("");  
     rw8cell1.setCellStyle(style_border);
      rw8cell2.setCellStyle(style_border);
      rw8cell3.setCellStyle(style_border);
      rw8cell4.setCellStyle(style_border);
      rw8cell5.setCellStyle(style_border);
      rw8cell6.setCellStyle(style_border);
      rw8cell7.setCellStyle(style_border);
      rw8cell8.setCellStyle(style_border);
      rw8cell9.setCellStyle(style_border);
//      rw8cell21.setCellStyle(style);
      rw8cell10.setCellStyle(style_border);
      rw8cell11.setCellStyle(style_border);
      rw8cell12.setCellStyle(style_border);
      rw8cell13.setCellStyle(style_border);
      rw8cell14.setCellStyle(style_border);
      rw8cell15.setCellStyle(style_border);
      rw8cell16.setCellStyle(style_border);
      rw8cell17.setCellStyle(style_border);
   
      
      //sheet3
      
//}
 previous_quarter_achieved_m=achieved_prior_m=achieved_this_year_m=achieved_this_period_m=previous_quarter=prior_quarter=0;
 previous_quarter_achieved_f=achieved_prior_f=achieved_this_year_f=achieved_this_period_f=0;
 district_counter_yr=district_counter=district_counter_tar=totaldistsprior=0;
  }
//     total for all counties achieved
     if(achieved_prior_total_m>0 ||total_quarterly_target_women>0 ||total_quarterly_target_men>0 || achieved_this_year_total_m>0 || achieved_this_period_total_m>0 || achieved_prior_total_f>0 || achieved_this_year_total_f>0 || achieved_this_period_total_f>0){
     aa+=1;
    
HSSFRow rwk=shet1.createRow(aa);
       rwk.setHeightInPoints(20);
      HSSFCell rwkcell1=rwk.createCell(1);
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
    
    
     if(percentages==0){
         
//         sheet 1
          rwkcell1.setCellValue("Total");
     rwkcell2.setCellValue("");
     rwkcell3.setCellValue("");
     

         rwkcell4.setCellValue(achieved_prior_total_f);
     rwkcell5.setCellValue(achieved_prior_total_m);

     rwkcell6.setCellValue(previous_quarter_achieved_total_f);
     rwkcell7.setCellValue(previous_quarter_achieved_total_m);
     
         
         
        
         
     rwkcell8.setCellValue(total_quarterly_target_women);
     rwkcell9.setCellValue(total_quarterly_target_men); 
     rwkcell10.setCellValue(achieved_this_period_total_f);
     rwkcell11.setCellValue(achieved_this_period_total_m);
     
        if(indicator_id.equals("8")||indicator_id.equals("7")||indicator_id.equals("9")||indicator_id.equals("16")||indicator_id.equals("18")||indicator_id.equals("19")||
                 indicator_id.equals("20")||indicator_id.equals("22")||indicator_id.equals("26")||
                indicator_id.equals("57") ) {
         
         rwkcell14.setCellValue(achieved_this_period_total_f);
     rwkcell15.setCellValue(achieved_this_period_total_m);
      }else{
          
             rwkcell14.setCellValue(achieved_this_year_total_f);
     rwkcell15.setCellValue(achieved_this_year_total_m);
      }
     rwkcell12.setCellValue(total_yearly_targets2);
     rwkcell13.setCellValue(total_yearly_targets1);
  
     


    
     }
     
       if(percentages==1){
           
           
           
            rwkcell1.setCellValue("Total");
     rwkcell2.setCellValue("");
     rwkcell3.setCellValue("");
//     rwkcell4.setCellValue(achieved_prior_total_f);
//     rwkcell5.setCellValue(achieved_prior_total_m);
           if(overall_district_counter_tar>0){
     rwkcell8.setCellValue((total_quarterly_target_women/overall_district_counter_tar)+"%");
     rwkcell9.setCellValue((total_quarterly_target_men/overall_district_counter_tar)+"%"); 
     
    
           }
           if(overall_districts>0){
               
              // if(indicator_id.equals("54")){
               
               //district_counter_yr1
                   if(district_counter_yr1>0){
                rwkcell10.setCellValue((achieved_this_period_total_f/overall_districts)+"%");
     rwkcell11.setCellValue((achieved_this_period_total_m/overall_districts)+"%");
     rwkcell12.setCellValue("");
     rwkcell13.setCellValue("");
     rwkcell14.setCellValue((achieved_this_year_total_f/district_counter_yr1)+"%");
     rwkcell15.setCellValue((achieved_this_year_total_m/district_counter_yr1)+"%");
     
    
        }
               //    }     
               
               
                   else if(overall_districts>0) {     
                   rwkcell10.setCellValue((achieved_this_period_total_f/overall_districts)+"%");
     rwkcell11.setCellValue((achieved_this_period_total_m/overall_districts)+"%");
     rwkcell12.setCellValue("");
     rwkcell13.setCellValue("");
     rwkcell14.setCellValue((achieved_this_year_total_f/overall_districts_yr)+"%");
     rwkcell15.setCellValue((achieved_this_year_total_m/overall_districts_yr)+"%");
     
     
               } }
         
           
      if(indicator_id.equals("54")) {
             if(totaldistspriors>0){
             
              rwkcell4.setCellValue(achieved_prior_total_f/totaldistspriors+"%");
     rwkcell5.setCellValue(achieved_prior_total_m/totaldistspriors+"%");
     
        
     System.out.println(achieved_prior_total_f / totaldistspriors + "%"+ achieved_prior_total_f +"%%%%%"+totaldistspriors);

     
             }
			    if(indicator_id.equals("54") && quarter.equals("Q2") && year==2015) {
				 
				  rwkcell4.setCellValue(previous_quarter_achieved_total_f/overall_district_counter_prev+"%");
     rwkcell5.setCellValue(previous_quarter_achieved_total_m/overall_district_counter_prev+"%");
     
   
				 
				 
				 }
      }
      else{
       if(overall_district_counter_prior>0 ){
          
       rwkcell4.setCellValue(achieved_prior_total_f/overall_district_counter_prior+"%");
     rwkcell5.setCellValue(achieved_prior_total_m/overall_district_counter_prior+"%");
      System.out.println(achieved_prior_total_f / totaldistspriors + "%"+ achieved_prior_total_f +"%%%%%"+totaldistspriors);

        
       }
      }
       
       System.out.println(district_counter_prev +"ll"+overall_district_counter_prior+"OOO"+ indicator_id +"bbb"+overall_district_counter_prior+"kkkk");
        if(overall_district_counter_prev>0){
     rwkcell6.setCellValue(previous_quarter_achieved_total_f/overall_district_counter_prev+"%");
     rwkcell7.setCellValue(previous_quarter_achieved_total_m/overall_district_counter_prev+"%");
     //sheet2
   
        }
       
       
     
       }
     rwkcell16.setCellValue("");
     rwkcell17.setCellValue("");
    
       
     rwkcell1.setCellStyle(stylex);
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
      
     
    }
      previous_quarter_achieved_total_m=achieved_prior_total_m=achieved_this_year_total_m=achieved_this_period_total_m=0;
        total_yearly_targets1=0;
        total_yearly_targets2=0;
       district_counter_prior=district_counter_prev=0;
      previous_quarter_achieved_total_f=achieved_prior_total_f=achieved_this_year_total_f=achieved_this_period_total_f=0; 
      total_quarterly_target_women=total_quarterly_target_men=overall_district_counter_prev=overall_districts=overall_districts_yr=overall_district_counter_tar=overall_district_counter_prior=district_counter_prev=district_counter_prior=0;
    aa+=2;    
 
    }
     aa+=2;
    
     
    
    }
    }
        
        
     ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PPMT_"+period+"_"+Display_year+"_"+"County_Report(Broken)_Created_On_"+current_time+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
    
        } finally {
//            response.sendRedirect("county_quarter_report.jsp");
        }}
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
                    } catch (ParseException ex) {
                        Logger.getLogger(county_reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(county_reports.class.getName()).log(Level.SEVERE, null, ex);
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
                    } catch (ParseException ex) {
                        Logger.getLogger(county_reports.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(county_reports.class.getName()).log(Level.SEVERE, null, ex);
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
