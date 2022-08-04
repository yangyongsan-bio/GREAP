//############################## datatable ###################################################
$(document).ready(function(){
    var table = $('#tableport').DataTable( {
        dom: '<"pull-left"B>ft<"pull-left"i>p',
	    buttons: [
	               {
	                    text: '<span class="glyphicon glyphicon-save"><font>Export</font></span>',
	                    extend: 'csv'
	                }],
	    aLengthMenu : [5],
	    "scrollX" : true,
    } );
    })
    
//########################## 表里单选框切换变色 ###################################################
 
	    var list = document.getElementById("tableport").getElementsByTagName("tr");
		
		//body加载时触发，默认第一个
		document.getElementsByClassName("check")[0].checked = true;
		document.getElementById("tableport").getElementsByTagName("tr")[1].getElementsByTagName("td")[4].style.backgroundColor="orange";
	
		$('input[type=radio][name=check]').change(function() {
			var checked = document.getElementsByClassName("check");
			for (i = 1; i < list.length; i++) {
	            document.getElementById("tableport").getElementsByTagName("tr")[i].getElementsByTagName("td")[4].style.backgroundColor = "#fff";
	        }
			for(var i=0,j=1;i<list.length;i++,j++){
				if(checked[i].checked){
					document.getElementById("tableport").getElementsByTagName("tr")[j].getElementsByTagName("td")[4].style.backgroundColor="orange";
				}
			}
		});
   
//########################## 单选框切换传值  默认点击第一个###################################################	
	$(document).ready(function() {
		$("input[name='position']").eq(0).attr("checked","checked");
		document.getElementById("tableform").action="../analysis/position?chr="+$("#chr").eq(0).text()+"&start="+$("#start").eq(0).text()+"&end="+$("#end").eq(0).text();
        document.getElementById("tableform").submit();
		})
//########################## 单选框切换传值  点击进行传值###################################################	
    function position(string1,string2,string3) {
		
	    document.getElementById("tableform").action="../analysis/position?chr="+string1+"&start="+string2+"&end="+string3;
	    document.getElementById("tableform").submit();
	    
    }
//################################### overlap图切换染色体号触发  ###################################
//################################### overlap图默认染色体号  ###################################
	$(function() {
		let text = $("#chartchr").find("tr").eq(0).find("td").eq(1).text();
		$("#chart_chr option[value='"+text+"']").attr("selected","selected")
		//############## 默认触发  #########	
		document.getElementById("chartform").action="../analysis/set_detail_chart?chr="+$("#chart_chr").val()+"&datatype="+$("#datatype").text()+
		"&set="+$("#set").text();
	    document.getElementById("chartform").submit();
	})

//############## 点击触发  #########
	$("#chart_chr").change(function(){
		document.getElementById("chartform").action="../analysis/set_detail_chart?chr="+$("#chart_chr").val()+"&datatype="+$("#datatype").text()+
		"&set="+$("#set").text();
		
	    document.getElementById("chartform").submit();
		  
		});
	
	
	
	
	
	
	
	
	