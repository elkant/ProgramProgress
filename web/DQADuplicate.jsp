<%-- 
    Document   : DQADuplicate
    Created on : Jan 13, 2015, 3:08:57 PM
    Author     : AphiaPlus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 
    Document   : filterActivities
    Created on : Jan 13, 2015, 9:24:41 AM
    Author     : Maureen
--%>




<%@page import="PP.Admin.dbConnect"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%! dbConnect conn = new dbConnect(); %>



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
        
        
               <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
         <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
          <script src="scripts/jquery-ui.js" type="text/javascript"></script>
          <script src="scripts/jquery.validate.js" type="text/javascript"></script>
          
          <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
          <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
          
          <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
          
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
              
          <script type="text/javascript">
        $(document).ready(function () {
       
       
 
//           $.ajax({  
//                    url:"loadgroups_json",  
//                    type:'post',  
//                    dataType: 'html',  
//                    success: function(data) {
//                   chws=data;
//            
//          var table= 
              $("#members").dataTable({
              
             
              
              "dom": 'T<"clear">lfrtip',
        "tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf",
            "aButtons": [ {
                    "sExtends": "csv",
                    "sButtonText": "Save to csv"
                },
                {
                    "sExtends": "xls",
                    "sButtonText": "Save to xls"
                },
                {
                    "sExtends": "pdf",
                    "sButtonText": "Save to pdf"
                } ],
             "sRowSelect": "single"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            }).makeEditable({
                sUpdateURL: "newupdatemember",            
                 sDeleteURL: "/DIC/DeleteDuplicates"
                 
                 ,
                "aoColumns": [ null,null,null
                        ]
            }
            
            ).columnFilter({aoColumns: [{type: "text"},{},{},{},{},{},{},{},{},{} ]});
            
            
// $('#members tbody').on( 'click', 'tr', function () {
//        $(this).toggleClass('selected');
//    } );
              // document.getElementById("loading").innerHTML="";
             // }
                    
                               
        });
         
//        });
        
        </script>       
        <script type="text/javascript">
            function add_years(){
                var current_year=document.getElementById("financial").value;
//                var quarter=document.getElementById("quarter").value;
//alert("previous year : "+prev_year)
if(current_year!=""){
    var prev_year=current_year-1;
document.getElementById("quarter").innerHTML="<option value=\"\">Select Quarter</option><option value=\"Q1\">Oct-Dec ("+prev_year +")</option><option value=\"Q2\">Jan-March("+ current_year+")</option><option value=\"Q3\">April-June("+current_year +")</option><option value=\"Q4\">July-Sept("+current_year +")</option>>";
document.getElementById("quarter").disabled=false;                
}   
       if(current_year=="")     {
 document.getElementById("quarter").disabled=true;                
            }
}
        </script>
        
        
           <script type="text/javascript">
            function filter_phoneno(schl){
               
                // var dist=school.value;    
                var school=schl.value;     
                var xmlhttp;    
                if (school=="")
                {
                    //filter the districts    
      
      
  
                    document.getElementById("p_id").innerHTML="<option value=\"\"></option>";
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
                        document.getElementById("p_id").innerHTML=xmlhttp.responseText;
                        filter_nhfs(dist);
                    }
                }
                xmlhttp.open("POST","phonenochooser?school_id="+school,true);
                xmlhttp.send();    
            
            } 
            
            function nullness() {
                var sname,sid,phoneno;
                sname=document.getElementById("sname").value;
                var fname=document.getElementById("fname").value;
                var mname=document.getElementById("mname").value;
                sid=document.getElementById("s_id").value;
                phoneno=document.getElementById("p_id").value;
                if(sname=="" && sid=="" && phoneno==""&&mname==""&&fname==""){
            
                    alert("Choose At least one category");  
            
                    return false;  
                }
          
            }   
            function getNames(){
                //  alert("called");
                var xmlhttp;    
    
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
                        document.getElementById("allnames").innerHTML=xmlhttp.responseText;
     
                    }
                }
                xmlhttp.open("POST","member_names_chooser",true);
                xmlhttp.send();   
          
            }
            function editmember(UniqueID){
                
                
                //alert(names);
                
                
                $.ajax({
                    
//                      f.action="/DIC/deleteWorker?UniqueID="+UniqueID; 
                    url:"deleteWorker?UniqueID="+UniqueID,
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        
                            
                                    
                                    
                
                    }
                    
                    
                    
                });
                
            }
            
            
            
            
            function loaddata(){
                                     document.getElementById("loading").innerHTML="<img src='images/loading.gif'>"; 

            var type=document.getElementById("s_id").value;
            var financialyear= document.getElementById("financialyear").value;
            var quarter= document.getElementById("quarter").value;
//            alert(financialyear);
//            alert(quarter);
             $.ajax({
                    url:"getDuplicates?type="+type+"&financialyear="+financialyear+"&quarter="+quarter,
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        window.location.reload();
                        
//                             var n = noty({text: "<font color='green'>data loaded..</font>",
//                                         layout: 'center',
//                                           type: 'Success', 
//                                        timeout: 1800,
//                                       callback:{
//                                            afterShow:function(){
//                                               // window.location.reload();
//                                                  //var win=window.open("geMemDetails",'_self');
//                                                    //win.focus();
//                                              },
//                                           afterClose:function(){           
//                                               //window.close("edit_member.jsp");
//                                           } 
//                                             
//                                                   
//                                      }
//                                 });                                    
                                    
                
                    }
                    
                    
                    
                });
            
            }
            
            
            
        </script>
          <script type="text/javascript">
            function add_years(){
                var current_year=document.getElementById("financialyear").value;
//                var quarter=document.getElementById("quarter").value;
//alert("previous year : "+prev_year)
if(current_year!=""){
    var prev_year=current_year-1;
document.getElementById("quarter").innerHTML="<option value=\"\">Select Quarter</option><option value=\"Q1\">Oct-Dec ("+prev_year +")</option><option value=\"Q2\">Jan-March("+ current_year+")</option><option value=\"Q3\">April-June("+current_year +")</option><option value=\"Q4\">July-Sept("+current_year +")</option>>";
document.getElementById("quarter").disabled=false;                
}   
       if(current_year=="")     {
 document.getElementById("quarter").disabled=true;                
            }
}
        </script>
  
