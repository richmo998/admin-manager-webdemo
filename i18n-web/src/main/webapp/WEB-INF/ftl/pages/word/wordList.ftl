<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>词库管理</title>
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
                <form name="wordListForm" id="wordListForm" method="post">
                    <table class="form-tb">
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;中文简体:</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="zhCn" id="zhCn"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;中文繁体：</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="zhHk" id="zhHk"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;英文：</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="zhEn" id="zhEn"
                                       data-options=""/>
                            </td>

                        </tr>
                        <tr>
                            <td>&nbsp;&nbsp;&nbsp;标识：</td>
                            <td style="width:115px;">
                                <input class="easyui-validatebox ipt" style="width:135px" name="wordType" id="wordType"
                                       data-options=""/>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;系统分组：</td>
                            <td style="width:115px;">
                                <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="sysGroup"
                                        name="sysGroup" >
                                    <option value="" selected>全部</option>
                                    <option value="BASE">公共</option>
                                    <option value="POS">POS</option>
                                    <option value="WMS">WMS</option>
                                    <option value="CRM">CRM</option>
                                    <option value="FAS">FAS</option>
                                    <option value="GMS">GMS</option>
                                    <option value="MPS">MPS</option>
                                    <option value="MDM">MDM</option>
                                    <option value="PMS">PMS</option>
                                    <option value="SSO">SSO</option>
                                </select>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;修改时间</td>
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
                                   onclick="create_word()">添加词条</a>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div region="center" data-options="border:false">
           <@p.datagrid id="message" name="" title="词条列表" loadUrl="/detail/WordList" saveUrl="" defaultColumn=""
           isHasToolBar="false"  divToolbar=""  height="500" width="" onClickRowEdit="true" singleSelect="true" pageSize='20'
           pagination="true" rownumbers="true"
           columnsJsonList="[
				{field:'wordType',title:'标识'},
				{field:'sysGroup',title:'系统分组',formatter: function(value,row,index){
					if('BASE'==value){
					    return '公共';
					}else{
					    return value;
  					}
			  }},
				{field:'zhCn',title:'中文简体'},
				{field:'zhHk',title:'中文繁体'},
				{field:'zhEn',title:'英文'},
				{field:'createUser',title:'创建人'},
			  {field:'createTime',title:'创建时间',width:170,formatter: function(value,row,index){
					return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
			  }},
			  {field:'updateUser',title:'更新人'},
			  {field:'updateTime',title:'更新时间',width:170,formatter: function(value,row,index){
					return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
			  }} ,
			  {field:'remark',title:'操作',width:80,align:'center'}
			]"
           />
        </div>

    </div>
</div>

<div id="dlg_updateWord" class="easyui-dialog" title="编辑词条" style="width:300px;height:200px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg-buttons' ">
    <form id="word_update" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td>&nbsp;&nbsp;&nbsp;词条id：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt" readonly style="width:135px" name="id_update" id="id_update"
                           data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;标识：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" readonly style="width:135px" type="text" name="wordType_update" id="wordType_update"
                    data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;系统分组：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="sysGroup_update"
                            name="sysGroup_update" >
                        <option value="BASE">公共</option>
                        <option value="POS">POS</option>
                        <option value="WMS">WMS</option>
                        <option value="CRM">CRM</option>
                        <option value="FAS">FAS</option>
                        <option value="GMS">GMS</option>
                        <option value="MPS">MPS</option>
                        <option value="MDM">MDM</option>
                        <option value="PMS">PMS</option>
                        <option value="SSO">SSO</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;中文简体:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt" required style="width:135px" name="zhCn_update" id="zhCn_update"
                           data-options=""/>
                </td>
            </tr>
            <TR>
                <td>&nbsp;&nbsp;&nbsp;中文繁体：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt" style="width:135px" name="zhHk_update" id="zhHk_update"
                           data-options=""/>
                </td>
            </TR>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;英文：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt" style="width:135px" name="zhEn_update" id="zhEn_update"
                           data-options=""/>
                </td>

            </tr>
        </table>
    </form>
</div>


<div id="dlg_addWord" class="easyui-dialog" title="添加词条" style="width:350x;height:300px;padding:10px;"
     data-options=" iconCls: 'icon-save', buttons: '#dlg_add-buttons' ">
    <form id="addWord" class="easyui-form" method="post">
        <table class="form-tb">
            <tr>
                <td>&nbsp;&nbsp;&nbsp;标识：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox" required validType="validWordType[1]" validType:'equals1[0]'
                    invalidMessage="标识已经存在" style="width:135px" type="text" name="wordType_add" id="wordType_add"
                    data-options=""/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;系统分组：</td>
                <td style="width:115px;">
                    <select style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id="sysGroup_add"
                            name="sysGroup_add" required>
                        <option value="BASE" selected>公共</option>
                        <option value="POS">POS</option>
                        <option value="WMS">WMS</option>
                        <option value="CRM">CRM</option>
                        <option value="FAS">FAS</option>
                        <option value="GMS">GMS</option>
                        <option value="MPS">MPS</option>
                        <option value="MDM">MDM</option>
                        <option value="PMS">PMS</option>
                        <option value="SSO">SSO</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>&nbsp;&nbsp;&nbsp;中文简体:</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt"  required style="width:135px" name="zhCn_add" id="zhCn_add"
                           data-options=""/>
                </td>
            </tr>
            <TR>
                <td>&nbsp;&nbsp;&nbsp;中文繁体：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt" style="width:135px" name="zhHk_add" id="zhHk_add"
                           data-options=""/>
                </td>
            </TR>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;英文：</td>
                <td style="width:115px;">
                    <input class="easyui-validatebox ipt" style="width:135px" name="zhEn_add" id="zhEn_add"
                           data-options=""/>
                </td>

            </tr>
        </table>
    </form>

