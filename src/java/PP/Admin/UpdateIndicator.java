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
 * @author Maureen
 */
public class UpdateIndicator extends HttpServlet {

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
    dbConnect conn;
       String allValues="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        allValues="";
               
// thsi gets the id og the row that is being edited
String id = request.getParameter("id");// values passed from the ajax
     String value = request.getParameter("value");// values passed from the ajax
//     String value1 = request.getParameter("cellvalue%5B%5D");// values passed from the ajax
//     String value2 = request.getParameter("value2");// values passed from the ajax
//     String value3 = request.getParameter("value3");// values passed from the ajax
    String columnName = request.getParameter("columnName");// values passed from the ajax
     String columnId = request.getParameter("columnId");// values passed from the ajax
     String columnPosition = request.getParameter("columnPosition");// values passed from the ajax
     String rowId = request.getParameter("rowId"); // values passed from the ajax 
//System.out.println("value"+value);
System.out.println("columnName"+columnName);
System.out.println(columnId);
int CID =(Integer.parseInt(columnId)) ;
System.out.println(columnPosition+columnPosition);
System.out.println(rowId+rowId);
//System.out.println("value recieved"+value1);
//response.getWriter().print(value); 


 String values []= null;
  System.out.println(allValues);
//     if(value==null)
//     {
     values=request.getParameterValues("cellvalue[]");
     
     
     
     int arr1length=0;
    
//for(int a=0;a<age1.length;a++){
//
//age1val+=age1[a];
//if(a+1<arr1length){
//age1val+=",";
//}
//
//}//end of forloop
      if(values!=null){
   arr1length=  values.length;
    
     for(int i=0;i<values.length;i++){
System.out.println("county"+values[i]);
allValues+=values[i];
if(i+1<arr1length){
allValues+=",";
}

} 
     // response.getWriter().print(allValues+=","); 
      
      }
      else{
       response.getWriter().print(value);
      }
  
 // }

 session= request.getSession(true);
 
   System.out.println(allValues);
//  String unique=(String)session.getAttribute("countyid");
	dbConnect conn = new dbConnect();
       String query1="";
       String query2="";
       String query3="";
       String query4="";
        
 	// update the table with the value that was edited from the form
        if(columnName.equals("Title No")){
	 query1 = "update indicatortitles set titleNo='"+value+"' where titleID='"+id+"'";
          conn.state.executeUpdate(query1);
//           out.println("Saved, Refresh page");
        }
        else if(columnName.equals("Title")){
            query2 = "update indicatortitles set title='"+value+"' where titleID='"+id+"'";
             conn.state.executeUpdate(query2);
//              out.println("Saved, Refresh page");
        }
        else if(CID==3){
            query3 = "update indicatortitles set tableNo='"+value+"' where titleID='"+id+"'";
             conn.state.executeUpdate(query3);
//             out.println("Saved, Refresh page");
        }
         
             else if(CID==4){
             query4 = "update indicatortitles set tableIdentifier='"+value+"' where titleID='"+id+"'";
             conn.state.executeUpdate(query4);
//              out.println("Saved, Refresh page");
            }
             else if(CID==5){
               query4 = "update indicatortitles set designation='"+allValues+"' where titleID='"+id+"'";
             conn.state.executeUpdate(query4);
               out.println("Saved, Refresh page");
            }
         
      System.out.println(query1);
      System.out.println(query2);
      System.out.println(query3);
      System.out.println(query4);
                                
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
            Logger.getLogger(UpdateIndicator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateIndicator.class.getName()).log(Level.SEVERE, null, ex);
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
