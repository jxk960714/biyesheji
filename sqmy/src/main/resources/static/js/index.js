var pageIndex = $('#pageIndex').val();
function getFabuList(){
    var fabuHtml='';
    $.getJSON("/sqmy/index/"+pageIndex, function(data){
        if(data.success){
            data.fabuList.map(function(item,index) {
                fabuHtml +=' <li><a href="/sqmy/getmessage/'+item.id+'">'+item.sqmyTitle+'</a><span class="fbsj">'+item.faBuTime+'</span></li>';
            });
            $('#fabulist').html(fabuHtml);
        }
    });

}
getFabuList();

$('#syy').on("click",function () {
     pageIndex = pageIndex * 1 - 1;
    if (pageIndex <= 0) {
        pageIndex = 1;
    }
   getFabuList();
});
$('#yyy').on("click",function () {
     pageIndex = pageIndex * 1 + 1;
    getFabuList();
});