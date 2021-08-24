<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<core:set var="req" value="${pageContext.request}" />
<core:set var="baseURL" value="${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees</title>
</head>
<body style="padding:0px 50px 00px 50px;">
	
	<jsp:include page="../template/adminHeader.jsp"/>
	
	
	<div class="container">
	
		<span class="text	-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		
		<h2>Add Employee</h2>
		<div class="employee_container">
			<%-- <form method="post" action="admin/addEmployee"> --%>
			<form:form action="admin/addEmployee" commandName="employeeForm">
				  <div class="form-group">
				    <label for="firstname">First Name:</label>
				    <!-- <input type="text" class="form-control" name="firstname" placeholder="First Name" id="firstname" required> -->
				    <form:input path="firstName" size="45" cssClass="form-control" maxlength="45"  placeholder="First Name" required="required" />
				    <form:errors path="firstName" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="lastname">Last Name:</label>
				    <!-- <input type="text" class="form-control" name="lastname" placeholder="Last Name" id="lastname" required> -->
				    <form:input path="lastName" size="45" cssClass="form-control" maxlength="45"  placeholder="Last Name" required="required" />
				    <form:errors path="lastName" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="dob">DOB:</label>
				    <!-- <input type="date" class="form-control" name="dob" placeholder="dd/mm/yyyy" id="dob" max="1997-12-31" required> -->
				    <!-- <input type="date" class="form-control" name="dob" placeholder="dd/mm/yyyy" id="dob" required> -->
				    <form:input type="date" path="dob" cssClass="form-control"  placeholder="dd/mm/yyy" required="required" />
				    <form:errors path="dob" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="email">Email address:</label>
				    <!-- <input type="email" class="form-control" name="email" placeholder="Enter email" id="email" required> -->
				    <form:input type="email" path="email" size="100" cssClass="form-control" maxlength="100"  placeholder="Email id" required="required" />
				    <form:errors path="email" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="pwd">Password:</label>
				    <!-- <input type="password" class="form-control" name="password" placeholder="Enter password" id="pwd" required> -->
				    <form:input type="password" path="password" cssClass="form-control" maxlength="30" placeholder="Password" required="required" />
				    <form:errors path="password" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="depaertment">Department:</label>
				    <select name="department">
				    	<core:forEach items="${allDepartments}" var="department">
							<option value="${department.departmentId}">${department.departmentName}</option>
						</core:forEach>
				    </select>
				    <form:errors path="department" cssClass="text-danger"/>
				  </div>
				  <button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
			<%-- </form> --%>
		</div>
	</div>

</body>
</html>