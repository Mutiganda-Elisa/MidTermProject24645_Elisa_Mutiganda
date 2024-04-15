<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Management</title>
</head>
<body>

<div align="center">
    <h1>Teacher Management</h1>
    <c:if test="${teacher.teacherId == null}">
        <h2>Add New Teacher</h2>
    </c:if>
    <c:if test="${teacher.teacherId != null}">
        <h2>Edit Teacher</h2>
    </c:if>

    <form action="TeacherServlet" method="post">
        <c:if test="${teacher.teacherId != null}">
            <input type="hidden" name="teacherId" value="<c:out value='${teacher.teacherId}' />" />
        </c:if>
        <input type="hidden" name="action" value="${action}" />
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName" value="<c:out value='${teacher.firstName}' />" /></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName" value="<c:out value='${teacher.lastName}' />" /></td>
            </tr>
            <tr>
                <td>Qualification:</td>
                <td>
                    <select name="qualification">
                        <c:forEach var="qualification" items="${qualifications}">
                            <option value="${qualification}"
                                    <c:if test="${teacher.qualification == qualification}">selected</c:if>>${qualification}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <c:if test="${action == 'insert'}">
                        <input type="submit" value="Save" />
                    </c:if>
                    <c:if test="${action == 'update'}">
                        <input type="submit" value="Update" />
                    </c:if>
                </td>
            </tr>
        </table>
    </form>

    <h2>List of Teachers</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Teacher ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Qualification</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="teacher" items="${teachers}">
            <tr>
                <td><c:out value="${teacher.teacherId}" /></td>
                <td><c:out value="${teacher.firstName}" /></td>
                <td><c:out value="${teacher.lastName}" /></td>
                <td><c:out value="${teacher.qualification}" /></td>
                <td>
                    <a href="TeacherServlet?action=edit&teacherId=<c:out value='${teacher.teacherId}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="TeacherServlet?action=delete&teacherId=<c:out value='${teacher.teacherId}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="TeacherServlet?action=new">Add New Teacher</a>
</div>
                                                             <a href="logout" class="btn btn-danger">Logout</a>
</body>
</html>
