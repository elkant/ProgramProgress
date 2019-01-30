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
public class ActivityServlets extends HttpServlet {

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
    String financial = "", quarter="", title="", results="";
    HttpSession session;
    String district = "";
    String baseline = "";
    String baselineid = "";
    String achieved = "";
    String target = "";
    String endTarget = "";
    String dists = "";
    int count = 0;
    String result = "";
    String activity = "";
    String activityID = "";
    String unit = "";
    String IndicatorID = "";
    String countyID = "";
    String DistrictID = "";
    String activityTitle = "";
    String startDate = "";
    String endDate = "";
    int test_count = 0;
    String total = "";
    String activities = "";
    String prior = "";
    String totalAchieved = "";
    String tablerows = "";
    String priorsTotal = "";
    String Q1 = "Q1";
    String Q2 = "Q2";
    String Q3 = "Q3";
    String Q4 = "Q4";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        tablerows = "";
       // financial = "";
session=request.getSession();
        int pepfar_year = 1;


        dbConnect conn = new dbConnect();
        if (request.getParameter("FY") != null && !request.getParameter("FY").equals("")) {
            financial = request.getParameter("FY").toString();

            System.out.println("Financial year 1: " + financial + "Pepfer " + pepfar_year);

        }
        
        
        //System.out.println("Financial year:2 " + financial + "Pepfer " + pepfar_year);
        if (!financial.equals("")) {
            pepfar_year = Integer.parseInt(financial);
        }
        System.out.println("Financial year 3: " + financial + "Pepfer " + pepfar_year);
        if (request.getParameter("QTR") != null && !"".equals(request.getParameter("QTR"))) {
            quarter = request.getParameter("QTR");
        }
           System.out.println("Quater is:" + quarter + "PEPFAR 11" + pepfar_year);

            if (quarter.equals("Q1")) {
                pepfar_year = pepfar_year - 1;
            }

           // System.out.println("Pepfer 2 is :" + pepfar_year);

        
        if (request.getParameter("title") != null && !"".equals(request.getParameter("title"))) {
            title = request.getParameter("title");

        }
        String baselines = "";
        district = "";

 results="";
          String priorq="";
         String currentq="";
         if(quarter.equals("Q1")){
         priorq="July-Sep";
         currentq="Oct-Dec";
         pepfar_year=pepfar_year;
         }
         if(quarter.equals("Q2")){
         priorq="Oct-Dec";
         currentq="Jan-March";
         }
         if(quarter.equals("Q3")){
         priorq="Jan-March";
         currentq="April-June";
         }
         if(quarter.equals("Q4")){
         priorq="July-Sept";
         currentq="Oct-Dec";
         }

        tablerows = "   <tr> "
                + "<!--                                      <th style=\"width:10px;\">NO.</th>-->"
                + "                                      <th colspan=\"2\">ADDITIONAL CRITERIA</th>"
                +" <th  colspan=\"1\">THIS REPORTING PERIOD <label>"+currentq+" "+pepfar_year+"</label></th></tr> "
                + " <tr>     \n"
                + "                                      <th colspan=\"2\" style=\"width:30px; text-align: left;\"  ></th>\n"
                + "<!--                                      <th style=\"width:10px;\"></th>-->\n"
                + "                                      <th>Achieved</th>\n"
                + "                                     \n"
                + "                                      \n"
                + "                                      \n"
                + "                                  </tr> ";





