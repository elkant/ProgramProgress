<%-- 
    Document   : set_project_targets
    Created on : Jan 16, 2014, 4:20:16 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="PP.Admin.dbConnect"%>
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
        <script type="text/javascript">
function check_existence(){
    var indicator_id=document.getElementById("indicator_id").value;
//    alert("called")


    if(indicator_id!=""){
         var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("loads").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_edit_indicator?indicator_id="+indicator_id,true);
xmlhttp.send();
}
if (indicator_id==""){
    
    document.getElementById("loads").innerHTML="<font color=\"red\" size=\"4px;\" style=\"margin-left: 400px;\">Please Choose an Indicator.</font>";
}
}
function load_districts(county){
 var current_dets=county.value;
 var curr=current_dets.split(",");
 var current_row=curr[1];
 var current_county=curr[0];
 var xmlhttp;
// alert("current_row ;;;;"+current_row)
var county=document.getElementById("county_id"+current_row).value;
 
if(current_county!="0"){
 document.getElementById("district_id"+current_row).required=true;
}
if(current_county=="0"){
  document.getElementById("district_id"+current_row).required=false;  
}
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("district_id"+current_row).innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_districts?current_county="+current_county,true);
xmlhttp.send();   
}
        </script>
<body onload="load_counties();">
   
    
    
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

<%
dbConnect conn = new dbConnect();
String indicator_details="<option value=\"\">Choose Indicator</option>";
String all_indicator_selector="SELECT * FROM indicatortitles";
conn.rs=conn.state.executeQuery(all_indicator_selector);
while(conn.rs.next()){
  indicator_details+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(3)+"</option>";  
}

%>
    

             
                   <%
                if (session.getAttribute("AccessLevel") != null) {

                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
            
             <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
             
                    <%} else  {%>
            
             <%@include file="menu/clerk_menu.html" %>

            <%}

            } else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
            <!--=====================================================================================--> 
    <div style="clear:both ;margin-top: 100px;">
      <h3 style="text-align: center;">Edit the Overall project target and Baseline for each indicator.</h3>
    <form action="save_edited_overall_target" method="post" style="margin-top: 0px; ">
        <input type="hidden" name="valuer_ex" id="valuer_ex" value="0">
   <table cellpadding="2px" cellspacing="3px" border="1px" class=""  style="background: #ccccff; max-width: 800px; margin-left: 200px">
<tr>
   <td>Choose Indicator  <select name="indicator_id" id="indicator_id" style="max-width: 634px;" onchange="return check_existence();"><%=indicator_details%></select></td></tr>
 </table>
   <table cellpadding="2px" cellspacing="3px" border="1px" class=""  style="background: #ccccff; max-width: 800px; ">
       <p id="loads" style="font-size: 10px;"></p>            
</table>    
     </form> 
    
       
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>

