var name ='orders';
$(function(){	
	var url = 'orders_list';	
	var btnText = "";
	var inoutTitle = "";
	document.title="我的销售订单";
	$('#grid').datagrid({
		url: url,
		columns:getColumns(),
		singleSelect:true,
		/*pagination:true,*/
		fitColumns:true,
		//显示编辑
		rownumbers: true,
		//显示行脚
		showFooter: true,
	});

	
	$('#btnSearch').bind('click',function(){
		//把表单数据转换成json对象
		var formData = $('#searchForm').serializeJSON();
		//$('#grid').datagrid('load',formData);
		//alert(JSON.stringify($('#grid').datagrid('getData')));
		//把json对象转换成字符串
		// alert(JSON.stringify(formData));
		$.ajax({
			url: 'orders_list',
			data: formData,
			dataType: 'json',
			type: 'post',
			success:function(rtn){
				$('#grid').datagrid('loadData',rtn);
				//
				//total = rtn.length;
				//{total: total, rows:rtn};
			}
	    });
	});
	$('#doExport').bind('click',function(){
		doExport();
	});
	
});

/**
 * 日期格式化器
 * @param value
 * @returns
 */
function formatDate(value){
	return new Date(value).Format('yyyy-MM-dd');
}

/**
 * 根据订单类型，获取不同的列
 */
function getColumns(){
		return [[
		            {field:'orderscode',title:'编号',width:50},
		  		    {field:'starttime',title:'下单日期',width:50,formatter:formatDate},	
		  		 	{field:'suppliercode',title:'客户',width:50},
		  		    {field:'totalmoney',title:'合计金额',width:50},
				]];
}

//
function doExport(){
	var url = 'orders_exportAll?suppliercode='+$('#suppliercode').val()+'&beforeDate='+$('#beforeDate').datebox('getValue')+'&afterDate='+$('#afterDate').datebox('getValue');
    var fileName = "testAjaxDownload.txt";
    var form = $("<form></form>").attr("action", url).attr("method", "post");
    form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
    form.appendTo('body').submit().remove();
}


