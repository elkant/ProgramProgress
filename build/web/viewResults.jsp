<%-- 
    Document   : viewResults
    Created on : Sep 12, 2013, 9:41:57 AM
    Author     : Maureen
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table style="padding-top: 100px;">
      
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
			String UniqueID1= viewList.get(0).toString();
                        
                       
                        session = request.getSession(true);
                        session.setAttribute("UniqueID1", UniqueID1);
                      System.out.println("UNIQUE"+UniqueID1);
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
        </table>
    </body>
</html>
