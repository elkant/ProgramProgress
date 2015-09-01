/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
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
public class indicatoractivities1 extends HttpServlet {

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
            String dist="";
            String achievedtotal="";
            String totalID="";
            String activityOthers="";
            int activitys=0;
             String financial= "";
                String quarter= "";
                 int count=0;
                 String title="";
           dbConnect conn = new dbConnect();
           HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String Replymsg="";
         String computername = InetAddress.getLocalHost().getHostName();
        try {
            
              String unit = request.getParameter("unit");
              session = request.getSession(true);
//        session.getAttribute("title");
         title = (String)session.getAttribute("titles");
        //String title = request.getParameter("title");
        
        
            System.out.println("title"+title);
            String num1="";
            int num=0;
            if(request.getParameter("h1")!=null && !"".equals(request.getParameter("h1"))){
           num1 =  request.getParameter("h1");
              num = Integer.parseInt(num1);}
           System.out.println("bashxdvsa"+num1);
          
           System.out.println("num1"+num);
           int number = num;
           System.out.println("number"+number);
for(int i=0 ;i<number;i++)
{
    dist="";
          System.out.println(i);
          if(request.getParameter("county_"+i)!= null && request.getParameter("county_"+i)!= "")
          {
           county = request.getParameter("county_"+i);
                    }
         if(request.getParameterValues("district1_"+i)!= null && !request.getParameterValues("district1_"+i).equals("")){
           district = request.getParameterValues("district1_"+i);
           for(int y=0;y <district.length;y++){
           dist+=district[y]+"_";
            System.out.println("district dddd"+district[y]);
            System.out.println("dist dddd"+dist);
           }
         }
          if(request.getParameter("activity_"+i)!=null && request.getParameter("activity_"+i)!=""){
           activity = request.getParameter("activity_"+i);}
          if(request.getParameter("activityOthers_"+i)!=null && request.getParameter("activityOthers_"+i)!=""){
           activityOthers = request.getParameter("activityOthers_"+i);}
          if(request.getParameter("startdate_"+i)!=null && request.getParameter("startdate_"+i)!=""){
            startdate = request.getParameter("startdate_"+i);}
          if(request.getParameter("enddate_"+i)!=null && request.getParameter("enddate_"+i)!="")
          {
           enddate = request.getParameter("enddate_"+i);}
           if(request.getParameter("total_"+i)!=null && request.getParameter("total_"+i)!=""){
            total  = request.getParameter("total_"+i);}
             if(request.getParameter("financial")!=null && request.getParameter("financial")!=""){
            financial = request.getParameter("financial");}
            if(request.getParameter("quarter")!=null && request.getParameter("quarter")!=""){
            quarter = request.getParameter("quarter");}  
           System.out.println(unit);
           System.out.println("county"+county);
           System.out.println("district"+dist);
           System.out.println("activity"+activity);
           System.out.println("startdate"+startdate);
           System.out.println("endate"+enddate);
          
           System.out.println("total"+total);
          
          
       String query = "INSERT INTO indicatoractivities1(unit,titleID,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter)"
+ " VALUES ('"+unit+"','"+title+"','"+county+"','"+dist+"','"+activity+"','"+activityOthers+"','"+startdate+"','"+enddate+"','"+total+"','"+financial+"','"+quarter+"')";    
            System.out.println(query);
                try {
                    
                    if(!county.equals("")&&!dist.equals("")&&!activity.equals("")&&!startdate.equals("")&&!total.equals("")&&!quarter.equals("")){
                    
                        
                        conn.rs6=conn.state6.executeQuery("select * from indicatoractivities1 where titleID='"+title+"' and activityTitle='"+activity+"' and  activityTitle!='"+1031+"'and districtID='"+dist+"' and Quarter='"+quarter+"' and FinancialYear='"+financial+"'");
                        if(!conn.rs6.next()){
                        conn.state.executeUpdate(query);
                        
        if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted activities data for titleID is "+title+" and Quarter is "+quarter+" and Financial Year is "+financial+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);} 
                        }
                        else {
                       Replymsg+="Data already added for district"+dist+"  for Quarter "+quarter+" "+financial; 
                        
                       System.out.println(Replymsg);
                       
                        }                      
                                              
                    }
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
                }
                  
String baselinetotal="",targettotal="",endTargetTotal="";


     
          
               // count=(Integer)session.getAttribute("test_counter");
                
                int resultcounts1=0;
                int newresultrows1=0;
                        
                         if(request.getParameter("resultcounts1")!=null && !"".equals(request.getParameter("resultcounts1"))){
            resultcounts1 = Integer.parseInt(request.getParameter("resultcounts1"));}
                         
                         if(request.getParameter("newresultrows1")!=null && !"".equals(request.getParameter("newresultrows1"))){
            newresultrows1 = Integer.parseInt(request.getParameter("newresultrows1"));}
              String dists=""; 
              String lowercounter="";
              System.out.println("resultcounts1 "+resultcounts1);
              
              
              
           for(int a=1 ;a<=newresultrows1;a++)
{ 
    
    String resultid="";
    
    
        if(request.getParameter("newachievedtotal_"+a)!=null && !"".equals(request.getParameter("newachievedtotal_"+a))){
            achievedtotal = request.getParameter("newachievedtotal_"+a);}
           
            if(request.getParameter("totalID_"+a)!=null && !"".equals(request.getParameter("totalID_"+a))){
            totalID = request.getParameter("totalID_"+a);}
            if(request.getParameter("newdistrict__"+a)!=null && !"".equals(request.getParameter("newdistrict__"+a))){
            dists = request.getParameter("newdistrict__"+a);}
               System.out.println("LOWER DISTRICT   "+dists);
            
            if(request.getParameter("newcounty__"+a)!=null && !"".equals(request.getParameter("newcounty__"+a))){
            lowercounter = request.getParameter("newcounty__"+a);}
           System.out.println("LOWER COUNTY   "+lowercounter);
            
            if( request.getParameter("baselinetotal_"+a)!= null){       
 baselinetotal = request.getParameter("baselinetotal_"+a);
System.out.println(baselinetotal);}

if( request.getParameter("endTargetTotal_"+a)!= null){
endTargetTotal = request.getParameter("endTargetTotal_"+a);
System.out.println("endTargetTotal"+endTargetTotal);}

if(request.getParameter("targettotal_"+a)!= null){
 targettotal = request.getParameter("targettotal_"+a);
System.out.println(targettotal);}
String query1="";
 String distr="";
  String query="select * from districts where DistrictName='"+dists+"'";
                  conn.rs5 = conn.state5.executeQuery(query);
//                  System.out.println()
                  while(conn.rs5.next()){
                distr= conn.rs5.getString("districtID");
                  }
if( request.getParameter("targettotal_"+a)!= null && !request.getParameter("endTargetTotal_"+a).equals("") && request.getParameter("endTargetTotal_"+a)!= null){
                  
    
                         query1= "INSERT INTO baselinetotal(QtargetTotal,baselineTotal,"
                                + "endtargetTotal,financialYear,quarter,districtID,titleID)"
                                + "VALUES('"+targettotal+"','"+baselinetotal+"','"+endTargetTotal+"',"
                                + "'"+financial+"','"+quarter+"','"+distr+"','"+title+"')";
                          //conn.state.executeUpdate(query1);
            
}
            
           String query2=""; 
      
             // query2 = "INSERT INTO indicatorachievedcombined SET totalAchieved='"+achievedtotal+"', reportingPeriod='"+quarter+"',financialYear='"+financial+"',titleID='"+title+"',district='"+dists+"' and County='"+lowercounter+"'";
              
           
           
           query2 = "INSERT INTO indicatorachievedcombined (totalAchieved,reportingPeriod,financialYear,titleID,district,county) Values('"+achievedtotal+"','"+quarter+"','"+financial+"','"+title+"','"+dists+"','"+lowercounter+"')";
        
            System.out.println(query2);
                try {
                    
 conn.rs6=conn.state6.executeQuery("select * from indicatorachievedcombined where reportingPeriod='"+quarter+"' and financialYear='"+financial+"'and district='"+dists+"' and titleID='"+title+"'");
    
    if(!conn.rs6.next()){
  
                                              
                                               conn.state.executeUpdate(query2);
                                               
        if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted results data for titleID is "+title+" and Quarter is "+quarter+" and Financial Year is "+financial+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);} 
                                        
    }
    else{
    
   Replymsg+=" Data for indicator "+title+" District "+dists +" For Quarter "+quarter+" "+financial+" is already added";
    
    
    }
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
          
}
        } finally {
            
                                           
               if(Replymsg.equals("")){
               Replymsg="Data saved succefully";
               }                           

      session.setAttribute("replytext", Replymsg);
              response.sendRedirect("ResultsMain.jsp");
           
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
            Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(indicatoractivities1.class.getName()).log(Level.SEVERE, null, ex);
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
