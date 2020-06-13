$(document).ready(function(){
	$('.delete-book').on('click',function({
		
		/*<![CDATA[*/
	    var path = /*[[${/}]]*/ 'remove';
	/*]]>*/
		
		var id=$(this).attr('id');
		bootbox.confirm({
		    message: "Are you sure to remove all selected books? It can't be undone.",
		    buttons: {
		        cancel: {
		            label: '<i class="fa fa-times"></i> Cancel'
		        },
		        confirm: {
		            label: '<i class="fa fa-check"></i> Confirm'
		        }
		    },
		    callback: function (confirmed) {
		    	if(confirmed){
		    		$.post(path, {'id':id}, function(res){
		    			location.reload();
		    		});
		    	}
		    }
		});
		
	});

	
	
	
	var bookIdList=[];
	#('.checkboxBook').click(function(){
		var id=$(this).attr('id');
	if(this.checked){
		bookIdList.push(id);
	}else
		{
		bookIdList.splice(bookIdList.indexOf(id), 1);
		}
	})
	$('#deleteSelected').click(function(){
			/*<![CDATA[*/
    var path = /*[[${/}]]*/ 'removeList';
        /*]]>*/
    
    buttons: {
        cancel: {
            label: '<i class="fa fa-times"></i> Cancel'
        },
        confirm: {
            label: '<i class="fa fa-check"></i> Confirm'
        }
    },
    callback: function (confirmed) {
    	if(confirmed){
    	 $.ajax({
    		 type:'POST',
    		 url:path,
    		 data:JSON.stringify(bookIdList),
    		 contentType:"application/json",
    		 scccess: function(res){console.log(res);location.reload()},
    		 error:function(res){console.log(res);location.reload();}
    	 }) ;
    	}
      }
	});
});

$("selectAllBooks".click(function(){
	if($(this).prop("checked")==true)
		{
		$(".checkedBox").click();
		}
	else if($(this).prop("checked")==false)
	{
	$(".checkedBox").click();
	}
});

});
