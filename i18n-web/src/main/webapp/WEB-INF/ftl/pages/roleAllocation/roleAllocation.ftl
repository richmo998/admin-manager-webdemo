<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>角色分配管理</title>
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
                <form name="roleAllocationListForm" id="roleAllocationListForm" method="post">
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
                        </tr>
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;菜单路径:</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="uri" id="uri"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;菜单名称：</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="menuName" id="menuName"
                                       data-options=""/>
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
                                   onclick="create_allocationrole()">添加角色权限</a>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div region="center" data-options="border:false">
           <@p.datagrid id="message" name="" title="角色列表" loadUrl="/detail/roleAllocationList" saveUrl="" defaultColumn=""
           isHasToolBar="false"  divToolbar=""  height="500" width="" onClickRowEdit="true" singleSelect="true" pageSize='20'
           pagination="true" rownumbers="true"
           columnsJsonList="[
				{field:'roleId',title:'角色id'},
				{field:'roleName',title:'角色名称'},
				{field:'uri',title:'菜单路径'},
				{field:'menuName',title:'菜单名称'},
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

<div id="dlg" class="easyui-dialog" title="编辑角色分配信息" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg-buttons' ">
    <form id="allocationrole_update" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td> &nbsp;&nbsp;&nbsp;id:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly style="width:135px" type="text" name="id_id"
                           id="id_id" data-options=""/>
                </td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;角色id:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly  style="width:135px" type="text"
                           name="roleId_id" id="roleId_id" data-options=""/>
                </td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;角色名称:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox" readonly style="width:135px" type="text"
                           name="roleName_id" id="roleName_id" data-options=""/>
                </td>
            </tr>
            <tr style="display:none">
                <td> &nbsp;&nbsp;&nbsp;菜单名称:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox"  style="width:135px" type="text"
                           name="menuName_id" id="menuName_id" data-options=""/>
                        <#--<select  id="menuName_id" name="menuName_id" style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" onchange="getMenuName(1)">-->
                        <#--</select>-->
                </td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;菜单:</td>
                <td style="width:115px;">
                    <#--<input class="easyui-validatebox textbox" type="text" style="width:135px"-->
                           <#--name="uri_id" id="uri_id" data-options=""/>-->
                        <select  id="uri_id" name="uri_id" style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" onchange="getMenuName(1)">
                        </select>
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


<div id="dlg_addAllocationRole" class="easyui-dialog" title="添加角色分配" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg_add-buttons' ">
    <form id="allocationRole" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td> &nbsp;&nbsp;&nbsp;角色:</td>
                <td style="width:115px;" >
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="roleId_add" name="roleId_add" onchange="getRoleName()">
                    </select>
                </td>
            </tr>
            <tr style="display:none" >
                <td> &nbsp;&nbsp;&nbsp;角色名:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox textbox"  readonly style="width:135px" type="text"
                           name="roleName_add" id="roleName_add" data-options=""/>
                </td>
            </tr>
            <tr style="display:none" >
                <td> &nbsp;&nbsp;&nbsp;菜单名称:</td>
                <td style="width:115px;">
                <input class="easyui-validatebox textbox"  style="width:135px" type="text"
                name="menuName_add" id="menuName_add" data-options=""/>
                    <#--<select id="menuName_add" name="menuName_add" style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;"  onchange="getMenuName(0)">-->
                    <#--</select>-->
                </td>
            </tr>
            <tr>
                <td> &nbsp;&nbsp;&nbsp;菜单:</td>
                <td style="width:115px;">
                    <#--<input class="easyui-validatebox textbox" style="width:135px"  type="text"-->
                           <#--name="uri_add" id="uri_add" data-options=""/>-->
                        <select  id="uri_add" name="uri_add" style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" onchange="getMenuName(0)">
                        </select>
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
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_addallocationrole()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_addAllocationRole').dialog('close')">关闭</a>
</div>

