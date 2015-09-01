<%@page import="PP.Admin.dbConnect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress Table</title>
    <style>

        </style>
</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px; overflow-y: auto;">
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

 <% dbConnect conn= new dbConnect();

String adt="select * from taskauditor";

conn.rs=conn.state.executeQuery(adt);




%>

                       
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("AccessLevel") != null) {

                    System.out.println("Access level "+session.getAttribute("AccessLevel").toString());
                    
                    
                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
               <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
             
                    <%} else  {%>

            <%}

            } else { %>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
    <div style="clear:both; padding-top: 100px;">
        <div id="container">  
        
          <table  cellpadding="0px" class="viewpdt" cellspacing="0px" border="1px" style="width:840px;" id="viewpdt">
<tr><th>Action</th><th>Time</th><th>User</th><th>Host Machine</th></tr>
<%
while(conn.rs.next()){
     String Action=conn.rs.getString(3);
    String Time=conn.rs.getString(4);
    String HostMachine=conn.rs.getString(2);
    String users="Select * from users where Username='"+conn.rs.getString(5) +"'";
   
    conn.rs2=conn.state2.executeQuery(users);
   while( conn.rs2.next()){
%>
<tr><td> <%= Action %> </td><td> <%= Time %></td><td> <%=conn.rs2.getString(2)%></td><td> <%= HostMachine %></td></tr>



<%
}
       }
%> 
                        
    </div>  
        
    </div>
</div>
</body>
</html>