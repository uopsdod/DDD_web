

	
 	var Taoyuan = ["桃園區","中壢區","平鎮區","龍潭區","楊梅區","新屋區","觀音區","龜山區","八德區","大溪區","大園區","蘆竹區","桃園區","復興區"];
	
 	var Taipie = ["中正區","大同區","中山區","松山區","大安區","萬華區","信義區","士林區","北投區","內湖區","南港區","文山區"];

 	var Xinbei = ["八里區","三芝區","三重區","三峽區","土城區","中和區","五股區","平溪區","永和區","石門區","石碇區","汐止區","坪林區","林口區","板橋區","金山區","泰山區","烏來區","貢寮區","淡水區","深坑區","新店區","新莊區","瑞芳區","萬里區","樹林區","雙溪區","蘆洲區","鶯歌區"];

 	var zone;	
	
	function doFirst(){

			var city = document.getElementById("city");
			city.addEventListener('change',showZone,false);	

			zone = document.getElementById("zone");	

	}	
	function showZone(){
			
			var city = document.getElementById("city").value;

		
			var num = 0;
			if (city == '桃園市'){
				
				zone.innerHTML = null;	
				        for(var i =0;i<Taoyuan.length;i++){
				        	var option = document.createElement("option");	
				        	option.value = Taoyuan[i];
				        	option.innerHTML = Taoyuan[i];
				        	zone.appendChild(option);			
				        }		
			}
			if (city == '台北市'){
				
				 zone.innerHTML = null;	
			        for(var i =0;i<Taipie.length;i++){
			        	var option = document.createElement("option");	
			        	option.value = Taipie[i];
			        	option.innerHTML = Taipie[i];
			        	zone.appendChild(option);			       
			        }	
			}
			if (city == '新北市'){			
				 zone.innerHTML = null;	
			        for(var i =0;i<Xinbei.length;i++){
			        	var option = document.createElement("option");	
			        	option.value = Xinbei[i];
			        	option.innerHTML = Xinbei[i];
			        	zone.appendChild(option);		       
			        }		
			}
						
	}	
 	window.addEventListener('load',doFirst,false);	



