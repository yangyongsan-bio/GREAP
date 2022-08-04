	var list = document.getElementById("tableport").getElementsByTagName("tr");

    function position(string1,string2,string3) {
		
	    document.getElementById("tableform").action="../analysis/position?chr="+string1+"&start="+string2+"&end="+string3;
	    document.getElementById("tableform").submit();
	    
	    var checked = document.getElementsByClassName("check");
		for (i = 1; i < list.length; i++) {
            document.getElementById("tableport").getElementsByTagName("tr")[i].getElementsByTagName("td")[1].style.backgroundColor = "#fff";
        }
		for(var i=0,j=1;i<list.length;i++,j++){
			if(checked[i].checked){
				document.getElementById("tableport").getElementsByTagName("tr")[j].getElementsByTagName("td")[1].style.backgroundColor="orange";
			}
		}
	    
    }

	
	
	
	
	
	
	
	
	