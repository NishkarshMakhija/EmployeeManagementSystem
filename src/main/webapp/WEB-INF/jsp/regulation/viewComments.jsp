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
<title>Comments</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body style="padding:0px 50px 00px 50px;">
	
	<jsp:include page="../template/adminHeader.jsp"/>
	
	
	<div class="container">
		
		<span class="text-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		
		<div class="employee_container">
		
		<core:choose>
				<core:when test="${empty allStatusReport}">
					<h3><strong>No Comments on this Regulations till now.</strong></h3>	
				</core:when>
				<core:otherwise>
					<h2>Comment List</h2>
						<table class="table table-striped table-sm">
							<tr>
								<th>Comment Id</th>
								<th>Comment</th>
								<th>Creation Date</th>
								<th>Employee Name</th>
								<th>Department</th>
							</tr>
							<core:forEach items="${allStatusReport}" var="statusReport">
								<tr>
									<td>${statusReport.statusReportId}</td>
									<td>${statusReport.comments}</td>
									<td>
										<fmt:formatDate var="createDate" value="${statusReport.createDate}" pattern="dd-MM-yyyy"/>
										${createDate}
									</td>
									<td>${statusReport.employee.firstName} ${statusReport.employee.lastName}</td>
									<td>${statusReport.department.departmentName}</td>
								</tr>
							</core:forEach>
						</table>
				</core:otherwise>
			</core:choose>
		
		</div>
	</div>

</body>
</html>