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
    <title>Program Progress Table</title>
    
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
                 <%if (session.getAttribute("datasend") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("datasend");
                            }

                        %>   
                        
                        
                              <h4>
 <%
 
 dbConnect conn = new dbConnect();
String Location="";
        String userid= session.getAttribute("userid").toString();
         String getlocation="select * from users where UserID='"+userid+"'";
         System.out.println(getlocation);
         conn.rs2 = conn.state2.executeQuery(getlocation);
            if(conn.state2.isClosed()){conn= new dbConnect();}
         while(conn.rs2.next()){
             Location= conn.rs2.getString("Location");
             System.out.println(Location);
         }
 if(Location!=null && !Location.equals("")){

if (Location.equals("None") )
                             {
    
 
    %>
         <script type="text/javascript"> 
                    var link="<h4><a href=\"edit_ur_details.jsp\">Kindly Update your Location Click Here</a> before you backup</h4>";
                    var n = noty({text: ''+link,
                        layout: 'center',
                        type: 'Success'
 
                         });
                    
                </script> <%
//                response.sendRedirect("edit_ur_details.jsp");
//                session.removeAttribute("Location");
                            }else{
    %>
              
   
                            
                         </h4>
                <form  action="Back_up_SQL" style="margin-left: 200px; margin-right: 100px;margin-top: 100px; height: 110px; width: 700px; background-color: #F5F5F5;">
                    <table><tr><td><h3>Press button to backup data</h3> </td>
                            <td><input type="submit" value="Backup Data" name="submit" style="width:100px;height: 60px; background-color: #1c94c4;"></td>
                        
                        
                        </tr></table>  
        
    </form>                
        
     <%
     
 System.out.println("XXXXXXXXXXXXXXXXXXX");
                            }}

                       

%>   
    </div>
    
    <%

         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.rs3!=null){ conn.rs3.close();}
         if(conn.rs4!=null){ conn.rs4.close();}
         if(conn.rs5!=null){ conn.rs5.close();}
         if(conn.rs6!=null){ conn.rs6.close();}
//         if(conn.rs7!=null){ conn.rs7.close();}
        
         if(conn.state!=null){ conn.state.close();}
         if(conn.state1!=null){ conn.state1.close();}
         if(conn.state2!=null){ conn.state2.close();}
         if(conn.state3!=null){ conn.state3.close();}
         if(conn.state4!=null){ conn.state4.close();}
         if(conn.state5!=null){ conn.state5.close();}
         if(conn.state6!=null){ conn.state6.close();}
//        / if(conn.state7!=null){ conn.state7.close();}
%>
</div>
</body>
</html>
