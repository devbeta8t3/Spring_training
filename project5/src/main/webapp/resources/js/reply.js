
/**
 * JavaScript의 준비 - 모듈화
 * 여러 기능들을 모아서 하나의 모듈화
 * 클로저를 이용해서 상태 유지
 * 여러 함수들이 메서드화 되므로 객체지향 구조에 적합
 */

console.log("Reply Module.............");

let replyService = (function(){
	
	function add(reply, callback){
		console.log("reply....................")
		
		$.ajax({
			
			type: 'post',
			url: '/replies/new',
			data: JSON.stringify(reply),
			contentType: "application/json: charset=utf-8",
			success: function(result, status, xhr){
						if (callback){
							callback(result);
						}
			},
			error: function(xhr, status, er){
						if (error) {
							error(er);
						}
			}
		})
	}
	//return {add:add};
	
	function getList(param, callback, error) {
		
		let bno = param.bno;
		let page = param.page || 1;
		
		// Ajax로 ReplyController 호출
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
					function(data) {
						if (callback) {
							callback(data);							// 댓글 목록만 가져오는 경우
							//callback(data.replyCnt, data.list);	// 댓글 숫자와 목록을 가져오는 경우(댓글 목록 페이징 처리)
						}
					}).fail(function(xhr, status, err) {
									if (error){
										error();
									}
					});
	}

	// 댓글 삭제와 갱신 (전송방식 DELETE)
	function remove(rno, callback, error) {
		
		$.ajax({
			type: 'delete',
			url: '/replies/' +rno,
			success: function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error();
				}
			}
		});
	}
	
	// 댓글 수정 (PUT)
	function update(reply, callback, error) {
		
		$.ajax({
			type: 'put',
			url: '/replies/' +reply.rno,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error();
				}
			}
		});
	}
	
	// 특정 댓글 조회
	function get(rno, callback, error) {
		
		$.get("/replies/" + rno + ".json", function(result) {
			if (callback) {
					callback(result);
				}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}
	
	// 시간 표기 변환, 24시간이 지나지 않으면 시분초 표시, 지나면 날짜 표시
	function displayTime(timeValue){
		let today = new Date();
		
		let gap = today.getTime() - timeValue;
		
		let dateObj = new Date(timeValue);
		let str = "";
		
		if(gap < (1000 * 60 * 60 * 24)){
			let hh = dateObj.getHours();
			let mi = dateObj.getMinutes();
			let ss = dateObj.getSeconds();
			
			return [(hh>9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
		}
		else{
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			let dd = dateObj.getDate();
			
			return [yy, '/', (mm > 9 ? '' : '0')+mm, '/', (dd > 9 ? '' : '0') + dd].join('');
		}
	}
	
	
	return {add:add, getList:getList, remove:remove, update:update, get:get, displayTime : displayTime};

})();