/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class LoginServlet extends HttpServlet {

    String pass;
    MessageDigest m;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("Username");
        String userPass = request.getParameter("Password");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String formattedDate = formatter.format(date);
        String computername = InetAddress.getLocalHost().getHostName();
        //encrypt password

//                m = MessageDigest.getInstance("MD5");
//                m.update(pass.getBytes(), 0, pass.length());
//                String pw = new BigInteger(1, m.digest()).toString(16);    
        dbConnect conn = new dbConnect();

        try {
            String queryString = "SELECT * FROM users WHERE Username=? AND Password=?";

            conn.ps = conn.connect.prepareStatement(queryString);
            conn.ps.setString(1, userName);
            conn.ps.setString(2, userPass);
            conn.rs = conn.ps.executeQuery();
            HttpSession session = request.getSession(true);
            // verifying user credentials before creating Servlet Context object
            String Location = "";
            Location = "None";
            if (conn.rs.next() == true) {

                System.out.println("nnn   " + conn.rs.getString("Location"));
                if (userName.equalsIgnoreCase(conn.rs.getString("Username"))) {
                    if (conn.rs.getString("AccessLevel").equalsIgnoreCase("5")) {
                        session = request.getSession(true);
                        session.setAttribute("Username", conn.rs.getString("Username"));
                        session.setAttribute("userid", conn.rs.getString("UserID"));
                        session.setAttribute("AccessLevel", conn.rs.getString("AccessLevel"));
                        Location = conn.rs.getString("Location");
                        session.setAttribute("Location", Location);
                        System.out.println("Location   is " + conn.rs.getString("Location"));
                        ServletContext context = getServletContext();
//                                            RequestDispatcher dispatcher = context.getRequestDispatcher("/admin_home.jsp");
                        response.sendRedirect("guest_home.jsp");
                        session.setAttribute("loggedIn", "log");
                        request.removeAttribute("loginError");
//                                            dispatcher.forward(request, response);

                        System.out.println("Acces Level is " + conn.rs.getString("AccessLevel"));

                        if (session.getAttribute("Username") != null) {
                            Date dat = new Date();
                            String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='LOGIN',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                            conn.state2.executeUpdate(inserter);
                        }

                    } else if (conn.rs.getString("AccessLevel").equalsIgnoreCase("1")) {
                        session = request.getSession(true);
                        session.setAttribute("Username", conn.rs.getString("Username"));
                        session.setAttribute("userid", conn.rs.getString("UserID"));
                        session.setAttribute("AccessLevel", conn.rs.getString("AccessLevel"));
                        Location = conn.rs.getString("Location");
                        session.setAttribute("Location", Location);
                        System.out.println("Location   is " + conn.rs.getString("Location"));
                        ServletContext context = getServletContext();
//                                            RequestDispatcher dispatcher = context.getRequestDispatcher("/admin_home.jsp");
                        response.sendRedirect("ResultsMain2.jsp");
                        session.setAttribute("loggedIn", "log");
                        request.removeAttribute("loginError");
//                                            dispatcher.forward(request, response);

                        System.out.println("Acces Level is " + conn.rs.getString("AccessLevel"));

                        if (session.getAttribute("Username") != null) {
                            Date dat = new Date();
                            String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='LOGIN',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                            conn.state2.executeUpdate(inserter);
                        }

                    } else if (conn.rs.getString("AccessLevel").equalsIgnoreCase("3")) {
                        session = request.getSession(true);
                        session.setAttribute("Username", conn.rs.getString("Username"));
                        session.setAttribute("userid", conn.rs.getString("UserID"));
                        session.setAttribute("AccessLevel", conn.rs.getString("AccessLevel"));
                        Location = conn.rs.getString("Location");
                        session.setAttribute("Location", Location);
                        ServletContext context = getServletContext();
//                                            RequestDispatcher dispatcher = context.getRequestDispatcher("/admin_home.jsp");
                        response.sendRedirect("ResultsMain2.jsp");
                        session.setAttribute("loggedIn", "log");
                        request.removeAttribute("loginError");
//                                            dispatcher.forward(request, response);

                        System.out.println("Acces Level is " + conn.rs.getString("AccessLevel"));
                        System.out.println("Location   is " + conn.rs.getString("Location"));

                        if (session.getAttribute("Username") != null) {
                            Date dat = new Date();
                            String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='LOGIN',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                            conn.state2.executeUpdate(inserter);
                        }

                    } else {
                        //if(conn.rs.getString("AccessLevel").equalsIgnoreCase("2") || conn.rs.getString("AccessLevel").equalsIgnoreCase("4")||conn.rs.getString("AccessLevel").equalsIgnoreCase("6")||conn.rs.getString("AccessLevel").equalsIgnoreCase("5")||conn.rs.getString("AccessLevel").equalsIgnoreCase("7")||conn.rs.getString("AccessLevel").equalsIgnoreCase("8")){
                        session = request.getSession(true);
                        session.setAttribute("Username", conn.rs.getString("Username"));
                        session.setAttribute("userid", conn.rs.getString("UserID"));
                        session.setAttribute("AccessLevel", conn.rs.getString("AccessLevel"));
                        Location = conn.rs.getString("Location");
                        session.setAttribute("Location", Location);
                        System.out.println("Location   is " + conn.rs.getString("Location"));
                        ServletContext context = getServletContext();
//                                            RequestDispatcher dispatcher = context.getRequestDispatcher("/home.jsp");
                        session.setAttribute("loggedIn", "log");
                        request.removeAttribute("loginError");
//                                            dispatcher.forward(request, response);
                        response.sendRedirect("ResultsMain2.jsp");

                        System.out.println("Acces Level is " + conn.rs.getString("AccessLevel"));
                        if (session.getAttribute("Username") != null) {
                            Date dat = new Date();
                            String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='LOGIN',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                            conn.state2.executeUpdate(inserter);
                        }

                    }
//                                            
//                                        else{    
//                                            session = request.getSession(true);
//                                            session.setAttribute("Username", conn.rs.getString("Username"));
//                                            session.setAttribute("AccessLevel", conn.rs.getString("AccessLevel"));
//                                            session.setAttribute("userid", "0");
//                                      System.out.println("Acces Level is "+ conn.rs.getString("AccessLevel"));     
//                                            session.setAttribute("loggedIn", "log");
//                                            request.removeAttribute("loginError");
////                                            ServletContext context = getServletContext();
////                                            RequestDispatcher dispatcher = context.getRequestDispatcher("/home.jsp");
////                                            dispatcher.forward(request, response);
//                                             response.sendRedirect("home.jsp");
//                                        }
                   // conn.rs.close();

                }

                if (!(userName.equalsIgnoreCase(conn.rs.getString("Username")))) {
                    request.setAttribute("loginError", "OOps!!! Invalid Username");
                    ServletContext context = getServletContext();
//                                                RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
//                                                dispatcher.forward(request, response);
                    response.sendRedirect("index.jsp");
                    System.out.println("Acces Level is llll" + conn.rs.getString("AccessLevel"));
                }

            }

            if (conn.rs.next() == false) {
                request.setAttribute("loginError", "OOps!!! Invalid Password");
                ServletContext context = getServletContext();
//                                            RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
//                                            dispatcher.forward(request, response);
                response.sendRedirect("index.jsp");
                System.out.println("Acces Level is  invaid pwd uname" + conn.rs.getString("AccessLevel"));
            }
            
              if(conn.rs!=null){conn.rs.close();}
              if(conn.rs1!=null){conn.rs1.close();}
              if(conn.rs2!=null){conn.rs2.close();}
              if(conn.rs3!=null){conn.rs3.close();}
              if(conn.rs4!=null){conn.rs4.close();}
              
         if(conn.state!=null){conn.state.close();}
         if(conn.state1!=null){conn.state1.close();}
         if(conn.state2!=null){conn.state2.close();}
         if(conn.state3!=null){conn.state3.close();}
         if(conn.state4!=null){conn.state4.close();}
         if(conn.state5!=null){conn.state5.close();}
         if(conn.state6!=null){conn.state6.close();}
         
         if(conn.pst!=null){conn.pst.close();}
         if(conn.connect!=null){conn.connect.close();}
            

        } catch (Exception e) {

            out.println(e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
