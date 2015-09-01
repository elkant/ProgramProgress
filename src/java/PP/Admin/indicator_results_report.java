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
import java.util.ArrayList;
import java.util.Collections;
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
public class indicator_results_report extends HttpServlet {
String indicator_name,indicator_id;
int total_achieved,total_districts;
String totalachieved="";
String replyback="";
HttpSession session;
String year,quarter;
int table_id,percentages,ppmp_id;
 String Quarter="";
    int erroroccured = 0;
    String unuploadedrows = "";
          int avg=0;
          int sum=0;
              int cumulative=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
     dbConnect ppmt = new dbConnect();
     dbConnectPPMP ppmp = new dbConnectPPMP();
//     year="2014"; quarter="Q1";
     year=request.getParameter("year");
     quarter=request.getParameter("quarter");
             ArrayList allcells = new ArrayList();
          
String targets="";
String baseline="";
        String itemName = "";
       int percentage=0;    
            int qtr1=0;
            int qtr2=0;
            int qtr3=0;
            int qtr4=0;
  String percent="";
     String check_targ="";  
             int target=0;
     int Quarter4=0;
      //          CREATE A WORKBOOK
       HSSFWorkbook wb=new HSSFWorkbook();
       HSSFSheet shet1=wb.createSheet();
int indicator_ids=0;
String data="";
         
        erroroccured = 0;
        unuploadedrows = ""; 
 String check_target="";
  check_target="1";

//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//    ^^^^^^^^^^^^HEADER STYLES^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     HSSFFont font_header=wb.createFont();
    font_header.setFontHeightInPoints((short)10);
    font_header.setFontName("Arial Black");
//    font.setItalic(true);
    font_header.setBoldweight((short)05);
    font_header.setColor(HSSFColor.BLACK.index);
    CellStyle style_header=wb.createCellStyle();
    style_header.setFont(font_header);
    style_header.setWrapText(true);
    style_header.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

    shet1.setColumnWidth(0, 10000);
    shet1.setColumnWidth(1, 6000); 
    shet1.setColumnWidth(2, 5000);
    shet1.setColumnWidth(3, 5500);
         
         
//         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//         ^^^^^^CREATE A NEW ROW AND ITS FORMATTINGS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         HSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)12);
    font.setFontName("Cambria");
//    font.setItalic(true);
    font.setBoldweight((short)02);
    font.setColor(HSSFColor.BLACK.index);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setWrapText(true);
    style.setFillForegroundColor(HSSFColor.GOLD.index);
style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFRow rw1=shet1.createRow(0);
     rw1.setHeightInPoints(35);
     
    HSSFCell rw1cell0=rw1.createCell(0);
    HSSFCell rw1cell1=rw1.createCell(1);
    HSSFCell rw1cell2=rw1.createCell(2); 

