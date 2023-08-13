<%-- 
    Document   : productReport
    Created on : Mar 2, 2023, 1:21:47 PM
    Author     : yelia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
        <script type="text/javascript">
            function limitSelection() {
                var checkboxes = document.getElementsByName("selectedClasses");
                var selectedCount = 0;
                for (var i = 0; i < checkboxes.length; i++) {
                    if (checkboxes[i].checked) {
                        selectedCount++;
                    }
                }
                if (selectedCount < 1) {
                    alert("Please select at least 1 product class");
                    return false;
                } else if (selectedCount > 1) {
                    alert("Please select no more than 1 product classes");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <form action="ManagerController" method="POST">
            <table id="table">
                <thead id="thead">
                    <tr id="trow">
                        <th id="th">Id</th>
                        <th id="th">Name</th>
                        <th id="th"></th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    <c:choose>
                        <c:when test="${productClassList != null }">
                            <c:forEach items="${productClassList}" var="class">
                                <tr id="trow">
                                    <td id="tdata">${class.id}</td>
                                    <td id="tdata">${class.name}</td>
                                    <td>
                                        <input type="checkbox" name="selectedClasses" value="${class.id}">
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </tbody>
            </table>
            <input type="hidden" name="action" value="postProductAnalysis">
            <input type="submit" value="Submit" onclick="return limitSelection()">
        </form>
    </body>
</html>
