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
var getcategoryUrl = '/sqmy/backend/getcategory';
function getCategory() {
    var time = new Date();
    $
        .getJSON(
            getcategoryUrl,
            function(data) {
                if (data.success) {
                    var num = 1;

                    var categoryHtml = '<tr><td>序号</td><td>类别名</td><td>操作</td></tr>';
                    data.categoryList
                        .map(function(item, index) {
                            categoryHtml += '<tr data-id="'
                                + item.id
                                + '"><td>'
                                + num++
                                + '</td><td>'
                                + item.categoryName
                                + '</td><td><button onclick="xgfc('
                                + item.id
                                + ',\''
                                + item.categoryName
                                + '\')">修改</button><button onclick="delCategory('
                                + item.id
                                + ')">删除</button></td></tr>';
                        });
                    $('#firstcategory').html(categoryHtml);

                }

            });
}
getCategory();
function delCategory(categoryid) {
    if (confirm('确定删除么')) {
        $.ajax({
            url : '/sqmy/backend/delCatery',
            type : "post",
            data : {
                categoryid : categoryid
            },
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    alert("删除成功");
                    window.location.href = '/sqmy/backend/categorymanage';
                }

            }

        });
    } else {
        window.location.href = '/sqmy/backend/categorymanage';
    }
}
function xgfc(categoryid, categoryName) {
    $('.caozuocategory').html(
        '类别名:<input type="text" id="categoryName" value="'
        + categoryName
        + '"><button  onclick="xiugaiCategory('
        + categoryid + ')">修改</button>');
}
function xiugaiCategory(categoryId) {
    var categoryName = $('#categoryName').val();
    $.ajax({
        url : '/sqmy/backend/xiugaifristCategory',
        type : "post",
        data : {
            categoryId : categoryId,
            categoryName : categoryName
        },
        dataType : "json",
        success : function(data) {
            if (data.success) {
                alert("提交成功");
                window.location.href = '/sqmy/backend/categorymanage';

            }

        }

    });
}
$('#addfirst')
    .on(
        "click",
        function() {
            $('.caozuocategory')
                .html(
                    '类别名:<input type="text" id="categoryName"/><button id="addfircate" onclick="addfircate();">增加</button>')
        });
function addfircate() {
    var categoryName = $('#categoryName').val();
    $.ajax({
        url : '/sqmy/backend/addcategory',
        type : "post",
        data : {
            categoryName : categoryName
        },
        dataType : 'json',
        success : function(data) {
            if (data.success) {
                alert("提交成功");
                window.location.href = '/sqmy/backend/categorymanage';
            } else {
                alert("输入为空");
            }
        }
    });

}