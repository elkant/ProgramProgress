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
 * @author Maureen
 */
public class filterhelp extends HttpServlet {

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
  String menuid="";
   String newaccordion="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    menuid=request.getParameter("menuid");
    
    String qry="select * from help where menu_id='"+menuid+"'";
    
    newaccordion="";
    dbConnect conn= new dbConnect();
    
    conn.rs2=conn.state.executeQuery(qry);
    
    int counter=1;
    while(conn.rs2.next()){
        
        newaccordion+="<h3>"+counter+"  "+conn.rs2.getString(3)+" <b>></b> <font color=\"orange\">"+conn.rs2.getString(5)+"</font><span id=\"saved"+counter+"\"></span></h3>"
        +"<div>"
         + "<textarea cols=\"80\" rows=\"5\" onkeypress=\"savedstatus('"+counter+"');\" id=\"textarea"+counter+"\" >"+conn.rs2.getString(2)+"</textarea>"
        +"<input type=\"button\" name=\"edit\"  value=\"save\" style=\"background-color:coral;height:30px ;font-weight:normal;color:#ffffff;\" onclick=\"edithelp('"+conn.rs2.getString(1)+"','"+counter+"');\"/>"
                + "</div>";
	
     
                      
                    
    counter++;                    
    
    
    
    }
    
   // System.out.println(newaccordion);
    
    PrintWriter out = response.getWriter();
    try {
        out.println(newaccordion);
       
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(filterhelp.class.getName()).log(Level.SEVERE, null, ex);
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
