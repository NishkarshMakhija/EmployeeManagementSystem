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
<title>Regulation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body style="padding:0px 50px 00px 50px;">
	
	<jsp:include page="../../template/employeeHeader.jsp"/>
	
	
	<div class="container">
		
		<span class="text-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		
		<h2>Regulation</h2>
		<div class="employee_container">
			<table class="table table-striped table-sm">
				<tr>
					<th>RL Id</th>
					<th>RL Type</th>
					<th>Description</th>
					<th>Creation Date</th>
					<th>Department Name</th>
				</tr>
					<tr>
						<%-- <td><core:out value="${employee}" /></td> --%>
						<td>${compliance.complianceId}</td>
						<td>${compliance.rltype}</td>
						<td>${compliance.details}</td>
						<td>
							<fmt:formatDate var="createDate" value="${compliance.createDate}" pattern="dd-MM-yyyy"/>
							${createDate}
						</td>
						<td>${compliance.department.departmentName}</td>
					</tr>
			</table>
		</div>
		<hr/>
		<div>
			<form method="post" action="user/addComment/${compliance.complianceId}">
				  <div class="form-group">
				    <label for="firstname">Comment:</label>
				    <br/>
				    <textarea name="comment" placeholder="Add Your Comment Here." required="required"></textarea>
				  </div>
				  <button type="submit" class="btn btn-primary">Add Comment</button>
			</form>
		</div>
	</div>

</body>
</html>