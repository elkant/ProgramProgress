<%@page import="PP.Admin.dbConnect"%>

<%@page import="java.util.Random"%>
<%!
dbConnect conn = new dbConnect();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>


<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
 <link rel="shortcut icon" href="images/pptlogo.png"/>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
    <title>Program Progress Table</title>
    <style>

        </style>
</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
        
        
<!--         <script>
        
          $.ajax({
                    
//                      f.action="/DIC/deleteWorker?UniqueID="+UniqueID; 
                    url:"checkbackup",
                    type:'post',
                    dataType:'html',
                    success:function (data){}
                        
                            
                                    
                                    
                
          })
    </script>-->
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
//    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
//    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
//    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("Username")==null) {
        response.sendRedirect("index.jsp");
    } 
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

           <% }} else {%>

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
       <%if (session.getAttribute("network_error") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("network_error")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("network_error");
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

  <%
 
                            if ( session.getAttribute("backupsms") != null)  { %>
                                <script type="text/javascript"> 
                                
                    var n = noty({text: '<%=session.getAttribute("backupsms")%>',
                                        layout: 'center',
                                        type: 'Success'
                                    });
                    
                </script> <%
                
                session.removeAttribute("backupsms");
                            }%> 
             
<div style=" position: absolute; left: 370px; width: 500px; background: #ffffff; padding-top:70px;">
    <h3><p align="center">Please Enter New Clerk's details</p></h3> 
<tr><td><p align='center'><font color="red">*</font> indicates must fill fields</p></td></tr>
<form action="RegisterUser" method="post">
<br/>
<table align="center" cellpadding="0px" cellspacing="0px" border="0px" style="height: 400px">
                       
                            
<tr>
<td class="align_button_left"><label for="first_name">Userid<font color="red">*</font></label></td>
<td><input id="first_name" type=text readonly value="<%=generateRandomNumber(1000,10888)%>" required name="userid" class="textbox"/></td>
</tr>

<tr>
<td class="align_button_left"><label for="s_name">Surname<font color="red">*</font></label></td>
<td><input id="surname" type=text required name="surname" class="textbox"/></td>
</tr>
                            
<tr>
<td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
<td><input id="first_name" type=text required name="first_name"  class="textbox"/></td>
</tr>
                            
<tr>
<td class="align_button_left"><label for="middle_name">Middle Name<font color="red">*</font></label></td>
<td><input id="m_name" type=text required name="m_name" student_name class="textbox"/></td></tr>
                            
<tr>
<td class="align_button_left"><label for="phoneno">Phone Number</label></td>
<td><input  type="text" name="phoneno" pattern="[0-9]{10,10}" class="textbox" /></td>                            
</tr>

<tr> 
<td class="align_button_left"><label for="username">Username <font color="red">*</font></label></td>
<td><input id="town" type=text required name="username" class="textbox"/></td>
</tr>
                           
<tr>
<td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
<td><input id="password" type=password required name="password" oninput="checkPasswords()" class="textbox"/></td>
</tr>                           
                            
<tr>
<td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
<td ><input id="conf_password" type=password required name="conf_password" oninput="checkPasswords()" class="textbox"/></td>                                
</tr>      
             
<!--<tr>  <td class="align_button_left"><label for="conf_password">Access Level<font color="red">*</font></label></td>
    <td><select align="right" size="" required="true" name="accesslevel" >
            <option value=""></option>
            <option value="1" >admin</option>
            <option value="2" >data clerk</option>
</select></td>
</tr>-->
<tr> <td class="align_button_left"><label for="conf_password">Access Level<font color="red">*</font></label></td>
    <td><select align="right" size="" required="true" name="accesslevel">
         <option value=""></option> 
         <%
         
         if(conn.state.isClosed()){conn= new dbConnect();}
          String query ="select * from designation";
          conn.rs = conn.state.executeQuery(query);
while(conn.rs.next()){%>  
 <option value="<%= conn.rs.getString("accesslevel")%>"><%= conn.rs.getString("designation")%></option>

     <%}%>
          
<!--            <option value=""></option>
            <option value="1">1</option>
            <option value="2">2</option>-->
</select></td>
<tr>
<tr>
   
<!--    <td>Access Level</td>-->
<td class="align_button_left"><input  size="12px"  type="reset" value="clear" /></td> <td class="align_button_right">
    <input type="submit" class="submit" value="Add" onselect=""/></td>
</tr>

<tr><td></td><td>Already Have an Account? <li><a href="index.jsp" class="red">LOGIN</a></td>


</tr>    
</table>
</form>
</div>        
        
                        
        
        
</div>
</div>
</div>
</body>
</html>
     
 <%
conn.state.close();

%>