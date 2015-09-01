<%-- 
    Document   : AllIndicatorsReport
    Created on : Jan 10, 2014, 2:57:26 PM
    Author     : Maureen
--%>

<%-- 
    Document   : 1stQuarterCountyReport
    Created on : Sep 21, 2013, 7:46:26 AM
    Author     : The Aluodos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="PP.Admin.dbConnect"%>

<%!
dbConnect conn = new dbConnect();
HttpSession session;
 String QueryDist2="";


%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
<!--    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>-->
    <title>Program Progress</title>
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
         <script src="js/jquery-1.7.2.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
        <link rel="shortcut icon" href="images/pptlogo.png"/>
   <style>
           
          
            .example {
    
    width:1500px;
    height:1270px;}
            #tables{
                width: 1200px;
                border-style: double;
                border:1;
                
                
                
                
            }
        </style> 



<script>
    function filterID(titleID){
    
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
    var tbID = titleID.value;
//       alert("id "+tbID);
       
       
//        var titles = new Array();
//// this will return an array with strings "1", "2", etc.
// titles = ids.split(",");
//var tbID=titles[0];
//var tbsID=titles[1];




           
var xmlhttp;  
// alert(ids);
   
if (tbID=="")
{
//filter the districts    



document.getElementById("titleNo").innerHTML="<td><input value=\"\"></td>";
return;
}
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}

xmlhttp.open("POST","/ProgramProgress/titleselector?titleID="+tbID,true);

xmlhttp.send();
    

  
 }  

//end of filter districts
</script>
</head>
<body>
   
    
    
<div class="example" style="width:1200px;height: 800px;">
     <div>
                    <% String logStatus=(String)session.getAttribute("loggedIn");
               if(logStatus==null){
//                  response.sendRedirect("index.jsp");
     %>
     <h4>  <a class="button-1" href="index.jsp">Login</a></h4>
     <%        } else{
     %>
     <h4>   <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a></h4>
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

                
                                    <%
if (session.getAttribute("First Quarter County Report Generated Succefully") != null) { %>

                                <script type="text/javascript"> 

                    var n = noty({text: '<%=session.getAttribute("First Quarter County Report Generated Succefully")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2700});
                    
                </script> 

                 <%
                session.removeAttribute("First Quarter County Report Generated Succefully");
                               }
                        %>

    


    <%@include file="menu/clerk_menu.html" %>
    <div style="clear:both"> 
    <form action="Quarter1CountyReport" method="post" style="margin-top: 100px;">
                      Select the fields as required then click generate.
    
    <input type="hidden" name="titles" id="titles" value="">
    <table class="fixed">
          <tr><th style="width:200px">INDICATOR TITLE</th>
              <Th><select name="indicator" style="width:300px" id="title" id="choose-form" required="true" onchange="filterID(this)">
                      <option value="">Select Indicator Title</option>       
                      <option value="All">All Indicators</option>
       <% 
                  String QueryDists= "SELECT title,titleID,tableIdentifier FROM indicatortitles";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryDists);
                                                      while(conn.rs.next())
                                                           {
                                                          
                                                          
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("titleID")%>'><%=conn.rs.getString("title")%></option>  
        
                                                   <%
                                                                                                  }    
                                                      
// System.out.println(conn.rs.getInt("titleID"));
//                                System.out.println(conn.rs.getString("title"));                           }
                                
                               
                                                   %>
                                 
                                 
                             </select></Th>
                                                   
                              <th>INDICATOR NUMBER:</th>
                             <Th><p id="titleNo" style="width:100px"  ><p></Th> 
                              </select></TD></tr>            
        
<tr>
    <th>Financial Year</th><th><select required="true" name="FinancialYear" id="financial">
                                       <option value="">Select Financial Year</option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       <option value="2014">2014</option>
                                       <option value="2015">2015</option>
                               
                               </select></th>
    <th>Quarter</th><th><select name="Quarter" required="true" id="quarter" >
                                       <option value="">Select Quarter</option>
                                       <option value="Q1">Oct-Dec</option>
                                       <option value="Q2">Jan-March</option>
                                       <option value="Q3">April-June</option>
                                       <option value="Q4">July-Sept</option>

                               </select>
                               </tr>        
        
                           
                               
                          
                 
                 <th><input type="submit" value="Generate Report" /></th>
                 
                 </tr>             
                              
                              
                              
                              
                          </tr>                             
                               
                               
                               
                               
                               
                               
                               
        
    </table>
     </form> 
    
    
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>

