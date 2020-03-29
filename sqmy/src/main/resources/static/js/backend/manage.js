var title = "";
var toggle = true;
var categoryId=0;
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
function showTime() {
    nowtime = new Date();
    year = nowtime.getFullYear();
    month = nowtime.getMonth() + 1;
    date = nowtime.getDate();
    document.getElementById("mytime").innerText = year + "年" + month + "月" + date + " " + nowtime.toLocaleTimeString();
}

setInterval("showTime()", 1000);
function getcategory() {
    $.getJSON("/sqmy/frontend/getcategory", function(data){
        var sqmycategotyHtml='';
        data.categoryList.map(function (item,index){
            sqmycategotyHtml+='<a href="#" onclick="bb('+item.id+');">'+item.categoryName+'</a>';
        });
        $('#categorymenu').html(sqmycategotyHtml);
    });
}
getcategory();
function aaa() {
    var pageIndex = $('#pageindex').val();
    var url = '';
    if (title !=""&&categoryId==0) {


        url = '/sqmy/backend/getsqmylist?pageIndex=' + pageIndex + '&title='+title + '&categoryid='+0;;
    } else if(categoryId!=0&&title==''){

        url = '/sqmy/backend/getsqmylist?pageIndex=' + pageIndex + '&categoryid='+categoryId;
        + categoryId;
    } else if(title !=""&&categoryId!=0){

        url = '/sqmy/backend/getsqmylist?pageIndex=' + pageIndex + '&title='+title+'&categoryid='+categoryId;
        + title;
    }else {
        url = '/sqmy/backend/getsqmylist?pageIndex=' + pageIndex + '&categoryid='+0;

    }
    $
        .getJSON(
            url,
            function(data) {
                if (data.success) {
                var sqmylistHtml = '<tr><td>序号</td><td>主题</td><td>类别</td><td>紧急程度</td><td>反映人</td><td>提交时间</td><td>提交人</td><td>查看</td><td>发布</td></tr>';
                data.sqmyList
                    .map(function (item, index) {
                        index = index + 1;
                        sqmylistHtml += '<tr><td>' + index + '</td><td>' + item.title + '</td><td>' + item.category.categoryName + '</td><td>' + sqmyjjcd(item.jjcd) + '</td><td>' + item.fyr + '</td><td>'+item.tjtime+'</td><td>'+item.user.name+'</td><td><img src="../images/frontend/ck.png" width="30px" height="30px" onclick="chakan('+item.id+')"></td></td><td><img alt="" id="fabu'+item.id+'" src="'+exchageimages(item.isFaBu)+'" width="30px" height="30px" onclick="fabusqmy('
                            + item.id
                            + ',\''
                            + item.category.categoryName
                            + '\',\''
                            + item.title
                            + '\',\''
                            + item.jjcd
                            + '\',\''
                            + item.keyWord
                            + '\',\''
                            + item.xxly
                            + '\',\''
                            + item.zw
                            + '\')"></td></tr>';
                    });

                $('#sqmylist').html(sqmylistHtml);
                $("#currentPageNo").val(data.pageIndex);
                $("#currentPageNo").html(data.pageIndex);
                $("#max").val(data.max);
                $("#max").html(data.max);
                $("#totalCount").val(data.count);
                $("#totalCount").html(data.count);


            }

        });

}
function chakan(id) {
    window.location.href = '/sqmy/backend/sqmyzhengwen?sqmyid=' + id;
}
function bb(categoryid) {
    categoryId=categoryid;
    aaa();
}
function exchageimages(isfabu) {
    if(isfabu==0){
        console.log(status);
        return "../images/backend/fb.png";
    }else {
        return "../images/backend/yfb.png";
    }

}
function fabusqmy(id,sqmycategory,sqmytitle,sqmyjjcd,keyword,xxly,sqmyzw) {
    var sqmy={"sqmyCategory":sqmycategory,"sqmyTitle":sqmytitle,"sqmyJjcd":sqmyjjcd,"sqmyZw":sqmyzw ,"keyWord":keyword,"xxly":xxly};
    $.ajax({
        type: "POST",
        url: "/sqmy/backend/fabu",
        data: JSON.stringify(sqmy),
        dataType:"json",
        contentType:"application/json",
        success: function(data){
            if(data.success){
                alert(data.msg);
                $('#fabu'+id).attr('src',"../images/backend/yfb.png");

            }else {
                alert(data.msg)
            }

        }
    });

}


$('#tuichu').click(function () {

    $.ajax({
        type: "get",
        url: '/sqmy/frontend/tuichu',
        dataType: "json",
        success: function (data) {
            if (data) {
                window.location.href = '/sqmy/frontend/login';
            }

        }
    });
});

function sqmyjjcd(jjcd) {
    if (jjcd == 1) {
        return "一级";
    } else if (jjcd == 2) {
        return '二级';
    } else {
        return "三级";
    }

}

aaa();
$('#search').on('input', function (e) {
    title = e.target.value;
    title = title.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');
    $('#sqmylist').empty();
    pageIndex = 1;
    aaa();

});
$("#lapage").on("click", function () {
    var pageIndex = $("#currentPageNo").val() * 1 + 1;
    if (pageIndex > $("#max").val()) {
        pageIndex = $("#max").val();
    }
    $("#pageindex").val(pageIndex);
    aaa();
})
$("#uppage").on("click", function () {
    var pageIndex = $("#currentPageNo").val() * 1 - 1;
    if (pageIndex <= 0) {
        pageIndex = 1;
    }
    $("#pageindex").val(pageIndex);
    aaa();
})

$("#minpage").on("click", function () {
    var pageIndex = 1;
    $("#pageindex").val(pageIndex);
    aaa();
})

$("#maxpage").on("click", function () {
    var pageIndex = $("#max").val();
    $("#pageindex").val(pageIndex);
    aaa();
})





