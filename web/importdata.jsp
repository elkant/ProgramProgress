<%-- 
    Document   : backup
    Created on : Mar 17, 2014, 8:23:02 AM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
      <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

 <link rel="shortcut icon" href="images/pptlogo.png"/>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
    <title>PPMT - Import data</title>
    
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
                    <a class="button-1" href="LogoutServlet">LogOut</a>
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

                    System.out.println("Access level "+session.getAttribute("AccessLevel").toString());
                    
                    
                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
             <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
             
                    <%} else  {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}

            } else { %>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
            <div style="clear:both" style="padding-top: 50px;">
                 <%if (session.getAttribute("msg") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text:'<%=session.getAttribute("msg")%>',
                                  layout: 'center',
                                    type: 'Success'});
                    
                </script> <%
                session.removeAttribute("msg");
                            }

                        %>   
                        
                        
                              <h4>
 
      
   
                            
                         </h4>
                <form  action="pullfromServer" style="margin-left: 200px; margin-right: 100px;margin-top: 100px; height: 110px; width: 700px; background-color: #F5F5F5;">
                    <table><tr><td><h3>Press button to import data</h3> </td>
                            <td><input type="submit" value="Pull Data" name="submit" style="width:100px;height: 60px; background-color: #1c94c4;"></td>
                        
                        
                        </tr></table>  
        
    </form>                
        
   
    </div>
    
    
</div>
</body>
</html>
