var biz_type = {};

//查询
biz_type.search = function() {
    var v_bizNo = $('#s_biz_no').val();
    var v_bizName = $('#s_biz_name').val();
    var v_msgReceiver = $('#s_msg_receiver').val();
    var v_enable = $('#enable').val();
    var v_smsType = $('#sms_type').val();
    var v_signType = $('#sign_type').val();

	$('#list').datagrid('load',{
		bizNo: v_bizNo,
        bizName: v_bizName,
        msgReceiver: v_msgReceiver,
        enable: v_enable,
        smsType: v_smsType,
        signType: v_signType
});
};

//删除
biz_type.del = function() {
	var row = $("#list").datagrid('getSelections');
	if (row == null || row[0] == null) return;
	
	$.messager.confirm('确定','删除操作不可恢复，是否继续？',function(r){
	    if (r){
	    	$.ajax({
	    		url: BasePath+'/biz/type/del',
	    		type: 'post',
	    		data: {
	    			id: row[0].id
	    		},
	    		success: function(message) {
	    			//$.messager.alert('结果',message);
	    			$("#list").datagrid('reload');
	    		}
	    	});
	    }
	});
}

//添加对话框
biz_type.addDialog = function() {
	$("#form").form('reset');
	$("input[name=bizNo]").removeAttr("readonly");

	$("#dialog").dialog({
		title: '添加',
		iconCls: 'icon-add',
		width: 480,
		height: 256,
		buttons: [{
			text: '保存',
			handler: function(){
				if ($("#form").form('validate')) {

					var data = $("#form").serialize();
					biz_type.add(data);
				}
			}
		},{
			text: '取消',
			handler: function(){
				$("#dialog").dialog('close');
			}
		}]
	});
}

//添加
biz_type.add = function(data) {
	$.ajax({
		url: BasePath + '/biz/type/add',
		type: 'post',
		data: data,
		success: function(message) {
			if (message == 'success') {
				$("#list").datagrid('reload');
				$("#dialog").dialog('close');
			} else {
				$.messager.alert('失败',message);
			}
		}
	});
};

//修改对话框
biz_type.editDialog = function() {
	var row = $("#list").datagrid('getSelections');
	if (row == null || row[0] == null) return;
	$("#form").form('reset');
	$("input[name=bizNo]").attr("readonly","readonly");
    $("#form").form('load',row[0]);
    // $("#bizNo").val(row[0].bizNo);
    // $("#bizName").val(row[0].bizName);
    // $("#userName").val(row[0].userName);
    // $("#enable").val(row[0].enable);
    // $("#smsType").val(row[0].smsType);
    // $("#description").val(row[0].description);
    var v_sign = row[0].signType;
    // biz_type.initCombobox('signType');
    // var val = $(this).combobox("loadData");
    // for (var a = 0; a < val.length; a++) {
    //     if (v_sign.contains(val[a].NAME) ) {
    //         //3.用后台传回来的实际值与下拉框中的值进行比较，若是二者相等那么默认选中该项。
    //         $(this).combobox("select", val[a].NAME);
    //     }
    // }

    $('#signType').combobox('setValue', v_sign);

	$("#dialog").dialog({
		title: '修改',
		iconCls: 'icon-edit',
		width: 480,
		height: 256,
		buttons: [{
			text: '确定',
			handler: function(){
				if ($("#form").form('validate')) {
					var data = $("#form").serialize();
					biz_type.edit(data);
				}
			}
		},{
			text: '取消',
			handler: function(){
                $("#form").form('reset');
				$("#dialog").dialog('close');
			}
		}]
	});
	

}

//修改
biz_type.edit = function(data) {
	$.ajax({
		url: BasePath + '/biz/type/edit',
		type: 'post',
		data: data,
		success: function(message) {
			if (message == 'success') {
				$("#list").datagrid('reload');
				$("#dialog").dialog('close');

				$("#signType").val('');
			} else {
				$.messager.alert('失败',message);
			}
		}
	});
};

