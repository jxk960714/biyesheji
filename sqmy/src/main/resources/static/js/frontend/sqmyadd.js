$(function() {
	var getcategoryUrl = '/sqmy/frontend/getcategory';
	var getsecondcategoryUrl = '/sqmy/frontend/getsecondcategory';
	var addsqmyUrl='/sqmy/frontend/insertsqmy'
		$('#tijiao').click(function(){
	        var formData=new FormData();
			var title=$('#title').val();
            title = title.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
			var categoryId=$("#firstCategory option:selected").attr("data-id");
			/*var bsdwId=$('#bsdw option:selected').attr("data-id");*/
			var jjcd=$('#jjcd option:selected').val();
			var xxly=$('#xxly').val();
			var fyr=$('#fyr').val();
			var bz=$('#bz').val();
			var zw=$('#zw').val();
            zw = zw.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
			var fj=$("#fj")[0].files[0];
			if(title==''||zw==''){
                alert("主题和消息来源和正文不能为空")
                return;
            }
			formData.append('title', title);
			formData.append("categoryId",categoryId);
			formData.append("jjcd",jjcd);
			formData.append("fyr",fyr);
			formData.append("xxly",xxly);
			formData.append("bz",bz);
			formData.append("zw",zw);
			formData.append("fj",fj);
			/*formData.append("bsdwId",bsdwId);*/
			$.ajax({
				url :addsqmyUrl,
				type :'POST',
				data : formData,
				dateType:"json",
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						alert("保存成功");
						window.location.href="/sqmy/frontend/sqmylist";
					} else {
						alert("保存失败");
					
					}
				}
			});
			});	
		
		
	function getCategory() {
		$.get(getcategoryUrl,function(data,status){
		    if (data.success) {	
				var firstCategoryHtml = '';
				var DanweiHtml = '';
				data.categoryList.map(function(item, index) {
					firstCategoryHtml += '<option data-id="' + item.id
							+ '">' + item.categoryName + '</option>';
				});
				data.bsdwList.map(function(item, index) {
					DanweiHtml += '<option data-id="' + item.id + '">'
							+ item.name + '</option>';
				});

				$('#firstCategory').html(firstCategoryHtml);
				$('#bsdw').html(DanweiHtml);

			}
		});
	}
	getCategory();
	$('#goback').click(function () {
        window.location.href="/sqmy/frontend/sqmylist";
    })

});