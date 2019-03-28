function parsePage(){
	/*buildSubSys_scheduler();*/
	
	InitLeftMenu('#leftMenu');

	$('#tabToolsFullScreen').click(function(){
		changeScreen();
	});
	
	addTab({
		title: '系统桌面',
		icon: 'icon-system'
	});
	
}
/**
*根据JSON数据生成子系统
*/
function buildSubSys_scheduler(){
	var callback = function(result){
		buildSubSysCommon(result);
	};
	$.getJSON(BasePath+"/uc_sub_system.json",callback);
}

//初始化左侧菜单
function InitLeftMenu(obj) {
	 $.getJSON(BasePath+"/get_menu_data", function(data){
		 buildMenuCommon(data[0].children);
	 });
}
