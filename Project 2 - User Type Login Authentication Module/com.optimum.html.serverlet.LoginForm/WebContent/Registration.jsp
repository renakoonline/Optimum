<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <form action="RegisterController" method="post" enctype="multipart/form-data">
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">New Employee Registration Form</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="fname" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lname" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td>
                          	<input type="radio" name="gender" value="male" checked> Male<br>
  							<input type="radio" name="gender" value="female"> Female<br>
 							<input type="radio" name="gender" value="other"> Other 
                        </td>
                    </tr>
                    <tr>
                        <td>Email (User ID)</td>
                        <td><input type="text" name="email" value="" required/>${invalidEmail}</td>
                    </tr>
                    <tr>
                        <td>Identification Number</td>
                        <td><input type="text" name="nric" value="" required/>${invalidNRIC}</td>
                    </tr>
                    <tr>
                        <td>Date of Birth</td>
                        <td><input type="date" name="dob" value="" /></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address" value="" /></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><select name="country"/>
                        	<option value=""></option>
                        	<option value="Others">Asia (Others)</option>
                        	<option value="China">China</option>
                        	<option value="Europe">Europe</option>
                        	<option value="Hong Kong">Hong Kong</option>
                        	<option value="Japan">Japan</option>
                        	<option value="Singapore">Singapore</option>
                        	<option value="South Korea">South Korea</option>
                        	<option value="Taiwan">Taiwan</option>
                        </td>
                    </tr>
                    <tr>
                        <td>Qualification</td>
                        <td><select name="qualification"/ required>
                        <option value=""></option>
                        <option value="Degree">Degree</option>
                        <option value="Diploma">Diploma</option>
                        <option value="Others">Others</option>
                        </td>
                    </tr>
                    <tr>
                        <td>Attach Certificate</td>
                        <td><input type="file" name="file" id="file" required></td>
                    </tr>
                    <tr>
                        <td>Department</td>
                        <td><input type="text" name="department" value="" /></td>
                    </tr>
                    <tr>
                        <td>Mobile Number</td>
                        <td><input type="number" name="mobile" value="" required/>${invalidMobile}</td>
                    </tr>
                    <tr>
                        <td>Employee ID</td>
                        <td><input type="number" name="empid" value="" required/>${invalidID}</td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" onclick="removeIFrame();" />
                          <script type="text/javascript">
   							 function removeIFrame() {
 						     var frame = document.getElementById("iframe");
 						    	toggle.style.display="none";
   								 }
 							</script>
                        </td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>