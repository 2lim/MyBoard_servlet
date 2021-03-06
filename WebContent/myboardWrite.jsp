<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
<script>
function goRegister(){
	var frm = document.noticeWrite;
		frm.action="<%=request.getContextPath()%>/myboardWrite.do";
		frm.method = "post";
		frm.submit();
	}
</script>
</head>

<body>
	<%-- <div class="register_res">
		<c:if test="${not empty register_fail}">
			<span>${register_fail}</span>
		</c:if>
	</div> --%>
	<div id="wrap">
		<div id="c_main">
<%-- 			<div id="c_nav">
				<h2 class="tit_c_nav">게시판</h2>
				<div class="inner_nav">
					<ul class="list_menu">
					<li><a href="<%=request.getContextPath()%>/noticeList.do">공지사항<span class="aside_arrow">></span></a></li>
					<li><a href="<%=request.getContextPath()%>/qna/faqList.jsp">자주하는질문<span class="aside_arrow">></span></a></li>
					<li><a href="<%=request.getContextPath()%>/qnaList.do">1:1문의<span class="aside_arrow">></span></a></li>
				</ul>
				</div>

				<a href="<%=request.getContextPath()%>/qnaList.do"
					class="link_inquire"> <span class="emph">도움이 필요하신가요 ?</span>
					1:1 문의하기
				</a>
			</div> --%>
			<div class="page_section">
				<div class="head_aticle">
					<h2 class="tit">
						게시판 <span class="tit_sub"> 게시판_글쓰기 </span>
					</h2>
				</div>
				<div class="CSq1_cont_board">
					<form name="noticeWrite">
						<table style="margin-top: 34px;">
							<tr>
								<td class="qna_title qna_box">제목</td>
								<td colspan="2" style="padding-left: 8px;">
								<input type="text" name="btitle" placeholder="제목을 입력해주세요."
									style="width: 640px; height: 30px; border: 1px solid #e8e8e8;">
								</td>
							</tr>
							<tr>
								<td class="qna_desc qna_box">내용</td>
								<td colspan="2" style="padding-left: 8px;"><textarea name="bcontent"
										style="resize: none; width: 640px; height: 350px; border: 1px solid #e8e8e8;"></textarea>
								</td>
							</tr>
						</table>
						<div class="subM">
							<button type="submit" onclick="goRegister();" class="b_button">등록하기</button>
						</div>
					</form>
				</div>
			</div>
			<p></p>
		</div>
	</div>
</body>
</html>