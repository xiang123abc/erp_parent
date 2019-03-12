// Ajax 文件下载
/*.download = function(url, data){    // 获得url和data
    var inputs = '';    
    $.each(data, function(name, value) {        	
        inputs+='<input type="hidden" name="'+ name +'" value="'+ value +'" />'; 
    }); 
    $('<form action="'+ url +'" method="post">'+inputs+'</form>')
    .appendTo('body').submit().remove();        
};*/
$.download = function(url){    // 获得url
	//定义text，添加div
	$(this).append('div');
	//在div里添加a标签，并将text作为a的href使用
	$('div').html('<a href="'+url+'"><span id="sp"></a>')  
	$("#sp").trigger("click");
};