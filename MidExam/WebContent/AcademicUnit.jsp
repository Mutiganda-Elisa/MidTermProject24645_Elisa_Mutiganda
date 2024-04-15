<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Academic Unit</title>
</head>
<body>

    <h1>Academic Unit</h1>
    
    <c:choose>
        <c:when test="${not empty academicUnit}">
            <form action="academicunit" method="post">
                <input type="hidden" name="action" value="${empty academicUnit.academicId ? 'create' : 'update'}">
                <input type="hidden" name="academicId" value="${academicUnit.academicId}">
                <label for="academicCode">Academic Code:</label>
                <input type="text" id="academicCode" name="academicCode" value="${academicUnit.academicCode}" required>
                <br>
                <label for="academicName">Academic Name:</label>
                <input type="text" id="academicName" name="academicName" value="${academicUnit.academicName}" required>
                <br>
                <label for="type">Type:</label>
                <select id="type" name="type" required>
                    <c:forEach var="eAcademicUnit" items="${EAcademicUnit}">
                        <option value="${eAcademicUnit}" ${academicUnit.type == eAcademicUnit ? 'selected' : ''}>${eAcademicUnit}</option>
                    </c:forEach>
                </select>
                <br>
                <label for="parentId">Parent:</label>
                <input type="number" id="parentId" name="parentId" value="${academicUnit.parent != null ? academicUnit.parent.academicId : 0}">
                <br>
                <button type="submit">Save</button>
                  <a href="logout" class="btn btn-danger">Logout</a>
            </form>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Parent</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="unit" items="${academicUnits}">
                        <tr>
                            <td>${unit.academicId}</td>
                            <td>${unit.academicCode}</td>
                            <td>${unit.academicName}</td>
                            <td>${unit.type}</td>
                            <td>${unit.parent != null ? unit.parent.academicName : ''}</td>
                            <td>
                                <a href="academicunit?action=read&id=${unit.academicId}">View</a>
                                <a href="academicunit?action=update&id=${unit.academicId}">Edit</a>
                                <a href="academicunit?action=delete&id=${unit.academicId}">Delete</a>
                            </td>
                            
                        </tr>
                        
                         <a href="logout" class="btn btn-danger">Logout</a>
                    </c:forEach>
                </tbody>
            </table>
            <a href="academicunit?action=create">Create New Academic Unit</a>
        </c:otherwise>
    </c:choose>
     
</body>
</html>