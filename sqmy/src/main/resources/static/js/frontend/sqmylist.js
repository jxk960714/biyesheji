var title = '';
var categoryId='';
var userdata;
function showTime() {
	nowtime = new Date();
	year = nowtime.getFullYear();
	month = nowtime.getMonth() + 1;
	date = nowtime.getDate();
	document.getElementById("mytime").innerText = year + "年" + month + "月"
			+ date + " " + nowtime.toLocaleTimeString();
}
setInterval("showTime()", 1000);
function aaa() {
	var pageIndex = $('#pageindex').val();
	var url = '';
    if (title !=""&&categoryId==0) {


        url = '/sqmy/frontend/getsqmylist?pageIndex=' + pageIndex + '&title='+title + '&categoryid='+0;;
    } else if(categoryId!=0&&title==''){

        url = '/sqmy/frontend/getsqmylist?pageIndex=' + pageIndex + '&categoryid='+categoryId;
        + categoryId;
    } else if(title !=""&&categoryId!=0){

        url = '/sqmy/frontend/getsqmylist?pageIndex=' + pageIndex + '&title='+title+'&categoryid='+categoryId;
        + title;
    }else {
        url = '/sqmy/frontend/getsqmylist?pageIndex=' + pageIndex + '&categoryid='+0;

    }
	$
			.getJSON(
					url,
					function(data) {
						if (data.success) {
							userdata=data.user;
							var sqmylistHtml = '<tr><td>序号</td><td>主题</td><td>类别</td><td>紧急程度</td><td>反映人</td><td>查看</td><td>修改</td><td>删除</td><td>提交</td><td style="width: 100px">状态</td></tr>';
							var sqmycategotyHtml='';
                            data.categoryList.map(function (item,index){
                                sqmycategotyHtml+='<a href="#" onclick="bb('+item.id+');">'+item.categoryName+'</a>';
                            });
							data.sqmyList
									.map(function(item, index) {
										index=index+1;

										sqmylistHtml+='<tr><td>'+index+'</td><td>'+item.title+'</td><td>'+item.category.categoryName+'</td><td>'+sqmyjjcd(item.jjcd)+'</td><td>'+item.fyr+'</td><td><img src="../images/frontend/ck.png" width="30px" height="30px" onclick="chakan('+item.id+')"></td><td><img src="../images/frontend/修改.png"width="30px" height="30px"onclick="xiugai('+item.id+')"></td><td><img src="../images/frontend/del.png"width="30px" height="30px"onclick="del('+item.id+','+item.status+')" ></td><td><img id="fabu'+item.id+'"src="'+exchageimage(item.status)+'" width="30px" height="30px"onclick="tijiao('+item.id+')"></td><td>'+isFabu(item.isFaBu,item.status)+'</td></tr>';
									});

							$('#sqmylist').html(sqmylistHtml);
							$('.dropdown-content').html(sqmycategotyHtml);

							$("#currentPageNo").val(data.pageIndex);
							$("#currentPageNo").html(data.pageIndex);
							$("#max").val(data.max);
							$("#max").html(data.max);
							$("#totalCount").val(data.count);
							$("#totalCount").html(data.count);

						}

					});
}
function exchageimage(status) {
	if(status==0){
        return "../images/frontend/tijiao.png";
	}else {
        return "../images/frontend/ytj.png";
	}

}
function isFabu( isfabu,status) {
	if (status==0){
		return "还没提交"
	} else if(isfabu==0&&status==1){
		return "审核中"
	}else {
		return "已发布"
	}

}
function bb(categoryid) {
    categoryId=categoryid;
    aaa();
}
aaa();
function tijiao(sqmyid) {
	$.ajax({
		type :"get",
		data:{sqmyid:sqmyid},
		url:'/sqmy/frontend/fabu',
		success:(function(data){
			if(data=='提交成功'){
				alert(data);
                $('#fabu'+sqmyid).attr('src',"../images/frontend/ytj.png");
                window.location.href = '/sqmy/frontend/sqmylist';
			}else {
                alert(data);
			}

		})
	});
}

function del(id,status) {
    if(status==1){
        alert("你已经提交了，不能删除")
        return;
    }
	if (confirm('确定删除么')) {
	$.ajax({
		type : "post",
		url : '/sqmy/frontend/delsqmy',
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
			    alert("删除成功");
				window.location.href = '/sqmy/frontend/sqmylist';
			}

		}
	});
	}
}

$('#mymessage').click(function () {
    $('#my').html("<br/>用户名:"+userdata.name+"<br>工作："+userdata.job+"<br>工作类型："+userdata.type+"<br><a style='color: #11f40b' href='/sqmy/frontend/sqmylist'>确定</a><span style='color: #f1d46b' onclick='xgPassword()'><img src='../images/xgpassword.png' width='20px'>修改密码</span><span style='color: red' onclick='zhuxiao();'><img src='../images/frontend/zhuxiao.png' width='20px'>注销</span>");
});
function  zhuxiao() {
    $.ajax({
        type: "get",
        url: '/sqmy/frontend/tuichu',
        dataType: "json",
        success: function (data) {
            if (data) {
                window.location.href = '/sqmy/index';
            }

        }
    })
}
function xgPassword() {
	$('#xgmm').html('密码：<input type="password" id="password">再次输入密码：<input type="password" id="repassword"><button onclick="passwordxg()">修改</button>')
}
function passwordxg() {
	var userid=userdata.userid;
	var username=userdata.name;
	alert(username)
	var password=$('#password').val();
	var repassword=$('#repassword').val();
	if(password!=repassword){
		alert("两次密码输入不一样")
		return;
	}
    $.ajax({
        type : "post",
        url : '/sqmy/xgpassword',
        data : {
            "userid": userid,
			"username":username,
			"password" :password
        },
        dataType : "json",
        success : function(data) {
            if (data.success) {
                alert("修改成功");
                window.location.href = '/sqmy/frontend/sqmylist';
            }

        }
    });
}
function chakan(id) {
	window.location.href = '/sqmy/frontend/sqmyzhengwen?sqmyid=' + id;
}
function xiugai(id) {
	window.location.href = '/sqmy/frontend/sqmyedit?sqmyid=' + id;
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

$('#search').on('input', function(e) {
    title = e.target.value;
    title = title.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
	console.log(title)
	$('#sqmylist').empty();
	pageIndex = 1;
	aaa();

});
$("#lapage").on("click", function() {
	var pageIndex = $("#currentPageNo").val() * 1 + 1;
	if (pageIndex > $("#max").val()) {
		pageIndex = $("#max").val();
	}
	$("#pageindex").val(pageIndex);
	aaa();
})
$("#uppage").on("click", function() {
	var pageIndex = $("#currentPageNo").val() * 1 - 1;
	if (pageIndex <= 0) {
		pageIndex = 1;
	}
	$("#pageindex").val(pageIndex);
	aaa();
})

$("#minpage").on("click", function() {
	var pageIndex = 1;
	$("#pageindex").val(pageIndex);
	aaa();
})

$("#maxpage").on("click", function() {
	var pageIndex = $("#max").val();
	$("#pageindex").val(pageIndex);
	aaa();
})