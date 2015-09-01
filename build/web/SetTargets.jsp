
<%-- 
    Document   : ResultsMain
    Created on : Sep 9, 2013, 7:33:58 AM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@page import="PP.Admin.dbConnect"%>

<%!
dbConnect conn = new dbConnect();
 String QueryDist2="";


%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
String username=(session.getAttribute("Username")).toString();
    if (session.getAttribute("Username")==null) {
        response.sendRedirect("index.jsp");
    } 
      else{%>
      
       <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>          
     <h5>Welcome <%=username%></h5>
      <%
     
     }
%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
<!--    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>-->
    <title>Program Progress</title>
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
         <script src="js/jquery-1.7.2.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
         <link rel="shortcut icon" href="images/pptlogo.png"/>
   <style>
           
          
            .example {
    
    width:1500px;
    height:1270px;}
            #tables{
                width: 1200px;
                border-style: double;
                border:1;
                
                
                
                
            }
        </style> 

         <script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filterID(titleID){
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
    var ids = titleID.value;
//       alert("hbhb"+ids);
       
       
        var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = ids.split(",");
var tbID=titles[0];
var tbsID=titles[1];
//alert("table id"+tbID);
//alert("table idssss"+tbsID);

if(tbsID == "1"){
    
    document.getElementById("Iframe1").src = "/ProgramProgress/baseline.jsp";}
else if(tbsID == "2"){
     document.getElementById("Iframe1").src = "/ProgramProgress/combinedBaseline.jsp";
}



           
var xmlhttp;  
// alert(ids);
   
if (tbID=="")
{
//filter the districts    



document.getElementById("titleNo").innerHTML="<td><input value=\"\"></td>";
return;
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
document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}

xmlhttp.open("POST","/ProgramProgress/titleselector?titleID="+tbID,true);

xmlhttp.send();
    
IndicatorFilt() 
IndicatorFilt1()
  
 }  

//end of filter districts
</script>
       
    

<script language="JavaScript">
    function frames(){
    var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
//     alert(title);
     
      var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = title.split(",");
var tbID=titles[0];
//alert("table id"+tbID);
           
  if(type=="separate"){
    
    document.getElementById("Iframe1").src = "/ProgramProgress/baseline.jsp";
}
else if(type== "combined"){
     document.getElementById("Iframe1").src = "/ProgramProgress/combinedBaseline.jsp";
}}
</script>
    
</head>
<body>
   
    
    
<div class="example" style="width:1200px;height: 1200px;">
     <div>

             
                   <!--=========================================menu=========================================-->     
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

             <input type="hidden" name="titles" id="titles" value="">             

   <table class="fixed" style="padding-top: 100px;">
                             <tr><th style="width:200px">INDICATOR TITLE</th>
                                 <Th><select name="" style="width:300px" id="title" onchange="filterID(this)">
                                  <option value=""></option>
                                  <% 
                  String QueryDist1= "SELECT title,titleID,tableIdentifier FROM indicatortitles";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryDist1);
                                                      while(conn.rs.next())
                                                           {
                                                          
                                                          
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("titleID")%>,<%=conn.rs.getString("tableIdentifier")%>'><%=conn.rs.getString("title")%></option>
           
                
                
                                                   <% } %>
                                 
                                 
                             </select></Th>
                                                 
                             
                             <th>INDICATOR NUMBER:</th>
                             <Th><p id="titleNo" ><p></Th>
                               
                                     
                                         

                                 
                             </select></TD>
       
                         </table>
                                                   
                                                   
                        <div>
                            <iframe id="Iframe1" seamless="seamless" align="middle" style="padding-top: 0px;"scrolling="" frameborder="0" width="1200" height="800" src="" ></iframe>
                   </div>

        
</div>
                        
        
        
   
</div>
</body>
</html>


