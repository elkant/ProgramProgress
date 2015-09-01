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
 <link rel="shortcut icon" href="images/pptlogo.png"/>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
    <title>Program Progress Table</title>
    <style>

        </style>
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
             
                   <%} else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}}%>

            <!--=====================================================================================--> 
    
     
   
    
   
            <div id="container"  style="width: 300px;margin-top:50px;">

          
        

<div style=" position: absolute; left: 370px; width: 500px; background: #ffffff; padding-top:70px;">

                    <%if (session.getAttribute("mailchanged") != null) {



                    %>
                    <script type="text/javascript"> 
                    
                                       var n = noty({
                                           text: '<%=session.getAttribute("mailchanged")%>',
                                           layout: 'center',
                                           type: 'Success',
                                           timeout:3000});
                    
                    </script>
                    <%
                            session.removeAttribute("mailchanged");
                        }

                    %>
                    <!--creating random user id-->






            

                    <form action="editmail" method="post" style="height:90px; ">
                        <br/>

                        <%

                            dbConnect conn = new dbConnect();
                            conn.rs = conn.state.executeQuery("select email from mail where mailid='1'");

                            String mandemail = "enter mail";
                            if (conn.rs.next()) {

                                mandemail = conn.rs.getString(1);

                            } else {
                                mandemail = "no email";

                            }


                            conn.state.close();

                        %>
                        <table>                 
                            <tr>          
                                <td><input type="email" name="mandemail" required  value="<%=mandemail%>" class="textbox1" style="height:40px;" /></td>
                                <td>

                                </td>
                                <td>
                                    <input type="submit" value="Update" style="height:40px;"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                        
</div>  
        
</div 
</div>
</div>
</body>
</html>