<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>

.pic{
	position: absolute;
	height: auto;
	right: 0;
	top: 0;
	left: 0;
}

.footer {
	position: absolute;
	right: 0;
	bottom: 0;
	left: 0;
	padding: 1rem;
	text-align: center;
	background-color: orange;
}

.user{
	position: absolute;
	table-layout: auto;
}

.table{
	border: 1px solid black;
	position: absolute;
	padding: 15px;
	width: 400px;
	height: 200px;
	margin: 300px 0 0 500px;
}

.frame{
	border: none;
	position: absolute;
	width: 500px;
	height: 450px;
	margin: 300px 0 0 950px;
}

</style>

<%@ page import="com.optimum.pojo.User" %>

<body>

		<%
			User refUser = (User) session.getAttribute("lastAcessTime"); 	
		%>
<form method="post">
<div class="pic" align="center"><img src="optimumlogo.jpg"></div>

<div class="user">
	<table class="table">
	<thead>
		<tr>
			<th><h1>Welcome <%=refUser.getFname()%></h1></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><a href="UserProfile.jsp" target="iframe">View your Profile</a>
			</td>
		</tr>
		<tr>
			<td><a href="UpdateUser.jsp" target="iframe">Update your Profile</a>
		</td>
		</tr>
		</tr>
		<tr>
			<td><a href="Login.jsp">Logout</a>
			<p><span>Last Accessed Time: ${lastAccessTime}</span></p>
		</td>
		</tr>
	</tbody>
	</table>
</div>

<div>
<iframe src="UserProfile.jsp" class="frame" name="iframe"></iframe>
</div>


		<div class="footer">
			<span>© 2001-2018 Copyrights Optimum Solutions Assignment</span><br>
			<p>
				Current Date/Time: <span id="datetime"></span>
			</p>
			<script>
				var dt = new Date();
				document.getElementById("datetime").innerHTML = (("0" + dt
						.getDate()).slice(-2))
						+ "."
						+ (("0" + (dt.getMonth() + 1)).slice(-2))
						+ "."
						+ (dt.getFullYear())
						+ " "
						+ (("0" + dt.getHours()).slice(-2))
						+ ":"
						+ (("0" + dt.getMinutes()).slice(-2));
			</script>
		</div>
	</form>
</body>
</html>