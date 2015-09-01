/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maureen
 */
public class RegisterUser extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
            dbConnect conn = new dbConnect();


            String userid = request.getParameter("userid").toString();
            String surname = request.getParameter("surname").toString();
            String firstname = request.getParameter("first_name").toString();
            String m_name = request.getParameter("m_name").toString();
            String phoneno = request.getParameter("phoneno").toString();
            String username = request.getParameter("username").toString();
            String password = request.getParameter("password").toString();
            String accesslevel = request.getParameter("accesslevel").toString();
            
            
             Date timestamp= new Date();

           
String query = "INSERT INTO users(UserID,Surname,Firstname,Middlename,Phoneno,Username,Password,AccessLevel)"
+ " VALUES ('"+userid+"','"+surname+"','"+firstname+"','"+m_name+"','"+phoneno+"','"+username+"','"+password+"','"+accesslevel+"')";		
        try {    
           
            conn.state.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            out.println("Inserted into db");
            response.sendRedirect("/ProgramProgress/admin_home.jsp");
        
                       
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
