/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scripts;

import PP.Admin.dbConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emmanuel E
 */
public class insertquarterlytargets extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            dbConnect conn= new dbConnect();
            String currentyear="2016";
            String skipquarter="Q1";
            
            if(request.getParameter("year")!=null){
            currentyear=request.getParameter("year");
            }
            
             if(request.getParameter("quarter")!=null){
            skipquarter=request.getParameter("quarter");
                                                      }
            
            String getannualtargets="select county_id,district_id,indicator_id,year,target_men,target_women,target_combined,table_type,percentage,tableIdentifier,cumulative_chooser from yearly_targets join indicatortitles on indicatortitles.titleID=yearly_targets.indicator_id where year='"+currentyear+"'";
            conn.rs=conn.state.executeQuery(getannualtargets);
            while(conn.rs.next()){
            //----------------------------------
                
                 int totaltarget=0;
                  int target_women=0;
                   int target_men=0;
         String county=conn.rs.getString("county_id");
         String district=conn.rs.getString("district_id");
         String indicator=conn.rs.getString("indicator_id");
         String tableidentifier=conn.rs.getString("tableIdentifier");
         
         if(conn.rs.getString("target_men")!=null && !conn.rs.getString("target_men").equalsIgnoreCase("null")){
          target_men=conn.rs.getInt("target_men");
         }
         if(conn.rs.getString("target_women")!=null && !conn.rs.getString("target_women").equalsIgnoreCase("null")){
          target_women=conn.rs.getInt("target_women");
         }
         if(conn.rs.getString("target_combined")!=null && !conn.rs.getString("target_combined").equalsIgnoreCase("null") ){
          totaltarget=conn.rs.getInt("target_combined");
         }
         String table_type=conn.rs.getString("table_type");
         
         
         int qtrylytarget_male=0;
         int qtrylytarget_female=0;
         int qtrylytarget_total=0;
         
         if(target_men>0 || target_women>0 || totaltarget > 0){
             
                 int target_malediff=0;
                 int target_femalediff=0;
                 int target_totaldiff=0;
             
             if(conn.rs.getString("percentage").equals("1") ||conn.rs.getString("cumulative_chooser").equals("Last Reported") || conn.rs.getString("cumulative_chooser").equals("OLMIS")){
           qtrylytarget_male=target_men;
         qtrylytarget_female=target_women;
          qtrylytarget_total=totaltarget;
                                                             }
             else {
             
                 
         qtrylytarget_male=new Integer(target_men/4);
         qtrylytarget_female=new Integer(target_women/4);
         qtrylytarget_total=new Integer(totaltarget/4);     
            
                 target_malediff=(qtrylytarget_male*4) - target_men;
                  target_femalediff=(qtrylytarget_female*4)-target_women;
                  target_totaldiff=(qtrylytarget_total*4)-totaltarget;
              
         //if difference is negative, then the deduct that value from original value, otherwise add
                
                 
              System.out.println((qtrylytarget_male*4)+" ==  "+target_men+" __"+target_malediff);
                 System.out.println((qtrylytarget_female*4)+" ==  "+target_women+"__"+target_femalediff);
                 System.out.println((qtrylytarget_total*4)+" ==  "+totaltarget+"__"+target_totaldiff);
             }
         
        String checkexistance=" select * from quartely_targets where quarter!='"+skipquarter+"' and indicator_id='"+indicator+"' and year='"+currentyear+"' and county_id='"+county+"' and district_id='"+district+"'";     
             
             conn.rs1=conn.state1.executeQuery(checkexistance);
         if(conn.rs1.next()){
         //do an update
         for(int a=1;a<=4;a++){
             if(!skipquarter.equals("Q"+a)){
             //add the targets difference in the last quarter
                 String qter="Q"+a;
            if(qter.equals("Q4") && !skipquarter.equals("Q4"))
            {  
            
              qtrylytarget_male=qtrylytarget_male+(target_malediff);
            qtrylytarget_female=qtrylytarget_female+(target_femalediff);
            qtrylytarget_total=qtrylytarget_total+(target_totaldiff);
            
            }
            else if(qter.equals("Q3") && skipquarter.equals("Q4")) {
              qtrylytarget_male=qtrylytarget_male+(target_malediff);
            qtrylytarget_female=qtrylytarget_female+(target_femalediff);
             qtrylytarget_total=qtrylytarget_total+(target_totaldiff);
            
            
            }
             String updatecode="update quartely_targets set target_men='"+qtrylytarget_male+"', target_women='"+qtrylytarget_female+"' , target_combined='"+qtrylytarget_total+"' where quarter='Q"+1+"' and  indicator_id='"+indicator+"' and year='"+currentyear+"' and county_id='"+county+"' and district_id='"+district+"' ";
                 System.out.println(updatecode);
                 conn.state1.executeUpdate(updatecode);
             }
             }
             
         }
         else {
         //insert
             
          for(int a=1;a<=4;a++){
             if(!skipquarter.equals("Q"+a)){
             //add the targets difference in the last quarter
                 String qter="Q"+a;
            if(qter.equals("Q4") && !skipquarter.equals("Q4")){  
            
              qtrylytarget_male=qtrylytarget_male+(target_malediff);
            qtrylytarget_female=qtrylytarget_female+(target_femalediff);
             qtrylytarget_total=qtrylytarget_total+(target_totaldiff);
            
            }
            else if(qter.equals("Q3") && skipquarter.equals("Q4")) {
              qtrylytarget_male=qtrylytarget_male+(target_malediff);
            qtrylytarget_female=qtrylytarget_female+(target_femalediff);
             qtrylytarget_total=qtrylytarget_total+(target_totaldiff);
            
            
            }
             String insertcode="insert into quartely_targets (county_id,district_id,indicator_id,year,quarter,target_men,target_women,target_combined,table_type) "
                     + " values ('"+county+"','"+district+"','"+indicator+"','"+currentyear+"','Q"+a+"','"+qtrylytarget_male+"','"+qtrylytarget_female+"','"+qtrylytarget_total+"','"+tableidentifier+"')";
             conn.state1.executeUpdate(insertcode);
             }
             }
         
         }
         
         
         }
         
            }
            out.println("<font color='green'>Data Imported Successfully!</font>");
           
            if(conn.rs!=null){conn.rs.close();}
            if(conn.rs1!=null){conn.rs1.close();}
            if(conn.state!=null){conn.state.close();}
            if(conn.state1!=null){conn.state1.close();}
            
        } finally {
            out.close();
        }
        
        response.sendRedirect("setquarterlytargets.jsp");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(insertquarterlytargets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(insertquarterlytargets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
