<%-- 
    Document   : index_main
    Created on : Sep 5, 2013, 7:56:24 AM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
dbConnect conn = new dbConnect();


%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>

    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>

<link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
<link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<title>Program Progress</title>
<link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
<link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
<script src="js/jquery-1.7.2.js"></script>
<script src="ui/jquery.ui.core.js"></script>
<script src="ui/jquery.ui.widget.js"></script>
<script src="ui/jquery.ui.datepicker.js"></script>

<style>

        </style>
    



<script>	
                function date(){
                   
                    
                        $( ".datepicker" ).datepicker({
                                dateFormat: "mm/dd/yy",
                                changeMonth: true,
                                changeYear: true
                             
                               
                        });
                           
                     }   
            </script>
    <style>
           
           .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            .example {
    
    width:1270px;
    height:1070px;}
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
        <script>
            function total(){
            var u = document.getElementById("women").value; 
            var t = document.getElementById("men").value;
            var q = parseInt(u)+ parseInt(t);
            
            document.getElementById("subtotal").value=q
            }
            
            
        </script>
                
</script> 
        
<SCRIPT type="text/javascript" language="javascript">
           var i=1 ;
        function addRow(tableID) {
           
            var table = document.getElementById(tableID);
           
          
            
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
//            var cell2 = row.insertCell(1);
           // cell2.innerHTML = rowCount + 1;
 
            var cell2 = row.insertCell(1);
            var element2 = document.createElement("select");
            element2.type = "text";
            element2.name="county_"+i;
            element2.id="county_"+i;
            element2.className="county_"+i;
            var option;
 <% 
 String QueryDist= "SELECT countyName,countyID FROM county";
                   conn.state= conn.connect.createStatement();
		   conn.rs = conn.state.executeQuery(QueryDist);
                                  while(conn.rs.next())
                                         {
                                                          
                                                   %>
                                     option = document.createElement("option");
                                     option.type="text";
                                     option.value="<%=conn.rs.getString("countyID")%>";
                                     option.innerHTML = "<%=conn.rs.getString("countyName")%>";
                                     element2.appendChild(option);
                                                           

           
                                                   <%
                                                      
 System.out.println(conn.rs.getInt("countyID"));
                                System.out.println(conn.rs.getString("countyName"));                                                      }
                                
                %>   
                        
            element2.onchange=function filters(){
//                 filter_districts(this);

var cnt="county_"+i;

  
                        var newi=parseInt(i)-1;
//                        alert("newi"+newi);
  var distr="district_"+newi;
    var t=document.getElementById("county_"+newi);
 
   var dist2 =t.options[t.selectedIndex].value; 
   alert(dist2);
//            
      // alert(cnt) ;
    
//          var dist1 = district1.value
//       alert("disttricts"+dist1);
var xmlhttp;  
  
   
if (dist2=="")
{
//filter the districts    



document.getElementById(distr).innerHTML="<option value=\"\">Choose District</option>";
//alert(document.getElementById("district_"+i).value)
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
    

document.getElementById(distr).innerHTML=xmlhttp.responseText;

}
}

xmlhttp.open("POST","/ProgramProgress/districtselector2?district="+dist2,true);


xmlhttp.send();
   }                                      
                                                           
                                                           
                                                           
          

//             element2.className ="filter";
          

            element2.style.width = "100px"; 
            cell2.appendChild(element2);
            
            var cell3 = row.insertCell(2);
            var element3 = document.createElement("select");
            element3.type = "text";
            element3.name='district_' + i;
            element3.id='district_'+i;
            element3.value='';
//            element3.name = "district";
//            element3.id ="district";
            element3.multiple = true;
            
            element3.style.width = "140px";
            
          var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           
           element3.appendChild(option);
           cell3.appendChild(element3);
            
            
          
 
            
            var cell4 = row.insertCell(3);
            var element4 = document.createElement("textarea");
            element4.type = "text";
            element4.name = 'activity_'+i;
            element4.id = 'activity';
            element4.value = '';
            cell4.appendChild(element4);
            
            var cell5 = row.insertCell(4);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = 'startdate_'+i;
            element5.id = 'startdate_'+i;
            element5.value='';
            element5.onclick = date();
            element5.className="datepicker";
           element5.onclick = 
          function dater(){
               
                        $( ".datepicker").datepicker({
                                dateFormat: "mm/dd/yy",
                                changeMonth: true,
                                changeYear: true
                               
                        }); }   
          
            cell5.appendChild(element5);
            
            var cell6= row.insertCell(5);
            var element6 = document.createElement("input");
            element6.type = "text";
            element6.name = 'enddate_'+i;
            element6.id = 'enddate_'+i;
            element6.value = '';
            element6.className="datepicker";
            element6.onclick = 
