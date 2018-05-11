<%-- 
    Document   : ResultsMain2
    Created on : Sep 28, 2013, 8:32:08 AM
    Author     : Maureen
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@page import="PP.Admin.dbConnect"%>

<%!
dbConnect conn = new dbConnect();

 String QueryDist2="";

%>

    
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <script type="text/javascript" src="js/jquery1.8.3.min.js"></script>
    <!--<script src="js/jquery-1.9.1.js"></script>-->
    <!--<script src="js/jquery-1.7.2.js"></script>-->
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>PPMT-data entry</title>
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
<!--         <script src="js/jquery-1.7.2.js"></script>-->
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
         <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
	
        <script src="js/results.js"></script>
        <script src="js/result2.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
        
     
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

   <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>     
   <script type="text/javascript" src="js/chosen/chosen.jquery.js"></script>     
   <script type="text/javascript" src="js/chosen/chosen.jquery.js"></script>     
   <script type="text/javascript" src="js/chosen/chosen.css"></script>     
        <!--External JAvascript-->
         <!--   <script src="js/ResultsMainJS.js"></script>-->
       <script type="text/javascript" src="js/ResultsMainHelp.js"></script>
        <!-- <script type="text/javascript" src="js/result2.js"></script>-->
      
         
         
         <script>
$( document ).tooltip();

         function select(){
           var e = document.getElementById("title");
   var titleid = e.options[e.selectedIndex].value;
//           alert(titleid);
            document.getElementById("HiddenID").value=titleid;
          
           
              }
      
        </script>
       
        
        
        
        <script>
             function total(){
            var u = document.getElementById("women").value; 
            var t = document.getElementById("men").value;
            var q = parseInt(u)+ parseInt(t);
            
            document.getElementById("subtotal").value=q
            }
        </script>
         <style>
/*        Styling of the divs*/
           
         .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            .example {
    
    width:1290px;
    height:970px;
    border-radius:25px;}
          

        </style>
   <style>
           
          
            .example {
    
    width:1500px;
    height:1270px;
  overflow-y: auto;}
            #tables{
                width: 1200px;
                border-style: double;
                border:1;
               
                
                
                
            }
            input {
    border: 1px solid #e5e5e5;
}
input, button, select, textarea,p,td,h2,th,a {
    font-family: cambria;
} 
label, input, button, select, textarea,p,td  {
    font-size: 18px;
    font-weight: normal;
    line-height: 20px;
    /*border-radius:5px;*/
}
table.fixed th  a { font-size: 18px;
    font-weight: bold;
    line-height: 20px;}
        </style> 
        
        
        
<!--======================MY CUSTOM CALENDER============================================-->

    <link rel="stylesheet" type="text/css" href="js/codebase/dhtmlxcalendar.css"/>
    <link rel="stylesheet" type="text/css" href="js/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"/>
        <script type="text/javascript" src="js/codebase/dhtmlxcalendar.js"></script>
 <link rel="shortcut icon" href="images/pptlogo.png"/>
    <!--calendar based on field ids-->
         <script type="text/javascript">

var newCalendar;
function doOnLoader() {
   
    newCalendar = new dhtmlXCalendarObject(["startdate_1","startdate_2","startdate_3","startdate_4","startdate_5","startdate_6","startdate_7","startdate_8","enddate_1","enddate_2","enddate_3","enddate_4","enddate_5","enddate_6","enddate_7","enddate_8"]);
   
}


   function limitdates(){
    var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].value;   
//alert("quarters "+quarters);
if(quarters=="Q1") {
 
    newCalendar.setSensitiveRange("2013-10-01", "2013-12-31");
//alert(quarters);
   }
else if(quarters=="Q2") {
  
    newCalendar.setSensitiveRange("2014-01-01", "2014-03-31");

   }}
  
</script>
                  
<script type="text/javascript">
    function changer(b) {

//   alert(b);
   var a = document.getElementById("activity_"+b);
   var v =a.options[a.selectedIndex].value;
//   alert(v);
if((v=="1031")) {
   
       if(document.getElementById("activityOthers"+b).type==("hidden")){
         
       document.getElementById("activityOthers"+b).type="text"; 
       document.getElementById("activityOthers"+b).required="true"; 
       
       
       }}
else{ 
   
       if(document.getElementById("activityOthers"+b).type==("text")){
          

       document.getElementById("activityOthers"+b).type="hidden";    
       document.getElementById("activityOthers"+b).value=""; 
       document.getElementById("activityOthers"+b).removeAttribute("required"); 
       }

}
}
</script>
     <script type="text/javascript">

var newCalendar1;
function doOnLoad() {
   
    newCalendar1 = new dhtmlXCalendarObject(["startdate_0","start_0","end_0","startdate_1","startdate_2","startdate_3","startdate_4","startdate_5","startdate_6","startdate_7","startdate_8","startdate_9","startdate_10","startdate_11","startdate_12","startdate_13","startdate_14","startdate_15","startdate_16","startdate_17","startdate_18","startdate_19","startdate_20",
        "enddate_0","enddate_1","enddate_2","enddate_3","enddate_4","enddate_5","enddate_6","enddate_7","enddate_8","enddate_9","enddate_10","enddate_11","enddate_12","enddate_13","enddate_14","enddate_15","enddate_16","enddate_17","enddate_18","enddate_19","enddate_20","new_startdate_0","new_startdate_1","new_startdate_2","new_startdate_3","new_startdate_4","new_startdate_5","new_startdate_6","new_startdate_7","new_startdate_8","new_startdate_9","new_startdate_10","new_startdate_11","new_startdate_12","new_startdate_13","new_startdate_14","new_startdate_15","new_startdate_16","new_startdate_17","new_startdate_18","new_startdate_19","new_startdate_20",
        "new_enddate_0","new_enddate_1","new_enddate_2","new_enddate_3","new_enddate_4","new_enddate_5","new_enddate_6","new_enddate_7","new_enddate_8","new_enddate_9","new_enddate_10","new_enddate_11","new_enddate_12","new_enddate_13","new_enddate_14","new_enddate_15","new_enddate_16","new_enddate_17","new_enddate_18","new_enddate_19","new_enddate_20"]);
   
}
  function limitdates(){
    var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].value;   

if(quarters=="Q1") {
 //alert(quarters);
    newCalendar1.setSensitiveRange("2013-10-01", "2013-12-31");
//alert("quarters "+quarters);
   }
else if(quarters=="Q2") {
  
    newCalendar1.setSensitiveRange("2014-01-01", "2014-03-31");

   }

}

    
</script>
     <script type="text/javascript">

var newCalendar1;
function doOnLoad1() {
   
    newCalendar1 = new dhtmlXCalendarObject(["startdate_0","start_0","end_0","startdate_1","startdate_2","startdate_3","startdate_4","startdate_5","startdate_6","startdate_7","startdate_8","startdate_9","startdate_10","startdate_11","startdate_12","startdate_13","startdate_14","startdate_15","startdate_16","startdate_17","startdate_18","startdate_19","startdate_20",
        "enddate_0","enddate_1","enddate_2","enddate_3","enddate_4","enddate_5","enddate_6","enddate_7","enddate_8","enddate_9","enddate_10","enddate_11","enddate_12","enddate_13","enddate_14","enddate_15","enddate_16","enddate_17","enddate_18","enddate_19","enddate_20"]);
   
}

   function limitdates(){
    var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].value;   
//alert("quarters "+quarters);
if(quarters=="Q1") {
 
    newCalendar1.setSensitiveRange("2013-10-01", "2013-12-31");

   }
else if(quarters=="Q2") {
  
    newCalendar1.setSensitiveRange("2014-01-01", "2014-03-31");

   }}

    
</script>
  <script type="text/javascript">
     $(document).ready(function() {
    $("#form-A, #form-B").hide();
    });