</div>


<div id="dlg_add-buttons" style="display:none" >
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_addWord()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_addWord').dialog('close')">关闭</a>
</div>

<div id="dlg-buttons" style="display:none">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save_wordinformation()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       onclick="javascript:$('#dlg_updateWord').dialog('close')">关闭</a>
</div>

<script>


    $(document).ready(function () {
        $('#dlg_updateWord').dialog('close');
        $('#dlg_addWord').dialog('close');
    });


    function create_word() {
        $('#wordType_add').val("");
        $('#dlg_addWord').dialog({
            title: '添加词条',
            width: 270,
            height: 280,
            closed: false,
            cache: false,
            modal: true
        });

        $.extend($.fn.validatebox.defaults.rules, {
            validWordType: {
                validator: function (value, param) {
                    var bi = false;
                    $.ajax({
                        type: 'POST',
                        data: {'wordType': $('#wordType_add').val()},
                        async: false,
                        url: BasePath + '/detail/validWordKey',
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
                message: '该词条名已经存在'
            }
        });

    }

    function save_addWord() {
        var b = $('#addWord').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'wordType': $('#wordType_add').val(),
                    'sysGroup': $('#sysGroup_add').val(),
                    'zhCn': $('#zhCn_add').val(),
                    'zhHk': $('#zhHk_add').val(),
                    'zhEn': $('#zhEn_add').val()
                },
                url: BasePath + '/detail/addWord',
                success: function (data) {
                    if (data.result == 1) {
                        $('#wordType_add').val("");
                        $('#sysGroup_add').val("");
                        $('#zhCn_add').val("");
                        $('#zhHk_add').val("");
                        $('#zhEn_add').val("");
                        $('#dlg_addWord').dialog('close');
                        doSearch();
                    } else {
                        $('#wordType_add').val("");
                        $('#sysGroup_add').val("");
                        $('#zhCn_add').val("");
                        $('#zhHk_add').val("");
                        $('#zhEn_add').val("");
                        alert("新增词条保存失败："+data.errorMsg);
                    }
                },
                error: function () {
                    $('#wordType_add').val("");
                    $('#sysGroup_add').val("");
                    $('#zhCn_add').val("");
                    $('#zhHk_add').val("");
                    $('#zhEn_add').val("");
                    alert("新增词条保存失败");
                },
                dataType: 'json'
            });
        }
    }


    function save_wordinformation() {
        var b = $('#word_update').form("enableValidation").form('validate');
        if (b) {
            $.ajax({
                type: 'POST',
                data: {
                    'id': $('#id_update').val(),
                    'wordType': $('#wordType_update').val(),
                    'sysGroup': $('#sysGroup_update').val(),
                    'zhCn': $('#zhCn_update').val(),
                    'zhHk': $('#zhHk_update').val(),
                    'zhEn': $('#zhEn_update').val()
                },
                url: BasePath + '/detail/modifyWords',
                success: function (data) {
                    if (data.result == 1) {
                        $('#dlg_updateWord').dialog('close');
                        doSearch();
                        alert("修改成功");
                    } else {
                        alert("修改失败:"+data.errorMsg);
                    }
                },
                error: function () {
                    alert("修改失败");
                },
                dataType: 'json'
            });
        }
    }

    function operator(word_id) {
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/detail/selectOneWordDetail?id=' + word_id ,
            success: function (data) {
                if(data){
                    $('#id_update').val(data.id);
                    $('#wordType_update').val(data.wordType);
                    $('#sysGroup_update').val(data.sysGroup);
                    $('#zhCn_update').val(data.zhCn);
                    $('#zhHk_update').val(data.zhHk);
                    $('#zhEn_update').val(data.zhEn);
                }

            },
            dataType: 'json'
        });
        $('#dlg_updateWord').dialog({
            title: '词条信息',
            width: 270,
            height: 280,
            closed: false,
            cache: true,
            modal: true
        });
    }

    function doSearch() {
        var $dg = $("#message");
        var reqParams = $('#wordListForm').form('getData');
        var queryMxURL = BasePath + "/detail/WordList";
        $dg.datagrid('options').queryParams = reqParams;
        $dg.datagrid('options').url = queryMxURL;
        $dg.datagrid('options').method = "post";
        $dg.datagrid('load');
    };

</script>

</body>
</html>