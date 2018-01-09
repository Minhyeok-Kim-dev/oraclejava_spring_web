<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Edit</title>
</head>
<body>
	<h2>회원 수정</h2>
	<form:form method="post" modelAttribute="memberForm">
		<form:label path="memberId">사용자id:</form:label>
		<form:input path="memberId" readonly="true"/><br/>
		<form:label path="passwd">비밀번호:</form:label>
		<form:password path="passwd"/><br/>
		<form:label path="email">이메일:</form:label>
		<form:input path="email"/><br/>
		<form:label path="userType">사용자타입:</form:label>
		<form:input path="userType"/><br/>
		<br/>
		<input type="submit" value="저장" />
	</form:form>
</body>
</html>