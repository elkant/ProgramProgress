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
public class ActivityServlets2 extends HttpServlet {

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
    String financial="",quarter="",title="",results="";
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
   String resultID="";
   
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
                                    
                                         String ResultQuery="";
                                             int indiccount=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        tablerows="";
             dbConnect conn = new dbConnect();
        System.out.println(" Financial "+financial);
           int test_count=0;   
           int pepfar_year = 1;
        
         if(request.getParameter("FY")!=null && !"".equals(request.getParameter("FY"))){
              financial=request.getParameter("FY");
                           
            } 
         
          if (!financial.equals("")) {
            pepfar_year = Integer.parseInt(financial);
        }
         
             if(request.getParameter("QTR")!=null && !"".equals(request.getParameter("QTR"))){
              quarter=request.getParameter("QTR");
                
            }
             
              if (quarter.equals("Q1")) {
                pepfar_year = pepfar_year - 1;
            }
             
              if(request.getParameter("title")!=null && !"".equals(request.getParameter("title"))){
              title = request.getParameter("title");
             
             } String baselines=""; 
             
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
"                                      <th colspan=\"2\">  Additional Criteria</th>"
                      + " <th  colspan=\"2\">THIS REPORTING PERIOD <label>"+currentq+" "+pepfar_year+"</label>"
                      + "</th></tr> </tr>" +
"                             <tr>" +
"                                      <th colspan=\"2\"></th>\n" +
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
                    
                   
             
             
            
             
             
