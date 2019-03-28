<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <title>业务类型</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <#include  "/WEB-INF/ftl/common/header.ftl" >
    <script type="text/javascript" src="<@s.url"/resources/js/date.js"/>"></script>
    <script type="text/javascript" src="<@s.url"/resources/js/biz_type/biz_type.js"/>"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
<div>
        <table class="form-tb">
            <tr>
                <td>业务编号：</td>
                <td><input id="s_biz_no" class="easyui-validatebox"></td>
                <td>业务名称：</td>
                <td><input id="s_biz_name" class="easyui-validatebox"></td>
                <td>接收人号码：</td>
                <td><input id="s_msg_receiver" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>是否启用：</td>
                <td>
                    <#--<input id="enable" class="easyui-validatebox">-->
                        <select  style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id ="enable" name="enable">
                            <option value=1 >启用</option>
                            <option value=0 >禁用</option>
                        </select>
                </td>
                <td>短信类型：</td>
                <td>
                    <#--<input id="sms_type" class="easyui-validatebox">-->
                        <select  style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id ="smsType" name="smsType">
                            <option value=0,1 selected=selected>ALL</option>
                            <option value=0 >普通短信</option>
                            <option value=1 >营销短信</option>
                        </select>
                </td>
                <td>签名类型：</td>
                <td><input id="sign_type" class="easyui-validatebox"></td>
            </tr>
        </table>
</div>

<table id="list"></table>
<div id="toolbar">
    <a href="javascript:void(0);" onclick="biz_type.search();" class="easyui-linkbutton"
       data-options="iconCls:'icon-search'">查询</a>
    <a href="javascript:void(0);" onclick="biz_type.addDialog();" class="easyui-linkbutton"
       data-options="iconCls:'icon-add'">添加</a>
    <a href="javascript:void(0);" onclick="biz_type.editDialog();" class="easyui-linkbutton"
       data-options="iconCls:'icon-edit'">修改</a>
    <a href="javascript:void(0);" onclick="biz_type.del();" class="easyui-linkbutton" data-options="iconCls:'icon-del'">删除</a>

</div>

<div id="dialog">
    <form id="form" style="margin:5px;">
        <table class="form-tb">
            <input type="hidden" name="id" value="0"/>
            <tr>
                <td>业务编号：</td>
                <td><input style="width:256px;" id ="bizNo" name="bizNo" class="easyui-validatebox"
                           data-options="required:true,missingMessage:'不能为空'"/></td>
            </tr>
            <tr>
                <td>业务名称：</td>
                <td><input style="width:256px;" id="bizName" name="bizName" class="easyui-validatebox"
                           data-options="required:true,missingMessage:'不能为空'"/></td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input style="width:256px;" id="userName" name="userName" class="easyui-validatebox"
                           data-options="required:true,missingMessage:'不能为空'"/></td>
            </tr>
            <tr>
                <td>短信状态报告接收人：</td>
                <td><input style="width:256px;"id="msgReceiver" name="msgReceiver" class="easyui-validatebox"/></td>
            </tr>

            <tr>
                <td>是否启用：</td>
                <td>
                    <#--<input style="width:256px;" name="enable" class="easyui-validatebox"/>-->
                        <select  style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id ="enable" name="enable">
                            <option value=1 selected=selected>启用</option>
                            <option value=0 >禁用</option>
                        </select>
                </td>
            </tr>
            <tr>
                <td>可发送短信类型：</td>
                <td>
                    <#--<input style="width:256px;" name="smsType" class="easyui-validatebox"/>-->
                        <select  style="width:135px; height: 23px ;marginLeft:10;marginTop:10 ;" id ="smsType" name="smsType">
                            <option value=0,1 selected=selected>ALL</option>
                            <option value=0 >普通短信</option>
                            <option value=1 >营销短信</option>
                        </select>
                </td>
            </tr>
            <tr>
                <td>可使用短信签名：</td>
                <#--<td><input style="width:256px;" name="signType" class="easyui-validatebox"/></td>-->
                <td >
                    <#--<select id="signType" name="signType"  class="easyui-combobox" data-options="multiple:true,multiline:true" style="width:256px;height:50px">-->
                    <#--</select>-->
                        <input class="easyui-combobox"
                               id="signType"
                               name="signType"
                               style="width:256px;"
                               data-options="
                            url:'/qSmsSign/getComboboxData',
                            method:'post',
                            valueField:'CODE',
                            textField:'NAME',
                            multiple:true,
                            panelHeight:'auto'
                    ">
                </td>
            </tr>
            <tr>
                <td>描述信息：</td>
                <td><textarea style="width:256px;height:66px;"id="description" name="description" class="easyui-validatebox"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>