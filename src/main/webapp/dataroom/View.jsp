<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/template/Top.jsp" />
<style>
	a{color:black}
</style>
<div class="container" style="margin-top: 65px;">
	<div class="jumbotron">
		<h1>자료실 상세</h1>
	</div>
	<table class="table table-bordered">
		<tbody class="table-sm">
			<tr>
				<th class="w-25 bg-dark text-white text-center">번호</th>
				<td>${record.no}</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성자</th>
				<td>${record.name}</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">작성일</th>
				<td>${record.postDate}</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center" >조회수</th>
				<td id="down-count">${record.hitCount }</td>
			</tr>
			<tr>
				<th class="w-25 bg-dark text-white text-center">제목</th>
				<td>${record.title}</td>
			</tr>
			<tr>
				<th class="bg-dark text-white text-center" colspan="2">내 용</th>
			</tr>
			<tr>
				<td colspan="2">${record.content}</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 수정/삭제/목록 컨트롤 버튼 -->
	<div class="text-center">
		
		<a href="<c:url value="/DataRoom/Edit.kosmo?no=${record.no}"/>" class="btn btn-success" >수정</a> 
		<a href="#" class="btn btn-success" >삭제</a>		
		<a href="<c:url value="/DataRoom/List.kosmo?nowPage=${param.nowPage}"/>" class="btn btn-success">목록</a>
	</div>
</div>
<jsp:include page="/template/Footer.jsp" />

