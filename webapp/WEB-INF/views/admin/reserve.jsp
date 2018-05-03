<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>lms</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/rent.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>대여일</th>
						<th>반납일</th>
						<th>email</th>
					</tr>
					<c:forEach items="${list.content }" var="l" varStatus="status">
						<tr>
							<td>${list.totalElements - (list.number)*list.size - status.index }</td>
							<td>${l.item.title }</td>
							<td>${l.item.category.name }</td>
							<td>
							 ${l.rentDate }
							</td>
							<td>
							 ${l.returnDate }
							</td>
							<td>
							 ${l.user.email }
							</td>
						</tr>					
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
 					<c:if test="${pb.previousPageGroup }">
						<li><a href="${pageContext.servletContext.contextPath }/admin/reserve?page=${pb.startPage-1}">◀</a></li>
					</c:if>
					<c:forEach var="i" begin="${pb.startPage }" end="${pb.endPage }">
						
						<c:choose>
							<c:when test="${(list.number+1) eq i }">
								<li class="selected">${i }</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/admin/reserve?page=${i }">${i }</a></li>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>	

					<c:if test="${pb.nextPageGroup }">
						<li><a href="${pageContext.servletContext.contextPath }/admin/reserve?page=${pb.endPage+1}">▶</a></li>
					</c:if>  
						
					</ul>
				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp" />
		</div>
	</div>
</body>
</html>