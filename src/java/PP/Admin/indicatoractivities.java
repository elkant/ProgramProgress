/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

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
public class indicatoractivities extends HttpServlet {

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
           String[] district=null;
           String activity = "";
           String startdate = "";
           String enddate = "";
           String women  = "";
           String men = "";
           String subtotal = "";
            String dist="";
            String achievedw="";
            String achievedm="";
            String baselineid="";
            int activitys=0;
             String financial= "";
                String quarter= "";
                 int count=0;
           dbConnect conn = new dbConnect();
           HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
              String unit = request.getParameter("unit");
              session = request.getSession(true);
        session.getAttribute("title");
        String title = (String)session.getAttribute("title");
        //String title = request.getParameter("title");
        String activityOthers="";
        
            System.out.println("title"+title);
            String num1="";
            int num=0;
            if(request.getParameter("h")!=null && !"".equals(request.getParameter("h"))){
           num1 =  request.getParameter("h");
              num = Integer.parseInt(num1);}
           System.out.println("bashxdvsa"+num1);
          
           System.out.println("num1"+num);
           int number = num;
           System.out.println("number"+number);
for(int i=0 ;i<number;i++)
{ 
          System.out.println(i);
          if(request.getParameter("county_"+i)!= null && !"".equals(request.getParameter("county_"+i)))
          {
           county = request.getParameter("county_"+i);
}
          if(!(request.getParameterValues("district_"+i).length<0)){
           district = request.getParameterValues("district_"+i);
           for(int y=0;y <district.length;y++){
           dist+=district[y];
           }}
          if(request.getParameter("activity_"+i)!=null && !"".equals(request.getParameter("activity_"+i))){
           activity = request.getParameter("activity_"+i);}
          else if("1031".equals(activity)){
          if(request.getParameter("activityOthers_"+i)!=null && !"".equals(request.getParameter("activityOthers_"+i))){
           activityOthers = request.getParameter("activityOthers_"+i);}}
          if(request.getParameter("startdate_"+i)!=null && !"".equals(request.getParameter("startdate_"+i))){
            startdate = request.getParameter("startdate_"+i);}
          if(request.getParameter("enddate_"+i)!=null && !"".equals(request.getParameter("enddate_"+i)))
          {
           enddate = request.getParameter("enddate_"+i);}
           if(request.getParameter("women_"+i)!=null && !"".equals(request.getParameter("women_"+i))){
            women  = request.getParameter("women_"+i);}
           if(request.getParameter("men_"+i)!=null && !"".equals(request.getParameter("men_"+i))){
            men = request.getParameter("men_"+i);}
            if(request.getParameter("subtotal_"+i)!=null && !"".equals(request.getParameter("subtotal_"+i))){
            subtotal = request.getParameter("subtotal_"+i);}
           
            
               
              
           System.out.println(unit);
           System.out.println("county"+county);
           System.out.println("district"+dist);
           System.out.println("activity"+activity);
           System.out.println("startdate"+startdate);
           System.out.println("endate"+enddate);
           System.out.println("women"+women);
           System.out.println("men"+men);
           System.out.println("subtotal"+subtotal);
           System.out.println("financial _indicator activities "+financial);
           System.out.println("quarter"+quarter);
           if(county!=null && !county.equals("") && dist!=null && !dist.equals("")){
       String query = "INSERT INTO indicatoractivities(unit,IndicatorID,countyID,DistrictID,activityTitle,activityOthers,startDate,endDate,men,women,subtotals)"
+ " VALUES ('"+unit+"','"+title+"','"+county+"','"+dist+"','"+activity+"','"+activityOthers+"','"+startdate+"','"+enddate+"','"+women+"','"+men+"','"+subtotal+"')";    
            
                try {
                    
                    
                     
                       
                                               conn.state.executeUpdate(query);
                                            
                                              
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
           }
                }


      String activities="select max(activityID) from indicatoractivities";
                   System.out.println(activities);
                
                   try{
                       conn.rs=conn.state.executeQuery(activities);
                  

           
           //create an arraylist to hold the beans
                   
                     while(conn.rs.next())
                                        {
                                    activitys =  conn.rs.getInt(1); 
                                       System.out.println(activitys);
                 
                                       
                                        }  } 
                   catch(SQLException EX){
                   System.out.println("ERROR"+EX.toString());
                   }
          
                count=(Integer)session.getAttribute("test_count");
                //resultcount
           for(int i=1 ;i<=count;i++)
{ 
        if(request.getParameter("achievedm_"+i)!=null && !"".equals(request.getParameter("achievedm_"+i))){
            achievedm = request.getParameter("achievedm_"+i);}
            if(request.getParameter("achievedw_"+i)!=null && !"".equals(request.getParameter("achievedw_"+i))){
            achievedw = request.getParameter("achievedw_"+i);}
            if(request.getParameter("baselineid_"+i)!=null && !"".equals(request.getParameter("baselineid_"+i))){
            baselineid = request.getParameter("baselineid_"+i);}
            if(request.getParameter("FinancialYear")!=null && !"".equals(request.getParameter("FinancialYear"))){
            financial = request.getParameter("FinancialYear");}
            if(request.getParameter("Quarter")!=null && !"".equals(request.getParameter("Quarter"))){
            quarter = request.getParameter("Quarter");}
                 
                
                
       String query2 = "INSERT INTO indicatorresults(activityID,districtID,baselineID,menAchieved,womenAchieved,reportingPeriod,financialYear)"
+ " VALUES ('"+activitys+"','"+dist+"','"+baselineid+"','"+achievedm+"','"+achievedw+"','"+quarter+"','"+financial+"')";    
            
                try {
                                               conn.state.executeUpdate(query2);
                                             
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
          
}
        } finally { 
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
            Logger.getLogger(indicatoractivities.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(indicatoractivities.class.getName()).log(Level.SEVERE, null, ex);
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
