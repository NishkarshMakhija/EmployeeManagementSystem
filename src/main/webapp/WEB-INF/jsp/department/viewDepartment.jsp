<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<core:set var="req" value="${pageContext.request}" />
<core:set var="baseURL" value="${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Department</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
</head>
<body style="padding:0px 50px 00px 50px;">
	
	<jsp:include page="../template/adminHeader.jsp"/>
	
	
	<div class="container">
		
		<!-- <form method="post" action="admin/addDepartment"> -->
		<form:form action="admin/addDepartment" commandName="departmentForm">
			<table>
				<tr>
					<td>
						<label for="lastname">Department Name:</label>
						<%-- <input type="text" name="departmentName" placeholder="Department Name" value="<core:out value="${departmentName}" />"  maxlength="25" required /> --%>	
						<form:input path="departmentName" placeholder="Department Name" maxlength="25" required="required"/>		
					</td>
					<td>
						<form:errors path="departmentName" cssClass="text-danger"/>
					</td>
					<!-- <td>
						<span>Department Name should not be more than 25 characters.</span>
					</td> -->
				</tr>
				<tr colspan="2">
					<td >
						<input class="btn btn-primary" type="submit" value="Add Department" />
					</td>
				</tr>
			</table> 
		<!-- </form> -->
		</form:form>
		
		<span class="text-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		<span class="text-danger"><core:out value="${departmentNameError}" /></span>
		
		<hr/>
		
		<h2>View All Department Details</h2>
		<div class="department_container">
			<table class="table table-striped table-sm">
				<tr>
					<th class="text-center">Department Id</th>
					<th>Department Name</th>
				</tr>
				<core:forEach items="${allDepartments}" var="department">
					<tr>
						<td class="text-center">${department.departmentId}</td>
						<td>${department.departmentName}</td>
					</tr>
				</core:forEach>
			</table>
		</div>
	</div>

</body>
</html>