<div id="dlg-buttons" style="display:none">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_allocationroleinformation()">修改</a>
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
        $('#dlg_addAllocationRole').dialog('close');
        $('#roleName_add').val("");
        $('#roleId_add').val("");

        $('#roleName_id').val("");
        $('#roleId_id').val("");
        $('#enable').val(1);

        //加载角色
        getRoleInfo();
        //加载菜单
        getMenuList();
    });


    function create_allocationrole() {
        // $('#roleName_add').val("");
        // $('#roleId_add').val("");
        // $('#enable').val(1);
        $('#dlg_addAllocationRole').dialog({
            title: '添加角色分配信息',
            width: 270,
            height: 250,
            closed: false,
            cache: false,
            modal: true
        });

        // $.extend($.fn.validatebox.defaults.rules, {
        //     valiRoleName: {
        //         validator: function (value, param) {
        //             var bi = false;
        //             $.ajax({
        //                 type: 'POST',
        //                 data: {'roleName': $('#roleName_add').val()},
        //                 async: false,
        //                 url: BasePath + '/detail/validrolename?current_tm=' + new Date(),
        //                 success: function (data) {
        //
        //                     if (data.result == 0) {
        //                         bi = true;
        //                     }
        //                     else {
        //                         bi = false;
        //                     }
        //                 },
        //                 error: function () {
        //                 },
        //                 dataType: 'json'
        //             });
        //
        //             return bi;
        //         },
        //         message: '该角色名已经存在'
        //     }
        // });

    }

    function save_addallocationrole() {
        var b = $('#role').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'enable': $('#enable_add').val(),
                    'uri': $('#uri_add').val(),
                    'menuName': $('#menuName_add').val(),
                    'roleId': $('#roleId_add').val(),
                    'roleName': $('#roleName_add').val()
                },
                url: BasePath + '/detail/addRoleAllocation?current_tm=' + new Date(),
                success: function (data) {
                    if (data.result == 1) {
                        // $('#dlg_addAllocationRole').dialog('close');
                        $('#roleName_add').val("");
                        $('#roleId_add').val("");
                        $('#uri_add').val("");
                        $('#menuName_add').val("");
                        $('#enable_add').val(1);
                        $('#dlg_addAllocationRole').dialog('close');
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


    function save_allocationroleinformation() {
        var b = $('#allocationrole_update').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'enable': $('#enable_id').val(),
                    'roleName': $('#roleName_id').val(),
                    'roleId': $('#roleId_id').val(),
                    'uri': $('#uri_id').val(),
                    'menuName': $('#menuName_id').val(),
                    'id': $('#id_id').val()
                },
                url: BasePath + '/detail/modifyRoleAllocation? current_tm=' + new Date(),
                success: function (data) {
                    if (data.result == 1) {
                        $('#id_id').val('');
                        $('#roleId_id').val('');
                        $('#roleName_id').val('');
                        $('#uri_id').val('');
                        $('#menuName_id').val('');
                        $('#enable_id').val('');
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

    function operator(id) {
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/selectOneRoleAllocationDetail?id=' + id + '&current_tm=' + new Date(),
            success: function (data) {
                $('#id_id').val(data.id);
                $('#roleId_id').val(data.roleId);
                $('#roleName_id').val(data.roleName);
                $('#uri_id').val(data.uri);
                $('#menuName_id').val(data.menuName);
                $('#enable_id').val(data.enable);
            },
            dataType: 'json'
        });
        $('#dlg').dialog({
            title: '角色分配信息',
            width: 270,
            height: 300,
            closed: false,
            cache: true,
            modal: true
        });
    }

    function doSearch() {
        var $dg = $("#message");
        var reqParams = $('#roleAllocationListForm').form('getData');
        var queryMxURL = BasePath + "/detail/roleAllocationList";
        $dg.datagrid('options').queryParams = reqParams;
        $dg.datagrid('options').url = queryMxURL;
        $dg.datagrid('options').method = "post";
        $dg.datagrid('load');
    };
    //获取角色信息，形成下拉数据
    function getRoleInfo(){
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/allRoleList?',
            success: function (data) {

                //所有角色信息
                if(data.roleList !='' && data.roleList !=null){
                    for (var i=0;i<data.roleList.length;i++){
                        $('#roleId_add').append("<option value="+data.roleList[i].roleId+">"+data.roleList[i].roleName+"</option>")
                        if(i==0){
                            $('#roleName_add').val(data.roleList[0].roleName);
                        }
                        // $('#roleId_id').append("<option value="+data.roleList[i].roleId+">"+data.roleList[i].roleName+"</option>")
                    }
                }else{
                    //没有任何角色则默认一个管理员
                    $('#roleId_add').append("<option value=1>admin</option>");
                    $('#roleName_add').val("admin");
                    // $('#roleId_id').append("<option value=1>admin</option>)
                }
            },
            dataType: 'json'
        });
    }

    /**
     * 下拉切换角色
     * 时候获取用户名
     */
    function getRoleName(){

        var v = $('#roleId_add').val();
        var text =$("#roleId_add").find("option:selected").text();
        $('#roleName_add').val(text);
    }

    /**
     * 获取菜单list
     * 以管理员为基准
     */
    function getMenuList(){
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/allMenuList',
            success: function (data) {

                //所有角色信息
                if(data.menuList !='' && data.menuList !=null){
                    for (var i=0;i<data.menuList.length;i++){
                        $('#uri_add').append("<option value="+data.menuList[i].uri+">"+data.menuList[i].menuName+"</option>")
                        $('#uri_id').append("<option value="+data.menuList[i].uri+">"+data.menuList[i].menuName+"</option>")
                        if(i==0){
                            $('#menuName_add').val(data.menuList[0].menuName);
                            $('#menuName_id').val(data.menuList[0].menuName);
                        }
                        // $('#roleId_id').append("<option value="+data.roleList[i].roleId+">"+data.roleList[i].roleName+"</option>")
                    }
                }
            },
            dataType: 'json'
        });
    }
    /**
     * 下拉 切换菜单
     */
    function getMenuName(type){

        if(1==type){
            // var v = $('#menuName_id').val();
            var text =$("#uri_id").find("option:selected").text();
            // $('#uri_id').val(v);
            $('#menuName_id').val(text);
        }else{
            // var v = $('#menuName_add').val();
            var text =$("#uri_add").find("option:selected").text();
            // $('#uri_add').val(v);
            $('#menuName_add').val(text);
        }

    }
</script>

</body>
</html>