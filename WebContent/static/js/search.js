 var vm = new Vue({
	        el: '#search_set',
	        data: {
	        	
	        	Data_Type:'eQTL',
	        	Data_Types:['',''],
	        	Tissue_Name:'',
	        	Tissue_Names:['',''],
	        	
	        	hmm_subset:'TssA',
	        	hmm_subsets:['',''],
	        	hmm_set:'E001_TssA_ES-I3_Cells',
	        	hmm_sets:['',''],
	        	hmm_count:'22335',
	        	hmm_counts:['',''],
        },
        methods: {
        	
         
  		list_Data_Type:function(){
  			
        	var _self = this;
        	$.get("../search/Data_Type",function (_data) {
                _self.Data_Types = _data;
            }, "json");

	     },
	     
	    list_Tissue_Name:function(){
	     	
	      	 var _self = this;
	      	$.get("../search/Tissue_Name", {Data_Type:_self.Data_Type} ,function (_data) {
	              _self.Tissue_Names = _data;
	              _self.Tissue_Name = _self.Tissue_Names[0];
	          }, "json");
	      },

        }
    });

    vm.list_Data_Type();
    vm.list_Tissue_Name();
    
    
//#####################################################################    
    //########### 第一个search ###########
    $(document).ready(function(){
  	  $("#searchcheck1").click(function(){
  	  var textarea = $("#tissuetype").val();
  	  if(textarea == ""){
  	    alert("Error:Please select the Sub class!");
  	    return false;
  	    }
  	  });
  	 $("#example1").click(function(){
     	$("#genome1").find("option[value='hg38']").prop("selected",true);
  	 }); 
  	 $("#example2").click(function(){
     	document.getElementById('chr').value="chr1";
     	document.getElementById('start').value="149799529";
     	document.getElementById('end').value="149837416";
     	$("#genome2").find("option[value='hg38']").prop("selected",true);
  	 }); 
  	$("#example3").click(function(){
     	document.getElementById('gene').value="LINC00115";
     	$("#genome3").find("option[value='hg38']").prop("selected",true);
  	 }); 
  	$("#searchcheck2").click(function(){
    	  var chr = $("#chr").val();
    	  var start = $("#start").val();
    	  var end = $("#end").val();
    	  if(chr == ""){
    	    alert("Error:Please input the chr!");
    	    return false;
    	    }
    	  if(start == ""){
      	    alert("Error:Please input the start!");
      	    return false;
      	    }
    	  if(end == ""){
      	    alert("Error:Please input the end!");
      	    return false;
      	    }
    	  });
  	$("#searchcheck3").click(function(){
  	  var gene = $("#gene").val();
  	  if(gene == ""){
  	    alert("Error:Please input the gene!");
  	    return false;
  	    }
  	  
  	  });
    
    
	});

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('search1'));

    // 指定图表的配置项和数据
    var option = {
		title : {
		    text: 'Data set distribution',
		    x:'center'
		},
		tooltip : {
		    trigger: 'item',
		    formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
		    orient: 'vertical',
		    left: 'left',
		    data: ['ChromHMM','TF','TcoF','Histone','ATAC','Super_Enhancer','Enhancer','SNP','Methylation','LncRNA','mRNA']
		},
		series : [
		    {
		        name: 'Data class',
		        type: 'pie',
		        radius : '55%',
		        center: ['50%', '50%'],
		        data:[
		            {value:15, name:'ChromHMM'},
		            {value:70, name:'TF'},
		            {value:46, name:'TcoF'},
		            {value:33, name:'Histone'},
		            {value:85, name:'ATAC'},
		            {value:2, name:'Super_Enhancer'},
		            {value:2, name:'Enhancer'},
		            {value:6, name:'SNP'},
		            {value:6, name:'Methylation'},
		            {value:7, name:'LncRNA'},
		            {value:2, name:'mRNA'},
		        ],
		        itemStyle: {
		            emphasis: {
		                shadowBlur: 10,
		                shadowOffsetX: 0,
		                shadowColor: 'rgba(0, 0, 0, 0.5)'
		            }
		        },
		    
		    }
		]
		};


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
//    #########################################################################################
   
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('search2'));

    // 指定图表的配置项和数据
     var labelOption = {
                normal: {
                    show: true,
                    position: 'insideBottom',
                    rotate: 90,
                    textStyle: {
                        align: 'left',
                        verticalAlign: 'middle'
                    }
                }
            };
            option = {
            		title : {
        				text : 'The regions of the Class' ,
        				left: '35% '
        			},
            
                color: ['#003366', '#006699', '#4cabce', '#e5323e'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['Samples', 'TFs'],
                    bottom:'0%'
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true,title: "Mark"},
                     
                        magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled'],title: ['line', 'bar', 'stack', 'tiled']},
                        restore: {show: true,title: "Restore"},
                        saveAsImage: {show: true,title: "SaveAsImage"}
                    }
                },
                calculable: true,
                grid: {
			         left: '20% '
			    },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {show: false},
                        data: ["ChromHMM", "TF", "TcoF", "Histone", "ATAC", "Super_Enhancer", "Enhancer", "SNP", "Methylation", "LncRNA", "mRNA"],
						axisLabel: 
	                      {
	                        show: true,
	                        interval: '0',
	                        rotate: 30,
	                      },
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
                        barGap: 0,
                        label: labelOption,
                        data: [55502957, 109820576, 40401446, 175953536, 103928936, 6621919, 396631, 34986456, 52300833, 73119, 1420785]
                    },
                    
                ]
            }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
