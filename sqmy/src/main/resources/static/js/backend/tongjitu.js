var namedata=[];
var count=[]
function getzhuxing() {
    $.getJSON("/sqmy/backend/zhuxing", function(json){
        zhuxing(json.data)


    });

}
function getzhexian() {
    $.getJSON("/sqmy/backend/zhexian", function(data){
        zhexian(data)
    });
}
getzhuxing();
getzhexian()

function zhexian(data) {
    var myChart = echarts.init(document.getElementById('zhexian'),'light');
    var option = {
        xAxis: {
            type: 'category',
            data: data.categorynameList
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: data.countList,
            type: 'line'
        }]
    };
    myChart.setOption(option);


}
function zhuxing(data) {
    var myChart = echarts.init(document.getElementById('zhuxing'),'light');
    /*指定图表的配置项和数据*/
    var option = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            /*// 坐标轴指示器，坐标轴触发有效*/
                type: 'shadow'        /*// 默认为直线，可选为：'line' | 'shadow'*/
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: data.usernameList,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                type: 'bar',
                name: '提交社情民意数量',
                type: 'bar',
                barWidth : 30,
                data: data.countList,
                barGap: '5%'
            },

        ]
    };

    myChart.setOption(option);
}


