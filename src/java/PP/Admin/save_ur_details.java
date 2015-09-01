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
public class save_ur_details extends HttpServlet {
String fname,mname,lname,username,phone,password,userid;
int counter;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
         dbConnect conn = new dbConnect();
         counter=0;
         session=request.getSession();
         String location="";
        location = request.getParameter("location");
        userid=request.getParameter("userid");
        fname=request.getParameter("fname");
        mname=request.getParameter("mname");
        lname=request.getParameter("lname");
        username=request.getParameter("username");
        phone=request.getParameter("phone");
        password=request.getParameter("pass");
        location=request.getParameter("location");
        
        
        if(userid!=null && !userid.equals("")){
            
           String check_userid_existence="SELECT COUNT(UserID) FROM users WHERE UserID='"+userid+"'";
           conn.rs=conn.state.executeQuery(check_userid_existence);
           if(conn.rs.next()==true){
               counter=conn.rs.getInt(1);
              }
            
            if(counter>0){
               String updator="UPDATE users SET Surname='"+lname+"',Firstname='"+fname+"',Middlename='"+mname+"',Phoneno='"+phone+"',Username='"+username+"',Password='"+password+"',Location='"+location+"' WHERE UserID='"+userid+"'";
                         System.out.println(updator);
               int update= conn.state.executeUpdate(updator);
               session.setAttribute("updated","User "+fname +"  updated successfully");
      
         if(update>0){
         session.setAttribute("Username", username); 
            }
         
            }
        }
 conn.rs.close();
 conn.state.close();
        response.sendRedirect("edit_ur_details.jsp");
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
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
