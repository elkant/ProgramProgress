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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class titleselector extends HttpServlet {

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
    String titleID,current_title;
   HttpSession session; 
    ArrayList dist=new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
           
       
           titleID=request.getParameter("titleID"); 
           System.out.println(" CountyID:"+ titleID); 
           current_title="";
           session = request.getSession();
           session.setAttribute("titleID",titleID);
           
           String titles="select * from indicatortitles where titleID='"+titleID+"'";
           System.out.println(titles);
           dbConnect conn=new dbConnect();
           
           conn.rs=conn.state.executeQuery(titles);
           
           //add all the districts to the 
          
          
           current_title="";
           
           while(conn.rs.next()){
               
         //dist arraylist stays in the session
          dist.add(conn.rs.getString("titleNo")); 
          
          
          //dynamically add districts to the string array
          
          current_title=current_title+"<td><input name=\"titleNo\" id=\"titleNo\" readonly value=\""+conn.rs.getString("titleNo")+"\" /></td>";
//        session.setAttribute("dist_names",dist);
         //12767711
        //3840
       //      1994
       // System.out.println(" <<>"+conn.rs.getString("district_name"));
         
           }
               
           
           System.out.println("size:" +current_title);
      
          // System.out.println("My districts:"+current_districts);
           
           
          // PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h4>" +current_title+"</h4>");
            out.println("</body>");
            out.println("</html>");
             
           
           
           
           
        
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
            Logger.getLogger(districtselector.class.getName()).log(Level.SEVERE, null, ex);
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
