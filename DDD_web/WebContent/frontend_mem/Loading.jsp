<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">

<head>
	<meta http-equiv="Refresh" content="2;url=<%=request.getContextPath()%>/frontend_mem/index.jsp">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/index.jpg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dua Dee Dou:晚鳥有優惠</title>
    <style type="text/css">
    .bg {
    	text-align: center;
		margin: auto;
        position: fixed;
        top: 40%;
        left: 0;
        bottom: 0;
        right: 0;
        z-index: -999;
            
    }    
    .bg img {

        min-height: 35%;
        width: 35%;
    }
    body{
    	background-color: #eaebed;
    }
    img{
    	text-align: center;
		margin: auto;
        position: fixed;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        z-index: 1;
    }
    #idbird{
    	text-align:center;
    	font-family: Tahoma, Verdana, 微軟正黑體;
    	position: fixed;
        top: 60%;
        left: 0;
        bottom: 0;
        right: 0;
        z-index: 2;
    }
    </style>
</head>

<body>
	<h2 id="idbird">Welcome Back ! Now Loading....</h2>
	<img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg" class="animsition">
    <div class="bg">
        <img src="<%=request.getContextPath()%>/frontend_mem/GIF/loading.gif">
       	<br>
       	
    </div>
    
</body>

</html>
