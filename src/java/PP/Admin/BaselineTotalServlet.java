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
public class BaselineTotalServlet extends HttpServlet {

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
  
        String totalbaseline="";
   
    String totalEndTarget="";
 
    String FinancialYear ="";
    String reportingPeriod="";
    String district ="";
    String totalTarget="";
   HttpSession session;
    String titleID="";
    dbConnect conn = new dbConnect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
if(request.getParameter("totalbaseline")!="" && request.getParameter("totalbaseline")!= null){       
 totalbaseline = request.getParameter("totalbaseline");
System.out.println(totalbaseline);}

if(request.getParameter("totalendTarget")!="" && request.getParameter("totalendTarget")!= null){
totalEndTarget = request.getParameter("totalendTarget");
System.out.println("totalEndTarget"+totalEndTarget);}

if(request.getParameter("FinancialYear")!="" && request.getParameter("FinancialYear")!= null){
 FinancialYear = request.getParameter("FinancialYear");
System.out.println(FinancialYear);}
if(request.getParameter("reportingPeriod")!="" && request.getParameter("reportingPeriod")!= null){
 reportingPeriod = request.getParameter("reportingPeriod");
 System.out.println(reportingPeriod);
}
if(request.getParameter("district")!="" && request.getParameter("district")!= null){
 district = request.getParameter("district");
System.out.println(district);}
if(request.getParameter("totalTarget")!="" && request.getParameter("totalTarget")!= null){
 totalTarget = request.getParameter("totalTarget");
System.out.println(totalTarget);}
session = request.getSession();
if(session.getAttribute("titleID")!="" && session.getAttribute("titleID")!= null){
 titleID = session.getAttribute("titleID").toString();}
System.out.println(titleID);
 

                    try {
    
                        conn.state.executeUpdate("INSERT INTO baselinetotal(QtargetTotal,baselineTotal,"
                                + "endtargetTotal,financialYear,quarter,districtID,titleID)"
                                + "VALUES('"+totalTarget+"','"+totalbaseline+"','"+totalEndTarget+"',"
                                + "'"+FinancialYear+"','"+reportingPeriod+"','"+district+"','"+titleID+"')");
                        out.println("Saved Successfully");
//                          response.sendRedirect("SetTargets.jsp");
                        
                                           }
catch (SQLException ex) {
                                               Logger.getLogger(BaselineServlet.class.getName()).log(Level.SEVERE, null, ex);
                                              System.out.println(ex.toString()) ;
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
