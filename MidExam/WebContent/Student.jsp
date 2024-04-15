<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>
<html>
<head>
    <title>Student Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h1>Student Management</h1>

    <div class="row">
        <div class="col-md-6">
            <h2>Create/Update Student</h2>
            <form action="StudentServlet" method="post">
                <input type="hidden" name="action" value="${student == null ? 'create' : 'update'}">
                <input type="hidden" name="studentId" value="${student != null ? student.studentId : ''}">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${student != null ? student.firstName : ''}">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${student != null ? student.lastName : ''}">
                </div>
                <div class="form-group">
                    <label for="dateOfBirth">Date of Birth</label>
                    <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${student != null ? student.dateOfBirth : ''}">
                </div>
                <button type="submit" class="btn btn-primary">${student == null ? 'Create' : 'Update'}</button>
            </form>
        </div>
        <div class="col-md-6">
            <h2>Students</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.dateOfBirth}</td>
                        <td>
                            <a href="StudentServlet?action=edit&studentId=${student.studentId}" class="btn btn-primary">Edit</a>
                            <a href="StudentServlet?action=delete&studentId=${student.studentId}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
 <a href="logout" class="btn btn-danger">Logout</a>
</body>
</html>