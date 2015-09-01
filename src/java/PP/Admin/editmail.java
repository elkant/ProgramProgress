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
public class editmail extends HttpServlet {

   String mail="";
    
    HttpSession session;
    
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    session=request.getSession();
    PrintWriter out = response.getWriter();
    
    
    if(request.getParameter("mandemail")!=null){
        
    mail=request.getParameter("mandemail");
    
    }
    dbConnect conn=new dbConnect();
    
    
    if(conn.state.executeUpdate("update mail set email='"+mail+"' where mailid='1'")==1){
    
        
       
    session.setAttribute("mailchanged", "<font color=\"green\">email changed successfully to  </font><font color=\"orange\">"+mail+"</font>");     
    }
    else{
    
         session.setAttribute("mailchanged", "<font color=\"red\">email NOT changed. Try again</font>"); 
        
    }
   
    response.sendRedirect("mandemail.jsp");
    
    
    try {
       
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(editmail.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
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
