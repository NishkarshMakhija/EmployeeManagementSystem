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
<title>Regulation</title>
</head>
<body style="padding:0px 50px 00px 50px;">
	
	<jsp:include page="../template/adminHeader.jsp"/>
	
	
	<div class="container">
		
		<h2>Add Regulation</h2>
		<div class="employee_container">
			<%-- <form method="post" action="admin/addRegulation"> --%>
			<form:form action="admin/addRegulation" commandName="regulationForm">
				  <div class="form-group">
				    <label for="rltype">RL Type:</label>
				    <!-- <input type="text" class="form-control" name="rltype" placeholder="RL Type" id="rltype"> -->
				    <form:input path="rltype" cssClass="form-control" maxlength="15" placeholder="RL Type" required="required" />
				    <form:errors path="rltype" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="des">Description:</label><br/>
				    <!-- <textarea style="width:100%;" width="100%" name="description"></textarea> -->
				    <form:textarea path="details" cssClass="form-control" maxlength="250" placeholder="details" required="required" />
				    <form:errors path="details" cssClass="text-danger"/>
				  </div>
				  <div class="form-group">
				    <label for="pwd">Department:</label>
				    <select name="department">
				    	<core:forEach items="${allDepartments}" var="department">
							<option value="${department.departmentId}">${department.departmentName}</option>
						</core:forEach>
				    </select>
				  </div>
				  <button type="submit" class="btn btn-primary">Add Regulation</button>
			<%-- </form> --%>
			</form:form>
		</div>
	</div>

</body>
</html>