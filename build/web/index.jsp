<%-- 
    Document   : ResultsMain2
    Created on : Sep 28, 2013, 8:32:08 AM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@page import="PP.Admin.dbConnect"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    <link rel="shortcut icon" href="images/pptlogo.png"/>
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
      
        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>
    <title>Program Progress</title>
    <style>
            #container{
        width:500px;
	height:300px;
                
            }
           .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
           input.m-wrap {
    border: 1px solid #e5e5e5;
}
input.m-wrap, button.m-wrap, select.m-wrap, textarea.m-wrap,p,td,h2 {
    font-family: cambria;
} 
label.m-wrap, input.m-wrap, button.m-wrap, select.m-wrap, textarea.m-wrap,p,td.m-wrap  {
    font-size: 18px;
    font-weight: normal;
    line-height: 20px;
}
        </style>
        
        
        
          <script>
        
          $.ajax({
                    
//                      f.action="/DIC/deleteWorker?UniqueID="+UniqueID; 
                    url:"checkbackup",
                    type:'post',
                    dataType:'html',
                    success:function (data){
//                    alert("returned");    
                       
                    }
                        
                            
                                    
                                    
                
          })
    </script>
    <script type="text/javascript" >
        
          $.ajax({
                    
//                      f.action="/DIC/deleteWorker?UniqueID="+UniqueID; 
                    url:"AutoBackup",
                    type:'post',
                    dataType:'html',
                    success:function (data){
             
                    }
                        
                            
                                    
                                    
                
          });
    </script>

</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
     <div>
               
           </div>
    
    <div><h1 style="text-align: center"><img src="images/aphia_logo.png" height="70" width="200"/></h1></div> 
  <h2 style="text-align: center;">Program Progress Monitoring Tables</h2>
    <div style="clear:both">
         <div id="container">
           
<!--            This is the login page-->
<div style="position: absolute; left: 350px; width: 550px; height: 300px; background: #ffffff">
        <form name="login" id="login" method="post"  action="LoginServlet">
                        <h3><p align="center">Have an Account? Please Login</p></h3>
                         
                        	 
                       
                        
                         <%
 
                            if ( session.getAttribute("checkversion") != null)  { %>
                                <script type="text/javascript"> 
                                 
                    var n = noty({text: '<%=session.getAttribute("checkversion")%>',
                                        layout: 'center',
                                        type: 'Success'
                                   
                                    });
                    
                </script> <%
                
                session.removeAttribute("checkversion");
                            }%>
                           
                         <%
 
                            if ( session.getAttribute("loginError") != null)  { %>
                                <script type="text/javascript"> 
                                  
                    var n = noty({text: '<%=session.getAttribute("loginError")%>',
                                        layout: 'center',
                                        type: 'Success'
                                   
                                    });
                    
                </script> <%
                
                session.removeAttribute("loginError");
                            }%>
                           
             
                        
                        
                        
                        
                              <table>
                         <tr><td class="m-wrap">Username</td><td><input type="text" name="Username" value="" class="m-wrap"></td></tr>  
                         <tr><td  class="m-wrap" >Password</td><td><input type="password" name="Password" value=""  class="m-wrap"></td></tr>  
                         <tr><td></td><td><input type="submit" name="Login" value="Login"  class="m-wrap"></td></tr>  
<!--                         <tr><td><h3>Don't Have an Account?</h3></td><td><a class="button-1" href="add_guest.jsp">Register As a Guest</a></td></tr>-->
                              </table>
                        
             

                       </form>
                            
                      
        </div>
        
        
     <%
dbConnect conn = new dbConnect();
String showcols="SHOW COLUMNS FROM users LIKE 'Location'";
   if(conn.state1.isClosed()){conn= new dbConnect();}
conn.rs= conn.state1.executeQuery(showcols);

if(conn.rs.next()==true){
System.out.println("Column already added");
}else{
String addcol="ALTER TABLE users ADD Location varchar(100) NOT NULL DEFAULT 'None'";   
   if(conn.state.isClosed()){conn= new dbConnect();}
conn.state.executeUpdate(addcol);}
%>                   
        
        
    </div>
                                  
                        
                         </div><br>
               <p align="center" title="Version 1.23 Last Updated 08/07/2015."> &copy PPMT System Version 1.23 Last Updated on 08/07/2015. Aphia Plus | USAID </p>
               
</div>
                            
   
</body>
</html>