      rw1cell0.setCellValue("INDICATOR ID");
      rw1cell1.setCellValue("Q4");
//      rw1cell1.setCellValue("Q4");
     String  percentages1="";
int rws=1;
  rw1cell0.setCellStyle(style);
  rw1cell1.setCellStyle(style);
      String indicator_selector="SELECT * FROM indicatortitles";
    ppmt.rs=ppmt.state.executeQuery(indicator_selector);
    while(ppmt.rs.next()){
       table_id=ppmt.rs.getInt("tableIdentifier");
      percentages=ppmt.rs.getInt("percentage"); 
      percentages1=ppmt.rs.getString("percentage"); 
      
        ppmp_id=ppmt.rs.getInt("ppmpid");
        indicator_id=ppmt.rs.getString("titleID");
        System.out.println(indicator_id);
        if(table_id==2) { 
        String count_total="SELECT SUM(totalAchieved) FROM indicatorachievedcombined WHERE titleID='"+indicator_id+"' && reportingPeriod='"+quarter+"' && financialYear='"+year+"'";
      ppmt.rs1=ppmt.state1.executeQuery(count_total);
      if(ppmt.rs1.next()==true){
       total_achieved=ppmt.rs1.getInt(1);
       
       
          if(percentages==1){
           String count_districts="SELECT COUNT(resultID) FROM indicatorachievedcombined WHERE titleID='"+indicator_id+"' && reportingPeriod='"+quarter+"' && financialYear='"+year+"'";
      ppmt.rs2=ppmt.state2.executeQuery(count_districts);
      if(ppmt.rs2.next()==true){
        total_districts=ppmt.rs2.getInt(1); 
      }
      if(total_districts>0){
        total_achieved=(total_achieved/total_districts);
      }
      }   
      }
      } 
        
        
        
        if(table_id==1) { 
        String count_total="SELECT SUM(menAchieved),SUM(womenAchieved) FROM indicatorachieved WHERE titleID='"+indicator_id+"' && reportingPeriod='"+quarter+"' && financialYear='"+year+"'";
      ppmt.rs1=ppmt.state1.executeQuery(count_total);
      if(ppmt.rs1.next()==true){
       total_achieved=ppmt.rs1.getInt(1);
       total_achieved+=ppmt.rs1.getInt(2);
       
          if(percentages==1){
           String count_districts="SELECT COUNT(resultID) FROM indicatorachieved WHERE titleID='"+indicator_id+"' && reportingPeriod='"+quarter+"' && financialYear='"+year+"'";
      ppmt.rs2=ppmt.state2.executeQuery(count_districts);
      if(ppmt.rs2.next()==true){
        total_districts= ppmt.rs2.getInt(1); 
      }
      if(total_districts>0){
        total_achieved=(total_achieved/total_districts);
      }
      }   
      }
      }
      HSSFRow rw2=shet1.createRow(rws);
     rw2.setHeightInPoints(20);
     
    HSSFCell rw2cell0=rw2.createCell(0);
    HSSFCell rw2cell1=rw2.createCell(1);
     

      rw2cell0.setCellValue(ppmp_id);
      if(percentages==1){
      rw2cell1.setCellValue(total_achieved+"%");
      }
      else{
         
       rw2cell1.setCellValue(""+total_achieved);   
      }
      
      System.out.println(total_achieved);
//    total_achieved=total_districts=0;
    rws++;   
    
    
    // merging data from ppmt system 
    
    
    
    System.out.println("~~~~~~~~~~Data merging from ppmt to ppmp starts~~~~~~~~~~~~~~ ");
   
            if(quarter.equals("Q1")){
            Quarter="Quarter4";
            }
            else if(quarter.equals("Q2")){
            Quarter="Quarter1";
            }
            else if(quarter.equals("Q3")){
            Quarter="Quarter2";
            }
            else if(quarter.equals("Q4")){
            Quarter="Quarter3";
            }
            String year_id="";
            if(year.equals("2011")){
                year_id="1";
            }
            if(year.equals("2012")){
                year_id="2";
            }
            if(year.equals("2013")){
                year_id="3";
            }
            if(year.equals("2014")){
                year_id="17";
            }
            if(year.equals("2015")){
                year_id="18";
            }
        int cumulativeachievement=0;    
           // System.out.println("County :"+county+"District :"+district+"quarter :"+quarter+"id1 :"+id1+"id2: "+id2+"id3 :"+id3+"id4 :"+id4+"id5 :"+id5+"id6 :"+id6+"id7 :"+id7+"Year :"+year);
                         if(ppmp_id!=0 ){
String query="";                            
String checkexistingval="select * from Master_Record where Year_ID = '"+year_id+"' and I_ID = '"+ppmp_id+"'";                              

System.out.println(checkexistingval);
ppmp.rs3=ppmp.state3.executeQuery(checkexistingval);

while(ppmp.rs3.next()){
//      cumulative=0;
//             qtr1=0;qtr2=0;qtr3=0;qtr4=0;
           
//  query = "update Master_Record set "+Quarter+"='"+total_achieved+"' where resultID='"+ppmt.rs3.getString(1)+"'";
  String checkexistingvals="";
    if(year.equals("18")){
  checkexistingvals="select Quarter4 from Master_Record where Year_ID = '"+year_id+"' and I_ID = '"+ppmp_id+"'";                              
  System.out.println(checkexistingvals);}
  else{
   int yearid=Integer.parseInt(year)-1;
   checkexistingvals="select Quarter4 from Master_Record where Year_ID = '"+year_id+"' and I_ID = '"+ppmp_id+"'";                              
   System.out.println(checkexistingvals);
  }

ppmp.rs5=ppmp.state5.executeQuery(checkexistingvals);
    while(ppmp.rs5.next()){
    Quarter4= ppmp.rs5.getInt("Quarter4");
    
    }
     cumulativeachievement=ppmp.rs3.getInt("Quarter1")+ppmp.rs3.getInt("Quarter2")+ppmp.rs3.getInt("Quarter3")+Quarter4;
    System.out.println("cumulative "+cumulativeachievement+"  "+ppmp.rs3.getInt("Quarter1")+ppmp.rs3.getInt("Quarter2")+ppmp.rs3.getInt("Quarter3")+ppmp.rs3.getInt("Quarter4"));
//    #Checking target
            String getInputValues="select * from Input_Values where Indicator_ID = '"+ppmp_id+"' and Year_ID ='"+year_id+"'";
   
    System.out.println("mmm"+getInputValues);
    ppmp.rs6 = ppmp.state6.executeQuery(getInputValues);
   while(ppmp.rs6.next()){
   targets= ppmp.rs6.getString("Year_Target");
   baseline= ppmp.rs6.getString("Baseline");
     check_target= ppmp.rs6.getString("Year_Target");
   }
   System.out.println( targets +"   ____   "+baseline );
         
           System.out.println("TARGETS "+check_target );
          
//             if(check_target!=null && !check_target.equals("")){
                   System.out.println("TARGETS "+check_target );
          
                  if (check_target.contains("%")){
                     check_targ = check_target.replace("%","");
                     System.out.println("entered");
                             }
                  else{
                     check_targ = check_target;
                  
                  System.out.println("entered 2 ");
                  }
//                  return check_targ
//               #Calculating Percentage
              System.out.println("checktarget    "+check_targ +"   "+check_target);
                if (check_target.equals("0")){
                  percent = "#DIV/0!";}
                if (check_target.equals("TBD") || check_target.equals("NA") || check_target.equals("Survey") || check_target.equals("SURVEY")){
                  percent = "#VALUE!";}
                  else{
                     target = Integer.parseInt(check_targ);
                 System.out.println(" target as integer   "+target);
//                   if(target!=0 ){
//                  percent =(cumulativeachievement/ Integer.parseInt(check_targ)) * 100 +"%";}
               }
//               if (check_target.contains("%")){
//                  cumulativeachievement = cumulativeachievement+'%';
//               }
               //}
              if (check_target.equals("")|| check_target.equals(null)){
                  percent = "Target Nill";
               }
                 System.out.println(cumulativeachievement +" CHECK  "+percent);
                 
                 
                 
                 
//   #Getting the Cumulative Chooser
//   def get_cumulative_chooser():
//      connection = sqlite3.connect('PPMP_APHIAPLUS.sqlite')
//      cursor = connection.cursor()
                 int chooser=0;
                 
                 
                
//       String targetdob1= document.getElementById("target").value;
//        var cumulativedob= document.getElementById("cum").value;
//        if (cumulativedob == ""){
//        cumulativedob = 0;
//        }

//        String age = ((Integer.parseInt(cumulativedob) / (Integer.parseInt(targetdob1))) * 100) ;           //calculating cumulative 
//        age = Round(age)+"%";
       
        
    
                 
                 
    String cumulative_chooser ="select * from Indicators where Indicator_ID = '"+ppmp_id+"' ";
  //    for col in cursor.fetchall():
    ppmp.rs4= ppmp.state4.executeQuery(cumulative_chooser);
    System.out.println(cumulative_chooser);
    if(ppmp.rs4.next()){
         chooser =ppmp.rs4.getInt(6);
     }
      System.out.println(chooser);
           String percentages="";
            String cumulatives="";
               ArrayList qtrlist = new ArrayList();
           if(!qtrlist.isEmpty()){
           qtrlist.clear();
           
           }    
           if(ppmp.rs3.getString("Quarter1")!=null && !ppmp.rs3.getString("Quarter1").equals("")){
           qtr1=Integer.parseInt(ppmp.rs3.getString("Quarter1").trim().replace("%",""));
           }
            if(ppmp.rs3.getString("Quarter2")!=null && !ppmp.rs3.getString("Quarter2").equals("")){
           qtr2=Integer.parseInt(ppmp.rs3.getString("Quarter2").trim().replace("%",""));}
             if(ppmp.rs3.getString("Quarter3")!=null && !ppmp.rs3.getString("Quarter3").equals("")){
           qtr3=Integer.parseInt(ppmp.rs3.getString("Quarter3").trim().replace("%",""));}
              if(ppmp.rs3.getString("Quarter4")!=null && !ppmp.rs3.getString("Quarter4").equals("")){
           qtr4=Integer.parseInt(ppmp.rs3.getString("Quarter4").trim().replace("%",""));}
                    if (chooser== 1){
          
            qtrlist.add(qtr1);
            qtrlist.add(qtr2);
            qtrlist.add(qtr3);
            qtrlist.add(qtr4);
            cumulatives = Collections.max(qtrlist).toString();
            cumulative= Integer.parseInt(cumulatives);
            if(target!=0){
            percentages = (Math.round(cumulative / target * 100))+"%";
            }
            System.out.println( chooser +"__ "+cumulatives  +"  moh  "+ indicator_id+" __ "+cumulative +" __ "+qtr1+" __ "+qtr2+" __ "+qtr3+" __  "+qtr4);
                    }
//         #For getting cumulative value
         if (chooser== 2){
  
            cumulative = ppmp.rs3.getInt("Quarter1") + ppmp.rs3.getInt("Quarter2") + ppmp.rs3.getInt("Quarter3") + ppmp.rs3.getInt("Quarter4");
          System.out.println("pppp" + cumulative +" "+indicator_id +"___"+  ppmp.rs3.getInt("Quarter4"));
               if(target!=0){
              percentages = (Math.round(cumulative / target * 100))+"%";
               }
         }
            //percentages = (Math.round(cumulative/target) * 100))+"%";
         //#For getting last reported   
         if (chooser == 3){

            
            if (qtr1 != 0  && qtr4 != 0){
               cumulative = qtr1;
                       }
               
            if (qtr2 != 0 && qtr1 != 0  && qtr4 != 0){
               cumulative = qtr2;
            }
               
            if (qtr3 != 0 && qtr2 != 0 && qtr1 != 0  && qtr4 != 0){
               cumulative = qtr3;
            }
               
            if (qtr4 != 0 && qtr3 == 0 && qtr2 == 0 && qtr1 == 0){
               cumulative = qtr4;}
             //  percentage = (str(round((cumulative / int(target)),1) * 100))+'%'
               if(target!=0){
               percentages = (Math.round(cumulative / target * 100))+"%";
               }
               }
                
//         #For getting longitudinal indicators
         if (chooser == 4){
            qtr1 = ppmp.rs3.getInt(2);
            cumulative = ppmp.rs3.getInt(12);
            percentages =ppmp.rs3.getString(13);
            
                    }
         
          if (chooser == 5){
    
       ArrayList qtravg =new ArrayList();
       if(!qtravg.isEmpty()){
           qtravg.clear();
       }
        if (qtr1 !=0){
        qtravg.add(qtr1);
        }
        if (qtr2 != 0){
        qtravg.add(qtr2);
        }
        if (qtr3!=0){
        qtravg.add(qtr3);
        }
        if (qtr4!=0){
        qtravg.add(qtr4);
        }
   
        
        for (int i = 0; i < qtravg.size(); i++){
        sum += Integer.parseInt(qtravg.get(i).toString());
        }
  
        if(qtravg.size()!=0){
        avg = sum/qtravg.size();
        }
//        String cumul="";
//        cumul=Math.round(avg)+"%";
       cumulative= Math.round(avg);

        }
          
 
          String percents="";
          if(target!=0){
        percents = (Math.round(cumulative/target) * 100)+"%";
          }

        if(percent.equals("NaN%")){
        percents = "Target NIL";

        }
        if (percent.equals("Infinity%")){
        percents = "#div/0!";

        }

//          System.out.println( percent+"___"+cumulative +" moh "+indicator_id + "_____"+ percentages +" qtr1  "+ qtr1 +" qtr2 "+ qtr2+" qtr3  "+qtr3 +"  qtr4   "+qtr4); 
           if(target!=0){
          System.out.println(indicator_id+" mmmm "+cumulative +"_____"+target+"____ percents "+ cumulative/target) ;
           }
//   and "+Quarter+"='"+Quarter+"'
  String Achieved="";
  String cumul="";
   if(percentages1.equals("1")){
 Achieved= total_achieved+"%";
 cumul=cumulative+"%";
  }
  else{
   Achieved=""+total_achieved;
   cumul=cumulative+"";
  }
    query="UPDATE Master_Record SET "+Quarter+"='"+Achieved+"',Cum_Yearly_Achievements='"+cumul+"',PAchieved_YearTarg='"+percent+"',Target='"+targets+"', Baseline='"+baseline+"' where I_ID ='"+ppmp_id+"' and Year_ID = '"+year_id+"'";
//   
//  
   System.out.println(query);
}
//else{
//    query="INSERT INTO Master_Record SET "+Quarter+"='"+total_achieved+"',Cum_Yearly_Achievements='"+cumulativeachievement+"',PAchieved_YearTarg='"+percent+"' , I_ID ='"+indicator_id+"' , Year_ID = '"+year+"'";
//   
//}               
                            try {
               ppmp.state.executeUpdate(query);
                replyback = "<font color=\"orange\"><b>Importing completed </b></font>";
                 session.setAttribute("replyback",replyback);
            } catch (SQLException se) {
                erroroccured = 1;
                unuploadedrows += ppmp_id+ " , ";
                System.out.println(se.toString());
                //System.out.println(se.printStackTrace());
  replyback = "<font color=\"orange\"><b>Some rows not uploaded "+unuploadedrows+" </b></font> ";
  session.setAttribute("replyback",replyback);
            }

avg=0;
sum=0;
cumulative=0;
    qtr1=0;
    qtr2=0;
    qtr3=0;
    qtr4=0;
    percentage=0;  
   
                         }
    
    
    
//    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//wb.write(outByteStream);
//byte [] outArray = outByteStream.toByteArray();
//response.setContentType("application/ms-excel");
//response.setContentLength(outArray.length);
//response.setHeader("Expires:", "0"); // eliminates browser caching
//response.setHeader("Content-Disposition", "attachment; filename=Indicator_Report.xls");
//OutputStream outStream = response.getOutputStream();
//outStream.write(outArray);
//outStream.flush();
    }
     response.sendRedirect("indicator_report.jsp");
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
            Logger.getLogger(indicator_results_report.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(indicator_results_report.class.getName()).log(Level.SEVERE, null, ex);
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
