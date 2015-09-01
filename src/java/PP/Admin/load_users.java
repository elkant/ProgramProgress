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

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_users extends HttpServlet {
String user_level;
String fname,mname,lname,username,phone,userid,user_type;
String all_user_details="";
String user_levels="",user;
int positioner;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         user_level = request.getParameter("level") ; 
       dbConnect conn = new dbConnect();
      if(user_level.equals("1")){
        user="Administrator(s)";  
      }
      if(user_level.equals("2")){
        user="Clerk(s)";  
      }
      if(user_level.equals("5")){
       user="Guest(s)" ;  
      }
       
       all_user_details="";
       positioner=0;
       all_user_details+=""
               + "<tr><td>User ID</td>"
             
               + "<td>User Name<font color=\"red\">*</font></td>"
               + "<td>Level<font color=\"red\">*</font></td>"
               + "</tr>";
       String selector="SELECT * FROM users WHERE AccessLevel='"+user_level+"'";
       conn.rs=conn.state.executeQuery(selector);
       while(conn.rs.next()){
           user_levels="";
           positioner++;
           fname=mname=lname=username=phone=userid=user_type="";
           fname=conn.rs.getString("Firstname");
           mname=conn.rs.getString("Middlename");
           lname=conn.rs.getString("Surname");
           username=conn.rs.getString("Username");
           phone=conn.rs.getString("Phoneno");
           userid=conn.rs.getString("UserID");
           user_type=conn.rs.getString("AccessLevel");
           if(user_level.equals("1")){
               user_levels+="<option value=\"1\" selected>Administrator</option>";
               user_levels+="<option value=\"2\" >Clerk</option>";
                user_levels+="<option value=\"5\" >Guest</option>";
           }
           if(user_level.equals("2")){
               user_levels+="<option value=\"2\" selected>Clerk</option>";
               user_levels+="<option value=\"1\" >Administrator</option>";
                user_levels+="<option value=\"5\" >Guest</option>";
           }
           if(user_level.equals("5")){
               user_levels+="<option value=\"5\" selected>Guest</option>";
               user_levels+="<option value=\"1\" >Administrator</option>";
                user_levels+="<option value=\"2\" >Clerk</option>";
           }
        all_user_details+="<tr>"
                + "<td> <input type=\"text\" name=\"userid"+positioner+"\" id=\"userid"+positioner+"\" value=\""+userid+"\" readonly></td>"
                + "<td> <input type=\"text\" name=\"fname"+positioner+"\" id=\"fname"+positioner+"\" value=\""+fname+"\" required></td>"
                + "<td> <input type=\"text\" name=\"mname"+positioner+"\" id=\"mname"+positioner+"\" value=\""+mname+"\" ></td>"
                + "<td> <input type=\"text\" name=\"lname"+positioner+"\" id=\"lname"+positioner+"\" value=\""+lname+"\" required></td>"
                + "<td> <input type=\"text\" name=\"phone"+positioner+"\" id=\"phone"+positioner+"\" value=\""+phone+"\"></td>"
                + "<td> <input type=\"text\" name=\"username"+positioner+"\" id=\"username"+positioner+"\" value=\""+username+"\" required></td>"
                + "<td><select name=\"level"+positioner+"\" id=\"level"+positioner+"\" required>"+user_levels+"</select></td>"
                + "</tr>";   
       }
         all_user_details+=""
                 + "<input type=\"hidden\" name=\"total\" value=\""+positioner+"\">"
                 + "<tr><td><input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Save\" style=\" margin-left: 500px;\"></td></tr>"
                 + "";
         if(positioner==0){
          all_user_details="<font size=\"3px\" color=\"red\" style=\"margin-left: 200px;\">No "+user+" are currently Registered in the system.</font>";   
         }
//        System.out.println("Here with level  :  "+user_level);    
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet load_users</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<tr>" +all_user_details+"</tr>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(load_users.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_users.class.getName()).log(Level.SEVERE, null, ex);
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
