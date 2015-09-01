<%-- 
    Document   : indicator_report
    Created on : Jan 31, 2014, 8:55:23 AM
    Author     : Geofrey Nyabuto
--%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>




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
         <link rel="shortcut icon" href="images/pptlogo.png"/>
         <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
        
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
                    <% String logStatus=(String)session.getAttribute("loggedIn");
               if(logStatus==null){
//                  response.sendRedirect("index.jsp");
     %>
     <h4>  <a class="button-1" href="index.jsp">Login</a></h4>
     <%        } else{
     %>
     <h4>   <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a></h4>
     <%
               }
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
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

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

            <%}

            } else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
    <div style="clear:both">
       <%if (session.getAttribute("replyback") != null) { %>
                                <script type="text/javascript"> 
                    alert("entered")
                    var n = noty({text: '<%=session.getAttribute("replyback")%>',
                        layout: 'center',
                        type: 'Success'
//                        ,
// 
//                         timeout: 4800
                     });
                    
                </script> <%
                session.removeAttribute("replyback");
                            }

                        %>  
    <form action="indicator_results_report" method="post" style="margin-top: 100px; ">
           <h3 style="text-align: center;">INDICATOR QUARTERLY REPORTS</h3>
                      <p style="text-align: center;">Select the fields as required then click generate.</p>
    
   <br><br>
           <table style="width: 500px; margin-left: 320px;">
<tr>
    <th>Pepfar Year</th><th><select required="true" name="year" id="financial" onchange="return add_years();">
                                       <option value="">Select Pepfar Year</option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       <option value="2014">2014</option>
                                       <option value="2015">2015</option>
                               
                               </select></th>
    <th>Quarter</th><th><select name="quarter" required="true" id="quarter" disabled="true">
                                       <option value="">Select Quarter</option>
                                       <option value="Q1">Oct-Dec ()</option>
                                       <option value="Q2">Jan-March()</option>
                                       <option value="Q3">April-June()</option>
                                       <option value="Q4">July-Sept()</option>

                               </select>
                               </tr>        
        
                   <tr>
                     <th colspan="4"></th>
                 
                 </tr>                     
                 <tr>
                     <th colspan="4"><input type="submit" value="Sync PPMP DB " /></th>
                 
                 </tr>             
 </tr>                             
 </table>
     </form> 
    
       
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>