</script> 
         
         <script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filter_district(index){
//    alert(index);
//      var cnt="county1_"+i;
//          var dist = "district"+districts;
         var distr="district_"+index;
//         alert(distr);
    var t=document.getElementById("county_"+index);
    var dist =t.options[t.selectedIndex].value;   
//alert(dist);
var xmlhttp;  
  
   
if (dist=="")
{
//filter the districts    



document.getElementById(distr).innerHTML="<option value=\"\">Choose District</option>";
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

xmlhttp.open("POST","/ProgramProgress/districtselector2?district="+dist,true);

xmlhttp.send();
    
   
  
 }  

 function totals(m){
//               var m = k;
//alert("m"+ m);
               var women = "women_"+m;

          
            var u = document.getElementById("women_"+m).value; 
           
            var t = document.getElementById("men_"+m).value;
           
            var q = parseInt(u)+ parseInt(t);
             
            document.getElementById("subtotal_"+m).value=q
            }  
//end of filter districts

</script> 
    
    
    
<!--    // for allowing other to show a text field-->

             
<script type="text/javascript">
    function new_changer(b) {

   
   var a = document.getElementById("new_activity_"+b);
   var v =a.options[a.selectedIndex].value;

if((v=="1031")) {
   
       if(document.getElementById("new_activityOthers"+b).type==("hidden")){
           
       document.getElementById("new_activityOthers"+b).type="text"; 
         document.getElementById("new_activityOthers"+b).required="true"; 
       }}
else{ 
   
       if(document.getElementById("new_activityOthers"+b).type==("text")){
           
       document.getElementById("new_activityOthers"+b).type="hidden";    
       document.getElementById("new_activityOthers"+b).value=""; 
       document.getElementById("new_activityOthers"+b).removeAttribute("required");
       }}
       }
//       if(document.getElementById("others1").type="text"){
//           
//       document.getElementById("others1").type="hidden";    
//       }



</script>
         



<!--  --------------------------------- add rows --------------------------------------------------------------------            -->
<script>
    function filterActivitys(a){
                 
    var t = a;
   
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;

       
        var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = title.split(",");
var tbID=titles[0];
var tbsID=titles[1];

         
var xmlhttp1;  

   
if (tbID=="")
{
//filter the districts    



document.getElementById('activity_'+t).innerHTML="<option value=\"\"></option>";
//document.getElementById("activityCombined").innerHTML="<option value=\"\"></option>";
return;
}
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp1=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp1.onreadystatechange=function()
{
if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
{
document.getElementById('activity_'+t).innerHTML=xmlhttp1.responseText;
$('.activities').chosen();
  $('.activities').trigger("chosen:updated");
}
}

xmlhttp1.open("POST","/ProgramProgress/activitySelector?titleID="+tbID,true);

xmlhttp1.send();

  
 } 
 
</script>


<script>
    function NewfilterActivitys(a){
                 
    var t = a;
   
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;

       
        var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = title.split(",");
var tbID=titles[0];
var tbsID=titles[1];

         
var xmlhttp1;  

   
if (tbID=="")
{
//filter the districts    



document.getElementById('new_activity_'+t).innerHTML="<option value=\"\"></option>";
//document.getElementById("activityCombined").innerHTML="<option value=\"\"></option>";
callotheractivity();
return;
}
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp1=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp1.onreadystatechange=function()
{
if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
{
document.getElementById('new_activity_'+t).innerHTML=xmlhttp1.responseText;
$('.activities').chosen();
  $('.activities').trigger("chosen:updated");
  //alert("here");
}
}

xmlhttp1.open("POST","/ProgramProgress/activitySelector?titleID="+tbID,true);

xmlhttp1.send();

  
 } 
 
</script>
     <script type="text/javascript" language="javascript">
 function filters(p) {
//     alert(p);
 var k =p;
var cnt="county_"+k;

//alert("k"+k);
  var newk=parseInt(k);
//                       
  var distr1="district_"+newk;
    var t=document.getElementById("county_"+newk);
 
   var dist3 =t.options[t.selectedIndex].value; 
//alert("t"+ dist3);
//            
      // alert(cnt) ;
    
//          var dist1 = district1.value
//       alert("disttricts"+dist1);
var xmlhttp;  
  
   
if (dist3=="")
{
//filter the districts    



document.getElementById(distr1).innerHTML="<option value=\"\">Choose District</option>";
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
    

document.getElementById(distr1).innerHTML=xmlhttp.responseText;

}
}

xmlhttp.open("POST","districtselector3?district="+dist3,true);


xmlhttp.send();
   }                  
   function totals1(m){
//               var m = k;
//alert("m"+ m);
               var women = "women_"+m;

          
            var u = document.getElementById("women_"+m).value; 
           
            var t = document.getElementById("men_"+m).value;
           
            var q = parseInt(u)+ parseInt(t);
             
            document.getElementById("subtotal_"+m).value=q
            }                                                       
    
    
    
</script>  


    
     <script type="text/javascript" language="javascript">
 function Newfilter(p) {
//     alert(p);
 var k =p;
var cnt="county_"+k;

//alert("k"+k);
  var newk=parseInt(k);
//                       
  var distr1="new_district_"+newk;
    var t=document.getElementById("new_county_"+newk);
 
   var dist3 =t.options[t.selectedIndex].value; 
//alert("t"+ dist3);
//            
      // alert(cnt) ;
    
//          var dist1 = district1.value
//       alert("disttricts"+dist1);
var xmlhttp;  
  
   
if (dist3=="")
{
//filter the districts    



document.getElementById(distr1).innerHTML="<option value=\"\">Choose District</option>";
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
    

document.getElementById(distr1).innerHTML=xmlhttp.responseText;

}
}

xmlhttp.open("POST","districtselector3?district="+dist3,true);


xmlhttp.send();
   }     
    
    
    
    
    
</script>
<SCRIPT type="text/javascript" language="javascript">



function enteryr(){
    
      if(document.getElementById("financialyr").value==null || document.getElementById("financialyr").value=="") {
               
                 alert("Remember to Select Pepfar Year");
                 $("#financial").focus();
                 
             }     
             if(document.getElementById("quarteryr").value==null || document.getElementById("quarteryr").value=="") {
               
                 alert("Remember to Select Quarter");
                  $("#quarter").focus();
                 
             }    
    
    
    
}
function enteryr1(){
    
      if(document.getElementById("financialyr1").value==null || document.getElementById("financialyr1").value=="") {
               
                 alert("Remember to Select Pepfar Year");
                 $("#financial").focus();
                 
             }     
             if(document.getElementById("quarteryr1").value==null || document.getElementById("quarteryr1").value=="") {
               
                 alert("Remember to Select Quarter");
                  $("#quarter").focus();
                 
             }    
    
    
    
}
    var j=0 ;
    var k=0 ;
    var verifier1=1;
   var verifier2=1;
function resetSeparate(){
    k=0;
    j=1;
    verifier1=1;
    verifier2=1;
    if(document.getElementById("newrows")!=null){
           document.getElementById("newrows").value="0"; 
             document.getElementById("dataTable").innerHTML="";
        
    }

    if(document.getElementById("h")!=null){
        document.getElementById("h").value="1";
    }
} 
      
           
 var b5=1;  //hplds the no of existing rows for the table to add new values

