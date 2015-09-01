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
public class Indicators extends HttpServlet {

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
           dbConnect conn = new dbConnect();
           HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
     
              String unit = request.getParameter("unit");
        String title = request.getParameter("title");
        
//        session = request.getSession(true);
//        session.setAttribute("title", title);
//            System.out.println("title for session"+title);
            String num1="";
            int num=0;
            if(request.getParameter("h")!=null && request.getParameter("h")!=""){
           num1 =  request.getParameter("h");
              num = Integer.parseInt(num1);}
           System.out.println("bashxdvsa"+num1);
          
           System.out.println("num1"+num);
           int number = num;
           System.out.println("number"+number);
for(int i=0 ;i<=number;i++)
{ 
          System.out.println(i);
          if(request.getParameter("county_"+i)!= null && request.getParameter("county_"+i)!= "")
          {
           county = request.getParameter("county_"+i);
}
         if(request.getParameterValues("district_"+i).length != 0){
           district = request.getParameterValues("district_"+i);
           System.out.println("cfgdrtsfcvbcnvbc"+district.length);
           for(int y=0;y < district.length;y++){
           dist += district[y];
           }          if(request.getParameter("activity_"+i)!=null && request.getParameter("activity_"+i)!=""){
           activity = request.getParameter("activity_"+i);}
          if(request.getParameter("startdate_"+i)!=null && request.getParameter("startdate_"+i)!=""){
            startdate = request.getParameter("startdate_"+i);}
          if(request.getParameter("enddate_"+i)!=null && request.getParameter("enddate_"+i)!="")
          {
           enddate = request.getParameter("enddate_"+i);}
           if(request.getParameter("women_"+i)!=null && request.getParameter("women_"+i)!=""){
            women  = request.getParameter("women_"+i);}
           if(request.getParameter("men_"+i)!=null && request.getParameter("men_"+i)!=""){
            men = request.getParameter("men_"+i);}
            if(request.getParameter("subtotal_"+i)!=null && request.getParameter("subtotal_"+i)!=""){
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
           
       String query = "INSERT INTO indicatoractivities(unit,IndicatorID,countyID,DistrictID,activityTitle,startDate,endDate,men,women,subtotals)"
+ " VALUES ('"+unit+"','"+title+"','"+county+"','"+dist+"','"+activity+"','"+startdate+"','"+enddate+"','"+women+"','"+men+"','"+subtotal+"')";    
            
                try {
                                               conn.state.executeUpdate(query);
                                               response.sendRedirect("ResultsMain.jsp");
                                           } catch (SQLException ex) {
                                               Logger.getLogger(indicatoractivities.class.getName()).log(Level.SEVERE, null, ex);
                                               System.out.println(ex.toString());
                                           }
          
         }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
