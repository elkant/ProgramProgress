<%-- 
    Document   : baseline
    Created on : Sep 9, 2013, 3:41:44 PM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<%
dbConnect conn = new dbConnect();
 String QueryDist2="";


%>
        <head>
   <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
     <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <title>Program Progress</title>
    <style>
        #container{
         width:1200px;
	 height:400px;
                
            }

           
         .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            .example {
    
    width:1270px;
    height:670px;}
        
        </style>
       
            
             <script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filter_districts(district){
    
          var dist = district.value
//       alert("dist"+dist);
var xmlhttp;  
  
   
if (dist=="")
{
//filter the districts    



document.getElementById("district").innerHTML="<option value=\"\">Choose District</option>";
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
document.getElementById("district").innerHTML=xmlhttp.responseText;
}
}

xmlhttp.open("POST","/ProgramProgress/districtselector?district="+dist,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts

</script>
<script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filterID(titleID){
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
    var ids = titleID.value;
//       alert("hbhb"+ids);
       
       
        var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = ids.split(",");
var tbID=titles[0];
var tbsID=titles[1];
//alert("table id"+tbID);
//alert("table idssss"+tbsID);          
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
    
IndicatorFilt() 
IndicatorFilt1()
  
 }  

//end of filter districts
</script>            
            
            
        
</head>
<body>
  
    
<div class="example" style="margin-top: 0; margin-bottom: 0; width:1500px; height: 1000px; ">
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
 
         <div id="container" style="width:1050px; border-radius:25px; padding-left:20px; padding-right:20px; margin-left:100px;; margin-right:0; padding-top: 20px;padding-bottom: 20px;" >
            

        

                        
        
        
   

 
<p align="center"><b>Please Input the baseline and end of project targets for each of the categories below.</b></p>      
        <form action="BaselineServlet" method="post">
            <table class="fixed" style="width:900px;">
        <div>
            
        
            <tr><td>Select Financial Year:<font color="red">*</font></td>
            <td><select name="FinancialYear" style="width:150px;">
                        <option>Choose Financial Year</option>
                        <option value="2010">2010</option>
                        <option value="2011">2011</option>
                        <option value="2012">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option Value="2015">2015</option>
                    
                        </select>
            </td>
            <td>Select Reporting Period:<font color="red">*</font><select name="reportingPeriod" style="width:150px;">
                                        <option value="">Choose Quarter</option>
                                       <option value="Q1">Oct-Dec</option>
                                       <option value="Q2">Jan-Mar</option>
                                       <option value="Q3">Apr-Jun</option>
                                       <option value="Q4">Jul-Sep</option>
                        
                        </select>
     </td>
     </tr>
     <tr>
                        <td>County</td>
                        <td>
                <select onchange="filter_districts(this);" style="width:150px;"" name="county" id="county">
                    <option value="">Choose County</option>
  <% 
             
                                                      String QueryCounty= "SELECT countyName,countyID FROM county";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryCounty);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("countyID")%>'><%=conn.rs.getString("countyName")%></option>
                                                   <%
                                                      
 System.out.println(conn.rs.getInt("countyID"));
                                System.out.println(conn.rs.getString("countyName"));                                                      }
                                
                               
                                                   %>
                                            
                                             
                                         </select>
                        </td>
     <tr>
                         <td>District</td>
                         <td>
                             <select style="width:150px;" name="district" id="district">
                                         <option value="">Choose District</option>
                                     
                        
                         </select>
                         </td>
     </tr>
     
   
     
           
           
           <tr><td>Baseline:<font color="red">*</font></td>
                <td><input type="text" placeholder="Input men Baseline" name="menbaseline"></td>
                <td><input type="text" placeholder="Input women baseline" name="womenbaseline"></td></tr>
        <tr><td>End of Project Target:<font color="red">*</font></th>
                <td><input type="text" placeholder="Input men end target" name="menEndTarget"></td>
                <td><input type="text" placeholder="Input women End Target" name="womenEndTarget"></td></tr>
           
           <tr><td>Target:<font color="red">*</font></td><td><input type="text" placeholder="Input men's target" name="menTarget"></td>
               <td><input type="text" placeholder="Input women Target" name="womenTarget"></td></tr> 
        
        <tr><td></td><td></td><td><input type="submit" value="SUBMIT"></td></tr></div>
   
        </table>
        </form>
                                                   
                                                   
                                                   
            </div>
                        
    </div>
        
   
</div>
        </form>    
     </body>
</html>
