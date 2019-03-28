<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="DC调度系统" />
<meta name="Description" content="DC调度系统" />
<title>多语言词库管理系统</title>
<#include  "/WEB-INF/ftl/common/header.ftl" >
<script type="text/javascript">
	
</script>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">

<div class="header" data-options="
	region: 'north',
	border: false
">
	<div class="wrapper">
        <div class="logo">
            <h1>多语言词库管理系统</h1>
        </div>
        <div class="nav">
            <div class="system">
                <span class="welcome">你好！${(Session["session_user"].userName)!} 欢迎进入！</span>
                <span class="logout">
					<a href="<@s.url "/logout"/>">退出</a>
				</span>
           	</div>            
			<div id="subSystem" class="subSys"></div>
        </div>
	</div>
</div>

<div style="width: 180px;" title="左侧菜单" data-options="
    region:'west',
	split:true,
	minSplit:true
">
    <div id="leftMenu"></div>
</div>

<div data-options="
    region:'center'
">
    <div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">
        <div title="系统桌面"  data-options="icon:'icon-home'">
            <div class="pd10">
                <img src="<@s.url "/resources/images/welcome.jpg" />"/>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<@s.url "/resources/common/js/index.js"/>" ></script>
</body>
</html>
