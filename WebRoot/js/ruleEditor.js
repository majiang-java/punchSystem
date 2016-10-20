var i = 0;
var j=0;
var flagSelect ="<span><select id='flagSelect'><option value=''>请选择</option><option value='+'>加</option><option value='-'>减</option><option value='*'>乘</option><option value='/'>除</option><option value='=='>等于</option><option value='!='>不等于</option><option value='>'>大于</option><option value='>='>大于等于</option><option value='<'>小于</option><option value='<='>小于等于</option></select></span>";
var flagSelect2 ="<span><select id='flagSelect2'><option value=''>请选择</option><option value='+'>加</option><option value='-'>减</option><option value='*'>乘</option><option value='/'>除</option><option value='=='>等于</option><option value='!='>不等于</option><option value='>'>大于</option><option value='>='>大于等于</option><option value='<'>小于</option><option value='<='>小于等于</option></select></span>";

var miniInput="<input type='text' class='input-small' placeholder='Type something…'>";
//var pulsButton="<a href='#' class='pulsButton' onClick='addText()'><i class='icon-plus-sign'></i></a>";
var minusBuffton="<a href='#' class='minusBuffton'><i class='icon-minus-sign'></i></a>";

$(document).ready(function(){
	
	$("#elementSelect").bind("change", function () { 
		
		var param = $("#elementSelect option:selected").text();
		var parm = "#element"+(i);
		$(parm).empty();
		$(parm).append("<a href='#fieldModel' role='button' class='btn' data-toggle='modal'>"+param+"</a>");
		
		//$('#condition').css("display","none");
		i++;
		$("#close").click();
	 });
	
	$("#con").click(function(){
		$("#context").append("<div id='element"+(i)+"'></div><br><div id='field"+(1)+"'></div>");
		
	});
	


	
	$("#fieldSelect").bind("change", function () { 
		var param = $("#fieldSelect option:selected").text();
		var parm = "#field"+(i);
		var pulsButton = "<a href='#' class='pulsButton' onClick='addText()' id='btn"+j+"'><i class='icon-plus-sign'></i></a>";
		$(parm).append("<a href='#field' class='btn btn-small'>"+param+"</a>&nbsp;"+flagSelect+"&nbsp;"+miniInput+pulsButton+minusBuffton+"<br>");
		
		//$('#condition').css("display","none");
		
		$("#close1").click();
	 });
	
	$("#context").find("a").click(function(){
		alert($(this).attr("id"));
	});
	
	
});


function addText(){
	var param = "#btn"+j;
	var pulsButton = "<a href='#' class='pulsButton' onClick='addText()' id='btn"+j+"'><i class='icon-plus-sign'></i></a>";
	$(param).after(flagSelect+miniInput+pulsButton+minusBuffton);
	j++;
	//$(this).after(flagSelect+pulsButton+minusBuffton);
	
}

function getI(i){
	i++;
	return i;
}
