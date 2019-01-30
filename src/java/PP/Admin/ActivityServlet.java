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
public class ActivityServlet extends HttpServlet {

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
   
            String financial,quarter,title,results;
   HttpSession session; 
   String district="";
   String baselinew="";
   String baselinem="";
   String targetw="";
   String targetm="";
   String endtargetm="";
   String endtargetw="";
   String dists="";
   int count=0;
    
   String result="";
   String activity="";
   String activityID="";
   String unit="";
   String IndicatorID="";
   String countyID="";
   String DistrictID="";
   String activityTitle="";
   String startDate="";
   String endDate="";
   String men="";
   String women="";
   String subtotal="";
   String activities="";
   
   
   String baseline="";
   String menAchieved="";
   String womenAchieved="";
   
                                         String dist =  ""; 
                                         String baselineid = "";   
                                         String baselinewomen = "";   
                                         String baselinemen = "";
                                         String targetwomen = "";
                                         String targetmen = "";
                                         String endTargetWomen = "";
                                         String endTargetMen = "";
                                         String priorw = "";
                                         String priorm = "";
                                         String achievedm = "";
                                         String achievedw = "";
                                         String tablerows="";
                                         String tablerows1="";
                                         
  String districts="" ; 
   String rw_id="";
     ArrayList list = new ArrayList();
  
     String filter="";
   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String activityOthers="";
            response.setContentType("text/html;charset=UTF-8");
           count=0;
       session = request.getSession(true);
     tablerows1="";
     
