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
	
	<jsp:include page="../../template/employeeHeader.jsp"/>
	
	
	<div class="container">
		
		<span class="text-success"><core:out value="${success}" /></span>
		<span class="text-danger"><core:out value="${error}" /></span>
		
		<h2>Comments</h2>
		<div class="employee_container">
			<table class="table table-striped table-sm">
				<tr class="text-center">
					<th>Comment Id</th>
					<th>Comment</th>
					<th>Creation Date</th>
				</tr>
				<core:forEach items="${allStatusReport}" var="statusreport">
					<tr>
						<%-- <td><core:out value="${employee}" /></td> --%>
						<td class="text-center">${statusreport.statusReportId}</td>
						<td>${statusreport.comments}</td>
						<td>
							<fmt:formatDate var="createDate" value="${statusreport.createDate}" pattern="dd-MM-yyyy"/>
							${createDate}
						</td>
					</tr>
				</core:forEach>
			</table>
		</div>
	</div>

</body>
</html>