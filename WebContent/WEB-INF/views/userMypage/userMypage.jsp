<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userMypage</title>
</head>
<body>

	<table>
		<tr>
			<td>아이디</td>
			<td>${userId}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${dto.email}</td>
		</tr>
		<tr>
			<td>휴대폰번호</td>
			<td>${dto.tel}</td>
		</tr>
		<tr>
			<td>회사명</td>
			<td>${dto.bizName}</td>
		</tr>
		<tr>
			<td>부서명</td>
			<td>${dto.dept}</td>
		</tr>
		<tr>
			<td>직위</td>
			<td>${dto.position}</td>
		</tr>
	</table>
</body>
</html>