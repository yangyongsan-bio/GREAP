
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
    <script src="https://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="https://cdn.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://cdn.highcharts.com.cn/highcharts/modules/oldie.js"></script>
    <script src="https://cdn.highcharts.com.cn/highcharts/modules/venn.js"></script>
    
    <div id="container" style="width: 750px;height:400px;" ></div>


<script>
    Highcharts.chart('container', {
        series: [{
            type: 'venn',
            name: '',
            data: [{
                sets: ['Input'],
                value: ${a}
            }, {
                sets: ['${chartset}'],
                value: ${b}
            }, {
                sets: ['Input', '${chartset}'],
                value: ${c},
                name: 'Intersection'
            }]
        }],
        title: {
            text: ''
        }
    });
</script>   

</body>
</html>  


