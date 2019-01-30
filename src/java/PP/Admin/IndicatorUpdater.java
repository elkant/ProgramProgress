/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author COMPAQ
 */
public class IndicatorUpdater extends HttpServlet {

    
    String county = "";
           String[] district;
           String activity = "";
           String startdate = "";
           String enddate = "";
           String women  = "";
           String men = "";
           String subtotal = "";
           String new_county = "";
           String[] new_district;
           String new_activity = "";
           String new_startdate = "";
           String new_enddate = "";
           String new_women  = "";
           String new_men = "";
           String new_subtotal = "";
           String new_dist="";
           String dist="";
           String achievedw="";
           String achievedm="";
           String baselineid="";
           int activitys=0;
           String financial= "";
           String quarter= "";
int counter=0;
       
           HttpSession session;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
 String computername = InetAddress.getLocalHost().getHostName();
        String Replytext="";
        
             dbConnect conn = new dbConnect(); 
     county = "";
         
            activity = "";
            startdate = "";
            enddate = "";
            women  = "";
            men = "";
            subtotal = "";
            new_county = "";
         
            new_activity = "";
            new_startdate = "";
            new_enddate = "";
            new_women  = "";
            new_men = "";
            new_subtotal = "";
            new_dist="";
            dist="";
            achievedw="";
            achievedm="";
            baselineid="";
            activitys=0;
            financial= "";
            quarter= "";
 counter=0;
        
        
         Date date= new Date();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(date);
        
     
        
        
          try {
           
              String unit = request.getParameter("unit");
              session = request.getSession(true);
        session.getAttribute("title");
        String title = (String)session.getAttribute("title");
        //String title = request.getParameter("title");
        
        String id="";
           
        String titleUpdate="";
        if(request.getParameter("titles")!=null && !request.getParameter("titles").equals("")){
           titleUpdate= request.getParameter("titles");
        }
         if(request.getParameter("financial")!=null && request.getParameter("financial")!=""){
            financial = request.getParameter("financial");
            }
            if(request.getParameter("quarter")!=null && request.getParameter("quarter")!=""){
            quarter = request.getParameter("quarter");}
        
        System.out.println(financial+"  "+quarter);
        
//            int num1="";
            int num=0;
            //if(request.getParameter("counter")!=null ){
            if(request.getParameter("old_rows")!=null  && !request.getParameter("old_rows").equals("")){
           
           num =  Integer.parseInt(request.getParameter("old_rows"));
             }
     String activityOthers="";     
             
for(int i=1 ;i<=num;i++)
{   dist="";
        
          if(request.getParameter("county_"+i)!= null && request.getParameter("county_"+i)!= "")
          {
           county = request.getParameter("county_"+i);
}
         if(request.getParameter("unit_"+i)!=null){ 
          unit = request.getParameter("unit_"+i);
         }
          
          if(request.getParameterValues("district_"+i)!= null && !(request.getParameterValues("district_"+i).equals(""))){
           district = request.getParameterValues("district_"+i);
           for(int y=0;y<district.length; y++){
           dist+=district[y]+"_";
           
           }}
          if(request.getParameter("activity_"+i)!=null && request.getParameter("activity_"+i)!=""){
           activity = request.getParameter("activity_"+i);}
          if(request.getParameter("startdate_"+i)!=null && request.getParameter("startdate_"+i)!=""){
            startdate = request.getParameter("startdate_"+i);}
          if(request.getParameter("enddate_"+i)!=null && request.getParameter("enddate_"+i)!="")
          {
           enddate = request.getParameter("enddate_"+i);}
          
            if(request.getParameter("subtotal_"+i)!=null && request.getParameter("subtotal_"+i)!=""){
            subtotal = request.getParameter("subtotal_"+i);}
            if(request.getParameter("activityOthers_"+i)!=null && request.getParameter("activityOthers_"+i)!=""){
            activityOthers = request.getParameter("activityOthers_"+i);}
        
           
            if(request.getParameter("id"+i)!=null && request.getParameter("id"+i)!=""){
            id = request.getParameter("id"+i);}
           
            
            
               
              
          
           
       String query1 = "UPDATE indicatoractivities SET unit='"+unit+"',countyID='"+county+"',DistrictID='"+dist+"',activityTitle='"+activity+"',activityOthers='"+activityOthers+"',startDate='"+startdate+"',endDate='"+enddate+"',subtotals='"+subtotal+"',timestamp='"+formattedDate+"' WHERE activityID='"+id+"' ";

            System.out.println(query1);
                try {
                                               conn.state.executeUpdate(query1);
                                              
                                           } catch (SQLException ex) {
                                               Logger.getLogger(IndicatorActivitiesServlet.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
                }

String numbers="";
int numbers1=0;

String new_activityOthers="";
String new_unit="";
if(request.getParameter("newrows")!=null && !"".equals(request.getParameter("newrows"))){
           numbers =  request.getParameter("newrows");
              numbers1 = Integer.parseInt(numbers);}

for(int j=1;j<=numbers1;j++){
    new_dist="";
     if(request.getParameter("financial")!=null && !request.getParameter("financial").equals("")){
            financial = request.getParameter("financial");
            }
            if(request.getParameter("quarter")!=null && !request.getParameter("quarter").equals("")){
            quarter = request.getParameter("quarter");}
    
    
    
  if(request.getParameter("new_county_"+j)!= null && request.getParameter("new_county_"+j)!= "")
          {
           new_county = request.getParameter("new_county_"+j);
}
  if(request.getParameter("new_unit_"+j)!= null && !"".equals(request.getParameter("new_unit_"+j)))
          {
           new_unit = request.getParameter("new_unit_"+j);
}
          if(request.getParameterValues("new_district_"+j)!= null && !request.getParameterValues("new_district_"+j).equals("")){
           new_district = request.getParameterValues("new_district_"+j);
           for(int y=0;y<new_district.length; y++){
           new_dist+=new_district[y]+"_";
            
           }}
          if(request.getParameter("new_activity_"+j)!=null && !"".equals(request.getParameter("new_activity_"+j))){
           new_activity = request.getParameter("new_activity_"+j);}
          if(request.getParameter("new_startdate_"+j)!=null && !"".equals(request.getParameter("new_startdate_"+j))){
           new_startdate = request.getParameter("new_startdate_"+j);}
          if(request.getParameter("new_enddate_"+j)!=null && !"".equals(request.getParameter("new_enddate_"+j)))
          {
           new_enddate = request.getParameter("new_enddate_"+j);}
          
            if(request.getParameter("new_subtotal_"+j)!=null && !"".equals(request.getParameter("new_subtotal_"+j))){
            new_subtotal = request.getParameter("new_subtotal_"+j);}
            if(request.getParameter("new_activityOthers_"+j)!=null && !"".equals(request.getParameter("new_activityOthers_"+j))){
            new_activityOthers = request.getParameter("new_activityOthers_"+j);}
//if(new_activity.equals("1037")){
//   if(new_activityOthers!=null && !new_activityOthers.equals("")) {
//       query = "INSERT INTO indicatoractivities SET activityID='"+ uniqueid().trim()+"',unit='"+new_unit+"',titleID='"+titleUpdate+"',countyID='"+new_county+"',DistrictID='"+new_dist+"',activityTitle='"+new_activity+"',activityOthers='"+new_activityOthers+"',startDate='"+new_startdate+"',endDate='"+new_enddate+"',subtotals='"+new_subtotal+"',FinancialYear='"+financial+"',Quarter='"+quarter+"' ";
//
//   }
//   
//}
//else{
  String query = "INSERT INTO indicatoractivities SET activityID='"+ uniqueid().trim()+"',unit='"+new_unit+"',titleID='"+titleUpdate+"',countyID='"+new_county+"',DistrictID='"+new_dist+"',activityTitle='"+new_activity+"',activityOthers='"+new_activityOthers+"',startDate='"+new_startdate+"',endDate='"+new_enddate+"',subtotals='"+new_subtotal+"',FinancialYear='"+financial+"',Quarter='"+quarter+"' ";
//}
            System.out.println(query);
                try {
                    
                    
                       // conn.rs6=conn.state6.executeQuery("select * from indicatoractivities where titleID='"+titleUpdate+"' and activityTitle='"+new_activity+"' and activityTitle!='"+1031+"' and DistrictID='"+new_dist+"' and Quarter='"+quarter+"' and FinancialYear='"+financial+"'");
                        //if(!conn.rs6.next()){
                     if(titleUpdate!=null && !titleUpdate.equals("") && !new_subtotal.equals("") && new_subtotal!=null && new_county!=null && !new_county.equals("") && new_activity!=null && !new_activity.equals("") && financial!=null && !financial.equals("") && quarter!=null && !quarter.equals("")){
                                               conn.state.executeUpdate(query);
                    }
                                                if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted  data for titleID is "+titleUpdate+" and Quarter is "+quarter+" and Financial Year is "+financial+" for activities',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);
                        }
//                        else{
//                        
//                        Replytext+=" Data for District "+dist+" for Quarter "+quarter+"  "+financial +" is already added <br/>";
//                        
//                        System.out.println(Replytext);
//                        
//                        }
                    
                    
                    
                    
                    
                    
                    
                   
                                              
                                           } catch (SQLException ex) {
                                               Logger.getLogger(IndicatorActivitiesServlet.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }


}






          String count="";
          int counter=0;

                String districts="";
                String resultID="";
                 if(request.getParameter("count")!=null && !request.getParameter("count").equals("")){
            //count = request.getParameter("count");
            count = request.getParameter("count");
            counter = Integer.parseInt(count);
       
                 }
                 
                 System.out.println("count"+ count);
                 String updateDists="";
           for(int k=1 ;k<=counter;k++)
{  
        if(request.getParameter("achievedm_"+k)!=null && !"".equals(request.getParameter("achievedm_"+k))){
            achievedm = request.getParameter("achievedm_"+k);
        
        }
       
            if(request.getParameter("achievedw_"+k)!=null && request.getParameter("achievedw_"+k)!=""){
            achievedw = request.getParameter("achievedw_"+k);
             
            }
           
            if(request.getParameter("districts_"+k)!=null && request.getParameter("districts_"+k)!=""){
            updateDists = request.getParameter("districts_"+k);
           
            }
           
            if(request.getParameter("resultID_"+k)!=null && request.getParameter("resultID_"+k)!=""){
            resultID = request.getParameter("resultID_"+k);
             
            }
            if(request.getParameter("financial")!=null && request.getParameter("financial")!=""){
            financial = request.getParameter("financial");
          
            }
            if(request.getParameter("quarter")!=null && !"".equals(request.getParameter("quarter"))){
            quarter = request.getParameter("quarter");}
                 
                
                
       String queryUpdate = "UPDATE indicatorachieved SET District='"+updateDists+"',menAchieved='"+achievedm+"',womenAchieved='"+achievedw+"',timestamp='"+formattedDate+"' where resultID='"+resultID+"'";

            System.out.println(queryUpdate);
                try {
                                               conn.state.executeUpdate(queryUpdate);
                                             
                                           } catch (SQLException ex) {
                                               Logger.getLogger(IndicatorActivitiesServlet.class.getName()).log(Level.SEVERE, null, ex);
                                                out.println("ERROR"+ex.toString());
                                           }
          
}
     
           
           
           
           
           
           
           
           // insert into indicator achievedcombined 
           
           
           int newresultrows=0;
String new_achievedm="";
String new_achievedw="";
String new_dists="";
String new_countys="";

    if(request.getParameter("newresultrows")!=null && !"".equals(request.getParameter("newresultrows"))){
            //count = request.getParameter("count");
            newresultrows = Integer.parseInt(request.getParameter("newresultrows"));
           
                 }
           for(int k=1 ;k<=newresultrows;k++)
{  
        if(request.getParameter("new_achievedm_"+k)!=null && !"".equals(request.getParameter("new_achievedm_"+k))){
            new_achievedm = request.getParameter("new_achievedm_"+k);
        
        }
       
            if(request.getParameter("new_achievedw_"+k)!=null && request.getParameter("new_achievedw_"+k)!=""){
            new_achievedw = request.getParameter("new_achievedw_"+k);
             
            }
           
            if(request.getParameter("new_districts_"+k)!=null && request.getParameter("new_districts_"+k)!=""){
            new_dists = request.getParameter("new_districts_"+k);
           
            }
            if(request.getParameter("new_countys_"+k)!=null && request.getParameter("new_countys_"+k)!=""){
            new_countys = request.getParameter("new_countys_"+k);
           
            }
            
                
                
       String querys = "INSERT INTO indicatorachieved SET resultID='"+  uniqueid().trim() +"', District='"+new_dists+"',County='"+new_countys+"',menAchieved='"+new_achievedm+"',womenAchieved='"+new_achievedw+"',reportingPeriod='"+quarter+"',financialYear='"+financial+"',titleID='"+titleUpdate+"' ";

            System.out.println(querys);
                try {
//                                               conn.state.executeUpdate(querys);
                    if(!new_achievedw.equals("")&&!new_achievedm.equals("")){
    
    
    conn.rs6=conn.state6.executeQuery("select * from indicatorachieved where reportingPeriod='"+quarter+"' and financialYear='"+financial+"'and District='"+new_dists+"' and titleID='"+titleUpdate+"'");
    
    if(!conn.rs6.next()){
     conn.state.executeUpdate(querys); 
    
        if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted results data for titleID is "+titleUpdate+" and Quarter is "+quarter+" and Financial Year is "+financial+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);} 
    }
    else{
    Replytext+=" Data for indicator "+titleUpdate+" District "+new_dists +" For Quarter "+quarter+" "+financial+" is already added";
    }  
                    }        } catch (SQLException ex) {
                                               Logger.getLogger(IndicatorActivitiesServlet.class.getName()).log(Level.SEVERE, null, ex);
                                                out.println("ERROR"+ex.toString());
                                           }
          
}
     
           if(conn.rs!=null){conn.rs.close();}
              if(conn.rs1!=null){conn.rs1.close();}
              if(conn.rs2!=null){conn.rs2.close();}
              if(conn.rs3!=null){conn.rs3.close();}
              if(conn.rs4!=null){conn.rs4.close();}
              
         if(conn.state!=null){conn.state.close();}
         if(conn.state1!=null){conn.state1.close();}
         if(conn.state2!=null){conn.state2.close();}
         if(conn.state3!=null){conn.state3.close();}
         if(conn.state4!=null){conn.state4.close();}
         if(conn.state5!=null){conn.state5.close();}
         if(conn.state6!=null){conn.state6.close();}
         
         if(conn.pst!=null){conn.pst.close();}
         if(conn.connect!=null){conn.connect.close();}
//         if(conn.state7!=null){ conn.state7.close();} 
            if(Replytext.equals("")){
               Replytext="Data saved successfully";
               }                           

      session.setAttribute("replytext", Replytext);
   
           
   
             } finally { 
              response.sendRedirect("ResultsMain2.jsp");
           
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IndicatorUpdater.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndicatorUpdater.class.getName()).log(Level.SEVERE, null, ex);
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
