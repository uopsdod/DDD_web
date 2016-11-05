
var opacity =1;  //起始值 做遞減下面
var timerId;     //宣告timerId
var timerId1;
var count=1
var opacity=1;
var img1=["images/banner1.jpg","images/banner2.jpg","images/banner3.jpg"];
var img2=["images/banner4.jpg","images/banner5.jpg","images/banner6.jpg"];
var flag=1;

// function img(){
// 		var img=["images/back1.jpg","images/back2.jpg","images/back3.jpg","images/back4.jpg","images/back5.jpg"];
// 		if(count<=4){
// 			$("#img").fadeOut();
// 			$("#img").fadeOut("slow");
			
// 			document.getElementById('img').innerHTML="<img src='"+img[count]+"' width='100%' style='display:none' height='600' id='imgview' ;>";
// 			$("#img").fadeIn();
// 			$("#img").fadeIn("slow");
			
// 			document.getElementById('img').innerHTML="<img src='"+img[count]+"' width='100%' height='600' id='imgview' ;>";
// 		}else{
// 			count=0;
// 			$("#img").fadeOut();
// 			$("#img").fadeOut("slow");
						
// 			document.getElementById('img').innerHTML="<img src='"+img[count]+"' width='100%' style='display:none' height='600' id='imgview' ;>";
// 			$("#img").fadeIn();
// 			$("#img").fadeIn("slow");
			
// 			document.getElementById('img').innerHTML="<img src='"+img[count]+"' width='100%' height='600' id='imgview' ;>";

// 		}	
// 	count++;

// }
function banner(){
		if(flag==1){
			document.getElementById('dot').style='background:white';
			document.getElementById('dot1').style='background:lightgray';
			document.getElementById('banner').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img2[0]+"' width='100%' height='100%' ;></a>"
			document.getElementById('banner1').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img2[1]+"' width='100%' height='100%' ;></a>"
			document.getElementById('banner2').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img2[2]+"' width='100%' height='100%' ;></a>"
			flag+=1;
		}else{
			document.getElementById('dot1').style='background:white';
			document.getElementById('dot').style='background:lightgray';
			document.getElementById('banner').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[0]+"' width='100%' height='100%' ;></a>"
			document.getElementById('banner1').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[1]+"' width='100%' height='100%' ;></a>"
			document.getElementById('banner2').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[2]+"' width='100%' height='100%' ;></a>"
			flag-=1;
		}        	
}
function onclickLeft(){
	document.getElementById('dot1').style='background:white';
	document.getElementById('dot').style='background:lightgray';
	document.getElementById('banner').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[0]+"' width='100%' height='100%' ;></a>"
	document.getElementById('banner1').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[1]+"' width='100%' height='100%' ;></a>"
	document.getElementById('banner2').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[2]+"' width='100%' height='100%' ;></a>"
	clearInterval(timerId1);
}
function onclickRight(){
	document.getElementById('dot').style='background:white';
	document.getElementById('dot1').style='background:lightgray';
	document.getElementById('banner').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img2[0]+"' width='100%' height='100%' ;></a>"
	document.getElementById('banner1').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img2[1]+"' width='100%' height='100%' ;></a>"
	document.getElementById('banner2').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img2[2]+"' width='100%' height='100%' ;></a>"
	clearInterval(timerId1);
}

// function playOrPause(){//不試暫停中或結束
// 	if(!myMovie.paused && !myMovie.ended){ //影片正在跑的時候,如果按按鈕會暫停
// 		myMovie.pause(); //按一下執行pause暫停
// 	}
// 	else{
// 		myMovie.play();//播放中
// 	}
// }

function mouseenter(){
	document.getElementById('address').style='opacity:0.5';
	
}
function mouseout(){
	document.getElementById('address').style='opacity:1';
	
}

function mouseenter1(){
	document.getElementById('address1').style='opacity:0.5';
	
}
function mouseout1(){
	document.getElementById('address1').style='opacity:1';
	
}

function mouseenter2(){
	document.getElementById('address2').style='opacity:0.5';
	
}
function mouseout2(){
	document.getElementById('address2').style='opacity:1';
	
}

function mouseenter3(){
	document.getElementById('address3').style='opacity:0.5';
	
}
function mouseout3(){
	document.getElementById('address3').style='opacity:1';
	
}

