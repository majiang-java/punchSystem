<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="./css/bootstrap-responsive.css" type="text/css" />
<script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>长治地方税务打卡系统</title>
</head>
<body>

	<jsp:include page="head.jsp" flush="true"/> 
	
	<form action ="person!update.action">
		<table align="center">
			<tr>
				<td align="left">卡号</td>
				<td><input type="text" name="CardNum" value="${CardNum} "/><br></td>
			
			</tr>
			<tr>
				<td align="left">姓名</td>
				<td><input type="text" name="Name"  value="${Name }"/><br></td>
			
			</tr>
			<tr>
				<td align="left">次数</td>
				<td><input type="text" name="CardLog" value="${CardLog }"/><br></td>
			</tr>
			
		</table>
	<div align="center"><input type="submit" class="btn btn-info" value="修改信息"/></div>
	</form>
</body>
</html>