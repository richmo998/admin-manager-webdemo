var commonValidRule =  {};

/**
 * 验证是否是中文
 */
commonValidRule.isChineseChar = function(str){
	var reg = /^[\u4e00-\u9fa5]+$/gi;
	if(reg.test(str)){
		return true;
	}
	return false;
};

/**
 * 不能包含中文验证
 */
commonValidRule.vnChinese = {
		vnChinese: {    
	        validator: function(value, param){
	        	for(var i=0;i<value.length;i++){
	        		if(commonValidRule.isChineseChar(value[i])){
	        			return false;
	        		}
	        	}
	            return true;    
	        },    
	        message: '{0}'   
	    }  
};

voidChar = ['<','>','input'];
function isValidString(szStr){
	if(szStr!=null&&szStr!=''){
		   for(var i=0;i<voidChar.length;i++){
			   if(szStr.indexOf(voidChar[i]) > -1){
				      return false;
			  }
		   }
		   
	}
  return true;
}

commonValidRule.isValidText = {
		isValidText: {    
	        validator: function(value, param){
	        	
	            return isValidString(value);    
	        },    
	        message: '包含非法字符(<,>,input)'   
	    }  
};

/**
 * 长度验证
 */
commonValidRule.vLength = {
		vLength: {    
	        validator: function(value, param){
	        	var chineseCharLength = param[3] || 3;
	        	var tempLength = 0;
	        	
	        	for(var i=0;i<value.length;i++){
	        		if(commonValidRule.isChineseChar(value[i])){
	        			tempLength += chineseCharLength;
	        		}else{
	        			tempLength += 1;
	        		}
	        	}
	        	if(tempLength<param[0] || tempLength>param[1]){
	        		return false;
	        	}
	            return true;    
	        },    
	        message: '{2}'   
	    }  
};

//验证电话号码
commonValidRule.validateInputTel = {
	validateInputTel: {
		validator: function(value, param){
			var v_regex = /^([0-9]|[-])+$/g;
			return v_regex.exec(value);
		},
		message: '无效的电话号码,请重新输入！'
	}
};

$(document).ready(function(){
	$.extend($.fn.validatebox.defaults.rules,commonValidRule.vnChinese); 
	$.extend($.fn.validatebox.defaults.rules,commonValidRule.vLength);
	$.extend($.fn.validatebox.defaults.rules,commonValidRule.validateInputTel);
	$.extend($.fn.validatebox.defaults.rules,commonValidRule.isValidText);
});

parseParam=function(param){
    var paramStr="";
   {
        $.each(param,function(i){
        	paramStr+='&'+i+'='+param[i];
        });
    }
return paramStr.substr(1);
}; 

/**
 * 基础资料的导出
 * @param dataGridId  导出数据的表格ID
 * @param exportUrl 导出数据的URL   基础资料一般都是 /模块名/do_export.htm     *如机构:/store/do_export 
 * @param excelTitle excel文件名
 * @param checkFlag 导出数据的表格是否有复选框(有复选框后台自动去掉该列)  0--无  1--有
 * @param reduceColnumName 不用导出的列名
 */
function exportExcelBaseInfo(dataGridId,exportUrl,excelTitle,checkFlag,reduceColnumName){
	var $dg = $("#"+dataGridId+"");
	
	var params=$dg.datagrid('options').queryParams;
	var columns=$dg.datagrid('options').columns;
	var v_pageNumber = $dg.datagrid('options').pageNumber;//当前页号
	var v_pageSize = $dg.datagrid('options').pageSize;//每页多少行记录
	var columnsNew = [];
	$.each(columns,function(index,item){
		var dataArray = [];
		$.each(item,function(rowIndex,rowData){
			var v_object = {};
			v_object.field = rowData.field;
			v_object.title = rowData.title;
			dataArray[rowIndex] = v_object;
		});
		columnsNew[index] = dataArray;
	});
	
	var exportColumns=JSON.stringify(columnsNew);
	
	var url=BasePath+exportUrl;
	
	var dataRow=$dg.datagrid('getRows');

	$("#exportExcelForm").remove();
	
	$("<form id='exportExcelForm'  method='post'></form>").appendTo("body"); ;
	
	var fromObj=$('#exportExcelForm');
	
	if(dataRow.length>0){
	    fromObj.form('submit', {
			url: url,
			onSubmit: function(param){
				
				param.exportColumns=exportColumns;
				param.fileName=excelTitle;
				param.checkFlag=checkFlag;
				param.reduceColnumName=reduceColnumName;
				param.pageNumber = v_pageNumber;
				param.pageSize = v_pageSize;
				if(params!=null&&params!={}){
					$.each(params,function(i){
						param[i]=params[i];
					});
				}
				
			},
			success: function(){
				
		    }
	   });
	}else{
		alert('查询记录为空，不能导出!',1);
	}
	

}

