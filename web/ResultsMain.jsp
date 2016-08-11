<%-- 
    Document   : ResultsMain
    Created on : Sep 9, 2013, 7:33:58 AM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@page import="PP.Admin.dbConnect"%>

<%!

dbConnect conn = new dbConnect();

HttpSession session;
 String QueryDist2="";
%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Program Progress</title>
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css"/>
         <link rel="stylesheet" href="themes/smoothness/jquery.ui.all.css"/>
<!--         <script src="js/jquery-1.7.2.js"></script>-->
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.datepicker.js"></script>
         <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
	 <script src="js/jquery-ui-1.10.3.custom.js"></script>
        <script src="js/ResultsMainJS.js"></script>
        <script type="text/javascript" src="js/ResultsMainHelp.js"></script>
        
       <!--   <script type="text/javascript" src="js/result2.js"></script>-->
        
<!--        <script type="text/javascript" src="js/addResultRows.js"></script>-->
          <script>
$( document ).tooltip();
</script>
   <style>
           
          
            .example {
    
    width:1500px;
    height:1270px;}
            #tables{
                width: 1200px;
                border-style: double;
                border:1;
                
                
                
                
            }
        </style> 

 <script>	
                function date(){
                   
                    
                        $( ".datepicker" ).datepicker({
                                dateFormat: "dd/mm/yy",
                                changeMonth: true,
                                changeYear: true
                             
                               
                        });
                           
                     }   
            </script>
        
        
               
<script type="text/javascript">
    function change() {

   
   var a = document.getElementById("activitySeparate");
   var v =a.options[a.selectedIndex].value;
  
if((v=="1031")) {
   
       if(document.getElementById("othersSeparate").type==("hidden")){
           
       document.getElementById("othersSeparate").type="text";    
       }}
else{ 
   
       if(document.getElementById("othersSeparate").type==("text")){
           
       document.getElementById("othersSeparate").type="hidden";    
       document.getElementById("othersSeparate").value="";    
       }
//       if(document.getElementById("others1").type="text"){
//           
//       document.getElementById("others1").type="hidden";    
//       }

}
}
</script>
<script type="text/javascript">
    function change1() {

   
   var a = document.getElementById("activityCombined");
   var v =a.options[a.selectedIndex].value;
  
if((v=="1031")) {
   
       if(document.getElementById("othersCombined").type==("hidden")){
           
       document.getElementById("othersCombined").type="text";    
       }}
else{ 
   
       if(document.getElementById("othersCombined").type==("text")){
           
       document.getElementById("othersCombined").type="hidden";    
       document.getElementById("othersCombined").value="";    
       }
//       if(document.getElementById("others1").type="text"){
//           
//       document.getElementById("others1").type="hidden";    
//       }

}
}
</script>
               
<script type="text/javascript">
    function changer(b) {

   
   var a = document.getElementById("activity_"+b);
   var v =a.options[a.selectedIndex].value;
 
if((v=="1031")) {
   
       if(document.getElementById("activityOthers"+b).type==("hidden")){
           
       document.getElementById("activityOthers"+b).type="text";    
       }}
else{ 
   
       if(document.getElementById("activityOthers"+b).type==("text")){
           
       document.getElementById("activityOthers"+b).type="hidden";    
       document.getElementById("activityOthers"+b).value="";    
       }
//       if(document.getElementById("others1").type="text"){
//           
//       document.getElementById("others1").type="hidden";    
//       }

}
}
</script>
         
<!--======================MY CUSTOM CALENDER============================================-->

    <link rel="stylesheet" type="text/css" href="js/codebase/dhtmlxcalendar.css"/>
    <link rel="stylesheet" type="text/css" href="js/codebase/skins/dhtmlxcalendar_dhx_skyblue.css"/>
    <script src="js/codebase/dhtmlxcalendar.js"></script>
    
    <!--calendar based on field ids-->
         <script type="text/javascript">

var newCalendar;
function doOnLoad() {
   
    newCalendar = new dhtmlXCalendarObject(["startdate_0","start_0","end_0","startdate_1","startdate_2","startdate_3","startdate_4","startdate_5","startdate_6","startdate_7","startdate_8","enddate_0","enddate_1","enddate_2","enddate_3","enddate_4","enddate_5","enddate_6","enddate_7","enddate_8"]);
   
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
        <script type="text/javascript">
     $(document).ready(function() {
    $("#form-A, #form-B").hide();
    });
</script>
        <script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filter_districts(district){
    
          var dist = district.value

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

xmlhttp.open("POST","districtselector2?district="+dist,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts

</script>
        <script type="text/javascript">
// a function that filters the districts in the passed county as per the county drop down.
    
function filter_district1(districts){
    
          var dist = districts.value

var xmlhttp;  
  
   
if (dist=="")
{
//filter the districts    



document.getElementById("districts").innerHTML="<option value=\"\">Choose District</option>";
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
document.getElementById("districts").innerHTML=xmlhttp.responseText;
}
}

xmlhttp.open("POST","/ProgramProgress/districtselector2?district="+dist,true);

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

}
}

xmlhttp1.open("POST","/ProgramProgress/activitySelector?titleID="+tbID,true);

xmlhttp1.send();

  
 } 
 
