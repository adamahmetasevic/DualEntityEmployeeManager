<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Employees</title>
</head>
<body>
    <h1>View Employees</h1>
    
    <table border="1">
        <tr>
            <th>Employee ID</th>
            <th>Name</th>
            <th>Position</th>
            <th>Department</th>
            <th>Edit</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.position}</td>
                <td>${employee.department.name}</td> 
                <td><a href="editEmployeeServlet?id=${employee.id}">Edit</a></td> 
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
