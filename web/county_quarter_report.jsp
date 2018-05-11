<%-- 
    Document   : county_quarter_report
    Created on : Jan 16, 2014, 9:20:20 AM
    Author     : Geofrey Nyabuto
--%>

<%-- 
    Document   : 1stQuarterCountyReport
    Created on : Sep 21, 2013, 7:46:26 AM
    Author     : The Aluodos
--%>

<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! dbConnect conn = new dbConnect(); %>



<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
        <link rel="shortcut icon" href="images/pptlogo.png"/>
<!--    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>-->
    <title>Program Progress</title>
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
         <script src="js/jquery-1.7.2.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript">
            function add_years(){
                var current_year=document.getElementById("financial").value;
//                var quarter=document.getElementById("quarter").value;
//alert("previous year : "+prev_year)
if(current_year!=""){
    var prev_year=current_year-1;
document.getElementById("quarter").innerHTML="<option value=\"\">Select Quarter</option><option value=\"Q1\">Oct-Dec ("+prev_year +")</option><option value=\"Q2\">Jan-March("+ current_year+")</option><option value=\"Q3\">April-June("+current_year +")</option><option value=\"Q4\">July-Sept("+current_year +")</option>>";
document.getElementById("quarter").disabled=false;                
}   
       if(current_year=="")     {
 document.getElementById("quarter").disabled=true;                
            }
}
        </script>
<body>
   
    
    
<div class="example" style="width:1200px;height: 800px;">
     <div>
             
     <%
               
        String username=(String)session.getAttribute("Username");
      
               if(username==null){
                   
               } else{
     %>
               <h5>Welcome <%=username%></h5>
     <%
               }
        
 
      %>
       <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
//    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
//    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
//    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("Username")==null) {
//        response.sendRedirect("index.jsp");
    } 
%>


    


               
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("AccessLevel") != null) {

                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
            <%} else if (session.getAttribute("AccessLevel").equals("5")) {%>

            <%@include file="menu/guest_menu.html" %>
   <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
             
                    <%} else  {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}}%>
            <!--=====================================================================================--> 
    <div style="clear:both">
     
    <form action="county_reports" method="post" style="margin-top: 100px; ">
           <h3 style="text-align: center;">COUNTY QUARTERLY REPORTS</h3>
                      <p style="text-align: center;">Select the fields as required then click generate.</p>
    
   <br><br>
           <table style="width: 500px; margin-left: 320px;">
<tr>
    <th>Pepfar Year</th><th><select style="height:38px;" required="true" name="FinancialYear" id="financial" onchange="return add_years();">
                                       <option value="">Select Financial Year</option>
                                       
                                       <option value="2017">2017</option>
                                       <option value="2018">2018</option>
                                       <option value="2019">2019</option>
                               
                               </select></th>
    <th>Quarter</th><th><select style="height:38px;" name="Quarter" required="true" id="quarter" disabled="true">
                                       <option value="">Select Quarter</option>
                                       <option value="Q1">Oct-Dec ()</option>
                                       <option value="Q2">Jan-March()</option>
                                       <option value="Q3">April-June()</option>
                                       <option value="Q4">July-Sept()</option>

                               </select>
                               </tr>        
        
                      <tr ><th>County <font style="color: blue">*</font> </th>
                    <th>     <select  name="countyid" id="countyid" multiple="true" style="height:120px;width:156px;" required="true">
  <%
            String QueryDists= "SELECT countyID,countyName FROM countyview order by countyName";
                                if(conn.state.isClosed()){conn= new dbConnect();}
                                                  
				conn.rs = conn.state.executeQuery(QueryDists);
                                
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("countyID")%>'><%=conn.rs.getString("countyName")%></option>
                                                   <%
                                                      
                                                                                      }
                                
                               
                                                   %>


                                 </select></th>
                                
                </tr>                      
                 <tr>
                     <th colspan="4"><input type="submit" style="height:37px;" value="Generate Report" /></th>
                 
                 </tr>             
 </tr>                             
 </table>
     </form> 
    
       
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>

<%

         //if(conn.rs!=null){ conn.rs.close();}
        
        
         //if(conn.state!=null){ conn.state.close();}

%>