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
public class DeleteResult2 extends HttpServlet {

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
         String id = request.getParameter("id");    
         String indicator = request.getParameter("titleID");    
         String district = request.getParameter("district");    
 
System.out.println("id   "+id);
System.out.println("indicator   "+indicator);
System.out.println("district   "+district);
    
        session = request.getSession();
       
        
                     dbConnect conn = new dbConnect();
                 
				String query = "delete from indicatorachievedcombined where resultID='"+id+"'";
			
                               
                    try {       
                        conn.state.executeUpdate(query);
                        
                         String computername = InetAddress.getLocalHost().getHostName();
         if (session.getAttribute("Username") != null) {
         Date dat= new Date();
         
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Deleted from db from system data for resultID ="+id+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);
                  System.out.println(inserter); 
         } 
                        
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(DeleteIndicator.class.getName()).log(Level.SEVERE, null, ex);
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
