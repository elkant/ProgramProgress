<%-- 
    Document   : indicatorResults
    Created on : Sep 8, 2013, 1:41:55 PM
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
/*        Styling of the divs*/
           
         .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            .example {
    
    width:1270px;
    height:1270px;}
            #tables{
                width: 1200px;
                border-style: double;
                border:1;
            }
            

        </style>
   
        
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


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay?FY="+y,true);

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


xmlhttp.open("POST","/ProgramProgress/ResultsDisplay?QTR="+v ,true);

xmlhttp.send();
    
   
  
 }  

</script>
</head>
<body>
   
    
    
<div class="example" style="margin-top: 0; margin-bottom: 0; width:1500px;">

 
         <div id="container" style="width:1450px; border-radius:25px; margin-left:0; margin-right:0; padding-top: 20px;padding-bottom: 20px;" >
 
<!--            This is results page with genders -->
 <h4>INDICATOR RESULTS</h4>
                         
       <%
session = request.getSession(true);
String sessi = (String)session.getAttribute("title");

System.out.println("sessionsbhsbh"+sessi);    



%>                     
                             
                         
                          

                               
                           
                                <form name=form id="form" method="post"  action="indicatorresults">
                                    <table style="padding-top: 100px;" class="fixed" style="padding-bottom: 30px;" >
                                       
                        <col width="130px" />
                        <col width="130px" />
                        <col width="130px" />
                        <col width="130px" />
                        <col width="130px" />
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
<!--                                      <th style="width:30px; text-align: left;"  >SELECT</th>-->
<!--                                      <th style="width:10px;">NO.</th>-->
                                      <th >ADDITIONAL CRITERIA</th>
                                      <th colspan="2">BASELINE</th>
                                      <th  colspan="2">RESULTS ACHIEVED PRIOR PERIODS</th>
                                      <th  colspan="4">THIS REPORTING PERIOD</th>
                                      <th  colspan="2">END OF PROJECT TARGET</th>
                                      
                                      
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
                                      <th>W</th>
                                      <th>M</th>
                                      <th>W</th>
                                      <th>M</th>
                                      <th>W</th>
                                      <th>M</th>
                                      <th>W</th>
                                      <th>M</th>
                                      <th>W</th>
                                      <th>M</th>
                                      
                                      
                                  </tr> 
                                    </table>
  
                        <table id="tablerow" class="fixed"> </table>                                                  
<!--                       
                                          
                                                  
                         
                            </form>
        
        </div>
        
        
                        
        
        
    </div>
</div>
</body>
</html>