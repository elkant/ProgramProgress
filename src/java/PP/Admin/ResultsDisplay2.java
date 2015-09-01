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
public class ResultsDisplay2 extends HttpServlet {

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
    String baselinew = "";
    String baselinem = "";
    String targetw = "";
    String targetm = "";
    String endtargetm = "";
    String endtargetw = "";
    String districts = "";
    String dists = "";
    int count = 0;
    String dist = "";
    String baselinetotal = "";
    String targettotal = "";
    String endTargetTotal = "";
    String priorTotal = "";
    String achievedtotal = "";
    String tablerows = "";
    String priorsTotal = "";
    String totalID = "";
    String Q1 = "Q1";
    String Q2 = "Q2";
    String Q3 = "Q3";
    String Q4 = "Q4";
    ArrayList list = new ArrayList();
    dbConnect conn = new dbConnect();
    String filter = "";
    String resultid="";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
        int pepfer_year=0;
        
        
            response.setContentType("text/html;charset=UTF-8");
           
       session = request.getSession(true);
           tablerows="";
            if(request.getParameter("FY")!=null && request.getParameter("FY")!=""){
              financial=request.getParameter("FY");
                System.out.println("financial"+financial);
           pepfer_year=Integer.parseInt(financial);
           
            } 
             if(request.getParameter("QTR")!=null && !"".equals(request.getParameter("QTR"))){
              quarter=request.getParameter("QTR");
               System.out.println("quarter"+quarter);
            } 
              if(request.getParameter("title")!=null && request.getParameter("title")!=""){
              title= request.getParameter("title");
              System.out.println("title"+title);
             }
         results="";
          String priorq="";
         String currentq="";
         if(quarter.equals("Q1")){
         priorq="July-Sep";
         currentq="Oct-Dec";
         pepfer_year=pepfer_year-1;
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
         tablerows+="   <tr> " +
"<!--                                      <th style=\"width:10px;\">NO.</th>-->" +
"                                      <th colspan=\"2\">ADDITIONAL CRITERIA</th>" +

"                                      <th  colspan=\"1\">THIS REPORTING PERIOD <label>"+currentq+" "+pepfer_year+"</label></th>" +

"                                  </tr> "
                 + " <tr>     \n" +
"                                      <th colspan=\"1\" style=\"width:30px; text-align: left;\"  ></th><th>District</th>\n" +
"<!--                                      <th style=\"width:10px;\"></th>-->\n" +
                                    



"                                      <th>Achieved</th>\n" +

"                                     \n" +
"                                      \n" +
"                                      \n" +
"                                  </tr> ";
       if(financial!=null && financial!="" && quarter!=null &&
                    quarter!="" && title != null && title!="" ){
//                 session.setAttribute("financial", financial);
//                 
//                   session.setAttribute("quarter", quarter);
               
               
               
               
            filter="select * from baselinetotal where financialYear='"+financial+"' AND quarter='"+quarter+"' and titleID='"+title+"'";
                   System.out.println(filter);
                
                   conn.rs1=conn.state.executeQuery(filter);
            
           
           //create an arraylist to hold the beans
                   
                     while(conn.rs1.next())
                                        {
                                    dists =  conn.rs1.getString("districtID"); 
                                        count++; 
                                        System.out.println("count2   "+count);
                                        }
                   
        }
         
       
         
            int test_count=0;
           if(financial!=null && financial!="" && quarter!=null &&
                    quarter!="" && title != null && title!="" ){
                 session.setAttribute("financial", financial);
                 
                   session.setAttribute("quarter", quarter);
                   session.setAttribute("titles", title);
    String resultsQuery ="select * from indicatorachievedcombined where reportingPeriod='"+quarter+"' AND financialYear='"+financial+"' and titleID='"+title+"'";
               conn.rs4 = conn.state4.executeQuery(resultsQuery);  
               while(conn.rs4.next()){
                     test_count++;
                   String distr= conn.rs4.getString("district");
                     String query="select * from districts where DistrictName='"+distr+"'";
                  conn.rs5 = conn.state5.executeQuery(query);
//                  System.out.println()
                  if(conn.rs5.next()){
                
           filter="select * from baselinetotal where financialYear='"+financial+"' AND quarter='"+quarter+"' and titleID='"+title+"' AND districtID='"+conn.rs5.getString("districtID") +"'";
                   System.out.println("filter2"+filter);
                   conn.rs=conn.state6.executeQuery(filter);
                 
           
           //create an arraylist to hold the beans
           
       
                                        while(conn.rs.next())
                                        {
                                          
                                              dist =  conn.rs.getString("districtID"); 
                                                
                                                 baselinetotal = conn.rs.getString("baselineTotal");  
                                               totalID=conn.rs.getString("totalID"); 
                                               priorTotal="";
                                               targettotal = conn.rs.getString("QtargetTotal");
                                               endTargetTotal = conn.rs.getString("endTargetTotal");
                                               achievedtotal="";
                                              System.out.println("test_count"+test_count);
                                            
                                        }
                                      }    
                                       int cnt=1;
                                       
                                        for(int a=0;a<7;a++){
                                            
                                        if(cnt==1){
//                                            String queries=" select DistrictName from districts where districtID='"+conn.rs4.getString("district")+"'";
//                                              System.out.println(queries);
//                                        conn.rs1=conn.state1.executeQuery(queries);
//                                        
//                                              while(conn.rs1.next()){
//                                                  districts= conn.rs1.getString("DistrictName");
//                                        
                                       
                                               
                                                 tablerows+="<tr><td></td><td  width=\"60px\"><input readonly type=\"text\" name=\"districts_"+test_count+"\" id=\"districts_"+test_count+"\"  value=\""+conn.rs4.getString("district")+"\" /></td>";
//                                             }
                                      
                                        }
                                        else if(cnt==2){
                                           // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinetotal_"+test_count+"\" id=\"baselinetotal_"+test_count+"\"  value=\""+baselinetotal+"\" /></td>";
                                              
                                        
                                        }
                                        else if(cnt==3){
                                        
                                         if(quarter.equals(Q2) ){
                                  String priors="select * from indicatorachievedcombined where reportingPeriod='"+Q1+"' AND financialYear='"+financial+"' AND district='"+conn.rs4.getString("district")+"' AND titleID='"+title+"'";
                                          System.out.println("priors1   "+priors);
                                           try{ conn.rs2=conn.state2.executeQuery(priors);
                                              if(conn.rs2.next()){
                                                     System.out.println("ERRRORNFJNFJVN ");
                                               priorsTotal=conn.rs2.getString("totalAchieved"); }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorsTotal!=null && !priorsTotal.equals("")){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorsTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                                catch(SQLException g){
                                                 System.out.println(g.toString());   
                                                 
                                              }  
                                               }
                                              else if(quarter.equals(Q3)){
                                  String priors="select * from indicatorachievedcombined where reportingPeriod='"+Q2+"' AND financialYear='"+financial+"' AND district='"+conn.rs4.getString("district")+"' AND titleID='"+title+"'";
                                            System.out.println("priors2    "+priors);
                                           try{ conn.rs2=conn.state2.executeQuery(priors);
                                               if(conn.rs2.next()){
                                                     System.out.println("ERRRORNFJNFJVN ");
                                               priorsTotal=conn.rs2.getString("totalAchieved"); }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorsTotal!=null && !priorsTotal.equals("")){
                                               //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                                catch(SQLException j){
                                                 System.out.println(j.toString()); 
                                                }
                                                 
                                               }
                                              else if(quarter.equals(Q1))
                                                   {
                                  String priors="select * from indicatorachievedcombined where reportingPeriod='"+Q4+"' AND financialYear='"+financial+"' AND district='"+conn.rs4.getString("district")+"' AND titleID='"+title+"'";
                                            System.out.println("priors3   "+priors);
                                           try{ conn.rs2=conn.state2.executeQuery(priors);
                                           
                                              if(conn.rs2.next()){
                                                     System.out.println("ERRRORNFJNFJVN ");
                                               priorsTotal=conn.rs2.getString("totalAchieved"); }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorsTotal!=null && priorsTotal!=""){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                                catch(SQLException j){
                                                 System.out.println(j.toString()); 
                                                 
                                                }
                                                 
                                                 
                                              
                                                                                           }
                                              else if(quarter.equals(Q4))
                                                   {
                                  String priors="select * from indicatorachievedcombined where reportingPeriod='"+Q3+"' AND financialYear='"+financial+"' AND district='"+conn.rs4.getString("district")+"' AND titleID='"+title+"'";
                                            System.out.println("priors3   "+priors);
                                           try{ conn.rs2=conn.state2.executeQuery(priors);
                                           
                                              if(conn.rs2.next()){
                                                     System.out.println("ERRRORNFJNFJVN ");
                                               priorsTotal=conn.rs2.getString("totalAchieved"); }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorsTotal!=null && priorsTotal!=""){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\""+priorsTotal+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorTotal_"+test_count+"\" id=\"priorTotal_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                                catch(SQLException j){
                                                 System.out.println(j.toString()); 
                                                 
                                                }
                                                 
                                                 
                                              
                                                                                           }
                                                
                                      
                                        }
                                       
                                        
                                        else if(cnt==4){
                                       // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"targettotal_"+test_count+"\" id=\"targettotal_"+test_count+"\"  value=\""+targettotal+"\" /></td>";
                                        }
                                        else if(cnt==5){
                                            tablerows+="<td  width=\"60px\"><input type=\"text\" readonly name=\"achievedtotal_"+test_count+"\" id=\"achievedtotal_"+test_count+"\"  value=\""+conn.rs4.getString("totalAchieved")+"\" /></td>";
                                        
                                        }
                                        else if(cnt==6){
                                           // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"endTargetTotal_"+test_count+"\" id=\"endTargetTotal_"+test_count+"\" value=\""+endTargetTotal+"\" /></td>";
                                        
                                        }
                                          else if(cnt==7){
                                          tablerows+="<input type=\"hidden\" name=\"totalID_"+test_count+"\" id=\"totalID_"+test_count+"\"  value=\""+totalID+"\" /></td>";
                                           
                                              }
                                        
                                       
                                        cnt++;
                                            // }//first for loop end
                                        }//second for loop end
                                         session.setAttribute("test_counter", test_count);
                                         baselinetotal="";
                                         endTargetTotal="";
                                         targettotal="";
                                        }//while end
                                        
                                       
                                       // resultcounts1
                                           
            
         
             
           
                                         }
           
              tablerows+="<input type=\"hidden\" name=\"resultcounts1\" id=\"resultcounts1\"  value=\""+test_count+"\" /></td>";
                                      
           
              out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
         

                 System.out.println("tablerows   2"+tablerows);             
                                  
                                  
              
            out.println("<tr>"+tablerows+"</tr>");
           
          
           
            out.println("</body>");
            out.println("</html>");
           
           tablerows="";
           
           
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
            Logger.getLogger(ResultsDisplay2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ResultsDisplay2.class.getName()).log(Level.SEVERE, null, ex);
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
