<%-- 
    Document   : EditActivity2
    Created on : Sep 18, 2013, 2:42:11 PM
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
                                  
									
                                                                        sUpdateURL: "UpdateActivity2",
//                                                                        sAddURL: "AddCounty",
                                                                        sDeleteURL: "DeleteActivity1",
                    							"aoColumns": [ null,null,
                    									null,
                    							                null,
                    									null,
                    									null,
                    									
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
                                                                                        {//subtotal
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
                                                                                                                        }
												
                    									}
                    									
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
        String username="";
       username=(String)session.getAttribute("Username");
      
               if(session.getAttribute("Username").toString()==null){
                  response.sendRedirect("index.jsp");  
               } else{
     %>
      <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>          
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
<!--  <button id="btnAddNewRow" title="Add title" value="Ok">Add new county...</button> -->
  <button id="btnDeleteRow" value="cancel">Delete selected activity</button>

<table cellpadding="0" style="padding-top: 100px;" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
                    <th>Activity ID</th>
		    <th>Unit</th>
		    <th>Indicator</th>
		    <th>County</th>
		    <th>District</th>
		    <th>Activity</th>
		    <th>Others</th>
		    <th>Start Date</th>
		    <th>End Date</th>
		    <th>Total</th>
		   
			
			
			
		</tr>
	</thead>
	<tfoot>
		<tr>

		        
		</tr>

	</tfoot>
   <tbody>
		 
        <c:forEach  var="activity" items="${activitys}"  >
          
          <c:set var="id"  value="${activity.ACTIVITYID}"></c:set>
            <c:set var="district"  value="${activity.DISTRICT}"></c:set>
          <c:set var="ActivityID"  value="${activity.ACTIVITY}"></c:set>
          <c:set var="titleID"  value="${activity.INDICATOR}"></c:set>
           
        <input type="hidden" id="id" name="id" value="<%= pageContext.getAttribute("id")%>" />
       <input type="hidden" id="titleID" name="titleID" value="<%= pageContext.getAttribute("titleID")%>" />
       <input type="hidden" id="district" name="district" value="<%= pageContext.getAttribute("district")%>" />
         
            <tr id="<%=pageContext.getAttribute("id")%>">
           
           <td>  ${activity.ACTIVITYID} </td>
           <td> ${activity.UNIT}  </td>
          
              <td class="sorting_1">
             <% 
           System.out.println(pageContext.getAttribute("titleID").toString());
                                                      String Query1= "SELECT title FROM indicatortitles where titleID='"+ pageContext.getAttribute("titleID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(Query1);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
                                    <%=conn.rs.getString("title")%>
               
                                                   <%
  
                                                                                }
                               
                                                   %>
                            </td>  
          <td class="sorting_1">${activity.COUNTY} </td>
          <td class="sorting_1">
                      <% 
                      
                      //System.out.println(pageContext.getAttribute("district").toString());
           String r = pageContext.getAttribute("district").toString();
//           String h[] = new String;
         String  h[] = r.split("_");
         
           for(int i=0;i<h.length;i++){
//               System.out.println("pppp "+h[i]);
//               System.out.println("lenght "+h.length);
               if(h[i]!=""){
                    System.out.println("lllll "+h[i]);
                          String QueryCounty= "SELECT DistrictName FROM districts where districtID='"+ h[i]+"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryCounty);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
                    <%=conn.rs.getString("DistrictName")%>
                                                <%
  
                                                                                                                  }                }
                                                              }
                                                   %>
             
              </td>

                                                    <td class="sorting_1">
                    <% 
                String Query= "SELECT  * FROM indicatoractivity where ActivityID='"+ pageContext.getAttribute("ActivityID") +"' ";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(Query);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
                                              <%=conn.rs.getString("Activity")%>
               
                                                   <%
  
                                                                                }
                               
                                                   %>
                                                    </td>
          <td class="sorting_1">${activity.OTHERS} </td>
          <td class="sorting_1">${activity.STARTDATE} </td>
          <td class="sorting_1">${activity.ENDDATE} </td>
          <td class="sorting_1">${activity.TOTAL} </td>
         
            
           
         
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
