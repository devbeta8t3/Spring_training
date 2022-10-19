<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Main Content -->
<div id="content">

	<%@ include file="../include/content_topbar.jsp" %>

	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">Tables</h1>
		<p class="mb-4">
			DataTables is a third party plugin that is used to generate the demo
			table below. For more information about DataTables, please visit the
			<a target="_blank" href="https://datatables.net">official
				DataTables documentation</a>.
		</p>

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">DataTables
					Example</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>#번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>수정일</th>
							</tr>
						</thead>

						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}" /></td>
								<td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
										<c:out value="${board.title}" />
								</a></td>

								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.updateDate}" /></td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<!-- end of table-responsive -->
				
<%-- 
				<!-- Pagination -->
				<div class='pull-right'>
					​
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">​
							<li class="paginate_button previous"><a href="#">Previous</a></li>​
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">​
							<li class="paginate_button"><a href="#">${num}</a></li>​
						</c:forEach>
						​ ​
						<c:if test="${pageMaker.next}">​
							<li class="paginate_button next"><a href="#">Next</a></li>​
						</c:if>
					</ul>
				</div>
				<!-- end of Pagination -->

				<form id='actionForm' action="/board/list" method='get'>
					​<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>​
					<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>​
				</form>
				
				<script type="text/javascript">

					var actionForm = $("#actionForm");​
					
					$(".paginate_button a").on("click", function(e) {​
						e.preventDefault(); //기본 동작 제한      ​
						console.log(＇click＇);
						
						//<form>태그의 내용 변경후 submit​
						actionForm.find("input[name='pageNum']").val($(this).attr("href"));​
						actionForm.submit();​
					});
					​
				</script>
				 --%>​
				
			</div>
			<!-- end of card-body -->
		</div>
		<!-- end of card -->
	</div>
	<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->