<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
<#if (a > b) >
<div id="main" style="width: 800px;height:300px;margin-left: -20%" ></div>
<#else>
<div id="main" style="width: 800px;height:300px;margin-left: -40%" ></div>
</#if>

    <script>

        var myChart = echarts.init(document.getElementById('main'), null, { renderer: 'svg' });

        var option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}: {c}"
            },
            toolbox: {
                show : true,
                feature : {
                    restore : {show: true,title: "Restore"},
                    saveAsImage : {show: true,title: "Save"}
                }
            },
            calculable : false,
            series : [
                {
                    name:'venn',
                    type:'venn',
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                textStyle: {
                                    fontFamily: 'Arial, Verdana, sans-serif',
                                    fontSize: 16,
                                    fontStyle: 'italic',
                                    fontWeight: 'bolder'
                                }
                            },
                            labelLine: {
                                show: false,
                                length: 10,
                                lineStyle: {
                                    // color: 各异,
                                    width: 1,
                                    type: 'solid'
                                }
                            }
                        },
                        emphasis: {
                            color: '#cc99cc',
                            borderWidth: 3,
                            borderColor: '#996699'
                        }
                    },
                    data:[
                        {value:${a}, name:'Input'},
                        {value:${b}, name:'${chartset}'},
                        {value:${c}, name:'Intersection'}
                    ]
                }
            ]
        };
                    
                    



        myChart.setOption(option);
    </script>   



   

</body>
</html>  


