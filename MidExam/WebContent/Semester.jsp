<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Semester Management</title>
</head>
<body>
 <%
       
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
          
            response.sendRedirect("Login.jsp");
            return;
        }
    %>
<div align="center">
    <h1>Semester Management</h1>
    <c:if test="${semester.semesterId == null}">
        <h2>Add New Semester</h2>
    </c:if>
    <c:if test="${semester.semesterId != null}">
        <h2>Edit Semester</h2>
    </c:if>

    <form action="SemesterServlet" method="post">
        <c:if test="${semester.semesterId != null}">
            <input type="hidden" name="semesterId" value="<c:out value='${semester.semesterId}' />" />
        </c:if>
        <input type="hidden" name="action" value="${action}" />
        <table>
            <tr>
                <td>Semester Name:</td>
                <td><input type="text" name="semesterName" value="<c:out value='${semester.semesterName}' />" /></td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><input type="text" name="startDate" value="<c:if test="${not empty semester.startDate}">${fn:replace(semester.startDate, '-', '/')}</c:if>" /></td>
            </tr>
            <tr>
                <td>End Date:</td>
                <td><input type="text" name="endDate" value="<c:if test="${not empty semester.endDate}">${fn:replace(semester.endDate, '-', '/')}</c:if>" /></td>
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

    <h2>List of Semesters</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Semester ID</th>
            <th>Semester Name</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="semester" items="${semesters}">
            <tr>
                <td><c:out value="${semester.semesterId}" /></td>
                <td><c:out value="${semester.semesterName}" /></td>
                <td><c:if test="${not empty semester.startDate}">${fn:replace(semester.startDate, '-', '/')}</c:if></td>
                <td><c:if test="${not empty semester.endDate}">${fn:replace(semester.endDate, '-', '/')}</c:if></td>
                <td>
                    <a href="SemesterServlet?action=edit&semesterId=<c:out value='${semester.semesterId}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="SemesterServlet?action=delete&semesterId=<c:out value='${semester.semesterId}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="SemesterServlet?action=new">Add New Semester</a>
</div>
 <a href="logout" class="btn btn-danger">Logout</a>
</body>
</html>