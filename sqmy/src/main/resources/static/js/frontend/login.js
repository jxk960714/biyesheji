$(function() {
	var loginCount = 0;
$('#denglu').click(function () {
	var name=$('#name').val();
	var password=$('#password').val();
	var verifyCodeActual = $('#j_captcha').val();
	var needVerify = false;
		if (!verifyCodeActual) {
			alert("请输入验证码");
			return;
		} else {
			needVerify = true;
		}
	
	$.ajax({
		url :'/sqmy/frontend/denglu',
		async : false,
		cache : false,
		type : "post",
		dataType : 'json',
		data:{name:name,
			password:password,
            verifyCodeActual : verifyCodeActual,
			needVerify : needVerify},
		success : function(data) {
			if(data.success){
				console.log(data.user.roleId);
				if(data.user.role.roleId==1){
					window.location.href='/sqmy/frontend/sqmylist';
				}else if(data.user.role.roleId==2){
					window.location.href='/sqmy/backend/manage';
				}
				alert("登录成功");
					
			}else{
				alert(data.errMsg);
				loginCount++;
				if (loginCount >= 3) {
					$('#verifyPart').show();
				}
			}
		}
	});
	
});
$('#zhuce').click(function () {
	var name=$('#name').val();
	var password=$('#password').val();
    var type=$("#type option:selected").text();
	var job =$("#joblist option:selected").text();
	var verifyCodeActual = $('#j_captcha').val();
	var needVerify = false;
	if (!verifyCodeActual) {
		alert("请输入验证码");
		return;
	} else {
		needVerify = true;
	}
	$.ajax({
		type : "post",
		dataType : 'json',
		url : '/sqmy/frontend/zhuce',
		data:{name:name,
			password:password,
			job:job,
			type:type,
			verifyCodeActual : verifyCodeActual,
			needVerify : needVerify},
		success : function(data) {
			if(data.success){
				alert("注册成功");
			}else{
				alert("注册失败");
			}
		}
	});
});
$('#fanhui').click(function(){
	window.location.href='/sqmy/frontend/login';
	
});
$('#tozhuce').click(function(){
	
	window.location.href = '/sqmy/frontend/register';
});
$('#tijiao').click(function () {
	var formData = new FormData($("#testform")[0]);
	var sport=$("#sport option:selected").val();
	alert(sport);
    //打印file 为对象
    $.ajax({
        url:'/sqmy/frontend/lianxi',
        data:formData,
        type: 'POST',
        dataType:'json',
        processData:false,
        contentType:false,
        success:function(data){
        	 DisplayListItems(data.list)	
		}
    });


});
function DisplayListItems(list) {
	var html = '';
    $.each(list, function(index, element) {
    	html +='<div>'+element+'</div>';
    	console.log(element);
    
    });
    $('#dd').html(html);
}
    $("#dept").change(function () {
        let value=$(this).find('option:selected').val();

    })

});


