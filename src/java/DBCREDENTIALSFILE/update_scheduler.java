/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
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
 * @author SIXTYFOURBIT
 */
public class update_scheduler extends HttpServlet {

    
    String allrows, tableid,hour,minute,second,am_pm;
   
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        session=request.getSession();
        
        dbConnect conn= new dbConnect();
        
        allrows=request.getParameter("allrows");
        
       
         Date dat= new Date();
        
       
        for(int a=1;a<=Integer.parseInt(allrows);a++ ){
            try {
                tableid=request.getParameter("tableid"+a);  
                hour=request.getParameter("hour"+a);  
                minute=request.getParameter("minute"+a);  
                second=request.getParameter("second"+a);  
                am_pm=request.getParameter("am_pm"+a);  
                  
                  String updateall="update scheduler_settings set hour='"+hour+"', minute='"+minute+"' , am_pm='"+am_pm+"' , second='"+second+"' where schedule_id='"+tableid+"'";
                
                conn.state.executeUpdate(updateall);
              
                
                
                //save content into audit log
                String computername=InetAddress.getLocalHost().getHostName();
              
               
                
//              if (session.getAttribute("userid")!=null) {
//       
//              String inserter="insert into audit set host_comp='"+computername+"' , action='Updated scheduler settings for scheduler id "+tableid+" to hour="+hour+", minute="+minute+" , am_pm="+am_pm+" , second="+second+" ',time='"+dat+"',actor_id='"+session.getAttribute("userid")+"'";                     
//              
//              conn.state2.executeUpdate(inserter);
//              
//              }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(update_scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
      
 
        
        }//end of for loop
     
         session.setAttribute("schedule_updated", "schedule updated succesfully. Press \'Restart\' to make the updates effective");
           
             response.sendRedirect("scheduler_settings.jsp");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
