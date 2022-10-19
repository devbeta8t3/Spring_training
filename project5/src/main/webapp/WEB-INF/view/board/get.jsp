<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<%@include file="../include/header.jsp"%>

<body class="bg-gradient-primary">

	<script type="text/javascript" src="/resources/js/reply.js"></script>
	<script type="text/javascript">
		$(function() {		// jQuery를 이용하여 form(post)의 버튼 동작을 구분하여 기능 실행
			
			const formObj = $("#operForm");
			
			$('button').on('click', function(e) {
				e.preventDefault();	// 원래의 동작 submit -> action 페이지를 무효화
				
				const operation = $(this).data("oper");
				
				console.log(operation);
				
				if (operation === 'remove') {
					formObj.attr("action", "/board/remove");
				}
				else if (operation === 'list') {
					//self.location = "/board/list";
					formObj.find("#bno").remove();
					formObj.attr("action", "/board/list");
				}
				
				formObj.submit();
			});
		});
		
		// replyService.add() 테스트
/* 		console.log("=====================");
		console.log("JS TEST");
		let bnoValue = '<c:out value="${board.bno}"/>';
		replyService.add(
				{reply:"JS TEST", replyer:"tester", bno:bnoValue},
				function(result){ alert("RESULT : " +result); }
		);
		 */
		
		// 댓글 목록 Test (완료)
/* 		$(function(){
			console.log(replyService);
		}); */
		
 		// 댓글 목록
		$(document).ready(function(){
			
			let bnoValue = '<c:out value = "${board.bno}"/>';
			let replyUL = $(".chat");
			
			showList(1);
			function showList(page){
				replyService.getList({bno:bnoValue, page:page || 1}, function(list){
					var str = "";
					if(list == null || list.list.length == 0){
						replyUL.html("<li>no reply</li>");
						
						return;
					}
					for(var i =0, len = list.list.length || 0 ; i<len; i++){
						str += "<li class  = 'list-group-item pb-0' data-rno = '"+list.list[i].rno+"'>";
						str += "	<div><div class = 'header'><strong class = 'primary-font'>"+list.list[i].replyer+"</strong>";
						str += "		<span class='float-right'><small class = 'text-muted'>"+replyService.displayTime(list.list[i].replyDate)+"</small></span></div>";
						str += "			<p>"+list.list[i].reply+"</p></div></li>";
					}
					replyUL.html(str);
				}); // end function
			} // end showList
		});

		
		// 댓글 추가
		//새로운 댓글을 추가하면 page값을 -1로 전송하고, 댓글의 전체 숫자를 파악한 후에 페이지 이동
		/* modalRegisterBtn.on("click",function(e){
			let reply = {
					reply: modalInputReply.val(),
					replyer: modalInputReplyer.val(),
					bno:bnoValue
			};
			
			replyService.add(reply, function(result){
				alert(result);
				modal.find("input").val("");
				modal.modal("hide");
				//showList(1);
				showList(-1);
			});
			
		}); */
		
		//댓글 조회 클릭 이벤트 처리
/* 		$(".chat").on("click", "li", function(e){
				
			let rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
				
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime( reply.replyDate)).attr("readonly","readonly");
				modal.data("rno", reply.rno);
				
				modal.find("button[id !='modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$(".modal").modal("show");
			});
		
		});
		 */
		//댓글의 수정/삭제 처리 이벤트
		//수정/삭제 처리는 Ajax로 하고, 모달창 close
		//수정/삭제 후에는 다시 최신 댓글 목록 갱신
/* 		modalModBtn.on("click", function(e){
			
			let reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			
			replyService.update(reply, function(result){
				
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		}); */
		
		
	</script>
	


	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-5">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">게시글 내용 조회</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">게시글 읽기 페이지</div>
							<!-- /.panel-heading -->
							
							<div class="panel-body">
								
								
								<div class="form-group">
									<label>Bno</label>
									<input class="form-control" name="bno" value="<c:out value='${board.bno}'/>" readonly="readonly">
								</div>
								
								<div class="form-group">
									<label>Title</label>
									<input class="form-control" name="title" value="<c:out value='${board.title}'/>" readonly="readonly">
								</div>
								
								<div class="form-group">
									<label>Text area</label>
									<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
								</div>
								
								<div class="form-group">
									<label>Writer</label>
									<input class="form-control" name="writer" value="<c:out value='${board.writer}'/>" readonly="readonly">
								</div>
								
								<%-- 
								<button data-oper="modify" class="btn btn-default" 
									onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'">
									Modify
								</button>
								<button data-oper="remove" class="btn btn-danger"
									onclick="location.href='/board/remove?bno=<c:out value="${board.bno }"/>'">
									Remove
								</button>
								<button data-oper="list" class="btn btn-info"
									onclick="location.href='/board/list'">
									List
								</button>
								 --%>
								
								<button type="submit" data-oper="modify" class="btn btn-outline-secondary">Modify</button>
								<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
								<button type="submit" data-oper="list" class="btn btn-info">List</button>
								
								<form id='operForm' action="/board/modify" method="get">
									<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}" />' />
								</form>
								
								<hr/>
							</div>
							<!-- end of panel-body -->
						</div>
						<!-- end of panel -->
						
						<!-- 댓글 패널 -->
						<div class="panel panel-default mt-3 ml-3">
							<div class="panel-heading">
								<span class="mt-3"><i class="fa fa-comments fa-fw"></i> 댓글</span>
								<button id="addReplyBtn" class="btn btn-primary btn-sm float-right">New Reply</button>
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body mt-2">
								<ul class="chat list-group">
									<!-- start reply -->
									<li class = "list-group-item" data-rno='20'>
										<div>
											<div class = "header">
												<strong class = "primary-font">user00</strong>
												<span class="float-right"><small class = "text-muted">2018-01-01 13:13</small></span>
											</div>
											<p>Good job!</p>
										</div>
									</li>
									<!-- end reply -->
								</ul>
								<!-- ./ end ul (chat) -->
							</div>
							<!-- end of panel-body -->
							<div class="panel-footer">
							</div>
							<!-- end of panel-footer -->
						</div>
						<!-- end of 댓글 패널 -->
					</div>
				</div>
			</div>
		</div>
	</div>



</body>

</html>