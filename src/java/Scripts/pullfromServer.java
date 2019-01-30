/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scripts;

import PP.Admin.dbConnect;
import PP.Admin.dbConnect1;
import PP.Admin.dbConnectTemp;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class pullfromServer extends HttpServlet {

    String activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter;
    String activityID1,titleID1,unit1,countyID1,districtID1,activityTitle1,activityOthers1,startDate1,endDate1,total1,FinancialYear1,Quarter1;
    String ResultID,County,District,menAchieved,womenAchieved, reportingPeriod,financialYear,titleIDs="";
    String ResultID1,County1,District1,totalAchieved, reportingPeriod1,financialYear1,titleIDs1="";
    String ActivityID,Activity,IndicatorID="";
    int already_added;
HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int numberofqueries=0;
        int existingdata=0;
      
        session=request.getSession();
        
         //mergeClerkData();
dbConnectTemp conn = new dbConnectTemp();
dbConnect conn1 = new dbConnect();

System.out.println("Merging Data");


String lasttimestamp="";
String lasttimestampid="";


 conn.rs = conn.state.executeQuery("select MAX(timestampid) from timestamp");
                    if (conn.rs.next()) {
                        
lasttimestampid=conn.rs.getString(1);
                        
      conn.rs1=conn.state1.executeQuery("select timestamp from timestamp where timestampid='"+conn.rs.getString(1)+"'");
          
      if(conn.rs1.next()){
       lasttimestamp = conn.rs1.getString("timestamp");

      System.out.println(conn.rs1.getString("timestamp"));
      
                        }
                        
     
                        System.out.println("Timestamp id::"+conn.rs.getString(1));
                        
                        
                        //lasttimestamp = conn.rs.getString(1);

                    }


                String timestampwhere=" timestamp>='" + lasttimestamp + "' ";
                    
                    
//read data from the new database-indicatoractivities1 
String selector="SELECT * FROM indicatoractivities1 WHERE activityID!='' and "+timestampwhere;
        System.out.println(""+selector);

conn.rs=conn.state.executeQuery(selector);
while(conn.rs.next()){
   
    
    already_added=0;
    activityID=titleID=unit=countyID=districtID=activityTitle=activityOthers=startDate=endDate=total=FinancialYear=Quarter=""; 
    
         activityID=conn.rs.getString(1);
         titleID=conn.rs.getString(2);
         unit=conn.rs.getString(3);
         countyID=conn.rs.getString(4);
         districtID=conn.rs.getString(5);
         activityTitle=conn.rs.getString(6);
         activityOthers=conn.rs.getString(7);
         startDate=conn.rs.getString(8);
         endDate=conn.rs.getString(9);
         total=conn.rs.getString(10);
         FinancialYear=conn.rs.getString(11);
         Quarter=conn.rs.getString(12); 
  
 String check_if_exist="SELECT * FROM indicatoractivities1 WHERE activityID='"+activityID+"' AND titleID='"+titleID+"' AND unit='"+unit+"' AND countyID='"+countyID+"' AND districtID='"+districtID+"'"
 + " AND activityTitle='"+activityTitle+"' AND activityOthers='"+activityOthers+"' AND startDate='"+startDate+"' AND endDate='"+endDate+"' AND total='"+total+"' AND FinancialYear='"+FinancialYear+"' AND Quarter='"+Quarter+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){
     already_added=1; 
 
  System.out.println("Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatoractivities1 (activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter)"
          + "VALUES('"+activityID+"','"+titleID+"','"+unit+"','"+countyID+"','"+districtID+"','"+activityTitle+"','"+activityOthers+"','"+startDate+"','"+endDate+"','"+total+"','"+FinancialYear+"','"+Quarter+"')"   ;
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state2.executeUpdate(inserter);  
   
   
   
 }   
    
}


// begininng of new select for indicatoractivities
String indicatoractivities="SELECT * FROM indicatoractivities WHERE activityID!='' and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatoractivities);
while(conn.rs2.next()){
   
    
    already_added=0;
    activityID1=titleID1=unit1=countyID1=districtID1=activityTitle1=activityOthers1=startDate1=endDate1=total1=FinancialYear1=Quarter1=""; 
    
         activityID1=conn.rs2.getString(1);
         unit1=conn.rs2.getString(2);
         titleID1=conn.rs2.getString(3);
         countyID1=conn.rs2.getString(4);
         districtID1=conn.rs2.getString(5);
         activityTitle1=conn.rs2.getString(6);
         activityOthers1=conn.rs2.getString(7);
         startDate1=conn.rs2.getString(8);
         endDate1=conn.rs2.getString(9);
         total1=conn.rs2.getString(12);
         FinancialYear1=conn.rs2.getString(13);
         Quarter1=conn.rs2.getString(14); 
  
 String check_if_exist="SELECT * FROM indicatoractivities WHERE activityID='"+activityID1+"' AND titleID='"+titleID1+"' AND unit='"+unit1+"' AND countyID='"+countyID1+"' AND districtID='"+districtID1+"'"
 + " AND activityTitle='"+activityTitle1+"' AND activityOthers='"+activityOthers1+"' AND startDate='"+startDate1+"' AND endDate='"+endDate1+"' AND subtotals='"+total1+"' AND FinancialYear='"+FinancialYear1+"' AND Quarter='"+Quarter1+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){already_added=1; 
 
  System.out.println("Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatoractivities (activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,subtotals,FinancialYear,Quarter)"
          + "VALUES('"+activityID1+"','"+titleID1+"','"+unit1+"','"+countyID1+"','"+districtID1+"','"+activityTitle1+"','"+activityOthers1+"','"+startDate1+"','"+endDate1+"','"+total1+"','"+FinancialYear1+"','"+Quarter1+"')"   ;
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}
//end of indicatoractivities merge



//start of indicatorachieved  merge

String indicatorachieved="SELECT * FROM indicatorachieved WHERE resultID!='' and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatorachieved);
while(conn.rs2.next()){
   
    
    already_added=0;
    ResultID=County=District=menAchieved=womenAchieved=reportingPeriod=financialYear=titleIDs="";
    
         ResultID=conn.rs2.getString(1);
         County=conn.rs2.getString(2);
         District=conn.rs2.getString(3);
         menAchieved=conn.rs2.getString(4);
         womenAchieved=conn.rs2.getString(5);
         reportingPeriod=conn.rs2.getString(6);
         financialYear=conn.rs2.getString(7);
         titleIDs=conn.rs2.getString(8);
     
 String check_if_exist="SELECT * FROM indicatorachieved  WHERE resultID='"+ResultID+"' AND County='"+County+"' AND District='"+District+"' AND menAchieved='"+menAchieved+"' AND womenAchieved='"+womenAchieved+"'"
 + " AND reportingPeriod='"+reportingPeriod+"' AND financialYear='"+financialYear+"' AND titleID='"+titleIDs+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){
     
     already_added=1; 
 
  System.out.println("Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatorachieved (resultID,County,District,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
          + "VALUES('"+ResultID+"','"+County+"','"+District+"','"+menAchieved+"','"+womenAchieved+"','"+reportingPeriod+"','"+financialYear+"','"+titleIDs+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  //conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}

// end of indicatorachieved //

// start of indicatorachievedcombined 


String indicatorachievedcombined="SELECT * FROM indicatorachievedcombined WHERE resultID!='' and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatorachievedcombined);
while(conn.rs2.next()){
   
    
    already_added=0;
    ResultID1=County1=District1=totalAchieved=reportingPeriod1=financialYear1=titleIDs1="";
    
         ResultID1=conn.rs2.getString(1);
         County1=conn.rs2.getString(2);
         District1=conn.rs2.getString(3);
         totalAchieved=conn.rs2.getString(4);
         reportingPeriod1=conn.rs2.getString(5);
         financialYear1=conn.rs2.getString(6);
         titleIDs1=conn.rs2.getString(7);
     
 String check_if_exist="SELECT * FROM indicatorachievedcombined  WHERE resultID='"+ResultID1+"' AND county='"+County1+"' AND district='"+District1+"' AND totalAchieved='"+totalAchieved+"'"
 + " AND reportingPeriod='"+reportingPeriod1+"' AND financialYear='"+financialYear1+"' AND titleID='"+titleIDs1+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()){already_added=1; 
 
  System.out.println("Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
          + "VALUES('"+ResultID1+"','"+County1+"','"+District1+"','"+totalAchieved+"','"+reportingPeriod1+"','"+financialYear1+"','"+titleIDs1+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  //conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}
// end of indicatorachievedcombined 



//start of indicatoractivity 

String indicatoractivity=" SELECT * FROM indicatoractivity WHERE ActivityID!='' and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatoractivity);
while(conn.rs2.next()){
   
    
    already_added=0;
  ActivityID=Activity=IndicatorID="";
    
         ActivityID=conn.rs2.getString(1);
         Activity=conn.rs2.getString(2);
         IndicatorID=conn.rs2.getString(3);
        
     
 String check_if_exist="SELECT * FROM indicatoractivity WHERE ActivityID='"+ActivityID+"' AND Activity='"+Activity.replace("'", "") +"' AND IndicatorID='"+IndicatorID+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){
     already_added=1; 
 
  System.out.println("Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatoractivity(ActivityID,Activity,IndicatorID)"
          + "VALUES('"+ActivityID+"','"+Activity.replace("'", "")+"','"+IndicatorID+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}

// end of indicatoractivity 



String wholemsg="<font color=\"green\">Data Merging Completed </font>";

if(numberofqueries>0){

wholemsg+="<br/><font color=\"orange\"> <b>"+numberofqueries+"</b> new Inserts done</font>.";


                            conn.state.executeUpdate("update timestamp set datasend='yes' where timestampid='" + lasttimestampid + "'");
  Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        
            
                            
                            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String mdate;

                           Date mydate = new Date();
                        mdate = formatter.format(mydate);
                            
                            
String daytime=""+year+"-"+month+"-"+day;
                            // a new timestamp that will be called next time a backup is being created.

                            conn.state.executeUpdate("insert into timestamp (timestamp,datasend) values('"+mdate+"','No')");

}
else{

    wholemsg+="<br/><font color=\"orange\"> No new Inserts done</font>";

}

if(existingdata>0){

//wholemsg+="<br/><b>"+existingdata+"</b> users in this file are already added to the Master database";
}



session.setAttribute("msg", wholemsg);

conn.state.close();
conn1.state1.close();


  response.sendRedirect("importdata.jsp");      
        
        
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
            Logger.getLogger(pullfromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(pullfromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>





public  void mergeClerkData() throws SQLException{

int numberofqueries=0;
        int existingdata=0;
        
dbConnectTemp conn = new dbConnectTemp();
dbConnect conn1 = new dbConnect();     
        
        
String lasttimestamp="";


 conn.rs = conn.state.executeQuery("select MAX(timestampid) from timestamp");
                    if (conn.rs.next()) {

                        
      conn.rs1=conn.state1.executeQuery("select timestamp from timestamp where timestampid='"+conn.rs.getString(1)+"'");
          
      if(conn.rs1.next()){
       lasttimestamp = conn.rs1.getString("timestamp");

      System.out.println(conn.rs1.getString("timestamp"));
      
      }
                        
     
                        System.out.println("Timestamp id::"+conn.rs.getString(1));
                        
                        
                        //lasttimestamp = conn.rs.getString(1);

                    }


                String timestampwhere=" timestamp>='" + lasttimestamp + "' ";
                    

        
        
        
        
        

        
//dbConnect conn = new dbConnect();
//dbConnect1 conn1 = new dbConnect1();

System.out.println("Merging Data for data clerk ");

//read data from the new database-indicatoractivities1 
String selector="SELECT * FROM indicatoractivities1 WHERE activityID!=''   and "+timestampwhere;
conn.rs=conn.state.executeQuery(selector);
while(conn.rs.next()){
   
    
    already_added=0;
    activityID=titleID=unit=countyID=districtID=activityTitle=activityOthers=startDate=endDate=total=FinancialYear=Quarter=""; 
    
         activityID=conn.rs.getString(1);
         titleID=conn.rs.getString(2);
         unit=conn.rs.getString(3);
         countyID=conn.rs.getString(4);
         districtID=conn.rs.getString(5);
         activityTitle=conn.rs.getString(6);
         activityOthers=conn.rs.getString(7);
         startDate=conn.rs.getString(8);
         endDate=conn.rs.getString(9);
         total=conn.rs.getString(10);
         FinancialYear=conn.rs.getString(11);
         Quarter=conn.rs.getString(12); 
  
 String check_if_exist="SELECT * FROM indicatoractivities1 WHERE activityID='"+activityID+"' AND titleID='"+titleID+"' AND unit='"+unit+"' AND countyID='"+countyID+"' AND districtID='"+districtID+"'"
 + " AND activityTitle='"+activityTitle+"' AND activityOthers='"+activityOthers+"' AND startDate='"+startDate+"' AND endDate='"+endDate+"' AND total='"+total+"' AND FinancialYear='"+FinancialYear+"' AND Quarter='"+Quarter+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){
     already_added=1; 
 
  System.out.println("Clerk Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatoractivities1 (activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter)"
          + "VALUES('"+activityID+"','"+titleID+"','"+unit+"','"+countyID+"','"+districtID+"','"+activityTitle+"','"+activityOthers+"','"+startDate+"','"+endDate+"','"+total+"','"+FinancialYear+"','"+Quarter+"')"   ;
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state2.executeUpdate(inserter);  
   
   
   
 }   
    
}


// begininng of new select for indicatoractivities
String indicatoractivities="SELECT * FROM indicatoractivities WHERE activityID!=''   and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatoractivities);
while(conn.rs2.next()){
   
    
    already_added=0;
    activityID1=titleID1=unit1=countyID1=districtID1=activityTitle1=activityOthers1=startDate1=endDate1=total1=FinancialYear1=Quarter1=""; 
    
         activityID1=conn.rs2.getString(1);
         unit1=conn.rs2.getString(2);
         titleID1=conn.rs2.getString(3);
         countyID1=conn.rs2.getString(4);
         districtID1=conn.rs2.getString(5);
         activityTitle1=conn.rs2.getString(6);
         activityOthers1=conn.rs2.getString(7);
         startDate1=conn.rs2.getString(8);
         endDate1=conn.rs2.getString(9);
         total1=conn.rs2.getString(12);
         FinancialYear1=conn.rs2.getString(13);
         Quarter1=conn.rs2.getString(14); 
  
 String check_if_exist="SELECT * FROM indicatoractivities WHERE activityID='"+activityID1+"' AND titleID='"+titleID1+"' AND unit='"+unit1+"' AND countyID='"+countyID1+"' AND districtID='"+districtID1+"'"
 + " AND activityTitle='"+activityTitle1+"' AND activityOthers='"+activityOthers1+"' AND startDate='"+startDate1+"' AND endDate='"+endDate1+"' AND subtotals='"+total1+"' AND FinancialYear='"+FinancialYear1+"' AND Quarter='"+Quarter1+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){already_added=1; 
 
  System.out.println("Clerk Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatoractivities (activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,subtotals,FinancialYear,Quarter)"
          + "VALUES('"+activityID1+"','"+titleID1+"','"+unit1+"','"+countyID1+"','"+districtID1+"','"+activityTitle1+"','"+activityOthers1+"','"+startDate1+"','"+endDate1+"','"+total1+"','"+FinancialYear1+"','"+Quarter1+"')"   ;
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}
//end of indicatoractivities merge



//start of indicatorachieved  merge

String indicatorachieved="SELECT * FROM indicatorachieved WHERE resultID!=''   and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatorachieved);
while(conn.rs2.next()){
   
    
    already_added=0;
    ResultID=County=District=menAchieved=womenAchieved=reportingPeriod=financialYear=titleIDs="";
    
         ResultID=conn.rs2.getString(1);
         County=conn.rs2.getString(2);
         District=conn.rs2.getString(3);
         menAchieved=conn.rs2.getString(4);
         womenAchieved=conn.rs2.getString(5);
         reportingPeriod=conn.rs2.getString(6);
         financialYear=conn.rs2.getString(7);
         titleIDs=conn.rs2.getString(8);
     
 String check_if_exist="SELECT * FROM indicatorachieved  WHERE resultID='"+ResultID+"' AND County='"+County+"' AND District='"+District+"' AND menAchieved='"+menAchieved+"' AND womenAchieved='"+womenAchieved+"'"
 + " AND reportingPeriod='"+reportingPeriod+"' AND financialYear='"+financialYear+"' AND titleID='"+titleIDs+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){
     
     already_added=1; 
 
  System.out.println("Clerk Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO  indicatorachieved (resultID,County,District,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
          + "VALUES('"+ResultID+"','"+County+"','"+District+"','"+menAchieved+"','"+womenAchieved+"','"+reportingPeriod+"','"+financialYear+"','"+titleIDs+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}

// end of indicatorachieved //

// start of indicatorachievedcombined 


String indicatorachievedcombined="SELECT * FROM indicatorachievedcombined WHERE resultID!=''   and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatorachievedcombined);
while(conn.rs2.next()){
   
    
    already_added=0;
    ResultID1=County1=District1=totalAchieved=reportingPeriod1=financialYear1=titleIDs1="";
    
         ResultID1=conn.rs2.getString(1);
         County1=conn.rs2.getString(2);
         District1=conn.rs2.getString(3);
         totalAchieved=conn.rs2.getString(4);
         reportingPeriod1=conn.rs2.getString(5);
         financialYear1=conn.rs2.getString(6);
         titleIDs1=conn.rs2.getString(7);
     
 String check_if_exist="SELECT * FROM indicatorachievedcombined  WHERE resultID='"+ResultID1+"' AND county='"+County1+"' AND district='"+District1+"' AND totalAchieved='"+totalAchieved+"'"
 + " AND reportingPeriod='"+reportingPeriod1+"' AND financialYear='"+financialYear1+"' AND titleID='"+titleIDs1+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()){already_added=1; 
 
  System.out.println("Clerk Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
          + "VALUES('"+ResultID1+"','"+County1+"','"+District1+"','"+totalAchieved+"','"+reportingPeriod1+"','"+financialYear1+"','"+titleIDs1+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}
// end of indicatorachievedcombined 



//start of indicatoractivity 

String indicatoractivity="SELECT * FROM indicatoractivity WHERE ActivityID!=''   and "+timestampwhere;
conn.rs2=conn.state3.executeQuery(indicatoractivity);
while(conn.rs2.next()){
   
    
    already_added=0;
  ActivityID=Activity=IndicatorID="";
    
         ActivityID=conn.rs2.getString(1);
         Activity=conn.rs2.getString(2);
         IndicatorID=conn.rs2.getString(3);
        
     
 String check_if_exist="SELECT * FROM indicatoractivity WHERE ActivityID='"+ActivityID+"' AND Activity='"+Activity+"' AND IndicatorID='"+IndicatorID+"'";

  System.out.println(check_if_exist+"\n");
 
 conn1.rs3=conn1.state1.executeQuery(check_if_exist);
 if(conn1.rs3.next()==true){
     already_added=1; 
 
  System.out.println("Clerk Data already added");
 existingdata++;
 }
 else {
  String inserter="REPLACE INTO indicatoractivity(ActivityID,Activity,IndicatorID)"
          + "VALUES('"+ActivityID+"','"+Activity+"','"+IndicatorID+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}

// end of indicatoractivity 



String wholemsg="<font color=\"green\">Data Merging Completed </font>";
System.out.println(wholemsg);
if(numberofqueries>0){

wholemsg+="<br/><font color=\"orange\"> <b>"+numberofqueries+"</b> new Inserts done</font>.";
System.out.println(wholemsg);
}
else{

    wholemsg+="<br/><font color=\"orange\"> No new Inserts done</font>";
System.out.println(wholemsg);
}

if(existingdata>0){

//wholemsg+="<br/><b>"+existingdata+"</b> users in this file are already added to the Master database";
}



//session.setAttribute("msg", wholemsg);

conn.state.close();
conn1.state1.close();




}}
