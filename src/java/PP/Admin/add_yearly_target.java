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
public class add_yearly_target extends HttpServlet {

    String indicator_id,county_id,district_id,year,target_men,target_women,target_combined;
int max_rows=0;
String table_type="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         dbConnect conn= new dbConnect();
        max_rows=Integer.parseInt(request.getParameter("all_rows"));
        System.out.println("the maximum number of rows is  : "+max_rows);
        
        for(int i=1;i<=max_rows;i++){
        indicator_id=county_id=district_id=year=target_men=target_women=target_combined="0";
        
    String    indicator=request.getParameter("indicator_id"+i);   
     String   county=request.getParameter("county_id"+i); 
        district_id=request.getParameter("district_id"+i); 
        year=request.getParameter("year"+i);
        target_men=request.getParameter("target_m"+i); 
        target_women=request.getParameter("target_f"+i); 
        target_combined=request.getParameter("target"+i); 
       
        System.out.println("At position : "+i);
        System.out.println("county "+county+"  district  : "+district_id+" indicator id  : "+indicator);   
        System.out.println("target total "+target_combined+"  target men  : "+target_men+" target_women  : "+target_women); 
        System.out.println("");
        
        if(district_id!=null && !"".equals(district_id)){
        String [] indics = indicator.split(",");
        indicator_id=indics[0];
        table_type=indics[1];
        String [] count_det=county.split(",");
        county_id=count_det[0];
        
        
        String table_identifier=indics[1];
        if( "1".equals(table_identifier)){
            int t_men,t_women=0;
            
            t_men=Integer.parseInt(target_men);
            t_women=Integer.parseInt(target_women);
            
       int target_total=t_men+t_women;
      
       target_combined=target_total+"";
       }
         if( "2".equals(table_identifier)){
          target_men=target_women="0";
       }
//       CHECK AND INSERT TO THE DATABASE.............................
   String check_existence="SELECT * FROM yearly_targets WHERE county_id='"+county_id+"' && district_id='"+district_id+"' && indicator_id='"+indicator_id+"' && year='"+year+"'";  
      conn.rs=conn.state.executeQuery(check_existence);
      if(conn.rs.next()==false){
      String inserter="INSERT INTO yearly_targets SET county_id='"+county_id+"',district_id='"+district_id+"',indicator_id='"+indicator_id+"',year='"+year+"'"
           + ", target_combined='"+target_combined+"',target_men='"+target_men+"', target_women='"+target_women+"',table_type='"+table_type+"'";  
       conn.state1.executeUpdate(inserter);   
          
      }
        }
        
        }
        response.sendRedirect("set_yearly_target.jsp");
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
            Logger.getLogger(add_yearly_target.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_yearly_target.class.getName()).log(Level.SEVERE, null, ex);
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