function daters(){
                  
//                    alert(dates2);
                        $( ".datepicker" ).datepicker({
                                dateFormat: "mm/dd/yy",
                                changeMonth: true,
                                changeYear: true
                               
                        }); 
                    }   
          
            cell6.appendChild(element6);
           
            var cell7 = row.insertCell(6);
            var element7 = document.createElement("input");
            element7.type = "text";
            element7.name = 'women_'+i;
            element7.id = 'women_'+i;
            element7.value = '';
             element7.style.width = "60px";
            cell7.appendChild(element7);
           
            var cell9 = row.insertCell(7);
            var element8 = document.createElement("input");
            element8.type = "text";
            element8.name = 'men_'+i;
            element8.id = 'men_'+i;
           
           element8.value = '';
           element8.style.width = "60px";
           element8.onkeyup= function totals(){
               var m = i-1;
               var women = "women_"+m;
//               alert(women);
          
            var u = document.getElementById("women_"+m).value; 
           
            var t = document.getElementById("men_"+m).value;
           
            var q = parseInt(u)+ parseInt(t);
             
            document.getElementById("subtotal_"+m).value=q
            }
            cell9.appendChild(element8);
            
            var cell10 = row.insertCell(8);
            var element9 = document.createElement("input");
            element9.type = "text";
            element9.name = 'subtotal_'+i;
            element9.id = 'subtotal_'+i;
            element9.value = '';
             element9.style.width = "60px";
            cell10.appendChild(element9);
         var frm = document.getElementById("frm");
//            var as = document.getElementById("h");    
         i++;
      frm.h.value=i;
     
//      alert("alert "+i);
            
