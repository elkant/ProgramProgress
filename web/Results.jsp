<%-- 
    Document   : Results
    Created on : Sep 10, 2013, 8:55:06 AM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%!

 dbConnect conn = new dbConnect();
  String financials="";
          String quarters="";
String district =""; 
String dist="";                            
%>

<%session = request.getSession(true);
if(session.getAttribute("district")!=null && session.getAttribute("district")!=""){
dist =(String) session.getAttribute("district");
System.out.println("sess"+dist); 
} 
else{out.println("session not valid");}                     


%> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress</title>
    <script> 
            function selected(){
                
          
            
       var  a = document.getElementById("district");
  
  
   var k =a.options[a.selectedIndex].value;  
            
       alert(k)     
            
      var xmlhttp;  
// alert(ids);
   
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
    window.location="Results.jsp"
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","Filter?District="+k ,true);

xmlhttp.send();
    
     }
       
</script>
    
    
    <style>
/*        Styling of the divs*/
           
         .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            .example {
    
    width:1270px;
    height:1270px;}
        </style>
        <script>
//            Jscript code to add and delete rows
        var i=1 ;
        function addRow(tableID) {
          
            var frm = document.getElementById("form");
           
          //add each element to the table
             var table = document.getElementById(tableID);
//             var tbl = document.getElementById('table1');
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
      
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
 var cell2 = row.insertCell(1);
           // cell2.innerHTML = rowCount + 1;
 
          
            var element1= document.createElement("select");
            element1.type = "text";
            element1.name = "district_"+i;
           
//            element.onclick= alert("tesssssss");
           
//            element1.value = "";
              element1.style.width = "100px";
                element1.id = "district1";
                  var option;
 <% 
  String Querys= "SELECT districtID,DistrictName FROM districts";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(Querys);
                                                      while(conn.rs.next())
                                                           {
                                                   %>  
                                                           
                                     option = document.createElement("option");
                                     option.type="text";

                                     option.value="<%=conn.rs.getString("districtID")%>";

                                     
                                     option.innerHTML = "<%=conn.rs.getString("DistrictName")%>";
                                     
                                     element1.appendChild(option);
           
                                                   <%
                                                      
                                System.out.println("DISTRICT 2"+conn.rs.getInt("districtID"));
                                System.out.println("DISTRICT NAME"+conn.rs.getString("DistrictName")); }
            
            %>
          
            element1.onchange = function selected1(){
             var   a = document.getElementById("district1");
           
  
  
   var k =a.options[a.selectedIndex].value;  
            
       alert("SECOND"+k)     
            
      var xmlhttp;  
// alert(ids);
   
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
//    window.location="Results.jsp"
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","Filter?District="+k ,true);

xmlhttp.send();
    
     }; 
   cell2.appendChild(element1);
     
          <c:forEach  var="filter1" items="${list}"  >
          <c:set var="targetmen"  value="${filter1.QTARGETMEN}"></c:set>
          <c:set var="targetwomen"  value="${filter1.QTARGETWOMEN}"></c:set>
          <c:set var="menbaseline"  value="${filter1.MENBASELINE}"></c:set>
          <c:set var="womenbaseline"  value="${filter1.WOMENBASELINE}"></c:set>
          <c:set var="endtargetmen"  value="${filter1.ENDTARGETMEN}"></c:set>
          <c:set var="endtargetwomen"  value="${filter1.ENDTARGETWOMEN}"></c:set>
          <c:set var="district"  value="${filter1.DISTRICT}"></c:set>
              
              
               <% 
 
System.out.println(pageContext.getAttribute("district") +"___________ggggggggg______"+ dist);

if(pageContext.getAttribute("district").equals(dist) ){ %>
            var cell3 = row.insertCell(2);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "baselinew_"+i;
            element2.id = "baselinew";
            element2.value = "${filter1.WOMENBASELINE}";
              element2.style.width = "100px";
            cell3.appendChild(element2);
            
             var cell4 = row.insertCell(3);
            var element3 = document.createElement("input");
            element3.type = "text";
            element3.name = "baselinem_"+i;
            element3.id = "baselinem";
            element3.value = "${filter1.MENBASELINE}";
            element3.style.width = "100px";
            cell4.appendChild(element3);
            
            var cell5 = row.insertCell(4);
            var element4 = document.createElement("input");
            element4.type = "text";
            element4.name = "priorw_"+i;
            element4.id = "priorw";
            element4.value = "";
            element4.style.width = "100px";
            cell5.appendChild(element4);
            
            var cell6 = row.insertCell(5);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = "priorm_"+i;
            element5.id = "priorm";
            element5.value = "";
            element5.style.width = "100px";
            cell6.appendChild(element5);
           
            var cell7 = row.insertCell(6);
            var element6 = document.createElement("input");
            element6.type = "text";
            element6.name = "targetw_"+i;
            element6.id = "targetw";
            element6.value = "${filter1.QTARGETWOMEN}";
            element6.style.width = "100px";
            cell7.appendChild(element6);
           
            var cell8 = row.insertCell(7);
            var element7 = document.createElement("input");
            element7.type = "text";
            element7.name = "targetm_"+i;
            element7.id = "targetm";
            element7.value = "${filter1.QTARGETMEN}";
            element7.style.width = "100px";
            cell8.appendChild(element7);
            
            var cell9 = row.insertCell(8);
            var element8 = document.createElement("input");
            element8.type = "text";
            element8.name = "achievedw_"+i;
            element8.id = "achievedw";
            element8.value = "";
            element8.style.width = "100px";
            cell9.appendChild(element8);
            
            var cell10 = row.insertCell(9);
            var element9 = document.createElement("input");
            element9.type = "text";
            element9.name = "achievedm_"+i;
            element9.id = "achievedm";
            element9.value = "";
            element9.style.width = "100px";
            cell10.appendChild(element9);
            
            var cell11 = row.insertCell(10);
            var element10 = document.createElement("input");
            element10.type = "text";
            element10.name = "endtargetw_"+i;
            element10.id = "endtargetw";
            element10.value = "${filter1.ENDTARGETWOMEN}";
            element10.style.width = "100px";
            cell11.appendChild(element10);
            
            var cell12 = row.insertCell(11);
            var element11 = document.createElement("input");
            element11.type = "text";
            element11.name = "endtargetm_"+i;
            element11.id = "endtargetm";
            element11.value = "${filter1.ENDTARGETMEN}";
            element11.style.width = "100px";
            cell12.appendChild(element11);
                   
             i++;
      frm.h.value=i;
     
      alert("alert "+i);
            
<% } %>
 </c:forEach>
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
        </script>
    
<!--     <script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function filterer(){
    
      var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value;
  
   alert("financial"+y);

    
 var xmlhttp;  
// alert(ids);
   
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
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","filterer?FY="+y,true);

xmlhttp.send();
    
   
  
 }  
function filterer1(){
    
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
   
   alert("quarter"+v);
   

    
 var xmlhttp;  
// alert(ids);
   
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
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","filterer?QTR="+v ,true);

xmlhttp.send();
    
   
  
 }  
function filterer2(){
    
      
   var a = document.getElementById("district");
  
  
   var k =a.options[a.selectedIndex].value;
   
   
   alert("district"+k);

    
 var xmlhttp;  
// alert(ids);
   
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
    window.location(Results.jsp)
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","Filter?District="+k ,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts
</script>-->
    
    
    
    
    
</head>
<body>
   
    
   
<div class="example" style="margin-top: 0; margin-bottom: 0; width:1500px;">

    
<!--    <div><h1 style="text-align: center"><img src="images/aphia_logo.png" height="70" width="200"/></h1></div> -->
<!--    <div class="menuholder">
        <ul class="menu slide">
            <li><a href="" class="red">Home</a></li>
            <li><a href="" class="violet">Program Progress</a></li>
            <li><a href="" class="orange">Manage Forms</a>
                <div class="subs">
                    <dl style="width: 200px; text-align: center;">
                        <dt></dt>
                        <dd style="text-align: center;"><a href="http://www.script-tutorials.com/category/html-css/">Indicator Activities</a></dd>
                        <dd style="text-align: center;"><a href="http://www.script-tutorials.com/category/jquery/">Indicator Results</a></dd>
                       
                    </dl>
                   
                </div>
            </li>
            <li><a href="" class="yellow">Reports</a>
               
            </li>
            <li><a href="" class="green">Help</a></li>
           
        </ul>
        <div class="back"></div>
        <div class="shadow"></div>
    </div>-->
<!--    <div style="clear:both">-->
        <div id="container" style="width:1450px; margin-left:0; margin-right:0;padding-top: 100px; ">
 
<!--            This is results page with genders -->
 <h4>INDICATOR RESULTS</h4>
                         
    
                             
                         </table >
                          <form name=form id="form" method="post"  action="indicatorresults">
                       <table   id="dataTable" style="text-align: center" border="1" style="width:2000px;" >
                           <tr>
                               <td>Financial Year</td><td><select name="FinancialYear" id="financial" onchange="filterer()">
                                       <option value="<%= financials %>"><%= financials %></option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       
                                   </select></td>
                               <td>Quarter</td><td><select name="Quarter" id="quarter" onchange="filterer1()">
                                       <option value="<%= quarters %>"><%= quarters %></option>
                                       <option value="Q1">October-January</option>
                                       <option value="Q2">Feb-May</option>
                                       <option value="Q3">June-Sept</option>
                                      
                                       
                                   </select>
                               </td>
                               
                               
                           </tr>
                             <tr>     
                                      <th style="width:30px; text-align: left;"  >SELECT</th>
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th width="190px;">ADDITIONAL CRITERIA</th>
                                      <th style="width:140px;" colspan="2">BASELINE</th>
                                      <th style="width:185px;" colspan="2">RESULTS ACHIEVED PRIOR PERIODS</th>
                                      <th style="width:150px;" colspan="4">THIS REPORTING PERIOD</th>
                                      <th style="width:150px;" colspan="2">END OF PROJECT TARGET</th>
                                      
                                      
                                  </tr> 
                             <tr>     
                                      <th style="width:30px; text-align: left;"  ></th>
<!--                                      <th style="width:10px;"></th>-->
                                      <th style="width:100px;" ></th>
                                      <th style="width:140px;" colspan="2"></th>
                                      <th  colspan="2">Achieved</th>
                                      <th style="width:185px;" colspan="2">Target</th>
                                      <th style="width:150px;" colspan="2">Achieved</th>
                                       <th style="width:185px;" colspan="2">Target</th>
                                     
                                      
                                      
                                  </tr> 
                             <tr>    
                                      <th style="width:30px;"></th>
                                     
<!--                                      <th style="width:30px;"></th>-->
                                      <th style="width:30px;"></th>
                                      <th style="width:100px;">W</th>
                                      <th style="width:100px;">M</th>
                                      <th style="width:100px;">W</th>
                                      <th style="width:100px;">M</th>
                                      <th style="width:100px;">W</th>
                                      <th style="width:100px;">M</th>
                                      <th style="width:100px;">W</th>
                                      <th style="width:100px;">M</th>
                                      <th style="width:100px;">W</th>
                                      <th style="width:100px;">M</th>
                                      
                                      
                                  </tr> 
                       
                       
                             
                                   <tr>
                                     <td width="60px;"><INPUT type="checkbox" name="chk"/></td>
<!--                                     <td width="40px;"> 1 </td>-->
<td width="100px;"> 
                                              <select style="width: 200px;" name="district_0" id="district" onchange="selected()">
                                                  <option value="<%= district %>"><%= district %></option>
  <% 
                  String QueryDist1= "SELECT districtID,DistrictName FROM districts";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryDist1);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("districtID")%>'><%=conn.rs.getString("DistrictName")%></option>
                                                   <%
                                                      
 System.out.println(conn.rs.getInt("districtID"));
                                System.out.println(conn.rs.getString("DistrictName"));                                                      }
                                
                               
                                                   %>
                                            
                                             
                                         </select> </td>
