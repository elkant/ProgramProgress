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
public class UpdateCounty extends HttpServlet {

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
         			



String id = request.getParameter("id");

     String value []= null;
     String allValues="";
     if(value==null)
     {
     value=request.getParameterValues("value[]");
     for(int i=0;i<value.length;i++){
System.out.println("county"+value[i]);
allValues+=value[i]+"_";
 response.getWriter().print(value[i]); 
}
     }

    
   
HttpSession session;

 session= request.getSession(true);
//  String unique=(String)session.getAttribute("countyid");
	dbConnect conn = new dbConnect();		
 			
	String query = "update county set countyName='"+allValues+"'where countyID='"+id+"'";
				
           System.out.println(query);
			     
                                try {
                        conn.state.executeUpdate(query);
//                         out.println("Saved,Refresh Page");
                       
//                   response.sendRedirect("CountyServlet");
            } 
                        
                    catch (SQLException ex) {
                                Logger.getLogger(UpdateCounty.class.getName()).log(Level.SEVERE, null, ex);
                                out.println(ex.toString());
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
