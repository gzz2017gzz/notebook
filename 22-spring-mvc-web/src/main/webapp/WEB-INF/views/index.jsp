<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<script src="/static/js/jquery-3.2.0.min.js"></script>
<title>用户登录</title>
</head>
<body>
	<h2>Hello World!</h2>
	<input name="name" id="name" />	<br>
	<input name="id" id="id" />	<br>
	<input name="pwd" id="pwd" />	<br>
	<input type="button" onclick="addUser()" value="提交" />
</body>
<script type="text/javascript">
function addUser() {
	var data = {
		"name" : $("#name").val(),
		"id" : $("#id").val(),
		"pwd" : $("#pwd").val()
	};
	$.ajax({
		type : "post", 
		url : "/user/add",
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(result) {
			console.log(result);//打印服务端返回的数据(调试用)
		},
	});
}
</script>
</html>
