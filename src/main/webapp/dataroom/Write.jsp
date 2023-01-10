<%@page import="model.BBSDto"%>
<%@page import="java.util.List"%>
<%@page import="model.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 65px;">

	<div class="jumbotron">
		<h1>등록 페이지</h1>
	</div>
	<form method="post" action='<c:url value="/DataRoom/Write.kosmo"/>'>
		<div class="form-group">
			<label><kbd class="lead">제목</kbd></label> <input type="text"
				class="form-control" placeholder="제목을 입력하세요" name="title" value="${param.title}">
		</div>
		<div class="form-group">
			<label><kbd class="lead">내용</kbd></label>
			<textarea class="form-control" rows="5" name="content">${param.content}</textarea>
		</div>
		<button type="submit" class="btn btn-primary">등록</button>
	</form>
</div>
<jsp:include page="/template/Footer.jsp" />
