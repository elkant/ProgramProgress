/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PPMP;

import PP.Admin.dbConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
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
public class transferppmpdata_2013 extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
       /* TODO output your page here. You may use following sample code. */
             dbConnect conn= new dbConnect(); 
            String mainquery=" SELECT titleID,Year_Name as Year,Quarter4 as Q1,Quarter1 as Q2,Quarter2 as Q3,Quarter3 as Q4,tableIdentifier,active FROM ppmp.master_record " +
" join ppmp.years on ppmp.years.Year_ID=ppmp.master_record.Year_ID " +
" join programprogress.indicatortitles on indicatortitles.ppmpid=ppmp.master_record.I_ID where Year_Name between '2011' and '2013' ";
            
     conn.rs=conn.state.executeQuery(mainquery);
     while (conn.rs.next()){
         
        
         
         String indicatorid=conn.rs.getString("titleID");
         String Year=conn.rs.getString("Year");
         String Q1=conn.rs.getString("Q1").replace("%", "");
         String Q2=conn.rs.getString("Q2").replace("%", "");
         String Q3=conn.rs.getString("Q3").replace("%", "");
         String Q4=conn.rs.getString("Q4").replace("%", "");
         String tableindentifier=conn.rs.getString("tableIdentifier");
         String active=conn.rs.getString("active");
         String quarterlyvalues[]={Q1,Q2,Q3,Q4};
        System.out.println("indic_"+conn.rs.getString("titleID")+" Year :"+Year+" Q 1 "+Q1);
        
         String insertqry="";
        if(tableindentifier.equals("1")){
           //by gender indicatorachieved           
        
            
             for(int a=0;a<4;a++){
                 
                 if(!quarterlyvalues[a].equalsIgnoreCase("Survey")){
            String period="Q"+(a+1);
      //NB for this indicators, i have added all data to men, if correct data from 2015 will have to be reviewed in the ppmt, then its not accurate but in ppmp it is accurate      
        insertqry=" REPLACE INTO programprogress.indicatorachieved " +
"(resultID,county,district,menAchieved,reportingPeriod,financialYear,titleID) " +
"VALUES " +
"('"+uniqueid().trim()+"','NAKURU','Nakuru Central','"+quarterlyvalues[a]+"','"+period+"','"+Year+"','"+indicatorid+"') ";
      
        //conn.state2.executeUpdate(insertqry);
                 }
        }
          
        
        }
        
        else {
        for(int a=0;a<4;a++){
            
          
            
            
            
             if(!quarterlyvalues[a].equalsIgnoreCase("Survey")){
            String period="Q"+(a+1);
            
        insertqry=" REPLACE INTO programprogress.indicatorachievedcombined " +
"(resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID) " +
"VALUES " +
"('"+uniqueid().trim()+"','NAKURU','Nakuru Central','"+quarterlyvalues[a]+"','"+period+"','"+Year+"','"+indicatorid+"') ";
      
       // conn.state2.executeUpdate(insertqry);
        }
             
        }
            }
        
        
     }
           
            out.println("migration complete");
        } finally {
            out.close();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(transferppmpdata_2013.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(transferppmpdata_2013.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
 //====================random id functions================================
public String uniqueid() {

Calendar cal1 = Calendar.getInstance();

int year1 = cal1.get(Calendar.YEAR);
int month1 = cal1.get(Calendar.MONTH) + 1;
int date1 = cal1.get(Calendar.DAY_OF_MONTH);
int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
int min1 = cal1.get(Calendar.MINUTE);
int sec1 = cal1.get(Calendar.SECOND);
int milsec = cal1.get(Calendar.MILLISECOND);


return generateRandomNumber(800, 8000) + year1 + "" + month1 + "" + date1 + hour1 + min1 + sec1 + milsec;
}

public int generateRandomNumber(int start, int end) {
Random random = new Random();
long fraction = (long) ((end - start + 1) * random.nextDouble());
return ((int) (fraction + start));
}

    
    
}
