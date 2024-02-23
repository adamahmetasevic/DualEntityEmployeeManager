<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
</head>
<body>
    <h1>Edit Employee</h1>
    
    <form action="editEmployeeServlet" method="post">
        <input type="hidden" name="id" value="${employee.id}">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${employee.name}" required><br><br>
        
        <label for="position">Position:</label>
        <input type="text" id="position" name="position" value="${employee.position}" required><br><br>
        
        <label for="department">Department:</label>
        <select id="department" name="departmentId">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}" <c:if test="${department.id eq employee.department.id}">selected</c:if>>${department.name}</option>
            </c:forEach>
        </select><br><br>
        
        <input type="submit" value="Update Employee">
    </form>
    
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
