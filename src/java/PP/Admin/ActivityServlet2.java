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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class ActivityServlet2 extends HttpServlet {

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
    String financial, quarter, title, results;
    HttpSession session;
    String district = "";
    String baseline = "";
    String baselineid = "";
    String achieved = "";
    String target = "";
    String endTarget = "";
    String dists = "";
//   int count=0;
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
    String total = "";
    String activities = "";
    int count = 0;
    String prior = "";
    String menAchieved = "";
    String womenAchieved = "";
    String activityOthers = "";
//   
//                                         String dist =  ""; 
//                                         String baselineid = "";   
//                                         String baseline = "";   
//                                       
//                                         String target = "";
//                                        
//                                         String endTargetWomen = "";
//                                         String endTargetMen = "";
//                                         String priorw = "";
//                                         String priorm = "";
//                                         String achievedm = "";
//                                         String achievedw = "";
    String tablerows = "";
    String tablerows1 = "";
    String districts = "";
    ArrayList list = new ArrayList();
 
    String filter = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

   dbConnect conn = new dbConnect();
        response.setContentType("text/html;charset=UTF-8");

        session = request.getSession(true);
        tablerows1 = "";
        count = 0;
     
     
        if (request.getParameter("FY") != null && request.getParameter("FY") != "") {
            financial = request.getParameter("FY");


        }
        if (request.getParameter("QTR") != null && request.getParameter("QTR") != "") {
            quarter = request.getParameter("QTR");

        }
        if (request.getParameter("title") != null && request.getParameter("title") != "") {
            title = request.getParameter("title");

        }
        ArrayList AllDistricts = new ArrayList();
        ArrayList AllDistrictsID = new ArrayList();
        ArrayList AllCountyID = new ArrayList();
        if (AllDistricts != null && !AllDistricts.isEmpty()) {
            AllDistricts.clear();
        }
        if (AllDistrictsID != null && !AllDistrictsID.isEmpty()) {
            AllDistrictsID.clear();
        }
        if (AllCountyID != null && !AllCountyID.isEmpty()) {
            AllCountyID.clear();
        }
        String allDists = " select * from districts";
        conn.rs3 = conn.state3.executeQuery(allDists);
        while (conn.rs3.next()) {
            AllDistricts.add(conn.rs3.getString("DistrictName"));
            AllDistrictsID.add(conn.rs3.getString("districtID"));
            AllCountyID.add(conn.rs3.getString("countyID"));

        }
        results = "";
        int counter = 0;
     tablerows1+=" <tr><th>Unit</th>\n" +
"\n" +
"                                      <th>County</th>\n" +
"                                      <th>Geographic Location</th>\n" +
"                                      <th>Activity Title</th>\n" +
"                                      <th>Start Date</th>\n" +
"                                      <th>End Date</th>\n" +
//"                                      <th>Women</th>\n" +
//"                                      <th>Men</th>\n" +
"                                      <th>Sub Total</th>\n" +
"                                  </tr>";

        if (financial != null && !financial.equals("") && quarter != null
                && !quarter.equals("") && title != null && !title.equals("")) {
            session.setAttribute("financial", financial);

            session.setAttribute("quarter", quarter);
            session.setAttribute("title", title);

            activities = "select * from indicatoractivities1 where FinancialYear='" + financial + "' AND Quarter='" + quarter + "' and titleID='" + title + "'";

            conn.rs = conn.state.executeQuery(activities);
            while (conn.rs.next()) {
                counter++;
                activity = conn.rs.getString(1);
                unit = conn.rs.getString("unit");
                IndicatorID = conn.rs.getString(2);
                countyID = conn.rs.getString(4);
                DistrictID = conn.rs.getString(5);
                activityTitle = conn.rs.getString(6);
                
                if(activityTitle.equals("1031")){
                activityOthers = conn.rs.getString(7);}
               
                startDate = conn.rs.getString(8);
                endDate = conn.rs.getString(9);
                total = conn.rs.getString(10);

System.out.println("!!!!!!"+activity+" "+IndicatorID+" "+activityTitle);

                int cnt1 = 1;


                ArrayList countysID = new ArrayList();
                ArrayList countys = new ArrayList();

                String queries = "select * from county";

                conn.rs2 = conn.state2.executeQuery(queries);
                while (conn.rs2.next()) {
                    countysID.add(conn.rs2.getString("countyID"));
                    countys.add(conn.rs2.getString("countyName"));
                }

                for (int b = 0; b < 9; b++) {
                    count = counter;
                    if (cnt1 == 1) {

                        tablerows1 += "<tr><td style=\"width: 125px;\"><input type=\"text\" style=\"width:125px;\" name=\"unit_" + count + "\" id=\"unit_" + count + "\" value=" + unit + "></td>";


                    }


                    if (cnt1 == 2) {
//                                             String queries="select * from county where countyID='"+countyID+"'";

                        String countyOption = "";
                        for (int d = 0; d < countysID.size(); d++) {
                            if (countysID.get(d).equals(countyID)) {
                                countyOption += "<option selected value=\"" + countysID.get(d) + "\">" + countys.get(d) + "</option>";

                            } else {
                                countyOption += "<option value=\"" + countysID.get(d) + "\">" + countys.get(d) + "</option>";
                                // tablerows1+="<td  style=\"width: 120px;\"><select name=\"county_"+count+"\" style=\"width: 120px;\" id=\"county_"+count+"\"><option value=\""+countysID.get(d) +"\">"+countys.get(d) +"</option></select></td>";
                            }
                        }
//                                              if(countysID.size()>0){ countysID.clear();
//                                                countys.clear();
//                                              }
                        tablerows1 += "<td  style=\"width: 120px;\"><select name=\"county_" + count + "\" style=\"width: 120px;\" onchange=\"filter_district(" + count + ");\" id=\"county_" + count + "\">" + countyOption + "</select></td>";
                    } else if (cnt1 == 3) {
                        tablerows1 += "<input type=\"hidden\" name=\"id" + count + "\" value=\"" + activity + "\">";
                        String DistOptions = "";
                        String splits[] = DistrictID.split("_");
                        for (int i = 0; i < splits.length; i++) {
                            if (!splits[i].equals("")) {

                                for (int a = 0; a < AllDistricts.size(); a++) {
                                    if (AllDistrictsID.get(a).equals(splits[i]) && AllCountyID.get(a).equals(countyID)) {

                                        DistOptions += "<option selected value=\"" + AllDistrictsID.get(a) + "\">" + AllDistricts.get(a) + "</option>";
                                    } else if (!AllDistrictsID.get(a).equals(splits[i]) && AllCountyID.get(a).equals(countyID)) {
                                        DistOptions += "<option value=\"" + AllDistrictsID.get(a) + "\">" + AllDistricts.get(a) + "</option>";
                                    }
                                }

                            }
                        }
                        tablerows1 += "<td style=\"width: 120px;\"><select   style=\"width: 120px;\"  multiple name=\"district_" + count + "\" id=\"district_" + count + "\">" + DistOptions + "</select></td>";
                    } //strart
                 
                                        else if(cnt1==4){
                                            ArrayList activityList = new ArrayList();
                                            ArrayList activityListID = new ArrayList(); 
                                            if(activityTitle.equals("1031")){
                                            String activitySelect = "select * from indicatoractivity where ActivityID='"+activityTitle+"'   ";
//                                              System.out.println("check this_______________"+activitySelect);
                                            conn.rs4 = conn.state2.executeQuery(activitySelect);
                                          
                                            while(conn.rs4.next()){
                                                String query1= "Select * from indicatoractivity where IndicatorID='"+conn.rs4.getString("IndicatorID")+"' OR IndicatorID='"+title+"'  ";
//                                     System.out.println("query1_______________"+query1);
                                                conn.rs5 = conn.state5.executeQuery(query1);
                                                while(conn.rs5.next()){
                                                    
                                                    // System.out.println("n this_______________"+conn.rs5.getString("ActivityID"));
                                            activityListID.add(conn.rs5.getString("ActivityID"));
                                            activityList.add(conn.rs5.getString("Activity"));
                                            }}
                                            String activityOption="";
                                            for(int y=0;y<activityList.size();y++){
                                                if(activityListID.get(y).equals(activityTitle)){
                                          activityOption+="<option selected value=\""+activityListID.get(y) +"\">"+activityList.get(y) +"</option>";
                                                }
                                                else{
                                         activityOption+=" <option value=\""+activityListID.get(y) +"\">"+activityList.get(y) +"</option>";
                                                          }
                                         
                                        }
                                            tablerows1+="<td style=\"width: 120px;\"><select style=\"width: 120px;\" name=\"activity_"+count+"\" id=\"activity_"+count+"\" onchange='changer("+count+")'>"+activityOption+"</select>";
                                        
                                        if(activityOthers!=null && !activityOthers.equals("")){
                                              tablerows1+="<textarea  name=\"activityOthers_"+count+"\" id=\"activityOthers"+count+"\"  style=\"width: 117px; height: 72px;\">"+ activityOthers.toString() +"</textarea></td>";
                                                      } 
                                        else{
                                         tablerows1+="<input type=\"hidden\" value=\"\" name=\"activityOthers_"+count+"\" id=\"activityOthers"+count+"\"></td>";
                                        }
                                            }
                                            else{
                                            
                                            String activitySelect = "select * from indicatoractivity where ActivityID='"+activityTitle+"' ";
//                                              System.out.println("check this_______________"+activitySelect);
                                            conn.rs4 = conn.state2.executeQuery(activitySelect);
                                          
                                            while(conn.rs4.next()){
                                                String query1= "Select * from indicatoractivity where IndicatorID='"+conn.rs4.getString("IndicatorID")+"' OR ActivityID='1031'  ";
                                    // System.out.println("query1_______________"+query1);
                                                conn.rs5 = conn.state5.executeQuery(query1);
                                                while(conn.rs5.next()){
                                                    
                                                     //System.out.println("n this_______________"+conn.rs5.getString("ActivityID"));
                                            activityListID.add(conn.rs5.getString("ActivityID"));
                                            activityList.add(conn.rs5.getString("Activity"));
                                            }}
                                            String activityOption="";
                                            for(int y=0;y<activityList.size();y++){
                                                if(activityListID.get(y).equals(activityTitle)){
                                          activityOption+="<option selected value=\""+activityListID.get(y) +"\">"+activityList.get(y) +"</option>";
                                                }
                                                else{
                                         activityOption+=" <option value=\""+activityListID.get(y) +"\">"+activityList.get(y) +"</option>";
                                                          }
                                         
                                        }
                                            tablerows1+="<td style=\"width: 120px;\"><select style=\"width: 120px;\" name=\"activity_"+count+"\" id=\"activity_"+count+"\" onchange='changer("+count+")'>"+activityOption+"</select>";
                                        
                                        if(activityOthers!=null && !activityOthers.equals("")){
                                              tablerows1+="<textarea  name=\"activityOthers_"+count+"\" id=\"activityOthers"+count+"\"  style=\"width: 117px; height: 72px;\">"+ activityOthers.toString() +"</textarea></td>";
                                                      } 
                                        else{
                                         tablerows1+="<input type=\"hidden\" value=\"\" name=\"activityOthers_"+count+"\" id=\"activityOthers"+count+"\"></td>";
                                        }
                                            
                                            }
                                        }
                                        
                    
                    //end
                    
                    else if (cnt1 == 5) {

                        tablerows1 += "<td style=\"width: 100px;\"><input type=\"text\" style=\"width: 100px;\" name=\"startdate_" + count + "\" id=\"startdate_" + count + "\"  value=\"" + startDate + "\" /></td>";
                    } else if (cnt1 == 6) {
                        tablerows1 += "<td  style=\"width: 100px;\"><input type=\"text\" style=\"width: 100px;\" name=\"enddate_" + count + "\" id=\"enddate_" + count + "\"  value=\"" + endDate + "\" /></td>";
                    } else if (cnt1 == 7) {
                        tablerows1 += "<td style=\"width: 100px;\"><input type=\"text\" name=\"total_" + count + "\"  style=\"width: 100px;\" id=\"total_" + count + "\"  value=\"" + total + "\" /></td></tr>";
                    }


//                                        
                    cnt1++;

                    // }//second for loop end
                }// first for loop end
            }//while end




            //display this whe counter is graeter than 0

            tablerows1 += "<input type=\"hidden\" name=\"count\" id=\"count\" value=\"" + count + "\">";
            tablerows1 += "<input type=\"hidden\" name=\"oldrows1\" id=\"oldrows1\" value=\"" + count + "\">";


            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");


            out.println("<h4>" + tablerows1 + "</h4>");



            out.println("</body>");
            out.println("</html>");
//            
////            out.println("<html>");
////            out.println("<head>");           
////            out.println("</head>");
////            out.println("<body>");
////            out.println("<table>");
////            out.println("<tr>");
////           
////           
////            out.println("</tr>");
////             out.println("<table>");
////            out.println("</body>");
////            out.println("</html>");
////             
//           
//                                         }
//           }
//    }
//           


            // response.sendRedirect("myajax.html");


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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ActivityServlet2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActivityServlet2.class.getName()).log(Level.SEVERE, null, ex);
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
