<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script src="${base.contextPath}/static/js/echarts/echarts.min.js"></script>
	<script src="${base.contextPath}/static/js/echarts/dataTool.min.js"></script>
</head>
<body>
    <div id="main" style="width: 900px;height:400px;"></div>
 <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'), null, { renderer: 'svg' });

        
        var data=${qipao},
        option = {
            title: {
                text: '',
                subtext: ''
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
	        dataZoom: [
	          {
	            show: true,
	            yAxisIndex: [0],
	            realtime: true,
	            start: 95,
	            end: 100
	          }
	        ],
            toolbox: {
                show: true,
                feature: {
                    dataView: {
                        show: true,
                        title: "dataView",
                        readOnly: false,
                        lang: ['dataView', 'close', 'refresh']
                    },
		            magicType: {show: true, type: ['line', 'bar'],title: ['line', 'bar']},
		            restore: {show: true,title: "Restore"},
		            saveAsImage: {
		                type: "svg",
			            show: true,
			            title: "Save"
		            }
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                name: '-log10(P-value)',
                nameLocation:'center',
                nameTextStyle:{
					           padding: [5, 0, 0, 0],
					        },
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: ${classname},
				axisLabel: {
					interval: 0, //代表显示所有x轴标签显示
				}
            },
            series: [
                {
                  
                    type: 'scatter',
                    data: data,
                      
                    
                }
            ]
        };
        myChart.setOption(option);
    </script>
</body>
</html>