<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>邮件短信系统</title>
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
                <form name="emailListForm" id="emailListForm" method="post">
                    <table class="form-tb">
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;用户名:</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="userName" id="userName"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;用户状态：</td>
                            <td style="width:115px;">
                                <select style="width:135px; height: 23px " id="status" name="status">
                                    <option selected value=""></option>
                                    <option value=1>启用</option>
                                    <option value=0>禁用</option>
                                </select>
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


                        </tr>
                        <tr>
                            <td>
                                <a id="searchButton" href="#" class="easyui-linkbutton" style="width:70px"
                                   data-options="iconCls:'icon-search'"
                                   onclick="doSearch()">查询</a>
                            </td>
                            <td>
                                <a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                                   onclick="create_user()">添加用户</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div region="center" data-options="border:false">
           <@p.datagrid id="message" name="" title="用户管理列表" loadUrl="/detail/userDetail" saveUrl="" defaultColumn=""
           isHasToolBar="false"  divToolbar=""  height="500" width="" onClickRowEdit="true" singleSelect="true" pageSize='20'
           pagination="true" rownumbers="true"
           columnsJsonList="[
				{field:'userId',title:'用户编号'},				
				{field:'userName',title:'用户名'},
				{field:'userType',title:'用户类型' ,width:60,formatter: function(value,row, index) {
			    	if (value == 0)  return '后台用户'; else if (value == 1)   return '接口用户';
			  	}},
			    {field:'status',title:'用户状态' ,width:60,formatter: function(value,row, index) {
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
    <#-- 暂时移除 {field:'roleName',title:'角色' },-->
    </div>
</div>

<div id="dlg" class="easyui-dialog" title="用户信息" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg-buttons' ">
    <form id="ff_update" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td> &nbsp;&nbsp;&nbsp;用户编号:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly style="width:135px" type="text" name="userID_id"
                           id="userID_id" data-options=""/>
                </td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;用户名:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly style="width:135px" type="text"
                           name="userName_id" id="userName_id" data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;用户密码:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" invalidMessage="注意密码长度3位以上以及密码是否一致"
                           validType="equalToAfter['#userPwd_id_comfirm']" style="width:135px" name="userPwd_id"
                           type="password" id="userPwd_id" data-options="" required/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;确认密码:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" validType="equalTo['#userPwd_id']" invalidMessage="两次输入密码不一致"
                           required style="width:135px" type="password" name="userPwd_id_comfirm"
                           id="userPwd_id_comfirm" data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;用户类型：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="userType_id"
                            name="userType_id" required>
                        <option value=0>后台用户</option>
                        <option value=1>接口用户</option>
                    </select>
                </td>
            </tr>
        <#--<tr>-->
        <#--<td>&nbsp;&nbsp;&nbsp;用户角色：</td>-->
        <#--<td style="width:115px;">-->
        <#--<select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="roleId_id" name="roleId_id">-->
        <#--</select>-->
        <#--</td>-->
        <#--</tr>-->
            <tr>
                <td>&nbsp;&nbsp;&nbsp;用户状态：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="status_id"
                            name="status_id" required>
                        <option value=1>启用</option>
                        <option value=0>禁用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;创建时间</td>
                <td style="width:115px;">
                    <input id="createTime_id" disabled="disabled" name="createTime_id" type="text"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:135"/>

                </td>
            </tr>
        </table>
    </form>
</div>


<div id="dlg_addUser" class="easyui-dialog" title="添加用户" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg_add-buttons' ">
    <form id="ff" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td> &nbsp;&nbsp;&nbsp;用户名:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" required validType="valiUserName[1]" validType:'equals1[0]'
                    invalidMessage="用户名已经存在" style="width:135px" type="text" name="userName_add" id="userName_add"
                    data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;用户密码:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" invalidMessage="注意密码长度3位以上以及密码是否一致"
                           validType="equalToAfter['#userPwd_add_comfirm']" required style="width:135px" type="password"
                           name="userPwd_add" id="userPwd_add" data-options="required:true"/>
                </td>
            </tr>
            <tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;确认密码:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" validType="equalTo['#userPwd_add']" invalidMessage="两次输入密码不一致"
                           required style="width:135px" type="password" name="userPwd_add_comfirm"
                           id="userPwd_add_comfirm" data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;用户类型：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="userType_add"
                            name="userType_add">
                        <option value=0>后台用户</option>
                        <option value=1>接口用户</option>
                    </select>
                </td>
            </tr>
        <#--<tr>-->
        <#--<td>&nbsp;&nbsp;&nbsp;用户角色：</td>-->
        <#--<td style="width:115px;">-->
        <#--<select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="roleId_add" name="roleId_add">-->
        <#--</select>-->
        <#--</td>-->
        <#--</tr>-->
            <tr>
                <td>&nbsp;&nbsp;&nbsp;用户状态：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="status_add"
                            name="status_add">
                        <option value=1>启用</option>
                        <option value=0>禁用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>

</div>


<div id="dlg_add-buttons" style="display:none">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_adduser()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_addUser').dialog('close')">关闭</a>
</div>

<div id="dlg-buttons" style="display:none">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_userinformation()">修改</a>
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
        $('#dlg_addUser').dialog('close');
        $('#userName_add').val("");
        $('#roleId_add').val("");
        $('#userPwd_add').val("");
        $('#userPwd_add_comfirm').val("");
        $('#status').val(1);
        //初始化角色数据
        getRoleInfo();
    });


    function create_user() {
        $('#userName_add').val("");
        $('#userPwd_add').val("");
        $('#roleId_add').val("");
        $('#userPwd_add_comfirm').val("");
        $('#status').val(1);
        var pwd = $('#userPwd_add').val();
        var pwd_comfirm = $('#userPwd_add_comfirm').val();
        $('#dlg_addUser').dialog({
            title: '添加用户信息',
            width: 270,
            height: 250,
            closed: false,
            cache: false,
            modal: true
        });

        $.extend($.fn.validatebox.defaults.rules, {
            valiUserName: {
                validator: function (value, param) {
                    var bi = false;
                    $.ajax({
                        type: 'POST',
                        data: {'userName': $('#userName_add').val()},
                        async: false,
                        url: BasePath + '/detail/validusername?current_tm=' + new Date(),
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
                message: '该用户名已经存在'
            }
        });

    }

    function save_adduser() {
        var b = $('#ff').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'status': $('#status_add').val(),
                    'userType': $('#userType_add').val(),
                    'userPwd': $('#userPwd_add').val(),
                    'userName': $('#userName_add').val(),
                    'roleId': $('#roleId_add').val(),
                    'userPwdCom': $('#userPwd_add_comfirm').val()
                },
                url: BasePath + '/detail/saveuserinformation?current_tm=' + new Date(),
                success: function (data) {
                    if (data.result == 1) {
                        $('#dlg_addUser').dialog('close');
                        $('#userName_add').val("");
                        $('#roleId_add').val("");
                        $('#userPwd_add').val("");
                        $('#userPwd_add_comfirm').val("");
                        $('#status').val(1);
                        $('#userType_add').val(0);
                        $('#dlg_addUser').dialog('close');
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


    function save_userinformation() {
        var b = $('#ff_update').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'status': $('#status_id').val(),
                    'userType': $('#userType_id').val(),
                    'userPwd': $('#userPwd_id').val(),
                    'userPwdComfirm': $('#userPwd_id_comfirm').val(),
                    'roleId': $('#roleId_id').val(),
                    'userId': $('#userID_id').val()
                },
                url: BasePath + '/detail/modifyuserinformation? current_tm=' + new Date(),
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

    function operator(user_id) {
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/to_user_operator?user_id=' + user_id + 'current_tm=' + new Date(),
            success: function (data) {
                $('#userID_id').val(data.userId);
                $('#roleId_id').val(data.roleId);
                $('#userName_id').val(data.userName);
                $('#userPwd_id').val(data.userPwd);
                $('#userPwd_id_comfirm').val(data.userPwd);
                $('#status_id').val(data.status);
                $('#userType_id').val(data.userType);
                $('#createTime_id').val(new Date(parseInt(data.createTime)).format('yyyy-MM-dd hh:mm:ss'));
            },
            dataType: 'json'
        });
        $('#dlg').dialog({
            title: '用户信息',
            width: 270,
            height: 300,
            closed: false,
            cache: true,
            modal: true
        });
    }

    function doSearch() {
        var $dg = $("#message");
        var reqParams = $('#emailListForm').form('getData');
        var queryMxURL = BasePath + "/detail/userDetail";
        $dg.datagrid('options').queryParams = reqParams;
        $dg.datagrid('options').url = queryMxURL;
        $dg.datagrid('options').method = "post";
        $dg.datagrid('load');
    };

    //获取角色信息，形成下拉数据
    function getRoleInfo() {
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/allRoleList?',
            success: function (data) {

                //所有角色信息
                if (data.roleList != '' && data.roleList != null) {
                    for (var i = 0; i < data.roleList.length; i++) {
                        $('#roleId_add').append("<option value=" + data.roleList[i].roleId + ">" + data.roleList[i].roleName + "</option>")
                        $('#roleId_id').append("<option value=" + data.roleList[i].roleId + ">" + data.roleList[i].roleName + "</option>")
                    }
                } else {
                    //没有任何角色则默认一个管理员
                    $('#roleId_add').append("<option value=1>admin</option>")
                    $('#roleId_id').append("<option value=1>admin</option>")
                }
            },
            dataType: 'json'
        });
    }
</script>

</body>
</html>