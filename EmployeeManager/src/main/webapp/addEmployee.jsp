<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Employee</title>
</head>
<body>
    <h1>Add Employee</h1>
    
    <form action="addEmployeeServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="position">Position:</label>
        <input type="text" id="position" name="position" required><br><br>
        
        <label for="department">Department:</label>
        <select id="department" name="departmentId">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}">${department.name}</option>
            </c:forEach>
        </select><br><br>
        
        <input type="submit" value="Add Employee">
    </form>
    
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
