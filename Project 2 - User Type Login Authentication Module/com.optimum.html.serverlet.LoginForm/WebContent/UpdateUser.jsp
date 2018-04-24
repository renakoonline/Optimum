<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="UpdateController" enctype="multipart/form-data">

	<%@ page import="com.optimum.pojo.User"%>

	<%
	User refUser = (User) session.getAttribute("lastAcessTime"); 	
	%>

	<table border="1" width="30%" cellpadding="5">
		<tr>
			<td align="center"><b>Upload profile picture: </b></td>
			<td><input type="file" name="updatePic" >
			</td>
		</tr>
		<tr>
		<tr>
			<td align="center"><b>First Name: </b></td>
			<td><input type="text" name="updateFname" value="<%=refUser.getFname()%>"></td>
		</tr>
		<tr>
			<td align="center"><b>Last Name: </b></td>
			<td><input type="text" name="updateLname" value="<%=refUser.getLname()%>"></td>
		</tr>
		<tr>
			<td align="center"><b>Password: </b></td>
			<td><input type="text" name="updatePassword" placeholder="Enter new password"> 
				<input type="hidden" name="existingPassword" value="<%=refUser.getPassword()%>"></td>
		</tr>
		<tr>
			<td align="center"><b>Country: </b></td>
			<td><select name="updateCountry">
                <option value="null"></option>
                <option value="Others">Asia (Others)</option>
                <option value="China">China</option>
                <option value="Europe">Europe</option>
                <option value="Hong Kong">Hong Kong</option>
                <option value="Japan">Japan</option>
                <option value="Singapore">Singapore</option>
                <option value="South Korea">South Korea</option>
                <option value="Taiwan">Taiwan</option>
        </select></td>
		</tr>
		<tr>
			<td align="center"><b>Address: </b></td>
			<td><input type="text" name="updateAddress" value="<%=refUser.getAddress()%>">
		</tr>
		<tr>
			<td align="center"><b>Mobile: </b></td>
			<td><input type="number" name="updateMobile" value="<%=refUser.getMobile()%>">${invalidMobile}</td>
		</tr>
		<tr>
			<td align="center"><b>Qualification:</b></td>
			<td><select name="updateQualification">
					<option value="null"></option>
					<option value="Degree">Degree</option>
					<option value="Diploma">Diploma</option>
					<option value="Others">Others</option>
			</select></td>
		</tr>
		<tr>
			<td align="center"><b>Attach Certificate:</b></td>
			<td><input type="file" name="updateFile"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><br />
				<button type="submit">Update</button>
				<br />
				<input type="hidden" name="existingMobile" value="<%=refUser.getMobile()%>">
				<input type="hidden" name="existingQualification" value="<%=refUser.getQualification()%>">
				<input type="hidden" name="existingCountry" value="<%=refUser.getCountry()%>">
				<input type="hidden" name="existingAddress" value="<%=refUser.getAddress()%>">
				<p>${UpdateOk}</p></td>
		</tr>
	</table>
	</form>
	</div>
	</td>
	</tr>
	</table>

</form>
</body>
</html>