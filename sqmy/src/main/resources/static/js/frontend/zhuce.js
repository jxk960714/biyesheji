$(function () {
    $("#type").change(function () {
        let value=$(this).find('option:selected').val();
        $.getJSON("/sqmy/frontend/getjob?typeid="+value, function(data){
            var jobHtml='<option selected="selected">请选择</option>';
            if (data.success){
                data.jobList.map(function (item,index) {
                    jobHtml+='<option>'+item.jobname+'</option>'
                })
                $('#joblist').html(jobHtml);
            }

        });
    })
});