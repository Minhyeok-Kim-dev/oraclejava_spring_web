<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<spring:url value="/js/jQuery.js" var="jqueryJs" />

<script src="${jqueryJs}"></script>
<script>
	$(document).ready(function() {
		$('#del').click(function(e) {   
		      //e.preventDefault();
		      alert("test");
	   });
	});
</script>
<title>Member List</title>
</head>
<body>
	<h2>회원 목록</h2>
	[<a href="<c:url value="/member/add"/>">새 회원 추가</a>]<br/><br/>
	<table border="1">
		<thead>
			<th>memberId</th>
			<th>passwd</th>
			<th>email</th>
			<th>userType</th>
			<th>active</th>
			<th>regDate</th>
			<th>수정</th>
			<th>삭제</th>
		</thead>
		<tbody>
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.memberId}</td>
					<td>${member.passwd}</td>
					<td>${member.email}</td>
					<td>${member.userType}</td>
					<td>${member.active}</td>
					<td>${member.regDate}</td>
					<td>[<a href="<c:url value="/member/edit/${member.memberId}" />">수정</a>]</td>
					<td>[<a id="del" href="<c:url value="/member/delete/${member.memberId}" />">삭제</a>]</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>