<body>
   
    
    
<div class="example" style="width:1200px;height:1200px;">
     <div>
             
     <%
               
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


    


               
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("AccessLevel") != null) {

                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
            <%} else if (session.getAttribute("AccessLevel").equals("5")) {%>

            <%@include file="menu/guest_menu.html" %>
   <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
             
                    <%} else  {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}}%>
            <!--=====================================================================================--> 
    <div style="clear:both">
      <form style="width:980px;margin-left: 100px; margin-top: 120px;"  >
                   
                    <table  class="table_form1" style="width:980px; height:50px;" >


 

                          
                            
    <tr>
    <th>Pepfar Year</th><th><select required="true" name="financialyear" id="financialyear" onchange="return add_years();">
                                       <option value="">Select Financial Year</option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       <option value="2014">2014</option>
                                       <option value="2015">2015</option>
                               
                               </select></th>
    <th>Quarter</th><th><select name="quarter" required="true" id="quarter" disabled="true">
                                       <option value="">Select Quarter</option>
                                       <option value="Q1">Oct-Dec ()</option>
                                       <option value="Q2">Jan-March()</option>
                                       <option value="Q3">April-June()</option>
                                       <option value="Q4">July-Sept()</option>

                               </select>
                               </tr>  
                            
      <tr> <td>Data Combination</td> <td> <select  id="s_id" name="type" class="textbox2" onchange="loaddata();">
                                    <option value="" selected="true">Select combinations</option>
                                    <option value="fullname">INDICATOR+ COUNTY+ DISTRICT YEAR + QUARTER +ACTIVITY</option>
                                    <option value="missingdata">Missing Data</option>
                                    <option value="wrongdate">Wrong Date Format </option>
                                 
                                  
                                  
                                    
                                </select></td> 
<!--                                <td>                  <button id="btnDeleteRow" value="cancel">Delete Client </button>            
</td>-->
</tr>
                         



<!--                            <td colspan="2">  <input type="submit"  name="sub"  value="Search..." class="submit"/>   </td>  -->


                        </tr>     
                    </table>
                </form>
            </div>



       





      
                
               <div id="demo_jui">
                  
                    <%   if(session.getAttribute("type")!=null){
                        if(session.getAttribute("type").equals("fullname")){
                        %>
                         <h4>DATA COMBINATION: INDICATOR+ COUNTY+ DISTRICT YEAR + QUARTER +ACTIVITY</h4>
                       <%
                                          } 
                        if(session.getAttribute("type").equals("missingdata")){%>
                         <h4>DATA COMBINATION: MISSING COLUMNS  </h4>
                       <% 
                        }
                           if(session.getAttribute("type").equals("wrongdate")){%>
                             <h4>DATA COMBINATION: Wrong Date Format</h4>  
                        <%
 }


}
%>
    
                   <p id="loading"></p>
               
		        <table id="members" class="display">
		            <thead>
		            <tr>
		          
                           <th>TITLE ID </th>
                           <th>COUNTY ID </th>
                           <th>DISTRICT ID </th>
                           <th>YEAR</th>
                           <th>QUARTER</th>
                              <%if(session.getAttribute("type").equals("wrongdate")){%>
                             <th>START DATE </th>
                              <th>END DATE</th>  
                        <%
 } else{%>
                           <th>ACTIVITY TITLE</th>
                              <th>TOTAL</th>
                           <%}%>
                        
                        
                                                  
                         
                            </tr>
		                
		            </thead>
		            <tbody>
                                
                                 <c:forEach items="${missingdata}" var="alldata">
                            <c:set var="fname" value="fname"></c:set>

                                <tr id="${alldata.TITLEID}">
                                    <td>${alldata.TITLEID}</td>
                                    <td>${alldata.COUNTYID}</td>
                                    <td>${alldata.DISTRICTID}</td>
                                    <td>${alldata.YEAR}</td>
                                    <td>${alldata.QUARTER}</td>                               
                                    <td>${alldata.ACTIVITYTITLE}</td>                               
                                    <td>${alldata.TOTAL}</td>
                                    
                                
                            </tr>
                        </c:forEach>
                                 <c:forEach items="${missingdata2}" var="alldata2">
                            <c:set var="fname" value="fname"></c:set>

                                <tr id="${alldata2.TITLEID}">
                                    <td>${alldata2.TITLEID}</td>
                                    <td>${alldata2.COUNTYID}</td>
                                    <td>${alldata2.DISTRICTID}</td>
                                    <td>${alldata2.YEAR}</td>
                                    <td>${alldata2.QUARTER}</td>                               
                                    <td>${alldata2.ACTIVITYTITLE}</td>                               
                                    <td>${alldata2.TOTAL}</td>
                                    
                                
                            </tr>
                        </c:forEach>

                                
		            </tbody>
		        </table>
       
    
    </div>

        
</div>
                        
 
        
   
</div>
</body>
</html>



