/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.startup.Catalina;

/**
 *
 * @author Maureen
 */
public class ResultsDisplay extends HttpServlet {

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
    String dists = "";
    int count = 0;
    String dist = "";
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
    String tablerows = "";
    String districts = "";
    String resultid = "";
    ArrayList list = new ArrayList();
    dbConnect conn = new dbConnect();
    String filter = "";
    String Q1 = "Q1";
    String Q2 = "Q2";
    String Q3 = "Q3";
    String Q4 = "Q4";
    int test_count = 0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         baselinewomen="";
         resultid = "";
           test_count = 0; 
            response.setContentType("text/html;charset=UTF-8");
             priorw="";
                                              priorm="";
       session = request.getSession(true);
           tablerows="";
           int Pepfer_year=0;
           
            if(request.getParameter("FY")!=null && !"".equals(request.getParameter("FY"))){
              financial=request.getParameter("FY");
                System.out.println("FINANCIAL"+financial);
           Pepfer_year=Integer.parseInt(financial);
            } 
             if(request.getParameter("QTR")!=null && !"".equals(request.getParameter("QTR"))){
              quarter=request.getParameter("QTR");
                System.out.println("QUARTER"+quarter);
            } 
              if(request.getParameter("title")!=null && !"".equals(request.getParameter("title"))){
              title = request.getParameter("title");
              System.out.println("TITLE"+title);
             }
         results="";
         String priorq="";
         String currentq="";
         if(quarter.equals("Q1")){
             Pepfer_year=Pepfer_year-1;
         priorq="July-Sep";
         currentq="Oct-Dec";
         
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
      tablerows+=" <tr>" +
                                  
"                        <col width=\"110px\" />" +
"                        <col width=\"112px\" />" +
"                        <col width=\"112px\" />" +
"                        <col width=\"112px\" />" +
"                        <col width=\"112px\" />" +
"                       \n" +
"                                                   \n" +
"                             <tr>     \n" +
"<!--                                      <th style=\"width:30px; text-align: left;\"  >SELECT</th>-->" +
"<!--                                      <th style=\"width:10px;\">NO.</th>-->" +
"                                      <th colspan=\"2\">  Additional Criteria</th>" +


"                                      <th  colspan=\"2\">This reporting period <label>"+currentq+" "+Pepfer_year+"</label></th>" +
                                  

"                                  </tr>" +
"                             <tr>" +
"                                      <th colspan=\"1\"></th><th>District</th>\n" +
"<!--                                      <th style=\"width:10px;\"></th>-->\n" +
"                                     \n" +

                                 

"                                      <th colspan=\"2\">Achieved</th>\n" +
                                 
"                                  </tr> " +
"                             <tr> " +
"                                      <th colspan=\"2\"></th>" +

"<!--                                      <th style=\"width:30px;\"></th>-->" +
"<!--                                      <th style=\"width:30px;\"></th>-->" +



"                                      <th style=\"width:60px;\">W</th>" +
"                                      <th style=\"width:60px;\">M</th>" +
"                                     </tr>";                                     
           
           if(financial!=null && !"".equals(financial) && quarter!=null &&
                    !"".equals(quarter) && title != null && !"".equals(title) ){
                 session.setAttribute("financial", financial);
                
                   session.setAttribute("quarter", quarter);
                   session.setAttribute("title", title);
               
               //String district="";
               String resultsQuery ="select * from indicatorachieved where reportingPeriod='"+quarter+"' AND financialYear='"+financial+"' and titleID='"+title+"'";
               conn.rs4 = conn.state4.executeQuery(resultsQuery);  
               while(conn.rs4.next()){
               String distr= conn.rs4.getString("district");
                  test_count++;

                  String query="select * from districts where DistrictName='"+distr+"'";
                  conn.rs5 = conn.state5.executeQuery(query);
                  if(conn.rs5.next()){
                        filter="select * from baseline where FinancialYear='"+financial+"' AND Quarter='"+quarter+"' and titleID='"+title+"' AND District='"+conn.rs5.getString("districtID") +"'";
                 
        //   filter="select * from baseline where FinancialYear='"+financial+"' AND Quarter='"+quarter+"' and titleID='"+title+"' AND District='"+distr+"'";
                   System.out.println(filter);
                   conn.rs=conn.state.executeQuery(filter);
                  System.out.println("filter  "+filter);
           
           //create an arraylist to hold the beans
                         while(conn.rs.next())
                                        {
                                            
                                     
                                           
                                                 dist =  conn.rs.getString("District"); 
                                                
                                                 baselinewomen = conn.rs.getString("womenBaseline");  
                                                 baselineid = conn.rs.getString("baselineID");  
                                                  System.out.println("baseline women          "+baselinewomen);
                                                 baselinemen =  conn.rs.getString("menBaseline"); 
                                                
                                            
                                            
                                         
                                          
                                                targetwomen = conn.rs.getString("QtargetWomen");
                                               
                                            
                                           
                                                 targetmen = conn.rs.getString("QtargetMen");
                                                
                                            
                                           
                                                   endTargetWomen = conn.rs.getString("endTargetWomen");
                                                
                                             
                                           
                                                   endTargetMen = conn.rs.getString("endTargetMen");
                                       }//end of while loop
                                      }        
                                            
                                                  achievedw="";
                                               
                                            
                                                 achievedm="";
                                                 
                                        
                                            
//                                              String queries=" select DistrictName from districts where districtID='"+dist+"'";
//                                              System.out.println(queries);
//                   conn.rs=conn.state.executeQuery(queries);
//                                              while(conn.rs.next()){
//                                                  districts= conn.rs.getString("DistrictName");
//                                        
                                    //   }
                                               
                                       
                                       int cnt=1;
                                       
                                        for(int a=0;a<12;a++){
                                          
                                        if(cnt==1){
//                                             String queries="select DistrictName from districts where districtID='"+dist+"'";
//                                              System.out.println(queries);
//                                              conn.rs1=conn.state1.executeQuery(queries);
//                                              while(conn.rs1.next()){
                                                 tablerows+="<tr><td></td><td  width=\"60px\"><input readonly type=\"text\" name=\"districts_"+test_count+"\" id=\"districts_"+test_count+"\"  value=\""+conn.rs4.getString("District")+"\" /></td>";
                                           
//                                              }
                                        }
                                        else if(cnt==2){
                                            if(baselinewomen!=null && !baselinewomen.equals("")){
                                           // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinewomen_"+test_count+"\" id=\"baselinewomen_"+test_count+"\"  value=\""+baselinewomen+"\" /></td>";
                                            }
                                            else{
                                                // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinewomen_"+test_count+"\" id=\"baselinewomen_"+test_count+"\"  value=\"\" /></td>";
                                            }
                                        
                                        }
                                        else if(cnt==3){
                                            if(baselinemen!=null && !baselinemen.equals("")){
                                         tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinemen_"+test_count+"\" id=\"baselinemen_"+test_count+"\"  value=\""+baselinemen+"\" /></td>";
                                        }
                                            else{
                                              //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinemen_"+test_count+"\" id=\"baselinemen_"+test_count+"\"  value=\"\" /></td>";
                                            }
                                        
                                        }
                                        else if(cnt==4){
                                            
                                                             if(quarter.equals(Q2) ){
                                                                 int year=0;
                                                  year = Integer.parseInt(financial)-1;
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q1+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                          System.out.println("priors"+priors);
                                            
                                             try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("rows for women"+priorw);
                                                }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && !"".equals(priorw)){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+conn.rs2.getString("womenAchieved")+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               
                                                }
                                               
                                              
                                                catch(SQLException g){
                                                 System.out.println(g.toString());   
                                                 
                                              }  
                                               }
                                              else if(quarter.equals(Q3)){
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q2+"' AND financialYear='"+financial+"' AND titleID='"+title+"'AND District='"+conn.rs4.getString("District")+"'";
                                            System.out.println("priors"+priors);
                                           try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("priorm"+priorw);
                                                }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && !"".equals(priorw)){
                                            //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+priorw+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                                catch(SQLException d){
                                                    System.out.println(d.toString()); 
                                                }
                                                 
                                               }
                                              else if(quarter.equals(Q1))
                                                   {
                                                       int year=0;
                                                  year = Integer.parseInt(financial)-1;
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q4+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                            System.out.println("priors"+priors);
                                           try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("priorm"+priorw);
                                                }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && !"".equals(priorw)){
                                             //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+priorw+"\" /></td>";
                                               }
                                               else{
                                             //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               } catch(SQLException n){
                                                    System.out.println(n.toString()); 
                                                }
                                                 
                                                 
                                               }
                                              else if(quarter.equals(Q4))
                                                   {
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q3+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                            System.out.println("priors"+priors);
                                           try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("priorm"+priorw);
                                               }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && !"".equals(priorw)){
                                             //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+priorw+"\" /></td>";
                                               }
                                               else{
                                               // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                           catch(SQLException n){
                                                    System.out.println(n.toString()); 
                                                }
                                                 
                                                 
                                               }
                                              
                                          else{ 
                                               //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                              }
                                                
                                      
                                        }
                                        else if(cnt==5){
                                            
                                             if(quarter.equals(Q2) ){
                                                 int year=0;
                                                  year = Integer.parseInt(financial)-1;

                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q1+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                          System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                              
                                                if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); 
                                                System.out.println("priorm"+priorm);}
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && !"".equals(priorm)){
                                               //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
                                               }
                                               else{
                                                  // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                                                                            }

                                              
                                              
                                               
                                             
                                                catch(SQLException o){
                                                 System.out.println(o.toString());   
                                                 
                                              }  
                                               }
                                              else if(quarter.equals(Q3)){
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q2+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                            System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                           
                                           
                                                 if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); 
                                                System.out.println("priorm"+priorm);
                                                 }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && !"".equals(priorm)){
                                                 // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
                                               }
                                               else{
                                                  // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                            catch(SQLException o){
                                                 System.out.println(o.toString());   
                                                 
                                              } 
                                              
                                              }
                                              else if(quarter.equals(Q1))
                                                   {
                                                   int year=0;
                                                  year = Integer.parseInt(financial)-1;    
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q4+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                            System.out.println("priors"+priors);
                                            System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                           
                                           
                                                 if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); 
                                                System.out.println("priorm"+priorm);
                                                 }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && !"".equals(priorm)){
                                                 // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
                                               }
                                               else{
                                                   //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                            catch(SQLException o){
                                                 System.out.println(o.toString());   
                                                 
                                              } 
                                              
                                              }
                                              else if(quarter.equals(Q4))
                                                   {
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q3+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+conn.rs4.getString("District")+"'";
                                            System.out.println("priors"+priors);
                                            System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                           
                                           
                                                 if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); 
                                                System.out.println("priorm"+priorm);
                                                 }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && !"".equals(priorm)){
                                               //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
                                               }
                                               else{
                                               //    tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                            catch(SQLException o){
                                                 System.out.println(o.toString());   
                                                 
                                              } 
                                              
                                              }
                                                 
                                           
                                              
                                                
                                               
                                                 
                                          
                                        
                                        }
                                        else if(cnt==6){
                                          //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"QtargetWomen_"+test_count+"\" id=\"QtargetWomen_"+test_count+"\"  value=\""+targetwomen+"\" /></td>";
                                        }
                                        else if(cnt==7){
                                         //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"QtargetMen_"+test_count+"\" id=\"QtargetMen_"+test_count+"\"  value=\""+targetmen+"\" /></td>";
                                        }
                                         else if(cnt==8){
                                             
                                             tablerows+="<td  width=\"60px\"><input type=\"text\" readonly name=\"achievedw_"+test_count+"\" id=\"achievedw_"+test_count+"\" value=\""+ conn.rs4.getString("womenAchieved")+"\" /></td>";
                                         }
                                         else if(cnt==9){
                                               tablerows+="<td  width=\"60px\"><input type=\"text\" readonly name=\"achievedm_"+test_count+"\" id=\"achievedm_"+test_count+"\" value=\""+conn.rs4.getString("menAchieved") +"\" /></td>";
                                         
                                         }
                                         else if(cnt==10){
                                               //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"endTargetWomen_"+test_count+"\" id=\"endTargetWomen_"+test_count+"\"  value=\""+endTargetWomen+"\" /></td>";
                                         
                                         }
                                         else if(cnt==11){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"endTargetMen_"+test_count+"\" id=\"endTargetMen_"+test_count+"\"  value=\""+endTargetMen+"\" /></td></tr>";
                                         
                                         }
                                         else if(cnt==12){
                                          tablerows+="<input type=\"hidden\" name=\"baselineid_"+test_count+"\" id=\"baselineid_"+test_count+"\"  value=\""+baselineid+"\" /></td>";
                                         
                                           
                                              }
                                        
                                        
                                        cnt++;
                                       
                                       // }//second for loop end
                                        }// first for loop end
                                        //}//while end
                                      session.setAttribute("test_count1", test_count);  
                                        baselinewomen="";
                                        baselinemen="";
                                        endTargetMen="";
                                        endTargetWomen="";
                                        targetwomen="";
                                        targetmen="";
                                        
               }//end of indicator achieved while
               
                                       
            
           
                                         }//
           
           
              tablerows+="<input type=\"hidden\" name=\"resultcount"+"\" id=\"resultcount\"  value=\""+test_count+"\" />";
            
           
           //end of if
           
                                       
                                        System.out.println("TABLE ROWS ==========================="+tablerows);
                                       
                                          
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<tr>"+tablerows+"</tr>");
            out.println("</body>");
            out.println("</html>");
             
           tablerows="";
           
           } 
          // response.sendRedirect("myajax.html");
     
    
        
    

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
            Logger.getLogger(ResultsDisplay.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ResultsDisplay.class.getName()).log(Level.SEVERE, null, ex);
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
