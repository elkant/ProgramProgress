<%-- 
    Document   : IndicatorTitles
    Created on : Sep 16, 2013, 7:36:04 AM
    Author     : Maureen
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
      

<%@page contentType="text/html" pageEncoding="UTF-8" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

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
        
  

            <script src="scripts/jquery-1.8.3.js" type="text/javascript"></script>
            <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
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
                                  		
                                                                        sUpdateURL: "UpdateIndicator",
                                                                        sAddURL: "AddIndicator",
                                                                        sDeleteURL: "DeleteIndicator",
                    							"aoColumns": [ null,                  									
                    									
                    									{
                									        event: 'mouseover',
                                                                                                indicator: 'Saving...',
                                                            					tooltip: 'Click to edit ',
												type: 'textarea',
                                                 						submit:'Save changes',
												 callback : function(value, settings)
                                                                                                                        { 
//                                                                                                                            alert(value);
                                                                                                                            window.location.reload();
                                                                                                                        
                                                                                                                        },
												fnOnCellUpdated: function(sStatus, sValue, row, column, settings){
													//alert("(Cell Callback): Cell["+row+","+column+"] is updated with value " + sValue);
                    									}},
                    									{
                									        event: 'mouseover',
                                                                                                indicator: 'Saving...',
                                                            					tooltip: 'Click to edit ',
												type: 'textarea',
                                                 						submit:'Save changes',
												 callback : function(value, settings)
                                                                                                                        { 
                                                                                                                            //alert(value);
                                                                                                                            window.location.reload();
                                                                                                                        
                                                                                                                        },
												fnOnCellUpdated: function(sStatus, sValue, row, column, settings){
													//alert("(Cell Callback): Cell["+row+","+column+"] is updated with value " + sValue);
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
                                                                                                                           // alert(value);
                                                                                                                            window.location.reload();
                                                                                                                        
                                                                                                                        },
												fnOnCellUpdated: function(sStatus, sValue, row, column, settings){
													//alert("(Cell Callback): Cell["+row+","+column+"] is updated with value " + sValue);
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
                                                                                                                         //   alert(value);
                                                                                                                            window.location.reload();
                                                                                                                        
                                                                                                                        },
												fnOnCellUpdated: function(sStatus, sValue, row, column, settings){
													//alert("(Cell Callback): Cell["+row+","+column+"] is updated with value " + sValue);
												}
                    									},
                                                                                      {   
                                                                                          
                                                                                          data: function (value, setting) {
                                                                                              return " {'3':'M&E','4':'Clinical','6':'CU','7':'OVC','8':'HC','9':'SDH', 'selected':'" + value.trim() + "'}"
                                                                                          }
                                                                                          ,                                                                                          
                                                                                                id         : 'cellid',
                                                                                                name       : 'cellvalue',
                                                                                                event      : 'dblclick',
                                                                                                tooltip     : 'Double click to edit...',
                                                                                                placeholder  : '<b style="color:#AAA">Edit</b>',
                                                                                                submitdata   : function(value, setting) {
                                                                                                  var array_val = new Array();
                                                                                                  //array_val['id'] = "1";
                                                                                                  var values = $(this).find('select').val();
                                                                                                  array_val['cellvalue'] = values.join();
                                                                                                  return array_val;
                                                                                                },
                                                                                                type       : 'selectmulti',
                                                                                                style    : 'display: inline',
                                                                                                submit     : 'Save',
                                                                                                onblur     : 'ignore',
                                                                                                cancel     : 'Cancel',
                                                                                             callback : function(value, settings)
                                                                                                                        { 
//                                                                                                                           alert(""+value);
                                                                                                                            window.location.reload();
                                                                                                                        
                                                                                                                        }
                                                                                                                        ,
												fnOnCellUpdated: function(sStatus, sValue, row, column, settings){
													alert("(Cell Callback): Cell["+row+","+column+"] is updated with value " + sValue);
												}
                                                                                            
                                                                                        
                                                                                    }
//                    									
											]									

										});
				
			} );
		</script>
<!--<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-17838786-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>-->
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
        
        
 
      %>
<!--   
    -->      
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
  <button id="btnAddNewRow" title="Add title" value="Ok">Add new title...</button> 
  <button id="btnDeleteRow" value="cancel">Delete selected title</button>

<table cellpadding="0" style="padding-top: 100px;" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th>Title ID</th>
			<th>Title No</th>
			<th>Title</th>
			<th>Table No. </th>
			<th>Table Identifier </th>
			<th>Designation </th>
			
			
		</tr>
	</thead>
	<tfoot>
		<tr>

		        
		</tr>

	</tfoot>
   <tbody>
		 
        <c:forEach  var="indicate" items="${indicators}"  >
          
          <c:set var="title"  value="${indicate.TITLE}"></c:set>
          <c:set var="id"  value="${indicate.TITLEID}"></c:set>
          <c:set var="titleNo"  value="${indicate.TITLENO}"></c:set>
           
       <input type="hidden" id="title" name="title" value="<%= pageContext.getAttribute("title")%>" />
       <input type="hidden" id="titleNo" name="titleNo" value="<%= pageContext.getAttribute("titleNo")%>" />
       <input type="hidden" id="id" name="id" value="<%= pageContext.getAttribute("id")%>" />
         
            <tr id="<%=pageContext.getAttribute("id")%>">
           
           <td>  ${indicate.TITLEID} </td>
          
           <td class="sorting_1">${indicate.TITLENO} </td>
            <td>  ${indicate.TITLE} </td>
           <td class="sorting_1">${indicate.TABLENO} </td>
           <td class="sorting_1">${indicate.TABLEIDENTIFIER} </td>
           <td class="sorting_1">${indicate.DESIGNATION} </td>
          
          </tr>
       
         </c:forEach>
               
	</tbody>
</table>
 
			</div>   
        
          
    
     <form id="formAddNewRow" action="#" title="Add new title">
         <table>  
             
             <input type="hidden" id="id" name="id" rel="0" />
            	               
             <tr><td><label for="name">Table No</label></td><td><input type="text" name="tableNo" title="This is the table no in the form" id="tableNo" rel="1" class="required" ></input></td></tr>
             <tr><td><label for="name">Title No</label></td><td><input name="titleNo" title="Specify the title No" id="titleNo" class="required" rel="2" ></input></td></tr>
             <tr><td><label for="name">Title</label></td><td><textarea type="text" name="title" id="title" class="required" rel="3" ></textarea></td></tr>
             <tr><td><label for="name">Title Identifier</label></td><td><input type="text" title="1 if Gender is required,2 if its a total" rel="4" name="tableIdentifier" id="tableIdentifier" class="required"  /></td></tr>
             <tr><td><label for="name">Percentage</label></td><td><input type="text" name="percentage" id="percentage" class="required"  rel="4" /></td></tr>
         </table>
     	</form>              
        
        
    </div>
</div>
    </div>
</body>
</html>