//            var element10 = document.createElement("form");
 
        }
 
        function deleteRow(tableID) {
            try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
 
            }
            }catch(e) {
                alert(e);
            }
        }
 
    </SCRIPT>
 <script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filterID(titleID){
    var ids = titleID.value;
//       alert("hbhb"+ids);
var xmlhttp;  
//alert(ids);
//   
if (ids=="")
{
//filter the districts    



document.getElementById("titleNo").innerHTML="<option value=\"\">Choose Title No</option>";
return false;
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

xmlhttp.open("POST","/ProgramProgress/titleselector?titleID="+ids,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts
</script>
               
<script type="text/javascript">
    
function number_validator(){
    var num;
      num=document.getElementById("").value;
if(num<=0){
  alert("");
return false; 
}   

if(num>=15){
  alert("");
return false;
}
        
        var start_date,end_date,dateObject,day,month,year,current_date;
    start_date=document.getElementById("start").value;
    end_date=document.getElementById("end").value;
    
    //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=day+"/"+month+"/"+year;     
    //we compare the date
    if(new Date(start_date) > new Date(current_date)){
     alert("Start date cannot be greater than current date");   
    return false;    
    }
    
    //we compare the current date and the preselected date
        if(new Date(start_date) > new Date(end_date)){
     alert("Start date cannot be greater than end date.");   
       return false; 
    }
    
            if(new Date(end_date) > new Date(current_date)){
     alert("End date cannot be greater than current date.");   
       return false; 
    }
            if(start_date=="" || end_date==""){
     alert("Choose start and end dates.");   
       return false; 
    }

}
$(function() {
$( ".datepicker" ).datepicker();
$( ".datepicker2" ).datepicker();
});

</script> 




   <script type="text/javascript">
    
    function date_end(){
        
    var start_date,end_date,dateObject,day,month,year,current_date;
    start_date=document.getElementById("start").value;
    end_date=document.getElementById("end").value;

    //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=month+"/"+day+"/"+year;     
    //we compare the date
    if(new Date(start_date) > new Date(current_date)){
     alert("Start date cannot be greater than current date");
      start_date=document.getElementById("start").value="";
    return false;    
    }
    
    //we compare the current date and the preselected date
        if(new Date(start_date) > new Date(end_date)){
     alert("Start date cannot be greater than end date."); 
      start_date=document.getElementById("start").value="";
       start_date=document.getElementById("end").value="";
       return false; 
    }
    
            if(new Date(end_date) > new Date(current_date)){
     alert("End date cannot be greater than current date.");  
      start_date=document.getElementById("end").value;
       return false; 
    }
            if(start_date=="" || end_date==""){
     alert("Choose start and end dates.");   
       return false; 
    }

}

function date_start(){
        
    var start_date,end_date,dateObject,day,month,year,current_date;
    start_date=document.getElementById("start").value;
//    end_date=document.getElementById("end").value;

    //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=month+"/"+day+"/"+year;     
    //we compare the date
            if(new Date(start_date) > new Date(current_date)){
     alert("start date cannot be greater than current date.");  
     start_date=document.getElementById("start").value="";
       return false; 
    }
            if(start_date==""){
     alert("Choose start.");   
       return false; 
    }

}

$(function() {
$( ".datepicker" ).datepicker();
$( ".datepicker2" ).datepicker();
});

</script>
        
</head>
<body>
   
    
    
<div class="example" style="width:1200px;height: 800px;">
     <div>
                    <% String logStatus=(String)session.getAttribute("loggedIn");
               if(logStatus==null){
//                     response.sendRedirect("index.jsp");
     %>
                   <a class="button-1" href="index.jsp">Login</a>
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
           </div>
    
<!--    <div><h2 style="text-align: center"><font color="blue;">Program Progress Table</font></h2>-->
<!--    <h1 style="text-align: center"><img src="images/aphia_logo.PNG" height="70" width="50%"/></h1></div> -->

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

    <div style="clear:both"></div>
    <div id="container">
<!--            This is the login page-->

       
                        <h4>INDICATOR ACTIVITIES</h4>
                        <form name="frm" id="frm" method="post"  action="Indicators" onsubmit="date_checker();">
                         <table style="padding-top: 100px;" style="padding-left: 400px;">
                             <tr><td>INDICATOR TITLE</td>
                                 <TD><select name="title" style="width:300px;" id="title" onchange="filterID(this)">
                                     <option value=""></option>   
                               <% 
                               dbConnect conn = new dbConnect();
                  String QueryTitle= "SELECT title,titleID FROM indicatortitles";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryTitle);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("titleID")%>'><%=conn.rs.getString("title")%></option>
           
                
                
                                                   <%
                                                                                                  }    
                                                      
// System.out.println(conn.rs.getInt("titleID"));
//                                System.out.println(conn.rs.getString("title"));                                                      }
                                
                               
                                                   %>   
                                 
                                 
                             </select></TD>
                            
                              <td>INDICATOR NUMBER:</td>
                             <TD><p id="titleNo" ><p></TD>
                                          </tr>
                         </table>
                          
                        
                             
                            
                        
                        <table id="dataTable" style="text-align: center" border="1" >
                             <tr><th></td><td colspan="10"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>
                             <tr><th width="190px;"></th>
                                      <th style="width:30px; text-align: left;"  >SELECT</th>
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th style="width:100px;">COUNTY</th>
                                      <th style="width:140px;">GEOGRAPHIC LOCATION</th>
                                      <th style="width:185px;">ACTIVITY TITLE</th>
                                      <th style="width:150px;">START DATE</th>
                                      <th style="width:150px;">END DATE</th>
                                      <th style="width:50px;">WOMEN</th>
                                      <th style="width:67px;">MEN</th>
                                      <th>SUB TOTAL</th>
                                  </tr> 
                             
                                   <tr><th width="200px;" rowspan="10" >UNIT <textarea name="unit"  ></textarea>
                                     <td width="60px;"><INPUT type="checkbox" name="chk"/></td>
<!--                                     <td width="40px;"> 1 </td>-->
                                     <td> 
                                              <select onchange="filter_districts(this);" style="width:100px;" name="county_0" id="county">
                                                   <option value=""></option>   
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
                                     <td><select style="width:140px;" name="district_0" id="district" multiple="true"></select>
                                         <option value=""></option>
                                     </td>
                                       <td><textarea name="activity_0"></textarea></td>
                                       <td><input type="text" name="startdate_0" value="" class="datepicker" id="start" required value="" onchange="date_start()"></td>
                                      <td><input type="text" name="enddate_0" value="" class="datepicker" id="end" required value="" onclick="date_checker()"></td>
                                      <td ><input type="text" name="women_0" id="women" value="" style="width: 60px;"></td>
                                     
                                      <td><input type="text" name="men_0" id="men" value="" onkeyup="total()" style="width: 60px;"></td>
                                      <td><input type="text" name="subtotal_0" id="subtotal"  value="" style="width: 60px;"></td>
                                     
                                  </tr>
                 
           
                                                  
                       
                               <INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />
 
                  <INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />
                  <input name="Submit" type="submit" value="Submit" />
                   <label>
                   <input name="h" type="hidden" id="h" value="0" />
                  </label>
                                                  
                              </table>
                                                    
                         
                            </form>
    
        
</div>
</div>                     
        
        
   
</div>
</body>
</html>
