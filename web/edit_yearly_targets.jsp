<%-- 
    Document   : edit_yearly_targets
    Created on : Jan 20, 2014, 10:49:45 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
        <link rel="shortcut icon" href="images/pptlogo.png"/>
             <link rel="stylesheet" href="select2/css/select2.css">
<!--    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>-->
    <title>Edit yearly targets</title>
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
         <script src="js/jquery-1.7.2.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
        
         <script src="select2/js/select2.js"></script>
       
          <script type="text/javascript">
 $(document).ready(function(){
 $('select').select2();    
 });   
</script>
         
        <script type="text/javascript">
            
            
function check_existence(){
    var indicator_id=document.getElementById("indicator_id").value;
    var year=document.getElementById("year").value;
//    alert("called")


    if(indicator_id!="" && year!=""){
//        alert(" here we are")
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
xmlhttp.open("POST","load_edit_yearly_target?indicator_id="+indicator_id+"&&year="+year,true);
xmlhttp.send();
}
if (indicator_id==""  || year==""){
    
    document.getElementById("loads").innerHTML="<font color=\"red\" size=\"4px;\" style=\"margin-left: 400px;\">Please Choose Both an Indicator and the year.</font>";
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
String all_indicator_selector="SELECT * FROM indicatortitles where active='yes'";
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
    <div style="clear:both ;margin-top: 100px;">
      <h3 style="text-align: center;">Edit the Yearly project target  for each indicator.</h3>
    <form action="save_edited_yearly_targets" method="post" style="margin-top: 0px; ">
        <input type="hidden" name="valuer_ex" id="valuer_ex" value="0">
   <table cellpadding="2px" cellspacing="3px" border="1px" class=""  style="background: #ccccff; max-width: 800px; margin-left: 200px">
<tr>
   <td>Choose Indicator : <select name="indicator_id" id="indicator_id" style="max-width: 634px;" onchange="return check_existence();"><%=indicator_details%></select></td>
<td>Choose Year : <select id="year" name="year" style='width: 120px;' onchange="return check_existence();">
        <option value=''>Choose Year</option>
        <option value='2010'>2010</option>
        <option value='2011'>2011</option>
        <option value='2012'>2012</option>
        <option value='2013'>2013</option>
        <option value='2014'>2014</option>
        <option value='2015'>2015</option>
        <option value='2016'>2016</option>
        <option value='2017'>2017</option>
        <option value='2018'>2018</option>
        <option value='2019'>2019</option>
        <option value='2020'>2020</option>
    </select></td>
</tr>
 </table>
       <p id="loads" style="font-size: 10px;"></p>                
     </form> 
    
       
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>

