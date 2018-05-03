<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js">
</script>

<script>
$(function(){
	
	$("#check-email").keyup(function() {
		var email = $("#check-email").val();
		//alert( email );
		if( email != "" ){
			$.ajax({
				url: "/lms/api/user/checkEmail?email="+email,
				type:"get",
				dataType:"json",
				success: function(response){
					console.log(response);
					if( response.result == "fail" ){
						console.log(response.message);
						//$("#check-image").hide();
						return;
					}
					
					if( response.data == "exist" ){
						//$("#img-checkid").data("flag","false");
						alert("해당 email이 존재합니다.");
						$("#check-email").val("");
						$("#check-image").hide();
						return;
					}
					if( response.data == "none" ){
						//$("#img-checkid").data("flag","true");
						$("#check-button").hide();
						$("#check-image").show();
						return;
					}
					
				}
			});//$.ajax
		}else{
			$("#check-image").hide();
		}
		
		
		
	});//$("#check-button").click
	
	
	$("#join-form").on("submit", function() {
		var allData = {};
		$.each( $(this).serializeArray(), function(index, o) {
			allData[ o.name ] = o.value;
		});//$.each

	 	if( allData["agreeProv"] == undefined ){
			alert("약관에 동의하세요!");
			event.preventDefault();
			return;
		} 

	});//$("#join-form").on("submit"
			
});// $(function()
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">
				<form:form
					modelAttribute="user" 
					id="join-form"
					name="joinForm"
					method="post"
					action="${pageContext.servletContext.contextPath }/user/join">
					
					<label class="block-label" for="name"><spring:message code="name" text="이름"/></label>		
					<input id="name" name="name" type="text" value="${user.name }" required="required">


					<label class="block-label" for="email">이메일</label>
					<form:input id="check-email" path="email" required="required" />
					<img id="check-image" src="${pageContext.request.contextPath }/assets/images/email-check.png" style="display:none"/>
					<input id="check-button" type="button" value="중복체크" style="display:;">
					<p style="margin:0; padding:0; color:red; text-align:left">
						<form:errors path="email" />		
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password" required="required"/>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>