/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IMIS;

import DBCREDENTIALSFILE.dbconnimis;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel E
 */
public class import_from_imis extends HttpServlet {

 String startyearmonth="";
 String endyearmonth="";
 int year=0;
 String quarter="";
 String quarterno="";
 HttpSession session=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session=request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
        try {
            
            
            //static parameters for testing purpose
            year=2015;
            quarter="Q4";
            startyearmonth="201507";
            endyearmonth="201509";
            
            if(request.getParameter("quarterval")!=null){
                quarter=request.getParameter("quarterval");
                
               // String quartervals[]=quarter.split("_");
                System.out.println("___________"+quarter);
                //quarter=quartervals[2];
                
            }
            quarterno=quarter.substring(1);
            if(request.getParameter("startdate")!=null){
                startyearmonth=request.getParameter("startdate");
            }
            if(request.getParameter("enddate")!=null){
                endyearmonth=request.getParameter("enddate");
            }
            if(request.getParameter("year")!=null){
                year=Integer.parseInt(request.getParameter("year"));
                
                
               
            }
            
            
            dbconnimis imisconn= new dbconnimis();
            dbConnect ppmtconn=new dbConnect();
            
            //have a list of all valid indicators from ppmt and save them into an array
            //have a list of kepms sources and save them into an array
            //use hash table to store each indicator and its respective imis data source in mysql query format
            //The hash table should use strings
            //Hashtable<String, String> ppmt_imis_link     = new Hashtable<String, String>();
            //ppmt_imis_link.put("one", 1);
            //** Maintain a order of MEN THEN WOMEN WHEN CREATING QUERY STRINGS
            
            String sum_where="yearmonth between "+startyearmonth+" and "+endyearmonth+" ";
            String qtr_where="year="+year+" and  quarter="+quarterno+" ";
            String cumulative_where="yearmonth = "+endyearmonth+" ";
//NB; -cum means cumulative... pick datya for last month of that period
//T14D_M (20)-cum	T14D_F(20)-cum	C11D_M(17)	C11D_F(17)	C21D_M(18)-cum	C21D_F(18)-cum	C22D_M(19)-cum	C22D_F(19)-cum	T12D_M(22)-cum	T12D_F(22)-cum	CurrentPG(12)
//Preg+ Enrolled in PMTCT	T11D_M	T11D_F	OptionB+	StartingARTPg
//P111D_M	P111D_F
//P11D Pg Women of Known Status		P12D	P15D	ANC1st	ANC4th	Deliveries		C42DProphy731
            String ppmtindicators[]={"20","17","18","19","22","12",//worksheet one //QryHIV-cummulative
                "65","21","64","0", //QryHIV - noncummulative //11 has a problem. Its not the right indicator. Komen to say the correct indicator
                "8",//QryNonHIV1
                "10","11","12","30","31","32","14",//Qry Non-HIV2
                "113","78","23","66","93" // HV03-01 ,  HV03-13 , HV03-49 , HV03-54, MATLiveBirth
               ,"25","24","79","90","77","15","40","56"
            ,"112"}; //TB STAT, TB_ART%, TX_VIRAL,TX_UNDETECT,PMTCT_FO
            // String ppmtindicators[]={"20"};
            String imisqryparams[]={"(SUM(HV0340) + SUM(HV0342)) as T14D_M,(SUM(HV0341)+SUM(HV0343)) as T14D_F ",
                "(SUM(HV0303) + SUM(HV0305)) as C11D_M,(SUM(HV0304)+SUM(HV0306)) as C11D_F"
                    ,"(SUM(HV0315)+SUM(HV0317)) as C21D_M, (SUM(HV0316)+SUM(HV0318)) as C21D_F "
                    ,"(SUM(HV0315)+SUM(HV0317)) as C22D_M , (SUM(HV0316)+SUM(HV0318)) as C22D_F"
                    ,"(SUM(HV0335)+SUM(HV0337)) as T12D_M , (SUM(HV0336)+SUM(HV0338)) as T12D_F"
                    ,"SUM(HV0326) as CurrentPG",
                    "SUM(HV0221) as PREGPMTCT",//worksheet two begins here
                    "(SUM(HV0321)+SUM(HV0323)) as T11D_M,(SUM(HV0322)+SUM(HV0324)) as T11D_F ",
                    "SUM(HV0216) as OPTIONB",
                    "SUM(HV0326) as StartingARTPg",
                    //"(SUM(VCTClient_Tested_CM)+SUM(VCTClient_Tested_AM)+SUM(DTCB_Test_In_CM)+SUM(DTCB_Test_In_AM)+SUM(DTCB_Test_Out_CM)+ SUM(DTCB_Test_Out_AM)+ COALESCE(SUM(P51DT),0) + (ROUND((SUM(HV0228))*0.5)) ) as P111DM  , (SUM(VCTClient_Tested_CF)+SUM(VCTClient_Tested_AF)+SUM(DTCB_Test_In_CF)+SUM(DTCB_Test_In_AF)+SUM(DTCB_Test_Out_CF)+SUM(DTCB_Test_Out_AF) + (SUM(HV0204))) as P111DF",
                    "((ROUND((SUM(HV0103))*0.38)) + COALESCE(SUM(P51DT),0) + (ROUND((SUM(HV0228))*0.5))) as P111DM  , ((ROUND((SUM(HV0103))*0.62))+ (SUM(HV0204)) + (ROUND((SUM(HV0228))*0.5))) as P111DF", //new HTC
                    "SUM(HV0210) as P11D"//worksheet 4 //QryNonHIV2
                    ,"(SUM(HV0217)) as P12D"// the value  being entered is achieved using the formulae p12 numerator/p12 denominator
                    ,"(SUM(HV0217)) as P15D ",
                    "SUM(PMCTA_1stVisit_ANC) as ANC1"
                    ,"SUM(PMCTANCClients4) as ANC4",
                    "SUM(MATDeliveryT) as Deliveries"
                    ,"ROUND(((SUM(HV0244))/(SUM(HV0209)))*100) as C42DProphy"
                    ,"SUM(HV0301) as PMTCT_CTX" //worksheet 5 begins here
                    ,"(SUM(HV0309)+SUM(HV0311)) as CARE_NEW_M,(SUM(HV0310)+SUM(HV0312)) as CARE_NEW_F"
                    ,"ROUND(((SUM(HV0349))/(SUM(HV0345)))*100) as  TX_RET"
                    ," ROUND(((SUM(HV0354))/(SUM(HV0319)))*100)  as TB_SCREENED ",
                    " SUM(MATLiveBirth) as MATLiveBirth"
                   ,"SUM(positive) as TBSTAT"                                          
                   ,"ROUND(((SUM(art_numerator))/(SUM(positive)))*100)  as TBART"  
                    ,"SUM(numerator_vi) as TX_VIRAL"
                    ,"SUM(numerator_un) as TX_UNDETECT"
                    ,"SUM(numerator) as PMTCT_FO"
            ,"ROUND(((SUM(HV0228)-SUM(HV0232))/(SUM(HV0228)))*100)  as P17N","SUM(P51DT) as P51DT","SUM(HV0510) AS HV0510M, SUM(HV0511) as HV0511F",
            "ROUND(((SUM(HV0232))/(SUM(HV0209)))*100) as  PMTCT_EID"};
            //all tables that are source of data
            String imistablesjoin[]={"moh731","moh731","moh731","moh731","moh731","moh731"//QryHIV - cummulative
                    ,"moh731","moh731","moh731","moh731"//QryHIV - noncummulative
                    ,"moh731 left join vmmc on moh731.id=vmmc.tableid"//QryNonHIV1
                    ,"moh731","moh731","moh731","moh711_new","moh711_new","moh711_new","moh731"
                    ,"moh731","moh731","moh731","moh731","moh711_new"//new indicators 201602
                    ,"tb_stat_art","tb_stat_art"
                    ,"viral_load","viral_load","pmtct_fo"
            ,"moh731","vmmc","moh731","moh731"}; //p17n
            String imismaintable[]={"moh731","moh731","moh731","moh731","moh731","moh731"//QryHIV - cummulative
                    ,"moh731","moh731","moh731","moh731"//QryHIV - noncummulative
                    ,"moh731"//QryNonHIV1
                    ,"moh731","moh731","moh731","moh711_new","moh711_new","moh711_new","moh731"//QryNonHIV2
                     ,"moh731","moh731","moh731","moh731","moh711_new"//new indicators 201602
                    ,"tb_stat_art","tb_stat_art"
                     ,"viral_load","viral_load","pmtct_fo"
            ,"moh731","vmmc","moh731","moh731"}; //p17n
//continue from here
            String ppmttable_ar[]={"indicatorachieved","indicatorachieved","indicatorachieved","indicatorachieved","indicatorachieved","indicatorachievedcombined" //QryHIV - cummulative
                    ,"indicatorachievedcombined","indicatorachieved","indicatorachievedcombined","indicatorachievedcombined"//QryHIV - noncummulative
                    ,"indicatorachieved"//QryNonHIV1
                    ,"indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined" //QryNonHIV2
                    ,"indicatorachievedcombined","indicatorachieved" ,"indicatorachievedcombined" ,"indicatorachievedcombined" ,"indicatorachievedcombined"//201602 new indicators
                    ,"indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined","indicatorachievedcombined"
             ,"indicatorachievedcombined","indicatorachievedcombined","indicatorachieved","indicatorachievedcombined"};
            String imiswhere[]={cumulative_where+" and art=1 ",cumulative_where+" and art=1 ",cumulative_where+" and art=1 ",cumulative_where+" and art=1 ",cumulative_where+" and art=1 ",cumulative_where+" and art=1 " //QryHIV - cummulative
                    ,sum_where,sum_where,sum_where,cumulative_where+" and art=1 "//QryHIV - noncummulative
                    ,"moh731."+sum_where//QryNonHIV1
                    ,sum_where,sum_where,sum_where,sum_where,sum_where,sum_where,sum_where//QryNonHIV2
                    ,sum_where,sum_where,sum_where,sum_where,sum_where //201602
                    ,qtr_where,qtr_where//201603 tbs
                    ,qtr_where,qtr_where,qtr_where//201603 tbs
            ,sum_where,sum_where,sum_where,sum_where};//p1.7n //vmmc // gendgbv
            //indicatorachievedcombined
            //indicatorachieved
            //insertachieved(dbconnimis imisconn,dbConnect ppmtconn,String imisparams,String ppmtid, String tablesjoin,String maintable,String ppmttable)
            //loop through all the indicators and call a function
            for(int a=0;a<ppmtindicators.length;a++){
                
                
                insertachieved(imisconn,ppmtconn,imisqryparams[a],ppmtindicators[a],imistablesjoin[a],imismaintable[a],ppmttable_ar[a],imiswhere[a]);
                
                
            }
            
            
            //remeber to close connection
            
            
           
            
            if(imisconn.rs!=null){ imisconn.rs.close();}
            if(imisconn.state!=null){ imisconn.state.close();}
            if(ppmtconn.rs1!=null){ ppmtconn.rs1.close();}
            if(ppmtconn.state1!=null){ ppmtconn.state1.close();}
            if(ppmtconn.state3!=null){ ppmtconn.state3.close();}
                  

        } catch (SQLException ex) {
         Logger.getLogger(import_from_imis.class.getName()).log(Level.SEVERE, null, ex);
     }
        
