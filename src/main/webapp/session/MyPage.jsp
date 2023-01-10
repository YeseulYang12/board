<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/Top.jsp"/>  
<div class="container" style="margin-top:50px;">

  <div class="jumbotron bg-warning">
    <small><%=session.getAttribute("USER-ID") %></small><h1>마이페이지</h1>      
  </div>
  
  
  
  <%if(session.getAttribute("USER-ID")!=null){ %>
  <a class="btn btn-info" href="Logout.jsp">로그아웃</a>
	<%} %>
</div>
<jsp:include page="/template/Footer.jsp"/>