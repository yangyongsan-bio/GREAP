<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Echarts 自定义系列</title>
</head>

<body>
<div id="echarts" style="height: 350px; width: 1100px;"></div>

<script type="text/javascript" src="${base.contextPath}/static/js/echarts/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${base.contextPath}/static/js/echarts/echarts.min.js"></script>

<script>
  $(document).ready(function () {

    let genes_start_ = [
      ${Input_Start},
      ${Db_Start}
    ];
    // 这个用于设置基因结束位置
    let genes_end_ = [
      ${Input_End},
      ${Db_End}
    ];

    // 获取基因数据标准化
    let data_handler = (start, end) => {
      // 区域
      let region = new Array(start.length);
      let genes_start = new Array(start.length);
      let genes_end = new Array(start.length);
      start.forEach((v, k) => {
        region[k] = new Array(v.length);
        genes_start[k] = new Array(v.length);
        genes_end[k] = new Array(v.length);
        v.forEach((m, i) => region[k][i] = end[k][i]/100000 - start[k][i]/100000);
      });
      // 深度降维，变成以为数组
      let region_flat = region.flat(Infinity);
      // 求和
      let sum = 0;
      region_flat.forEach(v => {
        sum += v * 1.0;
      });
      // 获取基因每个区域的平均值
      let region_average = Math.ceil(sum / region_flat.length);
      region.forEach((v, k) => {
        let start_middle = 0;
        let end_middle = 0;
        v.forEach((m, i) => {
          genes_start[k][i] = start[k][i]/100000+i*500;
          genes_end[k][i] = end[k][i]/100000+i*500;
        });
      });
      return [genes_start, genes_end];
    };
    let dataHandler = data_handler(genes_start_, genes_end_);
    let genes_start = dataHandler[0];
    let genes_end = dataHandler[1];
		console.log("genes_start="+genes_start[0][8]);
		console.log("genes_end="+genes_end);
    // 这个用于设置基因显示的颜色
    let getRandomColor = () => "#" + ("00000" + ((Math.random() * 16777215 + 0.5) >> 0).toString(16)).slice(-6);
    let color = [];
    for (let i = 0; i < genes_start.length; i++) {
      color[i] = [];
      for (let j = 0; j < genes_start[i].length; j++) {
        color[i][j] = getRandomColor();
      }
    }

    // 这个用于设置一栏条的高度
    let categories_height = 100;
    // 这个用于设置 基因/一栏条的高度 的比
    let gene_categories_percentage = 0.4;

    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById("echarts"));

    // 用于设置几个栏条
    let categories = ["User", "dbSet"];

    // 设置基因容器
    let data_gene = [];
    // 初始化基因
    echarts.util.each(categories, (category, index) => {
      for (let i = 0; i < genes_start[index].length; i++) {
        console.log(genes_end[index][i] - genes_start[index][i]);
        if (genes_end[index][i] - genes_start[index][i] <= 0) {
          console.log("error");
        }
        data_gene.push({
          // 基因名称
          name: "${chr}",
          // 基因的值
          value: [
            index, // 索引值
            genes_start[index][i], // 基因的开始位置
            genes_end[index][i], // 基因的结束位置
            genes_end[index][i] - genes_start[index][i] , // 基因的宽度
            genes_start_[index][i], // 基因的开始位置
            genes_end_[index][i], // 基因的结束位置
            genes_end_[index][i] - genes_start_[index][i] // 基因的宽度
          ],
          // 设置样式，颜色
          itemStyle: {
            normal: {
              color: color[index][i]
            }
          }
        });
      }
    });

    // 渲染基因
    let renderItem_gene = (params, api) => {
      // 得到类别索引
      let categoryIndex = api.value(0);
      // 得到开始位置
      let start = api.coord([api.value(1), categoryIndex]);
      // 得到结束位置
      let end = api.coord([api.value(2), categoryIndex]);
      // 设置基因高度
      let height = api.size([0, 1])[1] * gene_categories_percentage;

      // 渲染基因形状，矩形
      let rectShape = echarts.graphic.clipRectByRect({
        x: start[0]+2, // x 值：矩形左上角的位置
        y: start[1] - height / 3,  // y 值：矩形左上角的位置
        width: (end[0] - start[0])*20000, // 矩形的宽度
        height: height // 矩形的高度
      }, {
        x: params.coordSys.x,
        y: params.coordSys.y,
        width: params.coordSys.width,
        height: params.coordSys.height
      });

      return rectShape && {
        type: "rect",
        shape: rectShape,
        style: api.style()
      };
    };

    let option = {
      tooltip: {
        /* 防止提示框超出指定区域, 导致看不见全部信息 */
        confine: true,
        /* 自定义提示框的内容 */
        formatter: params => params.marker + params.name + ": " + params.value[4] + " - " + params.value[5] + " (" + params.value[6] + "bp)",
        /* 自定义提示框的背景颜色 */
        backgroundColor: "rgba(50,50,50,0.5)",
        /* 自定义提示框的边距颜色 */
        borderColor: "#333",
        /* 自定义提示框的内边距 */
        padding: [5, 10],
        /* 自定义提示框中的字体的颜色和大小 */
        textStyle: {
          color: "#fff",
          fontSize: 16
        }
      },
      toolbox: {
        show: true,
        right: 100,
        feature: {
          restore: {
            show: true,
            title: "Restore",
            iconStyle: {
              borderColor: "blue",
              borderWidth: 1
            },
            emphasis: {
              iconStyle: {
                borderColor: "#f9a300",
                borderWidth: 1
              }
            }
          },
          saveAsImage: {
            show: true,
            title: "Save as image",
            iconStyle: {
              borderColor: "blue",
              borderWidth: 1
            },
            emphasis: {
              iconStyle: {
                borderColor: "#f9a300",
                borderWidth: 1
              }
            }
          }
        }
      },
      /* 整体的高度: categories 的个数和 categorie_height 决定 */
      grid: {
        height: categories.length * categories_height
      },
      title: {
        text: "Overlap Chart",
        left: "center"
      },
      // 下面的伸缩条
      dataZoom: [{
        type: "slider",
        filterMode: "weakFilter",
        showDataShadow: false,
        top: (categories.length + 1.2) * categories_height,
        height: 10,
        borderColor: "transparent",
        backgroundColor: "#e2e2e2",
        handleIcon: "M10.7,11.9H9.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4h1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7v-1.2h6.6z M13.3,22H6.7v-1.2h6.6z M13.3,19.6H6.7v-1.2h6.6z", // jshint ignore:line
        handleSize: 20,
        handleStyle: {
          shadowBlur: 6,
          shadowOffsetX: 1,
          shadowOffsetY: 2,
          shadowColor: "#aaa"
        },
        labelFormatter: ""
      }, {
        type: "inside",
        filterMode: "weakFilter"
      }],
      // x 轴内容
      xAxis: {
        name: "${chr}",
        nameGap: 30,
        nameLocation: "middle",
        nameTextStyle: {
          fontSize: 18,
          fontWeight: "bolder"
        },
        /* 指定 x 轴的最小值 */
        min: Math.min.apply(null, genes_start.flat(Infinity)),
        /* 指定 x 轴的最大值 */
        max: Math.max.apply(null, genes_end.flat(Infinity))*1.1,
        scale: true,
        splitNumber: 8,
        axisLabel: {
          // formatter: val => val + " bp"
          formatter: _ => ""
        }
      },
      // y 轴内容
      yAxis: {
        data: categories
      },
      series: [{
        type: "custom",
        renderItem: renderItem_gene,
        itemStyle: {
          normal: {
            opacity: 0.8
          }
        },
        encode: {
          x: [1, 2],
          y: 0
        },
        z: 3,
        data: data_gene
      }]
    };
    myChart.setOption(option);
  });
</script>

</body>

</html>
