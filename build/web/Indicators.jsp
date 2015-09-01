<%-- 
    Document   : Indicators
    Created on : Sep 13, 2013, 6:24:32 PM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="PP.Admin.dbConnect"%>

<%!
dbConnect conn = new dbConnect();


%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
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
 <link rel="shortcut icon" href="images/pptlogo.png"/>
     <script>	
                function date(){
                   
                    
                        $( ".datepicker" ).datepicker({
                                dateFormat: "dd/mm/yy",
                                changeMonth: true,
                                changeYear: true
                             
                               
                        });
                           
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
           
          
//             var table = document.getElementById(tableID);
             
//             document.getElementById(tableID).addEventListener("change",function(e) {
	// e.target is the clicked element!
	// If it was a list item
//	if(e.target && e.target.id == "county_"+i) {
//		// List item found!  Output the ID!
//                 alert("vgacasacvghcv");
//		filters();
//               
//	}
//});
//             document.getElementById(tableID).addEventListener("click",function(e) {
//	// e.target is the clicked element!
//	// If it was a list item
//	if(e.target && e.target.id == "datepicker") {
//		// List item found!  Output the ID!
//		date();
//	}
//});
//            
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
                                dateFormat: "dd/mm/yy",
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
                                dateFormat: "dd/mm/yy",
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
//alert("IDS" +ids);
//  

        
if (ids=="")
{
//filter the districts    



document.getElementById("titleNo").innerHTML="<option value=\"\">Choose Title No</option>";
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
IndicatorFilt(); 
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
    function date_checker(){
        
        var start_date,end_date,dateObject,day,month,year,current_date;
    start_date=document.getElementById("start").value;
    end_date=document.getElementById("end").value;
//    alert(start_date);
//    alert(end_date);
    
    //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=day+"/"+month+"/"+year; 
//    alert(current_date);    
    //we compare the date
    if(new Date(start_date) < new Date(current_date)){
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
    
    }
</script>

        
     <script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function filterer(){
    
      var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value;
  
//   alert("financial"+y);
//  document.getElementById("financialyr").value = y;
 
  

    
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
  
//   alert("titlevbgvbv"+s);
   
    
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


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay?title="+s,true);

xmlhttp.send();
    
   
  
 }  
function filterer1(){
    
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
//   document.getElementById("quarters").value = v;
   
   
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
//    if(document.getElementById("financialyr").value!=null && document.getElementById("financialyr").value!="" 
//            && document.getElementById("titles").value!=null && document.getElementById("titles").value!=""){   
//    

document.getElementById("tablerow").innerHTML=xmlhttp.responseText;
//}
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
        
</head>
<body>
   
    
 
    
              
    
    
    
<div class="example" style="margin-top: 0; margin-bottom: 0; width:1500px;">

 
         <div id="container" style="width:1250px; border-radius:25px; padding-left:20px; padding-right:20px; padding-top:100px; margin-left:0; margin-right:0; padding-top: 20px;padding-bottom: 20px;" >
 
 
<!--            This is the the indicators page with separate genders -->
<form action="indicatoractivities" method="post"name="frm">
       
                        <h4>INDICATOR ACTIVITIES</h4>
                       
                        <table id="dataTable" class="fixed" style="text-align: center" >
                        <col width="150px" />
                        <col width="50px" />
                        <col width="100px" />
                        <col width="120px" />
                        <col width="120px" />
                        <col width="120px" />
                        <col width="120px" />
                        <col width="70px" />
                        <col width="70px" />
                        <col width="70px" />
                             <tr><th></td><td colspan="9"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>
                             <tr><th></th>
                                      <th>Select</th>
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th>County</th>
                                      <th>Geographic Location</th>
                                      <th>Activity Title</th>
                                      <th>Start Date</th>
                                      <th>End Date</th>
                                      <th>Women</th>
                                      <th>Men</th>
                                      <th>Sub Total</th>
                                  </tr> 
                             
                             <tr><th rowspan="10">Unit <textarea name="unit"  ></textarea></th>
                                     <td><INPUT type="checkbox" name="chk"/></td>
<!--                                     <td width="40px;"> 1 </td>-->
                                     <td> 
                                              <select onChange="filter_districts(this);" style="width:100px;" name="county_0" id="county">
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
                                       <td><input type="text" name="startdate_0" value="" class="datepicker" id="start" required value="" onclick="date()"></td>
                                      <td><input type="text" name="enddate_0" value="" class="datepicker" id="end" required value="" onclick="date()"></td>
                                      <td ><input type="text" name="women_0" id="women" value="" style="width: 60px;"></td>
                                     
                                      <td><input type="text" name="men_0" id="men" value="" onKeyUp="total()" style="width: 60px;"></td>
                                      <td><input type="text" name="subtotal_0" id="subtotal"  value="" style="width: 60px;"></td>
                                     
                                  </tr>
                 
           
                                                  
                       
                               <INPUT type="button" value="Add Row" onClick="addRow('dataTable')" />
 
                  <INPUT type="button" value="Delete Row" onClick="deleteRow('dataTable')" />
                  <input name="Submit" type="submit" value="Submit" />
                   <label>
                   <input name="h" type="hidden" id="h" value="0" />
                  </label>
                                                  
                              </table>
                                                    
                         
                                      
                                                   
                                                   
                                                   
                                                   
                                                   <h4>INDICATOR RESULTS</h4>
                          
                         
                          

                               
                           
                              
                                    <table  class="fixed">
                                       
                        <col width="100px" />
                        <col width="100px" />
                       
                        
                           <tr>
                               <td>Financial Year</td><td><select name="FinancialYear" id="financial" onChange="filterer()">
                                       <option value=""></option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
<!--                                       <input type="text" name="financialyr" id="financialyr" value=""></td> -->
                                   </select></td>
                               <td>Quarter</td><td><select name="Quarter" id="quarter" onChange="filterer1()">
                                       <option value=""></option>
                                       <option value="Q1">October-January</option>
                                       <option value="Q2">Feb-May</option>
                                       <option value="Q3">June-Sept</option>
                                      
                                                                
<!--                                                   <input type="text" name="quarter" id="quarters" value="">          -->
                                   </select>
                               </td>
                               
                               
                           </tr>
                                    </table>
                                                   <table  class="fixed">
                                       
                        <col width="110px" />
                        <col width="112px" />
                        <col width="112px" />
                        <col width="112px" />
                        <col width="112px" />
                       
                                                   
                             <tr>     
<!--                                      <th style="width:30px; text-align: left;"  >SELECT</th>-->
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th>  Additional Criteria</th>
                                      <th  colspan="2">Baseline</th>
                                      <th  colspan="2">Results achieved prior periods</th>
                                      <th  colspan="4">This reporting period</th>
                                      <th  colspan="2">End of Project Target</th>
                                      
                                      
                                  </tr> 
                             <tr>     
                                      <th  text-align: left;"  ></th>
<!--                                      <th style="width:10px;"></th>-->
                                     
                                      <th colspan="2"></th>
                                      <th colspan="2">Achieved</th>
                                      <th colspan="2">Target</th>
                                      <th colspan="2">Achieved</th>
                                      <th colspan="2">Target</th>
                                     
                                      
                                      
                                  </tr> 
                             <tr>    
                                      <th></th>
                                     
<!--                                      <th style="width:30px;"></th>-->
<!--                                      <th style="width:30px;"></th>-->
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                      
                                      
                                  </tr> 
                                    </table>
                                                   
  
                        <table id="tablerow" class="fixed"> 
                         
                        
                        
                        </table>  
                                </form>
    
        
</div>
</div>                     
        
        
   
</div>
</body>
</html>
