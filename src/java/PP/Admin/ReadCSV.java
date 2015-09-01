/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maureen
 */
public class ReadCSV extends HttpServlet {

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
    
     String replyback = "";
    int erroroccured = 0;
    String unuploadedrows = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
          dbConnect conn = new dbConnect();
            String itemName = "";
        erroroccured = 0;
        unuploadedrows = "";

        itemName = request.getParameter("fname");
        //____________________GET COMPUTER NAME____________________________________
        String computername = InetAddress.getLocalHost().getHostName();
//System.out.println("Computer name "+computername);



        String splits[] = computername.split("-");
        String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
String machineuname =System.getProperty("user.name");


System.out.println("Machine name is::"+machineuname);


   // dbpath=mydriv+":\\Users\\"+machineuname+"\\Downloads\\"+filename;
          String pth = mydrive + ":/PPT_UPLOADS/BACKUP/" + itemName; 
       // String pth = "C:/Users/"+machineuname+"/Downloads/" + itemName;

        
        //create a directory if not exists
        
       //  new File(dbconnpath).mkdir();
        
        System.out.println("path____________________"+pth);
            
            
           String query=""; 
            
          String csvFilename = pth;
CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
String[] row = null;
while((row = csvReader.readNext()) != null) {
    
    if(itemName.contains("indicatorachieved_Created_On_")){
    System.out.println( row[1]
              + " #  " + row[2]
             + " #  " + row[3]
              + " #  " + row[4]
              + " #  " + row[5]
              + " #  " + row[6]
            
       
            
            );
    
   String checkexistingval="Select * from indicatorachieved where district='"+row[2]+"' and financialYear='"+row[6]+"' and titleID='"+row[7]+"' and reportingPeriod='"+row[5]+"' ";                              
conn.rs3=conn.state3.executeQuery(checkexistingval);

if(conn.rs3.next()){
  query = "update indicatorachieved set menAchieved='"+row[3]+"',womenAchieved='"+row[4]+"' where resultID='"+conn.rs3.getString(1)+"'";
  
}      
else{    query = "insert into indicatorachieved(County,District,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
                                   + "VALUES('"+row[1]+"','"+row[2]+"','"+row[3]+"','"+row[4]+"','"+row[5]+"','"+row[6]+"','"+row[7]+"')";  
}
System.out.println("query    "+query);
    conn.state.executeUpdate(query);
    
    }
if(itemName.contains("indicatorachievedcombined_Created_On_")){
    System.out.println( row[1]
              + " #  " + row[2]
             + " #  " + row[3]
              + " #  " + row[4]
              + " #  " + row[5]
              + " #  " + row[6]
           );

String checkexistingval="Select * from indicatorachievedcombined where  district='"+row[2]+"' and financialYear='"+row[5]+"' and titleID='"+row[6]+"' and reportingPeriod='"+row[4]+"' ";                              
conn.rs3=conn.state3.executeQuery(checkexistingval);

if(conn.rs3.next()){
  query = "update indicatorachievedcombined set totalAchieved='"+row[3]+"' where resultID='"+conn.rs3.getString(1)+"'";
  
}      
else{
    
    query = "insert into indicatorachievedcombined(county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                                   + "VALUES('"+row[1]+"','"+row[2]+"','"+row[3]+"','"+row[4]+"','"+row[5]+"','"+row[6]+"')";  
}
  System.out.println("query    "+query);
    conn.state.executeUpdate(query); 

}
if(itemName.contains("indicatoractivities_Created_On_")){
      System.out.println( row[1]
              + " #  " + row[2]
              + " #  " + row[3]
              + " #  " + row[4]
              + " #  " + row[5]
              + " #  " + row[6]
              + " #  " + row[7]
              + " #  " + row[8]
             
              + " #  " + row[11]
              + " #  " + row[12]
         );
      
      String checkexistingval="Select * from indicatoractivities where  DistrictID='"+row[4]+"'and activityTitle='"+row[5]+"' and subtotals='"+row[11]+"'and FinancialYear='"+row[12]+"' and titleID='"+row[2]+"' and Quarter='"+row[13]+"' ";                              
conn.rs3=conn.state3.executeQuery(checkexistingval);

if(conn.rs3.next()){
  query = "update indicatoractivities set activityTitle='"+row[5]+"',startDate='"+row[7]+"',endDate='"+row[8]+"', subtotals='"+row[11]+"' where activityID='"+conn.rs3.getString(1)+"'";
  
}      
else{
    
    query = "insert into indicatoractivities(unit,titleID,countyID,DistrictID,activityTitle,activityOthers,startDate,endDate,subtotals,FinancialYear,Quarter)"
                                   + "VALUES('"+row[1]+"','"+row[2]+"','"+row[3]+"','"+row[4]+"','"+row[5]+"','"+row[6]+"','"+row[7]+"','"+row[8]+"','"+row[11]+"','"+row[12]+"','"+row[13]+"')";  
}
  System.out.println("query    "+query);
    conn.state.executeUpdate(query); 
      
      
}
if(itemName.contains("indicatoractivities1_Created_On_")){
    System.out.println(row[0]
              + " # " + row[1]
              + " #  " + row[2]
              + " #  " + row[3]
              + " #  " + row[4]
              + " #  " + row[5]
              + " #  " + row[6]
              + " #  " + row[7]
              + " #  " + row[8]
              + " #  " + row[9]
              + " #  " + row[10]
              + " #  " + row[11]
             
         );
    
     String checkexistingval="Select * from indicatoractivities1 where  districtID='"+row[4]+"'and activityTitle='"+row[5]+"' and activityOthers='"+row[6]+"' and total='"+row[9]+"'and FinancialYear='"+row[10]+"' and titleID='"+row[1]+"' and Quarter='"+row[11]+"' ";                              
conn.rs3=conn.state3.executeQuery(checkexistingval);

if(conn.rs3.next()){
  query = "update indicatoractivities1 set activityTitle='"+row[5]+"',startDate='"+row[7]+"',endDate='"+row[8]+"', total='"+row[9]+"' where activityID='"+conn.rs3.getString(1)+"'";
  
}      
else{
    
    query = "insert into indicatoractivities1(titleID,unit,countyID,districtID,activityTitle,activityOthers,startDate,endDate,total,FinancialYear,Quarter)"
                                   + "VALUES('"+row[1]+"','"+row[2]+"','"+row[3]+"','"+row[4]+"','"+row[5]+"','"+row[6]+"','"+row[7]+"','"+row[8]+"','"+row[9]+"','"+row[10]+"','"+row[11]+"')";  
}
  System.out.println("query    "+query);
    conn.state.executeUpdate(query); 
    
}



}
//...
csvReader.close();
        } finally {            
            out.close();
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
            Logger.getLogger(ReadCSV.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReadCSV.class.getName()).log(Level.SEVERE, null, ex);
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