function mouseenter4(){
	document.getElementById('address4').style='opacity:0.5';
	
}
function mouseout4(){
	document.getElementById('address4').style='opacity:1';
	
}

function mouseenter5(){
	document.getElementById('address5').style='opacity:0.5';
	
}
function mouseout5(){
	document.getElementById('address5').style='opacity:1';
	
}

function mouseenter6(){
	document.getElementById('address6').style='opacity:0.5';
	
}
function mouseout6(){
	document.getElementById('address6').style='opacity:1';
}
function mouseenter7(){
	document.getElementById('address7').style='opacity:0.5';
	
}
function mouseout7(){
	document.getElementById('address7').style='opacity:1';
}
function mouseenter8(){
	document.getElementById('address8').style='opacity:0.5';
	
}
function mouseout8(){
	document.getElementById('address8').style='opacity:1';
}

function btnColorChange(){
	document.getElementById("buttnOnimg").style='background:#ffffff;';
	document.getElementById("buttnOnimg").style='color:#000000';
}

function btnColorChange1(){
	document.getElementById("buttnOnimg").style='background:#0283df;';
	document.getElementById("buttnOnimg").style='color:#ffffff';
}

function load(){
	// document.getElementById('img').innerHTML="<img src='img/back1.jpg' width='100%' height='600' ;>";
	
	document.getElementById('address').onmouseover=mouseenter;
	document.getElementById('address1').onmouseover=mouseenter1;
	document.getElementById('address2').onmouseover=mouseenter2;
	document.getElementById('address3').onmouseover=mouseenter3;
	document.getElementById('address4').onmouseover=mouseenter4;
	document.getElementById('address5').onmouseover=mouseenter5;
	document.getElementById('address6').onmouseover=mouseenter6;
	document.getElementById('address7').onmouseover=mouseenter7;
	document.getElementById('address8').onmouseover=mouseenter8;

	document.getElementById('address').onmouseout=mouseout;
	document.getElementById('address1').onmouseout=mouseout1;
	document.getElementById('address2').onmouseout=mouseout2;
	document.getElementById('address3').onmouseout=mouseout3;
	document.getElementById('address4').onmouseout=mouseout4;
	document.getElementById('address5').onmouseout=mouseout5;
	document.getElementById('address6').onmouseout=mouseout6;
	document.getElementById('address7').onmouseout=mouseout7;
	document.getElementById('address8').onmouseout=mouseout8;
	
	document.getElementById('banner').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[0]+"' width='100%' height='100%' ;></a>"
	document.getElementById('banner1').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[1]+"' width='100%' height='100%' ;></a>"
	document.getElementById('banner2').innerHTML="<a href='https://goo.gl/I3Ti1G'><img src='"+img1[2]+"' width='100%' height='100%' ;></a>"
	document.getElementById('dot').style='background:lightgray';
	document.getElementById("btnOfLeft1").onclick=onclickLeft;
	document.getElementById("btnOfRight1").onclick=onclickRight;
	// document.getElementById("myMovie").onclick=playOrPause;
	document.getElementById("buttnOnimg").onmouseover=btnColorChange;
	document.getElementById("buttnOnimg").onmouseout=btnColorChange1;
	// timerId=setInterval(img,5000);
	timerId1=setInterval(banner,7000);

	$('#fade').cycle();//主頁圖 

	$('#slideshow').cycle({  
             fx: "scrollHorz" 
         });  
	 
    
   //  $("#datepicker1").datepicker({
			// 	showOn : "button",
			// 	buttonImage : "img/calendar.png",
			// 	buttonImageOnly : true,
			// 	minDate: -20,
			// 	maxDate: "+1M +10D"
			// });
   //  $("#datepicker2").datepicker({
			// 	showOn : "button",
			// 	buttonImage : "img/calendar.png",
			// 	buttonImageOnly : true,
			// 	minDate: -20,
			// 	maxDate: "+1M +10D"
			// });
    $(document).scroll(function(e) {
    var scrollTop = $(document).scrollTop();
    if (scrollTop > 10) {
        $('#top_header1').slideDown();
    } else {
        $('#top_header1').slideUp();

    }
});

    

    // $(".work").mouseenter(function(e){
    // 	$(this).find(".caption").animate({'opacity':1});		
    // });
    // $(".work").mouseout(function(e){
    // 	$(this).find(".caption").animate({'opacity':0});
    // });
  		    
}
window.onload=load;