/**
 * 订单功能的导出
 * @param dataGridId  表格ID
 * @param sysNo  品牌库的ID
 * @param preColNames  前面显示业务列 公用查询动态生成的参数
 * @param endColNames  后面显示的业务列
 * @param sizeTypeFiledName 
 * @param excelTitle excel文件名
 */
function exportExcelBill(dataGridId,sysNo,preColNames,endColNames,sizeTypeFiledName,excelTitle){
	
	var url=BasePath+'/initCache/do_export.htm';
	
	var $dg = $("#"+dataGridId+"");
	
	$("#exportExcelForm").remove();
	
	$("<form id='exportExcelForm'  method='post'></form>").appendTo("body"); ;
	
	var fromObj=$('#exportExcelForm');
	
	var dataRow=$dg.datagrid('getRows');
	
	if(dataRow.length>0){
	    fromObj.form('submit', {
			url: url,
			onSubmit: function(param){
				
				param.sysNo=sysNo;
				param.preColNames=JSON.stringify(preColNames);
				param.endColNames=JSON.stringify(endColNames);
				param.sizeTypeFiledName=sizeTypeFiledName;
				param.fileName=excelTitle;
				param.dataRow=JSON.stringify(dataRow);
			},
			success: function(){
				
		    }
	   });
	}else{
		alert('数据为空，不能导出!',1);
	}
	
	
}
/**
 * 下单下单公用方法
 * @param dataGridId
 * @param rowIndex
 * @param type  1--上单 2--下单
 * @param callBack  回调函数名
 */
function preBill(dataGridId,rowIndex,type,callBack){
	var $dg = $("#"+dataGridId+"");
	var curRowIndex=rowIndex;
	
	var options = $dg.datagrid('getPager').data("pagination").options;
	var currPage= options.pageNumber;
	var total = options.total;
	var max = Math.ceil(total/options.pageSize);
	var lastIndex=Math.ceil(total%options.pageSize);
	var pageSize=options.pageSize;
	var rowData=[];
	if(type==1){
		if(curRowIndex!=0){
			curRowIndex=curRowIndex-1;
			$dg.datagrid('unselectAll');
			$dg.datagrid('selectRow', curRowIndex);
			var rows = $dg.datagrid('getRows');
			if(rows){
				rowData=rows[curRowIndex];
			}
			
			callBack(curRowIndex,rowData);
		}else{ //跳转到上一页的最后一行
			if(currPage<=1){
				$dg.datagrid('unselectAll');
		    	$dg.datagrid('selectRow', curRowIndex);
				callBack(curRowIndex,null);
			}else{
				$dg.datagrid('getPager').pagination({pageSize:options.pageSize,pageNumber:(currPage-1)});
				$dg.datagrid('getPager').pagination('select', currPage-1);  
				
				curRowIndex=pageSize-1;
				$dg.datagrid({
				        onLoadSuccess:function(data){
							    if(type==1){
							    	$dg.datagrid('unselectAll');
							    	$dg.datagrid('selectRow', curRowIndex);
									var rows = $dg.datagrid('getRows');
									if(rows){
										rowData=rows[curRowIndex];
									}
									callBack(curRowIndex,rowData);
							    }
								
							
				       }
			     });
			
			}
		}
	}else if(type==2){
		
		if(curRowIndex!=(pageSize-1)){
			if(currPage==max&&lastIndex!=0&&curRowIndex==(lastIndex-1)){
				$dg.datagrid('unselectAll');
		    	$dg.datagrid('selectRow', curRowIndex);
				callBack(curRowIndex,null);
			}else{
				curRowIndex=curRowIndex+1;
				$dg.datagrid('unselectAll');
				$dg.datagrid('selectRow', curRowIndex);
				var rows = $dg.datagrid('getRows');
				if(rows){
					rowData=rows[curRowIndex];
				}
				
				callBack(curRowIndex,rowData);
			}
			
			
		}else{
			
			if(currPage>=max){
				$dg.datagrid('unselectAll');
		    	$dg.datagrid('selectRow', curRowIndex);
				callBack(curRowIndex,null);
			}else{
				$dg.datagrid('getPager').pagination({pageSize:options.pageSize,pageNumber:(currPage+1)});
				$dg.datagrid('getPager').pagination('select', currPage+1);  
				
				curRowIndex=0;
				$dg.datagrid({
			        onLoadSuccess:function(data){
						    if(type==2){
						    	
						    	$dg.datagrid('unselectAll');
						    	$dg.datagrid('selectRow', curRowIndex);
								var rows = $dg.datagrid('getRows');
								if(rows){
									rowData=rows[curRowIndex];
								}
								callBack(curRowIndex,rowData);
						    }
							
						
			       }
		     });
			}
		}
		
	}
	
	
}
//它将jquery系列化后的值转为name:value的形式。
function convertArray(o) { 
	var v = {};
	for ( var i in o) {
		if (typeof (v[o[i].name]) == 'undefined')
			v[o[i].name] = o[i].value;
		else
			v[o[i].name] += "," + o[i].value;
	}
	return JSON.stringify(v);
}

