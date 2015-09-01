<%-- 
    Document   : edit_user_details
    Created on : Jan 22, 2014, 12:26:55 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress Table</title>
    <script type="text/javascript">
        function load_users(user_type){
            var user_level=user_type.value;
            if(user_level!=""){
           
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
xmlhttp.open("POST","load_users?level="+user_level,true);
xmlhttp.send();
            }
         if(user_level==""){
             document.getElementById("loads").innerHTML="<font color='red' size='4px;' style'margin-left: 400px;'>Choose A Category Please.</font>"    
         }     
        }
    </script>
</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
     <div>
                    <% String logStatus=(String)session.getAttribute("loggedIn");
               if(logStatus==null){
//                     response.sendRedirect("index.jsp");
     %>
                   <a class="button-1" href="Login.jsp">Login</a>
     <%        } else{
     %>
                    <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>
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
//
//    if (session.getAttribute("Username")==null) {
//        response.sendRedirect("index.jsp");
//    } 
%>
        </div>

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
   
    
   
        <div id="container">

            <div id="content" style="height:auto; background: #f6a828; border-width: 0px" >
<div id="midcontent" style="margin-left:130px ;">
       
        
        
<h4>
                        
<%
if (session.getAttribute("clerk_added") != null) {
out.println(session.getAttribute("clerk_added"));
session.removeAttribute("clerk_added");
}

%>
                   
                        <!--creating random user id-->
                         <%!
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
%>  
                  
</h4>
<div style=" position: absolute; left: 90px; width: 1080px; background: #ffffff; padding-top:70px;">
    <h3><p align="center">Choose the Type of user you want to edit his/her details.</p></h3> 
    <h5><p align="center">The Fields marked with <font color="red">*</font> are editable fields.</p></h5> 
    <form action="save_edited_user" method="POST">
<table>
    <tr><td>Choose User Category : <select name="user" id="user" onchange="return load_users(this);">
                <option value="">Choose User Category</option>
                <option value="2">Clerks</option>
                <option value="5">Guest</option>
                <option value="1">Administrator</option>
            </select></td></tr>
</table>
        <p id="loads" style="font-size: 8px;"></p>
    </form>
</div>        
        
                        
        
        
</div>
</div>
</div>
</body>
</html>
