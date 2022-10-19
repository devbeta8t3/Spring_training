<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<%@include file="../include/header.jsp"%>

<body class="bg-gradient-primary">

	<script type="text/javascript">
		$(function() {		// jQuery를 이용하여 form(post)의 버튼 동작을 구분하여 기능 실행
			
			const formObj = $("form");
			
			$('button').on('click', function(e) {
				e.preventDefault();	// 원래의 동작 submit -> action 페이지를 무효화
				
				const operation = $(this).data("oper");
				
				console.log(operation);
				
				if (operation === 'remove') {
					formObj.attr("action", "/board/remove");
				}
				else if (operation === 'list') {
					//self.location = "/board/list";
					formObj.attr("action", "/board/list").attr("method", "get");
					formObj.empty();
					//return;
				}
				
				formObj.submit();
			});
		});
	</script>

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-5">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">게시글 수정</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="pannel-heading">게시글 수정 페이지</div>
							<!-- /.panel-heading -->
							<div class="pannel-body">
								<form role="form" action="/board/modify" method="post">
									<div class="form-group">
										<label>Bno</label>
										<input class="form-control" name="bno" value="<c:out value='${board.bno}'/>" readonly="readonly">
									</div>
									
									<div class="form-group">
										<label>Title</label>
										<input class="form-control" name="title" value="<c:out value='${board.title}'/>">
									</div>
									
									<div class="form-group">
										<label>Text area</label>
										<textarea class="form-control" rows="3" name="content"><c:out value="${board.content}"/></textarea>
									</div>
									
									<div class="form-group">
										<label>Writer</label>
										<input class="form-control" name="writer" value="<c:out value='${board.writer}'/>" readonly="readonly">
									</div>
									
									<div class="form-group">
										<label>Register Date</label>
										<input class="form-control" name="regDate" 
												 value="<fmt:formatDate pattern="yyyy/MM/dd" value='${board.regdate}'/>"
												 readonly="readonly">
									</div>
									
									<div class="form-group">
										<label>Update Date</label>
										<input class="form-control" name="updateDate" 
												 value="<fmt:formatDate pattern="yyyy/MM/dd" value='${board.updateDate}'/>"
												 readonly="readonly">
									</div>
									
									<button type="submit" data-oper="modify" class="btn btn-outline-secondary">Modify</button>
									<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
									<button type="submit" data-oper="list" class="btn btn-info">List</button>
								</form>
							</div>
							<!-- end of pannel-body -->
						</div>
						<!-- end of pannel -->
					</div>
				</div>
			</div>
		</div>

	</div>



</body>

</html>