//初始化列表
biz_type.initList = function() {
	$('#list').datagrid({
	    url: BasePath + '/biz/type/list',
	    method: 'get',
	    pagination: true,
	    fitColumns: false,
	    nowrap: true,
	    fit: true,
	    rownumbers: true,
	    selectOnCheck: true,
//	    checkOnSelect: true,
	    toolbar: '#toolbar',
	    columns:[[
	        {field:'ck',checkbox:true},
	        {field:'id',title:'ID'},
	        {field:'bizNo',title:'业务编号',width:60},
	        {field:'bizName',title:'业务名称',width:120},
	        {field:'userName',title:'用户名',width:80},
	        {field:'msgReceiver',title:'短信状态报告接收人',width:120},
	        {field:'description',title:'描述信息',width:240},
            {field:'enable',title:'启用状态',width:60,
                formatter: function(value,row,index){
                    if (value == '0') return "禁用";
                    if (value == '1') return "启用";
                    if (value == null || value == '') return null;
                }},
            {field:'smsType',title:'短信类型',width:60,
                formatter: function(value,row,index){
                    if (value == '0') return "普通短信";
                    if (value == '1') return "营销短信";
                    return 'ALL';
                }},
            {field:'signType',title:'签名类型',width:120},
	        {field:'createTime',title:'创建时间',width:120,
	        	formatter: function(value,row,index){
	        		if (value == null || value == '') return null;
	        		return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
			    }},
	        {field:'updateTime',title:'更新时间',width:120,
			    formatter: function(value,row,index){
			    	if (value == null || value == '') return null;
			    	return getFormatDateByLong(value, 'yyyy-MM-dd hh:mm:ss');
			    }}
	    ]]
	});
};

//暂时不适用，后续研究
biz_type.initCombobox = function(id){

    // var value = "";
    // //加载下拉框复选框
    // $('#'+id).combobox({
    //     url: BasePath + '/qSmsSign/getComboboxData',
    //     method:'post',
    //     panelHeight:200,//设置为固定高度，combobox出现竖直滚动条
    //     valueField:'CODE',
    //     textField:'NAME',
    //     multiple:true,
    //     formatter: function (row) { //formatter方法就是实现了在每个下拉选项前面增加checkbox框的方法
    //         var opts = $(this).combobox('options');
    //         return '<input type="checkbox" class="combobox-checkbox">' + row[opts.textField]
    //     },
    //     onLoadSuccess: function () {  //下拉框数据加载成功调用
    //         var opts = $(this).combobox('options');
    //         var target = this;
    //         var values = $(target).combobox('getValues');//获取选中的值的values
    //         $.map(values, function (value) {
    //             var el = opts.finder.getEl(target, value);
    //             el.find('input.combobox-checkbox')._propAttr('checked', true);
    //         })
    //     },
    //     onSelect: function (row) { //选中一个选项时调用
    //         var opts = $(this).combobox('options');
    //         //获取选中的值的values
    //         // $("#"+id).val($(this).combobox('getValues'));
    //
    //         //设置选中值所对应的复选框为选中状态
    //         var el = opts.finder.getEl(this, row[opts.valueField]);
    //         el.find('input.combobox-checkbox')._propAttr('checked', true);
    //     },
    //     onUnselect: function (row) {//不选中一个选项时调用
    //         var opts = $(this).combobox('options');
    //         //获取选中的值的values
    //         // $("#"+id).val($(this).combobox('getValues'));
    //         var el = opts.finder.getEl(this, row[opts.valueField]);
    //         el.find('input.combobox-checkbox')._propAttr('checked', false);
    //     }
    // });


    // $.ajax({
    //     url:BasePath + '/qSmsSign/getComboboxData',
    //     type: 'post',
    //     success: function(data) {
    //     	debugger
    //         if(data !='' && data !=null){
    //     		var json = JSON.parse(data);
    //             for (var i=0;i<json.length;i++){
    //                 $('#signType').append("<option value="+json[i].CODE+">"+json[i].NAME+"</option>")
    //                 if(i==0){
    //                     $('#signType').val(json[0].NAME);
    //                 }
    //                 // $('#roleId_id').append("<option value="+data.roleList[i].roleId+">"+data.roleList[i].roleName+"</option>")
    //             }
    //         }else{
    //             //没有任何角色则默认一个管理员
    //             $('#signType').append("<option value='新百丽'>新百丽</option>");
    //         }
    //     }
    // });
}

$(document).ready(function(){
	biz_type.initList();
    // biz_type.initCombobox('signType');
});