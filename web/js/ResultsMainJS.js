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
var percentID;
if(titles[2]!=null){
    
    percentID=titles[2];
    
}

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

 }  

//end of filter districts

//// a function that filters the id in the passed county as per the county drop down.
// 
// function IndicatorFilt1(){
//    
//      var p = document.getElementById("title");
//  
//   var s = p.options[p.selectedIndex].value;
//  
//    var titlesplit = new Array();
//// this will return an array with strings "1", "2", etc.
// titlesplit = s.split(",");
//var tbIDs = titlesplit[0];
////alert("tile id for 2"+tbIDs);
//   
// 
//
//    
// var xmlhttp;  
//// alert(ids)
//   
//if (window.XMLHttpRequest)
//{// code for IE7+, Firefox, Chrome, Opera, Safari
//xmlhttp=new XMLHttpRequest();
//}
//else
//{// code for IE6, IE5
//xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//}
//xmlhttp.onreadystatechange=function()
//{
//if (xmlhttp.readyState==4 && xmlhttp.status==200)
//{
//}
//}
//
//
//xmlhttp.open("POST","ResultsDisplay2?title="+tbIDs,true);
//
//xmlhttp.send();
//    
//
//  
// }  
// 
 //====I CHANGED THE NAME OF THIS FUNCTION TO THE ONE NAMED BELLOW 
 
 
 
 
 // for filtering the data that come from the he for resultsmain.jsp
function filterer1(){


//alert("called");

  var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value; 
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
   
  // alert("X1 "+x+" Y1: "+y+" Z1: "+z+"      ");
   
//=======================corrected=========================   
//     alert("quarter"+v);
    document.getElementById("quarteryr").value=v; 
   document.getElementById("financialyr").value=y;  
   
   if(document.getElementById("ReportQuarter")!=null){
   document.getElementById("ReportQuarter").value=v; 
   }
//    alert(document.getElementById("ReportQuarter").value);
if(document.getElementById("ReportQuarter")!=null){
   document.getElementById("ReportYear").value=y; 
}

//=============================================================
//  alert(document.getElementById("ReportYear").value);
     var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
 var xmlhttp1;  
// alert(ids);
  
if (v=="")
{
//filter the districts    

alert("V is blank");
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

document.getElementById("tablerow").innerHTML=xmlhttp1.responseText;



}
}


xmlhttp1.open("POST","ResultsDisplay?title="+tbIDs+"&FY="+y+"&QTR="+v ,true);

xmlhttp1.send();
 
  
   
   filtererCombined();
}  


//_________________________________________________++++++++++++++++++++++++_____________________________________________________

 // for filtering the data that come from the he for resultsmain.jsp
function filtererCombined(){

  var a = document.getElementById("financial");
   var n = a.options[a.selectedIndex].value;
     document.getElementById("financialyr1").value=n;  


 var k = document.getElementById("quarter");
  var l =k.options[k.selectedIndex].value;
   document.getElementById("quarteryr1").value=l; 

   

     var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
    // alert("N "+n+" L: "+l+" s "+s+"      ");
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
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

document.getElementById("tablerow1").innerHTML=xmlhttp1.responseText;



}
}


xmlhttp1.open("POST","ResultsDisplay2?title="+tbIDs+"&FY="+n+"&QTR="+l ,true);

xmlhttp1.send();
   
}  






















// a function that filters the id in the passed county as per the county drop down.
    
function filtererC(){
    
      var a = document.getElementById("financial");
  
   var n = a.options[a.selectedIndex].value;
//  
//   alert("financial c"+n);
//   document.getElementById("quarteryr1").value=l; 
    document.getElementById("financialyr1").value=n;  
 var xmlhttp2;  
// alert(ids)
   
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp2=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp2=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp2.onreadystatechange=function()
{
if (xmlhttp2.readyState==4 && xmlhttp2.status==200)
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


xmlhttp2.open("POST","ResultsDisplay2?FY="+n,true);

xmlhttp2.send();
    
   
  
  
 }  

function filtererC1(){
    
     
   var k = document.getElementById("quarter");
  
  
  
   var l =k.options[k.selectedIndex].value;
   
   
//     alert("filtererC1"+l);
//   

     document.getElementById("quarteryr1").value=l; 
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

}
}


xmlhttp.open("POST","ResultsDisplay2?QTR="+l ,true);

xmlhttp.send();
    
   
  
 }  
// for filtering activity 


//===============================================A FILTER SIMILER TO Filterer 1 which avoids name sconflicting since we already have two filterers





 // for filtering the data that come from the he for resultsmain.jsp
function filtereremg(){


//alert("called");

  var x = document.getElementById("financial");
  
   var y = x.options[x.selectedIndex].value; 
     
   var z = document.getElementById("quarter");
  
  
  
   var v =z.options[z.selectedIndex].value;
   
   
   
  // alert("X1 "+x+" Y1: "+y+" Z1: "+z+"      ");
   
//=======================corrected=========================   
//     alert("quarter"+v);
    document.getElementById("quarteryr").value=v; 
   document.getElementById("financialyr").value=y;  
   
   if(document.getElementById("ReportQuarter")!=null){
   document.getElementById("ReportQuarter").value=v; 
   }
//    alert(document.getElementById("ReportQuarter").value);
if(document.getElementById("ReportQuarter")!=null){
   document.getElementById("ReportYear").value=y; 
}

//=============================================================
//  alert(document.getElementById("ReportYear").value);
     var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
  
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
 var xmlhttp1;  
// alert(ids);
  
if (v=="")
{
//filter the districts    

alert("V is blank");
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

document.getElementById("tablerow").innerHTML=xmlhttp1.responseText;



}
}


xmlhttp1.open("POST","ResultsDisplay?title="+tbIDs+"&FY="+y+"&QTR="+v ,true);

xmlhttp1.send();
 
  
   
   filtererCombinedemg();
}  


//_________________________________________________++++++++++++++++++++++++_____________________________________________________

 // for filtering the data that come from the he for resultsmain.jsp
function filtererCombinedemg(){

  var a = document.getElementById("financial");
   var n = a.options[a.selectedIndex].value;
     document.getElementById("financialyr1").value=n;  


 var k = document.getElementById("quarter");
  var l =k.options[k.selectedIndex].value;
   document.getElementById("quarteryr1").value=l; 

   

     var p = document.getElementById("title");
  
   var s = p.options[p.selectedIndex].value;
   //  alert("N "+n+" L: "+l+" s "+s+"      ");
    var titlesplit = new Array();
// this will return an array with strings "1", "2", etc.
 titlesplit = s.split(",");
var tbIDs = titlesplit[0];
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

document.getElementById("tablerow1").innerHTML=xmlhttp1.responseText;



}
}


xmlhttp1.open("POST","ResultsDisplay2?title="+tbIDs+"&FY="+n+"&QTR="+l ,true);

xmlhttp1.send();
   
}  



