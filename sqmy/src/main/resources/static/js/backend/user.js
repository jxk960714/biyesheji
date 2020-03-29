var   getUserUrl = '/sqmy/backend/getcategory';
function getUser() {
    var time = new Date();
    $
        .getJSON(
            getUserUrl,
            function(data) {
                if (data.success) {
                    var usernum = 1;
                    var userHtml = '<tr><td>序号</td><td>用户名</td><td>密码</td><td>职业</td><td>类型</td><td>操作</td></tr>';
                    data.userList
                        .map(function(item, index) {
                            userHtml += '<tr data-id="'
                                + item.userId
                                + '"><td>'
                                + usernum++
                                + '</td><td>'
                                + item.name
                                + '</td><td>'
                                + item.password
                                + '</td><td>'
                                + item.job
                                + '</td><td>'
                                + item.type
                                + '</td><td><button onclick="xguser('
                                + item.userid
                                + ',\''
                                + item.name
                                + '\',\''
                                + item.password
                                + '\',\''
                                + item.job
                                + '\',\''
                                + item.type
                                + '\')">修改用户</button><button onclick="delUser('
                                + item.userid
                                + ',\''
                                + item.role.roleId
                                + '\')">删除用户</button></td></tr>';
                        });


                    $('#userlist').html(userHtml);

                }

            });
}
getUser();
function delUser(userid, roleId) {
    if (roleId == 2) {
        alert("这是管理员你本身的账号 ,不能删除");
        return;
    }
    if (confirm('确定删除么')) {
        if (confirm('确定删除么')) {
            $.ajax({
                url : '/sqmy/backend/deluser',
                type : "post",
                data : {
                    userid : userid
                },
                dataType : "json",
                success : function(data) {
                    if (data.success) {
                        alert("删除成功");
                        window.location.href = '/sqmy/backend/usermanage';
                    }

                }

            });
        }
    } else {
        window.location.href = '/sqmy/backend/usermanage';
    }
}
function xguser(userid, name, password, job, type) {
    $('.caozuouser').html(
        '用户名:<input type="text" id="username" value="' + name
        + '">密码:<input  id="password"type="text" value="'
        + password + '">职业:<input id="job"type="text" value="'
        + job + '">类型:<input type="text" id="type"value="' + type
        + '"><button onclick="xiugaiuser(' + userid
        + ')">修改</button>')
}
function xiugaiuser(userid) {
    var username = $('#username').val();
    var password = $('#password').val();
    var job = $('#job').val();
    var type = $('#type').val();
    $.ajax({
        url : '/sqmy/backend/xiugaiuser',
        type : "post",
        data : {
            userid : userid,
            username : username,
            password : password,
            job : job,
            type : type

        },
        dataType : "json",
        success : function(data) {
            if (data.success) {
                alert("提交成功");
                window.location.href = '/sqmy/backend/usermanage';
            }
        }

    });
}
$('#adduser')
    .on(
        "click",
        function() {
            $('.caozuouser')
                .html(
                    '用户名:<input type="text" id="username">密码:<input type="password" id="password">职业:<input type="text" id="job" >类型:<input type="text"id="type"><button onclick="adduser();">增加</button>');
        });
function adduser() {
    var username=$('#username').val();
    var password = $('#password').val();
    var  job = $('#job').val();
    var type = $('#type').val();
    if(username==''||password==''){
        alert("用户名和密码不能为空")
        return;
    }
    var user={ "name":username, "password":password, "job":job,"type":type}
    $.ajax({
        url : '/sqmy/backend/addUser',
        data : JSON.stringify(user),
        contentType : 'application/json',
        dataType : "json",
        type : "post",
        success : function(data) {
            if (data.success) {
                alert("提交成功");
                window.location.href = '/sqmy/backend/usermanage';
            }
        }

    });
}