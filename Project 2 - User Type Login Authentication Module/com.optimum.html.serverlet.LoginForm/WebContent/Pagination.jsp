<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.table{
table-layout: fixed
}
</style>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<c:set var="categories" value="${sessionScope.categories}" />
<c:set var="rowsPerPage" value="5" />
<c:set var="pageNumber" value="${param.pageNumber}"/>
<c:set var="a">
    <fmt:formatNumber value="${categories.rowCount/rowsPerPage}" maxFractionDigits="0"/>
</c:set>
 
<c:set var="b" value="${categories.rowCount/rowsPerPage}" />
 
<c:choose>
    <c:when test="${a==0}">
        <c:set var="numberOfPages" value="1" scope="session"/>   
    </c:when>
 
    <c:when test="${b>a}">
        <c:set var="xxx" value="${b%a}"/>
        <c:if test="${xxx>0}">
            <c:set var="numberOfPages" value="${b-xxx+1}" scope="session"/>   
        </c:if>
    </c:when>
 
    <c:when test="${a>=b}">
        <c:set var="numberOfPages" value="${a}" scope="session"/>    
    </c:when>
</c:choose>
 
<c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
<c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
<center>
    <table class="table" border="1">                    
        		<tr>
					<td><b>Serial No.</b></td>
					<td cellpadding="5"><b>Full Name</b></td>
					<td><b>Email Address</b></td>
					<td><b>Mobile No.</b></td>
					<td><b>Status</b></td>
					<td><b>Locked</b></td>
				</tr>
 
        <c:forEach items="${categories.rows}" var="JEE" begin="${start}" end="${stop}">
            <tr>
                <td><c:out value="${JEE.Serial}" /></td>
				<td><c:out value="${JEE.Fname}" /> <c:out value="${JEE.Lname}" /></td>
				<td><c:out value="${JEE.Email}" /></td>
				<td><c:out value="${JEE.Mobile}" /></td>
				<td><c:out value="${JEE.Status}" /></td>
				<td><c:out value="${JEE.Dlock}" /></td>
            </tr>
        </c:forEach>
    </table>
 
    <%--For displaying Previous link --%>
    <c:if test="${pageNumber gt 1}">
        <a href="Pagination.jsp?pageNumber=${pageNumber - 1}">Previous</a>
    </c:if>
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${i!=pageNumber}">
                <a href="Pagination.jsp?pageNumber=<c:out value="${i}"/>"><c:out value="${i}"/></a>
            </c:when>
            <c:otherwise>
                <c:out value="${i}"/>
            </c:otherwise>        
        </c:choose>       
    </c:forEach>  
    <%--For displaying Next link --%>
    <c:if test="${pageNumber lt numberOfPages}">
        <a href="Pagination.jsp?pageNumber=${pageNumber + 1}">Next</a>
    </c:if>
</center>

</body>
</html>