<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.table{
table-layout: fixed
}
</style>

<%@ page import="com.optimum.pojo.User" %>

<body>
<center>
		<%
			User refUser = (User) session.getAttribute("lastAcessTime"); 	
		%>
			  <table class="table" border="1" cellpadding="5">
			  <tr>
					<td align="center"><b>Display Pic: </b></td>
					<td><img src="PicController?email=<%=refUser.getEmail()%>" alt="logo" /></td>
				</tr>
				<tr>
					<td align="center"><b>Employee ID: </b></td>
					<td><%=refUser.getEmpid()%></td>
				</tr>
				<tr>
					<td align="center"><b>Employee Name: </b></td>
					<td><%=refUser.getFname()%> <%=refUser.getLname()%></td>
				</tr>
				<tr>
					<td align="center"><b>Email Address: </b></td>
					<td><%=refUser.getEmail()%></td>
				</tr>
				<tr>
					<td align="center"><b>NRIC: </b></td>
					<td><%=refUser.getNric()%></td>
				</tr>
				<tr>
					<td align="center"><b>Gender: </b></td>
					<td><%=refUser.getGender()%></td>
				</tr>
				<tr>
					<td align="center"><b>Address: </b></td>
					<td><%=refUser.getAddress()%></td>
				</tr>
				<tr>
					<td align="center"><b>Date of Birth: </b></td>
					<td><%=refUser.getDob()%></td>
				</tr>
				<tr>
					<td align="center"><b>Country: </b></td>
					<td><%=refUser.getCountry()%></td>
				</tr>
				<tr>
					<td align="center"><b>Department: </b></td>
					<td><%=refUser.getDepartment()%></td>
				</tr>
				<tr>
					<td align="center"><b>Mobile: </b></td>
					<td><%=refUser.getMobile()%></td>
				</tr>
				<tr>
					<td align="center"><b>Qualification: </b></td>
					<td><%=refUser.getQualification()%></td>
				</tr>
			</table>
			
			
		</center>
</body>
</html>