checkExistFun = function(url,checkColumnJsonData){
	var checkExist=false;
 	$.ajax({
		  type: 'get',
		  url: url,
		  data: checkColumnJsonData,
		  cache: true,
		  async:false, // 一定要
		  success: function(totalData){
			  totalData = parseInt(totalData,10);
			  if(totalData>0){
				  checkExist=true;
			  }
		  }
     });
 	return checkExist;
};

ajaxRequest = function(url,reqParam,callback){
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: reqParam,
		  cache: true,
		  success: callback
	});
};

//浮点数加法运算  
function FloatAdd(arg1,arg2){  
  var r1,r2,m;  
  try{
	  r1=arg1.toString().split(".")[1].length;
   }catch(e){
	   r1=0;
   } 
  try{
	  r2=arg2.toString().split(".")[1].length;
   }catch(e){
	   r2=0;
   }  
  m=Math.pow(10,Math.max(r1,r2)) ; 
  return (arg1*m+arg2*m)/m  ;
 }  

function trimBlank(thisObj){
	 var value=$(thisObj).val();
	  value=jQuery.trim(value);
	  $(thisObj).val(value);  	 	
}  

function accAdd(arg1, arg2) {
	 var r1, r2, m;
	 try {
	  r1 = arg1.toString().split(".")[1].length;
	 } catch (e) {
	  r1 = 0;
	 }
	 try {
	  r2 = arg2.toString().split(".")[1].length;
	 } catch (e) {
	  r2 = 0;
	 }
	 m = Math.pow(10, Math.max(r1, r2));
	 return accDiv((arg1 * m + arg2 * m) , m);
}

//js 除法函数
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1, arg2) {
var t1 = 0, t2 = 0, r1, r2;
try {
t1 = arg1.toString().split(".")[1].length;
} catch (e) {
}
try {
t2 = arg2.toString().split(".")[1].length;
} catch (e) {
}
with (Math) {
r1 = Number(arg1.toString().replace(".", ""));
r2 = Number(arg2.toString().replace(".", ""));
return (r1 / r2) * pow(10, t2 - t1);
}
}

function closeWindow(menuName){
    var tab = parent.$('#mainTabs').tabs('getSelected');
    var index = parent.$('#mainTabs').tabs('getTabIndex',tab);
    parent.$('#mainTabs').tabs('close',index);
    
}

// 公用弹出框
function alert(msg,type){
  //info-0,warning-1,error-2,question-3 ,success-4 
   var typeStr="info";
   if(type==1){
       typeStr='warning';
        $.messager.alert('提示框',msg,typeStr); 
   }else if(type==2){
       typeStr='error';
        $.messager.alert('提示框',msg,typeStr); 
   }else if(type==3){
       typeStr='question';
        $.messager.alert('提示框',msg,typeStr); 
   }else if(type==4){
       typeStr='success';
       $.messager.alert('提示框',msg,typeStr);
   }else{
       typeStr='info';
        $.messager.alert('提示框',msg,typeStr); 
   }
  
}

 //发达ajax请求
 function ajaxRequest(url,reqParam,callback){
		$.ajax({
			  type: 'POST',
			  url: url,
			  data: reqParam,
			  cache: true,
			  success: callback
	    });
 }
 
function checkPowerJS(powerValue,index){
        var flag=false;
        var  temp =parseInt(Math.pow(2,index));
          var result = powerValue&temp;
            if(result== temp){
                 flag=true;
            }
        return flag;
 }

//打开新的窗口
function openNewPane(url,modeId,noteText){
 	var t = parent.$('#mainTabs');
	//var url=BasePath+'/receipt/tms_bill_truck_info/list?quartzcenterNo='+currentQuartzcenterNo+'&truckNo='+item.truckNo;
	if(url.indexOf('?')>0){
	   url+='&moduleId='+modeId;
	}else{
	   url+='?moduleId='+modeId;
	}
 	if(!t.tabs('exists',noteText)){
 		top.addBlankTab({title:noteText,href:url,closable: true})
 	}else{
 		t.tabs('close',noteText);
 		top.addBlankTab({title:noteText,href:url,closable: true})
 	}
 	
}

//清除所有表单的验证提示信息
function clearAllValidateHintMsg(){
	$('.validatebox-tip').remove();
}

function lookProperty(obj){
    ob=eval(obj);
    var property="";
    for(var i in ob){
         property+="属性："+i+"<br/>";
    }
    alert(property);
 }
