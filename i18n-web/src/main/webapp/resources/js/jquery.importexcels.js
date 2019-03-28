/**
 * Excel 导入
 */
(function($) {
	//导入框
	var $imoprt = $('#import-body').find('#import-dialog');
	
	//提示面板
	var $imoprtInfo = $('#import-info');
	
	//初始化dialog
	$imoprt.dialog({closed:true});
	
	var defaults = {
		title : '导入',
		downloadText :'下载调度模板',
		submitUrl : '',
		templateName : '',
		importParams : {},
		success:function(){},
		error:function(){}
	};
	
	var importExcel = {
	    //打开导入框
		open : function(o){
			
			//初始化导入框
			$imoprt.dialog({
				width:500,
				height:175,
				title:o.title,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal:true,
				buttons:[{
					text:'上传',
					iconCls:'icon-upload',
					handler:function(self){
						$.easyui.loading({
							topMost: false,
							msg: "努力上传中..."
						});
						//验证文件是否符合要求
						var uploadFile = $('#uploadFile').val();
						var fileListType="xlsx";
						if(uploadFile==""){
						 	 $('#info').html("请选择{0}文件".format(fileListType));
						 	$.importExcel.expand(500,360);
						 	$.easyui.loaded(); 
							 return false;
						}
						var destStr = uploadFile.substring(uploadFile.lastIndexOf(".")+1,uploadFile.length);
						if(fileListType != destStr){
							 $('#info').html("只允许上传{0}文件".format(fileListType));
							 $.importExcel.expand(500,360);
							 $.easyui.loaded(); 
							 return false;
						}
						
						$.ajaxFileUpload({
							//用于文件上传的服务器端请求地址
					        url: o.submitUrl+'?importParams='+JSON.stringify(o.importParams),
					        //是否需要安全协议，一般设置为false
					        secureuri: false,
					        //文件上传域的ID
					        fileElementId: 'uploadFile', 
					        //参数
//					        data:JSON.stringify(o.importParams),
					        //返回值类型 一般设置为JSON
					        dataType: 'JSON',
					        //服务器成功响应处理函数
					        success: function (data, status){
//					            var reg = /<pre.+?>(.+)<\/pre>/g;  
//					            var result = data.match(reg);  
					            data = data.replace("<pre>","");
					            data = data.replace("</pre>","");
					            var result = jQuery.parseJSON(data);
//					            data = RegExp.$1;
//					            if(data !=null && data != ''){
//					            	
//					            	data = eval('(' + data + ')');
//					            }
					            if (typeof (result.error) != 'undefined') {
					                if (result.error != '' && result.error != null) {
					                	$imoprtInfo.find('#info').html(result.error);
					                	$.importExcel.expand(500,360);
					                } else {
					                	o.success(result, status);
					                	$('#import-dialog').dialog('close');
					                }
					            }
					            $.easyui.loaded(); 
					        },
					        //服务器响应失败处理函数
					        error: function (data, status, e){
					        	if(status != 'success') 
					            { 
					        		alert(e);
					        	}
					            $.easyui.loaded(); 
					        }
						});
					}
				},{
					text:o.downloadText,
					iconCls:'icon-download',
					handler:function(){
						window.location.href=BasePath + '/download?fileName='+ encodeURIComponent(o.templateName);
					},
				},{
					text:'取消',
					iconCls:'icon-close',
					handler:function(){
						$imoprt.panel('close');
					},
				}]
			});
			
			//初始化提示面板
			$imoprtInfo.panel({
				  width:488,    
				  height:158,
				  title: '提示信息',
				  collapsible : true,
				  onCollapse:function(){
					  $.importExcel.shrink(500,212);
				  },
				  onBeforeExpand:function(){
					  $.importExcel.expand(500,360);
				  }
			}),
			$imoprt.window('open');
		},
		
		shrink : function(_width,_height){
			$($imoprt).dialog('resize',{
				width: _width,
				height: _height
		    });
		},
		
		expand : function(_width,_height){
			$($imoprt).dialog('resize',{
				width: _width,
				height: _height
		    });
		},
		
		close : function(){
			$imoprt.panel('close');
		},
		success : function(){
			opts.success.call();
		},
		error : function(e){
			opts.error.call(this,e);
		}
	};
	
	$.importExcel = function(options) {
		$.fn.importExcel.open(options);
	};

	$.importExcel.open = function(options) {
		var opts = $.extend({}, defaults, options);
		importExcel.open(opts);
	};

	$.importExcel.shrink = function(_width,_height) {
		return importExcel.shrink(_width,_height);
	};
	
	$.importExcel.expand = function(_width,_height) {
		return importExcel.expand(_width,_height);
	};
	$.importExcel.success = function(){
		return importExcel.success();
	};
	
	$.importExcel.error = function(e){
		return importExcel.error(e);
	};
	
	$.importExcel.colse = function(){
		return importExcel.close();
	};
	
})(jQuery);