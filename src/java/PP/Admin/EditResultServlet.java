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
public class EditResultServlet extends HttpServlet {

    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
              dbConnect conn = new dbConnect();
session = request.getSession(true);
                                
                                
                                        
                                        
                                        ArrayList result = new ArrayList();
                                        String query="select * from indicatorachieved";
                                        
//                                        String query = "select indicatorachieved.resultID,indicatorresults.menAchieved, indicatorresults.womenAchieved,baseline.QtargetMen,baseline.QtargetWomen,"
//                                                + "baseline.menBaseline,baseline.womenBaseline,baseline.endTargetMen,baseline.endTargetWomen,baseline.FinancialYear,baseline.Quarter,"
//                                                + "baseline.District,baseline.titleID from indicatorresults LEFT JOIN baseline ON indicatorresults.baselineid=baseline.baselineID ";
				
				System.out.println("query " + query);
				conn.state = conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(query);
				


				
              
        if(result!=null && result.size()>0 ){result.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                                         
                       ResultBean DB= new ResultBean();
                       DB.setRESULTID(conn.rs.getString("resultID"));
                       DB.setMENACHIEVED(conn.rs.getString("menAchieved"));
                       DB.setWOMENACHIEVED(conn.rs.getString("womenAchieved"));
                       DB.setDISTRICT(conn.rs.getString("District"));
                       DB.setFINANCIAL(conn.rs.getString("financialYear"));
                       DB.setQUARTER(conn.rs.getString("reportingPeriod"));
                       DB.setCOUNTY(conn.rs.getString("County"));
                       DB.setTITLEID(conn.rs.getString("titleID"));
//                      if(conn.rs.getString("District")!=null){
//                       System.out.println("district id: "+conn.rs.getString("District"));
//                       String district_selector="select * from districts where districtID='"+conn.rs.getString("District")+"'";    
//                       conn.rs2=conn.state2.executeQuery(district_selector);
//                       conn.rs2.next();
//                     
//                    System.out.println("district name: "+conn.rs2.getString("DistrictName"));
//                       }
                      
                    
                      
          
                       result.add(DB);
                       
                      
        }
       session.setAttribute("result", result);
                                 String nextJSP = "/EditResult.jsp";
                                        
                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                                        dispatcher.forward(request,response);
                                        conn.connect.close();
                                        System.out.println("Disconnected from database");
       
        
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
            Logger.getLogger(EditResultServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditResultServlet.class.getName()).log(Level.SEVERE, null, ex);
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
