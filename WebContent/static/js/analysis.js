 var vm = new Vue({
	        el: '#search_set',
	        data: {
	        	
	        	Data_Type:'ChromHMM',
	        	Data_Types:['',''],
	        	Tissue_Name:'',
	        	Tissue_Names:['',''],
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
	              _self.Tissue_Name = _data[0];
	          }, "json");
	      },
	      
       
          
        }
    });

    vm.list_Data_Type();
    vm.list_Tissue_Name();

//####################################################################################################################
function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){

        var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;

        var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;

        var openTip = oOpenTip || "";

        var shutTip = oShutTip || "";

        if(targetObj.style.display!="none"){

            if(shutAble) return;

            targetObj.style.display="none";
            document.getElementById(sourceObj.id).className="icicon ion-plus-circled btn-lg";

            if(openTip  &&  shutTip){

                sourceObj.innerHTML = shutTip;

            }

        } else {

            targetObj.style.display="block";
            document.getElementById(sourceObj.id).className="icon ion-minus-circled btn-lg";


            if(openTip  &&  shutTip){

                sourceObj.innerHTML = openTip;

            }

        }

    }

//##########################################################################################
 function sa(ab,ac,ad,ae)
 {
     var div=document.getElementById(ab);
     var chks=div.getElementsByClassName(ac);
     var pd=document.getElementsByClassName(ad);


     if(pd[0].checked)
     {
         for(var i=0;i<chks.length;i++)
         {
             chks[i].checked=true ;
             document.getElementById(ae).innerHTML=" "+(i+1)+" options selected";
         }

     }else
     {
         for(var i=0;i<chks.length;i++)
         {
             chks[i].checked=false ;
             document.getElementById(ae).innerHTML=" 0 options selected";
         }
     }







 }
//##########################################################################################
 function  CheckAll(self)
 {
     var its = document.getElementsByTagName("input");

     if(self.id=="1")
     {
         for(var t=0;t<its.length;t++ )
         {
             if(its[t].type=="checkbox")
             {
                 its[t].checked=true;
             }
         }
         
         self.id="2";
     }
     else
     {
         for(var t=0;t<its.length;t++ )
         {
             if(its[t].type=="checkbox")
             {
                 its[t].checked=false;
             }
         }

         
         
         self.id="1";
     }
 }

 $(document).ready(function () {

     var radios = $("input[name='me']");

     $("#input").click(function () {

         radios.eq(0).prop("checked", true);

         radios.eq(1).prop("checked", false);

     });

     $("#file").click(function () {

         radios.eq(0).prop("checked", false);

         radios.eq(1).prop("checked", true);

     });

 });
//##########################################################################################
 function doit(x,y)
 {
     var n=0;
     var objs = document.getElementsByClassName(x);
     for(var i=0; i<objs.length; i++)
     {
         if(objs[i].checked)
         {
             n++;
         }
     }
     document.getElementById(y).innerHTML=" "+n+" options selected";
 }