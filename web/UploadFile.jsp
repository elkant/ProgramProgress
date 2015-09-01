<%-- 
    Document   : UploadFie
    Created on : Nov 6, 2013, 4:35:53 PM
    Author     : Maureen
--%>


<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<html>
    <head>
           <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
         <link rel="shortcut icon" href="images/pptlogo.png"/>
          <script>
$(document).tooltip();
         </script>

       
      


<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<title>import data</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 
    
<script type="text/javascript">
    
    
               
            function uploadfile(){
    

                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var name=document.getElementById("fname").value;
               
           
 
                if (name=="")
                {
                    //filter the districts    



                    //alert("anc number is blank");
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
                        
                        
                        
                        document.getElementById("loading").innerHTML="<img src=\"images/present.png\" alt=\"done\"><font color=\"orange\"> "+xmlhttp.responseText+"</font>";
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","UploadDataTrial?fname="+name,true);
                xmlhttp.send();

  document.getElementById("loading").innerHTML="<img src=\"images/sending.gif\" alt=\"uploading\"><b><font color=\"grey\"> importing data..</font></b>";
//               
            }//end of ajax class
             
    
</script>     


    </head>
    <body>
        <div id="container" style="width:1300px;" >
 <br/>
<!--              <div id="header" style="width:1250px; margin-left: 25px;">-->

                    
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
                    
         <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
String username=(session.getAttribute("Username")).toString();
    if (session.getAttribute("Username")==null) {
        response.sendRedirect("index.jsp");
    } 
      else{%>
      
       <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>          
     <h5>Welcome <%=username%></h5>
      <%
     
     }
%>           
<!--              </div>-->
            
            <br/>
            <!--<div id="content" style="width:1020px;">-->
       
                <form action="UploadDataTrial"  method="post"  style="margin-left: 400px; margin-right: 100px;margin-top: 100px; height: 210px;">   
                <table border="0" cellpadding="4px" cellspacing="6px" >
                      <h3>Upload Non HIV </h3>
<!--                    <tr>
                        <td>Choose Type of Data</td>
                        <td><select name="type" id="type">
                                <option value=""></option>
                                <option value="separate">Separate Data</option>
                                <option value="combined">Combined Data</option>
                               
                            </select>
                        </td>
                        
                    </tr>-->
                    
                    <tr>
                        <td>Choose file to upload
                      </td>
                        <td><input type="file" style="width:350px;" name="fname" id="fname" /></td>
<!--                        <input type="file" name="file" required id="file">-->
                    </tr>
                    <tr>
<!--                        <td>
                                  <img src="images/blguide.png" title="Navigate to the folder C:/MHC_UPLOADS of the host computer, then choose the excel file(.xls) with the data that you want to import. Rows that contains ANC numbers which have already been added to the System will not be uploaded.All new ANC numbers will be added."/>
                            
                        </td>-->
                        <td>
                            <input type="text" value="submit"  readonly style=" cursor:pointer;margin-left: 0px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="uploadfile();"/>

                            </td> </tr><tr>
                        <td style="width:200px;" colspan="2"><p id="loading" style="text-align: center;"></p></td>
                   </tr>
                    
                </table> 
                </form>   
              
              
            </div>

    </body>
</html>

