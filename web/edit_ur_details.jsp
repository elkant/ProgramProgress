<%-- 
    Document   : edit_ur_details
    Created on : Jan 23, 2014, 8:54:54 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress Table</title>
<script type="text/javascript">
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }}
$(function() {
$( "#datepicker" ).datepicker();
});

            
        </script> 
  
 
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
<!--         <script src="js/jquery-1.7.2.js"></script>-->
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
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

            
            
            <h4>
               <%
                            if (session.getAttribute("updated") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("updated")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                
                session.removeAttribute("updated");
                            }

                        %>
                        </h4>
            
            <!--=====================================================================================--> 
    
     <%
     String fname="",mname="",lname="",phone="",usern="";
//     String userid=session.getAttribute("userid").toString();
      String userid=session.getAttribute("userid").toString();
     dbConnect conn = new dbConnect();

     String select_user_details="SELECT * FROM users WHERE UserID='"+userid+"'";
     conn.rs=conn.state.executeQuery(select_user_details);
    if(conn.rs.next()==true){
        fname=conn.rs.getString("Firstname"); 
        mname=conn.rs.getString("Middlename");
        lname=conn.rs.getString("Surname");        
         usern=conn.rs.getString("Username");
         phone=conn.rs.getString("Phoneno");
//         out.println(fname);
     }
             
conn.rs.close();
%>

   
        <div id="container">

            <div id="content" style="height:auto; background: #f6a828; border-width: 0px" >
<div id="midcontent" style="margin-left:130px ;">
       
        
        
<h4>
                   
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
    <h3><p align="center">Enter All your details Approriately.</p></h3> 
    <!--<h5><p align="center">The Fields marked with <font color="red">*</font> are editable fields.</p></h5>-->
    <br><br>
    <form action="save_ur_details" method="POST">
        <input type="hidden" name="userid" value="<%=userid%>">
<table style="margin-left: 300px; font-size: 18px; width: 400px;">
    <tr><td>First Name</td><td><input type="text" name="fname" id="fname" value="<%=fname%>" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Middle Name</td><td><input type="text" name="mname" id="mname" value="<%=mname%>" ></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Last Name</td><td><input type="text" name="lname" id="lname" value="<%=lname%>" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Phone No.</td><td><input type="text" name="phone" id="phone" value="<%=phone%>"></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Username</td><td><input type="text" name="username" id="username" value="<%=usern%>" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>New Password</td><td><input type="password" name="pass" id="password" value="" oninput="checkPasswords()" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="pass2" id="conf_password" oninput="checkPasswords()" value="" required></td></tr>


  <tr><td>Select Your County <font style="color: blue">*</font> </td>
                    <td>     <select  name="location" id="location" >
                            
                            <option value="">Select your county</option>
  <%
            String QueryDists= "SELECT countyID,countyName FROM county  order by countyName";
            if(conn.state.isClosed()){conn= new dbConnect();}
                                                 
				conn.rs = conn.state.executeQuery(QueryDists);
                                
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("countyName")%>'><%=conn.rs.getString("countyName")%></option>
                                                   <%
                                                      
                                System.out.println(conn.rs.getInt("countyID"));
                                System.out.println(conn.rs.getString("countyName"));                             
                                                        }
                                
                               
                                                   %>


                                 </select></td>
                                
                </tr> 
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td colspan="2"><input type="submit" value="Save" style="margin-left: 130px;"></td></tr>
</table>
       
    </form>
</div>        
        
                        
        
        
</div>
</div>
</div>
</body>
</html>

