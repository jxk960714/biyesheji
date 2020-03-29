var pageIndex = $('#pageIndex').val();
function getFabu(){
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
getFabu();

$('#syy').on("click",function () {
     pageIndex = pageIndex * 1 - 1;
    if (pageIndex <= 0) {
        pageIndex = 1;
    }
   getFabu();
});
$('#yyy').on("click",function () {
     pageIndex = pageIndex * 1 + 1;
    getFabu();
});