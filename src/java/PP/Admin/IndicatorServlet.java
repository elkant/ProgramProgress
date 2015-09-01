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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class IndicatorServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
           
              dbConnect conn = new dbConnect();
session = request.getSession(true);
                                
                                
        String desi=""; 
          String designations="";
                                        
                                        ArrayList indicators = new ArrayList();
                                        
                                        String query = "select * from indicatortitles";
                                        System.out.println("query " + query);
                try {
                    conn.state = conn.connect.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(IndicatorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                                        conn.rs = conn.state.executeQuery(query);
        if(indicators!=null && indicators.size()>0 ){indicators.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                                         
                       IndicatorBean DB= new IndicatorBean();
                       DB.setTITLEID(conn.rs.getInt("titleID"));
                       DB.setTITLE(conn.rs.getString("title"));
                       DB.setTITLENO(conn.rs.getString("titleNo"));
                       DB.setTABLENO(conn.rs.getString("tableNo"));
                       DB.setTABLEIDENTIFIER(conn.rs.getString("tableIdentifier"));
                       desi=conn.rs.getString("designation");
                       String designations1="";
//                       System.out.println(desi);
                       if(desi!=null && !desi.equals("")){
                       String[] des = desi.split(",");
                     int arr1length=des.length;
                       for(int k=0;k<des.length;k++){
                       String query1 ="select * from designation where accesslevel ='"+des[k]+"'" ;
                       conn.rs4= conn.state4.executeQuery(query1);
                       while(conn.rs4.next()){
                            designations+=conn.rs4.getString("designation");
                            if(k+1<arr1length){
                            designations+=",";
                            }
                           //designations+=conn.rs4.getString("designation")+",";
//                           System.out.println
                       }
                          
                               }
                       DB.setDESIGNATION(designations);
                       designations="";
                       }
                        
                       else {
                        DB.setDESIGNATION(desi);}
                      
          
                       indicators.add(DB);
                       
//                       System.out.println(DB.getTITLE());
//                       System.out.println(DB.getTITLENO());
//                       System.out.println(DB.getTITLEID());
//                       
                      
        }
       session.setAttribute("indicators", indicators);
                                 String nextJSP = "/IndicatorTitles.jsp";
                                        
                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                                        dispatcher.forward(request,response);
                                        conn.connect.close();
                                        System.out.println("Disconnected from database");
        } catch (SQLException ex) {
            Logger.getLogger(IndicatorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
