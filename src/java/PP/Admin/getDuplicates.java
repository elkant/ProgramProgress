/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PP.Admin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




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
public class getDuplicates extends HttpServlet {

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
  
      HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
         try {
           
              dbConnect conn = new dbConnect();
             
session = request.getSession(true);
  String type="";
  String financialyear="";
  String quarter="";
  
  
  type=request.getParameter("type");
  financialyear=request.getParameter("financialyear");
  quarter=request.getParameter("quarter");
                                
     System.out.println(financialyear +" bbb   "+quarter);                                   
                                        
                                        ArrayList missingdata = new ArrayList();
                                        ArrayList missingdata2 = new ArrayList();
      
                                        if(type.equals("fullname")){


                           String query="SELECT u.titleID,u.countyID,u.DistrictID,u.activityTitle,u.activityOthers,u.startdate,u.enddate,u.FinancialYear,u.Quarter,u.subtotals from indicatoractivities as u  inner join("
+" select titleID,countyID,DistrictID,activityTitle,activityOthers,startdate, enddate,FinancialYear,Quarter,subtotals"
+" from indicatoractivities where"
+" FinancialYear='"+financialyear+"' and Quarter='"+quarter+"'"
+" group by  CONCAT(titleID,\"\", countyID,\"\", DistrictID,\"\",activityTitle,\"\",subtotals)"
+" having count(CONCAT(titleID,\"\", countyID,\"\", DistrictID,\"\",activityTitle,\"\",subtotals))>1"
+" ) as temp"
+" on"
+" temp.titleID= u.titleID"
+" and temp.countyID=u.countyID"
+" and temp.DistrictID= u.DistrictID"

+ " and temp.activityTitle=u.activityTitle "
+ " and temp.subtotals=u.subtotals "
+" where"
+"  u.FinancialYear='"+financialyear+"'and u.Quarter='"+quarter+"'"
+" limit 100000000";
                        
                                            
                                            System.out.println("query " + query);
              
                                                        conn.rs = conn.state.executeQuery(query);
        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                    System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
                       DQADUPLICATEBean DB= new DQADUPLICATEBean();
                       
                       
                     
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+conn.rs.getString("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs2 = conn.state2.executeQuery(Query1);
                                                      while(conn.rs2.next())
                                                           {
                                                       
                                                            DB.setTITLEID(conn.rs2.getString("title"));
                                  
  
                                                                                }
                       
                      
                       
                       
                           if(conn.rs.getString("countyID")!=null)
                      {
                 String county_selector="select * from county where countyID='"+conn.rs.getString("countyID")+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs.getString(4));
                  DB.setCOUNTYID(conn.rs2.getString("countyName"));        
                      }
                      
                           
                   String r =conn.rs.getString("DistrictID");
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                  
				conn.rs3 = conn.state3.executeQuery(QueryCounty);
                                                      while(conn.rs3.next())
                                                           {
                                              DB.setDISTRICTID(conn.rs3.getString("DistrictName"));                                                             
                  
                                                           }
  
                                                                                                                  }                }
                                                              
                                                         
                           
                    //   DB.setCOUNTYID(conn.rs.getString("countyID"));
                     
                       DB.setYEAR(conn.rs.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs.getString("Quarter"));
                       if(conn.rs.getString("activityTitle").equals("1031")){
                       
                        DB.setACTIVITYTITLE( conn.rs.getString("activityOthers"));  
                       }else{
                       String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ conn.rs.getString("activityTitle") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs3 = conn.state3.executeQuery(Query);
                                                      while(conn.rs3.next())
                                                           {
                                               DB.setACTIVITYTITLE( conn.rs3.getString("Activity"));                                                                          
                                             
               
                                                 
  
                                                           } }
                               
                                                 
                       
                      
                       DB.setTOTAL(conn.rs.getString("subtotals"));
                 
                     
                       
                    
                      
          
                       missingdata.add(DB);
                       
                      
        }
                           String query2="SELECT u.titleID,u.countyID,u.DistrictID,u.activityTitle,u.activityOthers,u.startdate,u.enddate,u.FinancialYear,u.Quarter,u.total from indicatoractivities1 as u  inner join("
