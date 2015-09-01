<%-- 
    Document   : set_project_targets
    Created on : Jan 16, 2014, 4:20:16 PM
    Author     : Geofrey Nyabuto
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
       <script type="text/javascript">
  function nullchecker(){
      var no_of_rws=document.getElementById("all_rows").value;
      
      var i=1;
           var all_indic="";
           var all_county="";
     while(i<=no_of_rws){
         var indicator=document.getElementById("indicator_id"+i).value;
         var county=document.getElementById("county_id"+i).value;

     if(indicator=="0,0,"+i && county!="0,"+i){
        alert(" Choose the Indicator at line : "+i)
          document.getElementById("indicator_id"+i).focus()
          return false;
        }
        
        if(indicator!="0,0,"+i && county=="0,"+i){
          alert(" Choose the County at line : "+i)
          document.getElementById("county_id"+i).focus()
          return false;   
        }
        if(indicator!="0,0,"+i){all_indic+=indicator;}
        if(county!="0,"+i){ all_county+=county;}
    
          i++; 
     
     }
if(all_county=="" && all_indic==""){
    
alert(" Can not save an empty set Please enter data..")
return false;    
}
     }
      
function deleteRow()
{
    var all_rows=document.getElementById("all_rows").value;
    var rws2=--all_rows
    if(rws2<0){
       rws2=0 
    }
document.getElementById('tb').deleteRow(all_rows)
document.getElementById("all_rows").value=rws2;
}

function insRow()
{
var all_rows=document.getElementById("all_rows").value;
    var rws2=++all_rows
   document.getElementById("all_rows").value=rws2;
var tbl = document.getElementById('tb');
var lastRow = tbl.rows.length;
var x=document.getElementById('tb').insertRow(lastRow)
var a=x.insertCell(0)
var y=x.insertCell(1)
var z=x.insertCell(2)
var v=x.insertCell(3)
var u=x.insertCell(4)
var w=x.insertCell(5)
var hi = 1;
y.innerHTML="<select id='indicator_id"+rws2+"' name='indicator_id"+rws2+"' style='width: 170px;' onchange='load_entries(this);'></select>"
z.innerHTML="<select id='county_id"+rws2+"' name='county_id"+rws2+"' value=''  style='width: 150px;' onchange='load_districts(this);'></select>"
v.innerHTML="<select id='district_id"+rws2+"' name='district_id"+rws2+"' style='width: 150px;' value='' ></select>"
u.innerHTML="<p id='tab_baseline"+rws2+"' style='width: 150px;'></p>"
w.innerHTML="<p id='tab"+rws2+"' style='width: 150px;'></p>"
a.innerHTML=""+rws2+""

load_indicators();
load_counties();
}

function rws(){
    var all_rows=0;
    document.getElementById("all_rows").value=all_rows;
    for( var i=0; i<=0; i++){
  insRow(); 
    }
}

function load_indicators(){
    var current_row=document.getElementById("all_rows").value;
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
document.getElementById("indicator_id"+current_row).innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_indicators?current_row="+current_row,true);
xmlhttp.send();
}
function load_entries(indics){
 var indic=indics.value;

 var indicators = indic.split(",");
// alert(indicators[0]+" qwu "+indicators[1]+" three "+indicators[2])
 var row_num=indicators[2];
 var table_id=indicators[1];
 var tab2="<input type='text'  id='target"+row_num+"' name='target"+row_num+"' value='' placeholder='Total' pattern='[0-9]{0,8}' style='width: 150px;' required='true' class='textbox'>";   
 var tab1="M: <input type='text'id='target_m"+row_num+"' pattern='[0-9]{0,8}' name='target_m"+row_num+"' value='' placeholder='Men' style='width: 50px;' required='true' class='textbox'>  F: <input type='text'id='target_f"+row_num+"' pattern='[0-9]{0,8}' name='target_f"+row_num+"' value='' style='width: 50px;' placeholder='Women' required='true' class='textbox'>";
 var tab2_baseline="<input type='text'  id='baseline"+row_num+"' pattern='[0-9]{0,8}' name='baseline"+row_num+"' value='' placeholder='Total' style='width: 150px;' required='true' class='textbox'>";   
 var tab1_baseline="M: <input type='text'id='baseline_m"+row_num+"' pattern='[0-9]{0,8}' name='baseline_m"+row_num+"' value='' placeholder='Men' style='width: 50px;' required='true' class='textbox'>   F: <input type='text'id='baseline_f"+row_num+"' pattern='[0-9]{0,8}' name='baseline_f"+row_num+"' value='' style='width: 50px;' required='true' placeholder='Women' class='textbox'>";

 if(table_id=="1"){//for men and women
     document.getElementById("tab"+row_num).innerHTML=tab1; 
     document.getElementById("tab_baseline"+row_num).innerHTML=tab1_baseline;
//     document.getElementById("tab_baseline"+row_num).
//     document.getElementById("tab_baseline"+row_num).
 }
 if(table_id=="2"){// for combined
  document.getElementById("tab"+row_num).innerHTML=tab2;
  document.getElementById("tab_baseline"+row_num).innerHTML=tab2_baseline;
 }
 if(table_id=="0"){
  document.getElementById("tab_baseline"+row_num).innerHTML="";
  document.getElementById("tab"+row_num).innerHTML="";
 }
}
function load_counties(){
    var current_row=document.getElementById("all_rows").value;
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
document.getElementById("county_id"+current_row).innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_counties?current_row="+current_row,true);
xmlhttp.send();
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
<body onload="rws();">
   
    
    
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
      <h3 style="text-align: center;">Enter Overall project target for each indicator.</h3>
      <form style=" margin-left: 150px;" method="post" action="add_project_targets" onsubmit="return nullchecker();">
          <input type="hidden" id="all_rows" name="all_rows" value="" >
    <input type="hidden" id="total_rows" name="total_rows" value="">
    <input type="button" onclick="insRow()" value="Add Row" class="textbox" style=" height: 30px; width: 100px;">
        <input type="button" onclick="deleteRow()" value="Delete row" class="textbox" style=" height: 30px; width: 100px;">
<input type="submit" name="sub" value="Save" class="textbox1" style="background: #cc99ff; color: #0000ff">
            <!--</form>
    <form action="#" method="post" style="margin-top: 0px; ">-->
   
   <table cellpadding="2px" cellspacing="3px" border="1px" class=""  style="background: #ccccff; width: 930px; ">
    
                      <tr>
                      <td >Indicator</td>
                      <td >County</td>
                       <td >District</td>
                       <td >Baseline</td>
                      <td >Target</td>
                      </tr>
</table>
  <table cellpadding="2px" cellspacing="3px" border="1px" class="" id="tb"  style="background: #ccccff; width: 930px;">
</table>
    <br>
<!--    <input type="submit" name="sub" value="Save" class="textbox1" style="background: #cc99ff; color: #0000ff">-->
       <br><br>     
     </form> 
    
       
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>

