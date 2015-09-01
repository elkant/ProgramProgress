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
 * @author Geofrey Nyabuto
 */
public class save_edited_user extends HttpServlet {
String fname,mname,lname,username,level,phone,userid;
int total_users;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
     dbConnect conn = new dbConnect();
     
     
     total_users=Integer.parseInt(request.getParameter("total"));
     for(int i=1;i<=total_users;i++){
         fname=request.getParameter("fname"+i);
         mname=request.getParameter("mname"+i);
         lname=request.getParameter("lname"+i);
         username=request.getParameter("username"+i);
         level=request.getParameter("level"+i);
         phone=request.getParameter("phone"+i);
         userid=request.getParameter("userid"+i);
         
         
         String updator="UPDATE users SET Surname='"+lname+"',Firstname='"+fname+"',Middlename='"+mname+"',Phoneno='"+phone+"',Username='"+username+"',AccessLevel='"+level+"' WHERE UserID='"+userid+"'";
         conn.state.executeUpdate(updator);
         
     }
    conn.state.close();
     
     response.sendRedirect("edit_user_details.jsp");
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
            Logger.getLogger(save_edited_user.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_edited_user.class.getName()).log(Level.SEVERE, null, ex);
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
