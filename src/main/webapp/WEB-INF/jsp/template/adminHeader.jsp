<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<core:set var="req" value="${pageContext.request}" />
<head>
	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
</head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 10px;">
  <a class="navbar-brand" href="admin/department">Employee Management System</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="admin/department">Departments<span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Employees
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="admin/addEmployee">Add Employee</a>
          <a class="dropdown-item" href="admin/viewEmployee">View Employees</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="regulationLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Regulations
        </a>
        <div class="dropdown-menu" aria-labelledby="regulationLink">
          <a class="dropdown-item" href="admin/addRegulation">Add Regulation</a>
          <a class="dropdown-item" href="admin/viewRegulation">View Regulations</a>
        </div>
      </li>
    </ul>
    
    <ul class="navbar-nav ml-auto">
    	<li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="userLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <core:out value="${sessionScope.employee.firstName} ${sessionScope.employee.lastName}" />
	        </a>
	        <div class="dropdown-menu" aria-labelledby="userLink">
	          <a class="dropdown-item" href="logout">Log Out</a>
	        </div>
	      </li>
    </ul>
  </div>
</nav>

<style>
	table{
		width:100%;
	}
</style>
