<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>角色管理</title>
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
                <form name="roleListForm" id="roleListForm" method="post">
                    <table class="form-tb">
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;角色名称:</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="roleName" id="roleName"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;是否启用：</td>
                            <td style="width:115px;">
                                <select style="width:135px; height: 23px " id="enable" name="enable">
                                    <option value=1 selected>启用</option>
                                    <option value=0>禁用</option>
                                </select>
                            </td>
                            <td>
                                <a id="searchButton" href="#" class="easyui-linkbutton" style="width:70px"
                                   data-options="iconCls:'icon-search'"
                                   onclick="doSearch()">查询</a>
                            </td>
                        </tr>
                        <tr>

                            <td>&nbsp;&nbsp;&nbsp;更新时间</td>
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
                            <td>
                                <a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                                   onclick="create_word()">添加角色</a>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div region="center" data-options="border:false">
           <@p.datagrid id="message" name="" title="角色列表" loadUrl="/detail/roleList" saveUrl="" defaultColumn=""
           isHasToolBar="false"  divToolbar=""  height="500" width="" onClickRowEdit="true" singleSelect="true" pageSize='20'
           pagination="true" rownumbers="true"
           columnsJsonList="[
				{field:'roleId',title:'角色id'},
				{field:'roleName',title:'角色名称'},
			    {field:'enable',title:'是否可用' ,width:60,formatter: function(value,row, index) {
			    	if (value == 0)  return '禁用'; else if (value == 1)   return '启用';
			       
			  }},			
			  {field:'createTime',title:'创建时间',width:170,formatter: function(value,row,index){
					return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
			  }},
			  {field:'updateTime',title:'更新时间',width:170,formatter: function(value,row,index){
					return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
			  }} ,
			  {field:'remark',title:'操作',width:80,align:'center'}
			]"
           />
        </div>

    </div>
</div>

<div id="dlg" class="easyui-dialog" title="编辑角色信息" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg-buttons' ">
    <form id="role_update" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td> &nbsp;&nbsp;&nbsp;角色id:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly style="width:135px" type="text" name="roleId_id"
                           id="roleId_id" data-options=""/>
                </td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;角色名称:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly style="width:135px" type="text"
                           name="roleName_id" id="roleName_id" data-options=""/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;是否启用：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="enable_id"
                            name="enable_id" required>
                        <option value=1>启用</option>
                        <option value=0>禁用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>


<div id="dlg_addRole" class="easyui-dialog" title="添加角色" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg_add-buttons' ">
    <form id="role" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td> &nbsp;&nbsp;&nbsp;角色名:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" required validType="valiRoleName[1]" validType:'equals1[0]'
                    invalidMessage="角色已经存在" style="width:135px" type="text" name="roleName_add" id="roleName_add"
                    data-options=""/>
                </td>
            </tr>


            <tr>
                <td>&nbsp;&nbsp;&nbsp;是否启用：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="enable_add"
                            name="enable_add">
                        <option value=1>启用</option>
                        <option value=0>禁用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>

</div>


<div id="dlg_add-buttons" style="display:none">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_addWord()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_addRole').dialog('close')">关闭</a>
</div>

<div id="dlg-buttons" style="display:none">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_wordinformation()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
</div>

<script>
    $.extend($.fn.validatebox.defaults.rules, {
        equalTo: {
            validator: function (value, param) {
                if (value.length < 3) {
                    return false;
                }
                return $(param[0]).val() == value;
            },
            message: '字段不匹配'
        },
        equalToAfter: {
            validator: function (value, param) {
                if (value.length < 3) {
                    return false;
                }
                if ($(param[0]).val().length == 0) {
                    return true;
                }
                return $(param[0]).val() == value;
            },
            message: '字段不匹配'
        }
    });


    $(document).ready(function () {
        $('#dlg').dialog('close');
        $('#dlg_addRole').dialog('close');
        $('#roleName_add').val("");
        $('#enable').val(1);
    });


    function create_word() {
        $('#roleName_add').val("");
        $('#enable').val(1);
        $('#dlg_addRole').dialog({
            title: '添加角色信息',
            width: 270,
            height: 250,
            closed: false,
            cache: false,
            modal: true
        });

        $.extend($.fn.validatebox.defaults.rules, {
            valiRoleName: {
                validator: function (value, param) {
                    var bi = false;
                    $.ajax({
                        type: 'POST',
                        data: {'roleName': $('#roleName_add').val()},
                        async: false,
                        url: BasePath + '/detail/validrolename?current_tm=' + new Date(),
                        success: function (data) {

                            if (data.result == 0) {
                                bi = true;
                            }
                            else {
                                bi = false;
                            }
                        },
                        error: function () {
                        },
                        dataType: 'json'
                    });

                    return bi;
                },
                message: '该角色名已经存在'
            }
        });

    }

    function save_addWord() {
        var b = $('#role').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'enable': $('#enable_add').val(),
                    'roleName': $('#roleName_add').val()
                },
                url: BasePath + '/detail/addRole?current_tm=' + new Date(),
                success: function (data) {
                    if (data.result == 1) {
                        // $('#dlg_addRole').dialog('close');
                        $('#roleName_add').val("");
                        $('#enable').val(1);
                        $('#dlg_addRole').dialog('close');
                        doSearch();
                    } else {
                        alert("保存失败");
                    }
                },
                error: function () {
                    alert("保存失败");
                },
                dataType: 'json'
            });
        }
    }


    function save_wordinformation() {
        var b = $('#role_update').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'enable': $('#enable_id').val(),
                    'roleName': $('#roleName_id').val(),
                    'roleId': $('#roleId_id').val()
                },
                url: BasePath + '/detail/modifyRole? current_tm=' + new Date(),
                success: function (data) {
                    if (data.result == 1) {
                        $('#dlg').dialog('close');
                        doSearch();
                        alert("修改成功");
                    } else {
                        alert("修改失败");
                    }
                },
                error: function () {
                    alert("修改失败");
                },
                dataType: 'json'
            });
        }
    }

    function operator(role_id) {
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/selectOneRoleDetail?roleId=' + role_id + '&current_tm=' + new Date(),
            success: function (data) {
                $('#roleId_id').val(data.roleId);
                $('#roleName_id').val(data.roleName);
                $('#enable_id').val(data.enable);
            },
            dataType: 'json'
        });
        $('#dlg').dialog({
            title: '角色信息',
            width: 270,
            height: 300,
            closed: false,
            cache: true,
            modal: true
        });
    }

    function doSearch() {
        var $dg = $("#message");
        var reqParams = $('#roleListForm').form('getData');
        var queryMxURL = BasePath + "/detail/roleList";
        $dg.datagrid('options').queryParams = reqParams;
        $dg.datagrid('options').url = queryMxURL;
        $dg.datagrid('options').method = "post";
        $dg.datagrid('load');
    };

</script>

</body>
</html>