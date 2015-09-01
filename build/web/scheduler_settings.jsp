<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>


<%@page import="PP.Admin.dbConnect"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scheduler settings</title>
  


        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        <!--clerk menu-->

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>



       <!--tooltip-->
        <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="css/demos.css" />


        <script type="text/javascript">
            $(function() {

                $( document ).tooltip();
                $( "#accordion" ).accordion();

            }); 
        </script>
        
        
<script type="text/javascript">       
        
         function restartscheduler(){
             
             
         var SCHEDULERNAME="BackupTrigger";    
         

        var myhref=document.getElementById("myschedulerlink").value; 
       
       if(myhref=="stop scheduler"){
           
           myhref=SCHEDULERNAME+"?shutdown=true";
           
       }
       else{ 
           
           myhref=SCHEDULERNAME+"?shutdown=false";
           
       }
       
       
        //alert(""+myhref);
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    

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


           
document.getElementById("myschedulerlink").value=xmlhttp.responseText;



    


}
}
xmlhttp.open("POST",""+myhref,true);
xmlhttp.send();

document.getElementById("myschedulerlink").value="stopping...";

//call step 5 ajax



}//end of filter districts
</script>
        
    </head>




    <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
        response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0); //prevents caching at the proxy server

        if (session.getAttribute("AccessLevel") == null) {
            response.sendRedirect("index.jsp");
        }
    %>
    <!-- Body page -->



    <body>
        <div id="container" style="height:620px;">

            
                <!--=========================================menu=========================================-->     
                <br/>
                <%@include file="menu/admin_menu.html" %>

                <br/>
                <!--=====================================================================================--> 
            




            <div id="content" style="margin-left:300px">

                <%

                    dbConnect conn = new dbConnect();

                    String allschedules = "Select * from scheduler_settings ";

                    conn.rs = conn.state.executeQuery(allschedules);
                    String myhoursArray[] = {"*", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"};

                    String myminutesArray[] = {"*", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"};

                    String mysecondsArray[] = {"*", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "00"};

                    String am_pm_array[] = {"*", "am", "pm"};

                    int mycount = 0;

                %>



                    
     <br/><br/>
     <form action="update_scheduler" method="post" style="margin-left: 25px; width:770px ;height:400px;margin-right: 105px; background: #ffffff; ">
     <p align="center"> Enter  appropriate settings for backup </p>
     <table style="margin-left:30px;width:680px ;height:270px; font-size: 12px;"   id="scheduler_table" class="viewpdt" cellpadding="8px" cellspacing="6px">
     <tr>
     <th></th><th>Schedule for</th><th>Hour</th><th>Minute</th><th>Seconds</th><th>am/pm</th>
     
                        </tr>
                        <%
                            
                         
                                
                                
                        
                        while (conn.rs.next()) {
                                mycount++;

                                
//put the hours in an array
   String hours = "";
                            String seconds = "";
                            String minutes = "";
                            String am_pm = "";
                                for (int a = 0; a < myhoursArray.length; a++) {
                                    if (conn.rs.getString(3).equals(myhoursArray[a])) {
                                        hours = hours + "<option selected value=\"" + myhoursArray[a] + "\">" + myhoursArray[a] + "<option>";
                                    } else {
                                        hours = hours + "<option value=\"" + myhoursArray[a] + "\">" + myhoursArray[a] + "<option>";
                                    }

                                }


//put the seconds in an option

                                for (int a = 0; a < mysecondsArray.length; a++) {
                                    if (conn.rs.getString(6).equals(mysecondsArray[a])) {

                                        seconds = seconds + "<option selected value=\"" + mysecondsArray[a] + "\">" + mysecondsArray[a] + "<option>";

                                    } else {

                                        seconds = seconds + "<option value=\"" + mysecondsArray[a] + "\">" + mysecondsArray[a] + "<option>";
                                    }

                                }
//put the minutes in an array

                                for (int a = 0; a < myminutesArray.length; a++) {
                                    if (conn.rs.getString(4).equals(myminutesArray[a])) {
                                        minutes = minutes + "<option selected value=\"" + myminutesArray[a] + "\">" + myminutesArray[a] + "<option>";
                                    } else {

                                        minutes = minutes + "<option value=\"" + myminutesArray[a] + "\">" + myminutesArray[a] + "<option>";

                                            }
                                }



//CREATE A aM PM LIST


                                for (int a = 0; a < am_pm_array.length; a++) {
                                    if (conn.rs.getString(5).equalsIgnoreCase(am_pm_array[a])) {

                                        am_pm += "<option selected value=\"" + am_pm_array[a] + "\">" + am_pm_array[a] + "</option>";

                                    } else {
                                        am_pm += "<option  value=\"" + am_pm_array[a] + "\">" + am_pm_array[a] + "</option>";
                                    }

                                }


                        %>
                        <tr>
                            <td><%=mycount%></td><td><%=conn.rs.getString(2)%></td><td> <select name="hour<%=mycount%>"><%=hours%></select></td><td> <select name="minute<%=mycount%>"><%=minutes%></select> </td> <td> <select name="second<%=mycount%>"><%=seconds%></select></td><td><select name="am_pm<%=mycount%>"> <%=am_pm%></select><input type="hidden" name="tableid<%=mycount%>" value="<%=conn.rs.getString(1)%>"></td>
                        </tr>
                        <%   }%>  
                        <tr>

                            <td style="text-align: right;"> <input type="hidden" name="allrows" value="<%=mycount%>"/></td>
                            
                            <td style="text-align: center;" colspan="4"><input type="submit" style="height:27px;"   class="submit" value="Update"/>


                            </td>
                        </tr>
                    </table>
                    <h4>
                        <%
                            if (session.getAttribute("schedule_updated") != null) { %>
                            <input type="text" maxlength="22" value="stop scheduler" id="myschedulerlink" onclick="restartscheduler();" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:150px;text-align:center ; color:white ;background:coral; border-style:ridge ;" /> 
           
                        <script type="text/javascript"> 
                    
                                    var n = noty({text: '<b><font color=\"green\">scheduler table updated succesfully. stop the scheduler first then restart it for the changes you have made to be effective</font></b>',
                                        layout: 'center',
                                        type: 'Success',
                                        timeout: 5000});
                    
                        </script> <%

                                session.removeAttribute("schedule_updated");
                            }

                        %>
                    </h4>
                </form>



            </div>



            <div id="footer">
                <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->

            </div>
        </div>
    </body>


</html>
