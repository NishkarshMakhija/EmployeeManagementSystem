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
	
	<jsp:include page="../template/employeeHeader.jsp"/>
	
	
	<div class="container">
		
		<span class="text-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		
		
		<div class="employee_container">
		
			<core:choose>
				<core:when test="${empty allCompliances}">
					<h3><strong>No New Regulations</strong></h3>	
				</core:when>
				<core:otherwise>
					<h2>Regulation List</h2>
					<table class="table table-striped table-sm">
						<tr>
							<th>RL Id</th>
							<th>RL Type</th>
							<th>Description</th>
							<th>Creation Date</th>
							<th>Department Name</th>
							<th>Action</th>
						</tr>
						<core:forEach items="${allCompliances}" var="compliance">
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
								<td>
									<a href="user/addComment/${compliance.complianceId}" class="btn btn-sm btn-primary">Add Comment</a>
								</td>
							</tr>
						</core:forEach>
					</table>						
						
				</core:otherwise>
			</core:choose>
		</div>
	</div>

</body>
</html>