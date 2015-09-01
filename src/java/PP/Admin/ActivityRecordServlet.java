/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maureen
 */
public class ActivityRecordServlet extends HttpServlet {

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
     

                       
                        dbConnect conn = new dbConnect();

			
			try {
				
				
				ArrayList al=null;
				ArrayList userList =new ArrayList();
				String query = "select * from indicatoractivities order by activityID";
				
				System.out.println("query " + query);
				conn.state = conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(query);
				


				while(conn.rs.next())
				{
					al  = new ArrayList();
				
				  al.add(conn.rs.getInt(1));
                                  al.add(conn.rs.getString(2));
				  al.add(conn.rs.getString(3));
				  al.add(conn.rs.getString(4));
                                 
                                  
				  al.add(conn.rs.getString(5));
				  al.add(conn.rs.getString(6));
				
                                  
				  al.add(conn.rs.getString(7));
				  al.add(conn.rs.getString(8));
				  al.add(conn.rs.getString(9));
				  al.add(conn.rs.getString(10));
				  al.add(conn.rs.getString(11));
				 
				 System.out.println("al :: "+al);
				  userList.add(al);
				}

				request.setAttribute("userList",userList);
				   conn.connect.close();
                                String nextJSP = "/ViewActivity.jsp";
				RequestDispatcher dispatcher;
                                           dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				conn.connect.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
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
