<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="./js/jquery-1.5.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="hero-unit" align="center">
		<h1><div id="title"></div></h1>
	</div>
</body>
</html>
<script>
$.get("person!getTitle.action", { title: "title" },
		  function(data){
		  $("#title").text("");
		 //   alert("Data Loaded: " + data);
		    $("#title").text(data);
		  });

</script>