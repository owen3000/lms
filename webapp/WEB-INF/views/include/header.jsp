<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div id="header">
			<h1><a href="${pageContext.servletContext.contextPath }/">도서대여서비스</a></h1>
			<ul>
			<c:choose>
				<c:when test="${authUser ne null }">
					
					<li><a href="${pageContext.servletContext.contextPath }/user/modify">회원정보수정</a><li>
					<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a><li>
					<c:if test="${authUser.role eq 'admin' }">
						<li><a href="${pageContext.servletContext.contextPath }/admin">관리자페이지</a><li>
					</c:if>
					<li>${authUser.name }님 안녕하세요 ^^; </li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/user/loginForm">로그인</a><li>
					<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a><li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>