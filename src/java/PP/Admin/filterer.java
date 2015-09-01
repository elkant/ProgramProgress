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
public class filterer extends HttpServlet {

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
    String financial,quarter,district,current_title;
   HttpSession session; 
   
     ArrayList list = new ArrayList();
    dbConnect conn=new dbConnect();
     String filter="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session= request.getSession(true);
        
        try {
            if(request.getParameter("FY")!=null && request.getParameter("FY")!=""){
              financial=request.getParameter("FY");
           
            } 
             if(request.getParameter("QTR")!=null && request.getParameter("QTR")!=""){
              quarter=request.getParameter("QTR");
            } 
//              if(request.getParameter("DIST")!=null && request.getParameter("DIST")!=""){
//              district=request.getParameter("DIST");
//             
//              }
           System.out.println(" financial:"+ financial); 
           System.out.println(" quarter:"+ quarter); 
//           System.out.println(" district:"+ district); 
           current_title="";
           // get the values from the table baseline according to the financial year, district, quarter from the values posted thro ajax
            if(financial!=null && financial!="" && quarter!=null &&
                    quarter!="" ){
                 session.setAttribute("financial", financial);
                 
                   session.setAttribute("quarter", quarter);
           filter="select * from baseline where FinancialYear='"+financial+"' AND Quarter='"+quarter+"'";
                   System.out.println(filter);
                   conn.rs=conn.state.executeQuery(filter);
            
           
           //create an arraylist to hold the beans
           
              if(list!=null && list.size()>0 ){list.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                        // save the values fettched in a bean                  
                       FilterBean DB= new FilterBean();
                       DB.setQTARGETMEN(conn.rs.getString(2));
                       DB.setQTARGETWOMEN(conn.rs.getString(3));
                       DB.setMENBASELINE(conn.rs.getString(4));
                       DB.setWOMENBASELINE(conn.rs.getString(5));
                       DB.setENDTARGETMEN(conn.rs.getString(6));
                       DB.setENDTARGETWOMEN(conn.rs.getString(7));
                       DB.setDISTRICT(conn.rs.getString(10));
                       
//                    String aaa= conn.rs.getString(2);
//                    String b= conn.rs.getString(3);
//                    String c= conn.rs.getString(4);
//                    String d= conn.rs.getString(5);
//                    String e= conn.rs.getString(6);
//                    String f= conn.rs.getString(7);
//                      System.out.println("ASDFG"+aaa);
//                      System.out.println("ASDFG"+b);
//                      System.out.println("ASDFG"+c);
//                      System.out.println("ASDFG"+d);
//                      System.out.println("ASDFG"+e);
//                      System.out.println("ASDFG"+f);
          
                       list.add(DB);
                       
                      System.out.println("a"+DB.getQTARGETMEN());
                      System.out.println("b"+DB.getQTARGETWOMEN());
                      System.out.println(""+DB.getMENBASELINE());
                      System.out.println(DB.getWOMENBASELINE());
                      System.out.println(DB.getENDTARGETMEN());
                      System.out.println(DB.getENDTARGETWOMEN());
                      System.out.println(DB.getDISTRICT());
                       
                                        }      }              
        
                             session.setAttribute("list", list);
//                                 String nextJSP = "Results.jsp";
//                                 
           
           response.sendRedirect("Results.jsp");
//             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//                                        dispatcher.forward(request,response);
//                                        conn.connect.close();
//                                        System.out.println("Disconnected from database");
        } catch (SQLException ex) {
            Logger.getLogger(filterer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          // response.sendRedirect("myajax.html");
        
        
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
