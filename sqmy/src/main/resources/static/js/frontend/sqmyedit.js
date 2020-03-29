$(function() {
	var jjdu='';
	var getcategoryUrl = '/sqmy/frontend/getcategory';
	var getsecondcategoryUrl = '/sqmy/frontend/getsecondcategory';
	var getinitinfoUrl = '/sqmy/frontend/getinitinfo';
	var xiugaisqmyUrl = '/sqmy/frontend/xiugaisqmy'
	var sqmyid = getQueryString('sqmyid');
	$('#tijiao').click(
			function() {
				var title = $('#title').val();
                title = title.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
				var categoryId = $("#category option:selected").attr(
						"data-id");
			/*	var bsdwId = $('#bsdw option:selected').attr("data-id");*/
				var jjcd = $('#jjcd option:selected').val();
				var xxly = $('#xxly').val();
				var fyr = $('#fyr').val();
				var bz = $('#bz').val();
				var zw = $('#zw').val();
                zw = zw.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
				var fj = $("#fj")[0].files[0];
                if(title==''||xxly==''||zw==''){
                    alert("主题和消息来源和正文不能为空")
                    return;
                }
				var formData = new FormData();
				formData.append('title', title);
				formData.append("categoryId", categoryId);
				formData.append("jjcd", jjcd);
				formData.append("fyr", fyr);
				formData.append("xxly", xxly);
				formData.append("bz", bz);
				formData.append("zw", zw);
				formData.append("fj", fj);
				/*formData.append("bsdwId", bsdwId);*/
				formData.append("sqmyid", sqmyid);
				$.ajax({
					url : xiugaisqmyUrl,
					type : 'POST',
					data : formData,
					dateType : "json",
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							alert("修改成功");
							window.location.href = "/sqmy/frontend/sqmylist";
						} else {
							alert("修改失败");

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

				$('#category').html(firstCategoryHtml);
				$('#bsdw').html(DanweiHtml);

			}
		});
	}
	function getinitinfo() {
		$.ajax({
			url : getinitinfoUrl,
			type : 'get',
			data : {
				sqmyid : sqmyid
			},
			dateType : "json",
			success : function(data) {
				if (data.success) {
					$('#title').val(data.sqmy.title);
					var xxly = $('#xxly').val(data.sqmy.xxly);
					var fyr = $('#fyr').val(data.sqmy.fyr);
					var bz = $('#bz').val(data.sqmy.bz);
					var zw = $('#zw').val(data.sqmy.zw);
                    $('#category').val(data.sqmy.category.categoryName);
					$('#jjcd').val(sqmyjjcd(data.sqmy.jjcd))
				} else {
				}
			}

		});
	}
    function sqmyjjcd(jjcd) {
        if (jjcd == 1) {
            return "一级";
        } else if (jjcd == 2) {
            return '二级';
        } else {
            return "三级";
        }

    }
	getCategory();
	getinitinfo();

    $('#goback').click(function () {
        window.location.href="/sqmy/frontend/sqmylist";
    })
});