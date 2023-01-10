
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<style>

.test_class{  
max-width: 100%;  
max-height: 100%;  
left: 0;  
right: 0;  
top: 0;  
bottom: 0;  
margin: auto;  
overflow: auto;  
position: fixed;  
}

</style>

<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 65px;">

<!-- 
	<div class="jumbotron bg-warning">
		<h1>메인 화면 <small>자료실</small></h1>
	</div>
	<h2>${INDEX }</h2>
	 -->
	 <!-- 
			<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			        <img src="..." class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="..." class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="..." class="d-block w-100" alt="...">
			    </div>
			  </div>
			</div>
			 -->
			 
			 <img src="이미지 경로" class = "test_class"/>


</div>	
<jsp:include page="/template/Footer.jsp" />
