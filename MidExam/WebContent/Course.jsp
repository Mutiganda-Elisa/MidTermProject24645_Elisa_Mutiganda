<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Management</title>
</head>
<body>
}
    %>
    <h1>Course Management</h1>
    <a href="courses?action=create">Create New Course</a>
    <table>
        <thead>
            <tr>
                <th>Course Code</th>
                <th>Name</th>
                <th>Semester</th>
                <th>Department</th>
                <th>Teacher</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.courseCode}</td>
                    <td>${course.name}</td>
                    <td>
                        <select name="semester">
                            <c:forEach items="${semesters}" var="semester">
                                <option value="${semester.id}" <c:if test="${course.semester.id == semester.id}">selected</c:if>>${semester.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="academicUnit">
                            <c:forEach items="${academicUnits}" var="academicUnit">
                                <option value="${academicUnit.id}" <c:if test="${course.department.id == academicUnit.id}">selected</c:if>>${academicUnit.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="teacher">
                            <c:forEach items="${teachers}" var="teacher">
                                <option value="${teacher.id}" <c:if test="${course.teacher.id == teacher.id}">selected</c:if>>${teacher.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <a href="courses?action=edit&id=${course.courseId}">Edit</a>
                        <a href="courses?action=delete&id=${course.courseId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
     <a href="Login.jsp" class="btn btn-danger">Logout</a>
</body>
</html>