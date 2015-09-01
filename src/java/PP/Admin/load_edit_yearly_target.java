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
public class load_edit_yearly_target extends HttpServlet {
String counties,districts;
String indicator_id,table_identifier,details;
int positioner,table_id,baseline_men,baseline_women,baseline_totals,target_men,target_women,target_total;;
 String table_definition,year,all_years;
 int displayer,table_type=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
     
            
    dbConnect conn = new dbConnect();
    positioner=table_id=0;
     table_definition=all_years="";
     displayer=table_type=0;
   indicator_id=request.getParameter("indicator_id");
   year=request.getParameter("year");
//     System.out.println("the indicator id is  :  "+indicator_id+" year is : "+year);       
      details="";      
     String get_data="SELECT * FROM yearly_targets WHERE indicator_id='"+indicator_id+"' && year='"+year+"'";
     conn.rs=conn.state.executeQuery(get_data);
     while(conn.rs.next()){
         positioner++;
         counties=districts="";
         table_type=conn.rs.getInt("table_type");
         table_id=conn.rs.getInt("id");
      target_men=conn.rs.getInt("target_men");   
      target_women=conn.rs.getInt("target_women");
      target_total=conn.rs.getInt("target_combined");
      String ct_id=conn.rs.getString("county_id");
       String dist_id=conn.rs.getString("district_id");
       
       for (int i=2010;i<=2015;i++){
           String current_y=i+"";
         if(year.equals(current_y)){
             all_years+="<option value=\""+current_y+"\" selected>"+current_y+"</option>";
         } 
         else{
             all_years+="<option value=\""+current_y+"\" >"+current_y+"</option>";
         } 
          
       }
       
      String select_county="SELECT * FROM county";
      conn.rs1=conn.state1.executeQuery(select_county);
      while(conn.rs1.next()){
          if(conn.rs1.getString(1).equals(ct_id)){
            counties+="<option value=\""+conn.rs1.getString(1)+","+positioner+"\" selected>"+conn.rs1.getString(2)+"</option>";   
             
          }
          else{
              counties+="<option value=\""+conn.rs1.getString(1)+","+positioner+"\">"+conn.rs1.getString(2)+"</option>"; 
          }  
      }
      
      String select_district="SELECT * FROM districts WHERE countyID='"+ct_id+"'";
      conn.rs1=conn.state1.executeQuery(select_district);
      while(conn.rs1.next()){
          if(conn.rs1.getString(1).equals(dist_id)){
            districts+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(3)+"</option>";   
             
          }
          else{
              districts+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(3)+"</option>"; 
          }  
      }
     if(table_type==1) { 
         displayer=0;
    details+= "<tr>"
            + "<td><input type=\"hidden\" name=\"table_id"+positioner+"\" value=\""+table_id+"\"style=\"width: 150px;\">"
            + "<select name=\"county_id"+positioner+"\" id=\"county_id"+positioner+"\"style=\"width: 150px;\" onchange=\"load_districts(this);\" required>"+counties+"</select></td>"
            + "<td><select name=\"district_id"+positioner+"\" id=\"district_id"+positioner+"\"style=\"width: 150px;\" required>"+districts+"</select></td>"
            + "<td><select name=\"year"+positioner+"\" id=\"year"+positioner+"\"style=\"width: 150px;\" required>"+all_years+"</select></td>"
            + "<td><input type=\"text\" name=\"target_men"+positioner+"\" pattern=\"[0-9]{0,8}\" value=\""+target_men+"\"style=\"width: 144px;\" required></td>"
            + "<td><input type=\"text\" name=\"target_women"+positioner+"\" pattern=\"[0-9]{0,8}\" value=\""+target_women+"\"style=\"width: 144px;\" required></td>"
            + "</tr>";  
     }
     else if(table_type==2){
         displayer=1;
     details+= "<tr>"
            + "<td><input type=\"hidden\" name=\"table_id"+positioner+"\" value=\""+table_id+"\"style=\"width: 150px;\">"
            + "<select name=\"county_id"+positioner+"\" id=\"county_id"+positioner+"\"style=\"width: 180px;\" onchange=\"load_districts(this);\" required>"+counties+"</select></td>"
            + "<td><select name=\"district_id"+positioner+"\" id=\"district_id"+positioner+"\"style=\"width: 187px;\" required>"+districts+"</select></td>"
            + "<td><select name=\"year"+positioner+"\" id=\"year"+positioner+"\"style=\"width: 180px;\" required>"+all_years+"</select></td>"
            + "<td><input type=\"text\" name=\"target_total"+positioner+"\" pattern=\"[0-9]{0,8}\" id=\"target_total"+positioner+"\" value=\""+target_total+"\"style=\"width: 206px;\" required></td>"
            + "</tr>";  
     }
     }
     details+="<input type=\"hidden\" name=\"total\" value=\""+positioner+"\">";
     if(positioner>0 && displayer==0){
       table_definition="<table cellpadding=\"2px\" cellspacing=\"3px\" border=\"1px\" class=\"\"  style=\"background: #ccccff; max-width: 632px; margin-top: -20px; margin-left: 200px \">"
               + "<tr><td>County</td><td>District</td><td>Pepfar Year</td><td>Target Men</td><td>Target Women</td></tr>"
               + ""+details+""
               + "<tr>"
               + "<td colspan=\"6\"><input type=\"submit\" name=\"sub\" id=\"sub\"value=\"Save\" style=\"background: #682bc2; color: #fff; margin-left: 400px; width: 80px; height: 30px;\"></td>"
               + "</tr>"
               + "</table>"; 
     }
     if(positioner>0 && displayer==1){
       table_definition="<table cellpadding=\"2px\" cellspacing=\"3px\" border=\"1px\" class=\"\"  style=\"background: #ccccff; max-width: 1000px; margin-top: -20px; margin-left: 200px \">"
               + "<tr><td>County</td><td>District</td><td>Pepfar Year</td><td>Target</td></tr>"
               + ""+details+""
               + "<tr>"
               + "<td colspan=\"4\"><input type=\"submit\" name=\"sub\" id=\"sub\"value=\"Save\" style=\"background: #682bc2; color: #fff; margin-left: 400px; width: 80px; height: 30px;\"></td>"
               + "</tr>"
               + "</table>";  
     }
//     System.out.println(details);       
            
        if(positioner==0)    {
            table_definition="<font color=\"red\" size=\"4px;\" style=\"margin-left: 250px;\"> The Year "+year+"'s Project targets  have not been set for this indicator.</font>";
        }
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +table_definition+ "</h1>");
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
            Logger.getLogger(load_edit_yearly_target.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_edit_yearly_target.class.getName()).log(Level.SEVERE, null, ex);
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
