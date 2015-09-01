<%-- 
    Document   : EditResult2
    Created on : Sep 19, 2013, 2:11:51 PM
    Author     : Maureen
--%>

<%-- 
    Document   : EditResult
    Created on : Sep 19, 2013, 8:30:57 AM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %> 

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
                                  
									
                                                                        sUpdateURL: "UpdateResult2",
//                                                                        sAddURL: "AddCounty",
                                                                        sDeleteURL: "DeleteResult2",
                    							"aoColumns": [ null,null,                  									
                    									
                    									{
                									        event: 'mouseover',
                                                                                                indicator: 'Saving...',
//                                                            					tooltip: 'Click to edit ',
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
//                                                            					tooltip: 'Click to edit ',
												type: 'textarea',
                                                 						submit:'Save changes',
//                                                                                                data: "{'':'Please select', 'A':'A','B':'B'}",
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
                                                                                      null,null,
                    									null, 
                    									null,null,null, 
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
 

   </head>

<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
     <div>
                    <% 
        String username=(String)session.getAttribute("Username");
      
               if(username==null){
                   response.sendRedirect("index.jsp");  
               } else{
     %>
               <h5>Welcome <%=username%></h5>
               <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>
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
           </div>
    
           
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("AccessLevel") != null) {

                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
               <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
             
                    <%} else  {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}

            } else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
 
    <div style="clear:both">
        
     <div id="container" style="padding-top: 100px;">
		
			<div id="demo">

  <button id="btnDeleteRow" value="cancel">Delete selected result</button>

<table cellpadding="0" cellspacing="0"  style="padding-top: 100px;" border="0" class="display" id="example">
	<thead>
		<tr>
                    <th>Result ID</th>
                     <th>County</th>
		    <th>District </th>
		    <th>Achieved </th>
		    <th>Financial Year</th>
		    <th>Quarter</th>
		   <th>Title ID</th>
		   
			
			
			
		</tr>
	</thead>
	<tfoot>
		<tr>

		        
		</tr>

	</tfoot>
   <tbody>
		 
        <c:forEach  var="result" items="${result2}"  >
          
          <c:set var="id"  value="${result.RESULTID}"></c:set>
          <c:set var="titleID"  value="${result.TITLEID}"></c:set>
          <c:set var="district"  value="${result.DISTRICT}"></c:set>
          <c:set var="Title"  value="${result.TITLEID}"></c:set>
         
           
        <input type="hidden" id="id" name="id" value="<%= pageContext.getAttribute("id")%>" />
<!--       <input type="hidden" id="titleID" name="titleID" value="<%= pageContext.getAttribute("Title")%>" />
       <input type="hidden" id="district" name="district" value="<%= pageContext.getAttribute("district")%>" />-->
      
         
            <tr id="<%=pageContext.getAttribute("id")%>">
      
           
           <td class="sorting_1">  ${result.RESULTID} </td>
          
            <td class="sorting_1">${result.COUNTY} </td>
            <td class="sorting_1">${result.DISTRICT} </td>
         
        
            <td class="sorting_1">${result.TOTALACHIEVED} </td>
        
        
          <td class="sorting_1">${result.FINANCIAL} </td>
          <td class="sorting_1">${result.QUARTER} </td>
         
        
         
         

           
         
                                                   
                                                     <% 
           
                                                      String Query= "SELECT title FROM indicatortitles where titleID='"+ pageContext.getAttribute("Title") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(Query);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
              <td class="sorting_1"><%=conn.rs.getString("title")%></td>
               
                                                   <%
  
                                                                                }
                               
                                                   %>
          
          
          
          </tr>
       
         </c:forEach>
               
	</tbody>
</table>
 
			</div>   
        
          
    
    
        
    </div>
</div>
    </div>
</body>
</html>

