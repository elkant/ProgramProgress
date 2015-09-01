/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
import PP.Admin.dbConnect1;
import PP.Admin.dbConnectTemp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class concatenateDataClerk extends HttpServlet {

      String activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter;
    String activityID1,titleID1,unit1,countyID1,districtID1,activityTitle1,activityOthers1,startDate1,endDate1,total1,FinancialYear1,Quarter1;
    String ResultID,County,District,menAchieved,womenAchieved, reportingPeriod,financialYear,titleIDs="";
    String ResultID1,County1,District1,totalAchieved, reportingPeriod1,financialYear1,titleIDs1="";
    String ActivityID,Activity,IndicatorID="";
    int already_added;
HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
        
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
            Logger.getLogger(concatenateDataClerk.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(concatenateDataClerk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    
    
    
    public  void mergeClerkData() throws SQLException{

int numberofqueries=0;
        int existingdata=0;
        
       
        
dbConnect conn = new dbConnect();
dbConnect1 conn1 = new dbConnect1();

System.out.println("Merging Data");

//read data from the new database-indicatoractivities1 
String selector="SELECT * FROM indicatoractivities1 WHERE activityID!=''";
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
  String inserter="INSERT INTO indicatoractivities1 (activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter)"
          + "VALUES('"+activityID+"','"+titleID+"','"+unit+"','"+countyID+"','"+districtID+"','"+activityTitle+"','"+activityOthers+"','"+startDate+"','"+endDate+"','"+total+"','"+FinancialYear+"','"+Quarter+"')"   ;
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state2.executeUpdate(inserter);  
   
   
   
 }   
    
}


// begininng of new select for indicatoractivities
String indicatoractivities="SELECT * FROM indicatoractivities WHERE activityID!=''";
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
  String inserter="INSERT INTO indicatoractivities (activityID,titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,subtotals,FinancialYear,Quarter)"
          + "VALUES('"+activityID1+"','"+titleID1+"','"+unit1+"','"+countyID1+"','"+districtID1+"','"+activityTitle1+"','"+activityOthers1+"','"+startDate1+"','"+endDate1+"','"+total1+"','"+FinancialYear1+"','"+Quarter1+"')"   ;
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}
//end of indicatoractivities merge



//start of indicatorachieved  merge

String indicatorachieved="SELECT * FROM indicatorachieved WHERE resultID!=''";
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
  String inserter="INSERT INTO indicatorachieved (resultID,County,District,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
          + "VALUES('"+ResultID+"','"+County+"','"+District+"','"+menAchieved+"','"+womenAchieved+"','"+reportingPeriod+"','"+financialYear+"','"+titleIDs+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}

// end of indicatorachieved //

// start of indicatorachievedcombined 


String indicatorachievedcombined="SELECT * FROM indicatorachievedcombined WHERE resultID!=''";
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
  String inserter="INSERT INTO indicatorachievedcombined(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
          + "VALUES('"+ResultID1+"','"+County1+"','"+District1+"','"+totalAchieved+"','"+reportingPeriod1+"','"+financialYear1+"','"+titleIDs1+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}
// end of indicatorachievedcombined 



//start of indicatoractivity 

String indicatoractivity="SELECT * FROM indicatoractivity WHERE ActivityID!=''";
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
 
  System.out.println("Data already added");
 existingdata++;
 }
 else {
  String inserter="INSERT INTO indicatoractivity(ActivityID,Activity,IndicatorID)"
          + "VALUES('"+ActivityID+"','"+Activity+"','"+IndicatorID+"')";
  
  System.out.println("_"+inserter+"\n");
  numberofqueries++;
  conn1.state4.executeUpdate(inserter);  
   
   
   
 }   
    
}

// end of indicatoractivity 



String wholemsg="<font color=\"green\">Data Merging Completed </font>";

if(numberofqueries>0){

wholemsg+="<br/><font color=\"orange\"> <b>"+numberofqueries+"</b> new Inserts done</font>.";

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




}
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
 