<!--                                     <td><select style="width:140px;" name="district" id="district"></select>
                                         <option value=""></option>
                                     </td>-->
<!--creating a jstl for each to get the values stored in the bean-->
    <c:forEach  var="filter" items="${list}"  >
          <c:set var="targetmen"  value="${filter.QTARGETMEN}"></c:set>
          <c:set var="targetwomen"  value="${filter.QTARGETWOMEN}"></c:set>
          <c:set var="menbaseline"  value="${filter.MENBASELINE}"></c:set>
          <c:set var="womenbaseline"  value="${filter.WOMENBASELINE}"></c:set>
          <c:set var="endtargetmen"  value="${filter.ENDTARGETMEN}"></c:set>
          <c:set var="endtargetwomen"  value="${filter.ENDTARGETWOMEN}"></c:set>
          <c:set var="district"  value="${filter.DISTRICT}"></c:set>
         <input type="hidden" id="id" name="id" value="<%= pageContext.getAttribute("district")%>" />
      <% 
 
System.out.println(pageContext.getAttribute("district") +"_________________"+ dist);

if(pageContext.getAttribute("district").equals(dist) ){ %>
                   <td style="width:100px;"><input type="text" name="baselinew_0" value="${filter.WOMENBASELINE}" style="width:100px;"></td>
               
                                       
                                       <td style="width:100px;"><input type="text" name="baselinem_0" readonly value="${filter.MENBASELINE}" style="width:100px;"></td>
                                       <td style="width:100px;"><input type="text" name="priorw_0"  readonly value="" style="width:100px;"></td>
                                       <td style="width:100px;"><input type="text" name="priorm_0" readonly value="" style="width:100px;"></td>
                                       <td style="width:100px;"><input type="text" name="targetw_0" readonly value="${filter.QTARGETWOMEN}" style="width:100px;"></td>
                                       <td style="width:100px;"><input type="text" name="targetm_0" readonly value="${filter.QTARGETMEN}" style="width: 100px;"></td>
                                       <td style="width:100px;"><input type="text" name="achievedw_0"   value="" style="width: 100px;"></td>
                                       <td style="width:100px;"><input type="text" name="achievedm_0" value="" style="width: 100px;"></td>
                                       <td style="width:100px;"><input type="text" name="endTargetw_0" readonly value="${filter.ENDTARGETWOMEN}" style="width: 100px;"></td>
                                       <td style="width:100px;"><input type="text" name="endTargetm_0" raedonly value="${filter.ENDTARGETMEN}" style="width: 100px;"></td>
                                  <% } %>     
                              </c:forEach>        
                                  </tr>
                          
                   <INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />
 
                  <INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />
                  <input name="Submit" type="submit" value="Submit" />
                   <label>
                   <input name="h" type="text" id="h" value="0" />
                  </label>
                                                  
                       
                               
                              </table>
                                                  
                            </form>
        
        </div>
        
        
                        
        
        
    </div>
</div>
</body>
</html>
