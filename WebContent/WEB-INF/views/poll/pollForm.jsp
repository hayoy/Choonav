<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>pollForm page</title>
	<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/resources/startbootstrap/css/styles.css"rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	//필수항목 유효성 검사
	function check(){
		var stDate = document.getElementById("stDate");
		var endDate = document.getElementById("endDate");
		var pollTitle = document.getElementById("pollTitle");
		var target = document.getElementById("target");
		var ans1 = document.getElementById("ans1");
		var ans2 = document.getElementById("ans2");
		var stDate = document.getElementById("stDate");
		var endDate = document.getElementById("endDate");
		
		if(stDate.value==""){
			alert("시작일을 입력하세요");
			stDate.focus();
			return false;
		}
		if(endDate.value==""){
			alert("마감일을 입력하세요");
			endDate.focus();
			return false;
		}
		if(pollTitle.value==""){
			alert("투표 제목을 입력하세요");
			pollTitle.focus();
			return false;
		}
		if(target.value==""){
			alert("참여대상을 선택하세요");
			target.focus();
			return false;
		}
		if(ans1.value==""){
			alert("항목1을 입력하세요");
			ans1.focus();
			return false;
		}
		if(ans2.value==""){
			alert("항목2를 입력하세요");
			ans2.focus();
			return false;
		}
		if(endDate.value < stDate.value ){
			alert("시작날짜보다 큰 날짜를 선택해 주세요");
			endDate.focus();
			return false;
		}
	}// check 함수 여기까지	
	
	$(document).ready(function(){
		$("#endDate").change(function(){
			var startDate = $("#stDate").val();//stDate에 선택된 값 가져오기
			var endDate = $("#endDate").val();//endDate에 선택된 값 가져오기
			if(startDate > endDate){
				alert("시작일보다 큰 날짜를 선택해 주세요");
				$("#endDate").empty();
			}
		})
	})
	
	</script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="/include/top_nav_bar.jsp" />
	<div id="layoutSidenav">
		<jsp:include page="/include/left_nav_bar.jsp" />
		<div id="layoutSidenav_content">
			<div id="">
				<!--로그인된 세션이 없을경우 startPage 로 이동시켜주기  -->
				<div id=""> 
					<c:if test="${sessionScope.sid == null}">
					<script>
						alert("로그인후 이용할 수 있습니다");
						var link = "/cnav/main/startPage.cnav";
			    		window.location.href = link;
			    		</script>
					</c:if>
				</div>
				<!--투표폼 본문  -->
				<div id="">
				<h2>투표 만들기</h2><br/><br/><br/>
					<form action="/cnav/poll/pollPro.cnav" onsubmit="return check()" name="pF">
						<div id="">
						투표기간(필수) <input type="date" id="stDate" name="stDate" min=<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" /> > <strong>~</strong> <input type="date" id="endDate" name="endDate" ><br/>
						제목(필수) <input type="text" id="pollTitle" name="pollTitle"/><br/>
						대상(필수) &nbsp;
							<select id="target" name="target">
								<option value="전체" selected>전체</option>
								<option value="영업팀">마케팅팀</option>
								<option value="영업팀">영업팀</option>
								<option value="인사팀">인사팀</option>
								<option value="총무팀">총무팀</option>
								<option value="디자인팀">디자인팀</option>
								<option value="개발팀">개발팀</option>
								<option value="기획팀">기획팀</option>
							</select><br/>
						내용 <input type="text" id="pollCon" name="pollCon"/><br/>
						항목<br/>
						1(필수) :<input type="text" name="ans1"/><br/>
						2(필수) :<input type="text" name="ans2"/><br/>
						3 		:<input type="text" name="ans3"/><br/>
						4 		:<input type="text" name="ans4"/><br/><br/><br/>
							<!-- 유효성 검사 추가 -->
							<input type="submit" value="만들기" id="" >
							<input type="button" value="취소" id="" onClick="window.location='/cnav/poll/pollList.cnav'">
						</div>
					</form>
				</div>
			</div>
			<jsp:include page="/include/footer.jsp" />
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/resources/startbootstrap/js/scripts.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/resources/startbootstrap/assets/demo/chart-area-demo.js"></script>
		<script src="<%=request.getContextPath()%>/resources/startbootstrap/assets/demo/chart-bar-demo.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/resource/startbootstrap/js/datatables-simple-demo.js"></script>
	</div>
</body>
</body>
</html>