         if(financial!=null && financial!="" && quarter!=null &&quarter!="" && title != null && title!="" ){
               
         String district="";
//          results="select * from indicatorachievedcombined where financialYear='"+financial+"' AND reportingPeriod='"+quarter+"' and titleID='"+title+"'";
             
            ResultQuery="select * from indicatorachieved where financialYear='"+financial+"' AND reportingPeriod='"+quarter+"' and titleID='"+title+"'";
                 
        
                   conn.rs1=conn.state.executeQuery(ResultQuery);
                   
                   
                   
                   
                   
                     
                   
                
                   
                   
                   
                   
                  
              while(conn.rs1.next())
                                        {
                                            indiccount++;
                                     test_count++;
//             baselines = conn.rs1.getString("baselineID");          
             menAchieved= conn.rs1.getString("menAchieved"); 
             womenAchieved= conn.rs1.getString("womenAchieved"); 
             district= conn.rs1.getString("District");
             resultID = conn.rs1.getString("resultID");
               String districts="";
              String queryDist ="select * from districts where DistrictName='"+district+"'";
              conn.rs4 = conn.state4.executeQuery(queryDist);
              while(conn.rs4.next()){
              districts = conn.rs4.getString("districtID");
              }
            
            
            
            
           String result1="select * from baseline where FinancialYear='"+financial+"' AND Quarter='"+quarter+"' and titleID='"+title+"' ";
            //results="select * from baselinetotal 
//                  AND District='"+districts+"'
                   conn.rs5=conn.state5.executeQuery(result1);
            
            
            
           //create an arraylist to hold the beans
                   
                 
          
           String Q1="Q1";
           String Q2="Q2";
           String Q3="Q3";
           String Q4="Q4";
                     
                    
                    
                    
                    
                    
                    
                    
                    

                                        while(conn.rs5.next())
                                        {
                                            
                                           
                                           
                                                 dist =  conn.rs5.getString("District"); 
                                                
                                                 baselinewomen = conn.rs5.getString("womenBaseline");  
                                                 baselineid = conn.rs5.getString("baselineID");  
                                              
                                                 baselinemen =  conn.rs5.getString("menBaseline"); 
               
//           
//                                              priorw="";
//                                              priorm="";
                                            
                                          
                                          
                                                targetwomen = conn.rs5.getString("QtargetWomen");
                                               
                                            
                                           
                                                 targetmen = conn.rs5.getString("QtargetMen");
                                                
                                            
                                           
                                                   endTargetWomen = conn.rs5.getString("endTargetWomen");
                                                
                                             
                                           
                                                   endTargetMen = conn.rs5.getString("endTargetMen");
                                               
//                                               result="select * from indicatorresults where activityID='"+activityID+"'";
//                 
                                            }
//                                                  achievedw=womenAchieved;
//                                                  achievedm=menAchieved;
                                                  
                                      
                                       int cnt=1;
                                       
                                        for(int a=0;a<12;a++){
                                          
                                        if(cnt==1){
//                                             String queries="select DistrictName from districts where districtID='"+dist+"'";
//                                               conn.rs1=conn.state1.executeQuery(queries);
//                                              while(conn.rs1.next()){
                                                 tablerows+="<tr><td></td><td  width=\"60px\"><input type=\"text\" name=\"districts_"+test_count+"\" id=\"districts_"+test_count+"\"  value=\""+district+"\" /></td>";
                                           
//                                              }
                                        }
                                        else if(cnt==2){
                                            
                                          //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinewomen_"+test_count+"\" id=\"baselinewomen_"+test_count+"\"  value=\""+baselinewomen+"\" /></td>";
                                             
                                        
                                        }
                                        else if(cnt==3){
                                        // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"baselinemen_"+test_count+"\" id=\"baselinemen_"+test_count+"\"  value=\""+baselinemen+"\" /></td>";
                                        }
                                        else if(cnt==4){
                                            
                                                 if(quarter.equals(Q2) ){
                                                                 int year=0;
                                                  year = Integer.parseInt(financial)-1;
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q1+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+district+"'";
                                          System.out.println("priors"+priors);
                                            
                                             try{  conn.rs2=conn.state3.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("rows for women"+priorw);
                                                }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && priorw!=""){
                                             //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+conn.rs2.getString("womenAchieved")+"\" /></td>";
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
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q2+"' AND financialYear='"+financial+"' AND titleID='"+title+"'AND District='"+district+"'";
                                            System.out.println("priors"+priors);
                                           try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("priorm"+priorw);
                                                }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && priorw!=""){
                                               //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+priorw+"\" /></td>";
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
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q4+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+district+"'";
                                            System.out.println("priors"+priors);
                                           try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("priorm"+priorw);
                                                }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && priorw!=""){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+priorw+"\" /></td>";
                                               }
                                               else{
                                                //tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               } catch(SQLException n){
                                                    System.out.println(n.toString()); 
                                                }
                                                 
                                                 
                                               }
                                              else if(quarter.equals(Q4))
                                                   {
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q3+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+district+"'";
                                            System.out.println("priors"+priors);
                                           try{  conn.rs2=conn.state2.executeQuery(priors);
                                                if(conn.rs2.next()){
                                               priorw=conn.rs2.getString("womenAchieved");
                                                System.out.println("priorm"+priorw);
                                               }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorw!=null && priorw!=""){
                                              // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\""+priorw+"\" /></td>";
                                               }
                                               else{
                                              //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                           catch(SQLException n){
                                                    System.out.println(n.toString()); 
                                                }
                                                 
                                                 
                                               }
                                              
                                          else{ 
                                                //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorw_"+test_count+"\" id=\"priorw_"+test_count+"\"  value=\"\" /></td>";
                                              }
                                                
                                      
                                        }
                                        else if(cnt==5){
                                            
                                             if(quarter.equals(Q2) ){
                                                 int year=0;
                                                  year = Integer.parseInt(financial)-1;

                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q1+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+district+"'";
                                          System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                              
                                                if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); ;
                                                System.out.println("priorm"+priorm);}
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && priorm!=""){
                                                //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
                                               }
                                               else{
                                                 //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                                                                            }

                                              
                                              
                                               
                                             
                                                catch(SQLException o){
                                                 System.out.println(o.toString());   
                                                 
                                              }  
                                               }
                                              else if(quarter.equals(Q3)){
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q2+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+district+"'";
                                            System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                           
                                           
                                                 if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); ;
                                                System.out.println("priorm"+priorm);
                                                 }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && priorm!=""){
                                                //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
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
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q4+"' AND financialYear='"+year+"' AND titleID='"+title+"' AND District='"+district+"'";
                                            System.out.println("priors"+priors);
                                            System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                           
                                           
                                                 if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); ;
                                                System.out.println("priorm"+priorm);
                                                 }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && priorm!=""){
                                                 // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\""+priorm+"\" /></td>";
                                               }
                                               else{
                                                 //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"priorm_"+test_count+"\" id=\"priorm_"+test_count+"\"  value=\"\" /></td>";
                                               }
                                               }
                                            catch(SQLException o){
                                                 System.out.println(o.toString());   
                                                 
                                              } 
                                              
                                              }
                                              else if(quarter.equals(Q4))
                                                   {
                                  String priors="select * from indicatorachieved where reportingPeriod='"+Q3+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+district+"'";
                                            System.out.println("priors"+priors);
                                            System.out.println("priors"+priors);
                                           try{ 
                                               conn.rs3=conn.state3.executeQuery(priors);
                                           
                                           
                                                 if(conn.rs3.next()){
                                                priorm=conn.rs3.getString("menAchieved"); ;
                                                System.out.println("priorm"+priorm);
                                                 }
                                               //System.out.println("priorsTotal    3"+priorsTotal);
                                               if(priorm!=null && priorm!=""){
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
                                                 
                                           
                                              
                                                
                                               
                                                 
                                          
                                        
                                        }
                                              
                                        else if(cnt==6){
                                       //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"QtargetWomen_"+test_count+"\" id=\"QtargetWomen_"+test_count+"\"  value=\""+targetwomen+"\" /></td>";
                                        }
                                        else if(cnt==7){
                                        // tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"QtargetMen_"+test_count+"\" id=\"QtargetMen_"+test_count+"\"  value=\""+targetmen+"\" /></td>";
                                        }
                                         else if(cnt==8){
                                             tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"achievedw_"+test_count+"\" id=\"achievedw_"+test_count+"\" value=\""+womenAchieved+"\" /></td>";
                                         }
                                         else if(cnt==9){
                                               tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"achievedm_"+test_count+"\" id=\"achievedm_"+test_count+"\" value=\""+menAchieved+"\" /></td>";
                                         
                                         }
                                         else if(cnt==10){
                                            //   tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"endTargetWomen_"+test_count+"\" id=\"endTargetWomen_"+test_count+"\"  value=\""+endTargetWomen+"\" /></td>";
                                         
                                         }
                                         else if(cnt==11){
                                             //  tablerows+="<td  width=\"60px\"><input type=\"text\" name=\"endTargetMen_"+test_count+"\" id=\"endTargetMen_"+test_count+"\"  value=\""+endTargetMen+"\" /></td></tr>";
                                         
                                         }
                                         else if(cnt==12){
                                          tablerows+="<input type=\"hidden\" name=\"baselineidsss_"+test_count+"\" id=\"baselineid_"+test_count+"\"  value=\""+baselineid+"\" />";
                                          tablerows+="<input type=\"hidden\" name=\"resultID_"+test_count+"\" id=\"resultID_"+test_count+"\"  value=\""+resultID+"\" />";
                                           
                                              }
                                         
                                        cnt++;
                                       
                                       // }//second for loop 
                                        }// first for loop end
                                      //  }//while end
                                        
                                        
                                        
                                      
                                        }// end of first while loop
//                                         session.setAttribute("test_counts", test_count);
              System.out.println("___"+indiccount);
              System.out.println("++++++"+test_count);
                                   
             tablerows+="<input type=\"hidden\" name=\"count\" id=\"count\"  value="+test_count+">"; 
        
    }
        
     out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h4>"+tablerows+"</h4>");
            out.println("</body>");
            out.println("</html>");
         
            
            
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
            Logger.getLogger(ActivityServlets2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ActivityServlets2.class.getName()).log(Level.SEVERE, null, ex);
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