var allrowsoldnew=0;
var newrows;

           
        function addRow(tableID) {
//            alert( document.getElementById("financialyr").value);
            
    if(verifier1==1){
        newrows=parseInt(document.getElementById("newrows").value);
       // k=document.getElementById("counter").value;
    }
            
    k++;
    
    verifier1++;
    verifier2++;  
   // allrowsoldnew++; 
           
           
           
           
            var table = document.getElementById(tableID);
        
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            
            element1.type = "textarea";
            element1.name="new_unit_"+j;
            element1.style.width="120px";
            cell1.appendChild(element1);
//            var cell1 = row.insertCell(0);
//            var element1 = document.createElement("input");
//            
//            element1.type = "checkbox";
//            element1.name="chkbox[]";
//            cell1.appendChild(element1);

 
            var cell2 = row.insertCell(1);

            var option;
 <% 
 String QueryDist= "SELECT countyName,countyID FROM countyview";
 String options="";
 
 if(conn.state3.isClosed()){conn= new dbConnect();}
             
		   conn.rs3 = conn.state3.executeQuery(QueryDist);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
                                                option+="<option value='<%=conn.rs3.getInt("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell2.innerHTML="<select  id='new_county_"+j+"' required='true' style='width:120px;' name='new_county_"+j+"' onchange='filtersSeparate("+j+")'>\n\
  <option value=''></option>'"+option+"'</select>"

            
            var cell3 = row.insertCell(2);
            var element3 = document.createElement("select");
            element3.type = "text";
            element3.name='new_district_'+j;
            element3.id='new_district_'+j;
            element3.value='';
             element3.required=true;
//            element3.name = "district";
//            element3.id ="district";
            element3.multiple = true;
            
            element3.style.width = "120px";
            
          var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           
           element3.appendChild(option);
           cell3.appendChild(element3);
            
            
          
 
//            
//            var cell4 = row.insertCell(3);
//            var element4 = document.createElement("select");
//            element4.type = "text";
//            element4.style.width="120px";
//            element4.name = 'new_activity_'+j;
//            element4.id = 'new_activity_'+k;
                                 var cell4 = row.insertCell(3);
             cell4.innerHTML="<select id='new_activity_"+k+"' class='activities' required='true' name='new_activity_"+j+"' style='width:140px;' value='' onchange='new_changer("+k+")'>"

//            element4.value = '';
           
            
//            cell4.appendChild(element4);


      var element10 = document.createElement("input")
            element10.type = "hidden";
            element10.name="new_activityOthers_"+j;
            element10.id="new_activityOthers"+k;
            element10.value = '';
            element10.class = 'otheractivities';
            element10.style.width="140px";
             cell4.appendChild(element10);
             
             
            var cell5 = row.insertCell(4);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = 'new_startdate_'+j;
            element5.id = 'new_startdate_'+j;
            element5.value='';
            element5.required=true;
            element5.style.width="140px";
            element5.onclick = date();
            cell5.appendChild(element5);
            
            var cell6= row.insertCell(5);
            var element6 = document.createElement("input");
            element6.type = "text";
            element6.name = 'new_enddate_'+j;
            element6.id = 'new_enddate_'+j;
            element6.value = '';
            element6.required=true;
              element6.style.width="140px";
            cell6.appendChild(element6);
           
//            var cell7 = row.insertCell(6);
//            var element7 = document.createElement("input");
//            element7.type = "text";
//            element7.name = 'new_women_'+j;
//            element7.id = 'women_'+k;
//            element7.value = '';
//             element7.style.width = "60px";
//            cell7.appendChild(element7);
//           
//            var cell9 = row.insertCell(7);
//            cell9.innerHTML="<input type='text' id='men_"+k+"' name='new_men_"+j+"' style='width:60px;' value='' onKeyUp='totals1("+k+")'>"

            
//            var cell10 = row.insertCell(6);
//            var element9 = document.createElement("input");
//            element9.type = "text";
//            element9.name = 'new_subtotal_'+j;
//            element9.id = 'new_subtotal_'+j;
//            element9.value = '';
//             element9.style.width = "60px";
//            cell10.appendChild(element9);


             var cell10 = row.insertCell(6);
             cell10.innerHTML="<input type=text id='new_subtotal_"+j+"' required='true' name='new_subtotal_"+j+"' style='width:140px;' value='' onblur='validateActivity("+j+")'>"



//         var frm = document.getElementById("frm");
//           var as = document.getElementById("h"); 
      
//             k++;
//             alert("k  "+k);
             NewfilterActivitys(k);
//      frm.newrows.value=k;
      document.getElementById("newrows").value=j;
//      document.getElementById("counter").value =k ;
      document.getElementById("h").value =parseInt(k);
      j++;
   //  alert("alert "+document.getElementById("newrows").value);
        doOnLoad();    
//            var element10 = document.createElement("form");
 $('.activities').chosen();
  $('.activities').trigger("chosen:updated");
  
        }
 
        function deleteRow(tableID) {
                 
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
//        alert("rowCount   "+rowCount);
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
         if(parseInt(document.getElementById("newrows").value)>=parseInt(1)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
            k--;
            j--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("counter").value=k;
              document.getElementById("newrows").value=j;      
              document.getElementById("h").value=(parseInt(j));       
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
        }   
 
    </SCRIPT>
<!--    scripts for adding row 2-->
  <script>
                             function filters2(i){
                             var cnt="county1_"+i;

//  alert("cnt  "+cnt);
                        var newi=parseInt(i);
//                        alert("newi"+newi);
  var distr="district1_"+newi;
    var t=document.getElementById("county1_"+newi);
 
   var dist2 =t.options[t.selectedIndex].value; 
//   alert("districvhdvd"+dist2);
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
                                            

                         </script> 
  <script>
                             function filtersSeparate(i){
                             var cnt="county1_"+i;

//  alert("cnt  "+cnt);
                        var newi=parseInt(i);
//                        alert("newi"+newi);
  var distr="new_district_"+newi;
    var t=document.getElementById("new_county_"+newi);
 
   var dist2 =t.options[t.selectedIndex].value; 
//   alert("districvhdvd"+dist2);
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
                                            

                         </script> 
<script type="text/javascript">
    function filterActivity(){
    
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
//     alert(title);
//    var id = title.value;
////       alert("hbhb"+ids);
//       alert(id);
       
        var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = title.split(",");
var tbID=titles[0];
var tbsID=titles[1];
//alert("table id"+tbID);
//alert("table idssss"+tbsID);
         
var xmlhttp1;  
// alert(ids);
   
if (tbID==="")
{
//filter the districts    



//document.getElementById("activitySeparate").innerHTML="<option value=\"\"></option>";
//document.getElementById("activityCombined").innerHTML="<option value=\"\"></option>";
return;
}
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp1=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp1.onreadystatechange=function()
{
if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
{
document.getElementById("activitySeparate").innerHTML=xmlhttp1.responseText;
document.getElementById("activityCombined").innerHTML=xmlhttp1.responseText;
 
 
//alert("activity   "+document.getElementById("activitySeparate").value);
//alert("activity   "+document.getElementById("activityCombined").value);
}
}

xmlhttp1.open("POST","/ProgramProgress/activitySelector?titleID="+tbID,true);

xmlhttp1.send();

  
 } 

    
    
    
    
</script>          
<SCRIPT type="text/javascript" language="javascript">
 
                   var i=1 ;
        var i=0 ;
           
var verifiers1=1;
var verifiers2=1;
var allnewrows1=0;   
var t=0;
function resetCombined(){
    i=0;
    verifiers1=1;
    verifiers2=1;
    if(document.getElementById("newrows1")!=null){ 
        
        document.getElementById("newrows1").value="0"; 
        document.getElementById("dataTable1").innerHTML="";    
    

}
   
        if(document.getElementById("h1")!=null){ document.getElementById("h1").value="1";}
   if(document.getElementById("newresultrows")!=null){ document.getElementById("newresultrows").value="0";
   }
    t=0;
} 

  
  
  
          
        function addRow1(tableID1) {
         
//              if(document.getElementById("financialyr1").value==null || document.getElementById("financialyr1").value=="") {
//               
//                 alert("Remember to Select Pepfar Year");
//                 $("#financial").focus();
//                 
//             }     
//             if(document.getElementById("quarteryr1").value==null || document.getElementById("quarteryr1").value=="") {
//               
//                 alert("Remember to Select Quarter");
//                  $("#quarter").focus();
//                 
//             }                                  
            
    if(verifiers1==1){
         allnewrows1=parseInt(document.getElementById("newrows1").value);
         document.getElementById("h1").value="1";
          if(document.getElementById("count")!=null){
         i = document.getElementById("count").value;}
//        f5=parseInt(document.getElementById("old_step5_no_rows").value)+(parseInt(1));
//        b5=parseInt(document.getElementById("new_step5_no_rows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
     t++;       
    i++;
    verifiers1++;
    verifiers2++;  
   // allrowsoldnew++; 
           
            var table = document.getElementById(tableID1);
           
         
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            
            element1.type = "textarea";
            element1.name="new_unit_"+t;
             element1.style.width = "125px";
            cell1.appendChild(element1);
           
//            var cell1 = row.insertCell(0);
//            var element1 = document.createElement("input");
//            
//            element1.type = "checkbox";
//            element1.name="chkbox[]";
//            cell1.appendChild(element1);
 
//            var cell2 = row.insertCell(1);
           // cell2.innerHTML = rowCount + 1;
 
//            var cell2 = row.insertCell(1);
          
//            var element2 = document.createElement("select");
//            element2.type = "text";
//            element2.name="county_"+i;
//            element2.id="county1_"+i;
//            
//            var option1;
//             var option;
//               
//           option1 = document.createElement("option");
//           option1.value=''
//           option1.innerHTML = '';
//           
//           element2.appendChild(option1);
  var cell2 = row.insertCell(1);
//   cell2.style.width = "140px";
//            var element2 = document.createElement("select");
//            element2.type = "text";
//            element2.name="county_"+k;
//            element2.id="county_"+k;
//            element2.className="county_"+k;
//           element2.onchange = function(evt){
//                    filters(k);  
//                };  
//             var option1;
//           
//           
//         
//           
//           option1 = document.createElement("option");
//           option1.value=''
//           option1.innerHTML = '';
//           
//           element2.appendChild(option1);
           
            var option;
 <% 
 String QueryDist1= "SELECT countyName,countyID FROM countyview";
 String options1="";
                   conn.state3= conn.connect.createStatement();
		   conn.rs3 = conn.state3.executeQuery(QueryDist1);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
                                                option+="<option value='<%=conn.rs3.getInt("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell2.innerHTML="<select  style='width:120px;' required='true' id='county1_"+i+"' name='new_county_"+t+"' onchange='filters2("+i+")'>\n\
  <option value=''></option>'"+option+"'</select>"
            
//  
//            element2.onchange=function filters(){}
////                 filter_districts(this);
//
//                                                           
//          
//
////             element2.className ="filter";
//          
//
//            element2.style.width = "100px"; 
//            cell2.appendChild(element2);
            
            var cell3 = row.insertCell(2);
            var element3 = document.createElement("select");
            element3.type = "text";
            element3.name='new_district1_'+t;
            element3.id='district1_'+i;
            element3.value='';
               element3.required=true;
//            element3.name = "district";
//            element3.id ="district";
            element3.multiple = true;
            
            element3.style.width = "120px";
            
            var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           
           element3.appendChild(option);
           cell3.appendChild(element3);
            
            
          
 
            
//            var cell4 = row.insertCell(3);
//            var element4 = document.createElement("select");
//            element4.type = "text";
//            element4.name = 'new_activity_'+t;
//            element4.id = 'new_activity_'+i;
//            element4.style.width = '120px';
////            element4.value = '';
//            cell4.appendChild(element4);

            var cell4 = row.insertCell(3);
             cell4.innerHTML="<select id='new_activity_"+i+"' class='activities' required='true' name='new_activity_"+t+"' style='width:100%' value='' onchange='new_changer("+i+")'>"




                var element10 = document.createElement("input")
            element10.type = "hidden";
            element10.name="new_activityOthers_"+t;
            element10.id="new_activityOthers"+i;
            element10.value = '';
            element10.style.width="100%";
           cell4.appendChild(element10);
            
            var cell5 = row.insertCell(4);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = 'new_startdate_'+t;
            element5.id = 'new_startdate_'+t;
               element5.required=true;
              element5.style.width = '120px';
            element5.value='';
//            element5.onclick = date();
//            element5.className="datepicker";
//           element5.onclick = 
//          function dater(){
//               
//                        $( ".datepicker").datepicker({
//                                dateFormat: "dd/mm/yy",
//                                changeMonth: true,
//                                changeYear: true
//                               
//                        }); }   
//          
            cell5.appendChild(element5);
            
            var cell6= row.insertCell(5);
            var element6 = document.createElement("input");
            element6.type = "text";
            element6.name = 'new_enddate_'+t;
            element6.id = 'new_enddate_'+t;
               element6.required=true;
            element6.style.width = '120px';
            element6.value = '';
//            element6.className="datepicker";
//            element6.onclick = 
//function daters(){
//                  
////                    alert(dates2);
//                        $( ".datepicker" ).datepicker({
//                                dateFormat: "dd/mm/yy",
//                                changeMonth: true,
//                                changeYear: true
//                               
//                        }); 
//                    }   
//          
            cell6.appendChild(element6);
           
            var cell7 = row.insertCell(6);
             cell7.innerHTML="<input type=text id='new_total_"+t+"' required='true' name='new_total_"+t+"' style='width:140px;' value='' onblur='validateNo("+t+")'>"

//            var element7 = document.createElement("input");
//            element7.type = "text";
//            element7.name = 'new_total_'+t;
//            element7.id = 'total_'+i;
//            element7.value = '';
//             element7.style.width = "60px";
//            cell7.appendChild(element7);
//           
//         var cell4 = row.insertCell(3);
//             cell4.innerHTML="<select id='new_activity_"+i+"' name='new_activity_"+t+"' style='width:140px;' value='' onchange='new_changer("+i+")'>"
//
//           
         var frm1 = document.getElementById("frm1");
//            var as = document.getElementById("h");    
//         i++;
doOnLoad();
         NewfilterActivitys(i);
//         alert("i   "+i);
      frm1.h1.value=parseInt(i);
      frm1.newrows1.value=t;
       if(document.getElementById("count")!=null){
      document.getElementById("count").value=i;}
//      document.getElementById("newrows1").value=i;
     
//      alert("alert "+i);
          
//            var element10 = document.createElement("form");
  $('.activities').chosen();
  $('.activities').trigger("chosen:updated");
        }
 
        function deleteRow1(tableID) {
         
       var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
//        alert("rowCount   "+rowCount);
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
         if(parseInt(document.getElementById("newrows1").value)>=parseInt(1)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
            i--;
            t--;
           // b5--;
           // allrowsoldnew--;
         
              document.getElementById("newrows1").value=t;      
              document.getElementById("h1").value=(parseInt(i));       
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
        }   
    
    
 //================================   
    
    
    
    
    
       var k=0;
       var verifier1=1;
       var verifier2=1;
       var allcombrows1=0;
       var newrows;
       
       
    function resetaddResultsRow(){
      verifier1=1;
      verifier2=1;
      if(document.getElementById("test_count")!=null){
      
      allcombrows1=document.getElementById("test_count").value;
      }
document.getElementById("newresultrows1").value="0";
document.getElementById("g1").value="1";
      
        
    }   
       
       
 function addResultsRow(tableID) {
         

            
    if(verifier1==1){
        
  newrows=parseInt(document.getElementById("newrows1").value);
     
    }
            
    k++;
    verifier1++;
    verifier2++; 
    allcombrows1++;
   // allrowsoldnew++; 
  var table = document.getElementById(tableID);
        
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
 
   var cell1 = row.insertCell(0);
 
                var option;
 <% 
 String Querys= "SELECT countyName,countyID FROM countyview";
 String option4="";
                   conn.state3= conn.connect.createStatement();
		   conn.rs3 = conn.state3.executeQuery(Querys);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
                                                option+="<option value='<%=conn.rs3.getInt("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                        }                                                     
                                
                %>  
  cell1.innerHTML="<select  id='new_county_"+k+"' required='true' name='new_county_"+k+"' onchange='filters3("+k+");'>\n\
  <option value=''></option>'"+option+"'</select>"

              
            var cell2 = row.insertCell(1);
            var element2 = document.createElement("select");
            element2.type = "text";
            element2.name='new_district_'+k;
            element2.id='new_district_'+k;
            element2.required=true;
            element2.value='';
           
            element2.style.width = "140px";
            
           var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           element2.appendChild(option);
           cell2.appendChild(element2);
        
            
       
             
          
      
            
            var cell6 = row.insertCell(2);
//            var element6 = document.createElement("input");
//            element6.type = "text";
//            element6.name = 'new_achievedtotal_'+k;
//            element6.id = 'new_achievedtotal_'+k;
//            element6.value='';
//          
//            cell6.appendChild(element6);
            
          
             cell6.innerHTML="<input type=text id='new_achievedtotal_"+k+"' required='true' name='new_achievedtotal_"+k+"' style='width:140px;' value='' onblur='validateAchieved("+k+")'>"

           
          
         
        
      document.getElementById("newresultrows1").value =k ;
      document.getElementById("g1").value =parseInt(k);    
 }
 
function deleteResultsRow(tableID) {
    //if the addrow has not been called  
        
           
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
        //alert("allcomb1 "+allcombrows1 +" and testcount is "+document.getElementById("test_count").value);    
        //delete ntil you have one row
      //  if(parseInt(document.getElementById("newresultrows1").value) >parseInt(document.getElementById("test_count").value)){
         if( parseInt(document.getElementById("newresultrows1").value)>=1 ){
                 
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
            k--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("newresultrows1").value =k ;
            document.getElementById("g1").value =k ;
            allcombrows1--;
           // document.getElementById("test_count").value=k;
                   
                     
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
        }
    
    
    
    
    
    
      function filters3(i){
                             
                             var cnt="county1_"+i;

//  alert("cnt  "+cnt);
                        var newi=parseInt(i);
//                        alert("newi"+newi);
  var distr="new_district_"+newi;
    var t=document.getElementById("new_county_"+newi);
 
   var dist2 =t.options[t.selectedIndex].value; 
//   alert("districvhdvd"+dist2);
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

xmlhttp.open("POST","districtselector3?district="+dist2,true);


xmlhttp.send();
   }                                      
                           
    
    
    
    
    
    
    
 //=================================   
 
 //(((((((((((((((((((((((((((((((((((((((((((((((((ADD RESULTS ROW))))))))))))))))))))))))))))))))))))))))
 
 var allcombrows=0;
      
function resetResults(){
    k=0;
    p=1;
    verifier1=1;
    verifier2=1;
    allcombrows=0;
    if(document.getElementById("newresultrows")!=null){
    document.getElementById("newresultrows").value="0";
    
       }
       if(document.getElementById("g")!=null){
    document.getElementById("g").value="1";
                                             }
                                       
                if(document.getElementById("count")!=null){
                    
                
             allcombrows=document.getElementById("count").value;
                                            
                                                          }
                                       
                                         
                       }


 function addResultRow(tableID) {
         

            
    if(verifier1==1){
        
        newrows=parseInt(document.getElementById("newresultrows").value);
         if(document.getElementById("count")!=null){
       allcombrows=document.getElementById("count").value;}
       
                    }
            
    k++;
    verifier1++;
    verifier2++;  
    allcombrows++;
    
   // alert(allcombrows);
    
   // allrowsoldnew++; 
  var table = document.getElementById(tableID);
        
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
 
           
   var cell1 = row.insertCell(0);
 
                var option;
 <% 
  Querys= "SELECT countyName,countyID FROM countyview";
  option4="";
                   if(conn.state2.isClosed()){conn= new dbConnect();}
		   conn.rs3 = conn.state3.executeQuery(Querys);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
                                                option+="<option value='<%=conn.rs3.getInt("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell1.innerHTML="<select  id='new_countys_"+k+"' required='true' name='new_countys_"+p+"' onchange='filters4("+k+");'>\n\
  <option value=''></option>'"+option+"'</select>"

              
            var cell2 = row.insertCell(1);
            var element2 = document.createElement("select");
            element2.type = "text";
            element2.name='new_districts_'+p;
            element2.id='new_districts_'+k;
             element2.required=true;
            element2.value='';
           
            element2.style.width = "140px";
            
           var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           element2.appendChild(option);
           cell2.appendChild(element2);

//            var cell8 = row.insertCell(2);
//            var element8 = document.createElement("input");
//            element8.type = "text";
//            element8.name = 'new_achievedw_'+p;
//            element8.id = 'new_achievedw_'+k;
//            element8.value = '';
//             element8.style.width = "140px";
//            cell8.appendChild(element8);
            
            
            
             var cell8 = row.insertCell(2);
             cell8.innerHTML="<input type=text id='new_achievedw_"+p+"' required='true' name='new_achievedw_"+p+"' style='width:100%;' value='' onblur='validateWomen("+p+")'>"

            
//            var cell9 = row.insertCell(3);
//            var element9 = document.createElement("input");
//            element9.type = "text";
//            element9.name = 'new_achievedm_'+p;
//            element9.id = 'new_achievedm_'+k;
//            element9.value = '';
//             element9.style.width = "140px";
//            cell9.appendChild(element9);
            
         var cell9 = row.insertCell(3)
             cell9.innerHTML="<input type=text id='new_achievedm_"+p+"' required='true' name='new_achievedm_"+p+"' style='width:100%;' value='' onblur='validateMen("+p+")'>"


            
        
      document.getElementById("newresultrows").value =p ;
      document.getElementById("g").value =parseInt(k);   
      p++;
 }
 
function deleteResultRow(tableID) {
    //if the addrow has not been called  
        
           
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
            //alert(allcombrows);  
        //delete ntil you have one row
      //  if(parseInt(allcombrows)> parseInt(document.getElementById("count").value) && parseInt(allcombrows)>0 ){
        if( parseInt(document.getElementById("newresultrows").value)>=1 ){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
         
            //reduce the number of rows           
            k--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("newresultrows").value=k;
            document.getElementById("g").value=k;
            //document.getElementById("count").value=k;
            allcombrows--;       
          // alert(allcombrows);          
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
            }
        }    
 
 
 
 
 
 
 
 
 //=======================FILTER4===============================================
 
 
 
    
      function filters4(i){
                             
                             var cnt="county1_"+i;

//  alert("cnt  "+cnt);
                        var newi=parseInt(i);
//                        alert("newi"+newi);
  var distr="new_districts_"+newi;
    var t=document.getElementById("new_countys_"+newi);
 
   var dist2 =t.options[t.selectedIndex].value; 
//   alert("districvhdvd"+dist2);
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

xmlhttp.open("POST","districtselector3?district="+dist2,true);


xmlhttp.send();
   }                                      
                           
    
    
    
    
    
    
    
 //=================================  
 
 
 
 
 //==========================================================================================
 
    //==========================================================================================//
function quarterlabel(yr){                                                                  //
                                                                                            //
    var curyear=yr.value;                                                                   //
    var prevyr=parseInt(curyear)-(1);                                                        //
var qtrs="<option value=\"\"></option>";                                                     // 
    var allquarters= new Array("Oct-Dec","Jan-March","April-June","July-Sept");              //
    var allquarters1= new Array("Q1","Q2","Q3","Q4");                                        //
    for(var a=0;a<allquarters.length;a++){                                                   //
                                                                                             //
    if(a==0){                                                                                //
    qtrs+="<option value=\""+allquarters1[a]+"\">"+allquarters[a]+" "+prevyr+"</option>";    //
    }                                                                                        //
    else{                                                                                    //
     qtrs+="<option value=\""+allquarters1[a]+"\">"+allquarters[a]+" "+curyear+"</option>";  //    
                                                                                             //
    }                                                                                        //
    }                                                                                        //
    document.getElementById("quarter").innerHTML=qtrs;                                       //
                                                                                             //
}                                                                                            //
                                                                                             //
//===========================================================================================    
    
    
    
    
    
    
    
    
    
    
    
    </SCRIPT>

  <script type="text/javascript" src="js/addResultRows.js"></script>
  <script type="text/javascript" src="js/addResultsCombined.js"></script>
  <script type="text/javascript" src="js/validateNumbers.js"></script>
<!--  --------------------------------- end of add rows --------------------------------------------------------------------            -->

<style>
    input[type=Submit] {padding:5px 15px; background:#c66200; border:0 none;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }
    input[type=button] {padding:5px 15px; background:#c66200; border:0 none;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }
</style>
    
    
    <!--    // script for validating date -->

<script>
function validatedates(){
    //alert("a");
   // alert(document.getElementById("newrows").value);
        var noofrows = document.getElementById("newrows").value;
        //alert("kkk"+noofrows);
for(var a=0;a<=noofrows;a++){

if (compare(a)==false){

break;

}

}
}


function compare(suffix){
var inputdate = new Date($("#new_enddate_"+suffix).val());
var startdate = new Date($("#new_startdate_"+suffix).val());
//alert("inputdate"+inputdate);
if(inputdate!="" || startdate!=""){

var dateOBj= new Date();
//dateOBj.toLocaleFormat('%d-%b-%Y'); 

var currdate=dateOBj.getDate()+1;
var curmonth=dateOBj.getMonth()+1;
var curyear=dateOBj.getFullYear();

var today=curyear+"/"+curmonth+"/"+currdate;

var dates = new Date();
//alert("today "+dates);
var todate = new Date(today);
var daters = new Date(inputdate);

//alert("end date"+todate+" "+inputdate);
//alert("start"+todate+" "+startdate);
//alert("converted"+todate+" "+daters);

if (inputdate > todate && inputdate!=null && inputdate!="")

{

// Do something
alert("End Date should not be greater than today");
$("#new_enddate_"+suffix).focus();
return false;

}
else if (startdate > todate && startdate!=null && startdate!="")

{

// Do something
alert("Start Date should not be greater than today");
$("#new_startdate_"+suffix).focus();
return false;

}

else if (startdate > inputdate && startdate!=null && startdate!="")

{

// Do something
alert("Start Date should not be greater than End Date ");
$("#new_startdate_"+suffix).focus();
return false;

}

else{
    return true;
}

}

}

    </script>
  
    
    <script>
        
        function validatequarters1(){
    //alert("a");
//    alert(document.getElementById("newrows1").value);
        var noofrows = document.getElementById("newrows").value;
//        alert("kkk"+noofrows);
for(var a=1;a<=noofrows;a++){

if (comparesquarters(a)==false){

break;

}

}
}
function comparesquarters(suffix){
        var e = document.getElementById("quarter");
var quarter = e.options[e.selectedIndex].value;
var quarters = e.options[e.selectedIndex].text;
        

//alert(quarter);

if(document.getElementById("new_enddate_"+suffix)!=null || document.getElementById("new_startdate_"+suffix)!=null){
  if(document.getElementById("new_enddate_"+suffix).value != null && document.getElementById("new_enddate_"+suffix).value!="") { 
var enddate = document.getElementById("new_enddate_"+suffix).value;


var c = new Array();
c= enddate.split("-");

if(quarter=="Q1" &&(c[1]=="10" || c[1]=="11" || c[1]=="12" )){
    return true;
}

 else if(quarter=="Q2" &&(c[1]=="01" || c[1]=="02" || c[1]=="03" )){
    
    return true;
}

else if(quarter=="Q3" &&(c[1]=="04" || c[1]=="05" || c[1]=="06" )){
    
    return true;
}
//quarter 2 Jan-March
//quarter 4 Aug-Sept
else if(quarter=="Q4" &&(c[1]=="07" || c[1]=="08" || c[1]=="09" )){
    
    return true;
}
else{
  alert("Date does not lie within "+quarters);  
    return false;
}

}



else if(document.getElementById("new_startdate_"+suffix).value!=null  && document.getElementById("new_startdate_"+suffix).value!=""){
var strtdate = document.getElementById("new_startdate_"+suffix).value;

//alert(strtdate);
var y = new Array();
y= strtdate.split("-");

if(quarter=="Q1" &&(y[1]=="10" || y[1]=="11" || y[1]=="12" )){
    return true;
}

 else if(quarter=="Q2" &&(y[1]=="01" || y[1]=="02" || y[1]=="03" )){
    
    return true;
}

else if(quarter=="Q3" &&(y[1]=="04" || y[1]=="05" || y[1]=="06" )){
    
    return true;
}
//quarter 2 Jan-March
//quarter 4 Aug-Sept
else if(quarter=="Q4" &&(y[1]=="07" || y[1]=="08" || y[1]=="09" )){
    
    return true;
}
else{
  alert("Date does not lie within "+quarters);  
    return false;
}


}
}      
}     
        
    </script>
    <script>
        function validateyears1(){
    //alert("a");
//    alert(document.getElementById("newrows1").value);
        var noofrows = document.getElementById("newrows").value;
//        alert("kkk"+noofrows);
for(var a=1;a<=noofrows;a++){

if (compareyear1(a)==false){

break;

}

}
}
function compareyear1(suffix){
      
        var f = document.getElementById("financial");
var financial = f.options[f.selectedIndex].value;

//alert(quarter);

if(document.getElementById("new_enddate_"+suffix)!=null || document.getElementById("new_startdate_"+suffix)!=null){
if(document.getElementById("new_enddate_"+suffix).value!=null && document.getElementById("new_enddate_"+suffix).value!=""){

var enddate = document.getElementById("new_enddate_"+suffix).value;
//alert("end date "+enddate);
var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].text;   
 var y = new Array();

y= quarters.split(" ");       


var v = new Array();
v= enddate.split("-");

//alert("year__"+v[0]);

if( y[1]==v[0]){
    return true;
    
}
else{
  alert("End Date Year does not lie within the "+y[1]);  
    return false;
}
}

else if( document.getElementById("new_startdate_"+suffix).value!=null && document.getElementById("new_startdate_"+suffix).value!=""){
var strtdate = document.getElementById("new_startdate_"+suffix).value;


var y = new Array();
y= strtdate.split("-");



var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].text;   
 var z = new Array();

z= quarters.split(" ");       

//alert("year__"+y[0]);
//alert("yearsssssssssssd__"+z[1]);

if( z[1]==y[0]){
    return true;
    
}
else{
  alert("Start Date Year does not lie within  "+z[1]);  
    return false;
}
//quarter 3 April-July 


}
        
} }    
        
    </script>
    
<!--  // end of separate   -->
    
<!--    // for combined data -->
    <script>
function validatedates1(){
    //alert("a");
   // alert(document.getElementById("newrows").value);
        var noofrows = document.getElementById("newrows1").value;
        //alert("kkk"+noofrows);
for(var a=1;a<=noofrows;a++){

if (compare1(a)==false){

break;

}

}
}


function compare1(suffix){
var inputdate = new Date($("#new_enddate_"+suffix).val());
var startdate = new Date($("#new_startdate_"+suffix).val());

//alert("aaaaaa"+document.getElementById("quarter").value);

if(inputdate!="" || startdate!=""){

var dateOBj= new Date();

var currdate=dateOBj.getDate()+1;

var curmonth=dateOBj.getMonth()+1;
var curyear=dateOBj.getFullYear();

var today=curyear+"/"+curmonth+"/"+currdate;
var todate = new Date(today);
var dater = new Date(inputdate);

//alert(todate+" "+inputdate);
//alert(todate+" "+dater);

if (inputdate > todate && inputdate!=null && inputdate!="")

{

// Do something
alert("End Date should not be greater than today ");
$("#new_enddate_"+suffix).focus();

return false;

}
else if(startdate > todate && startdate!=null && startdate!="")
    
    {

// Do something
alert("Start Date should not be greater than today");

$("#new_startdate_"+suffix).focus();
return false;

}
 else if (startdate > inputdate && startdate!=null && startdate!="")

{

// Do something
alert("Start Date should not be greater than End Date ");
$("#new_startdate_"+suffix).focus();
return false;

}   

else{
    return true;
}

}


}

    </script>
    <script>
        
        function validatequarters(){
    //alert("a");
//    alert(document.getElementById("newrows1").value);
        var noofrows = document.getElementById("newrows1").value;
//        alert("kkk"+noofrows);
for(var a=1;a<=noofrows;a++){

if (compares1(a)==false){

break;

}

}
}
function compares1(suffix){
        var e = document.getElementById("quarter");
var quarter = e.options[e.selectedIndex].value;
var quarters = e.options[e.selectedIndex].text;
        var f = document.getElementById("financial");
var financial = f.options[f.selectedIndex].value;

//alert(quarter);

if(document.getElementById("new_enddate_"+suffix)!=null ||document.getElementById("new_startdate_"+suffix)!=null){
    if(document.getElementById("new_enddate_"+suffix).value!=null && document.getElementById("new_enddate_"+suffix).value!=""){
var enddate = document.getElementById("new_enddate_"+suffix).value;


var c = new Array();
c= enddate.split("-");

if(quarter=="Q1" &&(c[1]=="10" || c[1]=="11" || c[1]=="12" )){
    return true;
}

 else if(quarter=="Q2" &&(c[1]=="01" || c[1]=="02" || c[1]=="03" )){
    
    return true;
}

else if(quarter=="Q3" &&(c[1]=="04" || c[1]=="05" || c[1]=="06" )){
    
    return true;
}
//quarter 2 Jan-March
//quarter 4 Aug-Sept
else if(quarter=="Q4" &&(c[1]=="07" || c[1]=="08" || c[1]=="09" )){
    
    return true;
}
else{
  alert("Date does not lie within "+quarters);  
    return false;
}

}


else if(document.getElementById("new_startdate_"+suffix).value !=null  && document.getElementById("new_startdate_"+suffix).value!=""){
var strtdate = document.getElementById("new_startdate_"+suffix).value;

//alert(strtdate);
var y = new Array();
y= strtdate.split("-");

if(quarter=="Q1" &&(y[1]=="10" || y[1]=="11" || y[1]=="12" )){
    return true;
}

 else if(quarter=="Q2" &&(y[1]=="01" || y[1]=="02" || y[1]=="03" )){
    
    return true;
}

else if(quarter=="Q3" &&(y[1]=="04" || y[1]=="05" || y[1]=="06" )){
    
    return true;
}
//quarter 2 Jan-March
//quarter 4 Aug-Sept
else if(quarter=="Q4" &&(y[1]=="07" || y[1]=="08" || y[1]=="09" )){
    
    return true;
}
else{
  alert("Date does not lie within "+quarters);  
    return false;
}


}}
}     
    
        
    </script>
    <script>
        function validateyears(){
    //alert("a");
//    alert(document.getElementById("newrows1").value);
        var noofrows = document.getElementById("newrows1").value;
//        alert("kkk"+noofrows);
for(var a=1;a<=noofrows;a++){

if (compareyear(a)==false){

break;

}

}
}
function compareyear(suffix){
    
if(document.getElementById("new_enddate_"+suffix)!=null||document.getElementById("new_startdate_"+suffix)!=null ){
    
    if(document.getElementById("new_enddate_"+suffix).value!=null && document.getElementById("new_enddate_"+suffix).value!=""){

var enddate = document.getElementById("new_enddate_"+suffix).value;
//alert("end date "+enddate);
var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].text;   
 var y = new Array();

y= quarters.split(" ");     


var v = new Array();
v= enddate.split("-");
//alert("pepfar yr end "+y[1]);
//alert("selected year end"+v[0]);
//alert("year__ end"+v[0]);

if( y[1]==v[0]){
    return true;
    
}
else{
  alert("Year does not lie within "+y[1]);  
    return false;
}
    }


else if(document.getElementById("new_startdate_"+suffix).value!=null && document.getElementById("new_startdate_"+suffix).value!=""){
var strtdate = document.getElementById("new_startdate_"+suffix).value;

//alert("startdate"+strtdate);
var y = new Array();
y= strtdate.split("-");


var e = document.getElementById("quarter");
var quarters = e.options[e.selectedIndex].text;   
 var k = new Array();

k= quarters.split(" ");   
//alert("year__"+y[0]);
//alert("pepfar"+y[0]);
//alert("selected"+k[1]);
if( k[1]==y[0]){
    return true;
    
}
else{
  alert("Year does not lie within "+k[1]);  
    return false;
}
//quarter 3 April-July 


}
        
}  }   
        
    </script>

<link rel="stylesheet" href="css/chosen.css" type="text/css" media="all"/>
<!--<link rel="stylesheet" href="docsupport/style.css">
<link rel="stylesheet" href="docsupport/prism.css">-->


    </head>
                         <body >
                       

   <%
   dbConnect con=new dbConnect();
 
 //get the helps from the database.
 
 String Sectionshelp[]=new String [12];
int mcount=0;

if(con.state.isClosed()){con= new dbConnect();}
con.rs=con.state.executeQuery("Select * from help where help_id<='12'");
while(con.rs.next()){
    
Sectionshelp[mcount]=con.rs.getString(2);
if(mcount<=12){
mcount++;
}
}

%>
    
    <div class="example" style="width:1400px;height: 800px;">
   
     
               <% String logStatus="";
      logStatus= (String)session.getAttribute("loggedIn");
       String username=(String)session.getAttribute("Username");
               if(logStatus==null && logStatus==""){
                    response.sendRedirect("index.jsp");
     %>
<!--                  <a class="button-1" href="index.jsp">Login</a>-->
     <%        } else{
     %>
       <h5>Welcome <%=username%></h5>               
     <a href="../ProgramProgress/LogoutServlet">LogOut</a>
     <%
               }
       
      
      
      %>
   

 
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

       
                      

   <table class="fixed" style="margin-top: 100px; margin-left:40px;width:95%; ">
       

<tr id="dialog1" title=" ">
    
    
      <h4>
                        <%
                            if (session.getAttribute("replytext") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("replytext")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                
                session.removeAttribute("replytext");
                            }

                        %>
                        </h4>
  
    <td>
         <% if(Sectionshelp[1]!=null){%>
         <%=Sectionshelp[1]%> 
         <%}%>
    </td>
    </tr>
  
                             <tr><th style="width:15%">  <a href="#" id="dialog-link1" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/> </a>INDICATOR TITLE</th>
                                 <!--<Th><select name=""  id="title" data-placeholder="Choose an indicator..." class="chosen-select"  id="choose-form" onchange="resetSeparate();resetCombined();filterID(this);filtersID(this);" required>-->
                                 <Th colspan='3'><select name=""  id="title" data-placeholder="Choose an indicator..."  style="width:99%;"  onchange="resetSeparate();resetCombined();filterID(this);filtersID(this);" required>
                                  <option value="">Type to filter indicator</option>
                                  
                                  <% 
                                  String designation="";
                                   if (session.getAttribute("AccessLevel") != null) {
                                       
                                     
                                         String AccessLevel=(String)session.getAttribute("AccessLevel");
                                           if(AccessLevel.equals("1")){
                                           
                                               String Query= "SELECT * FROM indicatortitles where active='yes' ";
//                                                     conn.state= conn.connect.createStatement();
                                               
                                               if(conn.state2.isClosed()){conn= new dbConnect();}
				conn.rs2 = conn.state2.executeQuery(Query);
                                                                      while(conn.rs2.next())
                                                           {
                                                      
                                                         %>   
                                                                                                 
            <option value='<%=conn.rs2.getString("titleID")%>,<%=conn.rs2.getString("tableIdentifier")%>'><%=conn.rs2.getString("title")%></option>
                                           
                                           
                                           
                                      <%     
                                           
                                                                                                                    }
                                           }else
                                           {                  String QueryDists= "SELECT * FROM indicatortitles where designation LIKE '%"+AccessLevel+"%' and active='yes' ";
//                                                     conn.state= conn.connect.createStatement();
                                                              
                                                              if(conn.state.isClosed()){conn= new dbConnect();}
                                                                conn.rs = conn.state.executeQuery(QueryDists);
                                                      while(conn.rs.next())
                                                           {
                                                        designation = conn.rs.getString("designation"); 
                                                        System.out.println("designation"+designation);
                                                        if(designation!=null && !designation.equals("")){
                                                         
                                                         %>   
                                                                                                 
            <option value='<%=conn.rs.getString("titleID")%>,<%=conn.rs.getString("tableIdentifier")%>'><%=conn.rs.getString("title")%></option>
<%}}
                                                                                                                                                               }}  %>
                                 
                                 
                             </select></Th>
                                                 
                             
                             
                               
                                     
                                         

                                 
                                         </select></TD></tr>
                               <tr>
                                   <th >PEPFAR Year</th><th><select name="FinancialYear" id="financial" required onChange=";resetSeparate();resetCombined();resetResults();resetaddResultsRow();financial(); filterer(); quarterlabel(this)">
                                       <option value="">Select Pepfar Year</option>
                                        <option value='' >Choose year</option>
                                            <%
                                                
                                                Calendar cal= Calendar.getInstance();
                                                int curyear=cal.get(Calendar.YEAR);
                                                
                                            for(int a=2016;a<=curyear;a++){
                                             out.println("<option value='"+a+"'>"+a+"</option>");
                                                %>
                                            
                                            
                                            <%
                                            }
                                            
                                            %>
                                            
                                  
<!--                                       <input type="text" name="financialyr" id="financialyr" value=""></td> -->
                                   </select></th>
                               <th>Quarter</th><th><select name="Quarter" id="quarter" required onChange="quarter();filterer1(); resetSeparate();resetCombined();resetResults();resetaddResultsRow(); ">
                                       <option value="">Select  Quarter</option>
                                       <option value="Q1">Oct-Dec</option>
                                       <option value="Q2">Jan-March</option>
                                       <option value="Q3">April-June</option>
                                       <option value="Q4">July-Sept</option>
                                      
                                                                
<!--                                                   <input type="text" name="quarter" id="quarters" value="">          -->
                                   </select>
                               </th>
                               
                               
                           </tr>
                             

                                         
                         </table>
                                                
                                                   
                                                   
                                                   
                                                   <div id="form-A" >
                                                       <div>

 
         <div id="container" style="width:1300px; border-radius:25px; padding-left:20px; padding-right:20px; margin-left:0; margin-right:0; padding-top: 20px;padding-bottom: 20px;" >
 
 
<!--            This is the the indicators page with separate genders -->
<form action="IndicatorUpdater" method="post" name="frm" id="frm" >
    <input type="hidden" name="financial" value="" required id="financialyr">
        <input type="hidden" name="quarter" value="" required id="quarteryr">
             <input type="hidden" name="titles" required id="titles" value="">  
                   <label>
                   <input name="h" type="hidden" id="h" value="1" />
                   <input name="newrows" type="hidden" id="newrows" value="0" />
                  </label>
                        <h4>INDICATOR ACTIVITIES</h4>
                        <table class="fixed" style="text-align: center;"> 
                          
                            <tr>
                                <td colspan="4"></td>
                              
                                <td><input name="Submit" type="submit" value="Save" style="background-color:#1385e5;color: white; " onmouseover="validatequarters1();validateyears1();validatedates();" /> </td>
                                <td><input id="addrowbtn" type="button" value="Add Row" style="background-color: #1385e5; color: white;" onClick="addRow('dataTable')" onmouseover="enteryr();" /></td>
                                <td><input type="button" value="Delete Row" style="background-color: #1385e5;color: white;" onClick="deleteRow('dataTable')" /></td>
                             </tr>
                       
   
                             <tr><td>
 
                              <a href="#" id="dialog-link2" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/>
                                     
                                     
                                 </td><td colspan="6"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>
                              <tr ><th>Unit </th>

                                      <th >County</th>
                                      <th>Geographic Location </th>
                                      <th >Activity Title</th>
                                      <th >Start Date</th>
                                      <th >End Date</th>
                                      <th >Achieved</th>
                                     
                                    
                                 
                             
                             </tr>
                        
                        
                        </table>
                        <table id="tablerow" class="fixed">
                                    </table>
                        <table id="dataTable" class="fixed" style="text-align: center;">
                                    
<!--<tr id="dialog2" title="Program Progress Help ">
  
    <td>
        
    </td>
    </tr>
   
                             <tr><td>
 
                              <a href="#" id="dialog-link2" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/>
                                     
                                     
                                 </td><td colspan="8"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>-->
<!--                             <tr><th></th>

                                      <th>County</th>
                                      <th>Geographic Location</th>
                                      <th>Activity Title</th>
                                      <th>Start Date</th>
                                      <th>End Date</th>
                                      <th>Women</th>
                                      <th>Men</th>
                                      <th>Sub Total</th>
                                  </tr> 
                             -->

                
                                                  
 


<!--                   <label>
                   <input name="h" type="hidden" id="h" value="0" /></label>-->
<!--                  <label><input type="text" id="h" value="0"/>
                  </label>-->
                                                  
                              </table>
                                                    
                              <h4 style='display:none;'>INDICATOR RESULTS   <a href="#" id="dialog-link3" style="padding-right: 0px;">
                                      <img src="images/help_24.png"/></a></h4>
    
    
                           <tr>   <td><input type="button" value="Add Row" style="background-color: #c66200;display:none;" onClick="addResultRow('tablerow1')" /></td>
                         <td><input type="button" value="Delete Row" style="background-color: #c66200;display:none;" onClick="deleteResultRow('tablerow1')" /></td></tr>
                          
                                   
                                                   <table style='display:none;'  class="fixed" id="tablerow1"  >
                                                                                         
<tr id="dialog3" title="Program Progress Helpddd ">
  
    <td>
         <% if(Sectionshelp[3]!=null){%>
         <%= Sectionshelp[3]%> 
         <%}%>
    </td>
    </tr>
                                       
                        <col width="110px" />
                        <col width="112px" />
                        <col width="112px" />
                        <col width="112px" />
                        <col width="112px" />
                       
                                                   
                             <tr>     

                                      <th colspan="2">  Additional Criteria</th>
                                
                                     
                                      <th  colspan="2">This reporting period <input type="hidden" class="convert" id="CurrentReportQuarter" value="" name="ReportQuarter">
                                                  <input type="hidden" class="convert"id="CurrentReportYear" value="" name="ReportYear">
</th>
                                   
                                      
                                      
                                  </tr> 
                             <tr>     
                                      <th colspan="2"></th>
                                   
                                    
                                      <th style="width:10px;">County</th>
                                      <th style="width:10px;">District</th>
                                     
                                   
                                     
                                    
                                      <th colspan="2">Achieved</th>
                              
                                     
                                      
                                      
                                  </tr> 
                             <tr>    
                                      <th>County</th>
                                      <th>District</th>
                                     
                                     
                                   
                                    
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                  
                                      
                                      
                                  </tr> 
                                  
                        <tr><td></td><td><input type="" name="districts_0" value="" id="districts_0"></td>
                         
                    
                   
                            <td><input type="" name="achievedw_0" value="" id="achievedw_0"></td>
                            <td><input type="" name="achievedm_0" value="" id="achievedm_0"></td>
                         
                         <label>
                   <input name="resultcount" type="hidden" id="g" value="1" />
                   <input name="newresultrows" type="hidden" id="newresultrows" value="0" />
                  </label>
                        
                        
                        
                        
                        
                        </tr>
            
                        
                        </table> 
                        <table id="tablerow1" class="fixed"> 
                         
                        
                        
                        </table>  
                                </form>
    
        
</div>
                                                       </div></div>

                                                   <div id="form-B" >
                                                       
                                                         
<div >

 
         <div id="container" style="width:1300px; border-radius:25px; padding-top: 20px;padding-bottom: 20px; padding-left:20px; padding-right:20px; margin-left:20px ">
<!--            This is the login page-->
       
       
                        <h4>INDICATOR ACTIVITIES  <a href="#" id="dialog-link4" style="padding-right: 0px;">
                                      <img src="images/help_24.png"/></a>
                        </h4>
<form name="frm1" id="frm1" method="post"  action="IndicatorUpdater1" >
                         <input type="hidden" name="financial"required value="" id="financialyr1">
                         <input type="hidden" name="quarter" required value="" id="quarteryr1">
                             <input type="hidden" name="titles1" required id="titles1" value="">  
                                  <label>
                   <input name="h1" type="hidden" id="h1" value="1" />
                    <input name="newrows1" type="hidden" id="newrows1" value="0" />
                  </label>
                                 <table class="fixed" style="text-align: center">
                                      <label>
<!--                       <input type="hidden" id="h1" value="0"/>-->
                  </label>
                                     <tr>  <td colspan="4"></td>
                                         <td> <input name="Submit" type="submit"  style="background-color:#1385e5;color: white; " value="Save" onmouseover="validatequarters();validateyears(); validatedates1();"/></td>
                                            <td><INPUT id="addrowbtn1" type="button"  style="background-color:#1385e5;color: white; " value="Add Row" onclick="addRow1('dataTable1')" onmouseover="enteryr1();" /></td>
                                                    <td>    <INPUT type="button" value="Delete Row" style="background-color:#1385e5;color: white;" onclick="deleteRow1('dataTable1')" /></td>
 
 
 </tr>
                            <tr><td>                                                                                                                    
<tr id="dialog4" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[4]!=null){%>
         <%= Sectionshelp[4]%> 
         <%}%>
    </td>
    </tr> </td><td colspan="6"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>
                                    <tr>
                                     <th >Unit </th>
                                     <th>County</th>
                                      <th >Geographic Location </th>
                                      <th >Activity Title</th>
                                      <th >Start Date</th>
                                      <th >End Date</th>
                                      <th  >Achieved</th>
                                     
                                    
                                  </tr> 
                                     
                                     
                                 </table>
        <table id="results1" class="fixed"> </table> 
                        <table id="dataTable1" class="fixed" style="text-align: center; " >
                          </table>
                                <table  style="text-align: center" class="fixed" id="">
                        <col width="165px" />
                        <col width="163px" />
                        <col width="163px" />
                        <col width="163px" />
                       
                        <h4>INDICATOR RESULTS <a href="#" id="dialog-link5" style="padding-right: 0px;">
                                      <img src="images/help_24.png"/></a> </h4>     
                          
                               
                                                                                                                      
<tr id="dialog5" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[5]!=null){%>
         <%= Sectionshelp[5]%> 
         <%}%>
    </td></tr>
      <tr>   <td><input type="button" value="Add Row" style="background-color: #c66200;" onClick="addResultsRow('results2')" /></td>
                         <td><input type="button" value="Delete Row" style="background-color: #c66200;" onClick="deleteResultsRow('results2')" /></td></tr>               
         <table class="fixed" id="results2">
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th colspan="2"> ADDITIONAL CRITERIA</th>
                                    
                                  
                                      <th colspan="1">THIS REPORTING PERIOD</br>
                                      <input type="hidden" class="convert" id="CurrentReportQuarter1" value="" name="ReportQuarter1"><br/>
                                                  <input type="hidden" class="convert"id="CurrentReportYear1" value="" name="ReportYear1"></th>
                                     
                                      
                                                      
            </tr> 
            <tr>     
                <th   ></th>
                <!--                                      <th style="width:10px;"></th>-->
                <th></th>
                                          
                                          
                <th>Achieved</th>
                                          
                                          
                                          
                                          
            </tr> 

                       
                       
                             
                       </table>
                                            
  
                                             
                                         
                            
            <table id="results2" class="fixed">
                        
            
            </table> 
                      <!--results2--->                        
            
            <table id="" class="fixed">
              <tr>     
                                     
                                    
                                      <th>ADDITIONAL CRITERIA</th>
                                      <th>BASELINE</th>
                                      <th>RESULTS ACHIEVED PRIOR PERIODS</th>
                                      <th colspan="2">THIS REPORTING PERIOD</th>
                                      <th >END OF PROJECT TARGET</th>
                                      
                                      
                                  </tr> 
                             <tr>     
                                      <th style="width:30px; text-align: left;"  ></th>
                                      <th style="width:10px;"></th>
                                      <t></th>
                                      <th></th>
                                      <th>Achieved</th>
                                      <th>Target</th>
                                      <th>Achieved</th>
                                      <th>Target</th>
                                     
                                      
                                      
                                  </tr> 
                             <tr>    
                                      <th></th>
                                     
                                      <th style="width:30px;"></th>
                                      <th></th>
                                      <th>Total</th>
                                      
                                      <th>Total</th>
                                      
                                      <th>Total</th>
                                      <th>Total</th>  
                                      <label>
                   <input name="resultcounts1" type="hidden" id="g1" value="1" />
                   <input name="newresultrows1" type="hidden" id="newresultrows1" value="0" />
                  </label>
            </table>                                    
                       
                               
                                                  
                         
                            </form>
    
        
</div>
</div>                     
        
                                                       
                                                       
                                                       
                                                       
                                                   </div>                        
                                                   
<!--                        <div>
                            <iframe id="Iframe1" seamless="seamless" align="middle" style="padding-top: 0px;"scrolling="auto" frameborder="0" width="1500" height="900" src="" ></iframe>
                   </div>-->

        
</div>
                        
        
        
   
</div>
    <div style="margin-bottom:-1px;">
        <h3 style="text-align: center;">Host Name :<b><i> <%=conn.dbsetup[0]%></i></b> &nbsp;   Database Name :<i> <%=conn.dbsetup[1]%></i></h3>

    </div>
    <script src="scripts/chosen.jquery.js" type="text/javascript"></script>
    <script>
    jQuery(document).ready(function(){
	jQuery(".chosen").chosen();
        
});
 
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  
</script> 
    <style>
        table.fixed th {
    font-family: cambria;
    /*font-family: fantasy;*/
    font-size: 15px;
}
.chosen-container {
    font-size: 15px;
      text-align: left;
      /*width:100%;*/
      /*text-height:*/ 
     /*white-space: nowrap;*/

  
}
#title_chosen{
     width:600px;
}   </style>
    <script>
	$(document).ready(function() {
	    $("#title").chosen();

	});
               
    
    $(document).ready(function() {
//	    $(".activities").searchable({
//	        maxListSize: 100,                       
//	        maxMultiMatch: 50,                     
//	        exactMatch: false,                     
//	        wildcards: true,                       
//	        ignoreCase: true,                      
//	        latency: 200,                           
//	        warnMultiMatch: 'top {0} matches ...',  
//	        warnNoMatch: 'no matches ...',         
//	        zIndex: 'auto'                         
//
//	    });
	    $(".activities").chosen();

	});
    
    
    function callotheractivity(){
        
	    $(".activities").chosen();
             $('.activities').trigger("chosen:updated");

	
        
        
    }
    
    
    
    function calladdrow(){
        
        
        $("#addrowbtn").click();
        $("#addrowbtn1").click();
    }
    
	</script>  
</body>
</html>
<%

         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.rs3!=null){ conn.rs3.close();}
         if(conn.rs4!=null){ conn.rs4.close();}
         if(conn.rs5!=null){ conn.rs5.close();}
         if(conn.rs6!=null){ conn.rs6.close();}
//         if(conn.rs7!=null){ conn.rs7.close();}
        
         if(conn.state!=null){ conn.state.close();}
         if(conn.state1!=null){ conn.state1.close();}
         if(conn.state2!=null){ conn.state2.close();}
         if(conn.state3!=null){ conn.state3.close();}
         if(conn.state4!=null){ conn.state4.close();}
         if(conn.state5!=null){ conn.state5.close();}
         if(conn.state6!=null){ conn.state6.close();}
//        / if(conn.state7!=null){ conn.state7.close();}
%>

