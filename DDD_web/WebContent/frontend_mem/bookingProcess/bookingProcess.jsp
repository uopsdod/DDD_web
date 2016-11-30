<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../indexHeader.jsp"%>

		<style type="text/css">
			.panel{
				background:#b0c4de;
			}
			*{
				font-family: Tahoma, Verdana, 微軟正黑體;
			}
			.checkbox-inline{
				margin-right: 1em;
			}
			.checkbox-inline + .checkbox-inline{
				margin-left: 0;
			}

			.list-group-item {
				font-size: xx-large;

			}

          .panel-default>.panel-heading {
		    color: #333;
		    background:#b0c4de;
		    
		    height:60px;
		    font-size: large;
		    width:1030px;
		  }

		  .panel-title a{
			font-size: large;
		  }

		</style>
	
      <center> <h1>訂房須知 <img src="<%=request.getContextPath()%>/frontend_mem/bookingProcess/images/question.png" /></h1></center>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-8 col-sm-offset-2"  >
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊1 -->
					 <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab1" >
					      <h4 class="panel-title">
					        <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
					         1.房價包括什麼？
					        </a>
					      </h4>
					    </div>
					    <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="tab1">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">房價包含各房型下所列設施，點選客房名稱即會顯示房價是否包含早餐或稅費等其他費用。您也可以查看住宿確認函或登入帳戶瞭解詳情。</a>
					     </div>
					    </div>
					  </div>
					  <!-- 區塊2 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab2">
					      <h4 class="panel-title">
					        <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
					         2.我可以使用信用卡預訂客房嗎？
					        </a>
					      </h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">大部分的旅館都有信用卡付費的機制，但本網站為避免信用卡扣款糾紛等問題，故付費方式以現場繳交為主。倘若未來使用信用卡的用戶居多，會在之後的版本增加此功能，造成不便請見諒!
             
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					  <!-- 區塊3 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab3">
					      <h4 class="panel-title">
					        <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
					      3.我如何能知道我的預訂已成功
					        </a>
					      </h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">完成預訂後您會看到確認頁面，我們也會發送確認函給您，
               住宿確認函包含所有訂單資訊、訂單編號及驗證碼。如果您需要與客服團隊聯繫，請提供訂單編號及驗證碼，同時您也可於線上查看確認函，登入帳號查看訂單詳情或修改預訂
                </a>
					      
					      </div>
					    </div>
					  </div>
					

				 <!-- 區塊4 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab4">
					      <h4 class="panel-title">
					        <a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd">
					        4.哪裡可以找到住宿的聯絡資訊？
					        </a>
					      </h4>
					    </div>
					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">預訂前，如果您對住宿方有疑問，請參見該住宿頁面。
                如果這些信息還不能解答您的疑問，請透過電話或電子郵件聯系我們的客戶服務團隊，我們會向住宿方轉達您的問題。
                預訂完成後，住宿方的聯系方式會顯示在您的預訂確認頁面和預訂確認郵件中，您也可以至管理預訂欄目中查看。
             
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					   <!-- 區塊5 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab5">
					      <h4 class="panel-title">
					        <a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="eee">
					        5.如何查詢住宿費用
					        </a>
					      </h4>
					    </div>
					    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab5">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">網站將列出可預訂的住宿，每筆搜尋結果旁均會標明住宿費用，
                因是特價房間，同一間住宿費用隨著時間不同亦將有所差異。
             
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					   <!-- 區塊6 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab6">
					      <h4 class="panel-title">
					        <a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="fff">
					         6.如何取消我的訂單？
					        </a>
					      </h4>
					    </div>
					    <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab6">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">您可以在\我的訂單\現有訂單\取消訂單，完成上述步驟 即可取消該筆訂單 ;
             
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					   <!-- 區塊7 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab7">
					      <h4 class="panel-title">
					        <a href="#ggg" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ggg">
					         7.我可以使用信用卡預訂客房嗎？
					        </a>
					      </h4>
					    </div>
					    <div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab7">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">大部分的旅館都有信用卡付費的機制，但本網站為避免信用卡扣款糾紛等問題，故付費方式以現場繳交為主。倘若未來使用信用卡的用戶居多，會在之後的版本增加此功能，造成不便請見諒!
             
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					   <!-- 區塊8 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab8">
					      <h4 class="panel-title">
					        <a href="#hhh" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="hhh">
					        8. 若我無法在預定時間內抵達，飯店會預留房間或是被設為黑名單嗎？
					        </a>
					      </h4>
					    </div>
					    <div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab8">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">若在三十分鐘內未辦理入住程序，便會將您的預訂取消。            因這是特價的房間，為了維護其他使用者與旅館業者的權益。
                恕不會幫您預留房間，請見諒! 
                倘若多次訂房卻逾時不到，會將您設為黑名單處理。
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					   <!-- 區塊9 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab9">
					      <h4 class="panel-title">
					        <a href="#iii" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="iii">
					        9.我該如何使用篩選條件
					        </a>
					      </h4>
					    </div>
					    <div id="iii" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab9">
					      <div class="list-group">
					      	<a href="#" class="list-group-item ">在搜尋住宿時如果想要縮小選擇範圍，您可以使用以下篩選條件
                "地區" ：選擇您想預訂的旅館所在地區 ，例如，台北市。
                "價格" ：選擇您想入住的房間價格區間。
                "人數" : 選擇您想入住的房間大小。
                "評分" :  選擇你想入住的旅館的評價分數。
                             </a>
					      	
					      </div>
					    </div>
					  </div>
					  </div>
				</div>
				</div>
				</div>
	

		<script>
            $(function(){
                $('[data-toggle="tooltip"]').tooltip();
	            $('[data-toggle="popover"]').popover();
            })
        </script>


<%@ include file="../indexFooter.jsp" %>