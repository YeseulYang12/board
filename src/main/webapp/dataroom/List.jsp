<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/template/Top.jsp" />
<style>
	a{
		color:white;
	}
	a:hover{
		color:yellow;
	}

</style>
<div class="container" style="margin-top: 65px;">

	<div class="jumbotron ">
		<h1>게시판 글 목록</h1>
	</div>
	<div class="text-right mb-2">
		<a href="<c:url value="/DataRoom/Write.kosmo"/>" class="btn btn-danger">글작성</a>
	</div>
	<table class="table table-dark table-hover text-center">
		<thead>
			<tr>
				<th class="col-1">번호</th>
				<th>제목</th>
				<th class="col-2">작성자</th>
				<th class="col-1">조회수</th>
				<th class="col-2">작성일</th>
			</tr>
		</thead>
		<tbody class="table-sm down-file-body">
			<c:if test="${empty records }" var="isEmpty">
				<tr>
					<td colspan="6">등록된 글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not isEmpty }">
				<c:forEach var="record" items="${records}" varStatus="loop">
					<tr>
						<td>${record.no }</td>
						<td class="text-left">
							<a href="<c:url value="/DataRoom/View.kosmo?no=${record.no}&nowPage="/><c:out value="${param.nowPage}" default="1"/>">${record.title}</a></td>
						<td>${record.name}</td>
						<td id="down-count${loop.count}">${record.hitCount }</td>
						<td>${record.postDate}</td>
					</tr>	
				</c:forEach>		
			</c:if>
		</tbody>
	</table>
	<!-- 페이징 출력 -->
	${pagingString}
	
</div>
<jsp:include page="/template/Footer.jsp" />

