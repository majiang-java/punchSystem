<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib prefix="s" uri="/struts-tags" %> 
     
   <%
   int curr = Integer.parseInt(request.getAttribute("totlePage").toString());
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="./css/bootstrap-responsive.css" type="text/css" />
<script type="text/javascript" src="./js/jquery-1.5.2.min.js"></script>
<script language="javascript" type="text/javascript" src="./My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>统计</title>
</head>
<body>
	<jsp:include page="head.jsp" flush="true"/>   
<div class="showalert"></div>

<form action="statistic!getStatisticList.action" method="post">
<center><table>
<tr>
<td>卡号</td>
<td><input type='text' name="cardNum"></td>
<td>姓名</td>
<td><input type='text' name="name"></td>

</tr>
<tr>
<td>最高日期</td>
<td><input type='text' name="highDate" onClick="WdatePicker()"></td>
<td>最低日期</td>
<td><input type='text' name="lowDate" onClick="WdatePicker()"></td>

</tr>
<tr><td colspan='4' align='center'><input class="btn btn-info" type="submit" ></td></tr>
</table>
</center>
</form>
<hr>



<table class="table table-striped table-bordered table-hover table-condensed">
				<tr>
					<th>卡号</th>
					<th>姓名 </th>
					<th>所剩余次数</th>
					<th>打卡时间</th>
				</tr>
			<s:if test="logList.size()==0">
			  <h2>没有可以显示的数据</h2>
			</s:if>
			<s:else>
			<s:iterator value="logList" status="status" var="f">
			    <tr>
					<td><s:property value="%{#f.cardNum}" /> </td>
					<td><s:property value="%{#f.name}" /> </td>
					<td><s:property value="%{#f.cardLog}" /> </td>
					<td><s:property value="%{#f.lastModifyDate}" /></td>
					
				</tr>

			</s:iterator>
			
			</s:else>
			
</table>
<div class="pagination">
<ul>
<li><a href="statistic!getStatisticList.action?currentPage=<%=curr-1%>" id="prev">前一页</a></li>
<%
  
   for(int i = 1;i<=curr;i++){
 %>
	<li><a href="statistic!getStatisticList.action?currentPage=<%=i%>"><%=i%></a></li>

	<%
	}
	 %>
<li><a href="statistic!getStatisticList.action?currentPage=<%=curr+1%>" id="after">后一页</a></li>
</ul></div>
	统计次数：${count}
				


</body>
</html>