</script>       
<SCRIPT type="text/javascript" language="javascript">
    
    
    
    function filterscopy(p) {
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

xmlhttp.open("POST","/ProgramProgress/districtselector2?district="+dist3,true);


xmlhttp.send();
   }                  
    
    
    
    
    
    
    
    
    
 function filters(p) {
//     alert(p);
 var k =p;
var cnt="county__"+k;

//alert("k"+k);
  var newk=parseInt(k);
//                       
  var distr1="district__"+newk;
    var t=document.getElementById("county__"+newk);
 
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

xmlhttp.open("POST","/ProgramProgress/districtselector3?district="+dist3,true);


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
              
               var k=0 ;
             var b5=1;  //hplds the no of existing rows for the table to add new values
var verifier1=1;
var verifier2=1;
var allrowsoldnew=0; 
var newrows;
              
function resetSeparate(){
    k=0;
    verifier1=1;
    verifier2=1;
      if(document.getElementById("newrows")!=null){ document.getElementById("newrows").value="0";}
      if(document.getElementById("h")!=null){ document.getElementById("h").value="1";}
    
} 
      
           
        function addRow(tableID) {
         

            
    if(verifier1==1){
        newrows=parseInt(document.getElementById("newrows").value);
     
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
            
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
//            var cell2 = row.insertCell(1);
           // cell2.innerHTML = rowCount + 1;
 
            var cell2 = row.insertCell(1);
           
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
 String QueryDist= "SELECT countyName,countyID FROM county";
 String options="";
                   conn.state3= conn.connect.createStatement();
		   conn.rs3 = conn.state3.executeQuery(QueryDist);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
 option+="<option value='<%=conn.rs3.getInt("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell2.innerHTML="<select  id='county_"+k+"' name='county_"+k+"' onchange='filterscopy("+k+");'>\n\
  <option value=''></option>'"+option+"'</select>"

              
            var cell3 = row.insertCell(2);
            var element3 = document.createElement("select");
            element3.type = "text";
            element3.name='district_'+k;
            element3.id='district_'+k;
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
             cell4.innerHTML="<select id='activity_"+k+"' name='activity_"+k+"' style='width:140px;' value='' onchange='changer("+k+")'>"

//            var element4 = document.createElement("select");
//            element4.type = "text";
//            element4.style.width="140px";
//            element4.name = 'activity_'+k;
//            element4.id = 'activity_'+k;
//            element4.value = '';
             var element10 = document.createElement("input")
            element10.type = "hidden";
            element10.name="activityOthers_"+k;
            element10.id="activityOthers"+k;
            element10.value = '';
            element10.style.width="140px";
            
//            cell4.appendChild(element4);
            cell4.appendChild(element10);
            
            var cell5 = row.insertCell(4);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = 'startdate_'+k;
            element5.id = 'startdate_'+k;
            element5.value='';
            element5.onclick = date();
            cell5.appendChild(element5);
            
            var cell6= row.insertCell(5);
            var element6 = document.createElement("input");
            element6.type = "text";
            element6.name = 'enddate_'+k;
            element6.id = 'enddate_'+k;
            element6.value = '';
            cell6.appendChild(element6);
           
          
            
            var cell10 = row.insertCell(6);
            var element9 = document.createElement("input");
            element9.type = "text";
            element9.name = 'subtotal_'+k;
            element9.id = 'subtotal_'+k;
            element9.value = '';
             element9.style.width = "60px";
            cell10.appendChild(element9);
//         var frm = document.getElementById("frm");
//           var as = document.getElementById("h"); 
      
//             k++;
//             alert("k  "+k);
             filterActivitys(k);
//      frm.newrows.value=k;
      document.getElementById("newrows").value=k;
      document.getElementById("h").value =parseInt(k)+parseInt(1);
   //  alert("alert "+document.getElementById("newrows").value);
        doOnLoad();    
//            var element10 = document.createElement("form");
 
        }
 
        function deleteRow(tableID) {
          //if the addrow has not been called  
        
           
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
        if(parseInt(document.getElementById("newrows").value) > parseInt(0)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
            k--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("newrows").value=k;
            document.getElementById("h").value=(parseInt(k)+parseInt(1));
                   
                     
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
        }
 
    </SCRIPT>
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

xmlhttp.open("POST","districtselector2?district="+dist2,true);


xmlhttp.send();
   }                                      
                                            
                             function filters3(i){
                             
                             var cnt="county1__"+i;

//  alert("cnt  "+cnt);
                        var newi=parseInt(i);
//                        alert("newi"+newi);
  var distr="district__"+newi;
    var t=document.getElementById("county__"+newi);
 
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
                                            

                         </script>
<!--    scripts for adding row 2-->
<script src="js/ResultsMainJS.js" type="text/javascript"></script>      
<SCRIPT type="text/javascript" language="javascript">
         
       
        var i=0;
           
var verifiers1=1;
var verifiers2=1;
var allnewrows1=0;  

function resetCombined(){
    i=0;
    verifiers1=1;
    verifiers2=1;
   if(document.getElementById("newrows1")!=null){ 
       document.getElementById("newrows1").value="0";
   }
   if(document.getElementById("h1")!=null){ 
       document.getElementById("h1").value="1";
   }
}



     function addRow1(tableID1) {
         

            
    if(verifiers1==1){
  allnewrows1=parseInt(document.getElementById("newrows1").value);
//        f5=parseInt(document.getElementById("old_step5_no_rows").value)+(parseInt(1));
//        b5=parseInt(document.getElementById("new_step5_no_rows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
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
            
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
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
 String QueryDist1= "SELECT countyName,countyID FROM county";
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
                         cell2.innerHTML="<select  id='county1_"+i+"' name='county_"+i+"' onchange='filters2("+i+")'>\n\
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
            element3.name='district1_'+i;
            element3.id='district1_'+i;
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
            
            
          
 
            
//            var cell4 = row.insertCell(3);
//            var element4 = document.createElement("select");
//            element4.type = "text";
//            element4.name = 'activity_'+i;
//            element4.id = 'activity_'+i;
//            element4.style.width = '140px';
////            element4.value = '';
//            cell4.appendChild(element4);

  
            var cell4 = row.insertCell(3);
             cell4.innerHTML="<select id='activity_"+i+"' name='activity_"+i+"' style='width:140px;' value='' onchange='changer("+i+")'>"

//            var element4 = document.createElement("select");
//            element4.type = "text";
//            element4.style.width="140px";
//            element4.name = 'activity_'+k;
//            element4.id = 'activity_'+k;
//            element4.value = '';
             var element10 = document.createElement("input")
            element10.type = "hidden";
            element10.name="activityOthers_"+i;
            element10.id="activityOthers"+i;
            element10.value = '';
            element10.style.width="140px";
            
//            cell4.appendChild(element4);
            cell4.appendChild(element10);
            
            
            var cell5 = row.insertCell(4);
            var element5 = document.createElement("input");
            element5.type = "text";
            element5.name = 'startdate_'+i;
            element5.id = 'startdate_'+i;
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
            element6.name = 'enddate_'+i;
            element6.id = 'enddate_'+i;
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
            var element7 = document.createElement("input");
            element7.type = "text";
            element7.name = 'total_'+i;
            element7.id = 'total_'+i;
            element7.value = '';
             element7.style.width = "60px";
            cell7.appendChild(element7);
           
        
           
         var frm1 = document.getElementById("frm1");
//            var as = document.getElementById("h");    
//         i++;
doOnLoad();
         filterActivitys(i);
//         alert("i   "+i);
      frm1.h1.value=parseInt(i)+parseInt(1);
      frm1.newrows1.value=i;
//      document.getElementById("newrows1").value=i;
     
//      alert("alert "+i);
          
//            var element10 = document.createElement("form");
 
        }
 
        function deleteRow1(tableID) {
          //if the addrow has not been called  
        
           
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
        if(parseInt(document.getElementById("newrows1").value)> parseInt(0)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
            i--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("newrows1").value=i;
            document.getElementById("h1").value= (parseInt(i)+ parseInt(1));
                   
                     
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
        }
 
    </SCRIPT>
<!--                         <script src="js/addResultsCombined.js" type="text/javascript"></script>-->


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
   
if (tbID=="")
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
<!--   // for adding the lower part of the results             -->
<script>
   var allnewoldrows=0;
   
 
 
function resetResultsCombined1(){
    k=0;
    verifier1=1;
    verifier2=1;
    var newrows;
    allnewoldrows=0;
  
    if(document.getElementById("newrows1")!=null){
        
        document.getElementById("newrows1").value="0";
    }
    if(document.getElementById("g1")!=null){
        
        document.getElementById("g1").value="0";
    }
   
   
} 
 function addResultsRow(tableID) {
         

            
    if(verifier1==1){
        newrows=parseInt(document.getElementById("newrows1").value);
        if(document.getElementById("resultcounts1")!=null){
            
            allnewoldrows=document.getElementById("resultcounts1").value;
            
        }
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
 
                var option;
 <% 
 String Querys= "SELECT countyName,countyID FROM county";
 String option4="";
                   conn.state3= conn.connect.createStatement();
		   conn.rs3 = conn.state3.executeQuery(Querys);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
                                                option+="<option value='<%=conn.rs3.getString("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell1.innerHTML="<select  id='county__"+k+"' name='newcounty__"+k+"' onchange='filters3("+k+");'>\n\
  <option value=''></option>'"+option+"'</select>"

              
            var cell2 = row.insertCell(1);
            var element2 = document.createElement("select");
            element2.type = "text";
            element2.name='newdistrict__'+k;
            element2.id='district__'+k;
            element2.value='';
           
            element2.style.width = "140px";
            
           var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           element2.appendChild(option);
           cell2.appendChild(element2);
        
            
         
            var cell6 = row.insertCell(2);
            var element6 = document.createElement("input");
            element6.type = "text";
            element6.name = 'newachievedtotal_'+k;
            element6.id = 'newachievedtotal_'+k;
            element6.value='';
            
            cell6.appendChild(element6);
            
        
           
          
         
        allnewoldrows++;
      document.getElementById("newresultrows1").value =k ;
      document.getElementById("g1").value =parseInt(k);    
 }
 
function deleteResultsRow(tableID) {
    //if the addrow has not been called  
        
           
        var table = document.getElementById(tableID);
        //delete the last row 
            
      // alert(allnewoldrows);      
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
        if(parseInt(allnewoldrows)> parseInt(document.getElementById("resultcounts1").value)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
          
            k--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("newresultrows1").value=k;
            document.getElementById("g1").value=k;
            allnewoldrows--;       
                     
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
        }    
    // }
    
   
      
          </script>
                         
                         <script>
                          
    k=0;
    
function resetResults(){
    k=0;
    verifier1=1;
    verifier2=1;
    if(document.getElementById("newresultrows")!=null){
        
        document.getElementById("newresultrows").value="0";
        
    }
    if(document.getElementById("g")!=null){
        
        document.getElementById("g").value="0";
        
    }
} 
 function addResultRow(tableID) {
         

            
    if(verifier1==1){
        newrows=parseInt(document.getElementById("newresultrows").value);
     
    }
            
    k++;
    verifier1++;
    verifier2++;  
   // allrowsoldnew++; 
  var table = document.getElementById(tableID);
        
      var lastRow = table.rows.length;
      var iteration = lastRow - 1;
      var row = table.insertRow(lastRow);
 
             var cell2 = row.insertCell(0);
           
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
 String Query5= "SELECT countyName,countyID FROM county";
 String options5="";
                   conn.state3= conn.connect.createStatement();
		   conn.rs3 = conn.state3.executeQuery(Query5);
                                  while(conn.rs3.next())
                                         {
                                                        
                                                   %>
                                                option+="<option value='<%=conn.rs3.getInt("countyID")%>'><%=conn.rs3.getString("countyName")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell2.innerHTML="<select  id='county__"+k+"' name='newcounty__"+k+"' onchange='filters("+k+");'>\n\
  <option value=''></option>'"+option+"'</select>"

              
            var cell2 = row.insertCell(1);
            var element2 = document.createElement("select");
            element2.type = "text";
            element2.name='newdistrict__'+k;
            element2.id='district__'+k;
            element2.value='';
            element2.style.width = "160px";
            
          var option;  
           option = document.createElement("option");
           option.value=''
           option.innerHTML = '';
           
           element2.appendChild(option);
            cell2.appendChild(element2);
  






            
           
          
            
            var cell9 = row.insertCell(2);
            var element9 = document.createElement("input");
            element9.type = "text";
            element9.name = 'newachievedw_'+k;
            element9.id = 'achievedw_'+k;
            element9.value = '';
             element9.style.width = "160px";
            cell9.appendChild(element9);
            
            var cell10 = row.insertCell(3);
            var element10 = document.createElement("input");
            element10.type = "text";
            element10.name = 'newachievedm_'+k;
            element10.id = 'achievedm_'+k;
            element10.value = '';
             element10.style.width = "160px";
            cell10.appendChild(element10);
            
          
            
        
      document.getElementById("newresultrows").value =k ;
      document.getElementById("g").value =parseInt(k);    
 }
 
//function deleteResultRows(tableID) {
//    //if the addrow has not been called  
//        
//           
//        var table = document.getElementById(tableID);
//        //delete the last row 
//            
//             
//            
//        var rowCount = table.rows.length;
//        //  alert(""+rowCount);
//        //alert(rowCount);
//        // for(var a=0; a<rowCount; a++) {
//        //    var row = table.rows[a];
//        //  var chkbox = row.cells[0].childNodes[0]; 
//              
//        //delete ntil you have one row
//        if(parseInt(document.getElementById("count").value)>= parseInt(document.getElementById("oldresultrows").value)){
//                  
//            table.deleteRow(rowCount-1);
//            rowCount--;
//                    
//            //reduce the number of rows           
//            i--;
//           // b5--;
//           // allrowsoldnew--;
//            document.getElementById("count").value=i;
//                   
//                     
//        }
//        else{
//                
//            alert("Maximum deletable rows reached!!");
//                
//        }
//        }    
    // }
    
   
   
   
   
   function deleteResultRow(tableID){
       
           var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
        if(parseInt(document.getElementById("newresultrows").value)> parseInt(0)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
          
            k--;
           // b5--;
           // allrowsoldnew--;
            document.getElementById("newresultrows").value=k;
            document.getElementById("g").value=k;
                   
                     
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
   }
   
   
   
   
      
                      
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
              
                             
                             
    //==========================================================================================//
function changeyear(){                                                                  //
                                                                     //
           // alert("called");                                              //
var yrs="<option value=\"\"></option>";                                                     // 
    var allyrs= new Array("2010","2011","2012","2013","2014","2015","2016","2017","2018");              //
                                         //
    for(var a=0;a<allyrs.length;a++){                                                   //
                                                                                             //
                                                                                        //
     yrs+="<option value=\""+allyrs[a]+"\">"+allyrs[a]+"</option>";  //    
                                                                                             //
                                                                                           //
    }                                                                                        //
    document.getElementById("financial").innerHTML=yrs;                                       //
                                                                                             //
}                                                                                            //
                                                                                             //
//===========================================================================================    
              
                             
                             
                             
                         </script>
                         
</head>
                         <body onload="doOnLoad();resetCombined();resetResults();">
 
                             <div class="example" style="overflow-y:auto; width:1300px;height:1000px;">
     <div>
       <% String logStatus="";
      logStatus= (String)session.getAttribute("loggedIn");
               if(logStatus==null && logStatus==""){
                    response.sendRedirect("index.jsp");
     %>
<!--                  <a class="button-1" href="index.jsp">Login</a>-->
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
       // response.sendRedirect("index.jsp");
    } 
%>
     


 <% 
 
 //get the helps from the database.
 
 String Sectionshelp[]=new String [12];
int mcount=0;
conn.rs2=conn.state2.executeQuery("Select * from help where help_id<='12'");
while(conn.rs2.next()){
    
Sectionshelp[mcount]=conn.rs2.getString(2);
if(mcount<=12){
mcount++;
}
}

%>
 
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

  
       
     </div> 
   
<!--    <div style="clear:both"></div>-->
        
<!--            This is the login page-->
<!--<div id="container">-->
       
             <input type="hidden" name="titles" id="titles" value="">             
<!--                          <INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />
 
                         <INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />-->

<!--<div style="padding-left: 0px;">-->


    <table class="fixed" style="padding-top: 100px;">
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


<tr id="dialog1" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[0]!=null){%>
         <%=Sectionshelp[0]%> 
         <%}%>
    </td>
    </tr>
       
                             <tr>   <th style="width:200px">
                                     <a href="#" id="dialog-link1" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/> </a>
                                     INDICATOR TITLE</th>
                                 <Th><select name="" style="width:300px" id="title" id="choose-form" onchange="filterID(this);filterActivity();resetSeparate();changeyear();resetResults();">
                                  <option value=""></option>
                                  
                                  <% 
                  String QueryDists= "SELECT title,titleID,tableIdentifier FROM indicatortitles";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryDists);
                                                      while(conn.rs.next())
                                                           {
                                                          
                                                          
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("titleID")%>,<%=conn.rs.getString("tableIdentifier")%>'><%=conn.rs.getString("title")%></option>
           
                
                
                                                   <%
                                                                                                  }    
                                                      
// System.out.println(conn.rs.getInt("titleID"));
//                                System.out.println(conn.rs.getString("title"));                                                      }
                                
                               
                                                   %>
                                 
                                 
                             </select></Th>
                                                 
                             
                             <th>INDICATOR NUMBER:</th>
                             <Th><p id="titleNo" ><p></Th>
                               
                                     
                                         

                                 
                                         </select></TD></tr>
                                                   <tr>
                               <th> Pepfar Year</th><th><select name="FinancialYear" id="financial" required onchange="quarterlabel(this);resetSeparate();resetCombined();resetResults();" >
                                       <option value=""></option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       <option value="2014">2014</option>
                                       <option value="2015">2015</option>
                                       <option value="2016">2016</option>
                                       <option value="2017">2017</option>
                                       <option value="2018">2018</option>
                                       <option value="2019">2019</option>
<!--                                       <input type="text" name="financialyr" id="financialyr" value=""></td> -->
                                   </select></th>
                               <th>Quarter</th><th><select name="Quarter" id="quarter" required onChange="filterer1();resetSeparate();resetCombined();resetResults();">
                                       <option value=""></option>
                                       <option value="Q1">Oct-Dec</option>
                                       <option value="Q2">Jan-March</option>
                                       <option value="Q3">April-June</option>
                                       <option value="Q4">July-Sept</option>
                                      
                                                                
<!--                                                   <input type="text" name="quarter" id="quarters" value="">          -->
                                   </select>
                               </th>
                               
                               
                           </tr>
                             
<!--                                 <td>FORM TYPE</td>
                             <TD><select name="type" id="type">
                                     <option value="separate">SEPARATE</option>  
                                     <option value="combined">COMBINED </option>  
                                 
                             </select></TD>
                             </tr>-->
                                         
                         </table>
                                                
                                                   
                                                   
                                                   
                                                   <div id="form-A" >
                                                       <div>

 
         <div id="container" style="width:1250px; border-radius:25px; padding-left:20px; padding-right:20px; margin-left:0; margin-right:0; padding-top: 20px;padding-bottom: 20px;" >
 
 
<!--            This is the the indicators page with separate genders -->
<form action="IndicatorActivitiesServlet" method="post" name="frm" id="frm">
    <input type="hidden" name="financial" value="" id="financialyr">
        <input type="hidden" name="quarter" value="" id="quarteryr">
                        <h4>INDICATOR ACTIVITIES <a href="#" id="dialog-link6" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/> </a></h4>
            <table style="width: 200px;"> <tr> 
                         <td><input type="button" value="Add Row" style="background-color: #c66200;" onClick="addRow('dataTable')" /></td>
                         <td><input type="button" value="Delete Row" style="background-color: #c66200;" onClick="deleteRow('dataTable')" /></td>
                    <td> <input name="Submit" type="submit" value="Submit" style="background-color: #c66200;" /></td></tr></table>
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
                        

<tr id="dialog6" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[6]!=null){%>
         <%=Sectionshelp[6]%> 
         <%}%>
    </td>
    </tr>
    
                             <tr><th></td><td colspan="7"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>
                             <tr><th></th>
                                      <th>Select</th>
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th>County</th>
                                      <th>Geographic Location</th>
                                      <th>Activity Title</th>
                                      <th>Start Date</th>
                                      <th>End Date</th>
                                     <!-- <th>Women</th>
                                      <th>Men</th>-->
                                      <th>Number</th>
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
                                                   <td><select style="width:140px;" name="activity_0" id="activitySeparate" onchange="change();">
                                                           <option value=""></option>
                                                       </select>
                                                    <input type="hidden" value="" name="activityOthers_0" id="othersSeparate">
                                                   
                                                   </td>
                                                  
                                       <td><input type="text" name="startdate_0" value="" id="startdate_0"  value=""></td>
                                      <td><input type="text" name="enddate_0" value=""  id="enddate_0"  value="" ><input type="hidden" name="women_0" id="women" value="" style="width: 60px;"><input type="hidden" name="men_0" id="men" value="" onKeyUp="total()" style="width: 60px;"></td>
                                      <td><input type="text" name="subtotal_0" id="subtotal"  value="" style="width: 60px;"></td>
                                     
                                  </tr>
                 
           
                                                  
                      
                   <label>
                   <input name="h" type="hidden" id="h" value="1" />
                   <input name="newrows" type="hidden" id="newrows" value="0" />
                  </label>
                                                  
                              </table>
                                                    
                              <h4>INDICATOR RESULTS <a href="#" id="dialog-link7" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/> </a></h4>
                          
                          
                                    <table  class="fixed">
                                       
                        <col width="100px" />
                        <col width="100px" />
                       
<tr id="dialog7" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[7]!=null){%>
         <%=Sectionshelp[7]%> 
         <%}%>
    </td>
    </tr>
                        
<!--                           <tr>
                               <td>Financial Year</td><td><select name="FinancialYear" id="financial" onChange="filterer()">
                                       <option value=""></option>
                                       <option value="2010">2010</option>
                                       <option value="2011">2011</option>
                                       <option value="2012">2012</option>
                                       <option value="2013">2013</option>
                                       <input type="text" name="financialyr" id="financialyr" value=""></td> 
                                   </select></td>
                               <td>Quarter</td><td><select name="Quarter" id="quarter" onChange="filterer1()">
                                       <option value=""></option>
                                       <option value="Q1">October-January</option>
                                       <option value="Q2">Feb-May</option>
                                       <option value="Q3">June-Sept</option>
                                      
                                                                
                                                   <input type="text" name="quarter" id="quarters" value="">          
                                   </select>
                               </td>
                               
                               
                           </tr>-->
                                    </table>
                                              
                                                   
   <tr>   <td><input type="button" value="Add Row" style="background-color: #c66200;" onClick="addResultRow('tablerow')" /></td>
                         <td><input type="button" value="Delete Row" style="background-color: #c66200;" onClick="deleteResultRow('tablerow')" /></td></tr>
                        <table id="tablerow" class="fixed"> 
                         
                        <col width="110px" />
                        <col width="112px" />
                        <col width="112px" />
                        <col width="112px" />
                        <col width="112px" />
                       
                                                   <!--
                             <tr>     -->
<!--                                      <th style="width:30px; text-align: left;"  >SELECT</th>-->
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th  colspan="2">  Additional Criteria</th>
                                  
                                  
                                      <th  colspan="2">This reporting period  </th>
                                    
                                      
                                      
                                  </tr> 
                             <tr>     
                                 <th colspan="2"></th>
<!--                                 <th></th>-->
<!--                                      <th style="width:10px;"></th>-->
                                     
                                
                                      <th colspan="2">Achieved</th>
                                      
                                   
                                      
                                      
                                  </tr> 
                             <tr>    
                                 <th colspan="2"></th>
<!--                                 <th></th>-->
                                     
<!--                                      <th style="width:30px;"></th>-->
<!--                                      <th style="width:30px;"></th>-->
                                      <th style="width:60px;">W</th>
                                      <th style="width:60px;">M</th>
                                      
                                          <label>
                   <input name="resultcount" type="hidden" id="g" value="1" />
                   <input name="newresultrows" type="hidden" id="newresultrows" value="0" />
                  </label>
                         
                                      
                                  </tr> 
<!--                        <tr><td><input type="" name="districts_0" value="" id="districts_0"></td><td></td>
                            
                           
                       
                        
                            <td><input type="" name="achievedw_0" value="" id="achievedw_0"></td>
                            <td><input type="" name="achievedm_0" value="" id="achievedm_0"></td>
                          
                   
                        
                        
                        
                        
                        </tr>
            -->
                        
                        </table>  
                                </form>
    
        
</div>
                                                       </div></div>

                                                   <div id="form-B" >
                                                       
                                                         
<div >

 
<div id="container" style="width:1000px; border-radius:25px; padding-top: 20px;padding-bottom: 20px; padding-left:20px; padding-right:20px; margin-left:150px; ">
<!--            This is the login page-->

       
                        <h4>INDICATOR ACTIVITIES <a href="#" id="dialog-link8" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/> </a></h4>
                        <form name="frm1" id="frm1" method="post"  action="indicatoractivities1">
                         <input type="hidden" name="financial" value="" id="financialyr1">
                         <input type="hidden" name="quarter" value="" id="quarteryr1">
                        <table id="dataTable1" class="fixed" style="text-align: center" >
                                                 
<tr id="dialog8" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[8]!=null){%>
         <%=Sectionshelp[8]%> 
         <%}%>
    </td>
    </tr>
                        <col width="170px" />
                        <col width="50px" />
                        <col width="100px" />
                        <col width="140px" />
                        <col width="170px" />
                        <col width="120px" />
                        <col width="120px" />
                        <col width="70px" />
                            <tr><td></td><td colspan="7"><h4>DISAGGREGATE BY: Location,event,date and gender</h4></th></tr>
                             <tr><th></th>
                                      <th>Select</th>
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th>County</th>
                                      <th>Geographic Location </th>
                                      <th>Activity Title</th>
                                      <th>Start Date</th>
                                      <th>End Date</th>
                                      <th >Achieved</th>
                                     
                                    
                                  </tr> 
                             
                                   <tr><th rowspan="10" >UNIT <textarea name="unit"  ></textarea>
                                     <td><INPUT type="checkbox" name="chk"/></td>
<!--                                     <td width="40px;"> 1 </td>-->
                                     <td> 
                                              <select onchange="filter_district1(this);" style="width:100px;" name="county_0" id="county">
                                                   <option value=""></option>   
  <% 
             
                                                      String QueryCountyK= "SELECT countyName,countyID FROM county";
                                                     conn.state= conn.connect.createStatement();
				conn.rs = conn.state.executeQuery(QueryCountyK);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getString("countyID")%>'><%=conn.rs.getString("countyName")%></option>
                                                   <%
                                                      
 System.out.println(conn.rs.getInt("countyID"));
                                System.out.println(conn.rs.getString("countyName"));                                                      }
                                
                               
                                                   %>
                                            
                                             
                                         </select> </td>
                                     <td><select style="width:140px;" name="district1_0" id="districts"  multiple="true"></select>
                                         <option value=""></option>
                                     </td>
                                                   <td><select style="width:140px;"  name="activity_0" id="activityCombined" onchange="change1();">
                                                           <option value=""></option>    
                                                       </select>
                                                     <input type="hidden" value="" name="activityOthers_0" id="othersCombined">
                                                   
                                                   </td>
                                       <td><input type="text" name="startdate_0" value="" id="start_0" ></td>
                                      <td><input type="text" name="enddate_0" value=""  id="end_0"  ></td>
                                      <td ><input type="text" name="total_0" id="total_0" value=""  style="width: 60px;"></td>
                                     
                                      
                                  </tr>
                 
           
                                                  
                       
                               <INPUT type="button"  style="background-color: #c66200;"value="Add Row" onclick="addRow1('dataTable1')" />
 
                  <INPUT type="button" value="Delete Row" style="background-color: #c66200;" onclick="deleteRow1('dataTable1')" />
                  <input name="Submit" type="submit" style="background-color: #c66200;" value="Submit" />
                   <label>
                   <input name="h1" type="hidden" id="h1" value="1" />
                    <input name="newrows1" type="hidden" id="newrows1" value="0" />
                  </label>
                                                  
                              </table>
                                                   
                                <table  style="text-align: center" class="fixed">
                         <h4>INDICATOR RESULTS <a href="#" id="dialog-link9" style="padding-right: 0px;">
                                     <img src="images/help_24.png"/> </a></h4>
                        <col width="165px" />
                        <col width="163px" />
                        <col width="163px" />
                        <col width="163px" />
                                            
<tr id="dialog9" title="Program Progress Help ">
  
    <td>
         <% if(Sectionshelp[9]!=null){%>
         <%=Sectionshelp[9]%> 
         <%}%>
    </td>
    </tr>
<!--                           <tr>
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
                               
                               
                           </tr>-->
<!--                             <tr>     
                                     
                                      <th style="width:10px;">NO.</th>
                                      <th>ADDITIONAL CRITERIA</th>
                                      <th>BASELINE</th>
                                      <th>RESULTS ACHIEVED PRIOR PERIODS</th>
                                      <th colspan="2">THIS REPORTING PERIOD</th>
                                      <th >END OF PROJECT TARGET</th>
                                      
                                      
                                  </tr> -->
<!--                             <tr>     
                                      <th style="width:30px; text-align: left;"  ></th>
                                      <th style="width:10px;"></th>
                                      <t></th>
                                      <th></th>
                                      <th>Achieved</th>
                                      <th>Target</th>
                                      <th>Achieved</th>
                                      <th>Target</th>
                                     
                                      
                                      
                                  </tr> -->
<!--                             <tr>    
                                      <th></th>
                                     
                                      <th style="width:30px;"></th>
                                      <th></th>
                                      <th>Total</th>
                                      
                                      <th>Total</th>
                                      
                                      <th>Total</th>
                                      <th>Total</th>
                                     
                                      
                                      
                                  </tr> -->
                       
                       
                             
                       </table>
                                            
  
                                             
                                         
              <tr>   <td><input type="button" value="Add Row" style="background-color: #c66200;" onClick="addResultsRow('tablerow1')" /></td>
                         <td><input type="button" value="Delete Row" style="background-color: #c66200;" onClick="deleteResultsRow('tablerow1')" /></td></tr>               
            <table id="tablerow1" class="fixed">
              <tr>     
                                     
                                    
                                      <th colspan="2">ADDITIONAL CRITERIA</th>
                                    
                                  
                                      <th colspan="1">THIS REPORTING PERIOD</th>
                                  
                                      
                                      
                                  </tr> 
                             <tr>     
                                      <th colspan="2"></th>
                                     
                                     
<!--                                      <th></th>-->
                                      <th colspan="1">Achieved</th>
                                   
                                   
                                     
                                      <label>
                   <input name="resultcounts1" type="hidden" id="g1" value="1" />
                   <input name="newresultrows1" type="hidden" id="newresultrows1" value="0" />
                  </label>
                                      
                                  </tr> 
                                
<!--                                  <tr><td><input type="" name="districts_0" value="" id="districts_0"></td><td></td>
                            
                           
                       
                        
                            <td><input type="" name="achievedw_0" value="" id="achievedw_0"></td>
                            <td><input type="" name="achievedm_0" value="" id="achievedm_0"></td> 
                                      </tr>-->
            </table> 
           
                               
                                                  
                         
                            </form>
    
        
</div>
</div>                     
        
                                                       
                                                       
                                                       
                                                       
                                                   </div>                        <!--
                                                   
<!--                        <div>
                            <iframe id="Iframe1" seamless="seamless" align="middle" style="padding-top: 0px;"scrolling="auto" frameborder="0" width="1500" height="900" src="" ></iframe>
                   </div>-->

        
<!--</div>-->
</div>                      
        
        
   
</div>
</body>
</html>

