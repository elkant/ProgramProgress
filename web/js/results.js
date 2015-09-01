/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// a function that filters the districts in the passed county as per the county drop down.
    
function filtersID(titleID){
    
     var f = document.getElementById("title");
     var title = f.options[f.selectedIndex].value;
    var ids = titleID.value;
//       alert("hbhb"+ids);
       
       
        var titles = new Array();
// this will return an array with strings "1", "2", etc.
 titles = ids.split(",");
var tbID=titles[0];
var tbsID=titles[1];
//alert("table id"+tbID);
//alert("table idssss"+tbsID);

//   $(document).ready(function() {
//        $("#choose-form").change(function() {
//  $("#form-A, #form-B").hide();
            //Hide -other- visible forms
          
            if(tbsID == "1"){
            $("#form-A").show();
         $("#form-B").hide();}
        else if(tbsID == "2"){
            
            $("#form-B").show();
            $("#form-A").hide();
        }   
//    });   });

//<!--if(tbsID == "1"){
//    
//    document.getElementById("Iframe1").src = "/ProgramProgress/Indicators.jsp";}
//else if(tbsID == "2"){
//     document.getElementById("Iframe1").src = "/ProgramProgress/Indicators2.jsp";
//}-->



           
var xmlhttp;  
// alert(ids);
   
if (tbID=="")
{
//filter the districts    



document.getElementById("titleNo").innerHTML="<td><input value=\"\"></td>";
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
}
}

xmlhttp.open("POST","/ProgramProgress/titleselector?titleID="+tbID,true);

xmlhttp.send();
    
IndicatorFilter() 
IndicatorFilter1()
  
 }  

//end of filter districts

// a function that filters the id in the passed county as per the county drop down.
    
function financial(){
    
      var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value;
//  
//   alert("financial"+y);

  document.getElementById("financialyr").value=y; 
  
//    alert(document.getElementById("ReportQuarter").value);
  // document.getElementById("ReportYear").value=y;  
  // document.getElementById("CurrentReportYear").value=y;  
//  alert(document.getElementById("ReportYear").value);
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

}
}


xmlhttp.open("POST","/ProgramProgress/ActivityServlet?FY="+y,true);

xmlhttp.send();
 
   financialC(); 
  
 }  
function IndicatorFilter(){
    
      var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
//alert("tile id"+tbIDs);
   
 
 document.getElementById("titles").value=tbIDs;
g=tbIDs;
//alert("hidden 2"+g);
    
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

}
}


xmlhttp.open("POST","/ProgramProgress/ActivityServlet?title="+tbIDs,true);

xmlhttp.send();
    
   
  
 }  
 function IndicatorFilter1(){
    
      var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
//alert("tile id for 2"+tbIDs);
   
 

    
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


xmlhttp.open("POST","/ProgramProgress/ActivityServlets2?title="+tbIDs,true);

xmlhttp.send();
    

  
 }  
 
function quarter(){
    
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
//     alert("quarter"+v);
    document.getElementById("quarteryr").value=v; 
  
          
         var priorq;
         var currentq;
         if(v== "Q1"){
         priorq="July-Sep";
         currentq="Oct-Dec";
         }
         if(v=="Q2"){
         priorq="Oct-Dec";
         currentq="Jan-March";
         }
         if(v=="Q3"){
         priorq="Jan-March";
         currentq="April-June";
         }
         if(v=="Q4"){
         priorq="July-Sept";
         currentq="Oct-Dec";
         }
    //document.getElementById("ReportQuarter").value=priorq; 
  //  document.getElementById("CurrentReportQuarter").value=currentq; 
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

//document.getElementById("tablerow").innerHTML=xmlhttp.responseText;
doOnLoader();

}
}


xmlhttp.open("POST","ActivityServlet?QTR="+v ,true);

xmlhttp.send();
 
  
   
    quarterC();
 
}  


// a function that filters the id in the passed county as per the county drop down.
    
function financialC(){
    
      var a = document.getElementById("financial");
  
   var n = a.options[a.selectedIndex].value;
//  
//   alert("financial c"+n);

    document.getElementById("financialyr1").value=n;  
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

}
}


xmlhttp.open("POST","/ProgramProgress/ActivityServlets2?FY="+n,true);

xmlhttp.send();
    
   
  
  
 }  

function quarterC(){
    
     
   var k = document.getElementById("quarter");
  
  
  
   var l =k.options[k.selectedIndex].value;
   
   
//     alert("filtererC1"+l);
//   

     document.getElementById("quarteryr1").value=l; 
     //alert(l);
 var xmlhttp;  
// alert(ids);
  
if (l=="")
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
document.getElementById("tablerow1").innerHTML=xmlhttp.responseText;
doOnLoader();
}
}


xmlhttp.open("POST","ActivityServlets2?QTR="+l ,true);

xmlhttp.send();
    
    
  
 }  


 


//<!--//scripts for adding for first indicator-->
	
                function date(){
                   
                    
                        $( ".datepicker" ).datepicker({
                                dateFormat: "dd/mm/yy",
                                changeMonth: true,
                                changeYear: true
                             
                               
                        });
                           
                     }   
          
// a function that filters the districts in the passed county as per the county drop down.
    
function filter_district(districts){
    
          var dist = districts.value
//       alert("dist"+dist);
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

xmlhttp.open("POST","districtselector2?district="+dist,true);

xmlhttp.send();
    
   
  
 }  

    
function filter_districts(districts){
    
          var dist = districts.value
//       alert(l"dist"+dist);
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

      
           
            
        



