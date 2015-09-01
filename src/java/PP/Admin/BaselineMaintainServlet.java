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
public class BaselineMaintainServlet extends HttpServlet {

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
  
          HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
           
              dbConnect conn = new dbConnect();
session = request.getSession(true);
                                
                                
                                        
                                        
                                        ArrayList baseline = new ArrayList();
                                        
                                        String query = "select * from baseline";
                                        System.out.println("query " + query);
                try {
                    conn.state = conn.connect.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(IndicatorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                                        conn.rs = conn.state.executeQuery(query);
        if(baseline!=null && baseline.size()>0 ){baseline.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                                         
                       BaselineBean DB= new BaselineBean();
                       DB.setBASELINEID(conn.rs.getInt("baselineID"));
                       DB.setQTARGETMEN(conn.rs.getString("QtargetMen"));
                       DB.setQTARGETWOMEN(conn.rs.getString("QtargetWomen"));
                       DB.setMENBASELINE(conn.rs.getString("menBaseline"));
                       DB.setWOMENBASELINE(conn.rs.getString("womenBaseline"));
                       DB.setENDTARGETMEN(conn.rs.getString("endTargetMen"));
                       DB.setENDTARGETWOMEN(conn.rs.getString("endTargetWomen"));
                       DB.setFINANCIALYEAR(conn.rs.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs.getString("Quarter"));
                       DB.setDISTRICT(conn.rs.getString("District"));
                       DB.setTITLEID(conn.rs.getString("titleID"));
                    
                      
          
                       baseline.add(DB);
                       
//                       System.out.println(DB.getTITLE());
//                       System.out.println(DB.getTITLENO());
//                       System.out.println(DB.getTITLEID());
//                       
                      
        }
       session.setAttribute("baseline", baseline);
                                 String nextJSP = "/BaselineMaintain.jsp";
                                        
                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                                        dispatcher.forward(request,response);
                                        conn.connect.close();
                                        System.out.println("Disconnected from database");
        } catch (SQLException ex) {
            Logger.getLogger(IndicatorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
