/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PPMP;

import PP.Admin.dbConnect;
import PP.Admin.dbConnectPPMP;
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
 * @author Emmanuel E
 */
public class updatebaseline extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");           
            PrintWriter out = response.getWriter();
           // String getppmpindicators="SELECT I_ID,baseline FROM ppmp.master_record where I_ID in ( select ppmpid from programprogress.indicatortitles) group by I_ID";
            String getppmpindicators=" SELECT Indicator_ID, Indicator_Type_Value as cumulative_chooser FROM ppmp.indicators join ppmp.indicator_type on indicators.Cumulative_Chooser=indicator_type.IT_ID ";
            
            dbConnectPPMP ppmpconn=new dbConnectPPMP();
            
            dbConnect conn= new dbConnect();
            
            
            conn.rs=conn.state.executeQuery(getppmpindicators);
            while(conn.rs.next())
            {
                
                
       //String updatestring="update programprogress.indicatortitles set totalbaseline='"+conn.rs.getString("baseline").trim()+"' where ppmpid='"+conn.rs.getString("I_ID")+"'";
       String updatestring="update programprogress.indicatortitles set cumulative_chooser='"+conn.rs.getString("cumulative_chooser")+"' where ppmpid='"+conn.rs.getString("Indicator_ID")+"'";
     //uncomment the lne below to execute the code
        conn.state1.executeUpdate(updatestring);
         
                 System.out.println("__"+updatestring); 
                
            }
            
            try {
                
                
                
                
                out.println("</html>");
                
                
            } finally {
                out.close();
            }
        }   catch (SQLException ex) {
            Logger.getLogger(updatebaseline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
