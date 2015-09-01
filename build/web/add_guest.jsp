<%-- 
    Document   : add_guest
    Created on : Jan 22, 2014, 8:20:26 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
     <link rel="shortcut icon" href="images/pptlogo.png"/>
    <title>Register As a Guest</title>
<script type="text/javascript">
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }}
$(function() {
$( "#datepicker" ).datepicker();
});

            
        </script> 
</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
   
        <div id="container">

            <div id="content" style="height:auto; background: #f6a828; border-width: 0px" >
<div id="midcontent" style="margin-left:130px ;">
       
        
        
<h4>
                        
<%
if (session.getAttribute("clerk_added") != null) {
out.println(session.getAttribute("clerk_added"));
session.removeAttribute("clerk_added");
}

%>
                   
                        <!--creating random user id-->
                         <%!
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
%>  
                  
</h4>
<div style=" position: absolute; left: 370px; width: 500px; background: #ffffff; padding-top:70px;">
    <h3><p align="center">Please Enter Your Details Here. </p></h3> 
<tr><td><p align='center'><font color="red">*</font> indicates must fill fields</p></td></tr>
<form action="RegisterUser" method="post">
<br/>
<table align="center" cellpadding="0px" cellspacing="0px" border="0px" style="height: 400px">
                       
                            
<tr>
<td class="align_button_left"><label for="first_name">Userid<font color="red">*</font></label></td>
<td><input id="first_name" type=text readonly value="<%=generateRandomNumber(1000,10888)%>" required name="userid" class="textbox"/></td>
</tr>

<tr>
<td class="align_button_left"><label for="s_name">Surname<font color="red">*</font></label></td>
<td><input id="surname" type=text required name="surname" class="textbox"/></td>
</tr>
                            
<tr>
<td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
<td><input id="first_name" type=text required name="first_name"  class="textbox"/></td>
</tr>
                            
<tr>
<td class="align_button_left"><label for="middle_name">Middle Name<font color="red"></font></label></td>
<td><input id="m_name" type=text name="m_name" student_name class="textbox"/></td></tr>
                            
<tr>
<td class="align_button_left"><label for="phoneno">Phone Number</label></td>
<td><input  type="text" name="phoneno" pattern="[0-9]{10,10}" class="textbox" /></td>                            
</tr>

<tr> 
<td class="align_button_left"><label for="username">Username <font color="red">*</font></label></td>
<td><input id="town" type=text required name="username" class="textbox"/></td>
</tr>
                           
<tr>
<td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
<td><input id="password" type=password required name="password" oninput="checkPasswords()" class="textbox"/></td>
</tr>                           
                            
<tr>
<td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
<td ><input id="conf_password" type=password required name="conf_password" oninput="checkPasswords()" class="textbox"/></td>                                
</tr> 
<input type="hidden" name="accesslevel" value="5" id="accesslevel">
<tr>
   
<!--    <td>Access Level</td>-->
<td class="align_button_left"></td> <td class="align_button_right">
    <input type="submit" class="submit" value="Add" onselect=""/></td>
</tr>

<tr><td></td><td>Already Have an Account? <li><a href="index.jsp" class="red">LOGIN</a></td>


</tr>    
</table>
</form>
</div>        
        
                        
        
        
</div>
</div>
</div>
</body>
</html>
