<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<core:set var="req" value="${pageContext.request}" />
<core:set var="baseURL" value="${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body style="padding:0px 50px 00px 50px;">
	
	<jsp:include page="../template/adminHeader.jsp"/>
	
	
	<div class="container">
		
		<span class="text-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		
		<h2>Employees List</h2>
		<div class="employee_container">
			<table class="table table-striped table-sm">
				<tr>
					<th>Employee Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>DOB</th>
					<th>Email Id</th>
					<th>Department Name</th>
					<th>Action</th>
				</tr>
				<core:forEach items="${allEmployees}" var="employee">
					<tr>
						<%-- <td><core:out value="${employee}" /></td> --%>
						<td>${employee.empId}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>
							<fmt:formatDate var="dob" value="${employee.dob}" pattern="dd-MM-yyyy"/>
							${dob}
						</td>
						<td>${employee.email}</td>
						<td>${employee.department.departmentName}</td>
						<td>
							<a href="admin/editEmployee/${employee.empId}" class="btn btn-sm btn-primary">Edit</a>
						</td>
					</tr>
				</core:forEach>
			</table>
		</div>
	</div>

</body>
</html>