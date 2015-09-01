<%@page import="PP.Admin.dbConnect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress Table</title>
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
      
        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>
        
<!--          <script>
        
          $.ajax({
                    
//                      f.action="/DIC/deleteWorker?UniqueID="+UniqueID; 
                    url:"checkbackup",
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        
                       
                    }
                        
                            
                                    
                                    
                
          })
    </script>-->
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
            <%} else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}

            } else { %>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>
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
            <!--=====================================================================================--> 
    <div style="clear:both">
        
        

 
      
        
                        
        
        
    </div>
</div>
</body>
</html>