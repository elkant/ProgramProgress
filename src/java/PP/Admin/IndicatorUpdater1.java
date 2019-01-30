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
public class IndicatorUpdater1 extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String county = "";
           String[] district;
           String activity = "";
           String startdate = "";
           String enddate = "";
          
           String total = "";
           String[] new_district;
           String new_activity = "";
           String new_startdate = "";
           String new_enddate = "";
          String new_unit="";
          String new_county="";
           String new_total = "";
            String dist="";
            String new_dist="";
            String achievedtotal="";
            String totalID="";
            
            String activitys="";
             String financial= "";
                String quarter= "";
                 int count=0;
               
          
           HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        
         dbConnect conn = new dbConnect();
        
         county = "";
          
            activity = "";
            startdate = "";
            enddate = "";
          
            total = "";
        
            new_activity = "";
            new_startdate = "";
            new_enddate = "";
           new_unit="";
           new_county="";
            new_total = "";
             dist="";
             new_dist="";
             achievedtotal="";
             totalID="";
            
             activitys="";
              financial= "";
                 quarter= "";
                  count=0;
        
        
          Date date= new Date();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(date);
        
          String Replymsg="";
         String computername = InetAddress.getLocalHost().getHostName();
        
        PrintWriter out = response.getWriter();
        try {
            String titles="";
             
              session = request.getSession(true);
//        session.getAttribute("title");
        String title = (String)session.getAttribute("titles");
        //String title = request.getParameter("title");
         if(request.getParameter("titles1")!=null && request.getParameter("titles1")!=""){
             titles= request.getParameter("titles1");
         }
        
            
            String num1="";
            int num=0;
             if(request.getParameter("count")!=null && !request.getParameter("count").equals("")){
           num1 =  request.getParameter("oldrows1");
          // num1 =  request.getParameter("count");
              num = Integer.parseInt(num1);}
         
          String counts="";
          int counters=0;
           
           int number = num;
          
           String id="";
           String unit="";
             String activityOthers="";
for(int i=0 ;i<=number;i++)
{  dist="";
   
          if(request.getParameter("county_"+i)!= null && !"".equals(request.getParameter("county_"+i)))
          {
           county = request.getParameter("county_"+i);
                    }
         if(request.getParameterValues("district_"+i)!= null && !request.getParameterValues("district_"+i).equals("")){
           district = request.getParameterValues("district_"+i);
           for(int y=0;y <district.length;y++){
           dist+=district[y]+"_";
            
           }
         }
          if(request.getParameter("activity_"+i)!=null && !"".equals(request.getParameter("activity_"+i))){
           activity = request.getParameter("activity_"+i);}
            if(request.getParameter("activityOthers_"+i)!=null && request.getParameter("activityOthers_"+i)!=""){
            activityOthers = request.getParameter("activityOthers_"+i);}
        
          if(request.getParameter("startdate_"+i)!=null && !"".equals(request.getParameter("startdate_"+i))){
            startdate = request.getParameter("startdate_"+i);}
          if(request.getParameter("enddate_"+i)!=null && !"".equals(request.getParameter("enddate_"+i)))
          {
           enddate = request.getParameter("enddate_"+i);}
           if(request.getParameter("total_"+i)!=null && !"".equals(request.getParameter("total_"+i))){
            total  = request.getParameter("total_"+i);}
           if(request.getParameter("unit_"+i)!=null && !"".equals(request.getParameter("unit_"+i))){
            unit  = request.getParameter("unit_"+i);}
             if(request.getParameter("financial")!=null && !"".equals(request.getParameter("financial"))){
            financial = request.getParameter("financial");}
            if(request.getParameter("quarter")!=null && !"".equals(request.getParameter("quarter"))){
            quarter = request.getParameter("quarter");} 
                        if(request.getParameter("id"+i)!=null && !"".equals(request.getParameter("id"+i))){
            id = request.getParameter("id"+i);}
           
          
          
          
       String query = "UPDATE indicatoractivities1 SET unit='"+unit+"',countyID='"+county+"',districtID='"+dist+"',activityTitle='"+activity+"',activityOthers='"+activityOthers+"',startDate='"+startdate+"',endDate='"+enddate+"',total='"+total+"',timestamp='"+formattedDate+"' WHERE activityID='"+id+"'";

            System.out.println(query);
                try {
                                               conn.state.executeUpdate(query);
                                              
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
                }
String numbers="";
int numbers1=0;

     if(request.getParameter("newrows1")!=null && !"".equals(request.getParameter("newrows1"))){
           numbers =  request.getParameter("newrows1");
              numbers1 = Integer.parseInt(numbers);}
for(int i=0 ;i<=numbers1;i++)
{String new_activityOthers="";
    new_dist="";
    dist="";
          System.out.println(i);
          if(request.getParameter("new_county_"+i)!= null && !"".equals(request.getParameter("new_county_"+i)))
          {
           new_county = request.getParameter("new_county_"+i);
                    }
         if(request.getParameterValues("new_district1_"+i)!= null && !request.getParameterValues("new_district1_"+i).equals("")){
           new_district = request.getParameterValues("new_district1_"+i);
           for(int y=0;y <new_district.length;y++){
           new_dist+=new_district[y]+"_";
            
           }
         }
          if(request.getParameter("new_activity_"+i)!=null && !"".equals(request.getParameter("new_activity_"+i))){
           new_activity = request.getParameter("new_activity_"+i);}
          if(request.getParameter("new_startdate_"+i)!=null && !"".equals(request.getParameter("new_startdate_"+i))){
            new_startdate = request.getParameter("new_startdate_"+i);}
          if(request.getParameter("new_enddate_"+i)!=null && !"".equals(request.getParameter("new_enddate_"+i)))
          {
           new_enddate = request.getParameter("new_enddate_"+i);}
           if(request.getParameter("new_total_"+i)!=null && !"".equals(request.getParameter("new_total_"+i))){
            new_total  = request.getParameter("new_total_"+i);}
           if(request.getParameter("new_unit_"+i)!=null && !"".equals(request.getParameter("new_unit_"+i))){
            new_unit  = request.getParameter("new_unit_"+i);}
               if(request.getParameter("new_activityOthers_"+i)!=null && !"".equals(request.getParameter("new_activityOthers_"+i))){
            new_activityOthers = request.getParameter("new_activityOthers_"+i);}

if(new_county!=null && !new_county.equals("") && new_district!=null && !new_dist.equals("") && new_startdate!= null &&!new_startdate.equals("") && new_enddate!=null && !new_enddate.equals("")){
   String querys = "INSERT INTO indicatoractivities1 SET activityID='"+ uniqueid().trim()+"',unit='"+new_unit+"',titleID='"+titles+"',countyID='"+new_county+"',districtID='"+new_dist+"',activityTitle='"+new_activity+"',activityOthers='"+new_activityOthers+"',startDate='"+new_startdate+"',endDate='"+new_enddate+"',total='"+new_total+"',FinancialYear='"+financial+"',Quarter='"+quarter+"'";

            System.out.println("INSERT ________"+querys);
                try {
                                              // conn.state.executeUpdate(querys);
                    
                    
                    
                       if(!new_county.equals("")&&!new_dist.equals("")&&!new_activity.equals("")&&!new_startdate.equals("")&&!new_total.equals("")&&!quarter.equals("") && financial!=null && !financial.equals("")){
                    
                        
                      //  conn.rs6=conn.state6.executeQuery("select * from indicatoractivities1 where titleID='"+titles+"' and activityTitle='"+new_activity+"' and  activityTitle!='"+1031+"'and districtID='"+new_dist+"' and Quarter='"+quarter+"' and FinancialYear='"+financial+"'");
                        //if(!conn.rs6.next()){
                        conn.state.executeUpdate(querys);
                        
        if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted activities data for titleID is "+titles+" and Quarter is "+quarter+" and Financial Year is "+financial+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);
        } 
                       // }
//                        else {
//                       Replymsg+="Data already added for district"+new_dist+"  for Quarter "+quarter+" "+financial; 
//                        
//                       System.out.println(Replymsg);
//                       
//                        }      
                       }                 
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
}
}//end of for loop
   
          
           if(request.getParameter("test_count")!=null && !"".equals(request.getParameter("test_count"))){
            counts = request.getParameter("test_count");
            counters =Integer.parseInt(counts);
            }
           String resultID="";    
          String dists="";  
          String baselineid="";
           for(int a=1 ;a<=counters;a++)
          { 
            if(request.getParameter("resultID_"+a)!=null && !"".equals(request.getParameter("resultID_"+a))){
            resultID = request.getParameter("resultID_"+a);}
            if(request.getParameter("achievedTotal"+a)!=null && !"".equals(request.getParameter("achievedTotal"+a))){
            achievedtotal = request.getParameter("achievedTotal"+a);}
            if(request.getParameter("districts_"+a)!=null && !"".equals(request.getParameter("districts_"+a))){
            dists = request.getParameter("districts_"+a);}
           
           
              
                
                
        String query2 = "UPDATE indicatorachievedcombined SET district='"+dists+"',totalAchieved='"+achievedtotal+"',timestamp='"+formattedDate+"' WHERE resultID='"+resultID+"'";
   
       
       
       
             System.out.println(query2);
                try {
                                               conn.state.executeUpdate(query2);
                                             
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
          
}//end of update for 
           
           // for saving the new rows 
           
             int resultscounts=0;
             String new_county="";
             String new_achieved ="";
             String new_dists="";
           if(request.getParameter("newresultrows1")!=null && !"".equals(request.getParameter("newresultrows1"))){
            resultscounts = Integer.parseInt(request.getParameter("newresultrows1"));
          
            }
          
           for(int a=1 ;a<=resultscounts;a++)
          { 
         
            if(request.getParameter("new_achievedtotal_"+a)!=null && !"".equals(request.getParameter("new_achievedtotal_"+a))){
            new_achieved = request.getParameter("new_achievedtotal_"+a);}
            if(request.getParameter("new_district_"+a)!=null && !"".equals(request.getParameter("new_district_"+a))){
            new_dists = request.getParameter("new_district_"+a);}
            if(request.getParameter("new_county_"+a)!=null && !"".equals(request.getParameter("new_county_"+a))){
            new_county = request.getParameter("new_county_"+a);
            }
           
              
                
                
       String query2 = "INSERT INTO indicatorachievedcombined SET resultID='"+  uniqueid().trim() +"',county='"+new_county+"',district='"+new_dists+"',totalAchieved='"+new_achieved+"',reportingPeriod='"+quarter+"',financialYear='"+financial+"',titleID='"+titles+"'";
   
       
       
       
             System.out.println(query2);
                try {
                                              // conn.state.executeUpdate(query2);
                                      
 conn.rs6=conn.state6.executeQuery("select * from indicatorachievedcombined where reportingPeriod='"+quarter+"' and financialYear='"+financial+"'and district='"+new_dists+"' and titleID='"+titles+"'");
    
    if(!conn.rs6.next()){
  
                                              
                                               conn.state.executeUpdate(query2);
                                               
        if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted results data for titleID is "+titles+" and Quarter is "+quarter+" and Financial Year is "+financial+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);} 
                                        
    }
    else{
    
   Replymsg+=" Data for indicator "+titles+" District "+new_dists +" For Quarter "+quarter+" "+financial+" is already added";
    
    
    }
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
                    
                    
                                             
                                        
          
}//end of update for 
           
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
        } finally { 
              if(Replymsg.equals("")){
               Replymsg="Data saved successfully";
               }                           

      session.setAttribute("replytext", Replymsg);
              response.sendRedirect("ResultsMain2.jsp");
           
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
            Logger.getLogger(IndicatorUpdater1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndicatorUpdater1.class.getName()).log(Level.SEVERE, null, ex);
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
