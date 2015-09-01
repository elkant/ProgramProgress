<%-- 
    Document   : indicatorResults2
    Created on : Sep 8, 2013, 9:17:36 PM
    Author     : Maureen
--%>

<%@page import="PP.Admin.dbConnect"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%!

 dbConnect conn = new dbConnect();
                                                
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress</title>
    <style>
           
           .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            .example {
    
    width:1270px;
    height:1270px;}
        </style>
        <SCRIPT language="javascript">
        function addRow(tableID) {
 
            var table = document.getElementById(tableID);
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
 var cell2 = row.insertCell(1);
           // cell2.innerHTML = rowCount + 1;
 
          
            var element1= document.createElement("select");
            element1.type = "text";
            element1.name = "district";
            element1.id = "district";
            element1.value = "";
              element1.style.width = "190px";
            cell2.appendChild(element1);
            
             var cell3 = row.insertCell(2);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "baselinetotal";
            element2.id = "baselinetotal";
            element2.value = "";
              element2.style.width = "150px";
            cell3.appendChild(element2);
            
             var cell4 = row.insertCell(3);
            var element3 = document.createElement("input");
            element3.type = "text";
            element3.name = "priortotal";
            element3.id = "priortotal";
            element3.value = "";
            element3.style.width = "150px";
            cell4.appendChild(element3);
            
            var cell5 = row.insertCell(4);
            var element4 = document.createElement("input");
            element4.type = "text";
            element4.name = "targettotal";
            element4.id = "targettotal";
            element4.value = "";
            element4.style.width = "150px";
            cell5.appendChild(element4);
            
            var cell6 = row.insertCell(5);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = "achievedtotal";
            element5.id = "achievedtotal";
            element5.value = "";
            element5.style.width = "150px";
            cell6.appendChild(element5);
//           
//            var cell7 = row.insertCell(6);
//            var element6 = document.createElement("input");
//            element6.type = "text";
//            element6.name = "targetw";
//            element6.id = "targetw";
//            element6.value = "";
//            element6.style.width = "100px";
//            cell7.appendChild(element6);
//           
//            var cell8 = row.insertCell(7);
//            var element7 = document.createElement("input");
//            element7.type = "text";
//            element7.name = "targetm";
//            element7.id = "targetm";
//            element7.value = "";
//            element7.style.width = "100px";
//            cell8.appendChild(element7);
//            
//            var cell9 = row.insertCell(8);
//            var element8 = document.createElement("input");
//            element8.type = "text";
//            element8.name = "achievedw";
//            element8.id = "achievedw";
//            element8.value = "";
//            element8.style.width = "100px";
//            cell9.appendChild(element8);
//            
//            var cell10 = row.insertCell(9);
//            var element9 = document.createElement("input");
//            element9.type = "text";
//            element9.name = "achievedm";
//            element9.id = "achievedm";
//            element9.value = "";
//            element9.style.width = "100px";
//            cell10.appendChild(element9);
//            
//            var cell11 = row.insertCell(10);
//            var element10 = document.createElement("input");
//            element10.type = "text";
//            element10.name = "txtbox[]";
//            element10.style.width = "100px";
//            cell11.appendChild(element10);
           
 
 
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
    
    
     <script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function filterer(){
    
      var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value;
  
   alert("financial"+y);

    
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


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay2?FY="+y,true);

xmlhttp.send();
    
   
  
 }  
function filterer1(){
    
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
   
   alert("quarter"+v);
   

    
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


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay2?QTR="+v ,true);

xmlhttp.send();
    
   
  
 }  

</script>
    
    
    
</head>
<body>
   
    
    
<div class="example" style="margin-top: 0; margin-bottom: 0; margin-left: 0;margin-right:0; width:1500px;" >

 
<div id="container" style="width:1000px; border-radius:25px; padding-top: 20px;padding-bottom: 20px;">
 
<!--            This is results page with genders -->
 <h4>INDICATOR RESULTS</h4>
<!--                          <INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />
 
                         <INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />-->
                        
                            
                             
                        
                          <form name=login id="login" method="post"  action="indicatorresults">
                       <table  style="padding-top: 100px;" style="text-align: center" class="fixed">
                        <col width="165px" />
                        <col width="163px" />
                        <col width="163px" />
                        <col width="163px" />
                       
                           <tr>
                               <td>Financial Year</td><td><select name="FinancialYear" id="financial" onchange="filterer()">
                                       <option value=""></option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       
                                   </select></td>
                               <td>Quarter</td><td><select name="Quarter" id="quarter" onchange="filterer1()">
                                       <option value=""></option>
                                       <option value="Q1">October-January</option>
                                       <option value="Q2">Feb-May</option>
                                       <option value="Q3">June-Sept</option>
                                      
                                       
                                   </select>
                               </td>
                               
                               
                           </tr>
                             <tr>     
                                     
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th>ADDITIONAL CRITERIA</th>
                                      <th>BASELINE</th>
                                      <th>RESULTS ACHIEVED PRIOR PERIODS</th>
                                      <th colspan="2">THIS REPORTING PERIOD</th>
                                      <th >END OF PROJECT TARGET</th>
                                      
                                      
                                  </tr> 
                             <tr>     
                                      <th style="width:30px; text-align: left;"  ></th>
<!--                                      <th style="width:10px;"></th>-->
                                      <t></th>
                                      <th></th>
                                      <th>Achieved</th>
                                      <th>Target</th>
                                      <th>Achieved</th>
                                      <th>Target</th>
                                     
                                      
                                      
                                  </tr> 
                             <tr>    
                                      <th></th>
                                     
<!--                                      <th style="width:30px;"></th>-->
                                      <th></th>
                                      <th>Total</th>
                                      
                                      <th>Total</th>
                                      
                                      <th>Total</th>
                                      <th>Total</th>
                                     
                                      
                                      
                                  </tr> 
                       
                       
                             
                       </table>
                                            
  
                                             
                                         
                            
            <table id="tablerow" class="fixed"> </table> 
                                                  
                       
                               
                              
                         
                            </form>
        
        </div>
        
        
                        
        
        
    </div>

</body>
</html>