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
public class updatehelp extends HttpServlet {

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
   
  String id,helptext;
  HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            session=request.getSession();
            
            //____________________COMPUTER NAME____________________________________
 String computername=InetAddress.getLocalHost().getHostName();
            
    response.setContentType("text/html;charset=UTF-8");
    
    
    id=request.getParameter("id");
    helptext=request.getParameter("update");
    helptext=helptext.replace("'"," ");
    
    dbConnect conn= new dbConnect();
    
    String qr="update help set help_text='"+helptext+"' where help_id='"+id+"'";
    
    conn.state.executeUpdate(qr);
    
   
    
     Date dat= new Date();

//String inserter="insert into audit set host_comp='"+computername+"' , action='<b>Edited help module whose id is "+id+". Values saved were  </b>:"+helptext+"  ',time='"+dat+"',actor_id='"+session.getAttribute("userid")+"'";                     
//if (session.getAttribute("userid")!=null) {
//       
//         
//       conn.state.executeUpdate(inserter);    
//             }
    
    
    PrintWriter out = response.getWriter();
    try {
        
       // out.println(qr);
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(updatehelp.class.getName()).log(Level.SEVERE, null, ex);
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
