//保存当编辑的行的索引
var existEditIndex = -1;
$(function(){
	$('#ordersgird').datagrid({
		columns:[[    
		          {field:'goodsname',title:'商品名称',width:100,editor:{type:'text',options:{
		          }}},
		          {field:'height',title:'高',width:70,editor:'numberbox'},
		          {field:'weight',title:'宽',width:70,editor:'numberbox'},
		          {field:'num',title:'数量',width:50,editor:'numberbox'},
		          {field:'goodstype',title:'商品类型',width:100,editor:{type:'combobox',options:{
		        	  url:'goodstype_list',
		        	  valueField:'name',
		        	  textField:'name',//goodsname
		        	  onSelect: function(goodstype){
		        		  //获取商品编辑编辑器
		        		  var goodstypeEditor = getEditor('goodstype');
		        		  //target，指向真正使用element
		        		  $(goodstypeEditor.target).val(goodstype.goodstypecode);
		        		  
		        		//获取数量编辑器
		        		  var numEditor = getEditor('num');
		        		  //选中数量输入框
		        		  $(numEditor.target).select();
		        		  //绑定事件
		        		  bindGridEditor();
		        		  //计算金额
		        		  cal();
		        		  //计算合计金额
		        		  sum();
		        	  }
		          }}},
		          {field:'area',title:'面积',width:100,editor:{type:'numberbox',options:{precision:4,disabled:true}}},
		          {field:'price',title:'单价',width:40,editor:{type:'numberbox',options:{precision:4}}},
		          {field:'money',title:'金额',width:100,editor:{type:'numberbox',options:{precision:4,disabled:true}}},
		          {field:'note',title:'备注',width:100,editor:{type:'text',options:{
		          }}},
		          {field:'-',title:'操作',width:100,formatter:function(value, row, rowIndex){
		        	  if(row.num != '合计'){
		        		  return '<a href="javascript:void(0)" onclick ="deleteRow(' + rowIndex + ')">删除</a>';
		        	  }
		          }}
		          
		]],
		singleSelect:true,
		//显示编辑
		rownumbers: true,
		//显示行脚
		showFooter: true,
		toolbar: [
			{
				text: '新增',
				iconCls: 'icon-add',
				handler: function(){
					//判断是否存在编辑的行
					if(existEditIndex > -1){
						//关闭编辑
						$('#ordersgird').datagrid('endEdit',existEditIndex);
					}
					//增加一行, row参数:{goodsuuid:'',goodsname:'',price:''}
					$('#ordersgird').datagrid('appendRow',{num:0,money:0});
					//获取所的行记录，数组
					var rows = $('#ordersgird').datagrid('getRows');
					
					//设置当前编辑行的索引
					existEditIndex = rows.length-1;
					//需要先设置它的编辑器，才能开启编辑状态
					$('#ordersgird').datagrid('beginEdit',existEditIndex);
					
				}
			},'-',{
				text: '提交',
				iconCls: 'icon-save',
				handler: function(){
					//1. 存在编辑状态的行
					if(existEditIndex > -1){
						$('#ordersgird').datagrid('endEdit',existEditIndex);
					}
					//获取所有的明细
					var rows = $('#ordersgird').datagrid('getRows');
					if(rows.length == 0){
						return;
					}
					var formdata = $('#orderForm').serializeJSON();
					//转换成json字符串
					//给formdata加了一个json属性，key。同时再给它赋值
					formdata.json = JSON.stringify(rows);
					//formdata['json']= JSON.stringify(rows);
					$.ajax({
						url: 'orders_add',
						data: formdata,
						dataType: 'json',
						type: 'post',
						success:function(rtn){
							$.messager.alert('提示',rtn.message,'info',function(){
								if(rtn.success){
									//清空供应商
									$('#supplier').combogrid('clear');
									//清空表格
									$('#ordersgird').datagrid('loadData',{total:0, rows:[],footer:[{num: '合计', money: 0}]});
								}
							});
						}
					});
				}
			}
		],
		onClickRow:function(rowIndex, rowData){
			//rowIndex：点击的行的索引值，该索引值从0开始。
			//rowData：对应于点击行的记录。			
			//关闭当前可以编辑的行
			$('#ordersgird').datagrid('endEdit',existEditIndex);
			//设置当前可编辑的索引行
			existEditIndex = rowIndex;
			$('#ordersgird').datagrid('beginEdit',existEditIndex);
		}
	});
	
	//加行脚
	$('#ordersgird').datagrid('reloadFooter',[{num: '合计', money: 0}]);
	
	//加载供应商下拉表格
	$('#supplier').combogrid({    
	    panelWidth:700,
	    idField:'suppliercode',    
	    textField:'name',    
	    url:'supplier_list?t1.type=1',    
	    columns:[[    
			{field:'suppliercode',title:'编号',width:100},
			{field:'name',title:'名称',width:100},
			{field:'address',title:'联系地址',width:100},
			{field:'contact',title:'联系人',width:100},
			{field:'tele',title:'联系电话',width:100},
			{field:'email',title:'邮件地址',width:100}
	    ]]    
	}); 

});

