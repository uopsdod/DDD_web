
//var save = [];

function doFirst(){
	document.getElementById('upfile1').onchange = fileChange1;
	document.getElementById('upfile2').onchange = fileChange2;

		
}
function fileChange1(){
	
	var file = document.getElementById('upfile1').files[0];
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.onload = function(){		
	//	var newURL= readFile.result
		var image = document.getElementById('image1');
		image.src = readFile.result;
		image.width = 350;
		//SaveImage(newURL);	
	};	
}
function fileChange2(){
	
	var file = document.getElementById('upfile2').files[0];
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.onload = function(){		
	//	var newURL= readFile.result
		var image = document.getElementById('image2');
		image.src = readFile.result;
		image.width = 350;
		//SaveImage(newURL);	
	};	
}
function fileChange3(){
	
	var file = document.getElementById('upfile3').files[0];
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.onload = function(){		
	//	var newURL= readFile.result
		var image = document.getElementById('image3');
		image.src = readFile.result;
		image.width = 350;
		//SaveImage(newURL);	
	};	
}
function fileChange4(){
	
	var file = document.getElementById('upfile4').files[0];
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.onload = function(){		
	//	var newURL= readFile.result
		var image = document.getElementById('image4');
		image.src = readFile.result;
		image.width = 350;
		//SaveImage(newURL);	
	};	
}
// function SaveImage(newURL){
	// console.log(newURL);
	// 操作HTML標籤本身的屬性width
	// 操作CSS的屬性width
	// image.style.width = '350px';
		// var img = document.createElement('img');
			// img.src =  newURL;
			// img.width = 350;
			// document.getElementById('image').appendChild(img);
			// save.push(newURL);	
// }



window.addEventListener('load',doFirst,false);