+" select titleID,countyID,DistrictID,activityTitle,activityOthers,startdate, enddate,FinancialYear,Quarter,total"
+" from indicatoractivities1 where"
+" FinancialYear='"+financialyear+"' and Quarter='"+quarter+"'"
+" group by  CONCAT(titleID,\"\", countyID,\"\", DistrictID,\"\",activityTitle,\"\",total)"
+" having count(CONCAT(titleID,\"\", countyID,\"\", DistrictID,\"\",activityTitle,\"\",total))>1"
+" ) as temp"
+" on"
+" temp.titleID= u.titleID"
+" and temp.countyID=u.countyID"
+" and temp.DistrictID= u.DistrictID"

+ " and temp.activityTitle=u.activityTitle "
+ " and temp.total=u.total"
+" where"
+"  u.FinancialYear='"+financialyear+"'and u.Quarter='"+quarter+"'"
+" limit 100000000";
                        
                                            
                                            System.out.println("query " + query2);
              
                                                        conn.rs4 = conn.state4.executeQuery(query2);
        if(missingdata2!=null && missingdata2.size()>0 ){missingdata2.clear();}

                                        while(conn.rs4.next())
                                        {
                                                
                    System.out.println(conn.rs4.getString(1) +"  "+ conn.rs4.getString(2) +" "+conn.rs4.getString(3));                     
                       DQADUPLICATEBean DB= new DQADUPLICATEBean();
                       
                       
                     
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+conn.rs4.getString("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs2 = conn.state2.executeQuery(Query1);
                                                      while(conn.rs2.next())
                                                           {
                                                       
                                                            DB.setTITLEID(conn.rs2.getString("title"));
                                  
  
                                                                                }
                       
                      
                       
                       
                           if(conn.rs4.getString("countyID")!=null)
                      {
                 String county_selector="select * from county where countyID='"+conn.rs4.getString("countyID")+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs4.getString(4));
                  DB.setCOUNTYID(conn.rs2.getString("countyName"));        
                      }
                      
                           
                   String r =conn.rs4.getString("DistrictID");
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                  
				conn.rs3 = conn.state3.executeQuery(QueryCounty);
                                                      while(conn.rs3.next())
                                                           {
                                              DB.setDISTRICTID(conn.rs3.getString("DistrictName"));                                                             
                  
                                                           }
  
                                                                                                                  }                }
                                                              
                                                         
                           
                    //   DB.setCOUNTYID(conn.rs.getString("countyID"));
                     
                       DB.setYEAR(conn.rs4.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs4.getString("Quarter"));
                       if(conn.rs4.getString("activityTitle").equals("1031")){
                       
                        DB.setACTIVITYTITLE( conn.rs4.getString("activityOthers"));  
                       }else{
                       String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ conn.rs4.getString("activityTitle") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs3 = conn.state3.executeQuery(Query);
                                                      while(conn.rs3.next())
                                                           {
                                                           
                                               DB.setACTIVITYTITLE( conn.rs3.getString("Activity"));                                                                          
                                             
               
                                                 
  
                                                                                }
                       }
                                                 
                       
                      
                       DB.setTOTAL(conn.rs4.getString("total"));
                 
                     
                       
                    
                      
          
                       missingdata2.add(DB);
                       
                      
        }
         }                           
                             
                               if(type.equals("missingdata")) {  
                 String query = "select  titleID,countyID,DistrictID,activityTitle,activityOthers,startdate,enddate,FinancialYear,"
                         + "Quarter,subtotals from indicatoractivities where "
                         + "(startdate=''|| startdate='NULL') || "
                         + "(enddate='' || enddate ='NULL') || (titleID='' || titleID ='NULL')||"
                         + " (countyID='' || countyID ='NULL')|| "
                         + "(DistrictID='' || DistrictID ='NULL')";
                                        System.out.println("query " + query);
              
                                                        conn.rs = conn.state.executeQuery(query);
        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                  
                     System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
                       DQADUPLICATEBean DB= new DQADUPLICATEBean();
                       
                       
                     
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+conn.rs.getString("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs2 = conn.state2.executeQuery(Query1);
                                                      while(conn.rs2.next())
                                                           {
                                                       
                                                            DB.setTITLEID(conn.rs2.getString("title"));
                                  
  
                                                                                }
                       
                      
                       
                       
                           if(conn.rs.getString("countyID")!=null)
                      {
                 String county_selector="select * from county where countyID='"+conn.rs.getString("countyID")+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs.getString(4));
                  DB.setCOUNTYID(conn.rs2.getString("countyName"));        
                      }
                      
                           
                   String r =conn.rs.getString("DistrictID");
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                  
				conn.rs3 = conn.state3.executeQuery(QueryCounty);
                                                      while(conn.rs3.next())
                                                           {
                                              DB.setDISTRICTID(conn.rs3.getString("DistrictName"));                                                             
                  
                                                           }
  
                                                                                                                  }                }
                                                              
                                                         
                           
                    //   DB.setCOUNTYID(conn.rs.getString("countyID"));
                     
                       DB.setYEAR(conn.rs.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs.getString("Quarter"));
                       if(conn.rs.getString("activityTitle").equals("1031")){
                       
                        DB.setACTIVITYTITLE( conn.rs.getString("activityOthers"));  
                       }else{
                       String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ conn.rs.getString("activityTitle") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs3 = conn.state3.executeQuery(Query);
                                                      while(conn.rs3.next())
                                                           {
                                               DB.setACTIVITYTITLE( conn.rs3.getString("Activity"));                                                                          
                                             
               
                                                 
  
                                                           } }
                               
                                                 
                       
                      
                       DB.setTOTAL(conn.rs.getString("subtotals"));
                 
                     
                       
                    
                   
                    
                      
          
                       missingdata.add(DB);
                       
                      
        }
                 String query2 = "select  titleID,countyID,DistrictID,activityTitle,activityOthers,startdate,enddate,FinancialYear,"
                         + "Quarter,total from indicatoractivities1 where "
                         + "(startdate=''|| startdate='NULL') || "
                         + "(enddate='' || enddate ='NULL') || (titleID='' || titleID ='NULL')||"
                         + " (countyID='' || countyID ='NULL')|| "
                         + "(DistrictID='' || DistrictID ='NULL')";
                                        System.out.println("query " + query2);
              
                                                        conn.rs = conn.state.executeQuery(query2);
        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                  
                     System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
                       DQADUPLICATEBean DB= new DQADUPLICATEBean();
                       
                       
                     
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+conn.rs.getString("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs2 = conn.state2.executeQuery(Query1);
                                                      while(conn.rs2.next())
                                                           {
                                                       
                                                            DB.setTITLEID(conn.rs2.getString("title"));
                                  
  
                                                                                }
                       
                      
                       
                       
                           if(conn.rs.getString("countyID")!=null)
                      {
                 String county_selector="select * from county where countyID='"+conn.rs.getString("countyID")+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs.getString(4));
                  DB.setCOUNTYID(conn.rs2.getString("countyName"));        
                      }
                      
                           
                   String r =conn.rs.getString("DistrictID");
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                  
				conn.rs3 = conn.state3.executeQuery(QueryCounty);
                                                      while(conn.rs3.next())
                                                           {
                                              DB.setDISTRICTID(conn.rs3.getString("DistrictName"));                                                             
                  
                                                           }
  
                                                                                                                  }                }
                                                              
                                                         
                           
                    //   DB.setCOUNTYID(conn.rs.getString("countyID"));
                     
                       DB.setYEAR(conn.rs.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs.getString("Quarter"));
                       if(conn.rs.getString("activityTitle").equals("1031")){
                       
                        DB.setACTIVITYTITLE( conn.rs.getString("activityOthers"));  
                       }else{
                       String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ conn.rs.getString("activityTitle") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs3 = conn.state3.executeQuery(Query);
                                                      while(conn.rs3.next())
                                                           {
                                               DB.setACTIVITYTITLE( conn.rs3.getString("Activity"));                                                                          
                                             
               
                                                 
  
                                                           } }
                               
                                                 
                       
                      
                       DB.setTOTAL(conn.rs.getString("total"));
                 
                     
                       
                    
                   
                    
                      
          
                       missingdata2.add(DB);
                       
                      
        }
                                     }
                               if(type.equals("wrongdate")) {  
                 String query = "select  titleID,countyID,DistrictID,activityTitle,activityOthers,startdate,enddate,FinancialYear,"
                         + "Quarter,subtotals from indicatoractivities where "
                         + "(startdate NOT REGEXP '^..........$') OR (enddate NOT REGEXP '^..........$')";
                                        System.out.println("query " + query);
              
                                                        conn.rs = conn.state.executeQuery(query);
        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                  
                     System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
                       DQADUPLICATEBean DB= new DQADUPLICATEBean();
                       
                       
                     
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+conn.rs.getString("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs2 = conn.state2.executeQuery(Query1);
                                                      while(conn.rs2.next())
                                                           {
                                                       
                                                            DB.setTITLEID(conn.rs2.getString("title"));
                                  
  
                                                                                }
                       
                      
                       
                       
                           if(conn.rs.getString("countyID")!=null)
                      {
                 String county_selector="select * from county where countyID='"+conn.rs.getString("countyID")+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs.getString(4));
                  DB.setCOUNTYID(conn.rs2.getString("countyName"));        
                      }
                      
                           
                   String r =conn.rs.getString("DistrictID");
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                  
				conn.rs3 = conn.state3.executeQuery(QueryCounty);
                                                      while(conn.rs3.next())
                                                           {
                                              DB.setDISTRICTID(conn.rs3.getString("DistrictName"));                                                             
                  
                                                           }
  
                                                                                                                  }                }
                                                              
                                                         
                           
                    //   DB.setCOUNTYID(conn.rs.getString("countyID"));
                     
                       DB.setYEAR(conn.rs.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs.getString("Quarter"));
