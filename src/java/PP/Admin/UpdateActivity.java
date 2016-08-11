/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class UpdateActivity extends HttpServlet {

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
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                      
// thsi gets the id og the row that is being edited
String id = request.getParameter("id");// values passed from the ajax
     String value = request.getParameter("value");// values passed from the ajax
//     String value2 = request.getParameter("value2");// values passed from the ajax
//     String value3 = request.getParameter("value3");// values passed from the ajax
    String columnName = request.getParameter("columnName");// values passed from the ajax
     String columnId = request.getParameter("columnId");// values passed from the ajax
     String columnPosition = request.getParameter("columnPosition");// values passed from the ajax
     String rowId = request.getParameter("rowId"); // values passed from the ajax 
System.out.println("value"+value);
System.out.println("columnName"+columnName);
System.out.println(columnId);
int CID =(Integer.parseInt(columnId)) ;
System.out.println(columnPosition);
System.out.println(rowId);
response.getWriter().print(value); 

 session= request.getSession(true);
//  String unique=(String)session.getAttribute("countyid");
	dbConnect conn = new dbConnect();
       String query1="";
       String query2="";
       String query3="";
       String query4="";
       String query5="";
        System.out.print("CID "+CID);
        Date date= new Date();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = formatter.format(date);
			
        
         query5 = "update indicatoractivities set timestamp='"+formattedDate+"' where activityID='"+id+"'";
             conn.state5.executeUpdate(query5);
//              out.println("Saved, Refresh page");
              System.out.println("query"+query5);
 	// update the table with the value that was edited from the form
        if(columnName.equalsIgnoreCase("unit")){
	 query1 = "update indicatoractivities set unit='"+value+"' where activityID='"+id+"'";
          conn.state.executeUpdate(query1);
//           out.println("Saved, Refresh page");
        }
        else if(columnName.equalsIgnoreCase("Indicator")){
            query2 = "update indicatoractivities set IndicatorID='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query2);
//              out.println("Saved, Refresh page");
        }
        else if(columnName.equalsIgnoreCase("county")){
            query2 = "update indicatoractivities set countyID='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query2);
//              out.println("Saved, Refresh page");
        }
        else if(columnName.equalsIgnoreCase("district")){
            query3 = "update indicatoractivities set DistrictID='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query3);
//             out.println("Saved, Refresh page");
        }
        else if(columnName.equalsIgnoreCase("activity")){
            query3 = "update indicatoractivities set activityTitle='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query3);
//             out.println("Saved, Refresh page");
        }
        
        else if(columnName.equalsIgnoreCase("Others") ){
             query4 = "update indicatoractivities set activityOthers='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query4);
//              out.println("Saved, Refresh page");
              System.out.println("query"+query4);
            }
        else if(columnName.equalsIgnoreCase("Start Date") ){
             query4 = "update indicatoractivities set startDate='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query4);
//              out.println("Saved, Refresh page");
              System.out.println("query"+query4);
            }
             
        else if(columnName.equalsIgnoreCase("End Date") ){
             query4 = "update indicatoractivities set endDate='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query4);
//              out.println("Saved, Refresh page");
              System.out.println("query"+query4);
            }
      
              
        else if(columnName.equalsIgnoreCase("Sub-Total") ){
             query4 = "update indicatoractivities set subtotals='"+value+"' where activityID='"+id+"'";
             conn.state.executeUpdate(query4);
//              out.println("Saved, Refresh page");
              System.out.println("query"+query4);
            }
        
      System.out.println(query1);
      System.out.println(query2);
      System.out.println(query3);
      System.out.println("query"+query4);
                                
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
            Logger.getLogger(UpdateActivity.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateActivity.class.getName()).log(Level.SEVERE, null, ex);
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
