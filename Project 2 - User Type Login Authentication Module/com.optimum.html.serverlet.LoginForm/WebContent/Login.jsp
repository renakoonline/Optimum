	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

.login{
	position:absolute;
	table-layout:auto;
	top:40%;
	left:35%;
	bottom:40%
}

.invisible{
	visibility:hidden;
}	

.ForgotPW{
	position: absolute;
	top:57%;
	left:40%;
	bottom:40%;
	text-align: center;
}

.span{
	cursor:pointer;
    color:blue;
}

.placeholder{
	text-align:center;
}

</style>
</head>
<body>

<div class="pic" align="center"><img src="optimumlogo.jpg"></div>

<form method="post" action="UserController">
		<div>
		<center>
			<table class="login" border="1" width="30%" cellpadding="3">
				<thead>
					<tr>
						<th colspan="2" cellpadding="5">Login Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="1" cellpadding="5">Email</td>
						<td><input cellpadding="5" colspan="1" type="text" name="username" placeholder="Please enter your Email" required/></td> 
					</tr>
					<tr>
						<td colspan="1" cellpadding="5">Password</td>
						<td><input cellpadding="5" colspan="1" type="password" name="password" placeholder="Please enter your password" required/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Login" /></td>
						<td><input type="reset" value="Reset" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">${Error}<span class="invisible">This is invisible text</span></td>
					</tr>
				</tbody>
				<script>
				var elements = document.getElementsByTagName("INPUT");
				for (var i = 0; i < elements.length; i++) {
				    elements[i].oninvalid = function(e) {
				        e.target.setCustomValidity("");
				        if (!e.target.validity.valid) {
				            e.target.setCustomValidity("This field cannot be left blank");
				        }
				    };
				    elements[i].oninput = function(e) {
				        e.target.setCustomValidity("");
				    };
				}
				</script>
			</table>
		</center>
		</div>
		</form>


	<div class="ForgotPW">
		<form name="myform" method="post" action="ForgetController">
		<center>
			<input class="placeholder" style="width:400px" type="text" name="pass1" 
			placeholder="If you have forgotten your password, enter your email here." />
		</center>
		</form>
	</div>

	<div class="footer">
			© 2001-2018 Copyrights Optimum Solutions Assignment<br>
			<p>
				Date/Time: <span id="datetime"></span>
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

</body>
</html>