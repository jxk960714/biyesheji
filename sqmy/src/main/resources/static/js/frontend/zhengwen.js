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
					$('#fj').html('附件：<a href="/sqmy/upload/'+data.sqmy.fj+'" download="">'+sqmyfj(data.sqmy.fj)+'<img alt="" src="../resources/images/frontend/下载.png"  width="30px"><font color="red">下载</font></a>');
					$('#xxly').html("信息来源："+data.sqmy.xxly);
					$('#sqmytitle').html(data.sqmy.title)
                    $('#bz').html("备注："+data.sqmy.bz);
					$('#zhengwen').html(data.sqmy.zw);
					/*正文：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="content">'+item.zw+'</div></div><div>附件: <div class="content"><a href="/sqmy/upload/'+item.fj+'" download="">'+sqmyfj(item.fj)+'</a></div></div>*/
				} else {
					
				

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