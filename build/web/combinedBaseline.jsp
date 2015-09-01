<%-- 
    Document   : combinedBaseline
    Created on : Sep 9, 2013, 3:46:51 PM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
dbConnect conn = new dbConnect();
%>
<head>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress</title>
    <style>
            #container{
        width:700px;
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
            
            
</head>
<body>
   
    
    
<div class="example" style="margin-top: 0; margin-bottom: 0; width:1500px; height: 1000px;">

 
         <div id="container" style="width:850px; border-radius:25px; padding-left:10px; padding-right:50px; margin-left:100px; margin-right:0; padding-top: 20px;padding-bottom: 20px;" >
            
<p align="center"><b>Please Input the baseline and the end target below.</b></p>      
        <form action="BaselineTotalServlet" method="post">
        <table style="padding-top: 100px;" class="fixed" style="width:800px;">
            
             <tr><td>Select Financial Year:<font color="red">*</font><select name="FinancialYear">
                        <option></option>
                        <option value="2010">2010</option>
                        <option value="2011">2011</option>
                        <option value="2012">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                    
                        
                        
                         </select></td>
              <td>Select Reporting Period:<font color="red">*</font><select name="reportingPeriod">
                                        <option value="">Choose Quarter</option>
                                       <option value="Q1">October-December</option>
                                       <option value="Q2">Jan-March</option>
                                       <option value="Q3">April-June</option>
                                       <option value="Q4">July-Sept</option>
                        
                        </select>
     </td>
     </tr>
     <tr>
                        <td>County</td>
                        <td>
                <select onchange="filter_districts(this);" style="width:100px;" name="county" id="county">
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
     </tr><tr>
                         <td>District</td>
                         <td>
                             <select style="width:140px;" name="district" id="district">
                                         <option value="">Choose District</option>
                                     
                        
                         </select>
                         </td>
     </tr>
            
        <tr><td>Baseline<font color="red">*</font></td><td><input type="text" placeholder="Input Baseline" name="totalbaseline"></td></tr>
       <tr><td>End of Project Target<font color="red">*</font></td><td><input type="text" placeholder="Input end target" name="totalendTarget"></td></tr>
      
      
       <tr><td>Total Target:<font color="red">*</font></td><td><input type="text" placeholder="Input Total target" name="totalTarget"></td></tr>
        <td></td><td><input type="submit" value="SUBMIT"></td></tr>
        </table>
                                                   </form>  
     </div>
                        
        
        
   
</div>
          
</div>
</body>
</html>