//                       if(conn.rs.getString("activityTitle").equals("1031")){
//                       
////                        DB.setACTIVITYTITLE( conn.rs.getString("activityOthers"));  
////                       }else{
////                       String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ conn.rs.getString("activityTitle") +"' ";
////                                                     conn.state= conn.connect.createStatement();
////				conn.rs3 = conn.state3.executeQuery(Query);
////                                                      while(conn.rs3.next())
////                                                           {
////                                               DB.setACTIVITYTITLE( conn.rs3.getString("Activity"));                                                                          
////                                             
////               
////                                                 
////  
////                                                           } }
////                               
////                                                 
////                       
////                      
////                       DB.setTOTAL(conn.rs.getString("subtotals"));
                 
                     
                      DB.setSTARTDATE(conn.rs.getString("startdate"));  
                      DB.setENDDATE(conn.rs.getString("enddate"));    
                    
                   
                    
                      
          
                       missingdata.add(DB);
                       
                      
        }
                 String query2 = "select  titleID,countyID,DistrictID,activityTitle,activityOthers,startdate,enddate,FinancialYear,"
                         + "Quarter,total from indicatoractivities1 where "
                         + "(startdate NOT REGEXP '^..........$') OR (enddate NOT REGEXP '^..........$')";
                                        System.out.println("query " + query2);
              
                                                        conn.rs = conn.state.executeQuery(query2);
        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}

                                        while(conn.rs.next())
                                        {
                                                
                  
                     System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
                       DQADUPLICATEBean DB= new DQADUPLICATEBean();
                       
                       
                     
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+conn.rs.getString("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs2 = conn.state2.executeQuery(Query1);
                                                      while(conn.rs2.next())
                                                           {
                                                       
                                                            DB.setTITLEID(conn.rs2.getString("title"));
                                  
  
                                                                                }
                       
                      
                       
                       
                           if(conn.rs.getString("countyID")!=null)
                      {
                 String county_selector="select * from county where countyID='"+conn.rs.getString("countyID")+"'" ;
                 conn.rs2=conn.state2.executeQuery(county_selector);
                 conn.rs2.next();
                 System.out.println("county id:"+conn.rs.getString(4));
                  DB.setCOUNTYID(conn.rs2.getString("countyName"));        
                      }
                      
                           
                   String r =conn.rs.getString("DistrictID");
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                  
				conn.rs3 = conn.state3.executeQuery(QueryCounty);
                                                      while(conn.rs3.next())
                                                           {
                                              DB.setDISTRICTID(conn.rs3.getString("DistrictName"));                                                             
                  
                                                           }
  
                                                                                                                  }                }
                                                              
                                                         
                           
                    //   DB.setCOUNTYID(conn.rs.getString("countyID"));
                     
                       DB.setYEAR(conn.rs.getString("FinancialYear"));
                       DB.setQUARTER(conn.rs.getString("Quarter"));
