<!--<link rel="stylesheet" type="text/css" href="<@s.url "/resources/css/styles/easyui.css"/>" />
<link rel="stylesheet" type="text/css" href="<@s.url "/resources/css/styles/validator.css"/>"/>
<link rel="stylesheet" type="text/css" href="<@s.url "/resources/css/styles/icon.css" />"/>
<script type="text/javascript" src="<@s.url "/resources/common/js/jquery-1.6.4.min.js"/>"></script>
<script type="text/javascript" src="<@s.url "/resources/common/js/jquery.form.js"/>" ></script>
<script type="text/javascript" src="<@s.url "/resources/common/js/jquery.easyui.min.js"/>" ></script>
<script type="text/javascript" src="<@s.url "/resources/common/js/easyui-lang-zh_CN.js"/>" ></script>
<script type="text/javascript">
	var BasePath='${BasePath}';
</script>
-->
<script type="text/javascript" src="${commonDomainStatic}/boot.js"></script>
<link rel="stylesheet" type="text/css" href="<@s.url '/resources/css/styles/sys-window.css'/>" />
<!--界面上直接用   ${basePath}  -->
<#assign BasePath = springMacroRequestContext.getContextPath()/>

<script>
var BasePath = '${BasePath}';
var currentQuartzcenterNo='${(Session["session_user"].quartzcenterNo)!}';
var currentTransportPointNo='${(Session["session_user"].transportPointNo)!}';
var currentDriverName='${(Session["session_user"].driverName)!}';
var applicationPath='${(Session["applicationPath"])!}'; 
</script>