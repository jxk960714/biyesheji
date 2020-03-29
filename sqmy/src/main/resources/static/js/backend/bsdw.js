var getbsdwUrl = '/sqmy/backend/getcategory';
function getBsdw() {
    var time = new Date();
    $
        .getJSON(
            getbsdwUrl,
            function(data) {
                if (data.success) {
                    var bsdwnum = 1;
                    var bsdwHtml = '<tr><td>序号</td><td>单位名称</td><td>单位编号</td><td>单位联系方式</td><td>单位邮件</td><td>操作</td></tr>';
                    var userHtml = '<tr><td>序号</td><td>用户名</td><td>密码</td><td>部门</td><td>类型</td><td>操作</td></tr>';

                    data.bsdwList
                        .map(function(item, index) {
                            bsdwHtml += '<tr data-id="'
                                + item.id
                                + '"><td>'
                                + bsdwnum++
                                + '</td><td>'
                                + item.name
                                + '</td><td>'
                                + item.number
                                + '</td><td>'
                                + item.phone
                                + '</td><td>'
                                + item.email
                                + '</td><td><button onclick="xgbs('
                                + item.id
                                + ',\''
                                + item.name
                                + '\',\''
                                + item.number
                                + '\',\''
                                + item.phone
                                + '\',\''
                                + item.email
                                + '\')">修改</button><button onclick="delDsdw('
                                + item.id
                                + ')">删除</button></td></tr>';

                        });


                    $('#baswlist').html(bsdwHtml);


                }

            });
}
getBsdw();
function delDsdw(bsdwId) {
    if (confirm('确定删除么')) {
        $.ajax({
            url : '/sqmy/backend/delbsdw',
            type : "post",
            data : {
                bsdwId : bsdwId
            },
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    alert("删除成功");
                    window.location.href = '/sqmy/backend/bsdwmanage';
                }

            }

        });
    } else {
        window.location.href = '/sqmy/backend/bsdwmanage';
    }

}
function xgbs(bsdwid, bsdwname, bsdwnum, bsdwphone, bsdwemail) {
    $('.caozuobs').html(
        '单位名称:<input type="text" id="bsdwname" value="' + bsdwname
        + '">编号:<input type="number" id="bsdwnum"value="' + bsdwnum
        + '">单位联系方式:<input type="text" id="bsdwphone" value="'
        + bsdwphone
        + '">单位邮件:<input type="text"id="bsdwemail" value="'
        + bsdwemail + '"><button onclick="xiugaibsdw(' + bsdwid
        + ')">修改</button>');
}
function xiugaibsdw(bsdwId) {
    var bsdwname = $('#bsdwname').val();
    var bsdwnum = $('#bsdwnum').val();
    var bsdwphone = $('#bsdwphone').val();
    var bsdwemail = $('#bsdwemail').val();
    $.ajax({
        url : '/sqmy/backend/xiugaibsdw',
        type : "post",
        data : {
            bsdwId : bsdwId,
            bsdwname : bsdwname,
            bsdwphone : bsdwphone,
            bsdwnum : bsdwnum,
            bsdwemail : bsdwemail
        },
        dataType : "json",
        success : function(data) {
            if (data.success) {
                alert("提交成功");
                window.location.href = '/sqmy/backend/bsdwmanage';

            }

        }

    });
}
$('#addbsdw')
    .on(
        "click",
        function() {
            $('.caozuobs')
                .html(
                    '单位名称:<input type="text" id="name">编号:<input type="number" id="number">单位联系方式:<input type="text" id="phone" >单位邮件:<input type="text"id="email"><button onclick="addbsdw();">增加</button>');
        });
function addbsdw() {
    var name = $('#name').val();
    var number = $('#number').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    $.ajax({
        url : '/sqmy/backend/addbsdw',
        type : "post",
        data : {
            name : name,
            number : number,
            phone : phone,
            email : email
        },
        dataType : "json",
        success : function(data) {
            if (data.success) {
                alert("提交成功");
                window.location.href = '/sqmy/backend/bsdwmanage';
            }
        }

    });
}