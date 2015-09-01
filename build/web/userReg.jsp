<%@page import="PP.Admin.dbConnect"%>

<%@page import="java.util.Random"%>
<%!
dbConnect conn = new dbConnect();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    <title>Program Progress</title>
    <style>
            #container{
        width:500px;
	height:400px;
                
            }
           .menu li div.subs dl{
                
                padding:0px 100px 100px 300px;
                
            } 
            
        </style>
</head>
<body>
   
    
    
    <div class="example" style="position: relative; left:-17px; width:1200px;height:527px;">
    
    <div style="clear:both">
         <div id="container" style="padding-top: 100px;">

<!--<div id="content" style="height:900;">
<div id="midcontent" style="margin-left:130px ;">-->
<h3><p align="center"> Please Enter New Clerk's details</p></h3>        
        
        
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
        
<tr><td><p align='center'><font color="red">*</font> indicates must fill fields</p></td></tr>
<form action="RegisterUser" method="post">
<br/>
<table align='center'cellpadding="0px" cellspacing="0px" border="0px">
                       
                            
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
<td class="align_button_left"><label for="middle_name">Middle Name<font color="red">*</font></label></td>
<td><input id="m_name" type=text required name="m_name" student_name class="textbox"/></td></tr><tr>
</tr>
                            
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
            
<tr> 
    <td><select align="right" size="" required="true" name="accesslevel" hidden="true">
         <option value=""></option> 
         <%
          String query ="select * from designation";
          conn.rs = conn.state.executeQuery(query);
while(conn.rs.next()){%>  
 <option value="<%= conn.rs.getString("accesslevel")%>"><%= conn.rs.getString("designation")%></option>

     <%}%>
          
<!--            <option value=""></option>
            <option value="1">1</option>
            <option value="2">2</option>-->
</select></td>
</tr>

<tr> 
<td class="align_button_left"><input  size="12px"  type="reset" value="clear" /></td> <td class="align_button_right">
    <input type="submit" class="submit" value="Add" onselect=""/></td>
</tr>

<tr><td></td><td>Already Have an Account? <li><a href="index.jsp" class="red">LOGIN</a></td>


</tr>    
</table>
</form>
</body>
</html>

      