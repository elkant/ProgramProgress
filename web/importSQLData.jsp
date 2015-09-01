<%-- 
    Document   : importSQLData
    Created on : Mar 18, 2014, 4:59:18 PM
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
 <link rel="shortcut icon" href="images/pptlogo.png"/>
        <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
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
                xmlhttp.open("POST","importSQLData?sqlfile="+name,true);
                xmlhttp.send();

  document.getElementById("loading").innerHTML="<img src=\"images/sending.gif\" alt=\"uploading\"><b><font color=\"grey\"> importing data..</font></b>";
//               
            }//end of ajax class
             
    
</script>     


    </head>
    <body>
        <div id="container"  >
            <div class="example" style="width:1300px; height: 600px;">               
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
 <br/>
<!--              <div id="header" style="width:1250px; margin-left: 25px;">-->

                    
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("AccessLevel") != null) {

                    if (session.getAttribute("AccessLevel").equals("1")) {%>
            <%@include file="menu/admin_menu.html" %>
            <%} else if (session.getAttribute("AccessLevel").equals("3")){%>
            
            
            <%@include file="menu/MnEMenu.html" %>
<%}else{%>
            <%@include file="menu/clerk_menu.html" %>

            <%}

            }else {%>

            <%@include file="menu/clerk_menu.html" %>

            <%}%>

            <!--=====================================================================================--> 
             
<!--              </div>-->
            
            <br/>
            <!--<div id="content" style="width:1020px;">-->
        <%if (session.getAttribute("datasend1") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend1")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("datasend1");
                            }

                        %>     
        <%if (session.getAttribute("msg") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n1 = noty({text: '<%=session.getAttribute("msg")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("msg");
                            }

                        %>     
                         
                <form action="importSQLData1"  method="post"  enctype="multipart/form-data"  >   
                <table border="0" cellpadding="4px" cellspacing="6px" style="margin-left: 200px; margin-right: 100px;margin-top: 100px; height: 210px; width: 700px; background-color: #F5F5F5;" >

                    
                    <tr> <td style="font-size: 20px;"><h4>Upload Database</h4></td></tr>
                    <tr>  <td>Choose file to upload
                      </td>
                        <td><input type="file" style="width:350px;" name="fname" id="fname" /></td>
<!--                        <input type="file" name="file" required id="file">-->
                    </tr>
                    <tr>
<!--                        <td>
                                  <img src="images/blguide.png" title="Navigate to the folder C:/MHC_UPLOADS of the host computer, then choose the excel file(.xls) with the data that you want to import. Rows that contains ANC numbers which have already been added to the System will not be uploaded.All new ANC numbers will be added."/>
                            
                        </td>-->
                        <td>
                            <input type="submit" value="submit" />

                            </td> </tr><tr>
                        <td style="width:200px;" colspan="2"><p id="loading" style="text-align: center;"></p></td>
                   </tr>
                    
                </table> 
                </form>   
              
            </div> 
            </div>

    </body>
</html>



