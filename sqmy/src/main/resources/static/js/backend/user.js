var   getUserUrl = '/sqmy/backend/getcategory';
function getUser() {
    var time = new Date();
    $
        .getJSON(
            getUserUrl,
            function(data) {
                if (data.success) {
                    var usernum = 1;
                    var userHtml = '<tr><td>序号</td><td>用户名</td><td>密码</td><td>实际职业</td><td>职业类型</td><td>用户类型</td><td>操作</td></tr>';
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
                                + '</td><td>'
                                + item.role.roloName
                                + '</td><td><button onclick="xguser('
                                + item.userid+','+item.role.roleId
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
        alert("这是管理员的账号 ,不能删除");
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
function xguser(userid,roleId ,name, password, job, type ) {
    if(roleId==2){
        alert("管理员的账号不能修改");
        return;
    }
    $('.caozuouser').html(
        '用户名:<input type="text" id="username" value="' + name
        + '">密码:<input  id="password"type="text" value="'
        + password + '">职业类型:<select id="type" onchange="jobtype();"><option selected="selected">'+type+'</option><option value="1">技能型</option><option value="2">事务型</option><option value="3">研究型</option><option value="4">艺术型</option><option value="5">经管型</option> <option value="6">社交型</option></select><label >实际职业</label><select id="joblist"><option selected="selected">'+job+'</option></select><label>用户类型</label><select id="usertype"><option>请选择</option><option value="1">普通用户</option><option value="2">管理员</option></select><button onclick="xiugaiuser(' + userid
        + ')">修改</button>')
}
function xiugaiuser(userid) {
    var username = $('#username').val();
    var password = $('#password').val();
    var  job =$("#joblist").find('option:selected').text();
    var  type =$("#type").find('option:selected').text();
    var roleId=$("#usertype").find('option:selected').val();
    $.ajax({
        url : '/sqmy/backend/xiugaiuser',
        type : "post",
        data : {
            userid : userid,
            username : username,
            password : password,
            job : job,
            type : type,
            roleId :roleId
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
                    '用户名:<input type="text" id="username">密码:<input type="password" id="password">职业类型:<select id="type" onchange="jobtype();"><option selected="selected">请选择</option><option value="1">技能型</option><option value="2">事务型</option><option value="3">研究型</option><option value="4">艺术型</option><option value="5">经管型</option> <option value="6">社交型</option></select><label >实际职业</label><select id="joblist"><option selected="selected">请选择</option></select><label>用户类型</label><select id="usertype"><option>请选择</option><option value="1">普通用户</option><option value="2">管理员</option></select><button onclick="adduser();">增加</button>');
        });
    function jobtype(){
        let value=$("#type").find('option:selected').val();
        $.getJSON("/sqmy/frontend/getjob?typeid="+value, function(data){
            var jobHtml='<option selected="selected">请选择</option>';
            if (data.success){
                data.jobList.map(function (item,index) {
                    jobHtml+='<option>'+item.jobname+'</option>'
                })
                $('#joblist').html(jobHtml);
            }

        });
    }

function adduser() {
    var username=$('#username').val();
    var password = $('#password').val();
    var  job =$("#joblist").find('option:selected').text();
    var  type =$("#type").find('option:selected').text();
    var roleId=$("#usertype").find('option:selected').val();
    if(username==''||password==''){
        alert("用户名和密码不能为空")
        return;
    }
    var user={ "username":username, "password":password,"job":job,"type":type ,"roleId":roleId}
    $.ajax({
        url : '/sqmy/backend/addUser',
        data :user,
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