         try {
                
              session.setAttribute("imisimportreply","Data synced successfully");
                                response.sendRedirect("imisimport.jsp");

            } finally {
                out.close();
            }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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


public void insertachieved(dbconnimis imisconn,dbConnect ppmtconn,String imisparams,String ppmtid, String tablesjoin,String maintable,String ppmttable,String imiswhere){
     try {
         //select from imis for the selected indicator
//here you get data by gender
         //user should define the startyearmonth and the end yearmonth
         //maintable will be used in getting SubPartnerID form the correct table to avoid a case of ambiguos in query
         //tablesjoin is used to hold table joins if data is coming from more than one table and a single table name if data is coming from one table         
        //JOIN subpartnera ON moh711.SubPartnerID=subpartnera.SubPartnerID JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON district.CountyID=county.CountyID
        //imispramas could be a sum of table columns or a simple column if data is for accumulatives such as art ever         
         String getimis="Select county,DistrictNom , "+imisparams+" from "+tablesjoin+" JOIN ( subpartnera JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON district.CountyID=county.CountyID ) ON "+maintable+".SubPartnerID=subpartnera.SubPartnerID  where "+imiswhere+" group by DistrictNom";
         
         System.out.println("Imis get code:"+getimis);
         
         String imisparamsarray[]=imisparams.split(",");
         imisconn.rs=imisconn.state.executeQuery(getimis);
         while(imisconn.rs.next()){
         //now read the values fetched from imis into ppmt
         //first check if the data exists in the ppmt tables for the selected year and quarter
             String countyft=imisconn.rs.getString(1);
             String districtft=imisconn.rs.getString(2);
             
         String checkexistingval="Select resultID from "+ppmttable+" where  district='"+districtft+"' and financialYear='"+year+"' and titleID='"+ppmtid+"' and reportingPeriod='"+quarter+"' ";                              
ppmtconn.rs1=ppmtconn.state1.executeQuery(checkexistingval);

//check if the table is indicatorachieved or indicatorachievedcombined

//==============INDICATOR ACHIEVED ===========================
if(ppmttable.equalsIgnoreCase("indicatorachieved")){
//there is male achieved and female achieved
 //NB now  i expect imis to return county, district , maleachieved , femaleachieved   
String menachieved=imisconn.rs.getString(3);
String femaleachieved=imisconn.rs.getString(4);

//skip if men acchieved is null

if(menachieved==null|| menachieved.equals("null")||femaleachieved==null||femaleachieved.equals("null")){
    
    //do nothing
}
    else { //do 

String query="";
    if(ppmtconn.rs1.next()){
  query = "update indicatorachieved set menAchieved='"+menachieved+"',womenAchieved='"+femaleachieved+"' where resultID='"+ppmtconn.rs1.getString(1)+"'";
  
                           }      
    else {
    
    query = "insert into indicatorachieved(resultID,county,district,menAchieved,womenAchieved,reportingPeriod,financialYear,titleID)"
                                   + "VALUES('"+uniqueid().trim()+"','"+countyft+"','"+districtft+"','"+menachieved+"','"+femaleachieved+"','"+quarter+"','"+year+"','"+ppmtid+"')";  
    }
    
    System.out.println(query);
   ppmtconn.state3.executeUpdate(query);
   
}
}








//==============INDICATOR ACHIEVED COMBINED===========================

else if(ppmttable.equalsIgnoreCase("indicatorachievedcombined")){
//no male and female 
 //NB now  i expect imis to return county, district , totalachieved   
    String totalachieved=imisconn.rs.getString(3);

if(totalachieved==null|| totalachieved.equals("null")){
//do nothing
}

else {
String query="";
    if(ppmtconn.rs1.next()){
  query = "update indicatorachievedcombined set totalAchieved='"+totalachieved+"' where resultID='"+ppmtconn.rs1.getString(1)+"'";
  
                           }      
    else {
    
    query = "insert into indicatorachievedcombined (resultID,county,district,totalAchieved,reportingPeriod,financialYear,titleID)"
                                   + "VALUES('"+uniqueid().trim()+"','"+countyft+"','"+districtft+"','"+totalachieved+"','"+quarter+"','"+year+"','"+ppmtid+"')";  
    }
    System.out.println(query);
   ppmtconn.state3.executeUpdate(query);
}//end of else  
}
else {
//there mus av been an error on ppmt table passed

    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ WRONG PPMT TABLE NAME PASSED. NO INSERT/UPDATE CONDUCTED");
}


         
         }
         
         
     } catch (SQLException ex) {
         Logger.getLogger(import_from_imis.class.getName()).log(Level.SEVERE, null, ex);
     }
    
}







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
