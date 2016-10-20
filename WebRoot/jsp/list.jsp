<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="s" uri="/struts-tags" %>  
  
  <%
   int curr = Integer.parseInt(request.getAttribute("totlePage").toString());
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="./css/bootstrap-responsive.css" type="text/css" />
<link rel="stylesheet" href="./css/smartpaginator.css" type="text/css" />
<script type="text/javascript" src="./js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="./js/smartpaginator.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>长治地方税务打卡系统</title>
</head>
<body>
	<jsp:include page="head.jsp" flush="true"/>   
<div class="showalert"></div>
<table class="table table-striped table-bordered table-hover table-condensed">
				<tr>
					<th>操作</th>
					<th>卡号</th>
					<th>姓名 </th>
					<th>所剩余次数</th>
					<th>生校日期</th>
				</tr>
			<s:if test="personList.size()==0">
			  <h2>没有可以显示的数据</h2>
			</s:if>
			<s:else>
			<s:iterator value="personList" status="status" var="f">
			    <tr>
					<td><input class="box" type="checkbox" value="<s:property value='%{#f.CardNum}' />"/> </td>
					<td><s:property value="%{#f.CardNum}" /> </td>
					<td><s:property value="%{#f.Name}" /> </td>
					<td><s:property value="%{#f.CardLog}" /> </td>
					<td><s:property value="%{#f.LastModifyTime}" /></td>
				</tr>

			</s:iterator>
			</s:else>
</table>
<div class="pagination">
<ul>
<li><a href="person!getAllList.action?currentPage=<%=curr-1%>" id="prev">前一页</a></li>
<%
  
   for(int i = 1;i<=curr;i++){
 %>
	<li><a href="person!getAllList.action?currentPage=<%=i%>"><%=i%></a></li>

	<%
	}
	 %>
<li><a href="person!getAllList.action?currentPage=<%=curr+1%>" id="after">后一页</a></li>
</ul></div>
<hr>

<a class="btn btn-info" href="person!add.action">注册新人</a>|
<a class="btn btn-info" id="modify" onclick="modifyRow()">修改次数</a>|
<a class="btn btn-info" id="delete" onclick="deleteRow()">删除</a>|
<a class="btn btn-info" href="statistic!getStatisticList.action">统计</a>
<input id="hidden" type="hidden" value="${flag}">
 
<center>修改标头：<input type="type" id="comcde" name="comcde" onChange="setTitle()"/><br></center>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
	 var returnMsg = $("#hidden").val();
	
	 if(returnMsg ==""){
	 	
		 return ;
	 }
	 if(Number(returnMsg) >0){
	 	alert("操作成功");
	 }else if(Number(returnMsg) ==0){
	 	alert("操作失败");
	 }
	});
	function deleteRow(){
		var i = 0;
		var param;
		$(".box").each(function(){
			if(($(this).attr("checked"))==true){
				param = $(this).val();
				i++;
			//	alert(i);
			}
		});
		if(i==0){
			$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>在修改的时候，请先选择对应的行</div>");
			return false;
		}else if(i>1){
			$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>在进行删除操作时，不能勾选多个标签.</div>");
			return false;
		}else{
	//		alert(param);
			window.location.href='person!delete.action?flags='+param;
		}
		
	}
	
	function modifyRow(){
		var i = 0;
		var param;
		$(".box").each(function(){
			if(($(this).attr("checked"))==true){
				param = $(this).val();
				i++;
			//	alert(i);
			}
		});
		if(i==0){
			$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>在修改的时候，请先选择对应的行</div>");
			return false;
		}else if(i>1){
			$(".showalert").empty();
			$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>在进行修改操作时，不能勾选多个标签.</div>");
			return false;
		}else{
	//		alert(param);
			window.location.href='person!edit.action?flag='+param;
		}
		
	}
	
	function closeTip(){
		$(".showalert").empty();
	}
	
	function  setTitle(){
		var titleMsg = $("#comcde").val();
		$.get("person!setTitle.action", { titleMsg: titleMsg },
			  function(data){
			  //  alert("Data Loaded: " + data);
			    if(data == 1){
			    $(".showalert").empty();
				$(".showalert").append("<div class='alert alert-success'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Success!</strong>标题修改成功</div>");
			    }else{
			       $(".showalert").empty();
				$(".showalert").append("<div class='alert alert-error'><button type='button' onclick='closeTip()' class='close' data-dismiss='alert'>&times;</button><strong>Warning!</strong>标题修改失败</div>");
			
			    }
			  });
	
	
	}
	
</script>
</html>