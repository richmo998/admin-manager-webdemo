<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>词条翻译</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<#include  "/WEB-INF/ftl/common/header.ftl" >
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
                                <#--<input class="easyui-validatebox ipt" required style="width:300px;height: auto" name="zhCn" id="zhCn"-->
                                       <#--data-options="" onblur="doSearch()"/>-->
                                <textarea style="width:300px;" rows="6" name="zhCn" id="zhCn" onblur="doSearch()"></textarea>
                            </td>

                            <td>&nbsp;&nbsp;&nbsp;中文繁体：</td>
                            <td style="width:115px;">
                                <#--<input class="easyui-validatebox ipt" style="width:300px;height: auto" name="zhHk" id="zhHk"-->
                                       <#--data-options=""/>-->
                                <textarea style="width:300px;"s name="zhHk" id="zhHk" rows="6"></textarea>
                            </td>

                            <td>&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <a id="searchButton" href="#" class="easyui-linkbutton" style="width:70px"
                                   data-options="iconCls:'icon-search'"
                                   onclick="doSearch()">翻译</a>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    function doSearch() {
        var zhCn =  $('#zhCn').val();
        $.ajax({
            type: 'POST',
            cache: false,
            url: BasePath + '/translate/translateFromApi?zhCn=' + zhCn,
            success: function (data) {
                if (data) {
                    $('#zhHk').val(data.zhHk);
                }

            },
            dataType: 'json'
        });
    };

</script>
</body>