       dbConnect conn=new dbConnect();
            if(request.getParameter("FY")!=null && request.getParameter("FY")!=""){
              financial=request.getParameter("FY");
              
           
            } 
             if(request.getParameter("QTR")!=null && request.getParameter("QTR")!=""){
              quarter=request.getParameter("QTR");
              
            } 
             if(request.getParameter("quarter")!=null && request.getParameter("quarter")!=""){
              quarter=request.getParameter("quarter");
              
            } 
              if(request.getParameter("title")!=null && request.getParameter("title")!=""){
              title = request.getParameter("title");
             
             }
         results="";
         int counter=0;
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
      while(conn.rs3.next()){
      AllDistricts.add(conn.rs3.getString("DistrictName"));
      AllDistrictsID.add(conn.rs3.getString("districtID"));
      AllCountyID.add(conn.rs3.getString("countyID"));
      
      }
          if(financial!=null && financial!="" && quarter!=null &&
                    quarter!="" && title != null && title!="" ){
                 session.setAttribute("financial", financial);
                
                   session.setAttribute("quarter", quarter);
                   session.setAttribute("title", title);
         

         activities="select * from indicatoractivities where FinancialYear='"+financial+"' AND Quarter='"+quarter+"' and titleID='"+title+"'";
                 
                   conn.rs=conn.state.executeQuery(activities);
                    while(conn.rs.next())
                                        {
                                    counter++;
                                                 activity =  conn.rs.getString(1); 
                                                 unit = conn.rs.getString("unit");  
                                                 IndicatorID = conn.rs.getString(2);          
                                                 countyID = conn.rs.getString(4);          
                                                 DistrictID = conn.rs.getString(5);    
                                                   activityTitle = conn.rs.getString(6);     
                                                    if(activityTitle.equals("1031")){
                                                activityOthers = conn.rs.getString(7); 
                                                    }
                                                    
                                                       
                                                 startDate = conn.rs.getString(8);          
                                                 endDate = conn.rs.getString(9);          
                                                 men = conn.rs.getString(10);          
                                                 women = conn.rs.getString(11);          
                                                 subtotal= conn.rs.getString(12);          
//                                            rw_id=
                                            
                                                 
                                                   ArrayList countysID = new ArrayList();
                                        ArrayList countys = new ArrayList();
                                        
                                        String queries="select * from county";
                                            
                                              conn.rs2=conn.state2.executeQuery(queries);
                                              while(conn.rs2.next()){
                                                  countysID.add(conn.rs2.getString("countyID"));
                                                  countys.add(conn.rs2.getString("countyName"));
                                              }
                                                 
                                                 
                                         int cnt1=1;
                                       
                                        for(int b=0;b<10;b++){
                                          int count= counter;
                                        if(cnt1==1){
                                            
                                                 tablerows1+="<tr><td style=\"width: 150px;\"><input type=\"text\" style=\"width: 150px;\" name=\"unit_"+count+"\" id=\"unit_"+count+"\" value="+unit+"></td>";
                                           
                                              
                                        }
                                      
                                        
                                        if(cnt1==2){
//                                             String queries="select * from county where countyID='"+countyID+"'";
                                            
                                              String countyOption="";
                                              for(int d=0;d<countysID.size();d++){
                                              if(countysID.get(d).equals(countyID)){
                                                  countyOption+="<option selected value=\""+countysID.get(d) +"\">"+countys.get(d) +"</option>";
                                                
                                              }
                                              else{
                                                   countyOption+="<option value=\""+countysID.get(d) +"\">"+countys.get(d) +"</option>";
                                                // tablerows1+="<td  style=\"width: 120px;\"><select name=\"county_"+count+"\" style=\"width: 120px;\" id=\"county_"+count+"\"><option value=\""+countysID.get(d) +"\">"+countys.get(d) +"</option></select></td>";
                                              }
                                              }
                                               tablerows1+="<td  style=\"width: 120px;\"><select name=\"county_"+count+"\" onchange=\"filter_district("+count+");\" style=\"width: 120px;\" id=\"county_"+count+"\">"+countyOption+"</select></td>";
                                        }
                                        else if(cnt1==3){
                                             tablerows1+="<input type=\"hidden\" name=\"id"+count+"\" value=\""+activity+"\">";
                                               String DistOptions="";
                                              String splits[]= DistrictID.split("_");
                                               for(int i=0;i<splits.length;i++){
                                                if(!splits[i].equals("")){
                                                    
                                                     for(int a=0;a<AllDistricts.size();a++){
                                                     if(AllDistrictsID.get(a).equals(splits[i]) && AllCountyID.get(a).equals(countyID)){
//                                                        
                                                       DistOptions+="<option selected value=\""+AllDistrictsID.get(a) +"\">"+AllDistricts.get(a) +"</option>";
                                                     }
                                                     else if(!AllDistrictsID.get(a).equals(splits[i]) && AllCountyID.get(a).equals(countyID)){
//                                                      DistOptions+="<option value=\"\"></option>"; 
                                                      DistOptions+="<option value=\""+AllDistrictsID.get(a) +"\">"+AllDistricts.get(a) +"</option>"; 
                                                     }
                                                     }
                                                     
////                                             String queries="select DistrictName from districts where districtID='"+splits[i]+"'";
////                                              System.out.println(queries);
////                                              conn.rs1=conn.state1.executeQuery(queries);
////                                              while(conn.rs1.next()){
////                                              String dist1= conn.rs1.getString("DistrictName");
//////                                              String splits[]= dist1.split("_");
//                                                  
//                                            
                                          
                                              }
                                        } 
                                         tablerows1+="<td style=\"width: 120px;\"><select   style=\"width: 120px;\"  multiple name=\"district_"+count+"\" id=\"district_"+count+"\">"+DistOptions+"</select></td>";
                                        }
                                        ///start
                                        else if(cnt1==4){
                                            ArrayList activityList = new ArrayList();
                                            ArrayList activityListID = new ArrayList(); 
                                            if(activityTitle.equals("1031")){
                                            String activitySelect = "select * from indicatoractivity where ActivityID='"+activityTitle+"'   ";
//                                              System.out.println("check this_______________"+activitySelect);
                                            conn.rs4 = conn.state4.executeQuery(activitySelect);
                                          
                                            while(conn.rs4.next()){
                                                String query1= "Select * from indicatoractivity where IndicatorID='"+conn.rs4.getString("IndicatorID")+"' OR IndicatorID='"+title+"' ";
//                                     System.out.println("query1_______________"+query1);
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
                                            else{
                                            
                                            String activitySelect = "select * from indicatoractivity where ActivityID='"+activityTitle+"' ";
//                                              System.out.println("check this_______________"+activitySelect);
                                            conn.rs4 = conn.state2.executeQuery(activitySelect);
                                          
                                            while(conn.rs4.next()){
                                                String query1= "Select * from indicatoractivity where IndicatorID='"+conn.rs4.getString("IndicatorID")+"' OR ActivityID='1031' ";
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
                                        else if(cnt1==5){
                                            
                                               tablerows1+="<td  style=\"width: 120px;\"><input type=\"text\"  style=\"width: 120px;\" name=\"startdate_"+count+"\" id=\"startdate_"+count+"\"  value=\""+startDate+"\" /></td>";
                                        }
                                      else if(cnt1==6){
                                               tablerows1+="<td  style=\"width: 120px;\"><input style=\"width: 120px;\" type=\"text\"  name=\"enddate_"+count+"\" id=\"enddate_"+count+"\"  value=\""+endDate+"\" /></td>";
                                               }
                                  
                                        else if(cnt1==7){
                                          //tablerows1+="<td  style=\"width: 60px;\"><input style=\"width: 60px;\" type=\"text\" name=\"women_"+count+"\" id=\"women_"+count+"\"  value=\""+women+"\" /></td>";
                                        }
                                        else if(cnt1==8){
                                         //tablerows1+="<td  style=\"width: 60px;\"><input style=\"width: 60px;\" type=\"text\" name=\"men_"+count+"\" id=\"men_"+count+"\"  value=\""+men+"\"  onKeyUp=\"totals("+count+")\"/></td>";
                                        }
                                         else if(cnt1==9){
                                             tablerows1+="<td  style=\"width: 60px;\"><input style=\"width: 60px;\" type=\"text\" name=\"subtotal_"+count+"\" id=\"subtotal_"+count+"\" value=\""+subtotal+"\" /></td></tr>";
                                         }
//                                         
                                         else if(cnt1==10){
                                          tablerows+="<input type=\"hidden\" name=\"activityID_"+count+"\" id=\"activityID_"+count+"\"  value=\""+baselineid+"\" />";
                                           
                                              }
                                       
                                        cnt1++;
                                       
                                       // }//second for loop end
                                        }// first for loop end
                                        }//while end
                    
                    
          tablerows1+="<input type=\"hidden\" name=\"count\" id=\"count\" value=\""+count+"\">";
          tablerows1+="<input type=\"hidden\" name=\"counter\" id=\"counter\" value=\""+counter+"\">";
          tablerows1+="<input type=\"hidden\" name=\"old_rows\" id=\"old_rows\" value=\""+counter+"\">";
         
      
//    
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
          
             out.println("<h4>"+tablerows1+"</h4>");
            
            out.println("</body>");
            out.println("</html>");
            
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
          
//              conn.state.close();
//    if (conn.rs != null) {
//        try {
//           conn.rs.close();
//        } catch (SQLException e) { /* ignored */}
//    }
//    if (conn.ps != null) {
//        try {
//            conn.ps.close();
//        } catch (SQLException e) { /* ignored */}
//    }
//    if ( conn.connect != null) {
//        try {
//            conn.connect.close();
//        } catch (SQLException e) { /* ignored */}
//    }
  
             
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
            Logger.getLogger(PP.Admin.ResultsDisplay.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.toString());
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
            Logger.getLogger(PP.Admin.ResultsDisplay.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
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
  

    