//                       if(conn.rs.getString("activityTitle").equals("1031")){
//                       
//                        DB.setACTIVITYTITLE( conn.rs.getString("activityOthers"));  
//                       }else{
//                       String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ conn.rs.getString("activityTitle") +"' ";
//                                                     conn.state= conn.connect.createStatement();
//				conn.rs3 = conn.state3.executeQuery(Query);
//                                                      while(conn.rs3.next())
//                                                           {
//                                               DB.setACTIVITYTITLE( conn.rs3.getString("Activity"));                                                                          
//                                             
//               
//                                                 
//  
//                                                           } }
//                               
//                                                 
//                       
//                      
//                       DB.setTOTAL(conn.rs.getString("total"));
//                 
//                     
                       
                    
                    DB.setSTARTDATE(conn.rs.getString("startdate"));  
                      DB.setENDDATE(conn.rs.getString("enddate"));  
                    
                      
          
                       missingdata2.add(DB);
                       
                      
        }
                                     }
//                                     if(type.equals("wrongdate")) {  
//                 String query = "select UniqueID, DICName,DOE from enrollment where  DOE NOT REGEXP '^..........$'";
//                                        System.out.println("query " + query);
//              
//                                                        conn.rs = conn.state.executeQuery(query);
//        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}
//
//                                        while(conn.rs.next())
//                                        {
//                                                
//                    System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
//                       DQAMissingBean DB= new DQAMissingBean();
//                       DB.setUNIQUEID(conn.rs.getString(1));
//                       DB.setDICNAME(conn.rs.getString(2));
//                       DB.setDOE(conn.rs.getString(3));
//                     
//                       
//                    
//                      
//          
//                       missingdata.add(DB);
//                       
//                      
//        }
//                                     }
////                                     if(type.equals("less2012")) {  
//                 String query = "select UniqueID, DICName,DOE from enrollment where (STR_TO_DATE(DOE,'%e/%c/%Y'))<STR_TO_DATE('01/01/2012','%e/%c/%Y')";
//                                        System.out.println("query " + query);
//              
//                                                        conn.rs = conn.state.executeQuery(query);
//        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}
//
//                                        while(conn.rs.next())
//                                        {
//                                                
//                    System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
//                       DQAMissingBean DB= new DQAMissingBean();
//                       DB.setUNIQUEID(conn.rs.getString(1));
//                       DB.setDICNAME(conn.rs.getString(2));
//                       DB.setDOE(conn.rs.getString(3));
//                     
//                       
//                    
//                      
//          
//                       missingdata.add(DB);
//                       
//                      
//        }
//                                     }
//                                     if(type.equals("dob")) {  
//                 String query = "select UniqueID, DICName,DOB from enrollment where (STR_TO_DATE(DOB,'%e/%c/%Y'))>STR_TO_DATE('01/01/2004','%e/%c/%Y') ";
//                                        System.out.println("query " + query);
//              
//                                                        conn.rs = conn.state.executeQuery(query);
//        if(missingdata!=null && missingdata.size()>0 ){missingdata.clear();}
//
//                                        while(conn.rs.next())
//                                        {
//                                                
//                    System.out.println(conn.rs.getString(1) +"  "+ conn.rs.getString(2) +" "+conn.rs.getString(3));                     
//                       DQAMissingBean DB= new DQAMissingBean();
//                       DB.setUNIQUEID(conn.rs.getString(1));
//                       DB.setDICNAME(conn.rs.getString(2));
//                       DB.setDOE(conn.rs.getString(3));
//                     
//                       
//                    
//                      
//          
//                       missingdata.add(DB);
//                       
//                      
//        }
//                                     }                            
                                     
       session.setAttribute("missingdata", missingdata);
       session.setAttribute("missingdata2", missingdata2);
         session.setAttribute("type",type);
         
         System.out.println(type);
//       String nextJSP = "/DQADuplicates.jsp";
//                                        
//                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//                                        dispatcher.forward(request,response);
////                                        conn.connect.close();
//                                        System.out.println("Disconnected from database");
        } catch (SQLException ex) {
            Logger.getLogger(getDuplicates.class.getName()).log(Level.SEVERE, null, ex);
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
