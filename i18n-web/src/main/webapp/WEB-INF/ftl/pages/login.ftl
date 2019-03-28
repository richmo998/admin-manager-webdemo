<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="多语言词库管理系统" />
    <meta name="Description" content="多语言词库管理系统" />
    <title>多语言词库管理系统</title>
    <link rel="stylesheet" type="text/css" href="<@s.url "/resources/css/login.css"/>">
<script type="text/javascript">
	window.isIframe = function() {
	    return top.location != self.location;
	};
	if(isIframe()){
		top.location.href = self.location.href
	}
</script>
    <#include  "/WEB-INF/ftl/common/header.ftl" >
</head>
<body>
<div class="loginbg"></div>
<div class="divLoginbox">
    <div class="logo"><img src="<@s.url "/resources/images/login/logo.png" />" /></div>
    <div class="loginform">
        <div class="loginform2">
            <div class="forms">
                <input type="text" id="userName" name="userName" class="inputstyle inputuser" placeholder="用户名">
                <div class="line10px"></div>
                <input type="password" id="password" name="password" class="inputstyle inputpassword" placeholder="密码">
                <div class="line10px"></div>
                <div class="inputema">
                    <input type="text" class="inputstyle" placeholder="验证码">
                    <img src="<@s.url "/resources/images/login/ma01.png" />" />
                </div>
                <div class="line10px"></div>
                <input name="记住密码" type="checkbox" id="remeber" value="remeber"> <label for="remeber">记住密码</label>
            </div>
        </div>
    </div>
    <div class="loginbtn" onclick="login()">&nbsp;</div>
    <div class="line20px">
    </div>
    <p style="text-align:center; color:#fff;">Copyright &copy; 2016 云盛海宏信息技术（深圳）有限公司 - DC 多语言词库管理系统</p>
</div>
<script type="text/javascript">
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            login();
        }
    });
    function login() {
        var userName = $('#userName').val();
        var password = $('#password').val();
        if (userName=="" || password=="") {
            alert('用户名或密码不能为空！',error);
            return false;
        }
        var data = {};
        data.userName = userName;
        data.password = password;
        $.ajax({
            url : BasePath + '/login',
            type : 'post',
            data : data,
            success : function(d) {
                if (d.success == "false") {
                    $.messager.alert('错误', d.message);
                } else {
                    window.location.href = BasePath + '/index';
                }
            }
        });
    }
</script>
</body>
</html>
