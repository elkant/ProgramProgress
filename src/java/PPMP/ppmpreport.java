/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PPMP;

import PP.Admin.dbConnect;
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
 * @author Emmanuel E
 */
public class ppmpreport extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       
        try {
            /* TODO output your page here. You may use following sample code. */
            
            //create ppmp report here
            
            
            
  //define some variables for keeping number of columns. 
 // this should be dynamic because of the annual cumulatives depending on the selected year
 //the minimum year is 2011
 //
            int selectedyear=2016;
            int projectstartyear=2011;
            int minimumcolumns=11;//this is if the year is 2011
            int currentcolumns=minimumcolumns+(selectedyear-projectstartyear);
            String selectedQTR="Q2";
            
            
            
            if(request.getParameter("year")!=null){
            
            selectedyear=new Integer(request.getParameter("year"));
            
            }
            
              if(request.getParameter("year")!=null){
            
            selectedQTR=request.getParameter("quarter");
            
            }
            
  HSSFWorkbook wb=new HSSFWorkbook();
  
  
Calendar cal= Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
String month=String.format("%02d",cal.get(Calendar.MONTH)+1);
String date=String.format("%02d",cal.get(Calendar.DATE));
String hour = String.format("%02d",cal.get(Calendar.HOUR_OF_DAY));
String min=String.format("%02d",cal.get(Calendar.MINUTE));
String sec=String.format("%02d",cal.get(Calendar.SECOND));

  
  String generationtime="("+year+"_"+month+"_"+date+")_"+hour+"-"+min+"-"+sec;
        
 
   //______________________________________________________________________________________
    //______________________________________________________________________________________
              
            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Arial Narrow");
            font.setColor((short) 0000);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setWrapText(true);
            
            
            HSSFFont font2 = wb.createFont();
            font2.setFontName("Cambria");
            font2.setColor((short) 0000);
            CellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style2.setWrapText(true);

            HSSFCellStyle stborder = wb.createCellStyle();
            stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCellStyle stylex = wb.createCellStyle();
            stylex.setFillForegroundColor(HSSFColor.WHITE.index);
            stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

            HSSFFont fontx = wb.createFont();
            fontx.setColor(HSSFColor.BLACK.index);
            fontx.setFontName("Arial Narrow");
            fontx.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            fontx.setFontHeightInPoints((short) 16);
            stylex.setFont(fontx);
            stylex.setWrapText(true);

   
   HSSFSheet shet=wb.createSheet("PPMP "+selectedyear+" Report ");   
     
    //create headers for that worksheet
      
      HSSFRow rw=shet.createRow(0);
      rw.setHeightInPoints(25);
 HSSFCell cl0= rw.createCell(0);
 cl0.setCellValue("PROJECT PERFORMANCE MONITORING PLAN (PPMP)");
 cl0.setCellStyle(stylex);
  
 
 
 
 for(int a=1;a<currentcolumns;a++){ 
 HSSFCell clx= rw.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
 //merge row one
 
  shet.addMergedRegion(new CellRangeAddress(0,0,0,currentcolumns-1));  
 
  
  
  //firt row
  
  
  ArrayList headerone=new ArrayList();
  headerone.add("Sub Purpose");
  headerone.add("Output");
  headerone.add("Indicators");
  headerone.add("Baseline");
  headerone.add("Year "+selectedyear+" Target");
  headerone.add(selectedyear+" Quarterly Achievements ");
  headerone.add("");
  headerone.add("");
  headerone.add("");
   headerone.add("Cumulative Year Achievements");
  //the header Cumulative Year Achievements could be in the report depending on the selected year
  //for 2011, its not expected to appear in the report

            for (int a = 0; a <= (selectedyear - projectstartyear); a++) {
                if (a == 0) {
                  //do nothing 
                } else {
                    headerone.add("");
                }

            }
  
headerone.add("Percentage (%) Achieved vs Year "+selectedyear);
  
 //header two which contains quartersa dn yearly achievement

ArrayList headertwo=new ArrayList();
  headertwo.add("Sub Purpose");
  headertwo.add("Output");
  headertwo.add("Indicators");
  headertwo.add("Baseline");
  headertwo.add("");
  headertwo.add("Oct-Dec "+(selectedyear-1));
  headertwo.add("Jan-Mar");
  headertwo.add("Apr-Jun");
  headertwo.add("Jul-Sep");
  headertwo.add(selectedyear);
  //the header Cumulative Year Achievements could be in the report depending on the selected year
  //for 2011, its not expected to appear in the report

            for (int a = 0; a <= (selectedyear - projectstartyear); a++) {
                if (a == 0) {
                  //do nothing 
                } else {
                    headertwo.add(selectedyear-a);
                    //eg 2016, 2015, 2014 ...
                }

            }
  
headertwo.add("");
  
  




//=================================================================================

//DISPLAY HEADER   DISPLAY HEADER   DISPLAY HEADER   DISPLAY HEADER  DISPLAY HEADER

//=================================================================================


  
  //display the header values for row one and two
  HSSFRow rw1= shet.createRow(1);
  for(int a=0;a<headerone.size();a++){
  HSSFCell cellx=rw1.createCell(a);
  
  cellx.setCellValue(headerone.get(a).toString());
  cellx.setCellStyle(style);
  shet.setColumnWidth(a, 3500);
  
                                     }
  
  
  //===================row 2=======================
  
   //display the header values for row one and two
  HSSFRow rw2= shet.createRow(2);
     rw2.setHeightInPoints(35);
  for(int a=0;a<headertwo.size();a++){
  HSSFCell cellx=rw2.createCell(a);
  
  if(headertwo.get(a).toString().startsWith("20")){
  
   cellx.setCellValue(new Integer(headertwo.get(a).toString()));
  }
  else {
  cellx.setCellValue(headertwo.get(a).toString());
  }
  
  cellx.setCellStyle(style);
 
  
                                     }
   
  
 
 // shet.addMergedRegion(new CellRangeAddress(start row, end row, start column ,end column));   
 String mergingarray[]={"1_2_0_0","1_2_1_1","1_2_2_2","1_2_3_3","1_2_4_4","1_1_5_8","1_1_9_"+(9+(selectedyear-projectstartyear)),"1_2_"+(9+(selectedyear-projectstartyear)+1)+"_"+(9+(selectedyear-projectstartyear)+1)};
  
    
 for(int a=0;a<mergingarray.length;a++){
 String content[]=mergingarray[a].split("_");
 
 shet.addMergedRegion(new CellRangeAddress(new Integer(content[0]), new Integer(content[1]), new Integer(content[2]),new Integer(content[3])));
 
 }
 
 
 
//===================================================================================

//DISPLAY VALUES    DISPLAY VALUES    DISPLAY VALUES   DISPLAY VALUES  DISPLAY VALUES

//===================================================================================
 
 
 String getindicators="select * from indicatortitles where  active='yes' order by subpurpose, output , title ";
 
            dbConnect conn= new dbConnect();
            
            conn.rs=conn.state.executeQuery(getindicators);
            int rownumber=3;
           
            shet.setColumnWidth(1,8000);
            shet.setColumnWidth(2,14000);
            
               ArrayList sp=new ArrayList();
            int subpurposerow=3;
            int subpurposerowcopy=3;
            
            ArrayList op= new ArrayList();
            int outputrow=3;
            int outputrowcopy=3;
        
          int count=0; 
            
 while(conn.rs.next()){
 
     //merge subpartner row
     sp.add(conn.rs.getString("subpurpose"));
     if(sp.size()>1){
     //check if subporpose has changed
      if(!sp.get(count).toString().equals(sp.get(count-1).toString())){
      
       subpurposerow=rownumber;  
       
       //should merge
       shet.addMergedRegion(new CellRangeAddress(subpurposerowcopy,subpurposerow-1, 0 ,0));     
       subpurposerowcopy=subpurposerow;
                                                                      }   
     
     }
     String outputval="";
     
     if(conn.rs.getString("output")!=null){
     outputval=conn.rs.getString("output");
     }
     
     //merge ouput rows
     op.add(outputval);
     if(op.size()>1){
     //check if out has changed
      if(!op.get(count).toString().equals(op.get(count-1).toString())){
      
       outputrow=rownumber;  
         
       //should merge
       shet.addMergedRegion(new CellRangeAddress(outputrowcopy,outputrow-1,1 ,1));     
       outputrowcopy=outputrow;
                                                                      }   
     
     }
     
     
     
 //now output the first part of the report
     
     HSSFRow rwx=shet.createRow(rownumber);
     
     //===================================================subpurpose========================
     HSSFCell cl01=rwx.createCell(0);
     cl01.setCellValue(conn.rs.getString("subpurpose"));
     cl01.setCellStyle(style2);     
     
     
     //====================================================output============================= 
     HSSFCell cl02=rwx.createCell(1);
     cl02.setCellValue(conn.rs.getString("output"));
     cl02.setCellStyle(style2);
     
     
      //===================================================indicators===========================
     HSSFCell cl03=rwx.createCell(2);
     cl03.setCellValue(conn.rs.getString("title"));
     cl03.setCellStyle(style2);
     
      //====================================================baseline=============================
     HSSFCell cl04=rwx.createCell(3);
     cl04.setCellValue(conn.rs.getString("totalbaseline"));
     cl04.setCellStyle(style2);
     
     //====================================================targets=================================
     String gettargets=" select sum(target_combined) as target from yearly_targets where indicator_id='"+conn.rs.getString("titleID")+"' and year='"+selectedyear+"' ";
     
     //for percent indicators, get avg
     if(conn.rs.getInt("percentage")==1){
     
     gettargets="select avg(target_combined) as target from yearly_targets where indicator_id='"+conn.rs.getString("titleID")+"'  and year='"+selectedyear+"' ";
     
     }
     
     HSSFCell cl05=rwx.createCell(4);
   
     int annualtarget=1;
     
     conn.rs1=conn.state1.executeQuery(gettargets);
     if(conn.rs1.next())
     {
     //set the target
     cl05.setCellValue(conn.rs1.getInt("target"));
       if(conn.rs.getInt("percentage")==1){
           if(conn.rs1.getInt("target")<200){
        cl05.setCellValue(conn.rs1.getInt("target")+"%");
           }
           else {
            cl05.setCellValue(conn.rs1.getInt("target"));
           }
       }
       else {
        cl05.setCellValue(conn.rs1.getInt("target"));
       }
     
     cl05.setCellStyle(style2);
     if(conn.rs1.getString("target")!=null){
     if(!conn.rs1.getString("target").equals("")){
     
         
      annualtarget=conn.rs1.getInt("target");
      
      
     }
     }
     
     }
     
    
 //===========================================current year values====================================
     
     String ispercent="";
    
     int highestvalue=0;
     String getdata=" select sum(case  when  reportingPeriod='Q1' then totalAchieved end) as Q1, sum(case  when  reportingPeriod='Q2' then totalAchieved end) as Q2, sum(case  when  reportingPeriod='Q3' then totalAchieved end) as Q3, sum(case  when  reportingPeriod='Q4' then totalAchieved end) as Q4  where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+selectedyear+"' group by titleID ";
     
     //for percent indicators, get avg
     if(conn.rs.getInt("percentage")==1)
     {
     ispercent="%";
          if(conn.rs.getString("tableIdentifier").equals("1")){
     //by gender and thus separate columns
         
     getdata=" select  ROUND(AVG(case  when  reportingPeriod='Q1' then ((menAchieved + womenAchieved)/2) end)) as Q1, ROUND(AVG(case  when  reportingPeriod='Q2' then ((menAchieved + womenAchieved)/2) end)) as Q2, ROUND(AVG(case  when  reportingPeriod='Q3' then ((menAchieved + womenAchieved)/2) end)) as Q3, ROUND(AVG(case  when  reportingPeriod='Q4' then ((menAchieved + womenAchieved)/2) end)) as Q4 from indicatorachieved   where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+selectedyear+"' group by titleID ";
     
     
          }
          
          else {
          //combined i.e male and female
              getdata=" select ROUND(AVG(case  when  reportingPeriod='Q1' then totalAchieved end)) as Q1,ROUND(AVG(case  when  reportingPeriod='Q2' then totalAchieved end)) as Q2, ROUND(AVG(case  when  reportingPeriod='Q3' then totalAchieved end)) as Q3, ROUND(AVG(case  when  reportingPeriod='Q4' then totalAchieved end)) as Q4  from indicatorachievedcombined    where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+selectedyear+"' group by titleID ";
     
          
          }
     }
     //non percentages
     else {
     //if 
     if(conn.rs.getString("tableIdentifier").equals("1")){
     //by gender and thus separate columns
      getdata=" select  sum(case  when  reportingPeriod='Q1' then (menAchieved + womenAchieved) end) as Q1,sum(case  when  reportingPeriod='Q2' then (menAchieved + womenAchieved) end) as Q2, sum(case  when  reportingPeriod='Q3' then (menAchieved + womenAchieved) end) as Q3, sum(case  when  reportingPeriod='Q4' then (menAchieved + womenAchieved) end) as Q4 from indicatorachieved  where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+selectedyear+"' group by titleID ";   
         
     }
     else {
         
          getdata=" select sum(case  when  reportingPeriod='Q1' then totalAchieved end) as Q1, sum(case  when  reportingPeriod='Q2' then totalAchieved end) as Q2, sum(case  when  reportingPeriod='Q3' then totalAchieved end) as Q3, sum(case  when  reportingPeriod='Q4' then totalAchieved end) as Q4 from indicatorachievedcombined  where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+selectedyear+"' group by titleID ";   
     
     
     }
     
     }
  
     String Q1="";
     String Q2="";
     String Q3="";
     String Q4="";
     
     conn.rs1=conn.state1.executeQuery(getdata);
     
     //Q1
      
     HSSFCell clQ1=rwx.createCell(5);
     HSSFCell clQ2=rwx.createCell(6);
     HSSFCell clQ3=rwx.createCell(7);
     HSSFCell clQ4=rwx.createCell(8);
     
     if (conn.rs1.next()) {

         if (conn.rs1.getString("Q1") != null) {
             if (!conn.rs1.getString("Q1").equals("")) {
               highestvalue=conn.rs1.getInt("Q1");
              if(!ispercent.equals("")){
                 
                 clQ1.setCellValue(conn.rs1.getInt("Q1")+ispercent);
               
                 }
                 else {
                 clQ1.setCellValue(conn.rs1.getInt("Q1"));
                 }

             }
         }

         if (conn.rs1.getString("Q2") != null) {
             if (!conn.rs1.getString("Q2").equals("")) {
    if(conn.rs1.getInt("Q2")>highestvalue){highestvalue=conn.rs1.getInt("Q2");}
                if(!ispercent.equals("")){
                 clQ2.setCellValue(conn.rs1.getInt("Q2")+ispercent);
                 
                 
                 
                 }
                 else {
                 clQ2.setCellValue(conn.rs1.getInt("Q2"));
                 }

             }
         }

         if (conn.rs1.getString("Q3") != null) {
             if (!conn.rs1.getString("Q3").equals("")) {
                 
                 if(conn.rs1.getInt("Q3")>highestvalue){highestvalue=conn.rs1.getInt("Q3");}
               if(!ispercent.equals("")){
                 clQ3.setCellValue(conn.rs1.getInt("Q3")+ispercent);
                 }
                 else {
                 clQ3.setCellValue(conn.rs1.getInt("Q3"));
                 }

             }
         }

         if (conn.rs1.getString("Q4") != null) {
             if (!conn.rs1.getString("Q4").equals("")) {
                 if(conn.rs1.getInt("Q4")>highestvalue){highestvalue=conn.rs1.getInt("Q4");}
                 if(!ispercent.equals("")){
                 clQ4.setCellValue(conn.rs1.getInt("Q4") + ispercent);
                 }
                 else {
                 clQ4.setCellValue(conn.rs1.getInt("Q4"));
                 }

             }
         }

     }
     
     
     clQ2.setCellStyle(style2);
     clQ1.setCellStyle(style2);
     clQ3.setCellStyle(style2);
     clQ4.setCellStyle(style2);
     
     
   //====================================Annual figures=======================  
     String percentageachievement="";
     String achievednonpercent="No target / achieved value"; 
     int curcol=9;
     String annualispercent=""; 
     
     int currentyearvalue=0;
     int currentyearhighestqtr=0;
 for(int yearval=selectedyear;yearval>=projectstartyear;yearval--){ 
 
     
     HSSFCell clx= rwx.createCell(curcol);
     
    // System.out.println("******"+curcol);
     //separate cumulates + average with the rest
   if(conn.rs.getString("cumulative_chooser").equalsIgnoreCase("Cumulative")|| conn.rs.getString("cumulative_chooser").equalsIgnoreCase("Average")){
     String qry="";
     
     
  if(conn.rs.getString("percentage").equals("1"))
  
  {
  annualispercent="%";
      if(conn.rs.getString("tableidentifier").equals("2")){
      //no gender thus its combined 
          
      qry=" select ROUND(AVG(totalAchieved))  as y"+yearval+" from indicatorachievedcombined where financialyear='"+yearval+"' and titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
   
      
      }
      else {
      
       qry=" select ROUND(AVG((menAchieved + womenAchieved)/2))  as y"+yearval+" from indicatorachieved where financialyear='"+yearval+"' and titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
      
      
         }
      
      
  }
  
  //non percents
  else {
      //for cumulatives
      //check tabletype
      //1 is by gender
      
      //_____________CUMULATIVES______________
      
     if(conn.rs.getString("cumulative_chooser").equalsIgnoreCase("Cumulative"))
      {
          
      if(conn.rs.getString("tableIdentifier").equals("1"))
      {
          
   qry=" select sum((menAchieved + womenAchieved)) as y"+yearval+" from indicatorachieved where financialyear='"+yearval+"' and titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
     
      }
      
else {
      
     qry=" select sum(totalAchieved) as y"+yearval+" from indicatorachievedcombined where financialyear='"+yearval+"' and titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
    
     }//end of else of table identifier
      
  }//end of sum/cumulatives
     
     
       }//end of else of non percents
       System.out.println("@"+qry);    
  
    conn.rs1=conn.state1.executeQuery(qry);
    
     
    if(conn.rs1.next())
    {  
       
   
       // System.out.println("__"+conn.rs1.getString(1));
       
        
        
        if (conn.rs1.getString(1) != null) {  
            if (!conn.rs1.getString(1).equals("")) {
                if(annualispercent.equals("") ){
                    
                    clx.setCellValue(conn.rs1.getInt(1));
                    //achieved nonpercentage
                  if(yearval==selectedyear){
                  
                   
                      if(annualtarget>1){
                    achievednonpercent=""+(int)conn.rs1.getInt(1)*100/(int)annualtarget+"%";
                      }
                    }
                    
                }
                else {
                    clx.setCellValue(conn.rs1.getInt(1)+annualispercent);
                   //do this for the cureent year
                    if(yearval==selectedyear){
                    percentageachievement=conn.rs1.getInt(1)+annualispercent;
                   
                    }
                }
                
            }
                                            }
         }
 clx.setCellStyle(style2);
 
 }//end of cumulatives and percentages
 
 
 else if(conn.rs.getString("cumulative_chooser").equalsIgnoreCase("Highest"))
      {  
     String qry="";
      
     if(conn.rs.getString("tableIdentifier").equals("1")){
     //by gender and thus separate columns
      qry=" select  sum(case  when  reportingPeriod='Q1' then (menAchieved + womenAchieved) end) as Q1,sum(case  when  reportingPeriod='Q2' then (menAchieved + womenAchieved) end) as Q2, sum(case  when  reportingPeriod='Q3' then (menAchieved + womenAchieved) end) as Q3, sum(case  when  reportingPeriod='Q4' then (menAchieved + womenAchieved) end) as Q4 from indicatorachieved  where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+yearval+"' group by titleID ";   
         
     }
     else {
         
          qry=" select sum(case  when  reportingPeriod='Q1' then totalAchieved end) as Q1, sum(case  when  reportingPeriod='Q2' then totalAchieved end) as Q2, sum(case  when  reportingPeriod='Q3' then totalAchieved end) as Q3, sum(case  when  reportingPeriod='Q4' then totalAchieved end) as Q4 from indicatorachievedcombined  where titleID='"+conn.rs.getString("titleID")+"' and financialyear='"+yearval+"' group by titleID ";   
     
     
     }    
        int highestqtr=0;  
         //excecute query
        conn.rs1=conn.state1.executeQuery(qry);
        while(conn.rs1.next()){
        //
            
            if(conn.rs1.getString("Q1")!=null){
            if(!conn.rs1.getString("Q1").equals("")){
         if(conn.rs1.getInt("Q1")>highestqtr){ highestqtr=conn.rs1.getInt("Q1"); }
            }
            }
            else if(conn.rs1.getString("Q2")!=null){
            if(!conn.rs1.getString("Q2").equals("")){
         if(conn.rs1.getInt("Q2")>highestqtr){ highestqtr=conn.rs1.getInt("Q2"); }
            }
            }
            
             else if(conn.rs1.getString("Q3")!=null){
            if(!conn.rs1.getString("Q3").equals("")){
         if(conn.rs1.getInt("Q3")>highestqtr){ highestqtr=conn.rs1.getInt("Q3"); }
            }
            }
            
            
              else if(conn.rs1.getString("Q4")!=null){
            if(!conn.rs1.getString("Q4").equals("")){
         if(conn.rs1.getInt("Q4")>highestqtr){ highestqtr=conn.rs1.getInt("Q4"); }
            }
            }
            
        }  
          if(highestqtr>0){
         clx.setCellValue(highestqtr);
         
         
          }
          else{
           clx.setCellValue("");
          }
         
            //achieved nonpercentage
                  if(yearval==selectedyear){
                   
                      if(annualtarget!=1){
                    achievednonpercent=""+(int)highestqtr*100/(int)annualtarget+"%";
                    
                          System.out.println(highestqtr+" / "+annualtarget+"___"+(int)highestqtr*100/(int)(annualtarget)+"%");
                      }
                    }
          
          
         clx.setCellStyle(style2);  
     
      }//   end of highest 
 
 
 
 else if(conn.rs.getString("cumulative_chooser").equalsIgnoreCase("OLMIS")||conn.rs.getString("cumulative_chooser").equalsIgnoreCase("Last Reported"))
      {
          
          String qry="";
     //get data for the last input quarter
     if(yearval==selectedyear){
     //get data for that quarter
         
         if(conn.rs.getString("tableidentifier").equals("2")){
         //no gender
          qry=" select sum((totalAchieved)) as y"+yearval+" from indicatorachievedcombined where financialyear='"+yearval+"' and reportingPeriod='"+selectedQTR+"' and  titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
         
         }
    else {
             
             qry=" select sum((menAchieved + womenAchieved)) as y"+yearval+" from indicatorachieved where financialyear='"+yearval+"' and reportingPeriod='"+selectedQTR+"' and  titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
         
          }
     
     
           }
     
     else {
     
     
           if(conn.rs.getString("tableidentifier").equals("2")){
         //no gender
          qry=" select sum((totalAchieved)) as y"+yearval+" from indicatorachievedcombined where financialyear='"+yearval+"' and reportingPeriod='Q4' and  titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
         
         }
    else {             
             qry=" select sum((menAchieved + womenAchieved)) as y"+yearval+" from indicatorachieved where financialyear='"+yearval+"' and reportingPeriod='Q4' and  titleID='"+conn.rs.getString("titleID")+"' group by titleID";    
         
          }
     
     
     }
          
          
   //execute the query  
    conn.rs1=conn.state1.executeQuery(qry);
    
     
    if(conn.rs1.next())
    {
       // System.out.println("__"+conn.rs1.getString(1));
        
        if (conn.rs1.getString(1) != null) {  
            if (!conn.rs1.getString(1).equals("")) {
                  
                    clx.setCellValue(conn.rs1.getInt(1));
                    //this is for percentage purpose
                    if(selectedyear==yearval){
                    currentyearvalue=conn.rs1.getInt(1);
                    }
               
                
            }
                                            }
        
        
         }
    
    //achieved nonpercentage
                  if(yearval==selectedyear){
                   
                      if(annualtarget!=1){
                    achievednonpercent=""+(int)currentyearvalue*100/(int)annualtarget+"%";
                      }
                    }
          
    
    
    
 clx.setCellStyle(style2);      
          
          
     
      }//end of olmis and last reported indicators
 
  curcol++; 
 
 
                     }//end of for loop
     HSSFCell clx= rwx.createCell(curcol);
     
       if(conn.rs.getInt("percentage")==1){
           
       clx.setCellValue(percentageachievement);
       
       
       }
       else {
       clx.setCellValue(achievednonpercent);
       
       
       }
     
     clx.setCellStyle(style2);
     rwx.setHeightInPoints(42);
     
     rownumber++;
     count++;
                      }
 
 
 shet.addMergedRegion(new CellRangeAddress(subpurposerowcopy,rownumber-1, 0 ,0));
 shet.addMergedRegion(new CellRangeAddress(outputrowcopy,rownumber-1,1 ,1));   
 
    if(conn.rs!=null){conn.rs.close();}        
    if(conn.rs1!=null){conn.rs1.close();}        
    if(conn.state1!=null){conn.state1.close();}        
    if(conn.state2!=null){conn.state2.close();} 
    
                     // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PPMPREPORT_"+selectedyear+"_"+selectedQTR+"_gen_on_"+generationtime+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
            
            
        } finally {
          
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
            Logger.getLogger(ppmpreport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
            Logger.getLogger(ppmpreport.class.getName()).log(Level.SEVERE, null, ex);
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
