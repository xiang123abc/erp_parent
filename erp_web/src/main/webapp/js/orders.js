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
		onDblClickRow:function(rowIndex, rowData){
			//rowIndex， 行的索引
			//rowData， 行里的数据
			//alert(JSON.stringify(rowData));
			//显示详情
			$('#orderscode').html(rowData.orderscode);
			$('#material').html(rowData.orderscode);
			$('#color').html(rowData.orderscode);
			$('#model').html(rowData.orderscode);
			$('#suppliercode').html(rowData.suppliercode);
			/*$('#state').html(getState(rowData.state));*/
			$('#creater').html(rowData.creatername);
			$('#starttime').html(formatDate(rowData.starttime));
			$('#endtime').html(formatDate(rowData.endtime));
			//打开窗口
			$('#ordersDlg').dialog('open');
			//加载明细列表
			$('#itemgrid').datagrid('loadData',rowData.orderdetails);
		 },
		//显示编辑
		rownumbers: true,
		//显示行脚
		showFooter: true
	
	
	});
	
	//明细表格
	$('#itemgrid').datagrid({
		columns:[[
			{field:'goodscode',title:'商品编号',width:100},
			{field:'goodsname',title:'商品名称',width:100},
            {field:'height',title:'高',width:100},
            {field:'weight',title:'宽',width:100},
  		    {field:'num',title:'数量',width:100},
            {field:'area',title:'面积',width:100},
  		    {field:'price',title:'价格',width:100},
  		    {field:'money',title:'金额',width:100},
  		   /* {field:'state',title:'状态',width:100,formatter:getDetailState}*/
		]],
		fitColumns:true,
		singleSelect:true,
		toolbar: [{
			text: '导出',
			iconCls: 'icon-excel',
			handler: doExport
		}]
	});
	
	//添加按钮
	var toolbar = new Array();
	//添加导出按钮
	toolbar.push({
		text:'导出',
		iconCls:'icon-excel',
		handler:doExport
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
				//grid加载数据
				//datagrid(参数1，参数2)
				//参数1 =》 datagrid里的方法名称
				//参数2 =》 datagrid里的方法所需要的参数
				$('#grid').datagrid('loadData',rtn);
				//
				//total = rtn.length;
				//{total: total, rows:rtn};
			}
	 }); 
		

	$('#grid').datagrid('footer',[{suppliercode: '合计', totalmoney: 0}]);
	
	sum();
	
	/*//订单导出
	$('#exportBtn').bind('click',function(){
		//var formdata= $('#searchForm').serializeJSON();
		//$.download('orders_export.action?id='+$("#orderscode").html(),formdata);
		$.download('orders_export.action?id='+$("#orderscode").html());
	   console.log("ajaxDownloadSynchronized");
        var url = "orders_export.action?id='+$("#orderscode").html()";
        var fileName = "testAjaxDownload.txt";
        var form = $("<form></form>").attr("action", url).attr("method", "post");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
        form.appendTo('body').submit().remove();
	
	});*/
    });
	
/*	//添加审核按钮
	if(Request['oper'] == 'doCheck'){
		$('#ordersDlg').dialog({
			toolbar:[{
				text:'审核',
				iconCls:'icon-search',
				handler:doCheck
			}]
		});
	}*/
	
	/*//添加审核按钮
	if(Request['oper'] == 'doStart'){
		$('#ordersDlg').dialog({
			toolbar:[{
				text:'确认',
				iconCls:'icon-search',
				handler:doStart
			}]
		});
	}*/
	/*//添加双击事件
	$('#itemgrid').datagrid({
		onDblClickRow:function(rowIndex, rowData){
			//显示数据
			
			$('#itemuuid').val(rowData.uuid);
			$('#goodsuuid').html(rowData.height);
			$('#goodsuuid').html(rowData.weight);
			$('#goodsuuid').html(rowData.num);
			$('#goodsuuid').html(rowData.area);
			$('#goodsname').html(rowData.price);
			$('#goodsnum').html(rowData.money);
			//打开出入库窗口
			$('#itemDlg').dialog('open');
			
			//显示数据
			
			$('#itemuuid').val(rowData.uuid);
			$('#goodsuuid').html(rowData.goodsuuid);
			$('#goodsuuid').html(rowData.goodsuuid);
			$('#goodsname').html(rowData.goodsname);
			$('#goodsnum').html(rowData.num);
			//打开出入库窗口
			$('#itemDlg').dialog('open');
		}
	});*/

	
	//增加订单的窗口
	$('#addOrdersDlg').dialog({
		title:'增加订单',
		width:700,
		height:400,
		modal:true,
		closed:true
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
		            {field:'orderscode',title:'编号',width:100},
		            {field:'material',title:'材料',width:100},
		            {field:'color',title:'颜色',width:100},
		            {field:'model',title:'造型',width:100},
		  		    {field:'starttime',title:'下单日期',width:100,formatter:formatDate},
		  		    {field:'endtime',title:'出货日期',width:100,formatter:formatDate},
		  		    {field:'totalarea',title:'面积',width:100},
		  		 	{field:'suppliercode',title:'客户',width:100},
		  		    {field:'totalmoney',title:'合计金额',width:100},
		  		    {field:'-',title:'操作',formatter: function(value,row,index){
		  		    	var oper = "<a href=\"javascript:void(0)\" onclick=\"del(\'" + row.orderscode + '\')">删除</a>';
		  		    	return oper;
		  			}}
		  		    /*{field:'state',title:'状态',width:100,formatter:getState},*/
		  		  /*  {field:'waybillsn',title:'运单号',width:100}*/
				]];
}
function sum(){	

	//获取所有行
	var rows = $('#gird').datagrid('getRows');
	var total = 0;
	//循环累计
	$.each(rows, function(i, row){
		total += parseFloat(row.money);
	});
	total = total.toFixed(4);
	
	//设置合计金额到行脚里去
	$('#ordersgird').datagrid('reloadFooter',[{price: '合计', totalmoney: total}]);
}

//
function doExport(){
	var url = 'orders_export?id='+$('#orderscode').html();
    var fileName = "testAjaxDownload.txt";
    var form = $("<form></form>").attr("action", url).attr("method", "post");
    form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
    form.appendTo('body').submit().remove();
}


/**
 * 删除部门
 */
function del(uuid){
	$.messager.confirm("确认","确认要删除吗？",function(yes){
		if(yes){
			$.ajax({
				url: name+'_delete?id='+ uuid,
				dataType: 'json',
				type: 'post',
				async:false,
				success:function(value){
					if(value.success){
						$('#grid').datagrid('reload');
					}
					$.messager.alert('提示',value.message);
				}
				
			});
		}
	});
}


