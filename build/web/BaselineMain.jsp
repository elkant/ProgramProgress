<%-- 
    Document   : BaselineMain
    Created on : Sep 22, 2013, 5:02:04 PM
    Author     : Maureen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="shortcut icon" href="images/pptlogo.png"/>
        <title>Program Progress</title>
        
         <script type="text/javascript">
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

if(tbsID == "1"){
    
    document.getElementById("Iframe1").src = "/ProgramProgress/baseline.jsp";}
else if(tbsID == "2"){
     document.getElementById("Iframe1").src = "/ProgramProgress/combinedBaseline.jsp";
}
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
