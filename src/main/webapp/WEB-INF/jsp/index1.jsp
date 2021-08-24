<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System - Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%-- <link rel="stylesheet" type="text/css" href="styles.css" /> --%>
</head>
<body style="padding-top:20px;">
	<center>
	<h3>Employee Management System</h3>
	<div class="container" style="border:2px solid black; margin:30px; padding:30px; width:50%;">
		<form action="/ems5/checkLogin" method="post">
			<table style="width:100%;">
				<tr>
					<td>
						<span class="text-success"><core:out value="${success}" /></span>
						<span class="text-danger"><core:out value="${error}" /></span>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-outline mb-2">
							<label class="form-label">Employee Id</label>
								<input type="text" name="empId" class="form-control" required />
						</div>			
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-outline mb-2">
							<label class="form-label">Password</label>
								<input type="password" name="password" class="form-control" required />
						</div>			
					</td>
				</tr>
			</table>

			<input type="submit" class="btn btn-primary" value="Sign In" />
			<input type="reset" class="btn btn-secondary" value="Reset" />
		</form>
		<span style="color: red">${requestScope.msg}</span>
	</div>
	</center>
</body>
</html>