var namedata=[];
var count=[]
function getbingtu() {
    $.getJSON("/sqmy/backend/zhuxing", function(json) {
        bingtu(json.data)

    });
}
function getzhexian() {
    $.getJSON("/sqmy/backend/zhexian", function(data){
        zhexian(data)
    });
}
getbingtu();
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
function bingtu(data) {
   console.log(data)
    var myChart = echarts.init(document.getElementById('bingtu'),'light');
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data:data.usernameList,
        },
        series: [
            {
                name: '上传人',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: data.dataList,
            }
        ]
    };
    myChart.setOption(option);

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


}


