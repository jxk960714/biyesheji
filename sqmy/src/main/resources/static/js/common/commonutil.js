/**
 * 
 */
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}
function changeVerifyCode(img) {
	img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
}
function getpath(){
	return "/Oto/";
}
var toggle = true;
//导航栏按钮
$('.nav-list>li').hover(function(){
    var $ul=$(this).find('ul');
    var oW=$(this).width();//li
    var otrigW=$(this).find('.trig').width();
    var oNavListL=$('.nav-list').offset().left;
    var oTL=$(this).offset().left-oNavListL;//距离最左边的距离
    var oTR=$('.nav-list').width()-oTL-oW;//距离最右边的距离
    console.log(oNavListL+":"+oTL);

    if($ul.find('li').length>0){
        $('.second-bg').show();
        $(this).find('.trig').show();
        $ul.show();
        var sum=0;
        var oLeft=0;
        for(var i=0;i<$ul.find('li').length;i++){
            sum+=$ul.find('li').eq(i).width()+4;
        }
        $ul.width(sum);
        oLeft=(sum-oW)/2;
        if(oLeft>oTL){//到达左侧边界
            oLeft=oTL;
            $ul.css('left',-oLeft+'px');
            return ;
        }
        if(oLeft>oTR){
            $ul.css('right',-oTR+'px');
            return ;
        }
        $ul.css('left',-oLeft+'px');

    }
},function(){
    $('.second-bg').hide();
    $(this).find('ul').hide();
    $(this).find('.trig').hide();
});