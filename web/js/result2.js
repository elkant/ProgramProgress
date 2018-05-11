/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// a function that filters the districts in the passed county as per the county drop down.
    
function filterID(titleID){
    
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



           
var xmlhttp1;  
// alert(ids);
   
if (tbID=="")
{
//filter the districts    



//document.getElementById("titleNo").innerHTML="<td><input value=\"\"></td>";
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
//document.getElementById("titleNo").innerHTML=xmlhttp1.responseText;
}
}

xmlhttp1.open("POST","titleselector?titleID="+tbID,true);

xmlhttp1.send();
    
IndicatorFilt() 
IndicatorFilt1()
  
 }  

//end of filter districts

// a function that filters the id in the passed county as per the county drop down.
    
function filterer(){
    
      var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value;
//  
//   alert("financial"+y);

  document.getElementById("financialyr").value=y; 
 var xmlhttp1;  
// alert(ids)
   
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

}
}


xmlhttp1.open("POST","ActivityServlet2?FY="+y,true);

xmlhttp1.send();
 
   filtererC(); 
  
 }  
function IndicatorFilt(){
    
      var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
//alert("tile id"+tbIDs);
   
 
document.getElementById("titles1").value = tbIDs;
var g=tbIDs;

//alert("hidden titles 1 "+g);
    
 var xmlhttp1;  
// alert(ids)
   
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

}
}


xmlhttp1.open("POST","ActivityServlet2?title="+tbIDs,true);

xmlhttp1.send();
    
   
  
 }  
 function IndicatorFilt1(){
    
      var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
//alert("tile id for 2"+tbIDs);
   
 

    
 var xmlhttp1;  
// alert(ids)
   
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

}
}


xmlhttp1.open("POST","ActivityServlets?title="+tbIDs,true);

xmlhttp1.send();
    

  
 }  
 
function filterer1(){
  //  alert("filterer");
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
//     alert("quarter"+v);
    document.getElementById("quarteryr").value=v; 

    
 var xmlhttp1;  
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

//document.getElementById("results1").innerHTML=xmlhttp1.responseText;
     doOnLoader();
doOnLoad1();

}
}


xmlhttp1.open("POST","ActivityServlet2?QTR="+v ,true);

xmlhttp1.send();
 
  
  
    filtererC1();
}  


// a function that filters the id in the passed county as per the county drop down.
    
function filtererC(){
    
      var a = document.getElementById("financial");
  
   var n = a.options[a.selectedIndex].value;
//  
//   alert("financial c"+n);

    document.getElementById("financialyr1").value=n;  
    //document.getElementById("ReportYear1").value=n;  
   //document.getElementById("CurrentReportYear1").value=n;  
// 
 var xmlhttp1;  
// alert(ids)
   
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

}
}


xmlhttp1.open("POST","ActivityServlets?FY="+n,true);

xmlhttp1.send();
    
   
  
  
 }  

function filtererC1(){
    
     
   var k = document.getElementById("quarter");
  
  
  
   var l =k.options[k.selectedIndex].value;
  
   
//     alert("filtererC1"+l);
//   

     document.getElementById("quarteryr1").value=l; 
      var priorq;
         var currentq;
         if(l== "Q1"){
         priorq="July-Sep";
         currentq="Oct-Dec";
         }
         if(l=="Q2"){
         priorq="Oct-Dec";
         currentq="Jan-March";
         }
         if(l=="Q3"){
         priorq="Jan-March";
         currentq="April-June";
         }
         if(l=="Q4"){
         priorq="July-Sept";
         currentq="Oct-Dec";
         
         }
   // document.getElementById("ReportQuarter1").value=priorq; 
  //  document.getElementById("CurrentReportQuarter1").value=currentq; 
 var xmlhttp1;  
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
document.getElementById("results2").innerHTML=xmlhttp1.responseText;
doOnLoader(); 
}
}


xmlhttp1.open("POST","ActivityServlets?QTR="+l ,true);

xmlhttp1.send();
 
   
  
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

xmlhttp.open("POST","districtselector2?district="+dist,true);

xmlhttp.send();
    
   
  
 }  


//end of filter districts

      
           
            
        




