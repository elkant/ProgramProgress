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
public class EditActivityServlet extends HttpServlet {

    HttpSession session;
    String others="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
              dbConnect conn = new dbConnect();
session = request.getSession(true);
                                
                     String financialyear="";
                     String quarter="";
                     financialyear = request.getParameter("FinancialYear");
                     quarter=request.getParameter("Quarter");
                                        
                                        
                                        ArrayList activity = new ArrayList();
                                        
                                        String query = "select * from indicatoractivities where FinancialYear='"+financialyear+"' and Quarter='"+quarter+"'order by activityID";
				
				System.out.println("query " + query);
				conn.state = conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(query);
				


				
              
        if(activity!=null && activity.size()>0 ){activity.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                                   if (conn.rs.getString(6).equals("1031")) {
                                         others=conn.rs.getString(7);
                                         } 
                                         else{others="";}       
                       ActivityBean DB= new ActivityBean();
                       DB.setACTIVITYID(conn.rs.getString(1));
                       DB.setUNIT(conn.rs.getString(2));
                       DB.setINDICATOR(conn.rs.getString(3));
                       
                       DB.setDISTRICT(conn.rs.getString(5));
                       DB.setACTIVITY(conn.rs.getString(6));
                       DB.setOTHERS(others);
                       DB.setSTARTDATE(conn.rs.getString(8));
                       DB.setENDDATE(conn.rs.getString(9));
                      
                       DB.setSUBTOTAL(conn.rs.getString(12));
                      if(conn.rs.getString(4).contains("_"))
                      {
                 String county_selector="select * from county where countyID='"+conn.rs.getString(4)+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs.getString(4));
                  DB.setCOUNTY(conn.rs2.getString("countyName"));        
                      }
                      else{
                       DB.setCOUNTY(conn.rs.getString(4)); }
                      
          
                       activity.add(DB);
                       
                      
        }
       session.setAttribute("activity", activity);
                                 String nextJSP = "editActivity.jsp";
                                        
//                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//                                        dispatcher.forward(request,response);
//                                        conn.connect.close();
                                 response.sendRedirect(nextJSP);
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
            Logger.getLogger(EditActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditActivityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