/**
 * 获取当前编辑行的指定编辑器
 * @param _field
 * @returns
 */
function getEditor(_field){
	return $('#ordersgird').datagrid('getEditor',{index:existEditIndex,field:_field});
}

/**
 * 计算面积
 */
function cal(){
	//获取数量编辑器
	var numEditor = getEditor('num');
	//取得商品的数量
	var num = $(numEditor.target).val();
	
	//获取高编辑器
	var heightEditor = getEditor('height');
	//取得高的值
	var height = $(heightEditor.target).val();
	
	//获取宽编辑器
	var weightEditor = getEditor('weight');
	//取得宽的值
	var weight = $(weightEditor.target).val();
	

	/*//获取面积编辑器
    var priceEditor = getEditor('area');
    //取出面积值
    var price = $(priceEditor.target).val();*/
	/*//获取价格编辑器
    var priceEditor = getEditor('price');
    //取出进货价格
    var price = $(priceEditor.target).val();*/
    
    //计算金额
//    var money = num * price;
    var area = height * weight * num /10000;
    //保留4位小数
    area = area.toFixed(4);

    //获取金额编辑器
    var areaEditor = getEditor('area');
    //设置金额
    $(areaEditor.target).val(area);
    
    //更新表格中的数据,设置row json对象里的key对应的值
    $('#ordersgird').datagrid('getRows')[existEditIndex].area = area;
    
    
    
   /* //更新表格中的数据,设置row json对象里的key对应的值
    $('#ordersgird').datagrid('getRows')[existEditIndex].money = money;*/
}

function calmoney(){
	//获取数量编辑器
	var priceEditor = getEditor('price');
	//取得商品的数量
	var price = $(priceEditor.target).val();
	
	//获取面积编辑器
	var areaEditor = getEditor('area');
	//取得面积的值
	var  area = $(areaEditor.target).val();
	
	 var money = price * area;
	    //保留4位小数
	 money = money.toFixed(4);

	    //获取金额编辑器
	    var moneyEditor = getEditor('money');
	    //设置金额
	    $(moneyEditor.target).val(money);
	    
	    //更新表格中的数据,设置row json对象里的key对应的值
	    $('#ordersgird').datagrid('getRows')[existEditIndex].money = money;
}


/**
 * 绑定键盘的输入事件
 */
function bindGridEditor(){
	//获取高编辑器
	var heightEditor = getEditor('height');
	$(heightEditor.target).bind('keyup',function(){
		//计算面积
		cal();
		//计算金额
		calmoney();
		//计算合计金额
		sum();
	});
	//获取宽编辑器
	var weightEditor = getEditor('weight');
	$(weightEditor.target).bind('keyup',function(){
		//计算面积
		cal();
		//计算金额
		calmoney();
		//计算合计金额
		sum();
	});
	//获取数量编辑器
	var numEditor = getEditor('num');
	$(numEditor.target).bind('keyup',function(){
		//计算面积
		cal();
		//计算金额
		calmoney();
		//计算合计金额
		sum();
	});
	
	//绑定价格编辑器
	var priceEditor = getEditor('price');
	$(priceEditor.target).bind('keyup',function(){
		/*//计算金额
		cal()*/
		//计算金额
		calmoney();
		//计算合计金额
		sum();
	});
}

/**
 * 计算合计金额
 */
function sum(){	
	//获取所有行
	var rows = $('#ordersgird').datagrid('getRows');
	var total = 0;
	//循环累计
	$.each(rows, function(i, row){
		total += parseFloat(row.money);
	});
	total = total.toFixed(4);
	
	//设置合计金额到行脚里去
	$('#ordersgird').datagrid('reloadFooter',[{num: '合计', money: total}]);
}

/**
 * 删除行
 * @param rowIndex
 */
function deleteRow(rowIndex){
	//alert(JSON.stringify(data));
	//关闭编辑
	$('#ordersgird').datagrid('endEdit',existEditIndex);
	//删除行
	$('#ordersgird').datagrid('deleteRow',rowIndex);
	
	var data = $('#ordersgird').datagrid('getData');
	//重新加载数据
	$('#ordersgird').datagrid('loadData',data);
	//计算合计
	sum();
}