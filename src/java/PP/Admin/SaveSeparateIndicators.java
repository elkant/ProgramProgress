/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Date;
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
public class SaveSeparateIndicators extends HttpServlet {

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
     String county = "";
           String[] district;
           String activity = "";
           String startdate = "";
           String enddate = "";
           String women  = "";
           String men = "";
           String subtotal = "";
            String dist="";
            String achievedw="";
            String achievedm="";
            String baselineid="";
            int activitys=0;
             String financial= "";
                String quarter= "";
                String districts="";
                 String baselinemen="",baselinewomen="",QtargetMen="",QtargetWomen="",endTargetWomen="",endTargetMen="";
//                   int count=0;
           dbConnect conn = new dbConnect();
           HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          session=request.getSession();
        
          String computername = InetAddress.getLocalHost().getHostName();
        String Replytext="";
        
        
        try {
           
              String unit = request.getParameter("unit");
              session = request.getSession(true);
        session.getAttribute("title");
        String title = (String)session.getAttribute("title");
        //String title = request.getParameter("title");
        
        
        
          county = "";
           
            activity = "";
            startdate = "";
            enddate = "";
            women  = "";
            men = "";
            subtotal = "";
             dist="";
             achievedw="";
             achievedm="";
             baselineid="";
             activitys=0;
              financial= "";
                 quarter= "";
                 districts="";
                  baselinemen="";
                  baselinewomen="";
                QtargetMen="";
                QtargetWomen="";
                endTargetWomen="";
                endTargetMen="";
//      
        
        
            System.out.println("title"+title);
            String activityOthers="";
            String num1="";
            int num=0;
            if(request.getParameter("h")!=null && request.getParameter("h")!=""){
           num1 =  request.getParameter("h");
              num = Integer.parseInt(num1);}
           System.out.println("bashxdvsa"+num1);
          
           System.out.println("num1"+num);
           int number = num;
           System.out.println("number"+number);
for(int i=0 ;i<number;i++)
{   dist="";
          System.out.println(i);
          if(request.getParameter("county_"+i)!= null && request.getParameter("county_"+i)!= "")
          {
           county = request.getParameter("county_"+i);
}
          if(request.getParameterValues("district_"+i)!= null && !(request.getParameterValues("district_"+i).equals(""))){
           district = request.getParameterValues("district_"+i);
           for(int y=0;y<district.length; y++){
           dist+=district[y]+"_";
            System.out.println(dist);
            System.out.println(district[y]);
           }}
          if(request.getParameter("activity_"+i)!=null && request.getParameter("activity_"+i)!=""){
           activity = request.getParameter("activity_"+i);}
           
          if(request.getParameter("activityOthers_"+i)!=null && request.getParameter("activityOthers_"+i)!=""){
           activityOthers = request.getParameter("activityOthers_"+i);}
          if(request.getParameter("startdate_"+i)!=null && request.getParameter("startdate_"+i)!=""){
            startdate = request.getParameter("startdate_"+i);}
          if(request.getParameter("enddate_"+i)!=null && request.getParameter("enddate_"+i)!="")
          {
           enddate = request.getParameter("enddate_"+i);}
           if(request.getParameter("women_"+i)!=null && request.getParameter("women_"+i)!=""){
            women  = request.getParameter("women_"+i);}
           if(request.getParameter("men_"+i)!=null && request.getParameter("men_"+i)!=""){
            men = request.getParameter("men_"+i);}
            if(request.getParameter("subtotal_"+i)!=null && request.getParameter("subtotal_"+i)!=""){
            subtotal = request.getParameter("subtotal_"+i);}
            if(request.getParameter("financial")!=null && request.getParameter("financial")!=""){
            financial = request.getParameter("financial");
            System.out.println(financial);}
            if(request.getParameter("quarter")!=null && request.getParameter("quarter")!=""){
            quarter = request.getParameter("quarter");}
            
               
              
           System.out.println(unit);
           System.out.println("county"+county);
           System.out.println("district"+dist);
           System.out.println("activity"+activity);
           System.out.println("startdate"+startdate);
           System.out.println("endate"+enddate);
           System.out.println("women"+women);
           System.out.println("men"+men);
           System.out.println("subtotal"+subtotal);
           System.out.println("financial"+financial);
           System.out.println("quarter"+quarter);
           if(county!=null && !county.equals("") && startdate!=null && !enddate.equals("") && enddate!=null && !enddate.equals("")){
               
               
       String query = "INSERT INTO indicatoractivities(unit,titleID,countyID,DistrictID,activityTitle,activityOthers,startDate,endDate,men,women,subtotals,FinancialYear,Quarter)"
+ " VALUES ('"+unit+"','"+title+"','"+county+"','"+dist+"','"+activity+"','"+activityOthers+"','"+startdate+"','"+enddate+"','"+men+"','"+women+"','"+subtotal+"','"+financial+"','"+quarter+"')";    
            System.out.println(query);
                try {
                     conn.rs6=conn.state6.executeQuery("select * from indicatoractivities where titleID='"+title+"' and activityTitle='"+activity+"' and activityTitle!='"+1031+"' and DistrictID='"+dist+"' and Quarter='"+quarter+"' and FinancialYear='"+financial+"'");
                        if(!conn.rs6.next()){
                    
                                               conn.state.executeUpdate(query);
                                                if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted  data for titleID is "+title+" and Quarter is "+quarter+" and Financial Year is "+financial+" for activities',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);}
                        }
                        else{
                        
                        Replytext+=" Data for District "+dist+" for Quarter "+quarter+"  "+financial +" is already added <br/>";
                        
                        System.out.println(Replytext);
                        
                        }
                                              
                                           } catch (SQLException ex) {
                                               Logger.getLogger(IndicatorActivitiesServlet.class.getName()).log(Level.SEVERE, null, ex);
                                                System.out.println("ERROR"+ex.toString());
                                           }
                }
}


          String count="";
                //count=session.getAttribute("test_count1").toString();
                int counter=0;
               int resultcounts1=0;
                int newresultrows1=0;
                        
                         if(request.getParameter("resultcounts1")!=null && !"".equals(request.getParameter("resultcounts1"))){
            counter = Integer.parseInt(request.getParameter("resultcounts1"));}
                System.out.println("count for K   "+count);
                String dists="";
                String lowercounty="";
               int newresultrows=0;
                int resultcount=0;
                if(!request.getParameter("resultcount").equals("") && request.getParameter("resultcount")!=null){
                resultcount=Integer.parseInt(request.getParameter("resultcount"));
                }
                if(!request.getParameter("newresultrows").equals("") && request.getParameter("newresultrows")!=null){
                newresultrows=Integer.parseInt(request.getParameter("newresultrows"));
                }
                System.out.println("newresultrows "+newresultrows);
                System.out.println("resultcount"+resultcount);
           for(int k=0 ;k<=newresultrows;k++)
{  
        if(request.getParameter("newachievedm_"+k)!=null && !request.getParameter("newachievedm_"+k).equals("")){
            achievedm = request.getParameter("newachievedm_"+k);
        System.out.println(achievedm);}
            if(request.getParameter("newachievedw_"+k)!=null && !request.getParameter("newachievedw_"+k).equals("")){
            achievedw = request.getParameter("newachievedw_"+k);
             System.out.println("acheived w"+achievedw);}
           
            if(request.getParameter("newdistrict__"+k)!=null && !request.getParameter("newdistrict__"+k).equals("")){
            dists = request.getParameter("newdistrict__"+k);
           }
            
            
              System.out.println("Districts "+request.getParameter("district__"+k));
            
            if(request.getParameter("newcounty__"+k)!=null && !request.getParameter("newcounty__"+k).equals("")){
            lowercounty = request.getParameter("newcounty__"+k);
             System.out.println("dists "+dists);}
            
            
            if(request.getParameter("baselineid_"+k)!=null && !request.getParameter("baselineid_"+k).equals("")){
            baselineid = request.getParameter("baselineid_"+k);
             System.out.println(baselineid);}
            if(request.getParameter("financial")!=null && !request.getParameter("financial").equals("")){
            financial = request.getParameter("financial");
            System.out.println(financial);}
            if(request.getParameter("quarter")!=null && !request.getParameter("quarter").equals("")){
            quarter = request.getParameter("quarter");}
String querys="";
            if(newresultrows>0){
                
//  querys = "Update indicatorachieved SET menAchieved='"+achievedm+"',womenAchieved='"+achievedw+"' WHERE reportingPeriod='"+quarter+"' AND financialYear='"+financial+"' AND titleID='"+title+"' AND District='"+dists+"' ";
// System.out.println("UPDATE"+querys);
//            }
//else{
 //querys = "INSERT INTO indicatorachieved SET menAchieved='"+achievedm+"',womenAchieved='"+achievedw+"',reportingPeriod='"+quarter+"',financialYear='"+financial+"',titleID='"+title+"',District='"+dists+"',County='"+lowercounty+"'";
 querys = "INSERT INTO indicatorachieved  (menAchieved,womenAchieved,reportingPeriod,financialYear,titleID,District,County) values ('"+achievedm+"','"+achievedw+"','"+quarter+"','"+financial+"','"+title+"','"+dists+"','"+lowercounty+"')";

            System.out.println("INSERT"+querys);
            // System.out.println(querys);
                try {                                              
if(!achievedm.equals("")&&!achievedw.equals("")){
    
    
    conn.rs6=conn.state6.executeQuery("select * from indicatorachieved where reportingPeriod='"+quarter+"' and financialYear='"+financial+"'and District='"+dists+"' and titleID='"+title+"'");
    
    if(!conn.rs6.next()){
     conn.state.executeUpdate(querys); 
    
        if (session.getAttribute("Username") != null) {
         Date dat= new Date();
      String inserter = "insert into taskauditor set host_comp='" + computername + "' , action='Inserted results data for titleID is "+title+" and Quarter is "+quarter+" and Financial Year is "+financial+"',time='" + dat + "',username='" + session.getAttribute("Username") + "'";

                    conn.state2.executeUpdate(inserter);} 
    }
    else{
    Replytext+=" Data for indicator "+title+" District "+dists +" For Quarter "+quarter+" "+financial+" is already added";
    }                           
                                   
}
}
                catch(SQLException ex){
                    System.out.println(ex.toString());
                
                }
           // END OF UPDATE FOR INDICATOR ACHIEVED 
           
                  
 if( request.getParameter("baselinemen_"+k)!= null){       
 baselinemen = request.getParameter("baselinemen_"+k);
 System.out.println("baselinemen   "+baselinemen);
 }
if(request.getParameter("baselinewomen_"+k)!= null){
 baselinewomen = request.getParameter("baselinewomen_"+k);
 System.out.println("baselinewomen "+baselinewomen);
}
if( request.getParameter("endTargetMen_"+k)!= null){
endTargetMen = request.getParameter("endTargetMen_"+k);
System.out.println("endTargetMen  "+ endTargetMen);
}
if( request.getParameter("endTargetWomen_"+k)!= null){
 endTargetWomen = request.getParameter("endTargetWomen_"+k);
System.out.println("endTargetWomen  "+ endTargetWomen);
}
if(request.getParameter("QtargetMen_"+k)!= null){
 QtargetMen = request.getParameter("QtargetMen_"+k);
System.out.println("QtargetMen  "+ QtargetMen);
}
if( request.getParameter("QtargetWomen_"+k)!= null){
 QtargetWomen = request.getParameter("QtargetWomen_"+k);
System.out.println("QtargetWomen  "+ QtargetWomen);

} if(request.getParameter("districts_"+k)!=null ){
            districts = request.getParameter("districts_"+k);
             System.out.println(districts);}

if(request.getParameter("QtargetWomen_"+k)!= null  && request.getParameter("QtargetMen_"+k)!= null  && request.getParameter("endTargetWomen_"+k)!= null && request.getParameter("baselinewomen_"+k)!= null){

//if(baselinewomen!=null && !baselinewomen.equals("") && baselinemen!=null && !baselinemen.equals("") && QtargetMen!=null && !QtargetMen.equals("") && QtargetWomen!=null && !QtargetWomen.equals("") && endTargetMen!=null && !endTargetMen.equals("") && endTargetWomen!=null && !endTargetWomen.equals("")){

 String query1="INSERT INTO baseline(QtargetMen,QtargetWomen,menBaseline,womenBaseline,endtargetMen,endTargetWomen,FinancialYear,Quarter,District,titleID)"
                                + "VALUES('"+QtargetMen+"','"+QtargetWomen+"','"+baselinemen+"','"+baselinewomen+"','"+endTargetMen+"','"+endTargetWomen+"','"+financial+"','"+quarter+"','"+districts+"','"+title+"')";
  conn.state.executeUpdate(query1);                               
 
 System.out.println(query1);           
}
}
             }
            
                                             
               if(Replytext.equals("")){
               Replytext="Data saved succefully";
               }                           

      session.setAttribute("replytext", Replytext);
   
             } finally { 
              response.sendRedirect("ResultsMain.jsp");
           
        }}

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
            Logger.getLogger(SaveSeparateIndicators.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SaveSeparateIndicators.class.getName()).log(Level.SEVERE, null, ex);
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
