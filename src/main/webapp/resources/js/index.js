$(function() {
	$('#queryJson').on('click',function(){
		var url = 'queryUser';
		$.get(url,function(data){
			$('#jsonArea').html('');
			$('#jsonArea').html(JSON.stringify(data,null,'\t'));
		})
	})
	
	$('#queryJsonp').on('click',function(){
		var url = 'queryUserJsonp';
		 $.ajax({
             type: "get",
             async: false,
             url: url,
             dataType: "jsonp",
             success: function(json){
            	 $('#jsonpArea').html('');
            	 $('#jsonpArea').html(JSON.stringify(json,null,'\t'));
             },
             error: function(data){
                 alert('fail');
             }
         });
	})
	
	$('#getJsonFile').on('click',function(){
		var url = 'resources/json/jsonp.json';
		 $.ajax({
            type: "get",
            url: url,
            dataType: "jsonp",
            jsonpCallback:"json",
            success: function(json){
            	$('#jsonFileArea').html('');
           	 $('#jsonFileArea').html(JSON.stringify(json,null,'\t'));
            },
            error: function(data){
                alert('fail');
            }
        }); 
	})
	
	$('#clearQuery').on('click',function(){
		$('#jsonArea').html('');
		$('#jsonpArea').html('');
		$('#jsonFileArea').html('');
	})
})