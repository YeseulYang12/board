<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 65px;">

	<div class="jumbotron">
		<h1>게시판 수정 <small>글을 수정하세요</small></h1>
	</div>
  
	<form method="post" action="<c:url value="/DataRoom/Edit.kosmo"/>">
		<input type="hidden" name="no" value="${record.no}"/>
		<input type="hidden" name="nowPage" value="${param.nowPage}"/>
		
		<div class="form-group">
			<label><kbd class="lead">제목</kbd></label> <input type="text"
				class="form-control" placeholder="제목을 입력하세요" name="title" value="${record.title}">
		</div>
		<div class="form-group">
			<label><kbd class="lead">내용</kbd></label>
			<textarea class="form-control" rows="5" name="content">${record.content }</textarea>
		</div>
		<button type="submit" class="btn btn-primary">수정</button>
	</form>
</div>
<jsp:include page="/template/Footer.jsp" />



