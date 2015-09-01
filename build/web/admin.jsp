<%-- 
    Document   : admin
    Created on : Sep 27, 2013, 9:57:06 AM
    Author     : The Aluodos
--%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
String username=(session.getAttribute("Username")).toString();
    if (session.getAttribute("Username")==null) {
        response.sendRedirect("index.jsp");
    } 
      else{%>
      
       <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>          
     <h5>Welcome <%=username%></h5>
      <%
     
     }
%>
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


   <div><h2 style="text-align: center"><font color="blue;">Program Progress Table</font></h2>
    <h1 style="text-align: center"><img src="images/aphia_logo.png" height="70" width="200"/></h1></div> 
    <div class="menuholder">
        <ul class="menu slide">
            <li><a href="home.jsp" class="red">Home</a></li>
            <li><a href="ResultsMain.jsp" class="violet">Program Progress</a></li>
            <li><a href="" class="orange">Manage Forms</a>
                <div class="subs">
                    <dl style="width: 200px; text-align: center;">
                        <dt></dt>
                        <dd style="text-align: center;"><a href="/ProgramProgress/EditActivityServlet">Separated Indicator Activities</a></dd>
                        <dd style="text-align: center;"><a href="/ProgramProgress/EditActivityServlet2">Combined Indicator Activities </a></dd></dl>
                        <dl style="width: 200px; text-align: center;"><dt></dt>
                        <dd style="text-align: center;"><a href="/ProgramProgress/EditResultServlet">Separated Indicator Results</a></dd>
                        <dd style="text-align: center;"><a href="/ProgramProgress/EditResultServlet2">Combined Indicator Results</a></dd>
                       </dl>
            
                   
                </div>
            </li>
             <li><a href="" class="orange">Maintenance</a>
                <div class="subs">
                    <dl>
                        <dt>Maintain Districts</dt>
                        <dd><a href="/ProgramProgress/DistrictServlet">Districts</a></dd>
                        <dd><a href="/ProgramProgress/CountyServlet">Counties</a></dd>
                    </dl>
                  
                    <dl>
                    <dt>Maintain Indicators</dt>
                        <dd><a href="/ProgramProgress/IndicatorServlet">Indicators</a></dd>
                  </dl>
                  
                    <dl>
                     <dt> Maintain Baseline/Targets</dt>
                    <dd ><a href="/ProgramProgress/BaselineMaintainServlet">Separate</a></dd>
                         <dd><a href="/ProgramProgress/Baseline2Servlet">Combined </a></dd>
                  </dl>      
                   
                   
                </div>
            </li>
            <li><a href="" class="yellow">Reports</a>
             <div class="subs">
                 <dl>
                     <dt>Quarterly Reports</dt>
                     <dd><a href="1stQuarterCountyReport.jsp">1st Quarter Report</a></dd>
                     <dd><a href="">2nd Quarter Report</a></dd>
                   </dl> <dl>
                    <dt>Quarterly Reports</dt>
                   <dd><a href="">3rd Quarter Report</a></dd>
                     <dd><a href="">4th Quarter Report</a></dd>
            </dl> <dl>
                    <dt>Geographic Reports</dt>
                   <dd><a href="">District Reports</a></dd>
                     <dd><a href="">County Reports</a></dd>
                     </dl> <dl>
                    <dt>Semi-Annual Reports</dt>
                   <dd><a href="">1st Half Report</a></dd>
                     <dd><a href="">2nd Half Report</a></dd>
                     
            </dl>
             </div>
            </li>
            <li><a href="" class="green">Help</a></li>
           
        </ul>
        <div class="back"></div>
        <div class="shadow"></div>
    </div>
    <div style="clear:both">
        
        
        
                        
        
        
    </div>
</div>
</body>
</html>