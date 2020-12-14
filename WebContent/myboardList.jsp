<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<title>게시판</title>
<script type="text/javascript">
function goSearch(){
	var frm = document.noticeSearch;
		frm.action="<%=request.getContextPath()%>/myboardSearch.do";
		frm.method = "post";
		frm.submit();
	};
</script>
</head>

<body>
	<c:if test="${not empty search_error }">
		<script>
			alert('${search_error}');
		</script>
	</c:if>
	<div id="c_main">
<%-- 		<div id="c_nav">
			<h2 class="tit_c_nav">고객센터</h2>
			<div class="inner_nav">
				<ul class="list_menu">
					<li><a href="<%=request.getContextPath()%>/noticeList.do">공지사항<span class="aside_arrow">></span></a></li>
					<li><a href="<%=request.getContextPath()%>/qna/faqList.jsp">자주하는질문<span class="aside_arrow">></span></a></li>
					<li><a href="<%=request.getContextPath()%>/qnaList.do">1:1문의<span class="aside_arrow">></span></a></li>
				</ul>
			</div>

			<a href="<%=request.getContextPath()%>/qnaList.do"
				class="link_inquire"> <span class="emph">도움이 필요하신가요 ?</span> 1:1
				문의하기
			</a>
		</div> --%>
		<div class="page_section">
			<div class="head_aticle">
				<h2 class="tit">
					게시판 <span class="tit_sub"> 게시판 리스트 </span>
				</h2>
			</div>
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td>
							<div class="section_t">
								<table width="100%" class="board-listheader" cellpadding="0"
									cellspacing="0">
									<thead>
										<tr>
											<th>No.</th>
											<th>제목</th>
											<th>등록일</th>
											<th>조회수</th>
										</tr>
									</thead>
									<tbody>
										<!--목록 뿌리기 -->
										<c:if test="${not empty myboardList}">
											<!--변경 : el태그 - servlet이랑 맞추기 -->
											<c:forEach items="${myboardList}" var="v" varStatus="s">
												<!--변경 : el태그 - servlet이랑 맞추기 -->
												<tr>
													<td>${v.bno}</td>
													<td><a
														href="myboardDetail.do?nno=${v.bno}&pageNum=${s.count}">${v.btitle}</a></td>
													<td><fmt:formatDate value="${v.bdate}"
															pattern="yyyy-MM-dd" var="date" /> ${date}</td>
													<td>${v.bview}</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<!--페이징 아래 숫자-->
			<div class="layout-pagination">
				<div class="pagediv">
					<c:if test="${startPage != 1}">
						<a href="<%=request.getContextPath()%>/myboardList.do?pageNum=${prev}"
							class="layout-pagination-button layout-back"></a>
					</c:if>
					<c:if test="${startPage != endPage}">
						<c:forEach varStatus="s" begin="${startPage}" end="${endPage}"
							step="1">

							<a href="<%=request.getContextPath()%>/myboardList.do?pageNum=${s.current}"
								class="layout-pagination-button">${s.current}</a>
							<!--변경 : href 경로 -->
						</c:forEach>
					</c:if>
					<c:if test="${next > pageCount}">
						<a href="<%=request.getContextPath()%>/myboardList.do?pageNum=${next}"
							class="layout-pagination-button layout-next">></a>
					</c:if>

				</div>
			</div>
		</div>

		<table class="board-search board-search2">
			<tbody>
				<tr>
					<td><c:if test="${not empty member}">
							<c:if test="${member.authority eq 1}">
								<div id="nWritebtn">
									<button type="button" id="nWrite" name="nWrite"
										onclick="location.href='<%=request.getContextPath()%>/myboardWrite.jsp'">글쓰기</button>
								</div>
							</c:if>
						</c:if></td>
					<td>
						<div class="search_bt">
							<form name="noticeSearch" action="noticeSearch.do">
								<input type="text" id="nSearch" name="nSearch"
									placeholder="검색어를 입력해주세요">
								<button type="button" id="nSearchbtn" name="nSearchbtn"
									onclick="goSearch()">
									<img src="<%=request.getContextPath()%>/imgs/Search_white.png"
										style="width: 15px; height: 15px;">
								</button>
							</form>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
