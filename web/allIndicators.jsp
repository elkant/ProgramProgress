<%-- 
    Document   : allIndicators
    Created on : Oct 3, 2013, 10:50:48 AM
    Author     : COMPAQ
--%>

<%-- 
    Document   : compCountyReport
    Created on : Oct 3, 2013, 10:08:38 AM
    Author     : COMPAQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
<!--        <script>
           function select(){
           var e = document.getElementById("title");
   var titleid = e.options[e.selectedIndex].value;
//           alert(titleid);
            document.getElementById("HiddenID").value=titleid;
          
           
              }
        </script>-->
       
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

if(tbsID == "1"){
    
    document.getElementById("Iframe1").src = "/ProgramProgress/Indicators.jsp";}
else if(tbsID == "2"){
     document.getElementById("Iframe1").src = "/ProgramProgress/Indicators2.jsp";
}



           
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

xmlhttp.open("POST","/ProgramProgress/districtselector2?district="+dist,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts

</script>    




<script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function filterer(){
    
      var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value;
//  
//   alert("financial"+y);

    
 var xmlhttp;  
// alert(ids)
   
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
//    document.getElementById("district").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;

//    window.location='Results.jsp';
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay?FY="+y,true);

xmlhttp.send();
    
   
  
 }  
function IndicatorFilt(){
    
      var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
//alert("tile id"+tbIDs);
   
 
var g = document.getElementById("titles").value;
g=tbIDs;
//alert("hidden"+g);
    
 var xmlhttp;  
// alert(ids)
   
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
//    document.getElementById("district").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;

//    window.location='Results.jsp';
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay?title="+tbIDs,true);

xmlhttp.send();
    
   
  
 }  
 function IndicatorFilt1(){
    
      var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
//alert("tile id for 2"+tbIDs);
   
 

    
 var xmlhttp;  
// alert(ids)
   
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
//    document.getElementById("district").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;

//    window.location='Results.jsp';
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay2?title="+tbIDs,true);

xmlhttp.send();
    
   
  
 }  
 
function filterer1(){
    
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
   
//   alert("quarter"+v);
   

    
 var xmlhttp;  
// alert(ids);
  
if (v=="")
{
//filter the districts    


//
//document.getElementById("district").innerHTML="<td><input value=\"\"></td>";
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
document.getElementById("tablerow").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;


}
}


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay?QTR="+v ,true);

xmlhttp.send();
    
   
  
 }  

</script>      
      
<script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filter_district1(districts){
    
          var dist = districts.value
//       alert("dist"+dist);
var xmlhttp;  
  
   
if (dist=="")
{
//filter the districts    



document.getElementById("districts").innerHTML="<option value=\"\">Choose District</option>";
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
document.getElementById("districts").innerHTML=xmlhttp.responseText;
}
}

xmlhttp.open("POST","/ProgramProgress/districtselector2?district="+dist,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts

</script>





<script language="JavaScript">
    function frames(){
    var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
//     alert(title);
     
      var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = title.split(",");
var tbID=titles[0];
//alert("table id"+tbID);
           
  if(type=="separate"){
    
    document.getElementById("Iframe1").src = "/ProgramProgress/indicatorResults.jsp";
}
else if(type== "combined"){
     document.getElementById("Iframe1").src = "/ProgramProgress/indicatorResults2.jsp";
}}
</script>
<script>
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
    <form action="Quarter1CountyReport" method="post" on>
                      Select the fields as required then click generate.
    
    <input type="hidden" name="titles" id="titles" value="">
    <table class="fixed" style="padding-top: 100px;" >
<!--          <tr><th style="width:200px">INDICATOR TITLE</th>
                      <Th><select name="indicator" style="width:300px" id="title" id="choose-form" onchange="filterID(this)">
                      <option value="">Select Indicator Title</option>       
        
       <% 
                  String QueryDists= "SELECT title,titleID,tableIdentifier FROM indicatortitles";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryDists);
                                                      while(conn.rs.next())
                                                           {
                                                          
                                                          
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("titleID")%>,<%=conn.rs.getString("tableIdentifier")%>'><%=conn.rs.getString("title")%></option>  
        
                                                   <%
                                                                                                  }    
                                                      
// System.out.println(conn.rs.getInt("titleID"));
//                                System.out.println(conn.rs.getString("title"));                           }
                                
                               
                                                   %>
                                 
                                 
                             </select></Th>
                                                   
                              <th>INDICATOR NUMBER:</th>
                             <Th><p id="titleNo" style="width:100px"  ><p></Th> 
                              </select></TD></tr>            -->
        
<tr>
                               <th>Financial Year</th><th><select name="FinancialYear" id="financial" onChange="filterer()">
                                       <option value="">Select Financial Year</option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       <option value="2013">2014</option>
                                       <option value="2013">2015</option>
<!--                                       <input type="text" name="financialyr" id="financialyr" value=""></td> -->
                               
                               </select></th>
                               <th>Quarter</th><th><select name="Quarter" id="quarter" onChange="filterer1()">
                                       <option value="">Select Quarter</option>
                                       <option value="Q1">Oct-Dec</option>
                                       <option value="Q2">Jan-March</option>
                                       <option value="Q3">April-June</option>
                                       <option value="Q4">July-Sept</option>
 <!--                                                   <input type="text" name="quarter" id="quarters" value="">          -->
                               </select>
                               <td> <th><input type="submit" value="Generate Report" /></th></td> 


                             </tr>        
        
<!--                               <tr><th style="height:50px"style="width:200px" >County</th>
        
  <td><select onChange="filter_districts(this);"  name="county_0" id="county" multiple="true">
                                                   <option value="">Select County</option>   
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
                          </select> </td>                             
                                                    
                               
                          
                 <th style="width:200px">Districts</th>             
                 <td><select style="width:140px;" name="district_0" id="district" multiple="true"></select>
                                         <option value=""></option>
                 </td>             
                </tr> <tr>-->
                 </tr>             
                              
                              
                              
                              
                          </tr>                             

     </form> 
    
    
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>



