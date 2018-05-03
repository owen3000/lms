<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LMS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
<%-- 				<form id="search_form" action="${pageContext.request.contextPath }" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form> --%>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list.content }" var="l" varStatus="status">
						<tr>
							<td>${list.totalElements - (list.number)*list.size - status.index }</td>
							<td>${l.title }</td>
							<td>
								${l.category.name }
							</td>
							<td>
							<c:if test="${authUser ne null }">
							<c:choose>
								<c:when test="${l.rented eq 'no' }">
									<a href="${pageContext.servletContext.contextPath }/rent?item-id=${l.id}" class="btn">대여</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.servletContext.contextPath }/reserve?item-id=${l.id}" class="btn">예약</a>
								</c:otherwise>
							</c:choose>
							</c:if>
							</td>
						</tr>					
					</c:forEach>
				</table>
				
				
				<div class="pager">
					<ul>
					 <c:if test="${pb.previousPageGroup }">
						<li><a href="${pageContext.servletContext.contextPath }/main?page=${pb.startPage-1}">◀</a></li>
					</c:if>
					<c:forEach var="i" begin="${pb.startPage }" end="${pb.endPage }">
						
						<c:choose>
							<c:when test="${(list.number+1) eq i }">
								<li class="selected">${i }</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/main?page=${i }">${i }</a></li>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>	

					<c:if test="${pb.nextPageGroup }">
						<li><a href="${pageContext.servletContext.contextPath }/main?page=${pb.endPage+1}">▶</a></li>
					</c:if>  
						
					</ul>
				</div>				
				<div class="bottom">
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>