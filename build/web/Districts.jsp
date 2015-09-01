<%-- 
    Document   : Districts
    Created on : Sep 16, 2013, 9:47:14 AM
    Author     : Maureen
--%>


<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
 <link rel="shortcut icon" href="images/pptlogo.png"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%!
  dbConnect conn = new dbConnect();

%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
        <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
         <link rel="shortcut icon" href="images/pptlogo.png"/>
          <script>
$( document ).tooltip();
</script>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    <title>Program Progress</title>
    <style>

        </style>
        
  

        <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
        <!--<script src="media/js/jquery-ui.js" type="text/javascript"></script>-->
<!--   <script src="media/js/jquery.validate.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-ui.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.js" type="text/javascript"></script>
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />

		<script type="text/javascript">
			$(document).ready( function () {
				$('#example').dataTable().makeEditable({
                                  
									
                                                                        sUpdateURL: "UpdateDistrict",
                                                                        sAddURL: "AddDistrict",
                                                                        sDeleteURL: "DeleteDistrict",
                    							"aoColumns": [ null,                  									
                    									
                    									
                    									{
                									        event: 'mouseover',
                                                                                                indicator: 'Saving...',
                                                            					tooltip: 'Click to edit ',
												type: 'textarea',
                                                 						submit:'Save changes',
												 callback : function(value, settings)
                                                                                                                        { 
                                                                                                                            alert(value);
                                                                                                                            window.location.reload();
                                                                                                                        // settings involing plugin parameters
//                                                                                                                        alert('Element Changed : '+value);											
                                                                                                                        //alert(settings.cssclass);
                                                                                                                        },
												fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
													alert("(Cell Callback): is updated with value " + sValue);
												}
                    									},
                    									{
                									        event: 'mouseover',
                                                                                                indicator: 'Saving...',
                                                            					tooltip: 'Click to edit ',
												type: 'textarea',
                                                 						submit:'Save changes',
                                                                                                callback : function(value, settings)
                                                                                                                        { 
                                                                                                                            alert(value);
                                                                                                                            window.location.reload();
                                                                                                                        // settings involing plugin parameters
//                                                                                                                        alert('Element Changed : '+value);											
                                                                                                                        //alert(settings.cssclass);
                                                                                                                        },
												fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
													alert("(Cell Callback): is updated with value " + sValue);
												}
                    									},
                    									{
                									        event: 'mouseover',
                                                                                                indicator: 'Saving...',
                                                            					tooltip: 'Click to edit ',
												type: 'textarea',
                                                 						submit:'Save changes',
												fnOnCellUpdated: function(sStatus, sValue, row, column, settings){
													alert("(Cell Callback): Cell["+row+","+column+"] is updated with value " + sValue);
												}
                    									},
//                    									
											]									

										});
				
			} );
		</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-17838786-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<link rel="StyleSheet" href="css/main.css" type="text/css" />
 <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
  <style type="text/css">
    #container{
                height:500px;
                width:1000px; 
    }
     .example {
    width:800px;
    height:auto;
    overflow-y: auto;
     }
    </style>
   
<style>
label {
display: inline-block;
width: 5em;
}
</style>
 <script>
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
          
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("AccessLevel") != null) {

                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
            <%} else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}

            } else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
    <div style="clear:both">
        
     <div id="container" style="padding-top: 100px;">
		
			<div id="demo">
  <button id="btnAddNewRow" title="Add title" value="Ok">Add new district...</button> 
  <button id="btnDeleteRow" value="cancel">Delete selected dis</button>

<table cellpadding="0"  cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
                    <th>District ID</th>
		    <th>District</th>
		    <th>County</th>
			
			
			
		</tr>
	</thead>
	<tfoot>
		<tr>

		        
		</tr>

	</tfoot>
   <tbody>
		 
        <c:forEach  var="dist" items="${districts}"  >
          
          <c:set var="id"  value="${dist.DISTRICTID}"></c:set>
          <c:set var="district"  value="${dist.DISTRICT}"></c:set>
          <c:set var="county"  value="${dist.COUNTYID}"></c:set>
         
           
        <input type="hidden" id="id" name="id" value="<%= pageContext.getAttribute("id")%>" />
       <input type="hidden" id="district" name="district" value="<%= pageContext.getAttribute("district")%>" />
      
         
            <tr id="<%=pageContext.getAttribute("id")%>">
           
           <td>  ${dist.DISTRICTID} </td>
          
           <td class="sorting_1">${dist.DISTRICT} </td>

           
              <% 
           
                                                      String QueryCounty= "SELECT countyName FROM county where countyID='"+ pageContext.getAttribute("county") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryCounty);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
              <td class="sorting_1"><%=conn.rs.getString("countyName")%></td>
                                                   <%
  
                                System.out.println(conn.rs.getString("countyName"));                                                      }
                                
                               
                                                   %>
          
         
          </tr>
       
         </c:forEach>
               
	</tbody>
</table>
 
			</div>   
        
          
    
     <form id="formAddNewRow" action="#" title="Add new district">
         <table>     
             <input type="hidden" id="id" name="id" rel="0" />
            
             <tr><td><label for="name">County</label></td><td>
                                    <select  style="width:100px;" name="county" title="select a county in which the district is in" id="county" rel="1">
                                                   <option value=""></option>   
  <% 
             String QueryCountys= "SELECT countyName,countyID FROM county";
                                                     conn.state= conn.connect.createStatement();
				conn.rs1 = conn.state1.executeQuery(QueryCountys);
                                                      while(conn.rs1.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs1.getString("countyID")%>'><%=conn.rs1.getString("countyName")%></option>
                                                   <%
                                                      
 System.out.println(conn.rs1.getInt("countyID"));
                                System.out.println(conn.rs1.getString("countyName"));                                                      }
                                
                               
                                                   %>
                                            
                                             
                                    </select> </td></tr>
                                
                                
                                
                                
                                
                                
                               
                            <tr><td><label for="name">District</label></td><td><input type="text" rel="2" name="district" title="Enter the District" id="district" class="required" ></td></tr>
                           
         </table>
         
			                                      
			        
			</form>              
        
        
    </div>
</div>
    </div>
</body>
</html>