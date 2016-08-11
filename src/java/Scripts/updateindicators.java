/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scripts;

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
public class updateindicators extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConnect conn=new dbConnect();
          dbConnectPPMP ppmpconn = new dbConnectPPMP(); 
           
           String getmatchingindicators="select * from indicatortitles  where ppmpid !=0 ";
           
           conn.rs=conn.state.executeQuery(getmatchingindicators);
           int count =0;
           while(conn.rs.next()){
               System.out.println(conn.rs.getString("ppmpid")+"__________"+conn.rs.getString("title"));
               //select indicator name from ppmp where indicator name is as shown
               
               String getppmpid="select Indicator from indicators where Indicator_ID='"+conn.rs.getString("ppmpid")+"'";
               ppmpconn.rs1=ppmpconn.state1.executeQuery(getppmpid);
               while (ppmpconn.rs1.next()){
               
                   System.out.println(conn.rs.getString("ppmpid")+"==="+ppmpconn.rs1.getString(1));
                   //now update ppmt indicators
                   
                   
             String updateindicator="update indicatortitles set title='"+ppmpconn.rs1.getString(1)+"' where titleID='"+conn.rs.getString("titleID")+"'";
                //   System.out.println("_"+conn.state2.executeUpdate(updateindicator));
              
               }
           }
            out.println("</html>");
        } finally {
            out.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(updateindicators.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(updateindicators.class.getName()).log(Level.SEVERE, null, ex);
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
