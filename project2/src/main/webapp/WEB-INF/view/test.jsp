<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>model ${data1 }</h1>
</body>
</html>
<!-- 
		/test.jsp 로 실행하면 data1은 출력되지 않는다.
		/test 로 실행하면 data1이 출력된다. 
		
		test.jsp의 직접 접근을 막기위해 webapp 폴더에서 WEB-INF 폴더로 jsp 파일을 이동하자.
		서버 내부 영역이므로 브라우저는 접근 불가. 컨트롤러는 접근 가능.
-->