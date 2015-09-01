<%-- 
    Document   : Login
    Created on : Sep 29, 2013, 2:13:20 PM
    Author     : COMPAQ
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    <title>Program Progress</title>
    <style>
            #container{
        width:500px;
	height:300px;
                
            }
           .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            
        </style>
</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
     <div>
                    <% String logStatus=(String)session.getAttribute("loggedIn");
               if(logStatus==null){
//                     response.sendRedirect("index.jsp");
     %>
                   <a class="button-1" href="Login.jsp"></a>
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
//        response.sendRedirect("index.jsp");
    } 
%>
           </div>
    
           
                <div>
                    <% String logStatus1=(String)session.getAttribute("loggedIn");
               if(logStatus1==null){
//                     response.sendRedirect("index.jsp");
     %>
                   <a class="button-1" href="Login.jsp">Login</a>
     <%        } else{
     %>
                    <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>
     <%
               }
        String username1=(String)session.getAttribute("Username");
      
               if(username1==null){
                   
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
    
        <div><h1 style="text-align: center"><img src="images/aphia_logo.png" height="70" width="200"/></h1></div> 

    <div style="clear:both">
         <div id="container">
 
<!--            This is the login page-->
        <form name=login id="login" method="post"  action="LoginServlet">
                        <h3><p align="center">Have an Account? Please Login</p></h3>
                         
                        	 <%
                                 Object alert=request.getAttribute("loginError");
                        if(alert==null){
                            request.setAttribute("qtyerror","");
                        }else{%>
                            <%=alert%>                           
                     <% }
                        %>
                       
                              <table>
                         <tr><td >Username</td><td><input type="text" name="Username" value=""></td></tr>  
                         <tr><td >Password</td><td><input type="password" name="Password" value=""></td></tr>  
                         <tr><td><input type="submit" name="Login" value="Login"></td></tr>  
                         <tr><td><h3>Don't Have an Account?</h3></td><td><a class="button-1" href="userReg.jsp">New User Registration</a></td></tr>
                              </table>
                        
                         
                            </form>
        </div>
        
        
                        
        
        
    </div>
</div>
</body>
</html>