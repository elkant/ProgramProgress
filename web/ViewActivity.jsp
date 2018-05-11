<%-- 
    Document   : ViewActivity
    Created on : Sep 17, 2013, 1:05:38 PM
    Author     : Maureen
--%>




<%@ page import="java.util.*"%>
<%@ page language="java"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Activity</title>

 <style type="text/css">
    #container{
                height:500px;
                width:1100px; 
    }
     .example {
    width:900px;
    height:auto;
    overflow-y: auto;
     }
    </style>
   



 <script language="javascript" type="text/javascript" >
    
     function editRecord(activityID){
    var f=document.form;
    f.method="post";
    f.action="/ProgramProgress/ActivityServlet?activityID="+activityID;
    f.submit();
    
}
function deleteRecord(activityID){
    var f=document.form;
    f.method="post";
    f.action="/ActivityServlet?UniqueID="+activityID; 
    f.submit();
}

</script>
 
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
   
    <link rel="shortcut icon" href="images/pptlogo.png"/>
</head>
<body>
   
    
    
    <div class="example" style="width:1200px;height: 800px;">
     <div>
                    <% String logStatus=(String)session.getAttribute("loggedIn");
               if(logStatus==null){
//                     response.sendRedirect("index.jsp");
     %>
                   <a class="button-1" href="Login.jsp">Login</a>
     <%        } else{
     %>
                    <a class="button-1" href="../ProgramProgress/LogoutServlet">LogOut</a>
     <%
               }
        String username=(String)session.getAttribute("Username");
      
               if(username==null){
                   
               } else{
     %>
               <h5>Welcome <%=username%></h5>
     <%
               }
        
 
      %>
       <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("Username")==null) {
        //response.sendRedirect("index.jsp");
    } 
%>
            </div>
    
    <div><h1 style="text-align: center"><img src="images/aphia_logo.png" height="70" width="50%"/></h1></div> 
    <div class="menuholder">
        <ul class="menu slide">
            <li><a href="home.jsp" class="red">Home</a></li>
            <li><a href="index_main.jsp" class="violet">Program Progress</a></li>
            <li><a href="" class="orange">Manage Forms</a>
                <div class="subs">
                    <dl style="width: 200px; text-align: center;">
                        <dt></dt>
                        <dd style="text-align: center;"><a href="http://www.script-tutorials.com/category/html-css/">Indicator Activities</a></dd>
                        <dd style="text-align: center;"><a href="http://www.script-tutorials.com/category/jquery/">Indicator Results</a></dd>
                       
                    </dl>
                   
                </div>
            </li>
            <li><a href="" class="yellow">Reports</a>
               
            </li>
            <li><a href="" class="green">Help</a></li>
           
        </ul>
        <div class="back"></div>
        <div class="shadow"></div>
    </div>
    <div style="clear:both">
        <div id="container" style="padding-top: 100px;">
     <form name="form">
  <table  class="fixed" >
    <tr>
        <td colspan=9 align="center"><a href="/DIC/Enrollment.jsp" style="font-weight:bold;color:#cc0000;">Enroll New Client</a></td>
    </tr>
    <tr><td colspan=9 align="center" ></td></tr>
    <tr style="background-color:#cccccc;font-weight:bold;">
       <td>Activity ID</td>
       <td>Unit</td>
       <td>Indicator ID</td>
       <td>County</td>
       <td>District ID</td>
       <td>Activity Title</td>
       <td>Start Date</td>
       <td>End Date</td>
       <td>Men</td>
       <td>Women</td>
       <td>Sub-Totals</td>
       

      <td>Edit</td>
      <td>Delete</td>
  </tr>
	<%
	String bgcolor="";
	int count=0;
	List viewList = new ArrayList();
	Iterator  viewItr;
	
	if(request.getAttribute("userList")!=null && request.getAttribute("userList")!="")
	{
		List userList =  (ArrayList)request.getAttribute("userList");
		Iterator itr = userList.iterator();
		 
		while(itr.hasNext())
		{
			
			if(count%2==0)
			{
			 bgcolor = "#F0F0F0;";
			}
			else
			{
				
				bgcolor = "#F8F8FF";
			}
			
			viewList = (ArrayList)itr.next();
			String activityID = viewList.get(0).toString();
                        
                        System.out.println("aaaaaaa"+activityID);
			
			viewItr = viewList.iterator();
			%>
			<tr style="background-color:<%=bgcolor%>;">
			<%	
			while(viewItr.hasNext())
			{
			
				%>
				<td><%=viewItr.next()%></td>
				
				<%
					
			}
			count++;
			%>
                        <td><input type="button" name="Edit" value="Edit"  onclick="editRecord('<%= activityID %>')"></td>
                       
                         <td><input type="button" disabled name="delete" value="Delete" onclick="deleteRecord('<%= activityID %>');"></td>
                        
                        </tr>
			<%
		}
	}
	if(count==0)
	{
		%>
		<tr><td colspan="9" align="center">&nbsp;</td></tr>
            <tr><td colspan="9" align="center">No Record Available</td></tr>
		<%
	}
	%>
     <tr><td colspan=9 align="center" ></td></tr>
  </table>
    </form>
      </div>
 
     </div>
    </body>
 
</html>

