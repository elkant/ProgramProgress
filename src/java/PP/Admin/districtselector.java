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
public class districtselector extends HttpServlet {

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
    String CountyID,current_districts1;
   HttpSession session; 
    ArrayList dist=new ArrayList();
   
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CountyID=request.getParameter("district"); 
           System.out.println(" CountyID:"+ CountyID); 
           current_districts1="";
           
           String districts="select * from districts where countyID='"+CountyID+"'";
           System.out.println(districts);
           dbConnect conn=new dbConnect();
           
           conn.rs=conn.state.executeQuery(districts);
           
           //add all the districts to the 
          
           current_districts1="<option value=\"\">Choose District</option>";
           
           while(conn.rs.next()){
               
         //dist arraylist stays in the session
//          dist.add(conn.rs.getString("DistrictName")); 
           System.out.println("DIST ID"+conn.rs.getInt("districtID"));
          
          //dynamically add districts to the string array
          
          current_districts1=current_districts1+"<option value=\""+conn.rs.getInt("districtID")+"\">"+conn.rs.getString("DistrictName")+"</option>";
//        session.setAttribute("dist_names",dist);
         //12767711
        //3840
       //      1994
       // System.out.println(" <<>"+conn.rs.getString("district_name"));
         
           }
               
           
          //  System.out.println("size:" +dist.size());
      
          // System.out.println("My districts:"+current_districts);
           
           
          // PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +current_districts1+"</h1>");
            out.println("</body>");
            out.println("</html>");
             
           
           
           
           
        
          // response.sendRedirect("myajax.html");
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
            Logger.getLogger(districtselector.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(districtselector.class.getName()).log(Level.SEVERE, null, ex);
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
