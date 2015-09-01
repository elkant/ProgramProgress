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
public class save_edited_overall_target extends HttpServlet {
HttpSession session;
String county_id,id,district_id,data_id;
int b_men,b_women,b_total,tar_men,tar_women,tar_total,total;
String indicator_id;
int found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConnect conn = new dbConnect();

total=Integer.parseInt(request.getParameter("total"));
indicator_id=request.getParameter("indicator_id");
//System.out.println("the max rows is  :  "+indicator_id);
for (int i=1; i<=total;i++){
    data_id=request.getParameter("table_id"+i);
    
    found=0;
  String  county=request.getParameter("county_id"+i);
  String [] ct=county.split(",");
  county_id=ct[0];
  
    district_id=request.getParameter("district_id"+i);
    
   String base_men=request.getParameter("baseline_men"+i);
    String base_women=request.getParameter("baseline_women"+i);
    String base_total=request.getParameter("baseline_total"+i);
    
   String target_men=request.getParameter("target_men"+i);
    String target_women=request.getParameter("target_women"+i);
   String target_total=request.getParameter("target_total"+i);
    if(base_men==null && base_women==null && target_men==null && target_women==null){
        b_total=Integer.parseInt(base_total);
        tar_total=Integer.parseInt(target_total);
        
        b_men=b_women=tar_men=tar_women=0; 
    }
    if(base_total==null && target_total==null){
       b_men=Integer.parseInt(base_men);
       b_women=Integer.parseInt(base_women);
       b_total=b_men+b_women;
       
       tar_men=Integer.parseInt(target_men);
       tar_women=Integer.parseInt(target_women);
       tar_total=tar_men+tar_women;
       
        
    }
    
    String chek_existence="SELECT COUNT(id) FROM project_target WHERE county_id='"+county_id+"' && district_id='"+district_id+"' && indicator_id='"+indicator_id+"' && id!='"+data_id+"'";
    conn.rs2=conn.state2.executeQuery(chek_existence);
    if(conn.rs2.next()==true){
        found=conn.rs2.getInt(1);
//        System.out.println("FOUND"+conn.rs2.getInt(1));
    }
    
    if(found==0){
  String database_updator="UPDATE project_target SET county_id='"+county_id+"', district_id='"+district_id+"',baseline_men='"+b_men+"',"
          + "baseline_women='"+b_women+"',baseline_combined='"+b_total+"',target_men='"+tar_men+"',target_women='"+tar_women+"',"
          + "target_combined='"+tar_total+"' WHERE id='"+data_id+"'"; 
  conn.state.executeUpdate(database_updator);
      
   
}
    System.out.println(data_id); 
    found=0;
}

response.sendRedirect("edit_project_target.jsp");
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
            Logger.getLogger(save_edited_overall_target.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_edited_overall_target.class.getName()).log(Level.SEVERE, null, ex);
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
