<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>系统日志管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<#include  "/WEB-INF/ftl/common/header.ftl" >
    <script type="text/javascript" src="<@s.url"/resources/js/date.js"/>"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div region="center" data-options="border:false">
    <div class="easyui-layout" data-options="fit:true" id="subLayout">

        <h1>Well </h1>
        <!--搜索start-->
        <div data-options="region:'north',border:false">
            <div class="search-div">
                <form name="wordListLogForm" id="wordListLogForm" method="post">
                    <table class="form-tb">
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;中文简体:</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="zhCn" id="zhCn"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;操作人姓名:</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="createUser" id="createUser"
                                       data-options=""/>
                            </td>

                        </tr>
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;修改时间:</td>
                            <td style="width:115px;">
                                <input id="createTime" name="createTime" type="text"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"
                                       required="true" style="width: 145px;"/>
                            </td>


                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至</td>
                            <td style="width:115px;">
                                <input id="updateTime" name="updateTime" type="text"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"
                                       required="true" style="width: 145px;"/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;操作类型:</td>
                            <td style="width:115px;">
                                <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="operation"
                                        name="operation" required>
                                    <option value="">全部</option>
                                    <option value="add">新增</option>
                                    <#--<option value="delete">删除</option>-->
                                    <option value="update">修改</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a id="searchButton" href="#" class="easyui-linkbutton" style="width:70px"
                                   data-options="iconCls:'icon-search'"
                                   onclick="doSearch()">查询</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div region="center" data-options="border:false">
           <@p.datagrid id="message" name="" title="系统日志" loadUrl="/detail/wordListLog" saveUrl="" defaultColumn=""
           isHasToolBar="false"  divToolbar=""  height="500" width="" onClickRowEdit="true" singleSelect="true" pageSize='20'
           pagination="true" rownumbers="true"
           columnsJsonList="[
				{field:'wordType',title:'标识'},
				{field:'sysGroup',title:'系统分组'},
				{field:'zhCn',title:'中文简体'},
				{field:'operation',title:'类型',formatter: function(value,row,index){
                       if(value=='ADD'){
                          return '新增';
                       }else if(value=='DELETE'){
                            return '删除';
                       }else if(value=='UPDATE'){
                            return '修改';
                       }else{
                           return '其他';
                       }
                }},
				{field:'exContent',title:'修改前内容'},
				{field:'content',title:'修改后内容'},
				{field:'createUser',title:'操作人'},
                {field:'createTime',title:'创建时间',width:170,formatter: function(value,row,index){
                        return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
                }}
			]"
           />
        </div>

    </div>
</div>

<script>

    $(document).ready(function () {
        $('#dlg').dialog('close');
    });


    function doSearch() {
        var $dg = $("#message");
        var reqParams = $('#wordListLogForm').form('getData');
        var queryMxURL = BasePath + "/detail/wordListLog";
        $dg.datagrid('options').queryParams = reqParams;
        $dg.datagrid('options').url = queryMxURL;
        $dg.datagrid('options').method = "post";
        $dg.datagrid('load');
    };

</script>

</body>
</html>