<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->


</head>

<body>

	<form method="post" action="<%=path%>/user/login">
	
	 <div class="int">
        <label for="email">邮箱:</label>
        <input type="text" id="email" name="email" class="required" />
    </div>
    <div class="int">
        <label for="密碼">密碼:</label>
        <input type="text" name="password" id="password" class="required" />
    </div>
    
    
    <div class="sub">
        <input type="submit" value="提交" id="send"/><input type="reset" id="res"/>
    </div>


	</form>
</body>


<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../js/login.js"></script>

</html>