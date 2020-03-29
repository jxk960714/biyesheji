$(function(){
	var sqmyid = getQueryString('sqmyid');
	var getinitinfoUrl='/sqmy/frontend/getinitinfo';
	function getinitinfo() {
		$.ajax({
			url :getinitinfoUrl,
			type : 'GET',
			data : {
				sqmyid:sqmyid
			},
			dateType : "json",
			success : function(data) {
				if (data.success) {
                    $('#fj').html('附件：<a href="/sqmy/upload/' + data.sqmy.fj + '" download="">' + sqmyfj(data.sqmy.fj) + '</a>');
                    $('#xxly').html("信息来源："+data.sqmy.xxly);
                    $('#sqmytitle').html(data.sqmy.title)
                    $('#bz').html("备注："+data.sqmy.bz);
                    $('#zhengwen').html(data.sqmy.zw);
                }
				 else {
					
				

				}
			}

		});
	}
	function sqmyfj(fj){
		if(fj==null){
			return "无"
		}else{
			return fj;
		}
		
	}
	getinitinfo();
})