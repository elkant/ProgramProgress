/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class EditResultServlet2 extends HttpServlet {

   HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
              dbConnect conn = new dbConnect();
session = request.getSession(true);
                                
                                
                                        
                                        
                                        ArrayList result2 = new ArrayList();
                                        String query="select * from indicatorachievedcombined";
//                                        String query = "select indicatorresults1.resultID,indicatorresults1.totalAchieved,baselinetotal.QtargetTotal,"
//                                                + "baselinetotal.baselineTotal,baselinetotal.endTargetTotal,baselinetotal.financialYear,baselinetotal.quarter,"
//                                                + "baselinetotal.districtID,baselinetotal.titleID from indicatorresults1 LEFT JOIN baselinetotal ON indicatorresults1.baselineID=baselinetotal.totalID ";
//				
				System.out.println("query " + query);
				conn.state = conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(query);
				


				
              
        if(result2!=null && result2.size()>0 ){result2.clear();}

                                        while(conn.rs.next())
                                        {
                      
                                         
                       Result2Bean DB= new Result2Bean();
                       DB.setRESULTID(conn.rs.getString("resultID"));
                       DB.setTOTALACHIEVED(conn.rs.getString("totalAchieved"));
                       DB.setFINANCIAL(conn.rs.getString("financialYear"));
                       DB.setQUARTER(conn.rs.getString("reportingPeriod"));
                       DB.setTITLEID(conn.rs.getString("titleID"));
                       DB.setDISTRICT(conn.rs.getString("district"));
                       DB.setCOUNTY(conn.rs.getString("county"));
                  
          
                       result2.add(DB);
                       
                      
        }
       session.setAttribute("result2", result2);
                                 String nextJSP = "/EditResult2.jsp";
                                        
                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                                        dispatcher.forward(request,response);
                                        conn.connect.close();
                                        System.out.println("Disconnected from database");
       
        
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
            Logger.getLogger(EditResultServlet2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditResultServlet2.class.getName()).log(Level.SEVERE, null, ex);
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
