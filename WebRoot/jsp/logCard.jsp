<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%
 String appContext = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort() + appContext; 
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="./css/bootstrap-responsive.css"
	type="text/css" />
	
<script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>刷卡页面</title>

</head>

<body>
	<jsp:include page="head.jsp" flush="true" />

	<form action="person!logCard.action" method="post">
		<div class="showalert"></div>
		<center>
			<img src="<%=appContext%>/images/location.jpg"/><br> <br> <br> 
			卡号：<input type="text" id="CardNum" name="CardNum" onChange="checkUser()" /> <input
				type="submit" class="btn btn-info" value="刷卡" onClick="" />
		</center>
	</form>

	<input type="hidden" id="flag"
		value="<s:property value="#request.msg"/>">
</body>
</html>
<script>
	$(document).ready(function(){
	 var returnMsg = $("#flag").val();
	 if(returnMsg == ""){
	 return ;
	 }
	// alert(returnMsg);
	 if(Number(returnMsg) =="-50"){
		$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>抱歉您的卡不存在</div>");
	 }
	  if(Number(returnMsg) =="-100"){
		$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>抱歉您的卡本月已经消费完毕</div>");
	 }else if(Number(returnMsg)>=0 ){
	 	$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-success'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Success!</strong>恭喜您，刷卡成功，剩余次数为"+returnMsg+"</div>");
	
	 }
	});
	
	function checkUser(){
		var CardNum = $("#CardNum").val();
		$.get("person!chectUser.action", { flag: CardNum },
		  function(data){
		  //  alert("Data Loaded: " + data);
		    if(data == 1){
		    $(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>抱歉您的卡不存在</div>");
		    }
		  });
	
	}
	function closeTip(){
		$(".showalert").empty();
	}

</script>