        if (financial != null && !"".equals(financial) && quarter != null && !"".equals(quarter) && title != null && !"".equals(title)) {
            String resultID = "";
            //results="select * from indicatorresults1 where financialYear='"+financial+"' AND reportingPeriod='"+quarter+"' and titleID='"+title+"'";
            results = "select * from indicatorachievedcombined where financialYear='" + financial + "' AND reportingPeriod='" + quarter + "' and titleID='" + title + "'";
            conn.rs3 = conn.state3.executeQuery(results);







            while (conn.rs3.next()) {
 test_count++;
//              baselines = conn.rs1.getString("baselineID");          
                totalAchieved = conn.rs3.getString("totalAchieved");
                resultID = conn.rs3.getString("resultID");
                district = conn.rs3.getString("district");
                String dists = "";
                String queryDist = "select * from districts where DistrictName='" + district + "'";
                conn.rs4 = conn.state4.executeQuery(queryDist);
                System.out.println("queryDist" + queryDist);
                while (conn.rs4.next()) {
                    dists = conn.rs4.getString("districtID");
                }
           
//              activityID=conn.rs1.getString("activityID"); //create an arraylist to hold the beans


                String Q1 = "Q1";
                String Q2 = "Q2";
                String Q3 = "Q3";
                String Q4 = "Q4";
                String dist = "";















                results = "select * from baselinetotal where financialYear='" + financial + "' AND quarter='" + quarter + "' and titleID='" + title + "' AND districtID='" + dists + "'";

                conn.rs2 = conn.state.executeQuery(results);



                while (conn.rs2.next()) {

                   

                    dist = conn.rs2.getString("districtID");


                    baselineid = conn.rs2.getString(3);
                    baseline = conn.rs2.getString("baselineTotal");




                    prior = "";




                    target = conn.rs2.getString("QtargetTotal");


//                                           
//                                                 targetmen = conn.rs2.getString("QtargetMen");
//                                               


                    endTarget = conn.rs2.getString("endTargetTotal");



//                                                   endTargetMen = conn.rs2.getString("endTargetMen");
//                                               
//                                               result="select * from indicatorresults1 where activityID='"+activityID+"'";
//                 

//                                                  achieved=totalAchieved;
                }//while end

                int cnt = 1;

                for (int a = 0; a < 12; a++) {

                    if (cnt == 1) {
//                                             String queries="select DistrictName from districts where districtID='"+dist+"'";
//                                            
//                                              conn.rs1=conn.state1.executeQuery(queries);
//                                              while(conn.rs1.next()){
                        tablerows += "<tr><td></td><td width=\"60px\"><input type=\"text\" name=\"districts_" + test_count + "\" id=\"districts_" + test_count + "\"  value=\"" + district + "\" /></td>";

//                                              }
                    } else if (cnt == 2) {
                        //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baseline_"+test_count+"\" id=\"baseline_"+test_count+"\"  value=\""+baseline+"\" /></td>";
                    } else if (cnt == 3) {
                        //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"prior_"+test_count+"\" id=\"prior_"+test_count+"\" value=\"\" /></td>";

                        if (quarter.equals(Q2)) {
                            String priors = "select * from indicatorachievedcombined where reportingPeriod='" + Q1 + "' AND financialYear='" + financial + "' AND district='" + district + "' AND titleID='" + title + "'";
                            System.out.println("priors1   " + priors);
                            try {
                                conn.rs2 = conn.state2.executeQuery(priors);
                                if (conn.rs2.next()) {
                                    System.out.println("ERRRORNFJNFJVN ");
                                    priorsTotal = conn.rs2.getString("totalAchieved");
                                }
                                //System.out.println("priorsTotal    3"+priorsTotal);
                                if (priorsTotal != null && !priorsTotal.equals("")) {
                                    //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorsTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                } else {
                                    // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                }
                            } catch (SQLException g) {
                                System.out.println(g.toString());

                            }
                        } else if (quarter.equals(Q3)) {
                            String priors = "select * from indicatorachievedcombined where reportingPeriod='" + Q2 + "' AND financialYear='" + financial + "' AND district='" + district + "' AND titleID='" + title + "'";
                            System.out.println("priors2    " + priors);
                            try {
                                conn.rs2 = conn.state2.executeQuery(priors);
                                if (conn.rs2.next()) {
                                    System.out.println("ERRRORNFJNFJVN ");
                                    priorsTotal = conn.rs2.getString("totalAchieved");
                                }
                                //System.out.println("priorsTotal    3"+priorsTotal);
                                if (priorsTotal != null && !priorsTotal.equals("")) {
                                    //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                } else {
                                    //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                }
                            } catch (SQLException j) {
                                System.out.println(j.toString());
                            }

                        } else if (quarter.equals(Q1)) {
                            String priors = "select * from indicatorachievedcombined where reportingPeriod='" + Q4 + "' AND financialYear='" + financial + "' AND district='" + district + "' AND titleID='" + title + "'";
                            System.out.println("priors3   " + priors);
                            try {
                                conn.rs2 = conn.state2.executeQuery(priors);

                                if (conn.rs2.next()) {
                                    System.out.println("ERRRORNFJNFJVN ");
                                    priorsTotal = conn.rs2.getString("totalAchieved");
                                }
                                //System.out.println("priorsTotal    3"+priorsTotal);
                                if (priorsTotal != null && !"".equals(priorsTotal)) {
                                    //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                } else {
                                    // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                }
                            } catch (SQLException j) {
                                System.out.println(j.toString());

                            }



                        } else if (quarter.equals(Q4)) {
                            String priors = "select * from indicatorachievedcombined where reportingPeriod='" + Q3 + "' AND financialYear='" + financial + "' AND district='" + district + "' AND titleID='" + title + "'";
                            System.out.println("priors3   " + priors);
                            try {
                                conn.rs2 = conn.state2.executeQuery(priors);

                                if (conn.rs2.next()) {
                                    System.out.println("ERRRORNFJNFJVN ");
                                    priorsTotal = conn.rs2.getString("totalAchieved");
                                }
                                //System.out.println("priorsTotal    3"+priorsTotal);
                                if (priorsTotal != null && !"".equals(priorsTotal)) {
                                    // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                } else {
                                    // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                }
                            } catch (SQLException j) {
                                System.out.println(j.toString());

                            }



                        }

                    } else if (cnt == 4) {
                        //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"QtargetTotal_"+test_count+"\" id=\"QtargetTotal_"+test_count+"\"  value=\""+target+"\" /></td>";
                    } else if (cnt == 5) {
                        tablerows += "<td  width=\"60px\"><input type=\"text\" name=\"achievedTotal" + test_count + "\" id=\"achievedTotal" + test_count + "\"  value=\"" + totalAchieved + "\" /></td>";
                    } else if (cnt == 6) {
                        //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"endTarget_"+test_count+"\" id=\"endTarget_"+test_count+"\"  value=\""+endTarget+"\" /></td>";
                    } else if (cnt == 7) {
                        tablerows += "<input type=\"hidden\" name=\"baselineid_" + test_count + "\" id=\"baselineid_" + test_count + "\"  value=\"" + baselineid + "\" /></td> </tr>";
                        tablerows += "<input type=\"hidden\" name=\"activityID_" + test_count + "\" id=\"activityID_" + test_count + "\"  value=\"" + activityID + "\" />";
                        tablerows += "<input type=\"hidden\" name=\"resultID_" + test_count + "\" id=\"resultID_" + test_count + "\"  value=\"" + resultID + "\" />";

                    }

                    cnt++;

                    // }//second for loop end
                }// first for loop end
                baseline = "";


            }// END OF OUTER WHILE LOOP
//      
//            out.println("<html>");
//            out.println("<head>");           
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<table>");
//            out.println("<tr>");
//           
//           
//            out.println("</tr>");
//             out.println("<table>");
//            out.println("</body>");
//            out.println("</html>");
//             

        }

                                   session.setAttribute("test_count", test_count);
            tablerows += "<input type=\"hidden\" name=\"test_count\" id=\"test_count\"  value=\"" + test_count + "\" />";


 if(conn.rs!=null){conn.rs.close();}
              if(conn.rs1!=null){conn.rs1.close();}
              if(conn.rs2!=null){conn.rs2.close();}
              if(conn.rs3!=null){conn.rs3.close();}
              if(conn.rs4!=null){conn.rs4.close();}
              if(conn.rs5!=null){conn.rs5.close();}
              if(conn.rs6!=null){conn.rs6.close();}
              
         if(conn.state!=null){conn.state.close();}
         if(conn.state1!=null){conn.state1.close();}
         if(conn.state2!=null){conn.state2.close();}
         if(conn.state3!=null){conn.state3.close();}
         if(conn.state4!=null){conn.state4.close();}
         if(conn.state5!=null){conn.state5.close();}
         if(conn.state6!=null){conn.state6.close();}
         if(conn.pst!=null){conn.pst.close();}
         if(conn.connect!=null){conn.connect.close();}

        System.out.println("TABLE ROWS 1" + tablerows);
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h4>" + tablerows + "</h4>");

        out.println("</body>");
        out.println("</html>");

test_count=0;
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
            Logger.getLogger(ActivityServlets.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActivityServlets.class.getName()).log(Level.SEVERE, null, ex);
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
