
var method = "";
$(function(){
	//$.messager.alert("提示",[{"name":"管理员组","tele":"000000","uuid":1},{"name":"总裁办","tele":"111111","uuid":2},{"name":"采购部","tele":"222222","uuid":3},{"name":"销售部","tele":"333333","uuid":4},{"name":"公关部","tele":"444444","uuid":5},{"name":"行政部","tele":"555555","uuid":6},{"name":"人事部","tele":"555555","uuid":7},{"name":"运输部","tele":"444444","uuid":8},{"name":"党办","tele":"555555","uuid":9},{"name":"工会","tele":"555555","uuid":10},{"name":"仓储部","tele":"555555","uuid":11},{"name":"客服部","tele":"555555","uuid":12},{"name":"财务部","tele":"555555","uuid":13},{"name":"运营部","tele":"555555","uuid":14}]);
	$('#grid').datagrid({    
	    url:name+'_list',    
	    columns:columns,
	    singleSelect: true,
	    pagination: true,
	    toolbar: [{
	    	text: '新增',
			iconCls: 'icon-add',
			handler: function(){
				method = "add";
				$('#editDlg').dialog('open');
			}
		}]
	});
	
	$('#btnSearch').bind('click',function(){
		//把表单数据转换成json对象
		var formData = $('#searchForm').serializeJSON();
		//$('#grid').datagrid('load',formData);
		//alert(JSON.stringify($('#grid').datagrid('getData')));
		//把json对象转换成字符串
		// alert(JSON.stringify(formData));
		$.ajax({
			url: name+'_list',
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
	});
	
	$('#editDlg').dialog({    
	    title: '部门编辑',    
	    width: 300,
	    height: 200,
	    closed: true,//窗口是是否为关闭状态, true：表示关闭    
	    modal: true//模式窗口
	});   
	
	$('#btnSave').bind('click',function(){
		var formData = $('#editForm').serializeJSON();
		$.ajax({
			url: name+'_'+method,
			data: formData,
			dataType: 'json',
			type: 'post',
			success:function(rtn){
				$.messager.alert("提示",rtn.message,'info',function(){	
					//成功的话，我们要关闭窗口
					$('#editDlg').dialog('close');
					//刷新表格数据
					$('#grid').datagrid('reload');
				});
			}
		});
	});

});


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

/**
 * 修改部门
 */
function edit(uuid){
	//弹出窗口
	$('#editDlg').dialog('open');
	
	//清空表单内容
	$('#editForm').form('clear');
	method = "update";
	//加载数据
	$('#editForm').form('load',name+'_get?id=' + uuid);
}
